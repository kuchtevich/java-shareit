package ru.practicum.shareit.item.dto;
import ru.practicum.shareit.item.model.Item;
/**
 * TODO Sprint add-controllers.
 */
public class ItemDto {
    public static ItemDto toItemDTO(Item item) {
        return new ItemDto(
                item.getName(),
                item.getDescription(),
                item.isAvailable,
                item.getRequest() != null ? item.getRequest().getId() : null
        );
    }
}
