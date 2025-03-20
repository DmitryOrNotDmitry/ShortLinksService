package ny.dmitrium.linkCreate;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/link")
public class LinkController {

    @GetMapping()
    public Mono<ResponseEntity<Link>> getLink() {
        return Mono.justOrEmpty(ResponseEntity.ok(new Link("shortLink", "real long link")))
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }
}

