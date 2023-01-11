package ru.practicum.shareit.item.repository.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import ru.practicum.shareit.exception_handler.exception.EntityNotFoundException;
import ru.practicum.shareit.item.model.Item;
import ru.practicum.shareit.item.repository.ItemRepository;

import java.util.*;
import java.util.stream.Collectors;

@Repository
@RequiredArgsConstructor
@Slf4j
public class InMemoryItemStorage implements ItemRepository {

    private final Map<Long, Item> items = new HashMap<>();
    private final Map<Long, List<Item>> userItemIndex = new LinkedHashMap<>();
    private Long id = 0L;

    @Override
    public Item create(Item item) {
        item.setId(++id);
        items.put(item.getId(), item);
        List<Item> userItems = userItemIndex.get(item.getOwner().getId());
        if (userItems != null) {
            userItems.add(item);
        } else {
            userItemIndex.put(item.getOwner().getId(), List.of(item));
        }
        log.info("Добавлена новая вещь " + item.getName() + " с id " + item.getId()
                + " для пользователя + " + item.getOwner().getId());
        return item;
    }

    @Override
    public Item update(Item item, Long userId, Long itemId) {
        if (items.get(itemId) != null
                && userItemIndex.get(userId) != null
                && userItemIndex.get(userId).stream().anyMatch(i -> i.getId().equals(itemId))) {
            patchItem(items.get(itemId), item);
            log.info("Вещь с id " + itemId + " Обновлена");
            return items.get(itemId);
        } else {
            throw new EntityNotFoundException("Объект для обновления не найден");
        }
    }

    @Override
    public Item getById(Long userId, Long id) {
        Item item = items.get(id);
        if (item != null) {
            return item;
        } else {
            throw new EntityNotFoundException("Вещи c таким id не существует");
        }
    }

    @Override
    public List<Item> getByUser(Long userId) {
        if (userItemIndex.get(userId) != null) {
            return userItemIndex.get(userId);
        } else {
            throw new EntityNotFoundException("Пользователь с id " + userId +
                    " не найден, либо у пользователя нет ни одной вещи");
        }
    }

    @Override
    public List<Item> searchItem(String text) {
        if (text.isEmpty() || text.isBlank()) {
            log.info("В параметр поиска была передана пустая строка");
            return Collections.emptyList();
        }
        return items.values().stream()
                .filter(i -> (i.getDescription().toLowerCase().contains(text.toLowerCase())
                        || i.getName().toLowerCase().contains(text.toLowerCase()))
                        && i.getAvailable().equals(true)
                )
                .collect(Collectors.toList());

    }

    private void patchItem(Item oldItem, Item newItem) {
        if (newItem.getName() != null) {
            oldItem.setName(newItem.getName());
        }
        if (newItem.getDescription() != null) {
            oldItem.setDescription(newItem.getDescription());
        }
        if (newItem.getRequest() != null) {
            oldItem.setRequest(newItem.getRequest());
        }
        if (newItem.getAvailable() != null) {
            oldItem.setAvailable(newItem.getAvailable());
        }
        items.put(oldItem.getId(), oldItem);
    }

}
