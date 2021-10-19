package com.madirex;

import com.madirex.jdom.JDOMController;
import com.madirex.jdom.model.Post;
import org.jdom2.JDOMException;

import java.io.File;
import java.io.IOException;

public class App {
    public static void main(String[] args) {
        String INPUT_XML = System.getProperty("user.dir") + File.separator + "data" + File.separator + "rss.xml";
        String OUTPUT_XML = System.getProperty("user.dir") + File.separator + "data" + File.separator + "rss_updated.xml";

        try {
            //Cargar datos
            JDOMController controller = JDOMController.getInstance(INPUT_XML);
            controller.loadData();

            //Exportar datos a XML
            controller.printXML();
            controller.writeXMLFile(OUTPUT_XML);

            System.out.println("Listar todos:");
            controller.getPosts().forEach(System.out::println);

            //CRUD
            //Creando algunos nuevos posts
            controller.addPost(new Post("Mon, 20 Sep 2021 17:06:00 +0000", "Qué es un IDE", "Toda la información sobre IDE que necesitas", "https://www.madirex.com/ide", "Madirex"));
            controller.addPost(new Post("Thu, 23 Sep 2021 15:15:00 +0000", "Cómo utilizar el navegador", "Tutorial de cómo utilizar el ordenador", "https://www.madirex.com/nav", "Madirex"));
            controller.addPost(new Post("Sun, 26 Sep 2021 10:24:00 +0000", "Bloc de notas", "Tutorial de uso del bloc de notas", "https://www.madirex.com/desc", "Madirex"));

            //Agregar un atributo id
            controller.addAttribute("id","1");

            //Agregar elemento multimedia, con atributo tipo
            controller.addElementAndAtribute("multimedia",
                    "https://www.youtube.com/watch?v=yAVqckdeykY",
                    "tipo", "vídeo");

            //Cambiar la fecha a formato -> DD/MM/AAAA a las HH:MM:SS
            controller.setPubDateFormat();

            //Mostrar y guardar output
            controller.printXML();
            controller.writeXMLFile(OUTPUT_XML);

            //XPATH
            System.out.println("\nXPATH:\nIniciando y rellenando modelo de datos...");
            controller.loadData();

            System.out.println("\nPosts creados el sábado:");
            controller.getPosts().stream().filter(post -> post.getPubDate().startsWith("Sat")).forEach(System.out::println);

            System.out.println("\nObtener todos los enlaces:");
            controller.getAllLinks().forEach(System.out::println);

            System.out.println("\nObtener todos los títulos:");
            controller.getAllTitles().forEach(System.out::println);


        } catch (IOException | JDOMException e) {
            System.err.println("ERROR:" + e.getMessage());
        }

    }
}