package ru.practicum.shareit.item.dto;

import lombok.Data;
import ru.practicum.shareit.booking.dto.BookingDtoTime;


@Data
public class ItemDto {
        private Long id;
        private String name;
        private String description;
        private Boolean available;
        private Long requestId;
        private BookingDtoTime lastBooking;
        private BookingDtoTime nextBooking;
}
