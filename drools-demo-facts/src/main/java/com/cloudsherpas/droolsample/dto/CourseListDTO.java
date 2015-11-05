package com.cloudsherpas.droolsample.dto;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sromares on 11/4/15.
 */
public class CourseListDTO {
    private List<CourseDTO> courseDTOList;

    public CourseListDTO() {
        this.courseDTOList = new ArrayList<CourseDTO>();
    }

    public List<CourseDTO> getCourseDTOList() {
        return courseDTOList;
    }

    public void setCourseDTOList(List<CourseDTO> courseDTOList) {
        this.courseDTOList = courseDTOList;
    }

    public void addCourse(CourseDTO courseDTO){
        this.courseDTOList.add(courseDTO);
    }
}
