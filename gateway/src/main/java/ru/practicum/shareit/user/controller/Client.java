package ru.practicum.shareit.user.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.util.DefaultUriBuilderFactory;

import static org.springframework.http.RequestEntity.post;

import ru.practicum.shareit.client.BaseClient;
import ru.practicum.shareit.user.dto.UsersDto;

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

    public ResponseEntity<Object> create(final UsersDto usersDto) {
        return post("", usersDto);
    }

    public ResponseEntity<Object> update(final long userId, final UsersDto usersDto) {
        return patch("/" + userId, usersDto);
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
