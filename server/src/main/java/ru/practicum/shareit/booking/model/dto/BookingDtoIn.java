package ru.practicum.shareit.booking.model.dto;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class BookingDtoIn {

    private Long id;

    private LocalDateTime start;
    private LocalDateTime end;
    private Long itemId;
}
