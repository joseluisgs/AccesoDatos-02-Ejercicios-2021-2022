package org.alozano.model;


import lombok.Data;

import java.io.Serializable;

@Data
public class Noticia implements Serializable {

    private String titulo;
    private String categoria;
    private String descripcion;
    private String fecha;
    private String enlace;
    private String author;
    private static int num=1;
    private int count;

    public String toString() {
        return "Mi Noticia: " +count+ "\n"+
                " Titulo: " + this.titulo + "\n" +
                " Categoria: " + this.categoria + "\n" +
                " Descripción: " + this.descripcion + "\n" +
                " Fecha: " + this.fecha + "\n" +
                " Enlace: " + this.enlace + "\n" +
                " Autor: " + this.author + "\n";
    }
    public Noticia(){
        count = num;
        num++;
    }

    public Noticia(String titulo, String categoria, String descripcion, String fecha, String enlace, String author) {
        this.titulo = titulo;
        this.categoria = categoria;
        this.descripcion = descripcion;
        this.fecha = fecha;
        this.enlace = enlace;
        this.author = author;
    }
}