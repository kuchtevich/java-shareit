package ru.practicum.shareit.booking.service;

import lombok.RequiredArgsConstructor;
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
    public BookingDtoTime create(Long userId, BookingDto bookingDto) throws ValidationException {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new NotFoundException("Пользователь с id = {} не найден"));
        Item item = itemRepository.findById(bookingDto.getItemId()).orElseThrow(() -> new NotFoundException("Вещи с id = {} нет."));
        if (!item.getAvailable()) {
            throw new ValidationException("Неправильный статус");
        }
        Booking booking = BookingMapper.toBooking(bookingDto, user, item);
        booking.setStatus(Status.WAITING);
        bookingRepository.save(booking);

        return BookingMapper.toBookingDtoTime(booking, userMapper.toUserDto(user), itemMapper.toItemDto(item));
    }

    @Override
    public BookingDtoTime approval(final Long userId, final Long bookingId, final Boolean approved) {
        Booking booking = bookingRepository.findByIdAndOwnerId(bookingId, userId).orElseThrow(() -> new NotFoundException("бронирование не найдено"));
        if (!booking.getStatus().equals(Status.WAITING)) {
            throw new NotFoundException("невозможно изменить статус");
        }
        booking.setStatus(approved ? Status.APPROVED : Status.REJECTED);
        final Booking updateBooking = bookingRepository.save(booking);

        return bookingMapper.toBookingDtoTime(updateBooking,
                userMapper.toUserDto(booking.getBooker()), itemMapper.toItemDto(booking.getItem()));
    }

    @Override
    public BookingDto getById(final Long userId, Long bookingId) {
        Booking booking = bookingRepository.findById(bookingId).orElseThrow(() -> new NotFoundException("бронирование не найдено"));
        if (!Objects.equals(booking.getItem().getOwner().getId(), userId)
                && !Objects.equals(booking.getBooker().getId(), userId)) {
            throw new UserNotOwnerException("Пользователь не является хозяином вещи");
        }
        return BookingMapper.toBookingDtoTime(booking,
                userMapper.toUserDto(booking.getBooker()), itemMapper.toItemDto(booking.getItem()));
    }

    @Override
    public Collection<BookingDto> getAllBookingFromUser(final Long userId, final Status status) {
        List<Booking> bookings;
        userRepository.findById(userId).orElseThrow(() -> new NotFoundException("Пользователь не найден"));
        LocalDateTime time = LocalDateTime.now();
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
        return bookings.stream().map(booking -> bookingMapper.toBookingDtoTime(booking,
                userMapper.toUserDto(booking.getBooker()), itemMapper.toItemDto(booking.getItem())))
                .collect(Collectors.toList());
    }

    @Override
    public Collection<BookingDtoTime> getAllBookingFromOwner(final Long userId, final Status status) {
        userRepository.findById(userId).orElseThrow(() -> new NotFoundException("Пользователь не найден"));
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
                bookings = bookingRepository.findAllByItemOwnerIdAndStartBeforeAndEndAfter(userId, time, time, sory);
                break;
            case PAST:
                bookings = bookingRepository.findAllByItemOwnerIdAndEndBefore(userId, time, sort);
                break;
            case FUTURE:
                bookings = bookingRepository.findAllByItemOwnerIdAndStartAfter(userId, time, sort);
                break;
            case WAITING:
                bookings = bookingRepository.gfindAllByItemOwnerIdAndStatusIs(userId, Status.WAITING, sort);
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
}
