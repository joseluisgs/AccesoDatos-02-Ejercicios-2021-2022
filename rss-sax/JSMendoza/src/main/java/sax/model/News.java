package sax.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class News implements Serializable {
    private String titulo;
    private String link;
    private String descripcion;
    private String fecha;
    private String categoria;

    @Override
    public String toString() {
        return "News" + "\n" +
                "Titulo -> " + titulo + "\n" +
                "Link -> " + link + "\n" +
                "Descripcion -> " + descripcion + "\n" +
                "Fecha -> " + fecha + "\n" +
                "Categoria -> " + categoria + "\n";
    }
}