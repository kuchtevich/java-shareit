package ru.practicum.shareit.item.repository;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.practicum.shareit.item.model.Item;

import java.util.List;

public interface ItemRepository extends JpaRepository<Item, Long> {
    @EntityGraph(type = EntityGraph.EntityGraphType.FETCH, value = "Item.owner")
    List<Item> findAllByOwnerId(Long userId);

    @Query("""
            SELECT i
            FROM Item i
            WHERE i.available = true
                AND (LOWER(i.name) LIKE LOWER(CONCAT('%', :text, '%'))
                OR LOWER(i.description) LIKE LOWER(CONCAT('%', :text, '%')))
            """)
    List<Item> search(String text);
}
