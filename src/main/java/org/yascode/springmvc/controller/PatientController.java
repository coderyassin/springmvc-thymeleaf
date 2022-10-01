package org.yascode.springmvc.controller;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.yascode.springmvc.dao.PatientRepository;
import org.yascode.springmvc.entities.Patient;

@Controller
@RequestMapping(path = "/patient")
public class PatientController {

    private PatientRepository patientRepository;

    public PatientController(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }

    @GetMapping(path = "/index")
    public String index() {
        return "index";
    }

    @GetMapping(path = "/patients")
    public String list(Model model,
                       @RequestParam(name = "page", defaultValue = "0", required = false) int page,
                       @RequestParam(name = "size", defaultValue = "4", required = false) int size,
                       @RequestParam(name = "keyword", defaultValue = "", required = false) String keyword) {

        Page<Patient> patientPage = null;

        if(Integer.valueOf(page) == null || page<0)
            page = 0;

        if(keyword == null) {
            patientPage = patientRepository.findAll(PageRequest.of(page, size));
            model.addAttribute("keyword", "");
        }
        else {
            patientPage = patientRepository.findByNameContaining(keyword, PageRequest.of(page, size));
            model.addAttribute("keyword", keyword);
        }

        int totalPages = patientPage.getTotalPages();

        model.addAttribute("patients", patientPage.getContent());
        model.addAttribute("pages", new int[totalPages]);
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", totalPages);

        return "patients";
    }
}