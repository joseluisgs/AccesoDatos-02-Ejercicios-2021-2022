package rss;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

@Data
public class Noticia implements Serializable {
    private String titulo;
    private String link;
    private String descripcion;
    private String imagen;
    private String fecha;
    private String categoria;

    public String toString() {
        return "*** Noticia ***" + "\n" +
                "\t--> Titular: " + this.titulo + "\n" +
                "\t--> Enlace: " + this.link + "\n" +
                "\t--> Descripción: " + this.descripcion + "\n" +
                "\t--> Imagen: " + this.imagen + "\n" +
                "\t--> Fecha: " + this.fecha + "\n" +
                "\t--> Categoría: " + this.categoria + "\n";


    }
}
