import org.xml.sax.SAXException;
import sax.SAXController;
import sax.model.Noticia;

import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;

public class App {
    public static void main(String[] args) {
        System.out.println("*** LECTOR DE NOTICIAS RSS ***");
        String INPUT_RSS = System.getProperty("user.dir")+ File.separator+ "data" +File.separator+"rss.rss";
        System.out.println(INPUT_RSS);

        try {
            System.out.println("Cargamos nuestros datos usando SAX desde fichero rss");
            SAXController controller = SAXController.getInstance(INPUT_RSS);
            controller.loadData();

            System.out.println("*** SAX RSS *** ");

            System.out.println("Listando todos los elementos");
            controller.getNews().forEach(System.out::println);

            System.out.println("*** Expresiones Lamda ***");
            System.out.println("Título que empieza por Sánchez");
            controller.getNews().stream().filter(news -> news.getTitulo().startsWith("Sánchez")).forEach(System.out::println);
            System.out.println("Número de Items en el RSS");
            System.out.println(controller.getNews().stream().count());
            System.out.println("Imprime los 2 primeros items");
            controller.getNews().stream().limit(2).forEach(System.out::println);

        } catch (SAXException | ParserConfigurationException | IOException e) {
            System.err.println("ERROR:" + e.getMessage());
        }

    }
}
