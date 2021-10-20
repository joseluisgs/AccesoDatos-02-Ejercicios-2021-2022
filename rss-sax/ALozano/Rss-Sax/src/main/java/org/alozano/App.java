package org.alozano;


import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;

public class App
{
    public static void main(String[] args) {
        //String INPUT_XML = System.getProperty("user.dir") + File.separator + "data" + File.separator + "users.xml";
        String INPUT_XML = "https://feeds.elpais.com/mrss-s/pages/ep/site/elpais.com/portada";
        System.out.println(INPUT_XML);

        try {
            System.out.println("Se cargan los datos usando SAX desde un RSS");
            SAXController controller = SAXController.getInstance(INPUT_XML);
            controller.loadData();
            System.out.println("*** Lector de RSS con SAX *** ");
            System.out.println("Listado de Noticias");
            controller.getNoticias().forEach(System.out::println);
        } catch (SAXException | ParserConfigurationException | IOException e) {
            System.err.println("ERROR:" + e.getMessage());
        }


    }
}
