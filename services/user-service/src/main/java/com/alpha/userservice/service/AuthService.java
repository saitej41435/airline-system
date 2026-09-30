package com.alpha.userservice.service;

import com.alpha.enums.UserRole;
import com.alpha.userservice.config.JwtProvider;
import com.alpha.payload.dto.UserDTO;
import com.alpha.payload.response.AuthResponse;
import com.alpha.userservice.entity.UserEntity;
import com.alpha.userservice.mapper.UserMapper;
import com.alpha.userservice.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final JwtProvider jwtProvider;
    private final CustomUserDetailsService customUserDetailsService;
    private final PasswordEncoder passwordEncoder;
    private final UserMapper userMapper;

    /*
        1. check email already exists
        2. encode the password using BCrypt
        3. save the user data in DB
        4. Generate JWT Token
        5. Return token and user information
     */

    public  AuthResponse signUp(UserDTO request) throws Exception {
        if(userRepository.existsByEmail(request.getEmail())){
            throw new Exception("User already exists with email "+ request.getEmail());
        }
        if(request.getRole().equals(UserRole.ROLE_SYSTEM_ADMIN)){
            throw new Exception("You cannot signup system admin");
        }
        UserEntity user = userRepository.save(userMapper.toEntity(request));

        Authentication authentication = new UsernamePasswordAuthenticationToken(user.getEmail(), user.getPassword());
        String jwt = jwtProvider.generateToken(authentication, user.getId());

        AuthResponse authResponse = new AuthResponse();
        authResponse.setJWT(jwt);
        authResponse.setUser(userMapper.toDTO(user));
        authResponse.setMessage("Welcome "+ user.getName());
        authResponse.setTitle("Registed successfully");
        return authResponse;
    }

     /*
        1. email and password as request
        2. find user by email (get the userDetails)
        2. compare password with BCrypt
        3. update lastlogin time
        4. Generate JWT Token
        5. Return token and user information
     */



    public AuthResponse login(String email, String password) throws Exception{
        Authentication authentication = authenticate(email, password);
        UserEntity user = userRepository.findByEmail(email);
        user.setLastLogin(LocalDateTime.now());
        userRepository.save(user);

        String jwt =jwtProvider.generateToken(authentication, user.getId());
        AuthResponse authResponse = new AuthResponse();
        authResponse.setJWT(jwt);
        authResponse.setUser(userMapper.toDTO(user));
        authResponse.setMessage("Welcome " + user.getName());
        authResponse.setTitle("Logged in Successfully");
        return authResponse;
    }

    public Authentication authenticate(String email, String password) throws Exception{
        UserDetails userDetails = customUserDetailsService.loadUserByUsername(email);

        if(!passwordEncoder.matches(password,userDetails.getPassword())){
            throw new Exception("invalid password");
        }
        return new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
    }
}
