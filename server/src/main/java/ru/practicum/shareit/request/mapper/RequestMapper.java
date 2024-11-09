package ru.practicum.shareit.request.mapper;

import org.springframework.stereotype.Component;
import ru.practicum.shareit.request.dto.ItemDtoAnswer;
import ru.practicum.shareit.request.dto.ItemRequestDto;
import ru.practicum.shareit.request.model.ItemRequest;
import ru.practicum.shareit.user.model.User;

import java.time.LocalDateTime;

@Component
public class RequestMapper {

    public ItemRequest toItemRequest(final ItemRequestDto itemRequestDto, final User user) {

        final ItemRequest itemRequest = new ItemRequest();

        itemRequest.setDescription(itemRequestDto.getDescription());
        itemRequest.setCreated(LocalDateTime.now());
        itemRequest.setRequestor(user);

        return itemRequest;
    }

public ItemDtoAnswer toItemDtoAnswer(final ItemRequest itemRequest) {

    final ItemDtoAnswer itemDtoAnswer = new ItemDtoAnswer();

    itemDtoAnswer.setId(itemRequest.getId());
    itemDtoAnswer.setCreated(itemRequest.getCreated());
    itemDtoAnswer.setDescription(itemRequest.getDescription());

    return itemDtoAnswer;
}
}
