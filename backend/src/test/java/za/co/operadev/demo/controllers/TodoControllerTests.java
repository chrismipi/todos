package za.co.operadev.demo.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import za.co.operadev.demo.models.Todo;
import za.co.operadev.demo.service.ToDoService;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(ToDoController.class)
public class TodoControllerTests {
    @Autowired
    private MockMvc mvc;

    @MockBean
    private ToDoService toDoService;

    @Test
    public void postTodo() throws Exception {
        Todo todo = new Todo();
        todo.setTitle("Test title");
        todo.setComplete(false);

        List<Todo> todos = new ArrayList<>();
        todos.add(todo);

        given(toDoService.getAll()).willReturn(todos);

        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
        String json = ow.writeValueAsString(todo);

        mvc.perform(MockMvcRequestBuilders
                .post("/todo")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(json))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].title", is(todo.getTitle())))
                .andExpect(jsonPath("$[0].complete", is(todo.getComplete())));
    }
}
