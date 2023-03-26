package com.chatop.ChatopApi.model;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;

@Data
@Entity
@DynamicUpdate
@Table(name="rentals")
public class Rental extends DbEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private double surface;
    private double price;
    private String picture;

    @Column(length = 2000)
    private String description;

    @Column(nullable = false)
    private Long owner_id;
}
