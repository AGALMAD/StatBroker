package com.statbroker.backend.service;

import com.statbroker.backend.dto.User.UserDto;
import com.statbroker.backend.mapper.UserMapper;
import com.statbroker.backend.model.User;
import com.statbroker.backend.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;


    public boolean existUserById(String userEmail){
        return userRepository.existsByEmail(userEmail);
    }

    public UserDto getUserByEmail(String userEmail){
        Optional<User> user = userRepository.findByEmail(userEmail);

        if(user.isEmpty())
            throw new UsernameNotFoundException("User not found");

        return userMapper.userToUserDto(user.get());
    }
}
