package rss.ejercicio;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Elementos {
    private String title;
    private String description;
    private String link;
    private String lastBuildDate;

    @Override
    public String toString() {
        return "Elementos{" +
                "title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", link='" + link + '\'' +
                ", lastBuildDate='" + lastBuildDate + '\'' +
                ", category='" + category + '\'' +
                ", guid='" + guid + '\'' +
                '}';
    }
    private String category;
    private String guid;


}
