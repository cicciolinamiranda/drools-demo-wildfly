package com.cloudsherpas.droolsample.api.endpoint;

import com.cloudsherpas.droolsample.api.exception.InvalidParameterException;
import com.cloudsherpas.droolsample.api.resource.SubjectListResource;
import com.cloudsherpas.droolsample.service.SubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by sromares on 11/9/15.
 */
@RestController
@RequestMapping(value = "/subject")
public class SubjectEndpoint {

    @Autowired
    private SubjectService subjectService;

    @RequestMapping(value = "/list",
            method = RequestMethod.GET)
    public SubjectListResource list(HttpServletRequest request) {
        try{
            return subjectService.list();
        } catch (IllegalArgumentException e) {
            throw new InvalidParameterException();
        }
    }
}
