package com.crud.email.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.crud.email.entity.User;
import com.crud.email.service.EmailService;

@RestController
@RequestMapping("/emails")
public class EmailController {
   
    @Autowired
    private EmailService emailService;
    // @Autowired
    // private User user;

    @GetMapping("/allmail")
    public List<User> getallmails(){
        return emailService.showallmail();
    }
    
    @PostMapping("/post")
    public ResponseEntity<?> createmail(@RequestBody User user){
        user.setStatus("PENDING");
        User saved = emailService.saveEmail(user);
        return ResponseEntity.ok(saved);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getemamil(@PathVariable Long id){
        Optional<User> email = emailService.findemail(id);
        if (email.isPresent()) {
            return ResponseEntity.ok(email.get());
        }
        else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("EMial not found");
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletemail(@PathVariable Long id){
        try {
            Optional<User> email = emailService.findemail(id);
            if (email.isPresent()) {
                User user = email.get();
                if ("SENT".equalsIgnoreCase(user.getStatus())) {
                    emailService.deletemailaftersent(user);
                    return ResponseEntity.ok("Email has been deleted");
                }
                else{
                    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("email cannot be deleteed");
                }
            }
            else{
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("id not found" + id);
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("error deleting mail" + e.getMessage());
        }
    }
}
