package com.cloudsherpas.droolsample.service;

import com.cloudsherpas.droolsample.api.resource.SuggestionResource;
import com.cloudsherpas.droolsample.dto.CourseListDTO;
import com.cloudsherpas.droolsample.fact.SubjectRating;
import com.cloudsherpas.droolsample.fact.Suggestions;
import com.cloudsherpas.droolsample.model.StudentSubjectRating;

import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.StatelessKieSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static java.util.stream.Collectors.toList;

/**
 * @author RMPader
 */
@Service
public class CourseService {

    @Autowired
    private KieContainer kieContainer;


    public SuggestionResource suggestCourses(Map<String, Integer> subjectRating) {
        StatelessKieSession courseMatchSession = kieContainer.newStatelessKieSession();
        SuggestionResource response = new SuggestionResource();
        List<Object> facts = subjectRating.entrySet()
                                          .stream()
                                          .map(stringStringEntry -> new SubjectRating(stringStringEntry.getKey(),
                                                                                      stringStringEntry.getValue()))
                                          .collect(toList());
        Suggestions suggestions = new Suggestions();
        courseMatchSession.setGlobal("suggestions", suggestions);
        courseMatchSession.execute(facts);

        //TODO: retrieve course name based on course code? Maybe.
        suggestions.getSuggestedCourseCodes()
                   .forEach(response::addSuggestion);

        return response;
    }

    public CourseListDTO adviceCourses(Map<String, Integer> subjectRatingMap) {
        CourseListDTO courseListDTO = new CourseListDTO();
        StudentSubjectRating studentSubjectRating = new StudentSubjectRating(subjectRatingMap);
        try {
            StatelessKieSession courseMatchSession = kieContainer.newStatelessKieSession();
            List<StudentSubjectRating> facts = new ArrayList<>();
            facts.add(studentSubjectRating);
            courseMatchSession.setGlobal("courseListDTO", courseListDTO);
            courseMatchSession.execute(facts);
        }catch (Exception ex){
            ex.printStackTrace();
        }
       return courseListDTO;
    }

}
