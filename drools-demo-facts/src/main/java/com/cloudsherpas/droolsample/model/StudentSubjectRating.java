package com.cloudsherpas.droolsample.model;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by sromares on 11/4/15.
 */
public class StudentSubjectRating {
    private Map<String, Integer> studentSubjectRating;

    public StudentSubjectRating(){
        this.studentSubjectRating = new HashMap<String, Integer>();
    }
    public StudentSubjectRating(Map<String, Integer> studentSubjectRating) {
        this.studentSubjectRating = studentSubjectRating;
    }

    public Map<String, Integer> getStudentSubjectRating() {
        return studentSubjectRating;
    }

    public void setStudentSubjectRating(Map<String, Integer> studentSubjectRating) {
        this.studentSubjectRating = studentSubjectRating;
    }

    public int getRatingOfSubject(String subject){
        if(this.studentSubjectRating != null && this.studentSubjectRating.containsKey(subject)){
            return this.studentSubjectRating.get(subject);
        }
        return 0;
    }

    @Override
    public String toString() {
        return "StudentSubjectRating{" +
                "studentSubjectRating=" + studentSubjectRating +
                '}';
    }
}
