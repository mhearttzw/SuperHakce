package com.superhakce.avengers.respository;


import com.superhakce.avengers.entity.SignUser;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.sql.Timestamp;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

/**
 * @author echelon
 * @email 2954632969@qq.com
 * @created_time 2018/9/29 18:47
 * @description 用款申请表dao层
 */
public interface SignUserRepository extends JpaRepository<SignUser, Long>, JpaSpecificationExecutor<SignUser> {

    /**
     * 根据id查询用户详情
     * @param id
     * @return
     */
    Optional<SignUser> findById(Long id);

    List<SignUser> findByNickname(String nickname);

    List<SignUser> findByPhone(String phone);

}


