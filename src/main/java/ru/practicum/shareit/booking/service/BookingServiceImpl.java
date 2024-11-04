package ru.practicum.shareit.booking.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.practicum.shareit.booking.dto.BookingDto;
import ru.practicum.shareit.booking.mapper.BookingMapper;
import ru.practicum.shareit.booking.model.Booking;
import ru.practicum.shareit.booking.model.Status;
import ru.practicum.shareit.booking.repository.BookingRepository;
import ru.practicum.shareit.error.NotFoundException;
import ru.practicum.shareit.error.UserNotOwnerException;
import ru.practicum.shareit.error.ValidationException;
import ru.practicum.shareit.item.repository.ItemRepository;
import ru.practicum.shareit.user.model.User;
import ru.practicum.shareit.user.repository.UserRepository;
import ru.practicum.shareit.item.model.Item;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BookingServiceImpl implements BookingService {
    private final BookingRepository bookingRepository;
    private final UserRepository userRepository;
    private final ItemRepository itemRepository;

    @Override
    public BookingDto create(Long userId, BookingDto bookingDto) throws ValidationException {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new NotFoundException("Пользователь с id = {} не найден"));
        Item item = itemRepository.findById(bookingDto.getItemId()).orElseThrow(() -> new NotFoundException("Вещи с id = {} нет."));
        if (!item.getAvailable()) {
            throw new ValidationException("Неправильный статус");
        }
        Booking booking = BookingMapper.toBooking(bookingDto, user, item);
        booking.setStatus(Status.WAITING);
        bookingRepository.save(booking);

        return BookingMapper.toBookingDto(booking);
    }

    @Override
    public BookingDto approval(Long userId, Long bookingId, Boolean approved) {
        Booking booking = bookingRepository.findById(bookingId).orElseThrow(() -> new NotFoundException("бронирование не найдено"));
        User user = userRepository.findById(userId).orElseThrow(() -> new NotFoundException("Пользователь не найден"));
        Item item = booking.getItem();
        if (!item.getOwner().getId().equals(userId)) {
            throw new UserNotOwnerException("Пользователь не является хозяином вещи");
        }
        if (!booking.getStatus().equals(Status.WAITING)) {
            throw new NotFoundException("невозможно изменить статус");
        }
        if (approved) {
            booking.setStatus(Status.APPROVED);
        } else {
            booking.setStatus(Status.REJECTED);
        }
        return BookingMapper.toBookingDto(bookingRepository.save(booking));
    }

    @Override
    public BookingDto getById(Long userId, Long bookingId, Boolean approved) {
        User user = userRepository.findById(userId).orElseThrow(() -> new NotFoundException("Пользователь не найден"));
        Booking booking = bookingRepository.findById(bookingId).orElseThrow(() -> new NotFoundException("бронирование не найдено"));
        Item item = booking.getItem();
        if (!item.getOwner().getId().equals(userId) && !booking.getBooker().getId().equals(userId)) {
            throw new UserNotOwnerException("Пользователь не является хозяином вещи");
        }
        return BookingMapper.toBookingDto(booking);
    }

    @Override
    public Collection<BookingDto> getAllBookingFromUser(Long userId, Status status) {
        List<Booking> bookings;
        userRepository.findById(userId).orElseThrow(() -> new NotFoundException("Пользователь не найден"));
        LocalDateTime time = LocalDateTime.now();
        switch (status) {
            case ALL:
                bookings = bookingRepository.getAllByBookerIdOrderByStartDesc(userId);
                break;
            case CURRENT:
                bookings = bookingRepository.getByBookerIdStateCurrent(userId, time);
                break;
            case PAST:
                bookings = bookingRepository.getByBookerIdStatePast(userId, time);
                break;
            case FUTURE:
                bookings = bookingRepository.getByBookerIdStateFuture(userId, time);
                break;
            case WAITING:
                bookings = bookingRepository.getByBookerIdAndStatus(userId, Status.WAITING);
                break;
            case REJECTED:
                bookings = bookingRepository.getByBookerIdAndStatus(userId, Status.REJECTED);
                break;
            default:
                throw new IllegalArgumentException("Ошибка состояния");
        }
        return bookings.stream().map(BookingMapper::toBookingDto).toList();
    }

    @Override
    public Collection<BookingDto> getAllBookingFromOwner(Long userId, Status status) {
        List<Booking> bookings;
        userRepository.findById(userId).orElseThrow(() -> new NotFoundException("Пользователь не найден"));
        LocalDateTime time = LocalDateTime.now();
        switch (status) {
            case ALL:
                bookings = bookingRepository.getAllByItemOwnerIdOrderByStartDesc(userId);
                break;
            case CURRENT:
                bookings = bookingRepository.getByOwnerIdStateCurrent(userId, time);
                break;
            case PAST:
                bookings = bookingRepository.getByOwnerIdStatePast(userId, time);
                break;
            case FUTURE:
                bookings = bookingRepository.getByOwnerIdStateFuture(userId, time);
                break;
            case WAITING:
                bookings = bookingRepository.getAllByItemOwnerIdAndStatus(userId, Status.WAITING);
                break;
            case REJECTED:
                bookings = bookingRepository.getAllByItemOwnerIdAndStatus(userId, Status.REJECTED);
                break;
            default:
                throw new IllegalArgumentException("Ошибка состояния");
        }
        return bookings.stream().map(BookingMapper::toBookingDto).toList();
    }
}
