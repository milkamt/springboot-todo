package com.connectionwithmysql.repository;

import com.connectionwithmysql.model.Assignee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AssigneeRepository extends JpaRepository<Assignee, Long> {
    Assignee getByName(String name);
}
