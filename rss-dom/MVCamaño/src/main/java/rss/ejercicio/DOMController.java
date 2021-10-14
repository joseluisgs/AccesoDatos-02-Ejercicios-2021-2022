package rss.ejercicio;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;

public class DOMController {
    private static String PATH = "C:\\Users\\mario\\IdeaProjects\\RSS\\src\\main\\resources\\portada.xml";
    Document document;

    public DOMController(String path) {
        this.PATH = path;
    }

    public void portada() {
        File file = new File(PATH);
        DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();

        try {

            DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
            document = documentBuilder.parse(file);

        } catch (ParserConfigurationException | SAXException | IOException e) {
            e.printStackTrace();

        }
        Elementos elementos = new Elementos();
        NodeList items = document.getElementsByTagName("item");

        for (int i = 0; i < items.getLength(); i++) {
            Node node = items.item(i);

                if (node.getNodeType() == Node.ELEMENT_NODE) {

                    Element element = (Element) node;

                    elementos.setTitle(element.getElementsByTagName("title").item(0).getTextContent());
                    System.out.println("Title" + "-----" + elementos.getTitle());

                    elementos.setCategory(element.getElementsByTagName("description").item(0).getTextContent());
                    System.out.println("Description"+"------" + elementos.getCategory());

                    elementos.setCategory(element.getElementsByTagName("guid").item(0).getTextContent());
                    System.out.println("Guid"+"------" + elementos.getCategory());

                    elementos.setCategory(element.getElementsByTagName("category").item(0).getTextContent());
                    System.out.println("Category"+"------" + elementos.getCategory());

                }



        }
    }
}


