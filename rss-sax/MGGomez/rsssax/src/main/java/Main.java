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
        Servicios serv = new Servicios();
        
        serv.imprimirNoticia(noticias);
    }
}
