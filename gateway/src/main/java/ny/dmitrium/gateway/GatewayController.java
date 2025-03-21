package ny.dmitrium.gateway;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api")
public class GatewayController {

    private final WebClient.Builder webClientBuilder;

    public GatewayController(WebClient.Builder webClientBuilder) {
        this.webClientBuilder = webClientBuilder;
    }

    private String getBaseUrl(ServerWebExchange exchange) {
        String scheme = exchange.getRequest().getURI().getScheme();
        String host = exchange.getRequest().getURI().getHost();
        int port = exchange.getRequest().getURI().getPort();

        String baseUrl = scheme + "://" + host;

        if ((scheme.equals("http") && port != 80) || (scheme.equals("https") && port != 443)) {
            baseUrl += ":" + port;
        }

        return baseUrl;
    }

    private final String BASE_URL = "http://localhost:8080/to/";

    @PostMapping("/link")
    public Mono<ResponseEntity<Link>> createLink(@RequestBody Link longLink) {
        return webClientBuilder.build()
                .post()
                .uri("http://localhost:8081/link")
                .bodyValue(longLink)
                .retrieve()
                .bodyToMono(Link.class)
                .map(link -> {
                    link.setShortLink(BASE_URL);
                    return ResponseEntity.ok(link);
                })
                .onErrorResume(e -> Mono.just(ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE)
                        .body(new Link("Unknown", "Service unavailable"))));
    }
}

