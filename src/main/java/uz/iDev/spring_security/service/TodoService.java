package uz.iDev.spring_security.service;


import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import uz.iDev.spring_security.config.security.UserDetails;
import uz.iDev.spring_security.dto.TodoCreateDto;
import uz.iDev.spring_security.dto.TodoDto;
import uz.iDev.spring_security.dto.TodoUpdateDto;
import uz.iDev.spring_security.entity.Todo;
import uz.iDev.spring_security.mappers.TodoMapper;
import uz.iDev.spring_security.repository.TodoRepository;

import java.util.List;

@Service
public class TodoService {
    private final TodoRepository todoRepository;
    private final TodoMapper mapper;

    public TodoService(TodoRepository todoRepository, TodoMapper mapper) {
        this.todoRepository = todoRepository;
        this.mapper = mapper;
    }

    public List<TodoDto> getAll() {
        Long id = ((UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getId();
        List<Todo> todoList = todoRepository.findAllByUserId(id);
        return mapper.toDto(todoList);
    }

    public void create(TodoCreateDto dto) {
        Todo todo = mapper.fromCreateDto(dto);
        Long id = ((UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getId();
        todo.setUserId(id);
        todoRepository.save(todo);
    }

    public void completeTodo(Long id) {
        todoRepository.completeTodo(id, !get(id).isCompleted());
    }

    public void deleteTodo(Long id) {
        todoRepository.deleteById(id);
    }

    public Todo get(Long id) {
        return  todoRepository.findById(id).get();
    }

    public void update(TodoUpdateDto dto) {
        todoRepository.update(dto.getTitle(), dto.getDescription(),  dto.getId());
    }
}
