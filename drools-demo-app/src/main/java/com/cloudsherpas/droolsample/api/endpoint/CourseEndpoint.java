package com.cloudsherpas.droolsample.api.endpoint;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.cloudsherpas.droolsample.api.exception.InvalidParameterException;
import com.cloudsherpas.droolsample.dto.CourseListDTO;
import com.cloudsherpas.droolsample.service.CourseService;

/**
 * @author RMPader
 */
@RestController
@RequestMapping(value = "/course")
public class CourseEndpoint {

    @Autowired
    private CourseService courseService;

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
}
