package com.project.ecommerce.service;

import com.project.ecommerce.dto.LoginRequest;
import com.project.ecommerce.dto.LoginResponse;
import com.project.ecommerce.dto.UserDTO;
import com.project.ecommerce.entity.User;
import com.project.ecommerce.exception.DuplicateResourceException;
import com.project.ecommerce.exception.ResourceNotFoundException;
import com.project.ecommerce.mapper.UserMapper;
import com.project.ecommerce.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Transactional(readOnly = true)
    public List<UserDTO> getAll(){
        return userRepository.findAll().stream().map((u) -> userMapper.toDto(u)).toList();
    }

    @Transactional(readOnly = true)
    public UserDTO getById(Long id){
        return userMapper.toDto(userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("User", "id", id)));
    }

    public UserDTO saveUser(UserDTO userDTO, String role){
        User userSameEmail = userRepository.findByEmail(userMapper.toEntity(userDTO, role).getEmail()).orElse(null);
        if(userSameEmail != null) {
            throw new DuplicateResourceException("Cet email exite déjà");
        }
        User userSameUsername = userRepository.findByUsername(userMapper.toEntity(userDTO, role).getUsername()).orElse(null);
        if(userSameUsername != null) {
            throw new DuplicateResourceException("Ce nom d'utilisateur exite déjà");
        }
        return userMapper.toDto(userRepository.save(userMapper.toEntity(userDTO, role)));
    }

    public UserDTO update(Long id, UserDTO userDTO){
        User user = userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("User", "id", id));
        return userMapper.toDto(userRepository.save(userMapper.updateEntity(userDTO, user)));
    }

    public void delete(Long id){
        User user = userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("User", "id", id));
        userRepository.delete(user);
    }

    public LoginResponse authenticate(LoginRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getUsername(),
                        request.getPassword()
                )
        );

        User user = userRepository.findByUsername(request.getUsername())
                .orElseThrow(() -> new RuntimeException("User not found"));
        String jwtToken = jwtService.generateToken(user.getUsername(), user.getRole());

        return new LoginResponse(jwtToken, user.getUsername(), user.getRole());
    }
}
