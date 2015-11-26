package com.cloudsherpas.droolsample.api.endpoint;

import com.cloudsherpas.droolsample.fact.CourseListDTO;
import com.cloudsherpas.droolsample.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

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
    public CourseListDTO adviceCourse(@RequestParam Map<String, String> ratings) {
        return courseService.adviceCourses(ratings);
    }
}