package com.cloudsherpas.droolsample.api.endpoint;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.cloudsherpas.droolsample.api.exception.InvalidParameterException;
import com.cloudsherpas.droolsample.api.resource.RulesVersionResource;
import com.cloudsherpas.droolsample.api.resource.SuggestionResource;
import com.cloudsherpas.droolsample.dto.CourseListDTO;
import com.cloudsherpas.droolsample.dto.DroolsRuleVersionDTOList;
import com.cloudsherpas.droolsample.service.CourseService;

/**
 * @author RMPader
 */
@RestController
@RequestMapping(value = "/course")
public class CourseEndpoint {

    @Autowired
    private CourseService courseService;

    @RequestMapping(value = "/suggest",
                    method = RequestMethod.GET)
    public SuggestionResource suggestCourse(HttpServletRequest request) {
        try {
            Map<String, Integer> ratings = new HashMap<>();
            for (Map.Entry<String, String[]> entry : request.getParameterMap()
                                                            .entrySet()) {
                Assert.isTrue(entry.getValue().length == 1);
                int rating = Integer.valueOf(entry.getValue()[0]);
                ratings.put(entry.getKey(), rating);
            }

            return courseService.suggestCourses(ratings);
        } catch (IllegalArgumentException e) {
            throw new InvalidParameterException();
        }
    }

    @RequestMapping(value = "/advice",
            method = RequestMethod.GET)
    public CourseListDTO adviceCourse(HttpServletRequest request) {
        try {
            Map<String, Integer> ratings = new HashMap<>();
            for (Map.Entry<String, String[]> entry : request.getParameterMap()
                    .entrySet()) {
                Assert.isTrue(entry.getValue().length == 1);
                int rating = Integer.valueOf(entry.getValue()[0]);
                ratings.put(entry.getKey(), rating);
            }

            return courseService.adviceCourses(ratings);
        } catch (IllegalArgumentException e) {
            throw new InvalidParameterException();
        }
    }

    @RequestMapping(value = "/addDroolUrl",
            method = RequestMethod.GET)
    public String addDroolUrl(HttpServletRequest request) {
        String url = "";
        try {
            for (Map.Entry<String, String[]> entry : request.getParameterMap()
                    .entrySet()) {
                url = String.valueOf(entry.getValue()[0]);
            }

            return url;
        } catch (IllegalArgumentException e) {
            throw new InvalidParameterException();
        }
    }

    @RequestMapping(value = "/rules",
            method = RequestMethod.GET)
    public DroolsRuleVersionDTOList getRules(final HttpServletRequest request) {
        return courseService.getRules();
    }

    @RequestMapping(value = "/addruleversion",
            method = RequestMethod.POST)
    public void addRuleVersion(@RequestBody RulesVersionResource ruleVersionResource) {
        System.out.println(ruleVersionResource.getVersion());
        courseService.addRuleVersion(ruleVersionResource);
    }

    @RequestMapping(value = "/listrules",
            method = RequestMethod.GET)
 public List<RulesVersionResource> getAllToDos(final HttpServletRequest request) {
        String packageName = request.getParameter("packageName").toString();
        return courseService.getRuleVersionBasedOnPackageName(packageName);
 }
}
