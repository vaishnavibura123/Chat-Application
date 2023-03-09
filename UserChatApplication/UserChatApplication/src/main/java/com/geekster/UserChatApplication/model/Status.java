package com.geekster.UserChatApplication.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "tbl_status")
public class Status {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="status_id")
    private int statusId;
    @Column(name="status_name")
    private String statusName;
    @Column(name="status_description")
    private String statusDescription;

}
