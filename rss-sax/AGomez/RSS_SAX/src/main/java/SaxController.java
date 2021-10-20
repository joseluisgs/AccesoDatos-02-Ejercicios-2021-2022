import lombok.NonNull;
import model.Noticia;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.IOException;
import java.util.List;

public class SaxController {
    private static SaxController controller;
    private final String uri;
    private List<Noticia> listaNoticias;


    private SaxController(String uri) {
        this.uri = uri;


    }

    public static SaxController getInstance(@NonNull String uri) {

        if (controller == null)
            controller = new SaxController(uri);

        return controller;


    }

    public void loadData() throws ParserConfigurationException, IOException, SAXException {

        SAXParserFactory saxParserFactory = SAXParserFactory.newInstance();
        SAXParser saxParser = saxParserFactory.newSAXParser();
        GestorNoticias gestor = new GestorNoticias();
        saxParser.parse(this.uri, gestor);
        this.listaNoticias = gestor.getNoticias();

    }
    public List<Noticia>getNoticias(){
        return this.listaNoticias;
    }





}
