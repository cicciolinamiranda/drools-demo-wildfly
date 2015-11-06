package com.cloudsherpas.droolsample.service;

import static java.util.stream.Collectors.toList;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.StatelessKieSession;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Service;

import com.cloudsherpas.droolsample.api.resource.DroolsRuleVersionDTO;
import com.cloudsherpas.droolsample.api.resource.DroolsRuleVersionDTOList;
import com.cloudsherpas.droolsample.api.resource.RuleVersionDTO;
import com.cloudsherpas.droolsample.api.resource.RuleVersionResource;
import com.cloudsherpas.droolsample.api.resource.SuggestionResource;
import com.cloudsherpas.droolsample.domain.RulesVersion;
import com.cloudsherpas.droolsample.dto.CourseListDTO;
import com.cloudsherpas.droolsample.fact.SubjectRating;
import com.cloudsherpas.droolsample.fact.Suggestions;
import com.cloudsherpas.droolsample.model.StudentSubjectRating;
import com.cloudsherpas.droolsample.repository.RulesVersionRepository;

/**
 * @author RMPader
 */
@Service
public class CourseService implements ApplicationContextAware {

    @Autowired
    private KieContainer kieContainer;

    private ApplicationContext applicationContext;

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

    public DroolsRuleVersionDTOList getRules() {
        DroolsRuleVersionDTOList list = new DroolsRuleVersionDTOList();
        DroolsRuleVersionDTO droolsRuleVersionDTO = new DroolsRuleVersionDTO();
        droolsRuleVersionDTO.setPackageName("com.cloudsherpas");
        RuleVersionDTO rule = new RuleVersionDTO();
        rule.setVersionName("1.0");
        rule.setDefault(true);
        RuleVersionDTO rule2 = new RuleVersionDTO();
        rule2.setVersionName("2.0");
        rule2.setDefault(false);
        droolsRuleVersionDTO.addRuleVersionDTO(rule);
        droolsRuleVersionDTO.addRuleVersionDTO(rule2);
        list.addDroolsRulesVersion(droolsRuleVersionDTO);
        return list;
    }

    public void addRuleVersion(final RuleVersionResource ruleVersionResource) {
        System.out.println("versionName: " + ruleVersionResource.getVersion());
        RulesVersion rulesVersion = new RulesVersion(
                ruleVersionResource.getPackageName(),
                ruleVersionResource.getVersion());
        rulesVersionRepository.save(rulesVersion);
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext)
            throws BeansException {
        // TODO Auto-generated method stub
        // applicationContext.getAutowireCapableBeanFactory().initializeBean(existingBean,
        // beanName)
    }

}
