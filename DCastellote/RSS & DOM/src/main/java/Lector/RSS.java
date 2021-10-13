package Lector;

import org.w3c.dom.*;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.*;

public class RSS {
    public static void main(String[] args) {

        File file = new File("C:\\Users\\danie\\OneDrive\\Escritorio\\Luis Vives\\2ºDAM\\Acesso a datos\\titulares.xml");

        try {
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(file);

            NodeList nList = doc.getElementsByTagName("item");
            System.out.println("Numero de items del RSS " + nList.getLength());
            doc.getDocumentElement().normalize();

            for (int temp = 0; temp < nList.getLength(); temp++) {
                Node nNode = nList.item(temp);

                if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element eElement = (Element) nNode;

                    System.out.println("Título: " + eElement.getElementsByTagName("title").item(0).getTextContent());
                    System.out.println("Categoría: " + eElement.getElementsByTagName("category").item(0).getTextContent());
                    System.out.println("Dia de publicación: " + eElement.getElementsByTagName("pubDate").item(0).getTextContent());
                }
                System.out.println(" ");

            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();
            Document doc = db.newDocument();

            Element noticias = doc.createElement("noticias");
            doc.appendChild(noticias);

            Element noticia = doc.createElement("noticia");
            noticias.appendChild(noticia);

            Attr attr = doc.createAttribute("id");
            attr.setValue("1");
            noticia.setAttributeNode(attr);

            Element titulo = doc.createElement("titulo");
            titulo.appendChild(doc.createTextNode("A por otro fichajazo 'Gratis' "));
            noticia.appendChild(titulo);

            Element categoria = doc.createElement("categoria");
            categoria.appendChild(doc.createTextNode("Futbol"));
            noticia.appendChild(categoria);

            Element publicacion = doc.createElement("pubDate");
            publicacion.appendChild(doc.createTextNode("13/10/2021"));
            noticia.appendChild(publicacion);

            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(new File("noticias.xml"));

            transformer.transform(source, result);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}