package ru.practicum.shareit.item.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.practicum.shareit.comment.model.dto.CommentDto;
import ru.practicum.shareit.comment.service.CommentService;
import ru.practicum.shareit.item.model.dto.ItemDtoIn;
import ru.practicum.shareit.item.model.dto.ItemDtoOut;
import ru.practicum.shareit.item.service.ItemService;

import javax.validation.Valid;
import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;
import java.util.List;

/**
 * TODO Sprint add-controllers.
 */
@RestController
@RequestMapping("/items")
@RequiredArgsConstructor
public class ItemController {

    private final ItemService itemService;
    private final CommentService commentService;
    private static final String SHARER_USER_ID = "X-Sharer-User-Id";

    @PostMapping
    public ItemDtoOut create(@RequestBody @Valid ItemDtoIn itemDto,
                             @RequestHeader(SHARER_USER_ID) Long userId) {
        return itemService.create(itemDto, userId);
    }

    @PatchMapping("/{itemId}")
    public ItemDtoOut update(@RequestHeader(SHARER_USER_ID) Long userId,
                             @PathVariable("itemId") Long itemId,
                             @RequestBody ItemDtoIn itemDto) {
        itemDto.setId(itemId);
        return itemService.update(itemDto, userId);
    }

    @GetMapping("{itemId}")
    public ItemDtoOut getItemById(@RequestHeader(SHARER_USER_ID) Long userId,
                                  @PathVariable("itemId") Long itemId) {
        return itemService.getByItemIdAndUserId(userId, itemId);
    }

    @GetMapping
    public List<ItemDtoOut> getItems(@RequestHeader(SHARER_USER_ID) Long userId,
                                     @RequestParam(value = "from", defaultValue = "0")
                                     @PositiveOrZero int from,
                                     @RequestParam(value = "size", defaultValue = "10")
                                     @Positive int size) {
        return itemService.getAll(userId, from, size);
    }

    @GetMapping("/search")
    public List<ItemDtoOut> getItemsByText(@RequestHeader(SHARER_USER_ID) Long userId,
                                           @RequestParam("text") String text,
                                           @RequestParam(value = "from", defaultValue = "0")
                                               @PositiveOrZero int from,
                                           @RequestParam(value = "size", defaultValue = "10")
                                               @Positive int size) {
        return itemService.getByText(text, userId, from, size);
    }

    @PostMapping("/{itemId}/comment")
    public CommentDto createComment(@Valid @RequestBody CommentDto commentDto,
                                    @PathVariable("itemId") Long itemId,
                                    @RequestHeader(SHARER_USER_ID) Long userId) {
        return commentService.create(commentDto, itemId, userId);
    }
}
