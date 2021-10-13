package rss_dom;

import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import java.io.File;
import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        String INPUT_RSS = System.getProperty("user.dir") + File.separator + "data" + File.separator +
                "data/rss.rss";
        System.out.println(INPUT_RSS);

        try {
            System.out.println("Cargamos nuestros Datos y DOM desde fichero");
            ControladorDOM controlador = ControladorDOM.getInstance(INPUT_RSS);
            controlador.loadData();
            System.out.println("Exportamos los datos a un XML");
            controlador.printRSS();

        } catch (ParserConfigurationException | TransformerException | SAXException | IOException e) {
            e.printStackTrace();
        }
    }
}
