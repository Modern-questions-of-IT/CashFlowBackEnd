package ru.cash.flow.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.cash.flow.entities.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
}
