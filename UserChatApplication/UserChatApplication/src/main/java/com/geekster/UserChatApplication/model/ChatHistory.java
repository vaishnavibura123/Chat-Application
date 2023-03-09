package com.geekster.UserChatApplication.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "tbl_chat_history")
public class ChatHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "chat_id")
    private int ChatId;
    @JoinColumn(name="sender_user_id")
    @ManyToOne
    private Users sender;
    @JoinColumn(name="receiver_user_id")
    @ManyToOne
    private Users receiver;

    @Column(name="message")
    private String message;
    @Column(name="created_date")
    private Timestamp createdDate;
    @Column(name="updated_date")
    private Timestamp updatedDate;


    @ManyToOne
    @JoinColumn(name = "status_id")
    private Status statusId;

}
