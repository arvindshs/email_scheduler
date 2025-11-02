package com.crud.email.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.crud.email.entity.User;
import com.crud.email.repositry.Emailrepo;
@Service
public class EmailService {
    @Autowired
    Emailrepo emailrepo;
    public User saveEmail(User user){
        return emailrepo.save(user);
    }

    public Optional<User> findemail(Long id){
            return emailrepo.findById(id);
    }

    public List<User> findpendingEmails(){
        return emailrepo.findByStatusAndSechedaLocalDateTimeBefore("PENDING", LocalDateTime.now());
    }

    public String markedasSent(Long id){
        User user =emailrepo.findById(id).orElseThrow(() -> new RuntimeException("mail not found"));
        user.setStatus("SENT");
        emailrepo.save(user);
        return "Marked as sent";
    }
    public void deletemailaftersent(User user){
        if("SENT".equals(user.getStatus())){
            emailrepo.delete(user);
        }
    }
    public List<User> showallmail(){
        return emailrepo.findAll();
    }

}
