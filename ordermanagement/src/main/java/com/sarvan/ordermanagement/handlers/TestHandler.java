package com.sarvan.ordermanagement.handlers;





        import com.sarvan.ordermanagement.models.Message;
        import org.springframework.http.MediaType;
        import org.springframework.stereotype.Component;
        import org.springframework.web.reactive.function.BodyInserters;
        import org.springframework.web.reactive.function.server.ServerRequest;
        import org.springframework.web.reactive.function.server.ServerResponse;

        import reactor.core.publisher.Mono;

@Component
public class TestHandler {

    public Mono<ServerResponse> hello(ServerRequest request) {
        return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromValue("{messge: \"hello\"}"));
    }
    public Mono<ServerResponse> test(ServerRequest request) {
        return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromValue("{messge: \"test\"}"));
    }
}
