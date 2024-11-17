package ru.cash.flow.dto;

import jakarta.persistence.Column;
import lombok.Getter;
import lombok.Setter;
import ru.cash.flow.entities.User;

import java.util.Date;

@Setter
@Getter
public class CategoryDto {
    private User user;
    private String name;

}
