package jdom.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class News {
    private String pubDate;
    private String title;
    private String description;
    private String link;
    private String author;


    @Override
    public String toString() {
        return "\njdom.model.Post:\n" +
                "   publication: " + pubDate + "\n" +
                "   title: " + title + "\n" +
                "   description: " + description + "\n" +
                "   link: " + link + "\n" +
                "   author: " + author + "\n" ;

    }
}
