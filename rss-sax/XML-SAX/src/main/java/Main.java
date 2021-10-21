
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.File;
import java.io.IOException;
import java.util.List;


/*
REFERENCIAS USADAS EN EL PROYECTO:
https://devs4j.com/2017/06/06/como-leer-un-xml-con-java-utilizando-sax/
http://www.padelspain.net/rss/rss.php?sec=1
https://decodigo.com/java-leer-xml-con-sax-parser
 */
public class Main {
    public static void main(String[] args) throws ParserConfigurationException, IOException, SAXException {

        SAXParserFactory factory = SAXParserFactory.newInstance();
        SAXParser saxParser = factory.newSAXParser();

        Manipulador handler = new Manipulador();
        saxParser.parse("http://www.padelspain.net/rss/rss.php?sec=1", handler);
        List<PojoNoticia> item= handler.getItem();
        for (PojoNoticia noticia : item) {
                System.out.println("Titlulo: " + noticia.getTitle());
                System.out.println("Link de la Imagen: " + noticia.getImage());
                System.out.println("Link de la Noticia: " + noticia.getLink());
                System.out.println("Autor: " + noticia.getAuthor());
                System.out.println("Guid de la Noticia: " + noticia.getGuid());
                System.out.println("Fecha de publicación: " + noticia.getPubDate());
            }
        }
    }
