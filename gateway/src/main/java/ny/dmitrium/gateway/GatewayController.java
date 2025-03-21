package ny.dmitrium.gateway;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api")
public class GatewayController {
    private final WebClient.Builder webClientBuilder;

    public GatewayController(WebClient.Builder webClientBuilder) {
        this.webClientBuilder = webClientBuilder;
    }

    @PostMapping("/link")
    public Mono<ResponseEntity<Link>> createLink(@RequestBody String longLink) {
        return webClientBuilder.build()
                .post()
                .uri("http://localhost:8081/link")
                .bodyValue(longLink)
                .retrieve()
                .bodyToMono(Link.class)
                .map(ResponseEntity::ok)
                .onErrorResume(e -> Mono.just(ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE)
                        .body(new Link("Unknown", "Service unavailable"))));
    }
}

