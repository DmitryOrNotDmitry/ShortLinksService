package ny.dmitrium.gateway.toservices;

import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import ny.dmitrium.gateway.Link;
import ny.dmitrium.gateway.config.ServicesConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@RestController
public class LinkCreateController {

    private final String BASE_URL = "http://localhost:8085/to/";

    private final WebClient.Builder webClientBuilder;

    private final ServicesConfig servicesConfig;

    public LinkCreateController(WebClient.Builder webClientBuilder, ServicesConfig servicesConfig) {
        this.webClientBuilder = webClientBuilder;
        this.servicesConfig = servicesConfig;
    }

    @Autowired
    private DiscoveryClient discoveryClient;

    @PostMapping("/link")
    public Mono<ResponseEntity<Link>> createLink(@RequestBody Link longLink) {
        return webClientBuilder.build()
                .post()
                .uri(servicesConfig.getLinkcreate() + "/link")
                .bodyValue(longLink)
                .retrieve()
                .bodyToMono(Link.class)
                .map(link -> {
                    link.setShortLink(BASE_URL);
                    return ResponseEntity.ok(link);
                })
                .onErrorResume(e -> {
                    e.printStackTrace();
                    return Mono.just(ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE)
                            .body(new Link("Unknown", "Service unavailable")));
                });
    }
}

