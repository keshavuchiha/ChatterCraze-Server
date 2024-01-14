package com.chattercraze.Server.Models;

import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.jpa.repository.JpaRepository;

import java.sql.Time;
import java.util.UUID;

@Entity
@Table(name="users")
@Getter
@Setter
@ToString
@EqualsAndHashCode
public class User {
    @Id
    @GeneratedValue
    private UUID user_id;
    @Column(name="username",nullable = false)
    private String username;
    @Column(name="email",nullable = false,unique = true)
    private String email;
    @Column(name="password",nullable = false)
    private String password;
    @Column(name="created_at")
    private Time created_at;
    @Column(name="updated_at")
    private Time updated_at;
}
