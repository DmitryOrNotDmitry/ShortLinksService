package ny.dmitrium.gateway;

import lombok.Data;

import java.time.LocalDate;


@Data
public class Link {

    private String hash;
    private String longLink;
    private String shortLink;
    private LocalDate createdAt;

    public Link() {
    }

    public Link(String hash, String longLink) {
        this.hash = hash;
        this.longLink = longLink;
    }

    public void setShortLink(String prefix) {
        this.shortLink = prefix + hash;
    }
}
