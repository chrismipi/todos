package za.co.operadev.demo.services;

import javassist.NotFoundException;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;
import za.co.operadev.demo.models.Todo;
import za.co.operadev.demo.repository.TodoRepository;
import za.co.operadev.demo.service.ToDoService;
import za.co.operadev.demo.service.ToDoServiceImpl;

import java.util.*;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
public class TodoServiceImplTests {
    @TestConfiguration
    static class TodoServiceImplTestContextConfiguration {

        @Bean
        public ToDoService toDoService() {
            return new ToDoServiceImpl();
        }
    }

    @MockBean
    private TodoRepository todoRepository;

    @Autowired
    private ToDoService toDoService;

    @Before
    public void setUp() {
        assertNotNull(todoRepository);
        assertNotNull(toDoService);

        List<Todo> todos = new ArrayList<>();
        Todo todo = new Todo();
        todo.setTitle("To do item");
        todo.setComplete(false);
        todos.add(todo);

        Todo todo1 = new Todo();
        todo1.setTitle("To do item 1");
        todo1.setComplete(false);
        todos.add(todo1);

        Mockito.when(todoRepository.findAll()).thenReturn(todos);

        Optional<Todo> todoOption = Optional.of(todo);
        Mockito.when(todoRepository.findById(1L)).thenReturn(todoOption);

        Mockito.when(todoRepository.save(todo)).thenReturn(null);
        // todo save
        // todo delete
    }

    @Test
    public void getAllToDos() {
        List<Todo> todos = toDoService.getAll();
        assertEquals(2, todos.size());
        assertEquals("To do item", todos.get(0).getTitle());
        assertEquals("To do item 1", todos.get(1).getTitle());
    }

    @Test
    public void getToDoById() {
        try {
            Todo todo = toDoService.getTodoById(1L);
            assertNotNull(todo);
            assertEquals("To do item", todo.getTitle());
        } catch (NotFoundException e) {
            fail("Item not found");
        }
    }
}
