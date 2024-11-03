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

    Optional<Booking> findById(Long itemId);

    List<Booking> getAllByBookerIdOrderByStartDesc(Long userId);

    List<Booking> findByItemIdInAndEndAfter(List<Long> itemIds, LocalDateTime time);

    @Query("SELECT b FROM Booking b " +
            "WHERE b.item.id = :itemId AND " +
            "((b.start <= :end) AND (b.end >= :start))")
    List<Booking> findByItemIdAndIntersection(Long itemId, LocalDateTime start, LocalDateTime end);

    List<Booking> findAllByItemIdAndEndBefore(Long itemId, LocalDateTime time);

    List<Booking> getAllByItemOwnerIdOrderByStartDesc(Long userId);

    List<Booking> getAllByItemOwnerIdAndStatus(Long ownerId, Status status);

    List<Booking> getByBookerIdAndStatus(Long bookerId, Status status);

    @Query("SELECT b FROM Booking b " +
            "WHERE b.booker.id = :id AND b.end < :time AND upper(b.status) = UPPER('APPROVED')" +
            "ORDER BY b.start DESC")
    List<Booking> getByBookerIdStatePast(Long id, LocalDateTime time);

    @Query("SELECT b FROM Booking b WHERE b.booker.id = :userId AND b.end >= :time AND :time >= b.start " +
            "ORDER BY b.start DESC")
    List<Booking> getByBookerIdStateCurrent(Long userId, LocalDateTime time);

    @Query("SELECT b FROM Booking b WHERE b.booker.id = :userId AND b.start > :time ORDER BY b.start DESC")
    List<Booking> getByBookerIdStateFuture(Long userId, LocalDateTime time);

    @Query("SELECT b FROM Booking b JOIN b.item i ON b.item = i WHERE  i.owner.id = :userId AND b.start > :time " +
            "ORDER BY b.start DESC")
    List<Booking> getByOwnerIdStateFuture(Long userId, LocalDateTime time);

    @Query("SELECT b FROM Booking b JOIN b.item i ON b.item = i WHERE i.owner.id = :userId " +
            "AND b.start <= :time AND b.end >= :time ORDER BY b.start DESC ")
    List<Booking> getByOwnerIdStateCurrent(Long userId, LocalDateTime time);

    @Query("SELECT b FROM Booking b JOIN b.item i ON b.item = i WHERE i.owner.id = :userId AND b.end < :time")
    List<Booking> getByOwnerIdStatePast(Long userId, LocalDateTime time);

    @Query("SELECT b FROM Booking b JOIN b.item i ON b.item = i WHERE b.id = :bookingId AND i.owner.id = :userId ")
    Optional<Booking> findByIdAndOwnerId(final long bookingId, final long userId);

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

    List<Booking> findAllByItemOwnerIdAndStatusIs(final long userId, final BookingStatus status, Sort sort);

    List<Booking> findAllByBookerIdAndItemIdAndStatusEqualsAndEndIsBefore(final long userId,
                                                                          final long itemId, final BookingStatus status,
                                                                          final LocalDateTime time);

    Optional<Booking> findTopByItemIdAndEndBeforeAndStatusInOrderByEndDesc(final long itemId,
                                                                           final LocalDateTime time,
                                                                           final List<BookingStatus> status);

    Optional<Booking> findTopByItemIdAndStartAfterAndStatusInOrderByStartAsc(final long itemId,
                                                                             final LocalDateTime time,
                                                                             final List<BookingStatus> status);
}
