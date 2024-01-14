package com.chattercraze.Server.dao;

import com.chattercraze.Server.Models.SocietyMembers;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface SocietyMembersRepository extends JpaRepository<SocietyMembers, UUID> {
    Page<SocietyMembers> findByUserIdAndSocietyId(UUID user_id, UUID society_id, Pageable pageable);
}
