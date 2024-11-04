package ru.practicum.shareit.booking.repository;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.practicum.shareit.booking.model.Booking;
import ru.practicum.shareit.booking.model.Status;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface BookingRepository extends JpaRepository<Booking, Long> {

    @Query("SELECT b FROM Booking b " +
            "WHERE b.item.id = :itemId AND " +
            "((b.start <= :end) AND (b.end >= :start))")
    List<Booking> findByItemIdAndIntersection(Long itemId, LocalDateTime start, LocalDateTime end);

    List<Booking> findAllByItemIdAndEndBefore(Long itemId, LocalDateTime time);

    List<Booking> getAllByItemOwnerIdOrderByStartDesc(Long userId);

    List<Booking> getAllByItemOwnerIdAndStatus(Long ownerId, Status status);

    List<Booking> getByBookerIdAndStatus(Long bookerId, Status status);

    List<Booking> findAllByBookerId(final long userId, Sort sort);

    List<Booking> findAllByBookerIdAndStartBeforeAndEndAfter(final long userId, final LocalDateTime time1,
                                                             final LocalDateTime time2, Sort sort);

    List<Booking> findAllByBookerIdAndEndBefore(final long userId, final LocalDateTime time, Sort sort);

    List<Booking> findAllByBookerIdAndStartAfter(final long userId, final LocalDateTime time, Sort sort);

    List<Booking> findAllByBookerIdAndStatusIs(final long userId, final Status status, Sort sort);

    List<Booking> findAllByItemOwnerId(final long userId, Sort sort);

    List<Booking> findAllByItemOwnerIdAndStartBeforeAndEndAfter(final long userId, final LocalDateTime time1,
                                                                final LocalDateTime time2, Sort sort);

    List<Booking> findAllByItemOwnerIdAndEndBefore(final long userId, final LocalDateTime time, Sort sort);

    List<Booking> findAllByItemOwnerIdAndStartAfter(final long userId, final LocalDateTime time, Sort sort);

    List<Booking> findAllByItemOwnerIdAndStatusIs(final long userId, final Status status, Sort sort);

    List<Booking> findAllByBookerIdAndItemIdAndStatusEqualsAndEndIsBefore(final long userId,
                                                                          final long itemId, final Status status,
                                                                          final LocalDateTime time);

    Optional<Booking> findTopByItemIdAndEndBeforeAndStatusInOrderByEndDesc(final long itemId,
                                                                           final LocalDateTime time,
                                                                           final List<Status> status);

    Optional<Booking> findTopByItemIdAndStartAfterAndStatusInOrderByStartAsc(final long itemId,
                                                                             final LocalDateTime time,
                                                                             final List<Status> status);
}
