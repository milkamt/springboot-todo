package com.connectionwithmysql.repository;

import com.connectionwithmysql.model.Detail;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DetailRepository extends JpaRepository <Detail, Long> {
}
