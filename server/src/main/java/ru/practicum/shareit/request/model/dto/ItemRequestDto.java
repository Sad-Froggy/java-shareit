package ru.practicum.shareit.request.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.practicum.shareit.item.model.dto.ItemDtoOut;

import java.time.LocalDateTime;
import java.util.List;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ItemRequestDto {

    private Long id;

    private String description;

    private LocalDateTime created;

    private List<ItemDtoOut> items;
}
