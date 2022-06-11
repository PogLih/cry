package com.dev.cry.Entity;

import lombok.Data;

import javax.persistence.*;
import javax.persistence.Entity;
import java.time.LocalDateTime;
import java.util.Set;

@Data
@Entity
public class User extends com.dev.cry.Entity.Entity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    private String username;

    private String password;
    @ManyToMany
    @JoinTable(
            name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles;

//    public User(String username,String password){
//        this.username = username;
//        this.password = password;
//    }
}
