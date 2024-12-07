package ru.cash.flow.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import ru.cash.flow.enums.TransactionType;

import java.math.BigDecimal;
import java.util.Date;

@Entity
@Getter
@Setter
@Table(name = "regular_transaction")
public class RegularTransaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "user_id")
    private Integer user;
    @Column(name = "type")
    @Enumerated(EnumType.STRING)
    private TransactionType type;
    @Column(name = "category")
    private Integer category;
    @Column(name = "title")
    private String title;
    @Column(name = "amount")
    private BigDecimal amount;
    @Column(name = "date")
    private Date date;
    @Column(name = "next_occurrence")
    private Date nextOccurrence;
    @Column(name = "created_at")
    private Date createdAt;
    @Column(name = "updated_at")
    private Date updatedAt;
}
