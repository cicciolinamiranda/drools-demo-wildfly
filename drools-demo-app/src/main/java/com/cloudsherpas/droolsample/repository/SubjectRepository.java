package com.cloudsherpas.droolsample.repository;


import com.cloudsherpas.droolsample.domain.Subject;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * @author RMPader
 */
public interface SubjectRepository extends PagingAndSortingRepository<Subject, Long> {


}
