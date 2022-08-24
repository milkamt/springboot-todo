package com.connectionwithmysql.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
public class Detail {

    @Id
    @Column(name = "todo_id")
    private long id;

    @Column(columnDefinition = "varchar(255) default 'no detail'")
    private String description = "no detail";

    @OneToOne(mappedBy = "detail", cascade = CascadeType.ALL)
    @MapsId
    @JoinColumn(name = "todo_id")
    private Todo todo;
}
