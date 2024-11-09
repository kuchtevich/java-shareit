package ru.practicum.shareit.item.controller;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.practicum.shareit.item.dto.ItemBookingInfoDto;
import ru.practicum.shareit.item.dto.ItemDto;
import ru.practicum.shareit.item.service.ItemService;
import ru.practicum.shareit.item.dto.CommentDto;

import java.util.List;


@RestController
@Slf4j
@Validated
@RequiredArgsConstructor
@RequestMapping("/items")
public class ItemController {
    private final ItemService itemService;


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ItemDto create(@RequestHeader("X-Sharer-User-Id") @Positive final long userId,
                           @Valid @RequestBody final ItemDto itemDto) {
        log.info("Получили данные для создания предмета {} у пользователя по id {}", itemDto, userId);
        return itemService.create(userId, itemDto);
    }

    @GetMapping("/{itemId}")
    public ItemBookingInfoDto getById(@RequestHeader("X-Sharer-User-Id") final long userId,
                                      @PathVariable @Positive final long itemId) {
        log.info("Получен запрос на вывод у пользователя по id {} по предмета id {}", userId, itemId);
        return itemService.getById(userId, itemId);
    }


    @PatchMapping("/{itemId}")
    public ItemDto update(@RequestHeader("X-Sharer-User-Id") @Positive final long userId,
                           @PathVariable @Positive final long itemId,
                           @RequestBody final ItemDto itemDto) {
        log.info("Переданы данные на редактировние предмета по id: {}, пользователя по id: {}, данные {}", userId, itemId, itemDto);
        return itemService.update(userId, itemId, itemDto);
    }

    @GetMapping
    public List<ItemDto> getAllItems(@RequestHeader("X-Sharer-User-Id") @Positive final long userId) {
        log.info("Получаем список предметов по юзеру с id {}", userId);
        return itemService.getAllItems(userId);
    }

    @GetMapping("/search")
    public List<ItemDto> search(@RequestParam final String text) {
        log.info("Получили данные для поиска {}", text);
        return itemService.search(text);
    }

    @DeleteMapping("/{itemId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable @Positive final Long itemId) {
        itemService.delete(itemId);
    }

    @PostMapping("/{itemId}/comment")
    public CommentDto addComments(@RequestHeader("X-Sharer-User-Id") final long userId,
                                      @PathVariable final long itemId,
                                      @Valid @RequestBody final CommentDto commentDtoItem) {
        return itemService.addComments(userId, itemId, commentDtoItem);
    }
}
