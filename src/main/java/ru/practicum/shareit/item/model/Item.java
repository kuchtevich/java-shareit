package ru.practicum.shareit.item.model;

import lombok.Data;

import java.net.http.HttpHeaders;


@Data
public class Item {
    private long id;
    private String name;
    private String description; //подробное описание вещи
    private String available; //статус
    private HttpHeaders request; //ссылка на соотв. запрос

}
