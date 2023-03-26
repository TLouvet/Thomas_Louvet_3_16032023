package com.chatop.ChatopApi.model;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.Instant;

@Data
@MappedSuperclass
public abstract class DbEntity {
    @Column(updatable = false)
    @CreationTimestamp
    protected Instant created_at;

    @UpdateTimestamp
    protected Instant updated_at;
}
