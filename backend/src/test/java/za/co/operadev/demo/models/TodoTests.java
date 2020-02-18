package za.co.operadev.demo.models;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;
import za.co.operadev.demo.repositories.TodoRepository;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@DataJpaTest
public class TodoTests {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private TodoRepository todoRepository;

    private Todo todo;

    @Before
    public void setUp(){
        Todo todo = new Todo();
        todo.setTitle("To do item");
        todo.setComplete(false);

        entityManager.persist(todo);
        entityManager.flush();
    }

    @Test
    public void getSavedTodoItem() {
        Todo found = todoRepository.findAll().get(0);

        assertEquals("To do item", found.getTitle());
    }
}
