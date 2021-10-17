import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;

public class App {
    public static void main(String[] args) {
        String INPUT_XML = System.getProperty("user.dir") + File.separator + "Fichero" + File.separator + "alumno.xml";
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
