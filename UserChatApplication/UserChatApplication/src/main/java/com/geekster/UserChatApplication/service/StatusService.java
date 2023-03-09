package com.geekster.UserChatApplication.service;

import com.geekster.UserChatApplication.dao.StatusRepo;
import com.geekster.UserChatApplication.model.Status;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StatusService {
    @Autowired
    private StatusRepo statusRepo;

    public int saveStatus(Status status) {
        Status statusObj=statusRepo.save(status);
        return statusObj.getStatusId();
    }
}

