package Lector.SAX;

import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.File;
import java.io.IOException;
import java.util.List;

public class SAX {
    public static void main(String[] args) throws ParserConfigurationException, SAXException, IOException {
        String INPUT_XML = System.getProperty("user.dir") + File.separator + "src" + File.separator + "main" + File.separator +"java" + File.separator +"Lector" + File.separator +"titulares.xml";
        System.out.println(INPUT_XML);

        try {
            System.out.println("Cargamos los datos del fichero usando SAX");
            ControladorSAX controller = ControladorSAX.getInstance(INPUT_XML);
            controller.loadData();
            System.out.println("SALIDA SAX");
            controller.getAlumnos().forEach(System.out::println);

        } catch (SAXException | ParserConfigurationException | IOException e) {
            System.err.println("ERROR:" + e.getMessage());
        }



    }
}
