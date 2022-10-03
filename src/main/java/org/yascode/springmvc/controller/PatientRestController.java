package org.yascode.springmvc.controller;

import org.springframework.web.bind.annotation.*;
import org.yascode.springmvc.dao.PatientRepository;
import org.yascode.springmvc.entities.Patient;

import java.util.List;

@RestController
@RequestMapping(path = "/patient")
public class PatientRestController {

    private PatientRepository patientRepository;

    public PatientRestController(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }

    @GetMapping("/listPatient")
    public List<Patient> list() {
        return patientRepository.findAll();
    }

    @GetMapping("/{id}")
    public Patient gteOne(@PathVariable(name = "id") Long id) {
        return patientRepository.findById(id).get();
    }

}
