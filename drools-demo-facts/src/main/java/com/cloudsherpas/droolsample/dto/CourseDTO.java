package com.cloudsherpas.droolsample.dto;

/**
 * Created by sromares on 11/4/15.
 */
public class CourseDTO {
    private String name;
    private String code;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Override
    public String toString() {
        return "CourseDTO{" +
                "name='" + name + '\'' +
                ", code='" + code + '\'' +
                '}';
    }
}
