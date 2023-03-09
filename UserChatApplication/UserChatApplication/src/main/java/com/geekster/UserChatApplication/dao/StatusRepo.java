package com.geekster.UserChatApplication.dao;

import com.geekster.UserChatApplication.model.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StatusRepo extends JpaRepository<Status,Integer> {
}
