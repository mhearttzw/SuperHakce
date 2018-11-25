package com.superhakce.avengers.respository;


import com.superhakce.avengers.entity.SignUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

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

    Optional<SignUser> findByNickname(String nickname);

    Optional<SignUser> findByPhone(String phone);

    /**
     * 用户修改密码
     * @param userId
     * @param oldPwd
     * @param newPwd
     * @return
     */
    @Modifying
    @Query("update SignUser o set o.password =?3 where o.id =?1 and o.password=?2")
    int updatePassword(Long userId, String oldPwd, String newPwd);

}


