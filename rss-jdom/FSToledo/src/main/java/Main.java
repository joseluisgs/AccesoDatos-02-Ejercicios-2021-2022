import jdom.JDOMController;
import jdom.model.News;
import org.jdom2.JDOMException;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        String INPUT_XML = System.getProperty("user.dir")+
                File.separator +"FSToledo"+File.separator+"data"+File.separator +"el_mundo.xml";
        String OUTPUT_XML = System.getProperty("user.dir")+
                File.separator +"FSToledo"+File.separator+"data"+File.separator +"el_mundo_updated.xml";
        System.out.println(INPUT_XML);

        try {
            System.out.println("Loading Data from XML using JDOM from the file");
            JDOMController controller = JDOMController.getInstance(INPUT_XML);
            controller.loadData();
            System.out.println("Exporting the Data from a XML");
            controller.printXML();
            controller.writeXMLFile(OUTPUT_XML);
            System.out.println("");

            System.out.println("---- JDOM XML ----");
            System.out.println("List");
            System.out.println("All news");
            controller.getNewsList().forEach(System.out::println);

            System.out.println("All news which Id greater than 20");
            controller.getNewsList().stream().filter(news -> news.getId() > 20).forEach(System.out::println);
            System.out.println("");

            System.out.println("XPath Operations");
            System.out.println("All Titles");
            controller.getAllTitles().forEach(System.out::println);
            System.out.println("Title of third item");
            System.out.println(controller.getTitle(3));
            System.out.println("All the url of Images of the news");
            controller.getAllImagesUrl().forEach(System.out::println);
        }catch (IOException | JDOMException e){
            e.printStackTrace();
        }
    }
}
