package com.mvcapp.shawarma.service;

import com.mvcapp.shawarma.exception.UserAlreadyExistException;
import com.mvcapp.shawarma.exception.UserDoesNotExistException;
import com.mvcapp.shawarma.model.dto.UserCreateRequest;
import com.mvcapp.shawarma.model.dto.UserUpdateRequest;
import com.mvcapp.shawarma.model.entity.UserEntity;
import com.mvcapp.shawarma.model.security.Role;
import com.mvcapp.shawarma.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Date;

// TODO: 17.04.2023 сделать passwordencoder
@Service
public class UserService {

    private final UserRepository userRepo;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserRepository userRepo, PasswordEncoder passwordEncoder) {
        this.userRepo = userRepo;
        //this.passwordEncoder = passwordEncoder;
        this.passwordEncoder = passwordEncoder;
    }

    public UserEntity getMe() throws UserDoesNotExistException {
        var user = userRepo.findByEmail(getMyEmail());

        if (user == null) {
            throw new UserDoesNotExistException("User does not exist");
        }

        return user;
    }

    @Transactional
    public UserEntity updateMe(UserUpdateRequest request) throws UserAlreadyExistException {
        if (userRepo.existsByEmail(request.getEmail()) || userRepo.existsByPhoneNumber(request.getPhone())) {
            throw new UserAlreadyExistException("User already exist with this email or phone");
        }

        var user = userRepo.findByEmail(getMyEmail());
        user.setEmail(request.getEmail());
        user.setFirstName(request.getFirstName());
        user.setLastName(request.getLastName());
        user.setPhoneNumber(request.getPhone());
        // TODO: 17.04.2023 сделать пассвворд енкодер
        user.setPassword(passwordEncoder.encode(request.getPassword()));

        return user;
    }

    @Transactional
    public UserEntity createMe(UserCreateRequest request) throws UserAlreadyExistException {
        // TODO: 17.04.2023 проверка на существование емайла
        if (userRepo.existsByEmail(request.getEmail()) || userRepo.existsByPhoneNumber(request.getPhone())) {
            throw new UserAlreadyExistException("User already exist with this email or phone");
        }

        var user = UserEntity.builder()
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .phoneNumber(request.getPhone())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                //.password(request.getPassword())
                .role(Role.USER)
                .createdAt(new Timestamp(new Date().getTime()))
                .build();

        return userRepo.save(user);
    }

    @Transactional
    public void deleteMe() throws UserDoesNotExistException {
        var user = userRepo.findByEmail(getMyEmail());

        if (user == null) {
            throw new UserDoesNotExistException("User does not exist");
        }

        userRepo.delete(user);
    }

    public String getMyEmail() {
        return SecurityContextHolder.getContext().getAuthentication().getName();
    }

    public Integer getMyId() {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        var user = userRepo.findByEmail(email);

        return user.getId();
    }
    public void save(UserEntity user){
        userRepo.save(user);
    }

    public UserEntity findByEmail(String email){
        return userRepo.findByEmail(email);
    }
}
