package com.cloudsherpas.droolsample.repository;

import java.util.Collection;

import org.springframework.data.repository.CrudRepository;

import com.cloudsherpas.droolsample.domain.RulesVersion;

public interface RulesVersionRepository extends CrudRepository<RulesVersion, Long> {

    Collection<RulesVersion> findByPackageName(final String packageName);
}
