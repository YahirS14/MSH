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

import java.lang.reflect.Field;
import java.util.Locale;

@Service
public class
EmailService implements EmailReposiroty {

    private final JavaMailSender javaMailSender;
    private final TemplateEngine templateEngine;

    public EmailService(JavaMailSender javaMailSender, TemplateEngine templateEngine) {
        this.javaMailSender = javaMailSender;
        this.templateEngine = templateEngine;
    }

    @Override
    public void sendEmail(EmailDto emailDto) throws MessagingException {
        try {

            System.out.println("emailDto = " + emailDto.getClass().getName());

            MimeMessage message = javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");
            helper.setTo(emailDto.getRecipient());
            helper.setSubject(emailDto.getSubject());

            Locale locale = Locale.forLanguageTag(emailDto.getLang());
            Context context = new Context(locale);
            context.setVariable("data", emailDto.toString());

            for (Field field : emailDto.getClass().getDeclaredFields()) {
                field.setAccessible(true);
                context.setVariable(field.getName(), field.get(emailDto));
            }

            String content = templateEngine.process(emailDto.getTemplate(), context);

            helper.setText(content, true);
            javaMailSender.send(message);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Error sending email");
        }
    }
}
