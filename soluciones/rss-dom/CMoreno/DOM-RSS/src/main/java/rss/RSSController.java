package rss;

import lombok.NonNull;
import org.w3c.dom.*;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class RSSController {
    public static RSSController controller;
    private String uri;

    private RSSController() {
    }

    public static RSSController getInstance() {
        if (controller == null) {
            controller = new RSSController();
        }
        return controller;
    }

    public List<Noticia> getNoticias(@NonNull String uri) {
        this.uri = uri;
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        ArrayList<Noticia> noticias = new ArrayList();

        try {
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document = builder.parse(uri);

            NodeList items = document.getElementsByTagName("item");

            for (int i = 0; i < items.getLength(); i++) {
                Node nodo = items.item(i);

                Noticia noticia = new Noticia();

                for (Node n = nodo.getFirstChild(); n != null; n = n.getNextSibling()) {
                    int contadorImagenes = 0;

                    if (n.getNodeName().equals("title")) {
                        String titulo = n.getTextContent();
                        noticia.setTitulo(titulo);
                    }

                    if (n.getNodeName().equals("link")) {
                        String enlace = n.getTextContent();
                        noticia.setLink(enlace);
                    }

                    if (n.getNodeName().equals("description")) {
                        String descripcion = n.getTextContent();
                        noticia.setDescripcion(descripcion);
                    }

                    if (n.getNodeName().equals("pubDate")) {
                        String fecha = n.getTextContent();
                        noticia.setFecha(fecha);
                    }

                    if (n.getNodeName().equals("category")) {
                        String categoria = n.getTextContent();
                        noticia.setCategoria(categoria);
                    }


                    if (n.getNodeName().equals("enclosure")) {
                        Element e = (Element) n;
                        String imagen = e.getAttribute("url");
                        if (contadorImagenes == 0) {
                            noticia.setImagen(imagen);
                        }
                        contadorImagenes++;
                    }

                }

                noticias.add(noticia);
            }

        } catch (ParserConfigurationException e) {

            System.err.println("ERROR:" + e.getMessage());

        } catch (IOException e) {
            System.err.println("ERROR:" + e.getMessage());

        } catch (DOMException e) {
            System.err.println("ERROR:" + e.getMessage());

        } catch (SAXException e) {
            System.err.println("ERROR:" + e.getMessage());

        }
        return noticias;
    }
}
