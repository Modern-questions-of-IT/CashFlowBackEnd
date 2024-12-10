package ru.cash.flow.entities;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import ru.cash.flow.enums.ETransaction;

import java.util.Date;

@Setter
@Getter
@Entity
@Table(name = "categories")
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "user_id")
    private Integer user;
    @Column(name = "name")
    private String name;
    @Column(name = "created_at")
    private Date createdAt;
    @Column(name = "type")
    @Enumerated(EnumType.STRING)
    private ETransaction type;
}
