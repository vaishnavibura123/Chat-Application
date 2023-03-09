package com.geekster.UserChatApplication.controller;

import com.geekster.UserChatApplication.model.Status;
import com.geekster.UserChatApplication.service.StatusService;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/status")
public class StatusController {
    @Autowired
    StatusService statusService;
    @PostMapping("/create-status")
    public ResponseEntity<String> createStatus(@RequestBody String statusData){

        Status status=setStatus(statusData);

        int statusId=statusService.saveStatus(status);

        return new ResponseEntity<>("Status saved-"+statusId, HttpStatus.CREATED);

    }
    private Status setStatus(String statusData){
        Status status=new Status();

        JSONObject json=new JSONObject(statusData);

        String statusName=json.getString("statusName");
        String statusDescription=json.getString("statusDescription");
        status.setStatusName(statusName);
        status.setStatusDescription(statusDescription);

        return status;

    }
}
