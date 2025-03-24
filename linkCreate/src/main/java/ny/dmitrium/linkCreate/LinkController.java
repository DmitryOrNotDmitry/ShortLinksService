package ny.dmitrium.linkCreate;

import ny.dmitrium.linkCreate.entity.Link;
import ny.dmitrium.linkCreate.entity.LinkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;
import org.springframework.web.server.ServerWebExchange;

@RestController
@RequestMapping("/link")
public class LinkController {

    @Autowired
    private LinkService linkService;

    @PostMapping
    public Mono<ResponseEntity<Link>> createLink(@RequestBody Link longLink) {
        return Mono.justOrEmpty(ResponseEntity.ok(linkService.createByLong(longLink.getLongLink())))
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }
}

