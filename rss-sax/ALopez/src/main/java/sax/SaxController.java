package sax;



import lombok.NonNull;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.IOException;
import java.util.List;

public class SaxController {
    private static SaxController controlador;
    private  String uri;
    private List<News> listanewst;

    private SaxController(String uri) {
        this.uri = uri;
    }


    public static SaxController getInstance(@NonNull String uri) {
        if (controlador == null)
            controlador = new SaxController(uri);
        return controlador;
    }
    public void cargarData() throws ParserConfigurationException, IOException, SAXException {
        SAXParserFactory saxParserFactory = SAXParserFactory.newInstance();
        SAXParser saxParser = saxParserFactory.newSAXParser();
        NewsHandler handler = new NewsHandler();
        saxParser.parse(this.uri, handler);
        this.listanewst = handler.getNews();
    }
    public List<News> getPosts(){return this.listanewst;}

}
