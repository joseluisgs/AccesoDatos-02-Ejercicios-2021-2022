package sax;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class News implements Serializable {
    private String guid;
    private String title;
    private String pubDate;
    private String link;
    private String category;
    private String content;


    public String toString() {
        return "*** sax.News ***" + "\n" +
                "\t--> Titular: " + title + "\n" +
                "\t--> Enlace: " + link + "\n" +
                "\t--> Fecha: " + pubDate + "\n" +
                "\t--> Categoría: " + category + "\n"+
                 "\t--> Imagen: " + content + "\n" ;
    }


}
