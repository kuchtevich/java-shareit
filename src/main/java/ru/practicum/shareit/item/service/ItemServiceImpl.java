package ru.practicum.shareit.item.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.practicum.shareit.error.NotFoundException;
import ru.practicum.shareit.item.dto.ItemDto;
import ru.practicum.shareit.item.mapper.ItemMapper;
import ru.practicum.shareit.item.model.Item;
import ru.practicum.shareit.item.repository.ItemRepository;
import ru.practicum.shareit.user.model.User;
import ru.practicum.shareit.user.repository.UserRepository;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class ItemServiceImpl implements ItemService {

    private final ItemRepository itemRepository;

    private final UserRepository userRepository;


    @Override
    public ItemDto create(Long userId, ItemDto itemDto) {
        User user;
        if (userRepository.get(userId).isPresent()) {
            user = userRepository.get(userId).get();
        } else {
            throw new NotFoundException("Пользователь не найден");
        }
        Item item = ItemMapper.toItem(user, itemDto);
        return ItemMapper.toItemDto(itemRepository.create(item));
    }

    @Override
    public ItemDto update(Long userId, Long itemId, ItemDto itemDto) {
        User user = userRepository.get(userId).orElseThrow(() -> new NotFoundException("Пользователя нет"));
        Item item = itemRepository.get(itemId).orElseThrow(() -> new NotFoundException("Вещи нет"));
        if (!item.getOwner().getId().equals(user.getId())) {
            throw new NotFoundException("Не является хозяином");
        }
        Item itemUpdate = ItemMapper.toItem(user, itemDto);
        itemUpdate.setId(itemId);
        return ItemMapper.toItemDto(itemRepository.update(itemUpdate));

    }

    @Override
    public ItemDto getById(Long itemId) {
        Item item = itemRepository.get(itemId).orElseThrow(() -> new NotFoundException("Такой вещи нет"));
        return ItemMapper.toItemDto(item);
    }

    @Override
    public List<ItemDto> getOwnerItems(Long userId) {
        return itemRepository.getOwnerItems(userId).stream().map(ItemMapper::toItemDto).toList();
    }

    @Override
    public List<ItemDto> search(String query) {
        return itemRepository.search(query).stream()
                .map(ItemMapper::toItemDto)
                .toList();
    }
}
