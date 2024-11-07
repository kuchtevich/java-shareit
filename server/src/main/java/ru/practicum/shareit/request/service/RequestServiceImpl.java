package ru.practicum.shareit.request.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.practicum.shareit.request.dto.ItemRequestDto;
import ru.practicum.shareit.user.repository.UserRepository;
import ru.practicum.shareit.user.mapper.UserMapper;
import ru.practicum.shareit.item.mapper.ItemMapper;
import ru.practicum.shareit.item.repository.ItemRepository;
import org.springframework.data.domain.Sort;
import ru.practicum.shareit.request.model.ItemRequest;


import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class RequestServiceImpl implements RequestService {
    private final UserRepository userRepository;
    private final RequestRepository requestRepository;
    private final requestMapper requestMapper;
    private final UserMapper userMapper;
    private final ItemRepository itemRepository;
    private final ItemMapper itemMapper;
    final Sort sort = Sort.by(Sort.Direction.DESC, "created");

    @Override
    public ItemRequestDto create(final long requestId, final ItemRequestDto itemRequestDto) {
        final User user = userRepository.findById(userId)
                .orElseThrow(() -> new NotFoundException("Пользователь с id: " + userId + " не найден."));
        final ItemRequest itemRequest = itemRequestRepository.save(itemRequestMapper.toItemRequest(itemRequestDto,
                user));

        final ItemRequestDtoitemRequestDtoO = requestMapper.torequestDto(request);
        itemRequestDto.setRequester(userMapper.toUserDto(user));

        return itemRequestDto;
        }

    @Override
        public ItemRequestDto answerRequestbyId(final long userId) {
        final List<ItemRequest> itemRequests = requestRepository.findAllByRequesterId(userId, sort);
        List<ItemRequestDto> responseList = new ArrayList<>();

        for (ItemRequest itemRequest : itemRequests) {
            ItemRequestDto itemRequestDto = requestMapper.toItemRequestDtoOutput(itemRequest);

            List<ItemDtoResponseRequest> items = itemRepository.findAllByRequest(itemRequest)
                    .stream()
                    .map(itemMapper::toItemDtoResponseRequest)
                    .collect(Collectors.toList());

            itemRequestDto.setItems(items);
            itemRequestDto.setRequester(userMapper.toUserDto(itemRequest.getRequester()));
            responseList.add(itemRequestDtoOutput);
        }
        return responseList;
    }

    @Override
    public List<ItemRequestDto> getAllRequest(final long usertId) {
        final List<ItemRequest> itemRequests = itemRequestRepository.findAllByRequesterIdNot(userId, sort);
        List<ItemRequestDtoOutput> responselist = new ArrayList<>();

        for (ItemRequest itemRequest : itemRequests) {
            ItemRequestDtoOutput itemRequestDtoOutput = itemRequestMapper.toItemRequestDtoOutput(itemRequest);
            itemRequestDtoOutput.setRequester(userMapper.toUserDto(itemRequest.getRequester()));
            responselist.add(itemRequestDtoOutput);
        }

        return responselist;
    }

    @Override
    public ItemRequestDto getById(final long requestId) {
        final ItemRequest itemRequest = itemRequestRepository.findById(requestId)
           .orElseThrow(() -> new NotFoundException("Запрос с id = {} не найден." + requestId));
        final ItemRequestDtoOutput itemRequestDtoOutput = itemRequestMapper.toItemRequestDtoOutput(itemRequest);
        final List<ItemDtoResponseRequest> items = itemRepository.findAllByRequest(itemRequest)
                .stream()
                .map(itemMapper::toItemDtoResponseRequest)
                .collect(Collectors.toList());

        itemRequestDtoOutput.setItems(items);
        itemRequestDtoOutput.setRequester(userMapper.toUserDto(itemRequest.getRequester()));

        return itemRequestDtoOutput;
    }
}
