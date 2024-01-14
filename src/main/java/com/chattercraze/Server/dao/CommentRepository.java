package com.chattercraze.Server.dao;

import com.chattercraze.Server.Models.Comments;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface CommentRepository extends JpaRepository<Comments, UUID> {
}
