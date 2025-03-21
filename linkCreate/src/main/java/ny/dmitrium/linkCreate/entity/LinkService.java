package ny.dmitrium.linkCreate.entity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.http.server.reactive.ServerHttpRequest;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

@Service
public class LinkService {

    @Autowired
    private LinkRepository linkRepository;

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

    public Link createByLong(String longLink) {
        Link link = new Link(hashMD5(longLink), longLink);
        linkRepository.save(link);

        return link;
    }

}
