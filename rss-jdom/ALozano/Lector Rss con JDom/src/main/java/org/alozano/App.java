package org.alozano;



import org.alozano.model.Noticia;
import org.jdom2.JDOMException;

import java.io.File;
import java.io.IOException;

public class App {
    public static void main(String[] args) {
        String INPUT_XML = "https://feeds.elpais.com/mrss-s/pages/ep/site/elpais.com/portada";
        String OUTPUT_XML = System.getProperty("user.dir") + File.separator + "data" + File.separator + "noticias.xml";
        String OUTPUT_XML2 = System.getProperty("user.dir") + File.separator + "data" + File.separator + "noticias2.xml";

        System.out.println(INPUT_XML);

        try {
            System.out.println("Se cargan los datos usando JDOM desde una dirección rss");
            JDOMController controller = JDOMController.getInstance(INPUT_XML);
            controller.loadData();
            System.out.println("Se exportan los datos a un XML");
            controller.printXML();
            controller.writeXMLFile(OUTPUT_XML);
            System.out.println();

            System.out.println("*** JDOM XML *** ");
            System.out.println("Lista de las noticias encontradas");
            controller.getNoticias().forEach(System.out::println);

            System.out.println();

            System.out.println("Operaciones XPATH");
            System.out.println("Se obtienen los títulos de las noticias:");
            controller.getAllTitulos().forEach(System.out::println);
            System.out.println("\n\nObtenemos el titulo del 5º elemento");
            System.out.println(controller.getIndexTitulo(5));

            System.out.println("\n\n\nOperaciones CRUD");
            System.out.println("Iniciamos un modelo de datos vacío y lo rellenamos nosotros");
            controller.initData();
            controller.addNoticia(new Noticia("Facebook sabe tanto de sus gustos que puede mostrarle un anuncio solo a usted ",
                    "Tecnología", "La nanosegmentación surge de un experimento único hecho en la " +
                    "plataforma por investigadores españoles. Expertos en privacidad lo ven como un peligro inaudito",
                    "20/10/2021 01:31:22" , "https://elpais.com/tecnologia/2021-10-20/como-poner-un-anuncio-a-una-sola-persona-en-facebook-de-entre-sus-cientos-de-millones-de-usuarios.html" ,
                    "anonimo"));

            controller.printXML();
            controller.writeXMLFile(OUTPUT_XML2);

            System.out.println("Se añade un elemento nuevo");
            controller.addElement("Periodico", "El Pais");
            System.out.println("Se elimina al autor");
            controller.deleteElement("creator");
            System.out.println("Ponemos en Mayúscula el valor del Titulo");
            controller.ValorEnMayuscula("title");
            controller.writeXMLFile(OUTPUT_XML2);
            controller.printXML();
            System.out.println();

        } catch (IOException | JDOMException e) {
            System.err.println("ERROR:" + e.getMessage());
        }

    }
}
