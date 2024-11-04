package ru.practicum.shareit.item.service;

import ru.practicum.shareit.item.dto.CommentDto;
import ru.practicum.shareit.item.dto.ItemBookingInfoDto;
import ru.practicum.shareit.item.dto.ItemDto;

import java.util.List;

public interface ItemService {
    List<ItemDto> getOwnerItems(Long userId);

    ItemBookingInfoDto getById(Long userId, Long itemId);

    ItemDto create(Long userId, ItemDto itemDto);

    ItemDto update(Long userId, Long itemId, ItemDto itemDto);

    List<ItemDto> search(String text);

    void delete(Long itemId);

    CommentDto addComments(Long userId, Long itemId, CommentDto commentDto);

}
