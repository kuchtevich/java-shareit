package ru.practicum.shareit.item.client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.util.DefaultUriBuilderFactory;
import ru.practicum.shareit.client.BaseClient;
import ru.practicum.shareit.item.dto.CommentDtoItem;
import ru.practicum.shareit.item.dto.ItemsDto;
import java.util.Map;

@Service
public class ClientItem extends BaseClient {
    private static final String API_PREFIX = "/items";

    @Autowired
    public ClientItem(@Value("${shareit-server.url}") String serverUrl, RestTemplateBuilder builder) {
        super(
                builder
                        .uriTemplateHandler(new DefaultUriBuilderFactory(serverUrl + API_PREFIX))
                        .requestFactory(() -> new HttpComponentsClientHttpRequestFactory())
                        .build()
        );
    }

    public ResponseEntity<Object> create(final long userId, final ItemsDto itemsDto) {
        return post("", userId, itemsDto);
    }

    public ResponseEntity<Object> update(final long userId, final long itemId, final ItemsDto itemsDto) {
        return patch("/" + userId, itemId, itemsDto);
    }

    public ResponseEntity<Object> getItemById(final long userId, final long itemId) {
        return get("/" + itemId, userId);
    }

    public void itemDelete(final long itemId) {
        delete("/" + itemId);
    }

    public ResponseEntity<Object> getAllItems(final long userId) {
        return get("", userId);
    }

    public ResponseEntity<Object> search(final long userId, final String text) {
        Map<String, Object> parameters = Map.of("text", text);
        return get("/search?text={text}", userId, parameters);
    }

    public ResponseEntity<Object> addComments(final long userId, final long itemId, final CommentDtoItem commentDtoItem) {
        return post("/" + itemId + "/comment", userId, commentDtoItem);
    }
}
