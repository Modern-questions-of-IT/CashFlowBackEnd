package ru.cash.flow.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Date;

@Entity
@Getter
@Setter
@Table(name = "regular_transaction")
public class RegularTransaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @JoinColumn(name = "user_id")
    @ManyToOne(fetch = FetchType.EAGER)
    private User user;
    @Column(name = "type")
    private String type;
    @JoinColumn(name = "category")
    @ManyToOne(fetch = FetchType.EAGER)
    private Category category;
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
