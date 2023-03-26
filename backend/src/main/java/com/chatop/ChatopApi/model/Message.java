package com.chatop.ChatopApi.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name="messages")
public class Message extends DbEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int rental_id;

    private int user_id;

    @Column(length = 2000)
    private String message;

}
