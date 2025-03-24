package ny.dmitrium.gateway;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.server.ResponseStatusException;
import reactor.core.publisher.Mono;

import java.net.URI;

@RestController
public class RedirectController {

    @Autowired
    private WebClient.Builder webClientBuilder;

    @GetMapping("/to/{hash}")
    public Mono<ResponseEntity<Object>> redirectTo(@PathVariable(name = "hash") String hash) {
        Link linkWithHash = new Link();
        linkWithHash.setHash(hash);

        return webClientBuilder.build()
                .post()
                .uri("http://localhost:8082/to")
                .bodyValue(linkWithHash)
                .retrieve()
                .bodyToMono(Link.class)
                .map(link -> ResponseEntity
                        .status(HttpStatus.FOUND)
                        .location(URI.create(link.getLongLink()))
                        .build())
                .switchIfEmpty(Mono.error(new ResponseStatusException(HttpStatus.NOT_FOUND)));
    }

}
