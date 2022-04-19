package com.challenge.school.modules.exam.entities;

import com.challenge.school.modules.student.Student;
import lombok.*;

import javax.persistence.*;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "exams")
public class Exam {
    @Id @GeneratedValue(generator = "uuid")
    private UUID id;
    private String examName;

    @OneToOne
    @JoinColumn(name = "student_id", referencedColumnName = "id")
    private Student student;
    private Integer grade;

    @ElementCollection(fetch = FetchType.LAZY)
    @CollectionTable(name = "answers_exams", joinColumns = @JoinColumn(name = "exam_id"))
    private List<ExamAnswer> answers;
}
