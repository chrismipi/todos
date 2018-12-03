package za.co.operadev.demo.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import za.co.operadev.demo.model.Todo;

@Repository
public interface ToDoRepo extends CrudRepository<Todo, Long> {
}
