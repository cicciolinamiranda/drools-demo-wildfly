package com.cloudsherpas.droolsample.service;

import com.cloudsherpas.droolsample.dto.CourseListDTO;
import com.cloudsherpas.droolsample.model.StudentSubjectRating;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.StatelessKieSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author CMiranda
 */
@Service
public class CourseService {

    @Autowired
    private KieContainer kieContainer;

    public CourseListDTO adviceCourses(Map<String, String> subjectRatingMap) {
        Map<String, Integer> ratings = subjectRatingMap.entrySet()
                                                       .stream()
                                                       .collect(Collectors.toMap(Map.Entry::getKey,
                                                                                 e -> Integer.valueOf(
                                                                                         e.getValue())));
        CourseListDTO courseListDTO = new CourseListDTO();
        StudentSubjectRating studentSubjectRating = new StudentSubjectRating(ratings);
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
