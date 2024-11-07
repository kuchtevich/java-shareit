package ru.practicum.shareit.request;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.practicum.shareit.request.dto.ItemRequestDto;
import ru.practicum.shareit.request.service.RequestService;

import javax.validation.constraints.Positive;
import java.util.List;

@RestController
@RequestMapping(path = "/requests")
public class ItemRequestController {
    private RequestService requestServicee;

    @PostMapping("/requestId")
    @ResponseStatus(HttpStatus.CREATED)
    public ItemRequestDto create(@Positive final long requestId,
                                 @Valid @RequestBody final ItemRequestDto itemRequestDto) {

    }

    @GetMapping("/requestId")
    @ResponseStatus //описание, дата и время создания
    public ItemRequestDto answerRequest(@Positive final long requestId, @Valid @RequestBody final long userId) {

    }

    @GetMapping
    public List<ItemRequestDto> getAllItems(final long requestId) {

    }

    @GetMapping("/{requestId}")
    public ItemRequestDto getById(final long itemId, final long userId) {

    }

}
