package com.sarvan.ordermanagement.router;

        import com.sarvan.ordermanagement.handlers.TestHandler;
        import org.springframework.context.annotation.Bean;
        import org.springframework.context.annotation.Configuration;
        import org.springframework.http.MediaType;
        import org.springframework.web.reactive.function.server.RouterFunction;
        import org.springframework.web.reactive.function.server.RouterFunctions;
        import org.springframework.web.reactive.function.server.ServerResponse;

        import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
        import static org.springframework.web.reactive.function.server.RequestPredicates.accept;

@Configuration(proxyBeanMethods = false)
public class Router {

    @Bean
    public RouterFunction<ServerResponse> route(TestHandler testHandler) {

        return RouterFunctions
                .route(GET("/hello").and(accept(MediaType.APPLICATION_JSON)), testHandler::hello)
                .andRoute(GET("/test").and(accept(MediaType.APPLICATION_JSON)), testHandler::test);
    }
}
