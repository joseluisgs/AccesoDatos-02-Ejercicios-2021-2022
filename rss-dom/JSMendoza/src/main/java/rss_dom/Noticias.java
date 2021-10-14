package rss_dom;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Noticias {
    private String titulo;
    private String link;
    private String descripcion;
    private String fecha;
    private String categoria;

    @Override
    public String toString() {
        return "Noticias{" +
                "titulo='" + titulo + '\'' +
                ", link='" + link + '\'' +
                ", descripcion='" + descripcion + '\'' +
                ", fecha='" + fecha + '\'' +
                ", categoria='" + categoria + '\'' +
                '}';
    }
}
