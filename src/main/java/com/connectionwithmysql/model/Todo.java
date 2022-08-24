package com.connectionwithmysql.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Todo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String title;

    @Column(columnDefinition = "boolean default false")
    private boolean urgent = false;

    @Column(columnDefinition = "boolean default false")
    private boolean done = false;

    @CreationTimestamp
    @Column(columnDefinition = "datetime default current_timestamp on update current_timestamp")
    private LocalDateTime creationDate;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private LocalDateTime dueDate;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @PrimaryKeyJoinColumn
    private Detail detail;

    @ManyToOne(cascade = CascadeType.ALL)
    private Assignee assignee;

    public Todo(String title) {
        this.title = title;
    }
}
