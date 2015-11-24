package com.cloudsherpas.droolsample.api.resource;

import com.cloudsherpas.droolsample.domain.Subject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sromares on 11/9/15.
 */
public class SubjectListResource {
    private List<Subject> subjects;

    public SubjectListResource(){
        this.subjects = new ArrayList<>();
    }
    public List<Subject> getSubjects() {
        return subjects;
    }

    public void setSubjects(List<Subject> subjects) {
        this.subjects = subjects;
    }
}
