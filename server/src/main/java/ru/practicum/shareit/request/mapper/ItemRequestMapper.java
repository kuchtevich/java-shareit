package ru.practicum.shareit.request.mapper;

import org.springframework.stereotype.Component;
import ru.practicum.shareit.request.dto.ItemRequestDto;
import ru.practicum.shareit.request.model.ItemRequest;
import ru.practicum.shareit.user.model.User;

import java.time.LocalDateTime;

@Component
public class ItemRequestMapper {

    public ItemRequest toItemRequest(final ItemRequestDto itemRequestDto, final User user) {

        final ItemRequest itemRequest = new ItemRequest();

        itemRequest.setDescription(itemRequestDto.getDescription());
        itemRequest.setCreated(LocalDateTime.now());
        itemRequest.setRequestor(user);

        return itemRequest;
    }

public ItemRequestDto toItemRequestDto(final ItemRequest itemRequest) {

    final ItemRequestDto itemRequestDto = new ItemRequestDto();

    itemRequestDto.setId(itemRequest.getId());
    itemRequestDto.setCreated(itemRequest.getCreated());
    itemRequestDto.setDescription(itemRequest.getDescription());

    return itemRequestDto;
}
}
