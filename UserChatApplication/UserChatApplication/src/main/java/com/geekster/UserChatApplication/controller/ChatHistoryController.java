package com.geekster.UserChatApplication.controller;

import com.geekster.UserChatApplication.dao.StatusRepo;
import com.geekster.UserChatApplication.dao.UserRepo;
import com.geekster.UserChatApplication.model.ChatHistory;
import com.geekster.UserChatApplication.model.Status;
import com.geekster.UserChatApplication.model.Users;
import com.geekster.UserChatApplication.service.ChatHistoryService;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;

@RestController
@RequestMapping(value ="/api/v1/chat")
public class ChatHistoryController {

    @Autowired
    ChatHistoryService chatHistoryService;
    @Autowired
    UserRepo userRepo;
    @Autowired
    StatusRepo statusRepo;
    @PostMapping(value = "/send-message")
    public ResponseEntity<String> saveMessage(@RequestBody String requestData){
        JSONObject jsonObject=new JSONObject(requestData);
        JSONObject validationList=validateRequest(jsonObject);
        if(validationList.isEmpty()){
            ChatHistory chat=setChatHistory(jsonObject);
            int chatId=chatHistoryService.saveMessage(chat);
            return new ResponseEntity<>("message sent-"+chatId, HttpStatus.CREATED);
        }else{
            return new ResponseEntity<String>("enter required parameters"+validationList.toString(), HttpStatus.BAD_REQUEST);
        }
    }
    @GetMapping(value = "/get-chat")
    public ResponseEntity<String> getChatByUserId(@RequestParam int senderId){
        JSONObject response=chatHistoryService.getChatByUserId(senderId);
        return new ResponseEntity<>(response.toString(), HttpStatus.OK);
    }
    @GetMapping(value = "get-conversation")
    public ResponseEntity<String> getConversationBetweenTwoUsers(@RequestParam int user1, @RequestParam int user2){
        JSONObject response=chatHistoryService.getConversation(user1, user2);
        return new ResponseEntity<>(response.toString(),HttpStatus.OK);
    }
    private ChatHistory setChatHistory(JSONObject jsonObject) {

        ChatHistory chatHistory=new ChatHistory();

        int senderId=jsonObject.getInt("sender");
        int receiverId=jsonObject.getInt("receiver");
        String message=jsonObject.getString("message");

        Users senderObj=userRepo.findById(senderId).get();
        Users receiverObj=userRepo.findById(receiverId).get();

        chatHistory.setSender(senderObj);
        chatHistory.setReceiver(receiverObj);
        chatHistory.setMessage(message);

        Status status=statusRepo.findById(1).get();

        chatHistory.setStatusId(status);

        Timestamp createTime=new Timestamp(System.currentTimeMillis());
        chatHistory.setCreatedDate(createTime);
        return chatHistory;

    }

    private JSONObject validateRequest(JSONObject jsonObject) {
        JSONObject errorObj = new JSONObject();
        if(!jsonObject.has("sender")) {
            errorObj.put("sender", "Missing parameter");
        }
        if(!jsonObject.has("receiver")) {
            errorObj.put("receiver", "Missing parameter");
        }
        if(jsonObject.has("message")) {
            String message = jsonObject.getString("message");
            if(message.isBlank() || message.isEmpty()) {
                errorObj.put("message", "message body can't be empty");
            }
        } else {
            errorObj.put("message", "Missing parameter");
        }
        return errorObj;

    }

}
