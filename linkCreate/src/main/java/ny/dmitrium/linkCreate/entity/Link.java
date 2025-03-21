package ny.dmitrium.linkCreate.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Data
@Entity
@Table(name = "link")
public class Link {

    @Id
    @Column(name = "hash", nullable = false)
    private String hash;

    @Column(name = "long", nullable = false)
    private String longLink;

    @Transient
    private String shortLink;

    @Column(name = "created_at")
    private LocalDate createdAt;

    public Link() {
    }

    public Link(String hash, String longLink) {
        this.hash = hash;
        this.longLink = longLink;
    }

}

