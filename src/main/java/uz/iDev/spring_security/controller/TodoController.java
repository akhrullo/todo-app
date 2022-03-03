package uz.iDev.spring_security.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import uz.iDev.spring_security.dto.TodoCreateDto;
import uz.iDev.spring_security.dto.TodoUpdateDto;
import uz.iDev.spring_security.service.TodoService;

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

    @RequestMapping(value = "update/{id}", method = RequestMethod.GET)
    public String updatePage(Model model, @PathVariable String id) {
        model.addAttribute("dto",  todoService.get(Long.parseLong(id)));
        return "todo/update";
    }

    @RequestMapping(value = "update/", method = RequestMethod.POST)
    public String update(@Valid @ModelAttribute TodoUpdateDto dto, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return "todo/update";
        }
        todoService.update(dto);
        return "redirect:/todo/list/";
    }

    @GetMapping(value = "complete/{id}")
    public String completeTodo(@PathVariable String id) {
        todoService.completeTodo(Long.parseLong(id));
//        System.out.println( todoService.get(Long.parseLong(id)).toString());
        return "redirect:/todo/list/";
    }

    @GetMapping("delete/{id}")
    public String deleteTodo(@PathVariable String id) {
        todoService.deleteTodo(Long.parseLong(id));

        return "redirect:/todo/list/";
    }

}
