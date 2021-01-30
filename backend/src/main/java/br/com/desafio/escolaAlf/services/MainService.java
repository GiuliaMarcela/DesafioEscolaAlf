package br.com.desafio.escolaAlf.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.desafio.escolaAlf.entities.Student;
import br.com.desafio.escolaAlf.entities.Template;
import br.com.desafio.escolaAlf.repositories.StudentRepository;
import br.com.desafio.escolaAlf.repositories.TemplateRepository;

@Service 
public class MainService {

	@Autowired 
	private StudentRepository repository; 
	
	@Autowired
	private TemplateRepository tr;
	
	List<Student> students = new ArrayList<>(); 
	Student aluno = new Student();
	Template template; 
	
	public Student findById(Integer id) {
		Optional<Student> a = repository.findById(id);
		return a.orElseThrow(() -> new RuntimeException("Não foi possível localizar esse id."));
	}
	
	public List<Student> findAllStudents() {
		return repository.findAllStudents();
	}
	
	public List<Student> registerStudentTest(Student student) throws Exception {
		
		while(students.size() <= 0 && students.size() <= 100) {
			students.add(student);
		}
		
		repository.saveAll(students);
		
		return students;
	}
	
	public Template registerTemplateTest(Template template) throws Exception {
		
		for(int i = 0; i < template.getQuestions().size(); i++) {
			Integer weight = template.getQuestions().get(i).getWeight();
			if(weight <= 0) {
				throw new Exception("O peso da questão não pode ser negativo ou igual a 0. Por favor, tente novamente!");
			}
		}
		
		return tr.save(template);
	}
	
	public List<Student> listStudentsApproved() throws Exception {
		List<String> approved = new ArrayList<>();
		Integer correct = template.getQuestions().size();
		Integer test = aluno.getTests().size();
		Integer finalGrade = 0;
		Integer answersCorrect = 0;
		Integer testGrade = 0;
		
		for(int i = 0; i < students.size(); i++) {
			if(correct > 0 && correct == test) {
				for(int j = 0; j < correct; j++) {
					String alternativaGabarito = template.getQuestions().get(i).getAnswer();
					if(this.students.get(j).getTests().get(i).equals(alternativaGabarito)) {
						answersCorrect += template.getQuestions().get(i).getWeight();
					}
					testGrade += template.getQuestions().get(i).getWeight();
				}
				finalGrade = (answersCorrect / testGrade) * 10;
			} else {
				throw new Exception("Gabarito está vazio ou a prova encontra-se incompleta.");
			}
			
			students.get(i).setResult(finalGrade);
			if(finalGrade > 7) {
				approved.add(this.students.get(i).getName());
			}
		}	
		
		return repository.saveAll(students);
	}
	
	public Integer listStudentFinalGrade(Integer id) {
		Student s = findById(id);
		return repository.save(s.getResult());
	}
}
