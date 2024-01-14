package com.chattercraze.Server.dao;

import com.chattercraze.Server.Models.Society;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface SocietyRespository extends JpaRepository<Society, UUID> {
    Page<Society> findByName(String name, Pageable pageable);

}
