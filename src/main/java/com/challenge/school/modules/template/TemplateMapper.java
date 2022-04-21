package com.challenge.school.modules.template;

import com.challenge.school.modules.template.dto.TemplateResponse;
import com.challenge.school.modules.template.entities.Template;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface TemplateMapper {
    TemplateMapper INSTANCE = Mappers.getMapper(TemplateMapper.class);

    default TemplateResponse toDTO(Template model) {
        return new TemplateResponse(
                model.getId(),
                model.getTestId(),
                model.getAnswers()
        );
    }
}
