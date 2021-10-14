import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import java.io.File;
import java.io.IOException;

public class App {
    public static void main(String[] args) {
        String INPUT_XML = System.getProperty("user.dir")+ File.separator+"Fichero"+File.separator+"alumno.xml";
        System.out.println(INPUT_XML);
        try {
            System.out.println("Datos Cargados Desde Fichero");
            ControladorRss controller = ControladorRss.getInstance(INPUT_XML);
            controller.loadData();
            System.out.println("Exportando Datos a un XML...");
            controller.printXML();
            System.out.println();
            controller.getNoticia().forEach(System.out::println);

        } catch (SAXException | ParserConfigurationException | IOException | TransformerException e) {
            System.err.println("ERROR:" + e.getMessage());
        }
    }
}
