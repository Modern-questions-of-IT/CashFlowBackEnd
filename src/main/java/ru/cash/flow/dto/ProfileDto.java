package ru.cash.flow.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import ru.cash.flow.enums.ERole;

@Setter
@Getter
@Builder
public class ProfileDto {
    private String name;
    private String email;
}
