import org.xml.sax.SAXException;
import sax.SAXController;

import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;

public class App {
    public static void main(String[] args) {
        String INPUT_RSS = System.getProperty("user.dir") + File.separator + "data" + File.separator + "portada.rss";
        System.out.println(INPUT_RSS);

        try {
            SAXController controller = SAXController.getInstance(INPUT_RSS);
            controller.loadData();
            controller.getNews().forEach(System.out::println);

        } catch (SAXException | ParserConfigurationException | IOException e) {
            System.err.println("ERROR:" + e.getMessage());
        }

    }
}