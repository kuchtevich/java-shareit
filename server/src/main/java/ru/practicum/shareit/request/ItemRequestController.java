package ru.practicum.shareit.request;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.practicum.shareit.request.dto.ItemDtoAnswer;
import ru.practicum.shareit.request.dto.ItemRequestDto;
import ru.practicum.shareit.request.service.RequestService;

import java.util.List;

@RestController
@RequestMapping(path = "/requests")
@RequiredArgsConstructor
public class ItemRequestController {
    private final RequestService requestService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ItemDtoAnswer create(@RequestHeader("X-Sharer-User-Id") final long userId,
                                @RequestBody final ItemRequestDto itemRequestDto) {
        return requestService.create(userId, itemRequestDto);

    }

    @GetMapping
    public List<ItemDtoAnswer> answerRequestById(@RequestHeader("X-Sharer-User-Id") final long userId) {
        return requestService.answerRequestbyId(userId);
    }

    @GetMapping("/all")
    public List<ItemDtoAnswer> getAllRequest(@RequestHeader("X-Sharer-User-Id") final long userId) {
    return requestService.getAllRequest(userId);
    }

    @GetMapping("/{requestId}")
    public ItemDtoAnswer getById(@PathVariable final long requestId) {
    return requestService.getById(requestId);
    }

}
