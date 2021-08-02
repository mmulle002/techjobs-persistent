package org.launchcode.javawebdevtechjobspersistent.models;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Employer extends AbstractEntity {

    @NotNull
    @NotBlank
    @Size(min = 1, max = 100)
    private String location;

    @OneToMany
    @JoinColumn
    private List<Job> jobs = new ArrayList<Job>();

    public Employer() {

    }

    public List<Job> getJobs() {

        return jobs;
    }
    /* public Employer( String location) {  I can't wrap my head around if I will reference at some point so comment out and come back to if needed, gotta keep it movin

        this.location = location;

    } */

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }




}
