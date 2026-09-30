package com.alpha.userservice.service;

import com.alpha.payload.dto.UserDTO;
import com.alpha.userservice.entity.UserEntity;
import com.alpha.userservice.mapper.UserMapper;
import com.alpha.userservice.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserMapper userMapper;

    private final UserRepository userRepository;
    public List<UserDTO> getAllUsers(){
        return userRepository.findAll()
                .stream()
                .map(userMapper::toDTO)
                .toList();
    }
    public UserDTO getUserByEmail(String email){
        UserEntity user =  userRepository.findByEmail(email);
        if(user == null){
            throw new RuntimeException("User not found with email " + email);
        }
        return userMapper.toDTO(user);
    }

    public UserDTO getUserById(Long id){
        UserEntity user =  userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found with id " + id));
        return userMapper.toDTO(user);
    }
}
