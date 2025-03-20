package ny.dmitrium.linkCreate;

import lombok.Data;

@Data
public class Link {

    private String shortLink;
    private String longLink;

    public Link(String shortLink, String longLink) {
        this.shortLink = shortLink;
        this.longLink = longLink;
    }
}
