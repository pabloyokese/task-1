package com.example.task.handler;

import com.example.task.service.FooService;
import com.example.task.dto.Foo;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@Component
public class RequestHandler {

    private final FooService fooService;

    public RequestHandler(FooService fooService) {
        this.fooService = fooService;
    }

    public Mono<ServerResponse> streamFoo(ServerRequest request) {
        return ServerResponse.ok()
                .contentType(MediaType.TEXT_EVENT_STREAM)
                .body(fooService.streamFoo(), Foo.class);
    }
}
