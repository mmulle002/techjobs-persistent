package org.launchcode.javawebdevtechjobspersistent.controllers;

import org.launchcode.javawebdevtechjobspersistent.dataRepos.EmployerRepository;
import org.launchcode.javawebdevtechjobspersistent.dataRepos.JobRepository;
import org.launchcode.javawebdevtechjobspersistent.dataRepos.SkillRepository;
import org.launchcode.javawebdevtechjobspersistent.models.Employer;
import org.launchcode.javawebdevtechjobspersistent.models.Job;
import org.launchcode.javawebdevtechjobspersistent.models.Skill;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

/**
 * Created by LaunchCode
 */
@Controller
public class HomeController {

    @Autowired
    private EmployerRepository employerRepository;

    @Autowired
    private SkillRepository skillRepository;

    @Autowired
    private JobRepository jobRepository;


    @RequestMapping("")
    public String index(Model model) {

        model.addAttribute("jobs", jobRepository.findAll());  //<-----I think, I'll come back to

        return "index";
    }

    @GetMapping("add")
    public String displayAddJobForm(Model model) {

        model.addAttribute(new Job());
        model.addAttribute("employers", employerRepository.findAll());
        model.addAttribute("skills", skillRepository.findAll());

        return "add";
    }

    @PostMapping("add")
    public String processAddJobForm(@ModelAttribute @Valid Job newJob, Errors errors, Model model, @RequestParam int employerId, @RequestParam List<Integer> skills) {

        if (errors.hasErrors()) {

            // model.addAttribute("title", "Add Job");  <---- unsure at this time,  come back to check

            return "add";
        } else {

            List<Skill> skillObjs = (List<Skill>) skillRepository.findAllById(skills);
            newJob.setSkills(skillObjs);

            Optional<Employer> optEmployer = employerRepository.findById(employerId);
            Employer employer = (Employer) optEmployer.get();
            newJob.setEmployer(employer);

            jobRepository.save(newJob);
        }
        return "redirect:";
    }

    @GetMapping("view/{jobId}")
    public String displayViewJob(Model model, @PathVariable int jobId) {

        Optional<Job> optJob = jobRepository.findById(jobId);

        if (optJob.isPresent()) {
            model.addAttribute("job", optJob.get());
        }

        return "view";
    }


}
