package com.symon.msh.controller;

import com.symon.msh.dto.EmailDto;
import com.symon.msh.repository.EmailReposiroty;
import jakarta.mail.MessagingException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MailController {

    private final EmailReposiroty emailReposiroty;

    public MailController(EmailReposiroty emailReposiroty) {
        this.emailReposiroty = emailReposiroty;
    }

    @PostMapping("/send-email")
    private ResponseEntity<String> sendEmail(@RequestBody EmailDto emailDto) throws MessagingException {
        emailReposiroty.sendEmail(emailDto);
        return ResponseEntity.ok("Email sent successfully");
    }
}
