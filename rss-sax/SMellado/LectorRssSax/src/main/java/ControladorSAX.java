import lombok.NonNull;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.IOException;
import java.util.List;

public class ControladorSAX {
    private static ControladorSAX controller;
    private final String uri;
    private List<Alumno> listaAlumno;

    private ControladorSAX(String uri) {

        this.uri = uri;
    }

    public static ControladorSAX getInstance(@NonNull String uri) {
        if (controller == null)
            controller = new ControladorSAX(uri);
        return controller;
    }

    public void loadData() throws ParserConfigurationException, SAXException, IOException {
        SAXParserFactory saxParserFactory = SAXParserFactory.newInstance();
        SAXParser saxParser = saxParserFactory.newSAXParser();
        AlumnoHandler handler = new AlumnoHandler();
        saxParser.parse(this.uri, handler);
        this.listaAlumno = handler.getAlumnos();
    }

    public List<Alumno> getAlumnos() {
        return this.listaAlumno;
    }
}

