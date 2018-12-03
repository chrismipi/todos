package za.co.operadev.demo.service;

import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import za.co.operadev.demo.model.Todo;
import za.co.operadev.demo.repository.ToDoRepo;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ToDoService {
	@Autowired
	private ToDoRepo repo;

	public void save(Todo todo) {
		repo.save(todo);
	}

	public Todo getPerson(Long id) throws NotFoundException {
		Optional<Todo> p = repo.findById(id);

		if(p.isPresent()) {
			return p.get();
		} else throw new NotFoundException("Todo does not exit ");
	}

	public List<Todo> getAll() {
		Iterable<Todo> it = repo.findAll();
		List<Todo> people = new ArrayList<>();
		it.forEach((people::add));
		return people;
	}

	public void delete(Long id) {
		if(repo.existsById(id)) {
			repo.deleteById(id);
		}
	}
}
