package dam;

import dam.dom.DOMController;
import dam.dom.model.News;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import java.io.File;
import java.io.IOException;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        String INPUT_XML = System.getProperty("user.dir")+ File.separator +"data"+ File.separator+"el_mundo.xml";
        String OUTPUT_XML = System.getProperty("user.dir")+ File.separator +"data"+ File.separator+"el_mundo_updated.xml";
        System.out.println(INPUT_XML);

        try {
            System.out.println("Loading our Data and DOM from file");
            DOMController controller = DOMController.getInstance(INPUT_XML);
            controller.loadData();
            System.out.println("Exporting all data to a XMl");
            controller.printXML();
            List<News> newsList = controller.getNewsList();
            System.out.println("");
            controller.initData();
            for (News news:newsList) {
                controller.addNewsItem(news);
            }
            controller.printXML();
            controller.writeXMLFile(OUTPUT_XML);

        } catch (SAXException | ParserConfigurationException | IOException | TransformerException e) {
          System.err.println("ERROR:"+e.getMessage());
        }
    }
}
