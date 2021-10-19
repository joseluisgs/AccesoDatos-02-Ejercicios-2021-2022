package dom.model;

import lombok.Data;


@Data
public class Noticia {

    private String titulo;
    private String link;
    private String creador;
    private String fecha;
    private String descripcion;
    private String contenido;

    public String toString() {
        return "* Noticia: " + "\n" +
                "\t--> Titular: " + this.titulo + "\n" +
                "\t--> Link: " + this.link + "\n" +
                "\t--> Creador: " + this.creador + "\n" +
                "\t--> Fecha: " + this.fecha + "\n" +
                "\t--> Descripción: " + this.descripcion + "\n" +
                "\t--> Contenido: " + this.contenido + "\n";
    }

}
