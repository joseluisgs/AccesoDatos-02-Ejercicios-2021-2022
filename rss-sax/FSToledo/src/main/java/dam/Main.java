package dam;

import dam.sax.SAXController;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;

public class Main {
    public static void main(String[] args){
        String INPUT_XML = System.getProperty("user.dir")+File.separator+"data"+File.separator+"el_mundo.xml";
        System.out.println(INPUT_XML);
        try{
            System.out.println("Loading Data XML using SAX from the file");
            SAXController controller = SAXController.getInstance(INPUT_XML);
            controller.loadData();
            System.out.println("---- SAX XML ----");
            System.out.println("List");
            System.out.println("All");
            controller.getNews().forEach(System.out::println);
            System.out.println("Every news which Pablo Pardo is the creator");
            controller.getNews().stream().filter(news ->news.getCreator().contains("PABLO PARDO")).forEach(System.out::println);

        } catch (ParserConfigurationException| SAXException | IOException e) {
            e.printStackTrace();
        }
    }
}
