package rss_dom;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import lombok.NonNull;
import org.w3c.dom.DOMException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class Controlador {
    public static Controlador controller;
    private String uri;

    private Controlador() {
    }

    public static Controlador getInstance() {
        if (controller == null) {
            controller = new Controlador();
        }

        return controller;
    }

    public List<News> getNews(@NonNull String url) {
        if (url == null) {
            throw new NullPointerException("url is marked non-null but is null");
        } else {
            this.uri = url;
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            ArrayList news = new ArrayList();

            try {
                DocumentBuilder builder = factory.newDocumentBuilder();
                Document document = builder.parse(url);
                NodeList items = document.getElementsByTagName("item");

                for(int i = 0; i < items.getLength(); ++i) {
                    Node node = items.item(i);
                    News noticia = new News();

                    for(Node n = node.getFirstChild(); n != null; n = n.getNextSibling()) {
                        int imagescount = 0;
                        String category;
                        if (n.getNodeName().equals("title")) {
                            category = n.getTextContent();
                            noticia.setTitle(category);
                        }

                        if (n.getNodeName().equals("description")) {
                            category = n.getTextContent();
                            noticia.setDescription(category);
                        }

                        if (n.getNodeName().equals("link")) {
                            category = n.getTextContent();
                            noticia.setLink(category);
                        }

                        if (n.getNodeName().equals("pubDate")) {
                            category = n.getTextContent();
                            noticia.setPubDate(category);
                        }

                        if (n.getNodeName().equals("category")) {
                            category = n.getTextContent();
                            noticia.setCategory(category);
                        }

                        if (n.getNodeName().equals("enclosure")) {
                            Element e = (Element)n;
                            String imagen = e.getAttribute("url");
                            if (imagescount == 0) {
                                noticia.setImage(imagen);
                            }

                            int var18 = imagescount + 1;
                        }
                    }

                    news.add(noticia);
                }
            } catch (ParserConfigurationException var14) {
                System.err.println(var14.getMessage());
            } catch (IOException var15) {
                System.err.println(var15.getMessage());
            } catch (DOMException var16) {
                System.err.println(var16.getMessage());
            } catch (SAXException var17) {
                System.err.println(var17.getMessage());
            }

            return news;
        }
    }
}
