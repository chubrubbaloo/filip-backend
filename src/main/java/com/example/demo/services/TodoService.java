package com.example.demo.services;

import com.example.demo.entities.Todo;
import com.example.demo.repositories.TodoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TodoService {

    private final TodoRepository todoRepository;

    @Autowired
    public TodoService(TodoRepository todoRepository) {
        this.todoRepository = todoRepository;
    }

    public Todo createTodo(Todo todo) {
        return todoRepository.save(todo);
    }

    public List<Todo> getAllTodos() {
        return todoRepository.findAll();
    }

    public Todo getTodoById(Long id) {
        return todoRepository.findById(id).orElseThrow();
    }

    public Todo updateTodoById(Todo changedTodo, Long id) {
        return todoRepository.findById(id)
                .map((todo -> {
                    todo.setTitle(changedTodo.getTitle());
                    todo.setTodo(changedTodo.getTodo());
                    return todoRepository.save(todo);
                })).orElseThrow();
    }

    public void deleteTodoById(Long id) {
        todoRepository.deleteById(id);
    }


}
