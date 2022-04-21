package com.challenge.school.modules.template.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

import java.util.List;
import java.util.UUID;

import static javax.persistence.FetchType.LAZY;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "templates")
public class Template {
    @Id
    @GeneratedValue(generator = "uuid")
    private UUID id;
    private UUID testId;

    @ElementCollection(fetch = LAZY)
    @CollectionTable(name = "answers_template", joinColumns = @JoinColumn(name = "template_id"))
    private List<TemplateAnswer> answers;
}
