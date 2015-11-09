package com.cloudsherpas.droolsample.service;

import static com.cloudsherpas.droolsample.util.ResourceUtil.toResource;
import static java.util.stream.Collectors.toList;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.StatelessKieSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cloudsherpas.droolsample.api.resource.RulesVersionResource;
import com.cloudsherpas.droolsample.api.resource.SuggestionResource;
import com.cloudsherpas.droolsample.domain.RulesVersion;
import com.cloudsherpas.droolsample.dto.CourseListDTO;
import com.cloudsherpas.droolsample.fact.SubjectRating;
import com.cloudsherpas.droolsample.fact.Suggestions;
import com.cloudsherpas.droolsample.model.StudentSubjectRating;
import com.cloudsherpas.droolsample.repository.RulesVersionRepository;

/**
 * @author CMiranda
 */
@Service
public class CourseService {

    @Autowired
    private KieContainer kieContainer;

//    private ApplicationContext applicationContext;

    @Autowired
    private RulesVersionRepository rulesVersionRepository;

    public SuggestionResource suggestCourses(Map<String, Integer> subjectRating) {
        StatelessKieSession courseMatchSession = kieContainer
                .newStatelessKieSession();
        SuggestionResource response = new SuggestionResource();
        List<Object> facts = subjectRating
                .entrySet()
                .stream()
                .map(stringStringEntry -> new SubjectRating(stringStringEntry
                        .getKey(), stringStringEntry.getValue()))
                .collect(toList());
        Suggestions suggestions = new Suggestions();
        courseMatchSession.setGlobal("suggestions", suggestions);
        courseMatchSession.execute(facts);

        // TODO: retrieve course name based on course code? Maybe.
        suggestions.getSuggestedCourseCodes().forEach(response::addSuggestion);

        return response;
    }

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

    public void addRuleVersion(final RulesVersionResource ruleVersionResource) {
        //delete first before adding so that duplicate records will be omitted
        rulesVersionRepository.deleteAll();
        RulesVersion rulesVersion = new RulesVersion(
                ruleVersionResource.getPackageName(),
                ruleVersionResource.getVersion());
        rulesVersionRepository.save(rulesVersion);
    }

    public List<RulesVersionResource> getRuleVersionBasedOnPackageName(
            final String packageName) {
        Collection<RulesVersion> rulesVersionList = rulesVersionRepository
                .findByPackageName(packageName);
        List<RulesVersionResource> resultList = new ArrayList<>();
        for (RulesVersion rulesVersion : rulesVersionList) {
        	resultList.add(toResource(rulesVersion));
        }

        return resultList;
    }

}
