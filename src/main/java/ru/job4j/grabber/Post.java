package ru.job4j.grabber;

import java.time.LocalDateTime;

public class Post {
    private int id;
    private String header;
    private String description;
    private String link;
    private LocalDateTime created;

    public Post() {
    }

    public Post(int id, String header, String description, String link, LocalDateTime created) {
        this.id = id;
        this.header = header;
        this.description = description;
        this.link = link;
        this.created = created;
    }

    public Post(String header, String description, String link, LocalDateTime created) {
        this.header = header;
        this.description = description;
        this.link = link;
        this.created = created;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
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
                + ", header='" + header + '\''
                + ", description='" + description + '\''
                + ", link='" + link + '\''
                + ", created=" + created
                + '}';
    }
}
