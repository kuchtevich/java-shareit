package ru.practicum.shareit.booking.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import ru.practicum.shareit.booking.dto.BookingDto;
import ru.practicum.shareit.booking.dto.BookingDtoTime;
import ru.practicum.shareit.booking.mapper.BookingMapper;
import ru.practicum.shareit.booking.model.Booking;
import ru.practicum.shareit.booking.model.Status;
import ru.practicum.shareit.booking.repository.BookingRepository;
import ru.practicum.shareit.error.NotFoundException;
import ru.practicum.shareit.error.UserNotOwnerException;
import ru.practicum.shareit.error.ValidationException;
import ru.practicum.shareit.item.mapper.ItemMapper;
import ru.practicum.shareit.item.repository.ItemRepository;
import ru.practicum.shareit.user.mapper.UserMapper;
import ru.practicum.shareit.user.model.User;
import ru.practicum.shareit.user.repository.UserRepository;
import ru.practicum.shareit.item.model.Item;
import java.time.LocalDateTime;
import ru.practicum.shareit.booking.model.Booking;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Slf4j
@RequiredArgsConstructor
@Service
public class BookingServiceImpl implements BookingService {
    private final BookingRepository bookingRepository;
    private final UserRepository userRepository;
    private final ItemRepository itemRepository;
    private final BookingMapper bookingMapper;
    private final UserMapper userMapper;
    private final ItemMapper itemMapper;
    private final Sort sort = Sort.by(Sort.Direction.DESC, "start");


    @Override
    public BookingDtoTime create(final Long userId, BookingDto bookingDto) {
        User user = findUser(userId);
        Item item = itemRepository.findById(bookingDto.getItemId()).orElseThrow(()
                -> new NotFoundException("Вещи с id = {} нет." + bookingDto.getItemId()));
        if (!item.getAvailable()) {
            throw new ValidationException("Неправильный статус");
        }
        Booking booking = bookingMapper.toBooking(bookingDto, user, item);
        booking.setStatus(Status.WAITING);
        bookingRepository.save(booking);

        return bookingMapper.toBookingDtoTime(booking, userMapper.toUserDto(user), itemMapper.toItemDto(item));
    }

    @Override
    public BookingDtoTime approval(final Long userId, final Long bookingId, final Boolean approved) {
        Booking booking = bookingRepository.findByIdAndOwnerId(bookingId, userId).orElseThrow(()
                -> new ValidationException("бронирование с id = {} не найдено" + bookingId));
        if (!booking.getStatus().equals(Status.WAITING)) {
            throw new NotFoundException("невозможно изменить статус");
        }
        booking.setStatus(approved ? Status.APPROVED : Status.REJECTED);
        final Booking updateBooking = bookingRepository.save(booking);

        return bookingMapper.toBookingDtoTime(updateBooking,
                userMapper.toUserDto(booking.getBooker()), itemMapper.toItemDto(booking.getItem()));
    }

    @Override
    public BookingDtoTime getById(final Long userId, Long bookingId) {
        Booking booking = bookingRepository.findById(bookingId).orElseThrow(()
                -> new NotFoundException("Бронирование с id = {} не найдено" + bookingId));
        if (!Objects.equals(booking.getItem().getOwner().getId(), userId)
                && !Objects.equals(booking.getBooker().getId(), userId)) {
            throw new UserNotOwnerException("Пользователь не является хозяином вещи");
        }
        return bookingMapper.toBookingDtoTime(booking,
                userMapper.toUserDto(booking.getBooker()), itemMapper.toItemDto(booking.getItem()));
    }

    @Override
    public Collection<BookingDtoTime> getAllBookingFromUser(final Long userId, final Status status) {
        findUser(userId);
        LocalDateTime time = LocalDateTime.now();
        List<Booking> bookings;
        switch (status) {
            case ALL:
                bookings = bookingRepository.findAllByBookerId(userId, sort);
                break;
            case CURRENT:
                bookings = bookingRepository.findAllByBookerIdAndStartBeforeAndEndAfter(userId, time, time, sort);
                break;
            case PAST:
                bookings = bookingRepository.findAllByBookerIdAndEndBefore(userId, time, sort);
                break;
            case FUTURE:
                bookings = bookingRepository.findAllByBookerIdAndStartAfter(userId, time, sort);
                break;
            case WAITING:
                bookings = bookingRepository.findAllByBookerIdAndStatusIs(userId, Status.WAITING, sort);
                break;
            case REJECTED:
                bookings = bookingRepository.findAllByBookerIdAndStatusIs(userId, Status.REJECTED, sort);
                break;
            default:
                throw new IllegalArgumentException("Ошибка состояния");
        }
        return bookings.stream()
                .map(booking -> bookingMapper.toBookingDtoTime(booking,
                        userMapper.toUserDto(booking.getBooker()), itemMapper.toItemDto(booking.getItem())))
                .collect(Collectors.toList());
    }

    @Override
    public Collection<BookingDtoTime> getAllBookingFromOwner(final Long userId, final Status status) {
        findUser(userId);
        LocalDateTime time = LocalDateTime.now();
        List<Booking> bookings;
        if (itemRepository.findAllByOwnerId(userId).isEmpty()) {
            throw new ValidationException("Вещи не найдены.");
        }
        switch (status) {
            case ALL:
                bookings = bookingRepository.findAllByItemOwnerId(userId, sort);
                break;
            case CURRENT:
                bookings = bookingRepository.findAllByItemOwnerIdAndStartBeforeAndEndAfter(userId, time, time, sort);
                break;
            case PAST:
                bookings = bookingRepository.findAllByItemOwnerIdAndEndBefore(userId, time, sort);
                break;
            case FUTURE:
                bookings = bookingRepository.findAllByItemOwnerIdAndStartAfter(userId, time, sort);
                break;
            case WAITING:
                bookings = bookingRepository.findAllByItemOwnerIdAndStatusIs(userId, Status.WAITING, sort);
                break;
            case REJECTED:
                bookings = bookingRepository.findAllByItemOwnerIdAndStatusIs(userId, Status.REJECTED, sort);
                break;
            default:
                throw new IllegalArgumentException("Ошибка состояния");
        }
        return bookings.stream()
                .map(booking -> bookingMapper.toBookingDtoTime(booking,
                        userMapper.toUserDto(booking.getBooker()), itemMapper.toItemDto(booking.getItem())))
                .collect(Collectors.toList());
    }

    private User findUser(final Long userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new NotFoundException("Пользователь с id: " + userId + " не найден."));
    }
}
