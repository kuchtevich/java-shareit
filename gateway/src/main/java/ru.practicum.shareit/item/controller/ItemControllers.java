package ru.practicum.shareit.item.controller;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.practicum.shareit.item.client.ClientItem;
import ru.practicum.shareit.item.dto.CommentDtoItem;
import ru.practicum.shareit.item.dto.ItemsDto;
import org.springframework.http.ResponseEntity;


@RestController
@Slf4j
@Validated
@RequiredArgsConstructor
@RequestMapping("/items")
public class ItemControllers {
    private final ClientItem clientItem;


    @GetMapping
    public ResponseEntity<Object> getAllItems(@RequestHeader("X-Sharer-User-Id") @Positive final long userId) {
        return clientItem.getAllItems(userId);
    }

    @GetMapping("/{itemId}")
    public ResponseEntity<Object> getItemById(@RequestHeader("X-Sharer-User-Id") final long userId, @PathVariable @Positive final long itemId) {
        return clientItem.getItemById(userId, itemId);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Object> create(@RequestHeader("X-Sharer-User-Id") @Positive final long userId,
                                             @Valid @RequestBody final ItemsDto itemsDto) {
        return clientItem.create(userId, itemsDto);
    }

    @PatchMapping("/{itemId}")
    public ResponseEntity<Object> update(@RequestHeader("X-Sharer-User-Id") @Positive final long userId,
                                             @PathVariable @Positive final long itemId, @RequestBody final ItemsDto itemsDto) {
        return clientItem.update(userId, itemId, itemsDto);
    }

    @GetMapping("/search")
    public ResponseEntity<Object> search(@RequestHeader("X-Sharer-User-Id") @Positive final long userId,
                                             @RequestParam(required = false) final String text) {
        return clientItem.search(userId, text);
    }

    @DeleteMapping("/{itemId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void itemDelete(@PathVariable @Positive final Long itemId) {
        clientItem.itemDelete(itemId);
    }

    @PostMapping("/{itemId}/comments")
    public ResponseEntity<Object> addComments(@RequestHeader("X-Sharer-User-Id") final long userId,
                                              @PathVariable final long itemId, @Valid @RequestBody final CommentDtoItem commentDtoItem) {
        return clientItem.addComments(userId, itemId, commentDtoItem);
    }
}
