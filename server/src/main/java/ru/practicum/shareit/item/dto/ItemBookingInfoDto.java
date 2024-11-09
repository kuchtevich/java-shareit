package ru.practicum.shareit.item.dto;


import lombok.Data;
import java.util.List;
import java.util.ArrayList;
import java.time.LocalDateTime;


@Data
public class ItemBookingInfoDto {
    private Long id;
    private String name;
    private String description;
    private Boolean available;
    private String ownerName;
    private List<CommentDto> comment = new ArrayList<>();
    private LocalDateTime lastBooking;
    private LocalDateTime nextBooking;
}
