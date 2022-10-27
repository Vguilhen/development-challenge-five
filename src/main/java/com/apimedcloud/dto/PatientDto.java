package com.apimedcloud.dto;

import com.apimedcloud.models.Address;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
public class PatientDto {

    private String name;
    private LocalDate birthDate;
    private String email;
    private Address address;
}
