package br.com.desafio.escolaAlf.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.desafio.escolaAlf.entities.Student;
import br.com.desafio.escolaAlf.entities.Template;
import br.com.desafio.escolaAlf.services.MainService;

@RestController
@RequestMapping
public class IndexController {

	@Autowired
	private MainService service;
	
	@GetMapping(name = "students", path = "/students")
	public ResponseEntity<List<Student>> listAllStudents(){
		List<Student> stu = service.findAllStudents();
		return ResponseEntity.ok().body(stu);
	}
	
	@PostMapping(name = "template", path = "/register")
	public ResponseEntity<Template> registerTemplateTest(@RequestBody Template t) throws Exception {
		Template test = service.registerTemplateTest(t);
		return ResponseEntity.ok().body(test);
	}
	
	@GetMapping(name = "approved", path = "/approved")
	public ResponseEntity<List<Student>> listAllStudentsApproved() throws Exception{
		List<Student> approved = service.listStudentsApproved();
		return ResponseEntity.ok().body(approved);
	}
	
	@GetMapping(name = "final-grade", path = "/student/{id}")
	public Integer findStudentFinalGrade(@PathVariable Integer id) {
		return service.listStudentFinalGrade(id);
	}
	
	@PostMapping(name = "test", path = "/student/register")
	public ResponseEntity<List<Student>> registerStudentTest(@RequestBody Student s) throws Exception {
		List<Student> a = service.registerStudentTest(s);
		return ResponseEntity.ok().body(a);
	}
}
