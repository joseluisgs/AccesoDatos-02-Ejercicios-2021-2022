package sax.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    private String pubDate;
    private String title;
    private String description;
    private String link;
    private String author;
    private String category;

    @Override
    public String toString() {
        return "\nPost:\n" +
                "   pubDate: " + pubDate + "\n" +
                "   title: " + title + "\n" +
                "   description: " + description + "\n" +
                "   category: "+ category + "\n"+
                "   link: " + link + "\n" +
                "   author: " + author + "\n";
    }
}
