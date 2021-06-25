package com.manjenko.testproject.repo;

import com.manjenko.testproject.model.ToDoList;
import com.manjenko.testproject.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface ListRepository extends CrudRepository<ToDoList, Long> {
}
