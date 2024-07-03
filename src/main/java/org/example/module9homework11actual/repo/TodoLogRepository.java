package org.example.module9homework11actual.repo;

import org.example.module9homework11actual.entity.TodoLog;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TodoLogRepository extends JpaRepository<TodoLog, Integer> {
}