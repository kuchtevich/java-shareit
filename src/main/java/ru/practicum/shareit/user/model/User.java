package ru.practicum.shareit.user.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;


@Data
@Entity
@Table(name = "users")
@EqualsAndHashCode(exclude = {"id"})
@AllArgsConstructor
public class User {
    @Id
    private Long id;
    @Column(name = "name")
    private String name;
    @Column(name = "email", unique = true)
    private String email;
}
