package ru.cash.flow.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Date;

@Entity
@Getter
@Setter
@Table(name = "transactions")
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "user_id")
    private Integer user;
    @Column(name = "type")
    private String type;
    @Column(name = "category_id")
    private Integer category;
    @Column(name = "title")
    private String title;
    @Column(name = "amount")
    private BigDecimal amount;
    @Column(name = "date")
    private Date date;
    @Column(name = "next_occurrence")
    private Date nextOccurrence;
}
