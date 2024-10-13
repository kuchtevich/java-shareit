package ru.practicum.shareit.request;

import java.time.LocalDate;

/**
 * TODO Sprint add-item-requests.
 */
public class ItemRequest {
    private long id;
    private  String  description;
    private String requestor; //пользователь создавший запрос
    private LocalDate created;
}
