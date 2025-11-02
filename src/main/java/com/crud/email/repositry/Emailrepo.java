package com.crud.email.repositry;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import com.crud.email.entity.User;
@Component
@Repository
public interface Emailrepo extends JpaRepository<User,Long>{
    
    List<User> findByStatusAndSechedaLocalDateTimeBefore(String status, LocalDateTime time);
} 
