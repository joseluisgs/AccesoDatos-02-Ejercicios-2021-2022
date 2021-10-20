
import org.xml.sax.SAXException;
import sax.SAXController;

import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        String INPUT_XML = System.getProperty("user.dir") + File.separator + "data" + File.separator + "portada.rss";

        System.out.println(INPUT_XML);
        try {
            SAXController controller = SAXController.getInstance(INPUT_XML);
            controller.loadData();

            System.out.println("Listando todos:");
            controller.getNews().forEach(System.out::println);

        } catch (SAXException | ParserConfigurationException | IOException e) {
            System.err.println("ERROR:" + e.getMessage());
        }


    }
}
