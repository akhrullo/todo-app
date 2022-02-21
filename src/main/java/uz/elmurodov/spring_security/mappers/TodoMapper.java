package uz.elmurodov.spring_security.mappers;

import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;
import uz.elmurodov.spring_security.dto.TodoCreateDto;
import uz.elmurodov.spring_security.dto.TodoDto;
import uz.elmurodov.spring_security.entity.Todo;

import java.util.List;


@Component
@Mapper(componentModel = "spring")
public interface TodoMapper {
    TodoDto toDto(Todo todo);

    List<TodoDto> toDto(List<Todo> todo);

    Todo fromCreateDto(TodoCreateDto dto);
}
