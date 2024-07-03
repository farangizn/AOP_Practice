package org.example.module9homework11actual.repo;

import org.example.module9homework11actual.entity.Todo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TodoRepository extends JpaRepository<Todo, Integer> {

    @Query(value = "select * from todo order by id", nativeQuery = true)
    List<Todo> findAll();
}