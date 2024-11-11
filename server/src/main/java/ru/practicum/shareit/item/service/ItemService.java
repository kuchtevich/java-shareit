package ru.practicum.shareit.item.service;

import ru.practicum.shareit.item.dto.CommentDto;
import ru.practicum.shareit.item.dto.ItemBookingInfoDto;
import ru.practicum.shareit.item.dto.ItemDto;

import java.util.List;


public interface ItemService {
    List<ItemDto> getAllItems(final long userId);

    ItemBookingInfoDto getById(final long userId, final long itemId);

    ItemDto create(final long userId, final ItemDto itemDto);

    ItemDto update(final long userId, final long itemId, final ItemDto itemDto);

    List<ItemDto> search(final String text);

    void delete(final Long itemId);

    CommentDto addComments(final long userId, final long itemId, final CommentDto commentDto);

}
