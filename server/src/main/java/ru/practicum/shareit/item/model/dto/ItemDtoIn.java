package ru.practicum.shareit.item.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ItemDtoIn {

    private Long id;

    private String name;

    private String description;

    private Boolean available;

    private Long requestId;
}
