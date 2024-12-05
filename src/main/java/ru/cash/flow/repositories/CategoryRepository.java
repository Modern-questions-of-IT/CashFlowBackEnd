package ru.cash.flow.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.bind.annotation.PathVariable;
import ru.cash.flow.entities.Category;
import ru.cash.flow.enums.ETransaction;

import java.util.List;

public interface CategoryRepository extends JpaRepository<Category, Integer> {
    List<Category> getAllByUserAndType(Integer userId, ETransaction type);
}
