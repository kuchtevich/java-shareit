package src.main.java.ru.practicum.shareit.request.client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.util.DefaultUriBuilderFactory;
import src.main.java.ru.practicum.shareit.client.BaseClient;
import src.main.java.ru.practicum.shareit.request.dto.ItemRequestDto;


import static org.springframework.http.RequestEntity.post;

@Service
public class ClientRequest extends BaseClient {
    private static final String API_PREFIX = "/requests";

    @Autowired
    public ClientRequest(@Value("${shareit-server.url}") String serverUrl, RestTemplateBuilder builder) {
        super(
                builder
                        .uriTemplateHandler(new DefaultUriBuilderFactory(serverUrl + API_PREFIX))
                        .requestFactory(() -> new HttpComponentsClientHttpRequestFactory())
                        .build()
        );
    }

    public ResponseEntity<Object> itemRequestCreate(final long userId, final ItemRequestDto itemRequestDto) {
        return post("", userId, itemRequestDto);
    }

    public ResponseEntity<Object> getAllRequestByUser(final long userId) {
        return get("", userId);
    }

    public ResponseEntity<Object> getAllRequests(final long userId) {
        return get("/all", userId);
    }

    public ResponseEntity<Object> getRequestById(final long requestId) {
        return get("/" + requestId);
    }
}
