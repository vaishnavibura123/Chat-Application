package com.geekster.UserChatApplication.dao;

import com.geekster.UserChatApplication.model.Users;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepo extends JpaRepository<Users,Integer> {

    @Query(value = "Select * from tbl_user where user_id= :userId and status_id=1", nativeQuery = true)
    List<Users> getUserByUserId(Integer userId);
    @Query(value = "select * from tbl_user where status_id=1",nativeQuery = true)
    List<Users> getAllUsers();

    @Query(value = "select * from tbl_user where user_name= :username and status_id=1", nativeQuery = true)
    List<Users> findByUsername(String username);
    @Modifying
    @Transactional
    @Query(value = "update tbl_user set status_id=2 where user_id= :userId", countQuery = "select count(*) from tbl_user", nativeQuery = true)
    void deleteUserByUserId(String userId);
}
