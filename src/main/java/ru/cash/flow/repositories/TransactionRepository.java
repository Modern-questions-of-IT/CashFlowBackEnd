package ru.cash.flow.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.cash.flow.entities.Transaction;

public interface TransactionRepository extends JpaRepository<Transaction, Integer> {
}
