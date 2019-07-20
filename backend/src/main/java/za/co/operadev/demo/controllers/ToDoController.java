package za.co.operadev.demo.controllers;

import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import za.co.operadev.demo.models.Todo;
import za.co.operadev.demo.models.TodoRequest;
import za.co.operadev.demo.service.ToDoService;

import javax.servlet.http.HttpServletRequest;

@CrossOrigin(origins = "*")
@Controller
public class ToDoController {

	private ToDoService service;

	@Autowired
	public ToDoController(ToDoService service) {
		this.service = service;
	}

	@PostMapping(value = "/todo", produces = {MediaType.APPLICATION_JSON_VALUE})
	public @ResponseBody ResponseEntity<Object> postTodo(@RequestBody TodoRequest todo, HttpServletRequest request) {
		Todo td = new Todo(todo);
		service.save(td);
		return ResponseEntity.ok(service.getAll());
	}

	@GetMapping(value = "/todo", produces = {MediaType.APPLICATION_JSON_VALUE})
	public @ResponseBody ResponseEntity<Object> getAllTodos(HttpServletRequest request) {
		return ResponseEntity.ok(service.getAll());
	}

	@GetMapping("/todo/{id}")
	public @ResponseBody ResponseEntity<Object> getTodo(@PathVariable long id, HttpServletRequest request) {
		Object p;
		try {
			p = service.getTodoById(id);
		} catch (NotFoundException e) {
			p = e.getMessage();
		}
		return ResponseEntity.ok(p);
	}

	@DeleteMapping(value = "/todo/{id}", produces = {MediaType.APPLICATION_JSON_VALUE})
	public @ResponseBody ResponseEntity<Object> deleteTodo(@PathVariable long id, HttpServletRequest request) {
		service.delete(id);
		return ResponseEntity.ok(service.getAll());
	}

	@PutMapping(value = "/todo/{id}", produces = {MediaType.APPLICATION_JSON_VALUE})
	public @ResponseBody ResponseEntity<Object> updateTodo(@PathVariable long id, @RequestBody TodoRequest todo, HttpServletRequest request) {
		try {
			Todo td = service.getTodoById(id);
			td.setComplete(todo.getComplete());
			td.setTitle(todo.getTitle());

			service.save(td);
		} catch(NotFoundException e) {
			// TODO: 2018/11/29 Need to do something
		}
		return ResponseEntity.ok(service.getAll());
	}
}
