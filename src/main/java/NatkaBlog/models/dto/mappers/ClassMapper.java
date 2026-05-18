package NatkaBlog.models.dto.mappers;

import NatkaBlog.data.entities.ClassEntity;
import NatkaBlog.models.dto.ClassDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ClassMapper {
    @Mapping(source = "classType.name", target = "classTypeName")
    ClassDTO toDTO(ClassEntity classEntity);

    ClassEntity toEntity(ClassDTO classDTO);


}
