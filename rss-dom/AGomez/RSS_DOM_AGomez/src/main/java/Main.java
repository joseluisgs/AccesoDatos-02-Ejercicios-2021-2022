import dom.DOMController;
import dom.model.Noticia;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import java.io.File;
import java.io.IOException;
import java.io.LineNumberInputStream;
import java.util.List;

public class Main {


    public static void main(String[] args) throws ParserConfigurationException, IOException, SAXException, TransformerException {

        String XML_SALIDA = System.getProperty("user.dir") + File.separator + "data" + File.separator + "noticias.xml";
        String XML_RSS = System.getProperty("user.dir") + File.separator + "data" + File.separator + "RSS.xml";
        System.out.println("LECTOR DE NOTICIAS RSS CON DOM");
        DOMController controller = DOMController.getInstance(XML_RSS);
        controller.cargarDatos();
        System.out.println("Exportación de datos a un XML");
        //controller.printXML();
        controller.writeXMLFile(XML_SALIDA);
        System.out.println();

        for (Noticia noticia : controller.getNoticias()
        ) {
            System.out.println(noticia.toString());

        }


    }
}
