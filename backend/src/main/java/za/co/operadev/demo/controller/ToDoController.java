package za.co.operadev.demo.controller;

import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import za.co.operadev.demo.models.Todo;
import za.co.operadev.demo.service.ToDoService;

@CrossOrigin(origins = "*")
@Controller
public class ToDoController {

	private ToDoService service;

	@Autowired
	public ToDoController(ToDoService service) {
		this.service = service;
	}

	@PostMapping("/todo")
	public @ResponseBody ResponseEntity<Object> post(@RequestBody Todo todo) {
		service.save(todo);
		return ResponseEntity.ok(service.getAll());
	}

	@GetMapping("/todo")
	public @ResponseBody ResponseEntity<Object> getAllPersons() {
		return ResponseEntity.ok(service.getAll());
	}

	@GetMapping("/todo/{id}")
	public @ResponseBody ResponseEntity<Object> getPerson(@PathVariable long id) {
		Object p;
		try {
			p = service.getPerson(id);
		} catch (NotFoundException e) {
			p = e.getMessage();
		}
		return ResponseEntity.ok(p);
	}

	@DeleteMapping("/todo/{id}")
	public @ResponseBody ResponseEntity<Object> deleteTodo(@PathVariable long id) {
		service.delete(id);
		return ResponseEntity.ok(service.getAll());
	}

	@PutMapping("/todo/{id}")
	public @ResponseBody ResponseEntity<Object> updateTodo(@PathVariable long id, @RequestBody Todo todo) {
		try {
			Todo td = service.getPerson(id);
			td.setComplete(todo.getComplete());
			td.setTitle(todo.getTitle());

			service.save(td);
		} catch(NotFoundException e) {
			// TODO: 2018/11/29 Need to do something
		}
		return ResponseEntity.ok(service.getAll());
	}
}
