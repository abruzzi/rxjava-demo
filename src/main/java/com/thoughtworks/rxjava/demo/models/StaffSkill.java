package com.thoughtworks.rxjava.demo.models;

import java.util.List;

public class StaffSkill {
    private String id;

    private String employeeId;

    List<Skill> skills;

    public StaffSkill() {
    }

    public String getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
    }

    public List<Skill> getSkills() {
        return skills;
    }

    public void setSkills(List<Skill> skills) {
        this.skills = skills;
    }
}
