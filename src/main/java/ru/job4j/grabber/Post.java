package ru.job4j.grabber;

import java.time.LocalDateTime;

public class Post {
    private int id;
    private String link;
    private String header;
    private String description;
    private LocalDateTime date;

    public Post(int id, String link, String header, String description, LocalDateTime date) {
        this.id = id;
        this.link = link;
        this.header = header;
        this.description = description;
        this.date = date;
    }

    public Post(String link, String header, String description, LocalDateTime date) {
        this.link = link;
        this.header = header;
        this.description = description;
        this.date = date;
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

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "Post{"
                + "id=" + id
                + ", link='" + link + '\''
                + ", header='" + header + '\''
                + ", description='" + description + '\''
                + ", date=" + date
                + '}';
    }
}
