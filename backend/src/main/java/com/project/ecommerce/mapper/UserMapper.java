package com.project.ecommerce.mapper;

import com.project.ecommerce.dto.UserDTO;
import com.project.ecommerce.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {
    @Autowired
    private PasswordEncoder passwordEncoder;

    public User toEntity(UserDTO dto, String role){
        if(dto == null){
            return  null;
        }
        User user = new User();
        user.setLastName(dto.getLastName());
        user.setFirstName(dto.getFirstName());
        user.setAddress(dto.getAddress());
        user.setEmail(dto.getEmail());
        user.setPhoneNumber(dto.getPhoneNumber());
        user.setUsername(dto.getUsername());
        user.setPassword(passwordEncoder.encode(dto.getPassword()));
        user.setRole(role);
        return user;
    }

    public UserDTO toDto(User user){
        if(user == null){
            return null;
        }
        UserDTO dto = new UserDTO();
        dto.setId(user.getId());
        dto.setLastName(user.getLastName());
        dto.setFirstName(user.getFirstName());
        dto.setAddress(user.getAddress());
        dto.setEmail(user.getEmail());
        dto.setPhoneNumber(user.getPhoneNumber());
        dto.setUsername(user.getUsername());
        dto.setRole(user.getRole());
        return dto;
    }

    public User updateEntity(UserDTO dto, User user){
        if(dto == null || user == null){
            return null;
        }
        user.setLastName(dto.getLastName());
        user.setFirstName(dto.getFirstName());
        user.setAddress(dto.getAddress());
        user.setEmail(dto.getEmail());
        user.setPhoneNumber(dto.getPhoneNumber());
        user.setUsername(dto.getUsername());
        user.setPassword(passwordEncoder.encode(dto.getPassword()));
        return user;
    }
}
