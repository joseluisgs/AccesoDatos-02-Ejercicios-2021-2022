import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.IOException;
import java.util.List;

public class SaxReader {

    List<Noticia> noticias;
    public void rssToList(String uri){

        try{
            SAXParserFactory factory = SAXParserFactory.newInstance();
            SAXParser parser = factory.newSAXParser();

            NoticiasHandler handler = new NoticiasHandler();

            parser.parse(uri,handler);

            noticias = handler.getNoticias();

        } catch (ParserConfigurationException | SAXException | IOException e) {
            e.printStackTrace();
        }
    }

    public List<Noticia> getNoticias(){
        return this.noticias;
    }
}
