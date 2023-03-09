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
@Table(name = "tbl_user")
public class Users {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "user_id")
    private int userId;
    @Column(name="user_name")
    private String username;
    @Column(name="password")
    private String password;

    @Column(name="email")
    private  String email;
    @Column(name="phone_number")
    private String phoneNumber;
    @Column(name="gender")
    private String gender;
    @Column(name="first_name")
    private String firstName;
    @Column(name="last_name")
    private String lastName;
    @Column(name="age")
    private Integer age;
    @Column(name="created_date")
    private Timestamp createdDate;
    @Column(name="updated_date")
    private Timestamp updatedDate;
    @ManyToOne
    @JoinColumn(name="status_id")
    private Status statusId;

}
