package ru.practicum.shareit.item.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.practicum.shareit.booking.model.Booking;
import ru.practicum.shareit.booking.repository.BookingRepository;
import ru.practicum.shareit.error.BadRequestException;
import ru.practicum.shareit.error.NotFoundException;
import ru.practicum.shareit.item.dto.CommentDto;
import ru.practicum.shareit.item.dto.ItemBookingInfoDto;
import ru.practicum.shareit.item.dto.ItemDto;
import ru.practicum.shareit.item.mapper.CommentMapper;
import ru.practicum.shareit.item.mapper.ItemMapper;
import ru.practicum.shareit.item.model.Comment;
import ru.practicum.shareit.item.model.Item;
import ru.practicum.shareit.item.repository.CommentRepository;
import ru.practicum.shareit.item.repository.ItemRepository;
import ru.practicum.shareit.user.model.User;
import ru.practicum.shareit.user.repository.UserRepository;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ItemServiceImpl implements ItemService {

    private final ItemRepository itemRepository;

    private final UserRepository userRepository;

    private final BookingRepository bookingRepository;

    private final CommentRepository commentRepository;

    private final ItemMapper itemMapper;


    @Override
    public ItemDto create(Long userId, ItemDto itemDto) {
        User user = userRepository.findById(userId).orElseThrow(() ->
                new NotFoundException("Пользователя нет")
        );

        Item item = ItemMapper.toItem(user, itemDto);
        return ItemMapper.toItemDto(itemRepository.save(item));
    }

    @Override
    public ItemDto update(Long userId, Long itemId, ItemDto itemDto) {
        Item item = itemRepository.findById(itemId).orElseThrow(() -> new NotFoundException("Вещи нет"));

        if (itemDto.getName() != null && !itemDto.getName().isBlank()) {
            item.setName(itemDto.getName());
        }

        if (itemDto.getDescription() != null && !itemDto.getDescription().isBlank()) {
            item.setDescription(itemDto.getDescription());
        }
        if (itemDto.getAvailable() != null) {
            item.setAvailable(itemDto.getAvailable());
        }
        //item.setId(itemId);
        return itemMapper.toItemDto(itemRepository.save(item));
    }

    @Override
    public ItemBookingInfoDto get(Long userId, Long itemId) {
        if (userRepository.findById(userId).isEmpty()) {
            throw new NotFoundException("Пользователя нет.");
        }
        Item item = itemRepository.findById(itemId).orElseThrow(() -> new NotFoundException("Вещи нет"));
        List<CommentDto> commentsDto = commentRepository.findAllByItemId(itemId).steam().map(comment
                -> commentMapper.toCommentDto(comment)).collect(Collectors.toList());
        ItemBookingInfoDto itemBookingInfoDtot = itemMapper.toItemBookingInfoDto(item, commentsDto);
        if ((Objects.equals(item.getOwner().getId(), userId))) {
            Optional<Booking> last = bookingRepository.findTopByItemIdAndEndBeforeAndStatusInOrderByEndDesc(itemId,
                    LocalDateTime.now(), List.of(BookingStatus.APPROVED));
            itemBookingInfoDtot.setLastBooking(last == null ? null : last.get().getEnd());

            Optional<Booking> future = bookingRepository.findTopByItemIdAndStartAfterAndStatusInOrderByStartAsc(itemId,
                    LocalDateTime.now(), List.of(BookingStatus.APPROVED));
            itemBookingInfoDtot.setNextBooking(future == null ? null : future.get().getStart());
        }

        return itemBookingInfoDtot;
    }

    @Override
    public List<ItemDto> getOwnerItems(Long userId) {
        if (userRepository.findById(userId).isEmpty()) {
            throw new NotFoundException("Пользователя нет.");
        }
        List<Item> items = itemRepository.findAllByOwnerId(userId);
        return itemMapper.toListDto(items);
    }

    @Override
    public CommentDto addComment(Long userId, Long itemId, CommentDto commentDto) {
        User owner = userRepository.findById(userId).orElseThrow(() -> new NotFoundException("Пользователя нет"));
        Item item = itemRepository.findById(itemId).orElseThrow(() -> new NotFoundException("Вещи нет"));
        Comment comment = CommentMapper.toComment(commentDto, item, user);
        List<Booking> booking = bookingRepository.findAllByBookerIdAndItemIdAndStatusEqualsAndEndIsBefore(userId, itemId,
                BookingStatus.APPROVED, LocalDateTime.now());
        if (booking.isEmpty()) {
            throw new BadRequestException("Нет бронирований");
        }
        commentRepository.save(comment);
        return CommentMapper.toCommentDto(comment);
    }

    @Override
    public List<ItemDto> search(String text) {
        if (text.isEmpty()) {
            return new ArrayList<>();
        }
        return itemRepository.itemSearch(text.trim().toLowerCase()).stream().map(itemMapper::toItemDto).toList();;
    }



    private void delete(Long itemId) {
        Item item = itemRepository.findById(itemId)
                .orElseThrow(() -> new NotFoundException("Вещь не найдена."));
        itemRepository.delete(item);
    }
}
