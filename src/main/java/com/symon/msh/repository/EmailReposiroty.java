package com.symon.msh.repository;

import com.symon.msh.dto.EmailDto;
import jakarta.mail.MessagingException;

public interface EmailReposiroty {
    public void sendEmail(EmailDto emailDto) throws MessagingException;
}
