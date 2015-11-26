package com.cloudsherpas.droolsample.repository;

import com.cloudsherpas.droolsample.domain.RuleArtifact;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

/**
 * @author CMiranda
 */
public interface RuleArtifactRepository extends PagingAndSortingRepository<RuleArtifact, Long> {

    RuleArtifact findByActiveTrue();

    @Query("select ra from RuleArtifact ra where ra.groupId=:groupId and ra.artifactId=:artifactId and ra.version=:version")
    RuleArtifact findByDetails(@Param("groupId") String groupId,
                               @Param("artifactId") String artifactId,
                               @Param("version") String version);

}