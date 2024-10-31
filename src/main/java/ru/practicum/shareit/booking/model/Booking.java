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
    @Id
    private long id;
    @Column(name = "start")
    private LocalDateTime start;
    @Column(name = "end")
    private LocalDateTime end;
    @Column(name = "item")
    private Item item; //вещь которую бронируют
    @Column(name = "booker")
    private User booker; //пользователь который забронировал
    @Enumerated(EnumType.STRING)
    @Column(name = "booker")
    private Status status; //WAITING, APPROVED, REJECTED, CANCELED
}
