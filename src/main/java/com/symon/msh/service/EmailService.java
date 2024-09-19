package com.symon.msh.service;

import com.symon.msh.dto.EmailDto;
import com.symon.msh.repository.EmailReposiroty;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

@Service
public class EmailService implements EmailReposiroty {

    private final JavaMailSender javaMailSender;
    private final TemplateEngine templateEngine;

    public EmailService(JavaMailSender javaMailSender, TemplateEngine templateEngine) {
        this.javaMailSender = javaMailSender;
        this.templateEngine = templateEngine;
    }

    @Override
    public void sendEmail(EmailDto emailDto) throws MessagingException {
        try {
            MimeMessage message = javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");
            helper.setTo(emailDto.getRecipient());
            helper.setSubject(emailDto.getSubject());

            Context context = new Context();
            context.setVariable("message", emailDto.getMessage());
            String content = templateEngine.process("email-template", context);

            helper.setText(content, true);
            javaMailSender.send(message);
        } catch (Exception e) {
            throw  new RuntimeException("Error sending email");
        }
    }
}
