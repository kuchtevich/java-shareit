package ru.practicum.shareit.item.repository;

import ru.practicum.shareit.item.model.Item;

import java.util.List;
import java.util.Optional;


public interface ItemRepository {
    Item create(Item item);

    Item update(Item item);

    Optional<Item> get(Long id);

    List<Item> getALl();

    List<Item> getOwnerItems(Long id);

    List<Item> search(String query);
}
