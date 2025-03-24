package ny.dmitrium.redirect;

import ny.dmitrium.redirect.entity.Link;
import ny.dmitrium.redirect.entity.LinkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/to")
public class RedirectController {

    @Autowired
    private LinkService linkService;

    @PostMapping
    public Mono<ResponseEntity<Link>> redirectTo(@RequestBody Link shortLink) {
        return Mono.justOrEmpty(ResponseEntity.ok(linkService.searchByShort(shortLink)))
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

}
