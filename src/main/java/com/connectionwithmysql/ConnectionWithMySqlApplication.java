package com.connectionwithmysql;

import com.connectionwithmysql.service.TodoService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@RequiredArgsConstructor
public class ConnectionWithMySqlApplication{

    private final TodoService todoService;

    public static void main(String[] args) {
        SpringApplication.run(ConnectionWithMySqlApplication.class, args);
    }

}
