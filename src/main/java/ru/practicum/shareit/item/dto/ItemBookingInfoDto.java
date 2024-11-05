package ru.practicum.shareit.item.dto;


import lombok.Getter;
import lombok.Setter;
import java.util.List;
import java.util.ArrayList;
import java.time.LocalDateTime;


@Setter
@Getter
public class ItemBookingInfoDto {
    private Long id;
    private String name;
    private String description;
    private Boolean available;
    private String ownerName;
    private List<CommentDto> comments = new ArrayList<>();
    private LocalDateTime lastBooking;
    private LocalDateTime nextBooking;
}
