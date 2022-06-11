package com.dev.cry.Entity;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@MappedSuperclass
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class Entity implements Serializable {
    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt = LocalDateTime.now();

    @Column(name = "modified_at", nullable = true, updatable = true)
    private LocalDateTime modifiedAt = LocalDateTime.now();

    @Column(name = "deleted",nullable = false,updatable = true)
    private Boolean deleted = false;
}
