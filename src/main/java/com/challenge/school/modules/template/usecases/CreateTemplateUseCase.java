package com.challenge.school.modules.template.usecases;

import com.challenge.school.exceptions.CustomBadRequestException;
import com.challenge.school.modules.exam.ExamRepository;
import com.challenge.school.modules.exam.entities.Exam;
import com.challenge.school.modules.exam.entities.ExamAnswer;
import com.challenge.school.modules.exam.usecases.GetExamByIdUseCase;
import com.challenge.school.modules.template.TemplateMapper;
import com.challenge.school.modules.template.TemplateRepository;
import com.challenge.school.modules.template.dto.TemplateRequest;
import com.challenge.school.modules.template.dto.TemplateResponse;
import com.challenge.school.modules.template.entities.Template;
import com.challenge.school.modules.template.entities.TemplateAnswer;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class CreateTemplateUseCase {
    private static final TemplateMapper mapper = TemplateMapper.INSTANCE;
    private final TemplateRepository repository;

    private final ExamRepository examRepository;
    private final GetExamByIdUseCase getExamByIdUseCase;

    public TemplateResponse execute(TemplateRequest request) {
        checkIfQuestionWeightIsValid(request.getAnswers());

        Exam exam = getExamByIdUseCase.execute(request.getTestId());

        Template gabarito = new Template(null, exam.getId(), request.getAnswers());

        correctExam(gabarito, exam);

        repository.save(gabarito);

        return mapper.toDTO(gabarito);
    }

    /**
     * Verifica se o peso de cada questão é sempre um inteiro maior que 0.
     * Se não for, ele lança uma exceção.
     *
     * @param correctAnswers uma lista de {@link TemplateAnswer}.
     * @throws CustomBadRequestException caso o peso da questão seja menor ou igual a zero.
     */
    private void checkIfQuestionWeightIsValid(List<TemplateAnswer> correctAnswers) {
        Integer totalWeight = 0;

        for (TemplateAnswer answer : correctAnswers) {
            totalWeight = totalWeight + answer.getWeight();

            if (answer.getWeight() <= 0 || answer.getWeight() == 10) {
                throw new CustomBadRequestException(
                        "O peso de cada questão é sempre um inteiro maior que 0 e menor que 10."
                );
            }

            if (totalWeight <= 0 || totalWeight >= 10) {
                throw new CustomBadRequestException("O peso total deve ser maior que 0 e menor do que 10");
            }
        }
    }

    /**
     * Corrige o exame, salvando a nota conforme a quantidade de acertos do estudante.
     *
     * @param template gabarito da prova a ser corrigida
     * @param exam     prova a ser corrigida
     * @throws CustomBadRequestException caso a quantidade de questões da prova, não seja idêntica a do gabarito.
     */
    private void correctExam(Template template, Exam exam) {
        List<TemplateAnswer> correctAnswerList = template.getAnswers();
        List<ExamAnswer> studentAnswerList = exam.getAnswers();
        Integer rightAnswers = 0;

        if (correctAnswerList.size() != studentAnswerList.size()) {
            throw new CustomBadRequestException(
                    "O gabarito e a prova do estudante não tem a mesma quantidade de perguntas e respostas."
            );
        }

        for (TemplateAnswer correct : correctAnswerList) {
            for (ExamAnswer studentAnswer : studentAnswerList) {
                if (
                        correct.getQuestion().equalsIgnoreCase(studentAnswer.getQuestion())
                                && correct.getCorrectAlternative().equalsIgnoreCase(studentAnswer.getAlternative())
                ) {
                    rightAnswers = rightAnswers + correct.getWeight();
                }
            }
        }

        exam.setGrade(rightAnswers);
        examRepository.save(exam);
    }
}
