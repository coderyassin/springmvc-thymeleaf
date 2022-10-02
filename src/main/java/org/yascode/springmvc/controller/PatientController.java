package org.yascode.springmvc.controller;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.yascode.springmvc.dao.PatientRepository;
import org.yascode.springmvc.entities.Patient;

import javax.validation.Valid;

@Controller
@RequestMapping(path = "/patient")
public class PatientController {

    private PatientRepository patientRepository;

    public PatientController(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }

    @GetMapping(path = "/index")
    public String patients(Model model,
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

    @GetMapping("/delete")
    public String delete(@RequestParam(name = "id") Long id,
                         @RequestParam(name = "keyword") String keyword,
                         @RequestParam(name = "page") int page) {
        patientRepository.deleteById(id);
        return "redirect:/patient/index?page="+page+"&keyword="+keyword;
    }

    @GetMapping(path = "/formPatient")
    public String formPatient(Model model) {
        model.addAttribute("patient", new Patient());
        return "formPatient.html";
    }

    @PostMapping(path = "/savePatient")
    public String savePatient(@Valid Patient patient, BindingResult bindingResult) {
        if (bindingResult.hasErrors())
            return "formPatient";

        patientRepository.save(patient);
        return "formPatient";
    }

}
