package br.com.desafio.escolaAlf.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.desafio.escolaAlf.entities.Student;

@Repository
public interface StudentRepository extends JpaRepository<Student, Integer> {

	@Query("select count (id) from Student s")
	List<Student> findAllStudents();

	Integer save(Integer result);
}
