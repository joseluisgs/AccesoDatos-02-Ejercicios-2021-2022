package com.madirex.jdom.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Post {
    private String pubDate;
    private String title;
    private String description;
    private String link;
    private String author;

    @Override
    public String toString() {
        return "\nPost:\n" +
                "   publicación=" + pubDate + "\n" +
                "   título=" + title + "\n" +
                "   descripción=" + description + "\n" +
                "   enlace=" + link + "\n" +
                "   autor=" + author + "\n";
    }
}