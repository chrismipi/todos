package za.co.operadev.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import za.co.operadev.demo.models.Todo;

@Repository
public interface TodoRepository extends JpaRepository<Todo, Long> {
}
