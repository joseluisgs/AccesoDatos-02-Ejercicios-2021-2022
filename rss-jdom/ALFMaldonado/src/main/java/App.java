import jdom.JDOMController;
import jdom.model.News;
import org.jdom2.JDOMException;

import java.io.File;
import java.io.IOException;

public class App {
    public static void main(String[] args) {
        String INPUT_XML = System.getProperty("user.dir") + File.separator + "data" + File.separator + "portada.rss";
        String OUTPUT_XML = System.getProperty("user.dir") + File.separator + "data" + File.separator + "portada_updated.rss";

        try {
            System.out.println("Cargamos nuestros Datos usando JDOM desde fichero");
            JDOMController controller = JDOMController.getInstance(INPUT_XML);
            controller.loadData();

            System.out.println("Exportar datos a XML");
            System.out.println();
            System.out.println("*** JDOM XML ***");
            System.out.println("Todas las news:");
            controller.getNews().forEach(System.out::println);


            System.out.println("new news");
            controller.addNews(new News("Mon, 18 Oct 2021 09:01:35 +0000", "reyalfre", "Soy el programador de este jdom", "https://unpocovariado.blogspot.com/", "Alfredo Maldonado"));
            controller.addAttribute("id","4");
            System.out.println("DD/MM/AAAA");
            controller.PubDate();


            controller.printXML();
            controller.writeXMLFile(OUTPUT_XML);


            System.out.println("XPATH:");
            controller.loadData();
            System.out.println("Posts del lunes:");
            controller.getNews().stream().filter(news -> news.getPubDate().startsWith("Mon")).forEach(System.out::println);
            System.out.println("Get descriptions:");
            controller.Description().forEach(System.out::println);

        } catch (IOException | JDOMException e) {
            System.err.println("ERROR:" + e.getMessage());
        }

    }
}
