package com.orionsolution.auth.controller;

import com.orionsolution.auth.model.ResourcesPolicyDataDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;


@Slf4j
@RestController
@RequestMapping("/api/v1/login")
public class LoginController {

    //TODO: Implement the login method

    @PostMapping(consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public ResponseEntity<ResourcesPolicyDataDTO> login(@RequestParam Map<String, String> credentials) {
        return  ResponseEntity.ok(new ResourcesPolicyDataDTO("John", "Doe", "", "", ""));
    }


}