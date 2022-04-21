package com.challenge.school.modules.template;

import com.challenge.school.modules.template.entities.Template;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface TemplateRepository extends JpaRepository<Template, UUID> {
}
