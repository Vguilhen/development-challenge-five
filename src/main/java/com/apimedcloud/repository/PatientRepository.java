package com.apimedcloud.repository;

import com.apimedcloud.models.PatientModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PatientRepository extends JpaRepository<PatientModel, Long> {

    Optional<PatientModel> findByEmail(String email);

}
