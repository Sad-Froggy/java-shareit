package ru.practicum.shareit.user.dto;

import lombok.*;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class UserDto {

    @NotNull
    long id;
    @NotBlank(message = "Имя пользователя не указано")
    String name;
    @Email(message = "Введен некорректный email.")
    @NotNull(message = "Email не указан")
    String email;

}
