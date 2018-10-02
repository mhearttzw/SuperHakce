package com.superhakce.algorithm.config;

import com.atomikos.jdbc.AtomikosDataSourceBean;
import com.mysql.jdbc.jdbc2.optional.MysqlXADataSource;
import com.superhakce.algorithm.config.MasterConfig;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;
import java.sql.SQLException;


/**
 * SqlSessionFactoryBuilder：build方法创建SqlSessionFactory实例。
 * SqlSessionFactory：创建SqlSession实例的工厂。
 * SqlSession：用于执行持久化操作的对象，类似于jdbc中的Connection。
 * SqlSessionTemplate：MyBatis提供的持久层访问模板化的工具，线程安全，可通过构造参数或依赖注入SqlSessionFactory实例
 *
 * 从库的数据源模板，应用在从库所对应的Dao层上（扫描对应的mapper），实现从数据源的指定+增删改查
 * @author SuperHakce
 * @date Create in 2018/9/28 11:10
 */
@Configuration   // ---> 标注此注解，Spring—Boot启动时，会自动进行相应的主数据源配置 -->注入Bean
@MapperScan(basePackages = "com.superhakce.algorithm.mapper.slave", sqlSessionTemplateRef = "slaveSqlSessionTemplate")
@Slf4j
public class SlaveDBSource {

    // 配置从数据源
    @Bean(name = "SlaveDB")
    public DataSource testDataSource(SlaveConfig slaveConfig) throws SQLException {

        /**
         * MySql数据库驱动 实现 XADataSource接口
         */
        MysqlXADataSource mysqlXaDataSource = new MysqlXADataSource();
        mysqlXaDataSource.setUrl(slaveConfig.getUrl());
        mysqlXaDataSource.setPinGlobalTxToPhysicalConnection(true);
        mysqlXaDataSource.setPassword(slaveConfig.getPassword());
        mysqlXaDataSource.setUser(slaveConfig.getUsername());
        mysqlXaDataSource.setPinGlobalTxToPhysicalConnection(true);

		/**
		 * Postgresql数据库驱动 实现 XADataSource
		 * 包 --> org.postgresql.xa.PGXADataSource;
		 */
		/*PGXADataSource pgxaDataSource = new PGXADataSource();
		pgxaDataSource.setUrl(masterConfig.getUrl());*/

        /**
         * 设置分布式-- 从数据源
         */
        AtomikosDataSourceBean xaDataSource = new AtomikosDataSourceBean();
        xaDataSource.setXaDataSource(mysqlXaDataSource);
        xaDataSource.setUniqueResourceName("SlaveDB");

        xaDataSource.setMinPoolSize(slaveConfig.getMinPoolSize());
        xaDataSource.setMaxPoolSize(slaveConfig.getMaxPoolSize());
        xaDataSource.setMaxLifetime(slaveConfig.getMaxLifetime());
        xaDataSource.setBorrowConnectionTimeout(slaveConfig.getBorrowConnectionTimeout());
        xaDataSource.setLoginTimeout(slaveConfig.getLoginTimeout());
        xaDataSource.setMaintenanceInterval(slaveConfig.getMaintenanceInterval());
        xaDataSource.setMaxIdleTime(slaveConfig.getMaxIdleTime());
        xaDataSource.setTestQuery(slaveConfig.getTestQuery());
        log.info("从数据源注入成功.....");

        return xaDataSource;
    }

    @Bean(name = "slaveSqlSessionFactory")
    public SqlSessionFactory masterSqlSessionFactory(@Qualifier("SlaveDB") DataSource dataSource) throws Exception {
        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        bean.setDataSource(dataSource);
        return bean.getObject();
    }

    @Bean(name = "slaveSqlSessionTemplate")
    public SqlSessionTemplate masterSqlSessionTemplate(
            @Qualifier("slaveSqlSessionFactory") SqlSessionFactory sqlSessionFactory) throws Exception {
        return new SqlSessionTemplate(sqlSessionFactory);
    }

}