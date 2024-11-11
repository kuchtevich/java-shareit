package ru.practicum.shareit.request.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.practicum.shareit.item.dto.ItemDtoRequest;
import ru.practicum.shareit.request.dto.ItemDtoAnswer;
import ru.practicum.shareit.request.dto.ItemRequestDto;
import ru.practicum.shareit.request.mapper.RequestMapper;
import ru.practicum.shareit.request.repository.RequestRepository;
import ru.practicum.shareit.user.repository.UserRepository;
import ru.practicum.shareit.user.mapper.UserMapper;
import ru.practicum.shareit.item.mapper.ItemMapper;
import ru.practicum.shareit.item.repository.ItemRepository;
import org.springframework.data.domain.Sort;
import ru.practicum.shareit.request.model.ItemRequest;
import ru.practicum.shareit.error.NotFoundException;
import ru.practicum.shareit.user.model.User;


import java.util.List;
import java.util.ArrayList;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class RequestServiceImpl implements RequestService {
    private final UserRepository userRepository;
    private final RequestRepository requestRepository;
    private final RequestMapper requestMapper;
    private final UserMapper userMapper;
    private final ItemRepository itemRepository;
    private final ItemMapper itemMapper;
    final Sort sort = Sort.by(Sort.Direction.DESC, "created");

    @Override
    public ItemDtoAnswer create(final long userId, final ItemRequestDto itemRequestDto) {
        final User user = userRepository.findById(userId)
                .orElseThrow(() -> new NotFoundException("Пользователь с id: " + userId + " не найден."));
        final ItemRequest itemRequest = requestRepository.save(requestMapper.toItemRequest(itemRequestDto,
                user));

        final ItemDtoAnswer itemDtoAnswer = requestMapper.toItemDtoAnswer(itemRequest);
        itemDtoAnswer.setRequestor(userMapper.toUserDto(user));

        return itemDtoAnswer;
    }

    @Override
    public List<ItemDtoAnswer> answerRequestbyId(final long userId) {
        final List<ItemRequest> itemRequests = requestRepository.findAllByRequestorId(userId, sort);
        List<ItemDtoAnswer> answer = new ArrayList<>();

        for (ItemRequest itemRequest : itemRequests) {
            ItemDtoAnswer itemDtoAnswer = requestMapper.toItemDtoAnswer(itemRequest);

            List<ItemDtoRequest> items = itemRepository.findAllByRequest(itemRequest)
                    .stream()
                    .map(itemMapper::toItemDtoRequest)
                    .collect(Collectors.toList());

            itemDtoAnswer.setItems(items);
            itemDtoAnswer.setRequestor(userMapper.toUserDto(itemRequest.getRequestor()));
            answer.add(itemDtoAnswer);
        }
        return answer;
    }


        @Override
    public List<ItemDtoAnswer> getAllRequest(final long userId) {
        final List<ItemRequest> itemRequests = requestRepository.findAllByRequestorIdNot(userId, sort);
        List<ItemDtoAnswer> answer = new ArrayList<>();

        for (ItemRequest itemRequest : itemRequests) {
            ItemDtoAnswer itemDtoAnswer = requestMapper.toItemDtoAnswer(itemRequest);
            itemDtoAnswer.setRequestor(userMapper.toUserDto(itemRequest.getRequestor()));
            answer.add(itemDtoAnswer);
        }

        return answer;
    }

    @Override
    public ItemDtoAnswer getById(final long requestId) {
        final ItemRequest itemRequest = requestRepository.findById(requestId)
                .orElseThrow(() -> new NotFoundException("Запрос с id = {} не найден." + requestId));
        final ItemDtoAnswer itemDtoAnswer = requestMapper.toItemDtoAnswer(itemRequest);
        final List<ItemDtoRequest> items = itemRepository.findAllByRequestId(itemRequest.getId())
                .stream()
                .map(itemMapper::toItemDtoRequest)
                .collect(Collectors.toList());

        itemDtoAnswer.setItems(items);
        itemDtoAnswer.setRequestor(userMapper.toUserDto(itemRequest.getRequestor()));

        return itemDtoAnswer;
    }
}
