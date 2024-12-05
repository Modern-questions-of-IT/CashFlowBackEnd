package ru.cash.flow.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ChangeUsernameRequest {
    private String email;
}
