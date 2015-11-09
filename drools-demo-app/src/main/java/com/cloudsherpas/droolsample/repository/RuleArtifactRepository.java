package com.cloudsherpas.droolsample.repository;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.cloudsherpas.droolsample.domain.RuleArtifact;

/**
 * @author CMiranda
 */
public interface RuleArtifactRepository extends PagingAndSortingRepository<RuleArtifact, Long> {

    RuleArtifact findByActiveTrue();

}