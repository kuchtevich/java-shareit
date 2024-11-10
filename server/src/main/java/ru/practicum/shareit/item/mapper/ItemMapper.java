package ru.practicum.shareit.item.mapper;

import org.springframework.stereotype.Component;
import ru.practicum.shareit.item.dto.CommentDto;
import ru.practicum.shareit.item.dto.ItemBookingInfoDto;
import ru.practicum.shareit.item.dto.ItemDtoRequest;
import ru.practicum.shareit.item.model.Item;
import ru.practicum.shareit.item.dto.ItemDto;
import ru.practicum.shareit.user.model.User;

import java.util.List;
import java.util.ArrayList;
import java.util.Objects;

@Component
public class ItemMapper {
    public ItemDto toItemDto(final Item item) {
        ItemDto itemDto = new ItemDto();

        itemDto.setId(item.getId());
        itemDto.setName(item.getName());
        itemDto.setDescription(item.getDescription());
        itemDto.setAvailable(item.getAvailable());
        if (Objects.nonNull(item.getRequest())) {
            itemDto.setRequestId(item.getRequest().getId());
        }

        return itemDto;
    }

    public Item toItem(final User user, final ItemDto itemDto) {
        Item item = new Item();

        item.setName(itemDto.getName());
        item.setDescription(itemDto.getDescription());
        item.setOwner(user);
        item.setAvailable(itemDto.getAvailable());


        return item;
    }

    public List<ItemDto> toListDto(Iterable<Item> items) {
        List<ItemDto> result = new ArrayList<>();

        for (Item item : items) {
            result.add(toItemDto(item));
        }

        return result;
    }

    public ItemBookingInfoDto toItemBookingInfoDto(final Item item, List<CommentDto> comments) {

        final ItemBookingInfoDto itemBookingInfoDto = new ItemBookingInfoDto();

        itemBookingInfoDto.setId(item.getId());
        itemBookingInfoDto.setName(item.getName());
        itemBookingInfoDto.setDescription(item.getDescription());
        itemBookingInfoDto.setAvailable(item.getAvailable());
        itemBookingInfoDto.setComments(comments);

        return itemBookingInfoDto;
    }

    public ItemDtoRequest toItemDtoRequest(Item item) {

        final ItemDtoRequest itemDtoRequest = new ItemDtoRequest();

        itemDtoRequest.setItemId(item.getId());
        itemDtoRequest.setOwnerId(item.getOwner().getId());
        itemDtoRequest.setName(item.getName());

        return itemDtoRequest;

    }
}
