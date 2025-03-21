package ny.dmitrium.linkCreate;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

@RestController
@RequestMapping("/link")
public class LinkController {

    private MessageDigest messageDigest;

    private String hashMD5(String longLink) {

        if (messageDigest == null) {
            try {
                messageDigest = MessageDigest.getInstance("MD5");
            } catch (NoSuchAlgorithmException e) {
                e.printStackTrace();
                return null;
            }
        }

        byte[] hashBytes = messageDigest.digest(longLink.getBytes());

        StringBuilder hexString = new StringBuilder();
        for (byte b : hashBytes) {
            hexString.append(String.format("%02x", b));
        }

        return hexString.toString();
    }

    @PostMapping()
    public Mono<ResponseEntity<Link>> createLink(@RequestBody String longLink) {
        return Mono.justOrEmpty(ResponseEntity.ok(new Link(hashMD5(longLink), longLink)))
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }
}

