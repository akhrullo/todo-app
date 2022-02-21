package uz.elmurodov.spring_security.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import uz.elmurodov.spring_security.dto.TodoCreateDto;
import uz.elmurodov.spring_security.dto.TodoDto;
import uz.elmurodov.spring_security.service.TodoService;

import javax.validation.Valid;

@Controller
@RequestMapping("/todo/*")
public class TodoController {

    private final TodoService todoService;

    public TodoController(TodoService todoService) {
        this.todoService = todoService;
    }

    @RequestMapping("")
    public String todo(Model model) {
        model.addAttribute("todos", todoService.getAll());
        return "todo/all";
    }

    @RequestMapping(value = "create", method = RequestMethod.GET)
    public String createPage(Model model) {
        model.addAttribute("dto", new TodoCreateDto());
        return "todo/create";
    }

    @RequestMapping(value = "create", method = RequestMethod.POST)
    public String create(@Valid @ModelAttribute("dto") TodoCreateDto dto, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "todo/create";
        }
        todoService.create(dto);
        return "redirect:/todo/all";
    }


}
