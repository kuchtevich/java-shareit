package src.main.java.ru.practicum.shareit.request.service;


import ru.practicum.shareit.request.dto.ItemDtoAnswer;
import src.main.java.ru.practicum.shareit.request.dto.ItemRequestDto;

import java.util.List;

public interface RequestService {

    ItemDtoAnswer create(final long userId, final ItemRequestDto itemRequestDto);

    List<ItemDtoAnswer> answerRequestbyId(final long userId);

    List<ItemDtoAnswer> getAllRequest(final long usertId);

    ItemDtoAnswer getById(final long requestId);

}
