package za.co.operadev.demo.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.swagger2.mappers.ModelMapper;
import za.co.operadev.demo.dto.TodoDTO;
import za.co.operadev.demo.models.Todo;
import za.co.operadev.demo.service.ToDoService;
import za.co.operadev.demo.utils.EntityUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin(origins = "*")
@Controller
public class ToDoController {

	private final ToDoService service;
	private final EntityUtils entityUtils;
	@Autowired
	public ToDoController(ToDoService service, EntityUtils entityUtils) {
		this.service = service;
		this.entityUtils = entityUtils;
	}

	private List<TodoDTO> allTodos() {
		List<Todo> todoList = this.service.getAll();
		return todoList.stream().map(
				entity -> (TodoDTO) this.entityUtils.convertToDto(entity, TodoDTO.class)).collect(Collectors.toList()
		);
	}

	@PostMapping(value = "/todo", produces = {MediaType.APPLICATION_JSON_VALUE})
	public @ResponseBody ResponseEntity<Object> postTodo(@RequestBody TodoDTO todoDto) {
		Todo todo = (Todo) this.entityUtils.convertToEntity(todoDto, Todo.class);
		this.service.save(todo);

		return ResponseEntity.ok(this.allTodos());
	}

	@GetMapping(value = "/todo", produces = {MediaType.APPLICATION_JSON_VALUE})
	public @ResponseBody ResponseEntity<Object> getAllTodos() {
		return ResponseEntity.ok(this.allTodos());
	}

	@GetMapping("/todo/{id}")
	public @ResponseBody ResponseEntity<Object> getTodo(@PathVariable long id) {
		try {
			TodoDTO todo = (TodoDTO) this.entityUtils.convertToDto(this.service.getTodoById(id), TodoDTO.class);
			return ResponseEntity.ok(todo);
		} catch (NotFoundException e) {
			return ResponseEntity.notFound().build();
		}
	}

	@DeleteMapping(value = "/todo/{id}", produces = {MediaType.APPLICATION_JSON_VALUE})
	public @ResponseBody ResponseEntity<Object> deleteTodo(@PathVariable long id) {
		this.service.delete(id);
		return ResponseEntity.ok(this.allTodos());
	}

	@PutMapping(value = "/todo/{id}", produces = {MediaType.APPLICATION_JSON_VALUE})
	public @ResponseBody ResponseEntity<Object> updateTodo(@PathVariable long id, @RequestBody TodoDTO todo) {
		try {
			Todo td = this.service.getTodoById(id);
			td.setComplete(todo.getComplete());
			td.setTitle(todo.getTitle());

			this.service.save(td);
			return ResponseEntity.ok(this.allTodos());
		} catch(NotFoundException e) {
			return ResponseEntity.notFound().build();
		}
	}
}
