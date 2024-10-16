package ru.practicum.shareit.item.repository;

import ru.practicum.shareit.item.model.Item;

import java.util.List;


public interface ItemRepository {
    List<Item> getAll(final long userId);

    Item create(Item item);

    Item update(Item item);

    Item get(long id);
}
