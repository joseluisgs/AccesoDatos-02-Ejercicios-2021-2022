import org.xml.sax.SAXException;
import sax.SaxController;

import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;

public class Principal {
    public static void main(String[] args) {
        String INPUT_XML = "src" + File.separator + "main" + File.separator + "resources" + File.separator + "portada.xml";

        try {
            System.out.println("Procesamos el fichero usando SAX");
            SaxController controller = SaxController.getInstance(INPUT_XML);
            controller.cargarDatos();

            controller.getPortada().forEach(System.out::println);




        } catch (SAXException | ParserConfigurationException | IOException e) {
            System.err.println("ERROR:" + e.getMessage());
        }


    }
}

