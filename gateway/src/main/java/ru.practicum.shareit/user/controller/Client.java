package src.main.java.ru.practicum.shareit.user.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.util.DefaultUriBuilderFactory;

import static org.springframework.http.RequestEntity.post;

import src.main.java.ru.practicum.shareit.client.BaseClient;
import src.main.java.ru.practicum.shareit.user.dto.UserDto;

@Service
public class Client extends BaseClient {
    private static final String API_PREFIX = "/users";

    @Autowired
    public Client(@Value("${shareit-server.url}") String serverUrl, RestTemplateBuilder builder) {
        super(
                builder
                        .uriTemplateHandler(new DefaultUriBuilderFactory(serverUrl + API_PREFIX))
                        .requestFactory(() -> new HttpComponentsClientHttpRequestFactory())
                        .build()
        );
    }

    public ResponseEntity<Object> create(final UserDto userDto) {
        return post("", userDto);
    }

    public ResponseEntity<Object> update(final long userId, final UserDto userDto) {
        return patch("/" + userId, userDto);
    }

    public ResponseEntity<Object> getUserById(final long userId) {
        return get("/" + userId);
    }

    public ResponseEntity<Object> getAllUsers() {
        return get("");
    }

    public void delete(final long userId) {
        delete("/" + userId);
    }
}
