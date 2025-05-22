package com.matheus.services;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.matheus.dtos.EmailRequestDTO;

@FeignClient(name = "email-service", url = "http://localhost:8080/api/email")
public interface EmailService {

  @PostMapping("/send")
  void sendEmail(@RequestBody EmailRequestDTO emailRequestDTO);
}
