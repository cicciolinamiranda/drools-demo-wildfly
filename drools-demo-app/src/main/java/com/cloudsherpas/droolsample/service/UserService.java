package com.cloudsherpas.droolsample.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cloudsherpas.droolsample.domain.UserModel;
import com.cloudsherpas.droolsample.api.resource.UserResource;
import com.cloudsherpas.droolsample.repository.UserRepository;
import org.springframework.transaction.annotation.Transactional;
import org.mindrot.jbcrypt.BCrypt;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    private ModelMapper modelMapper;

    public UserService() {
        modelMapper = new ModelMapper();
    }

    @Transactional
    public void createUser(final UserResource dto) {
        String pw_hash = BCrypt.hashpw(dto.getPassword(), BCrypt.gensalt());

        UserModel model = new UserModel(
    			dto.getUsername(),
    			pw_hash,
    			dto.getRole());
        userRepository.save(model);
    }

    public void updateUser(final UserResource dto) {
        UserModel model = new UserModel(
        			dto.getUsername(),
        			dto.getPassword(),
        			dto.getRole());
        userRepository.save(model);
    }

    public UserResource getUserByUsername(final String username) {
    	UserModel result = userRepository.findUserByUsername(username);
        return modelMapper.map(result, UserResource.class);
    }

    public UserResource getUserByUserID(final Long userId) {
        UserModel result = userRepository.findOne(userId);
        return modelMapper.map(result, UserResource.class);
    }
}
