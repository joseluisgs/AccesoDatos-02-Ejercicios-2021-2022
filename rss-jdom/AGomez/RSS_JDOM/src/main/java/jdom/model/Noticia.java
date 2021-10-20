package jdom.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class Noticia {

    private int id;
    private String titulo;
    private String link;
    private String creador;
    private String descripcion;
    private String fechaPublicacion;
    private List<String> categorias;


    public void printData() {
        System.out.println("* Noticia: " + "\n" +
                "\t--> Id " + this.id + "\n"+
                "\t--> Titular: " + this.titulo + "\n" +
                "\t--> Link: " + this.link + "\n" +
                "\t--> Creador: " + this.creador + "\n" +
                "\t--> Descripción: " + this.descripcion + "\n" +
                "\t--> Fecha de publicación: " + this.fechaPublicacion + "\n" +
                "\t-->  Categorías " + this.categorias.toString() + "\n");


    }


}