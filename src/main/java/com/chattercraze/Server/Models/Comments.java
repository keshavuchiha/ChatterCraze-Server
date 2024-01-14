package com.chattercraze.Server.Models;

import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.sql.Time;
import java.util.UUID;

@Entity
@Table(name="comments")
@Getter
@Setter
@ToString
@EqualsAndHashCode
public class Comments {
    @Id
    @GeneratedValue
    @Column(name="comment_id")
    UUID commentId;
    @Column(name="user_id")
    UUID userId;
    @Column(name="post_id")
    UUID postId;
    String content;
    @Column(name="created_at")
    Time createdAt;
    @Column(name="updated_at")
    Time updatedAt;
    @PrimaryKeyJoinColumn
    @Column(name="parent_id")
    UUID parentId;
}
