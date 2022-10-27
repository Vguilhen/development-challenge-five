package com.apimedcloud.service;

import com.apimedcloud.dto.PatientDto;
import com.apimedcloud.models.PatientModel;
import com.apimedcloud.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PatientService {

    private final PatientRepository patientRepository;

    @Autowired
    public PatientService(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }

    public PatientModel findByEmail(String email) {
        Optional<PatientModel> patientEmail = patientRepository.findByEmail(email);

        if (patientEmail.isPresent()) {
            return patientEmail.get();
        }
        throw new RuntimeException("e-mail inexistente");
    }

    public PatientModel findById(Long id) {
        Optional<PatientModel> patientId = patientRepository.findById(id);

        if (patientId.isPresent()) {
            return patientId.get();
        }
        throw new RuntimeException("id inexistente");
    }

    public PatientModel savePatient(PatientDto dto) {

        PatientModel patientModel = new PatientModel();
        patientModel.setName(dto.getName());
        patientModel.setBirthDate(dto.getBirthDate());
        patientModel.setEmail(dto.getEmail());
        patientModel.setAddress(dto.getAddress());
        return patientRepository.save(patientModel);
    }

    public PatientModel update(PatientModel patientModel, PatientDto dto) {
        patientModel.setName(dto.getName());
        patientModel.setBirthDate(dto.getBirthDate());
        patientModel.setEmail(dto.getEmail());
        patientModel.setAddress(dto.getAddress());
        return patientRepository.save(patientModel);
    }

    public void delete(Long id) {
        patientRepository.deleteById(id);
    }

}


