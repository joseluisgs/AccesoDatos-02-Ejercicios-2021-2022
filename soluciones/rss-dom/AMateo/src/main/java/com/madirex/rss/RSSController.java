package com.madirex.rss;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.w3c.dom.*;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;

@Data
@AllArgsConstructor
public class RSSController {
    private Document doc;

    public RSSController(String path){
        File file = new File(path);

        try {
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            this.doc = dBuilder.parse(file);
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    public void mostrar(){

        try{
            NodeList nList = doc.getElementsByTagName("item");

            for(int temp = 0; temp < nList.getLength(); temp++) {
                Node nNode = nList.item(temp);

                if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element eElement = (Element) nNode;

                    System.out.println("\nEntrada: " + eElement.getAttribute("id"));
                    System.out.println("Publicación: "
                            + eElement.getElementsByTagName("pubDate").item(0).getTextContent());
                    System.out.println("Categorías: "
                            + eElement.getElementsByTagName("category").item(0).getTextContent());
                    System.out.println("Título: "
                            + eElement.getElementsByTagName("title").item(0).getTextContent());
                    System.out.println("Descripción: "
                            + eElement.getElementsByTagName("description").item(0).getTextContent());
                    System.out.println("Enlace: "
                            + eElement.getElementsByTagName("link").item(0).getTextContent());
                    System.out.println("Autor: "
                            + eElement.getElementsByTagName("author").item(0).getTextContent());
                }
            }
        }catch(Exception e){
            System.out.println(e);
        }

    }
}
