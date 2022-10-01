package org.yascode.springmvc.data;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.yascode.springmvc.dao.PatientRepository;
import org.yascode.springmvc.entities.Patient;

import java.time.LocalDate;
import java.time.Month;

@Component
@RequiredArgsConstructor
public class InsertDataIntoStartup implements CommandLineRunner {

    private final PatientRepository patientRepository;

    @Override
    public void run(String... args) throws Exception {
        /*patientRepository.save(new Patient(null, "Yassin", LocalDate.of(1997, Month.JUNE, 4), false));
        patientRepository.save(new Patient(null, "Fadoua", LocalDate.of(1993, Month.AUGUST, 1), false));
        patientRepository.save(new Patient(null, "Omar", LocalDate.of(1990, Month.DECEMBER, 9), false));
        patientRepository.save(new Patient(null, "Sanaa", LocalDate.of(1988, Month.JUNE, 17), false));

        patientRepository.findAll().forEach(p -> System.out.println(p.isSick()));*/
    }
}
