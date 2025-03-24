package ny.dmitrium.redirect.entity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class LinkService {

    @Autowired
    private LinkRepository linkRepository;

    public Link searchByShort(Link shortLink) {
        return linkRepository.findById(shortLink.getHash()).orElse(null);

    }

}
