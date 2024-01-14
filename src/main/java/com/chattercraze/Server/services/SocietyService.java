package com.chattercraze.Server.services;

import ch.qos.logback.core.encoder.EchoEncoder;
import com.chattercraze.Server.Models.Society;
import com.chattercraze.Server.Models.SocietyMembers;
import com.chattercraze.Server.configs.ControllerException;
import com.chattercraze.Server.dao.SocietyMembersRepository;
import com.chattercraze.Server.dao.SocietyRespository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class SocietyService {
    @Autowired
    SocietyMembersRepository societyMembersRepository;

    @Autowired
    SocietyRespository societyRespository;

    public List<String> getNames() throws ControllerException {
        Pageable page = PageRequest.of(0, 4, Sort.by("name"));
        List<String> societyList = societyRespository.findAll(page).stream().map(Society::getName).toList();
        if (societyList.isEmpty()) {
            throw new ControllerException("Society List is empty",HttpStatus.NO_CONTENT);
        }
        return societyList;

    }

    public String getSocietyForUser(String name, UUID userId) throws ControllerException {
        Pageable pageable = PageRequest.of(0, 1);
        Page<Society> societyIdPage = societyRespository.findByName(name, pageable);
        if (societyIdPage.isEmpty() || societyIdPage.getContent().isEmpty()) {
            throw new ControllerException("Society not found", HttpStatus.NO_CONTENT);
        }
        Society society = societyIdPage.getContent().get(0);
        Page<SocietyMembers> societyMembersPage = societyMembersRepository.findByUserIdAndSocietyId(
                userId, society.getSocietyId(), pageable);
        if (societyMembersPage.isEmpty() || societyMembersPage.getContent().isEmpty()) {
            throw new ControllerException(String.format("User has not joined %s", society.getName()), HttpStatus.NO_CONTENT);
        }
        SocietyMembers societyMembers = societyMembersPage.getContent().get(0);
        return societyMembers.getRole();
    }
}
