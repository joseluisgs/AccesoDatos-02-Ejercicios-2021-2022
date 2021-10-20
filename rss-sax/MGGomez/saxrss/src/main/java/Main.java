import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.IOException;
import java.util.List;

public class Main {
    public static void main(String[] args) throws ParserConfigurationException, SAXException, IOException {
        SAXParserFactory factory = SAXParserFactory.newInstance();
        SAXParser saxParser = factory.newSAXParser();
        LectorSax handler = new LectorSax();
        saxParser.parse("http://ep00.epimg.net/rss/tags/noticias_mas_vistas.xml", handler);
        List<Noticia> noticias = handler.getNoticias();

        for (Noticia noticia : noticias) {
            System.out.println(noticia.getTitle());
            if (noticia.getBody() == null) {
                System.out.println("\t\t" + noticia.getBodyopt());
            } else {
                System.out.println("\t" + noticia.getBody());
            }

            System.out.println("\t\t-Autor-Autores: " + noticia.getAuthor());
            System.out.println("\t\t\t- Para más información, consulata el link de la publicación: " + noticia.getLink());
            System.out.println("\n\n");
        }
    }
}
