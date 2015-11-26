package com.cloudsherpas.droolsample.service;

import com.cloudsherpas.droolsample.api.exception.InvalidParameterException;
import com.cloudsherpas.droolsample.dto.CourseListDTO;
import com.cloudsherpas.droolsample.model.StudentSubjectRating;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.StatelessKieSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    private static final Logger LOGGER = LoggerFactory.getLogger(CourseService.class);

    @Autowired
    private KieContainer kieContainer;

    public CourseListDTO adviceCourses(Map<String, String> subjectRatingMap) {
        try {
            Map<String, Integer> ratings = subjectRatingMap.entrySet()
                                                           .stream()
                                                           .collect(Collectors.toMap(Map.Entry::getKey,
                                                                                     e -> Integer.valueOf(
                                                                                             e.getValue())));
            CourseListDTO courseListDTO = new CourseListDTO();
            StudentSubjectRating studentSubjectRating = new StudentSubjectRating(ratings);
            StatelessKieSession courseMatchSession = kieContainer
                    .newStatelessKieSession();
            List<StudentSubjectRating> facts = new ArrayList<>();
            facts.add(studentSubjectRating);
            courseMatchSession.setGlobal("courseListDTO", courseListDTO);
            courseMatchSession.execute(facts);
            return courseListDTO;
        } catch (Exception ex) {
            LOGGER.error("Encountered error while processing course ratings", ex);
            throw new InvalidParameterException();
        }
    }

}
