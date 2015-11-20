package com.cloudsherpas.droolsample.repository;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.cloudsherpas.droolsample.domain.UserModel;

public interface UserRepository extends PagingAndSortingRepository<UserModel, Long> {

    UserModel findUserByUsername(final String username);
}
