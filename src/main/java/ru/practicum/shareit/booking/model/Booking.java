package ru.practicum.shareit.booking.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import ru.practicum.shareit.user.model.User;
import ru.practicum.shareit.item.model.Item;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "bookings")
@EqualsAndHashCode(exclude = {"id"})
public class Booking {
    @Column(name = "booking_id")
    @Id
    private long id;
    @Column(name = "booking_start")
    private LocalDateTime start;
    @Column(name = "booking_end")
    private LocalDateTime end;
    @Column(name = "item_id")
    private Item item; //вещь которую бронируют
    @Column(name = "booker_id")
    private User booker; //пользователь который забронировал
    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private Status status; //WAITING, APPROVED, REJECTED, CANCELED
}
