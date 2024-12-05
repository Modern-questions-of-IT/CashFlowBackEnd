package ru.cash.flow.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import ru.cash.flow.enums.ETimeInterval;

import java.util.Date;

@Setter
@Getter
@Entity
@Table(name = "statistics")
public class Statistic {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JoinColumn(name = "user_id")
    private Long userId;

    private String name;

    @Column(name = "time_interval")
    @Enumerated(EnumType.STRING)
    private ETimeInterval eTimeInterval;

    private Integer year;

    private Integer month;

    @Column(name = "created_at")
    private Date createdAt;
}
