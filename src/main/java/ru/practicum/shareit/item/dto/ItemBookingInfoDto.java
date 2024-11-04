package ru.practicum.shareit.item.dto;

import lombok.Data;
import ru.practicum.shareit.booking.model.Booking;

import java.util.List;
import java.util.ArrayList;


@Data
public class ItemBookingInfoDto {
    private Long id;
    private String name;
    private String description;
    private Boolean available;
    private String ownerName;
    private List<CommentDto> comments = new ArrayList<>();
    private Booking lastBooking;
    private Booking nextBooking;
}
