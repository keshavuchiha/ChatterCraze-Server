package com.chattercraze.Server.Controllers;

import com.chattercraze.Server.configs.ControllerException;
import com.chattercraze.Server.dao.SocietyRespository;
import com.chattercraze.Server.services.SocietyService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/v1/society")
public class SocietyController {
    @Autowired
    SocietyRespository societyRespository;
    @Autowired
    SocietyService societyService;

    @GetMapping(value="/getNames")
    ResponseEntity<Object> getSocietNames(){
        try {
            List<String> result = societyService.getNames();
            return new ResponseEntity<>(result, HttpStatus.OK);
        } catch (ControllerException ex) {
            return new ResponseEntity<>(ex.getMessage(), ex.getHttpStatus());
        } catch (Exception ex) {
            return ControllerException.throwGenericError();
        }
    }
    @Transactional
    @GetMapping(value="/society")
    ResponseEntity<Object> getSociety(@RequestParam String name,@RequestParam UUID userId){
        try {
            String result = societyService.getSocietyForUser(name, userId);
            return new ResponseEntity<>(result, HttpStatus.OK);
        } catch (ControllerException ex) {
            return new ResponseEntity<>(ex.getMessage(), ex.getHttpStatus());
        } catch (Exception ex) {
            return ControllerException.throwGenericError();
        }
    }
}
