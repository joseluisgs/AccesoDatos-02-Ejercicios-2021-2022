import dom.model.Noticia;
import dom.DOMController;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import java.io.File;
import java.io.IOException;

public class Main {

    public static void main(String[] args) {

        System.out.println("*** LECTOR DE NOTICIAS RSS ***");
        String INPUT_RSS = System.getProperty("user.dir")+ File.separator+ "data" +File.separator+"rss.rss";
        String OUTPUT_RSS = System.getProperty("user.dir")+ File.separator+ "data" +File.separator+"news_update.rss";
        System.out.println(INPUT_RSS);

        try {
            System.out.println("*** Cargamos nuestros Datos y DOM desde fichero ***");
            DOMController controller = DOMController.getInstance(INPUT_RSS);
            controller.loadData();
            System.out.println("Exportamos los datos a un RSS");
            controller.printRSS();
            controller.writeRSSFile(OUTPUT_RSS);
            System.out.println();

            System.out.println("*** DOM RSS *** ");


            System.out.println("Listando todos los elementos");
            controller.getNoticias().forEach(System.out::println);

            System.out.println("Todos Mayores de 40");
            controller.getNoticias().stream().filter(notia -> notia.getCategoria().equals("Nacional")).forEach(System.out::println);
            System.out.println();

            System.out.println("Se inicia el modelo vacío y se rellena con los datos pasados");
            controller.initData();
            controller.addNews(new Noticia("El Gobierno prevé invertir en Cataluña y Andalucía",
                    "https://www.europapress.es/nacional/noticia-av-gobierno-preve-invertir-cataluna-doble-" +
                            "comunidad-madrid-20211013145311.html", "Madrid, Euskadi, Extremadura y Baleares, " +
                    "las únicas CCAA en las que disminuye la inversión respecto al proyecto de PGE del año pasado",
                    "https://img.europapress.es/fotoweb/fotonoticia_20211013145311_120.jpg", "Wed, 13 Oct 2021 " +
                    "12:53:11 GMT", "Nacional"));

            controller.printRSS();
            controller.writeRSSFile(OUTPUT_RSS);

            System.out.println("Añadimos el elemento GUID");
            controller.addElement("guid", "https://www.europapress.es/nacional/noticia-av-gobierno-preve-" +
                    "invertir-cataluna-doble-comunidad-madrid-20211013145311.html");
            System.out.println("Eliminamos Link");
            controller.deleteElement("link");
            controller.writeRSSFile(OUTPUT_RSS);
            controller.printRSS();
            System.out.println();

        } catch (SAXException | ParserConfigurationException | IOException | TransformerException e) {
            System.err.println("ERROR:" + e.getMessage());
        }
    }
}