package ru.job4j.grabber;

import java.time.LocalDateTime;

public class Post {
    private int id;
    private String link;
    private String header;
    private String description;
    private LocalDateTime created;

    public Post(int id, String link, String header, String description, LocalDateTime created) {
        this.id = id;
        this.link = link;
        this.header = header;
        this.description = description;
        this.created = created;
    }

    public Post(String link, String header, String description, LocalDateTime created) {
        this.link = link;
        this.header = header;
        this.description = description;
        this.created = created;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getHeader() {
        return header;
    }

    public void setHeader(String header) {
        this.header = header;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDateTime getCreated() {
        return created;
    }

    public void setCreated(LocalDateTime created) {
        this.created = created;
    }

    @Override
    public String toString() {
        return "Post{"
                + "id=" + id
                + ", link='" + link + '\''
                + ", header='" + header + '\''
                + ", description='" + description + '\''
                + ", created=" + created
                + '}';
    }
}
