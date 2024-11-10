package ru.practicum.shareit.item.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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
import ru.practicum.shareit.request.model.ItemRequest;
import ru.practicum.shareit.request.repository.RequestRepository;
import ru.practicum.shareit.user.model.User;
import ru.practicum.shareit.user.repository.UserRepository;
import ru.practicum.shareit.booking.model.Status;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class ItemServiceImpl implements ItemService {

    private final ItemRepository itemRepository;

    private final UserRepository userRepository;

    private final BookingRepository bookingRepository;

    private final CommentRepository commentRepository;

    private final ItemMapper itemMapper;

    private final CommentMapper commentMapper;
    private final RequestRepository requestRepository;

    @Override
    public ItemDto create(final long userId,final ItemDto itemDto){
        User user = findUser(userId);
        Item item = itemMapper.toItem(user, itemDto);
        System.out.println();
        if (itemDto.getRequestId() != null) {
            ItemRequest itemRequest = requestRepository.findById(itemDto.getRequestId())
                    .orElseThrow(() -> new NotFoundException("Запрос не найден"));
            item.setRequest(itemRequest);
        }
        return itemMapper.toItemDto(itemRepository.save(item));
    }

    @Override
    public ItemDto update(final long userId, final long itemId, final ItemDto itemDto) {
        if (userRepository.findById(userId).isEmpty()) {
            throw new NotFoundException("Пользователя нет.");
        }
        Item item = findItem(itemId);
        if (!Objects.equals(item.getOwner().getId(), userId)) {
            throw new NotFoundException("Ошибка.");
        }
        if (itemDto.getName() != null && !itemDto.getName().isBlank()) {
            item.setName(itemDto.getName());
        }

        if (itemDto.getDescription() != null && !itemDto.getDescription().isBlank()) {
            item.setDescription(itemDto.getDescription());
        }
        if (itemDto.getAvailable() != null) {
            item.setAvailable(itemDto.getAvailable());
        }
        return itemMapper.toItemDto(itemRepository.save(item));
    }

    @Override
    public ItemBookingInfoDto getById(final long userId, final long itemId) {
        final Item item = findItem(itemId);
        List<CommentDto> commentsDto = commentRepository.findAllByItemId(itemId).stream()
                .map(comment -> commentMapper.toCommentDto(comment)).collect(Collectors.toList());
        ItemBookingInfoDto itemBookingInfoDto = itemMapper.toItemBookingInfoDto(item, commentsDto);
        if ((Objects.equals(item.getOwner().getId(), userId))) {
            Optional<Booking> last = bookingRepository.findTopByItemIdAndEndBeforeAndStatusInOrderByEndDesc(itemId,
                    LocalDateTime.now(), List.of(Status.APPROVED));
            itemBookingInfoDto.setLastBooking(last == null ? null : last.get().getEnd());

            Optional<Booking> future = bookingRepository.findTopByItemIdAndStartAfterAndStatusInOrderByStartAsc(itemId,
                    LocalDateTime.now(), List.of(Status.APPROVED));
            itemBookingInfoDto.setNextBooking(future == null ? null : future.get().getStart());
        }
        return itemBookingInfoDto;
    }

    @Override
    public List<ItemDto> getAllItems(final long userId) {
        if (userRepository.findById(userId).isEmpty()) {
            throw new NotFoundException("Пользователя нет.");
        }
        List<Item> items = itemRepository.findAllByOwnerId(userId);
        return itemMapper.toListDto(items);
    }

    @Override
    public CommentDto addComments(final long userId, final long itemId, final CommentDto commentDto) {
        User owner = findUser(userId);
        Item item = findItem(itemId);
        if (bookingRepository.findAllByBookerIdAndItemIdAndStatusEqualsAndEndIsBefore(userId, itemId,
                Status.APPROVED, LocalDateTime.now()).isEmpty()) {
            throw new BadRequestException("Нет бронирований");
        }
        final Comment comment = commentMapper.toComment(commentDto, owner, item);
        commentRepository.save(comment);
        return commentMapper.toCommentDto(comment);
    }

    @Override
    public List<ItemDto> search(final String text) {
        if (text.isEmpty()) {
            return new ArrayList<>();
        }
        return itemRepository.search(text.trim().toLowerCase()).stream().map(itemMapper::toItemDto).toList();

    }

    @Override
    public void delete(final Long itemId) {
        Item item = findItem(itemId);
        itemRepository.delete(item);
    }

    private Item findItem(final Long itemId) {
        return itemRepository.findById(itemId).orElseThrow(() -> new NotFoundException("Вещи нет с id " + itemId + "нет"));
    }

    private User findUser(final Long userId) {
        return userRepository.findById(userId).orElseThrow(() -> new NotFoundException("Пользователя с id" + userId + "нет"));
    }

}
