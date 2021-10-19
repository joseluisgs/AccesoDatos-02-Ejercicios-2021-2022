import model.Noticia;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.util.List;

public class Main {
    public static void main(String[] args) throws ParserConfigurationException, IOException, SAXException {


        try {
            SaxController controller = SaxController.getInstance("http://ep00.epimg.net/rss/tags/ultimas_noticias.xml");
            //String INPUT_XML = System.getProperty("user.dir") + File.separator + "data" + File.separator + "RSS.xml";
           // SaxController controller = SaxController.getInstance(INPUT_XML);

            controller.loadData();

            System.out.println("==== PROCESANDO XML CON SAX ========");

            System.out.println("Lista de todas las noticias");

            controller.getNoticias().forEach(System.out::println);

        } catch (SAXException | ParserConfigurationException | IOException e) {
            System.err.println("ERROR:" + e.getMessage());
        }
    }
}
