package com.challenge.school.modules.student.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StudentResponse implements Serializable {
    private static final long serialVersionUID = 1L;

    private UUID id;
    private String enrollment;
    private String name;
    private String email;
}
