package ru.cash.flow.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.cash.flow.entities.Transaction;

import java.time.Instant;
import java.util.Date;
import java.util.List;

public interface TransactionRepository extends JpaRepository<Transaction, Integer> {
    @Query("SELECT t FROM Transaction t " +
            "WHERE t.date BETWEEN :startDate " +
            "AND :endDate")
    List<Transaction> findByUserIdAndCreatedAtBetween(
            @Param("startDate") Date startDate,
            @Param("endDate") Date endDate);
}
