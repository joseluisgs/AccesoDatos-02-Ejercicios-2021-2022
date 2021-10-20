package Lector.SAX;

import lombok.NonNull;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.IOException;
import java.util.List;

public class ControladorSAX {
    private static ControladorSAX controllerSax;
    private final String uri;
    private List<Noticias> listaNoticias;

    private ControladorSAX(String uri) {

        this.uri = uri;
    }

    public static ControladorSAX getInstance(@NonNull String uri) {
        if (controllerSax == null)
            controllerSax = new ControladorSAX(uri);
        return controllerSax;
    }

    public void loadData() throws ParserConfigurationException, SAXException, IOException {
        SAXParserFactory saxParserFactory = SAXParserFactory.newInstance();
        SAXParser saxParser = saxParserFactory.newSAXParser();
        ControladorNoticias controllernews = new ControladorNoticias();
        saxParser.parse(this.uri, controllernews);
        this.listaNoticias = controllernews.getNews();
    }

    public List<Noticias> getAlumnos() {
        return this.listaNoticias;
    }
}
