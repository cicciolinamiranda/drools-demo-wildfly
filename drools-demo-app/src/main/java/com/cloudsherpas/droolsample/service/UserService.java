package com.cloudsherpas.droolsample.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import com.cloudsherpas.droolsample.domain.UserModel;
import com.cloudsherpas.droolsample.dto.UserDTO;
import com.cloudsherpas.droolsample.repository.UserRepository;

public class UserService {

    @Autowired
    private UserRepository userRepostory;

    @Autowired
    private ModelMapper modelMapper;

    public UserService() {
        modelMapper = new ModelMapper();
    }

    public Long createUser(final UserDTO dto) {
    	UserModel model = new UserModel(
    			dto.getUsername(),
    			dto.getPassword(),
    			dto.getRole());
        return userRepostory.save(model);
    }

    public Long updateUser(final UserDTO dto) {
        UserModel model = new UserModel(
        			dto.getUsername(),
        			dto.getPassword(),
        			dto.getRole());
        return userRepostory.save(model);
    }

    public UserDTO getUserByUsername(final String username) {
    	UserModel result = userRepostory.findUserByUsername(username);
        return modelMapper.map(result, UserDTO.class);
    }

    public UserDTO getUserByUserID(final Long userId) {
        UserModel result = userRepostory.findOne(userId);
        return modelMapper.map(result, UserDTO.class);
    }
}
