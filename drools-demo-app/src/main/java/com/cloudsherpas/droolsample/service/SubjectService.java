package com.cloudsherpas.droolsample.service;
import com.cloudsherpas.droolsample.api.resource.SubjectListResource;
import com.cloudsherpas.droolsample.domain.Subject;
import com.cloudsherpas.droolsample.repository.SubjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sromares on 11/9/15.
 */
@Service
public class SubjectService {

    @Autowired
    private SubjectRepository subjectRepository;

    public SubjectListResource list(){
        SubjectListResource subjectListResource = new SubjectListResource();

        Iterable<Subject> subjects = subjectRepository.findAll();
        List<Subject> subjectList = new ArrayList<>();
        for (Subject subject : subjects) {
            subjectList.add(subject);
        }
        subjectListResource.setSubjects(subjectList);
        return subjectListResource;
    }


}
