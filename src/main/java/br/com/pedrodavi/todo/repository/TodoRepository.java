package br.com.pedrodavi.todo.repository;

import br.com.pedrodavi.todo.model.Todo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TodoRepository extends JpaRepository<Todo, Long> {



}
