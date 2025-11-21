package com.crud.email.sechdules;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.crud.email.entity.User;
import com.crud.email.service.EmailService;

@Component
public class EmailScheduler {
    @Autowired
    private EmailService emailService;

    @Autowired
    private JavaMailSender javaMailSender;
    @Value("${spring.mail.username}")
    private String fromEmail;
    @Scheduled(cron = "0 0 18 * * *", zone = "Asia/Kolkata")

    public void schedulingemail(){
        List<User> mails =  emailService.findpendingEmails();

        for(User emails : mails){
            if("PENDING".equals(emails.getStatus())){
                if(emails.getSechedaLocalDateTime().isBefore(LocalDateTime.now()) || emails.getSechedaLocalDateTime().isEqual(LocalDateTime.now())){
                    try {
                        SimpleMailMessage message = new SimpleMailMessage();
                        message.setTo(emails.getEmail());
                        message.setSubject(emails.getSubject());
                        message.setText(emails.getMessage());
                        message.setFrom(fromEmail);

                        javaMailSender.send(message);
                        
                        // emailService.saveEmail(emails);
                        emailService.markedasSent(emails.getId());
                    } catch (Exception e) {
                        System.out.println("Failed to sent the email"+ emails.getEmail());
                        e.getStackTrace();
                    }
                    
                }
            }
        }
    }

}
