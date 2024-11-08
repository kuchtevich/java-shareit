package src.main.java.ru.practicum.shareit.item.controller;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import src.main.java.ru.practicum.shareit.item.client.ClientItem;
import src.main.java.ru.practicum.shareit.item.dto.CommentDto;
import src.main.java.ru.practicum.shareit.item.dto.ItemDto;
import org.springframework.http.ResponseEntity;

import java.util.List;


@RestController
@Slf4j
@Validated
@RequiredArgsConstructor
@RequestMapping("/items")
public class ItemController {
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
                                             @Valid @RequestBody final ItemDto itemDto) {
        return clientItem.create(userId, itemDto);
    }

    @PatchMapping("/{itemId}")
    public ResponseEntity<Object> update(@RequestHeader("X-Sharer-User-Id") @Positive final long userId,
                                             @PathVariable @Positive final long itemId, @RequestBody final ItemDto itemDto) {
        return clientItem.update(userId, itemId, itemDto);
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

    @PostMapping("/{itemId}/comment")
    public ResponseEntity<Object> addComments(@RequestHeader("X-Sharer-User-Id") final long userId,
                                              @PathVariable final long itemId, @Valid @RequestBody final CommentDto commentDto) {
        return clientItem.addComments(userId, itemId, commentDto);
    }
}
