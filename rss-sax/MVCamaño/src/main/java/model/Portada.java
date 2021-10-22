package model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class Portada {

    private String pubDate;
    private String title;
    private String description;
    private String link;
    private String author;
    private String category;

    @Override
    public String toString() {
        return "Portada{" +
                "pubDate='" + pubDate + "\n" +
                " title='" + title + "\n" +
                "description='" + description + "\n" +
                " link='" + link + "\n" +
                "author='" + author + "\n" +
                " category='" + category + "\n" +
                '}';
    }
}
