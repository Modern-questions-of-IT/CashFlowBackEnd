package ru.cash.flow.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.cash.flow.entities.TransactionCategory;

import java.util.List;

public interface CategoryRepository extends JpaRepository<TransactionCategory, Integer> {
}
