package za.co.operadev.demo.service;

import javassist.NotFoundException;
import za.co.operadev.demo.models.Todo;

import java.util.List;

public interface ToDoService {

	void save(Todo todo);

	Todo getTodoById(Long id) throws NotFoundException;

	List<Todo> getAll();

	void delete(Long id);
}
