import org.xml.sax.SAXException;
import sax.SaxController;

import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;

public class App {

    public static void main(String[] args) {
        String INPUT_RSS = System.getProperty("user.dir") + File.separator + "data" + File.separator + "paisRss.rss";

        System.out.println(INPUT_RSS);
        try {
            SaxController controlador = SaxController.getInstance(INPUT_RSS);
            controlador.cargarData();

            System.out.println("Produciondo listado");
            controlador.getPosts().forEach(System.out::println);

            System.out.println("Posts creados el sabado:");
            controlador.getPosts().stream().filter(post -> post.getPubDate().startsWith("Sat")).forEach(System.out::println);

            System.out.println();

        } catch (SAXException | ParserConfigurationException | IOException e) {
            System.err.println("ERROR:" + e.getMessage());
        }


    }


}
