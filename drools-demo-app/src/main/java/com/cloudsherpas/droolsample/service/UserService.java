package com.cloudsherpas.droolsample.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cloudsherpas.droolsample.domain.UserModel;
import com.cloudsherpas.droolsample.dto.UserDTO;
import com.cloudsherpas.droolsample.repository.UserRepository;
import org.springframework.transaction.annotation.Transactional;
import org.mindrot.jbcrypt.BCrypt;

import java.util.Arrays;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    private ModelMapper modelMapper;

    public UserService() {
        modelMapper = new ModelMapper();
    }

    @Transactional
    public void createUser(final UserDTO dto) {
        String pw_hash = BCrypt.hashpw(dto.getPassword(), BCrypt.gensalt());

        UserModel model = new UserModel(
    			dto.getUsername(),
    			pw_hash,
    			dto.getRole());
        userRepository.save(model);
    }

    public void updateUser(final UserDTO dto) {
        UserModel model = new UserModel(
        			dto.getUsername(),
        			dto.getPassword(),
        			dto.getRole());
        userRepository.save(model);
    }

    public UserDTO getUserByUsername(final String username) {
    	UserModel result = userRepository.findUserByUsername(username);
        return modelMapper.map(result, UserDTO.class);
    }

    public UserDTO getUserByUserID(final Long userId) {
        UserModel result = userRepository.findOne(userId);
        return modelMapper.map(result, UserDTO.class);
    }
}
