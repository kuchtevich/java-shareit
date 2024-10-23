package ru.practicum.shareit.item.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

import java.time.LocalDate;

@Data
@Table(name = "comments")
@Entity
public class Comment {
    @Id
    private Long id;
    @Column(name = "text")
    private String text;
    @Column(name = "Item_id")
    private Long item_id;
    @Column(name = "author_id")
    private Long author_id;
    @Column(name = "created")
    private LocalDate created;
}
