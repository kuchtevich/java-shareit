package ru.practicum.shareit.booking.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.annotation.Id;
import ru.practicum.shareit.user.model.User;
import ru.practicum.shareit.item.model.Item;
import jdk.jshell.Snippet;

import java.time.LocalDate;

@Data
@Entity
@Table(name = "bookings")
@EqualsAndHashCode(exclude = {"id"})
public class Booking {
    @NotNull(message = "Указать id.")
    @Id
    private long id;
    @Column(name = "start")
    private LocalDate start;
    @Column(name = "end")
    private LocalDate end;
    @Column(name = "item")
    private Item item; //вещь которую бронируют
    @Column(name = "booker")
    private User booker; //пользователь который забронировал
    @Enumerated(EnumType.STRING)
    @Column(name = "booker")
    private Snippet.Status status; //WAITING, APPROVED, REJECTED, CANCELED
}
