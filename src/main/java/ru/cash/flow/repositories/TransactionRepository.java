package ru.cash.flow.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.cash.flow.entities.Transaction;
import ru.cash.flow.enums.TransactionType;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

public interface TransactionRepository extends JpaRepository<Transaction, Integer> {
    @Query("SELECT t FROM Transaction t " +
            "WHERE t.user = :userId " +
            "AND t.date BETWEEN :startDate " +
            "AND :endDate")
    List<Transaction> findByUserIdAndCreatedAtBetween(
            @Param("userId") Integer userId,
            @Param("startDate") Date startDate,
            @Param("endDate") Date endDate);


    @Query("SELECT t FROM Transaction t WHERE t.user.id = :userId and t.type = :type AND t.createdAt BETWEEN :startDate AND :endDate")
    List<Transaction> findByUserIdAndCreatedAtBetween(
            @Param("userId") Long userId,
            @Param("startDate") LocalDateTime startDate,
            @Param("endDate") LocalDateTime endDate,
            @Param("type") TransactionType type);

    @Query("SELECT t FROM Transaction t WHERE t.user.id = :userId " +
            "and t.type = :type AND t.createdAt >= :startOfMonth AND t.createdAt < :endOfMonth")
    List<Transaction> findByUserIdAndMonth(
            @Param("userId") Long userId,
            @Param("startOfMonth") LocalDateTime startOfMonth,
            @Param("endOfMonth") LocalDateTime endOfMonth,
            @Param("type") TransactionType type);

    @Query("SELECT t FROM Transaction t WHERE t.user.id = :userId " +
            "and t.type = :type AND t.createdAt >= :startOfYear AND t.createdAt < :endOfYear")
    List<Transaction> findByUserIdAndYear(
            @Param("userId") Long userId,
            @Param("startOfYear") LocalDateTime startOfYear,
            @Param("endOfYear") LocalDateTime endOfYear,
            @Param("type") TransactionType type);
}
