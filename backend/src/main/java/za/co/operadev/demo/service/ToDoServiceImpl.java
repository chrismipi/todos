package za.co.operadev.demo.service;

import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import za.co.operadev.demo.models.Todo;
import za.co.operadev.demo.repositories.TodoRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ToDoServiceImpl implements ToDoService{
    @Autowired
    private TodoRepository repo;

    @Override
    public void save(Todo todo) {
        repo.save(todo);
    }

    @Override
    public Todo getTodoById(Long id) throws NotFoundException {
        Optional<Todo> p = repo.findById(id);
        if(p.isPresent()) {
            return p.get();
        } else throw new NotFoundException("Todo item with id " + id.toString() + " does not exit ");
    }

    @Override
    public List<Todo> getAll() {
        Iterable<Todo> it = repo.findAll();
        List<Todo> people = new ArrayList<>();
        it.forEach((people::add));
        return people;
    }

    @Override
    public void delete(Long id) {
        //todo perhaps look at soft delete, not this :-(
        if(repo.existsById(id)) {
            repo.deleteById(id);
        }
    }
}
