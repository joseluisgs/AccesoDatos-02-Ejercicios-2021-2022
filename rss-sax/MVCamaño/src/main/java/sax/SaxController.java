package sax;

import lombok.NonNull;
import model.Portada;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.IOException;
import java.util.List;

public class SaxController {

    private static SaxController controller;
    private final String uri;
    private List<Portada> portadaList;

    public SaxController(String uri) {
        this.uri = uri;
    }

    public static SaxController getInstance(@NonNull String uri) {
        if (controller == null)
            controller = new SaxController(uri);
        return controller;
    }

    public void cargarDatos() throws ParserConfigurationException, SAXException, IOException {
        SAXParserFactory saxParserFactory = SAXParserFactory.newInstance();
        SAXParser saxParser = saxParserFactory.newSAXParser();

        PortadaHandler portadaHandler = new PortadaHandler();

        saxParser.parse(this.uri, portadaHandler);
        //Obtenemos la lista de portada, que es el rss
        portadaList = portadaHandler.getPortada();
    }

    public List<Portada> getPortada() {
        return this.portadaList;
    }
}
