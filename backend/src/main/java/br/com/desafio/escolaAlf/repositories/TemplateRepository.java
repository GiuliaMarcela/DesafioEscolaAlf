package br.com.desafio.escolaAlf.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.desafio.escolaAlf.entities.Template;

@Repository
public interface TemplateRepository extends JpaRepository<Template, Integer> {

}
