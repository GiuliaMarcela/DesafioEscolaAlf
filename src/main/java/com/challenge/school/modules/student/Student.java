package com.challenge.school.modules.student;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import java.util.UUID;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "students")
public class Student {
    @Id @GeneratedValue(generator = "uuid")
    private UUID id;
    private String enrollment;
    private String name;
    private String email;
    private Integer finalGrade;
    private Status status;
}
