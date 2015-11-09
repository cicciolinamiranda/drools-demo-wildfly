package com.cloudsherpas.droolsample.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.StatelessKieSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cloudsherpas.droolsample.dto.CourseListDTO;
import com.cloudsherpas.droolsample.model.StudentSubjectRating;
import com.cloudsherpas.droolsample.repository.RulesVersionRepository;

/**
 * @author CMiranda
 */
@Service
public class CourseService {

    @Autowired
    private KieContainer kieContainer;


    @Autowired
    private RulesVersionRepository rulesVersionRepository;

    public CourseListDTO adviceCourses(Map<String, Integer> subjectRatingMap) {
        CourseListDTO courseListDTO = new CourseListDTO();
        StudentSubjectRating studentSubjectRating = new StudentSubjectRating(
                subjectRatingMap);
        try {
            StatelessKieSession courseMatchSession = kieContainer
                    .newStatelessKieSession();
            List<StudentSubjectRating> facts = new ArrayList<>();
            facts.add(studentSubjectRating);
            courseMatchSession.setGlobal("courseListDTO", courseListDTO);
            courseMatchSession.execute(facts);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return courseListDTO;
    }

}
