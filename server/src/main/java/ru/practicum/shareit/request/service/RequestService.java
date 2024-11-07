package ru.practicum.shareit.request.service;


import ru.practicum.shareit.item.dto.ItemBookingInfoDto;
import ru.practicum.shareit.request.dto.ItemRequestDto;

import java.util.List;

public interface RequestService {

    ItemRequestDto create(final long requestId, final ItemRequestDto itemRequestDto);

    ItemRequestDto answerRequestbyId(final long userId);

    List<ItemRequestDto> getAllRequest(final long usertId);

    ItemRequestDto getById(final long requestId);

}
