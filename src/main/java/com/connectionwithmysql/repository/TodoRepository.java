package com.connectionwithmysql.repository;

import com.connectionwithmysql.model.Todo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TodoRepository extends JpaRepository<Todo, Long> {
    List<Todo> findAllByDoneTrue();
    List<Todo> findAllByDoneFalse();
    Todo getByTitle(String title);
}
