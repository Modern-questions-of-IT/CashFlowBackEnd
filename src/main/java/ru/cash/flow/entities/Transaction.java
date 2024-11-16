package ru.cash.flow.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Date;

@Entity
@Getter
@Setter
@Table(name = "Transaction")
public class Transaction {
    @Id
    private Long id;
    @JoinColumn(name = "user_id")
    @ManyToOne
    private User userId;
    @Column(name = "type")
    private String type;
    @JoinColumn(name = "category_id")
    @ManyToOne
    private TransactionCategory categoryId;
    @Column(name = "title")
    private String title;
    @Column(name = "amount")
    private BigDecimal amount;
    @Column(name = "date")
    private Date date;
    @Column(name = "is_recurring")
    private Boolean isRecurring;
    @Column(name = "frequency")
    private Long frequency;
    @Column(name = "next_occurrence")
    private Date nextOccurrence;
    @Column(name = "created_at")
    private Date createdAt;
    @Column(name = "updated_at")
    private Date updatedAt;
}
