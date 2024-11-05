package ru.practicum.shareit.booking.model;

import jakarta.persistence.*;
import lombok.Data;
import ru.practicum.shareit.user.model.User;
import ru.practicum.shareit.item.model.Item;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "bookings")
public class Booking {
    @Id
    @Column(name = "booking_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "booking_start", nullable = false)
    private LocalDateTime start;
    @Column(name = "booking_end", nullable = false)
    private LocalDateTime end;
    @JoinColumn(name = "item_id", nullable = false)
    @ManyToOne(fetch = FetchType.LAZY)
    private Item item; //вещь которую бронируют
    @JoinColumn(name = "booker_id", nullable = false)
    @ManyToOne(fetch = FetchType.LAZY)
    private User booker; //пользователь который забронировал
    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private Status status; //WAITING, APPROVED, REJECTED, CANCELED
}