
import org.w3c.dom.*;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;

public class Documento {

    public void leerDocumento() {
        File file = new File("C:\\Users\\Mario\\Desktop\\titulares.xml");

        try {
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(file);
            doc.normalize();
            NodeList nList = doc.getElementsByTagName("item");
            System.out.println("El número de noticias total es de " + nList.getLength());
            System.out.println("Fecha de la publicación: " + doc.getElementsByTagName("pubDate").item(0).getTextContent() + "\n\n");
            for (int i = 0; i < nList.getLength(); i++) {
                Node nNode = nList.item(i);
                if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element eElement = (Element) nNode;
                    System.out.println(eElement.getElementsByTagName("title").item(0).getTextContent() + "\n");
                    if (eElement.getElementsByTagName("content:encoded").item(0) == null) {
                        System.out.println("\t" + eElement.getElementsByTagName("description").item(0).getTextContent());
                    } else {
                        System.out.println("\t" + eElement.getElementsByTagName("content:encoded").item(0).getTextContent());
                    }
                    System.out.println("\t\t-Autor-Autores: " + eElement.getElementsByTagName("dc:creator").item(0).getTextContent());
                    System.out.println("\t\t\t-Link de la publicación: " + eElement.getElementsByTagName("link").item(0).getTextContent());
                    System.out.println("\n\n");
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }


    }


}
