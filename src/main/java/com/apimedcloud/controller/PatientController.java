package com.apimedcloud.controller;

import com.apimedcloud.dto.PatientDto;
import com.apimedcloud.models.PatientModel;
import com.apimedcloud.repository.PatientRepository;
import com.apimedcloud.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api")
public class PatientController {

    @Autowired
    private PatientRepository patientRepository;

    @Autowired
    private PatientService patientService;

    @GetMapping(value = "/patient")
    @ResponseStatus(HttpStatus.OK)
    public List<PatientModel> ListPatients() {
        return patientRepository.findAll();
    }

    @GetMapping(value = "patient/email/{email}")
    @ResponseStatus(HttpStatus.OK)
    public PatientModel findByEmail(@PathVariable(value = "email") String email) {
        return patientService.findByEmail(email);
    }

    @GetMapping(value = "patient/id/{id}")
    @ResponseStatus(HttpStatus.OK)
    public PatientModel findById(@PathVariable(value = "id") Long id) {
        return patientService.findById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public PatientModel savePatient(@RequestBody PatientDto dto) {
        return patientService.savePatient(dto);
    }

    @PutMapping("patient/id/{id}")
    @ResponseStatus(HttpStatus.OK)
    public PatientModel update(@RequestBody PatientDto dto, @PathVariable Long id) {
        PatientModel patientModel = patientService.findById(id);
        return patientService.update(patientModel, dto);
    }

    @DeleteMapping("patient/id/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void delete(@PathVariable Long id) {
        patientService.delete(id);
    }

}