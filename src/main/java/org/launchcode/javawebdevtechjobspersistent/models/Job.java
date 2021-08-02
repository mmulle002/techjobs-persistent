package org.launchcode.javawebdevtechjobspersistent.models;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Job extends AbstractEntity {

    @ManyToOne
    public Employer employer;

    @ManyToMany
    private List<Skill> skills = new ArrayList<>();

    public Job() {

    }

    public Job(Employer employer, List<Skill> someSkills) {
        super();
        this.employer = employer;
        this.skills = someSkills;
    }

    public Employer getEmployer() {

        return employer;
    }

    public void setEmployer(Employer employer) {

        this.employer = employer;
    }

    public List<Skill> getSkills() {

        return skills;
    }

    public void setSkills(List<Skill> skills) {
        this.skills = skills;
    }


}
