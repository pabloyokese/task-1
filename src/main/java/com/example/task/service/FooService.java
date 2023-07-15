package com.example.task.service;

import com.example.task.dto.Foo;
import com.github.javafaker.Faker;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

import java.time.Duration;
import java.util.UUID;
import java.util.stream.Stream;

@Service
public class FooService {

    private final Faker faker;

    public FooService(Faker faker) {
        this.faker = faker;
    }

    public Flux<Foo> streamFoo() {
        Flux<Long> interval = Flux.interval(Duration.ofSeconds(1));
        Flux<Foo> events = Flux.fromStream(Stream.generate(() -> new Foo(UUID.randomUUID().toString(), faker.name().firstName())));
        return Flux.zip(events, interval, (key, value) -> key);
    }
}
