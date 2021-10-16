import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.util.ArrayList;
import java.util.List;

public class AlumnoHandler extends DefaultHandler {

    private boolean hasNombre = false;
    private boolean hasApellido = false;
    private boolean hasDni = false;
    private boolean hasComentario = false;

    private List<Alumno> listaAlumno = null;
    private Alumno alumno = null;

    public List<Alumno> getAlumnos() {
        return listaAlumno;
    }
    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) {
        if (listaAlumno == null)
            listaAlumno = new ArrayList<>();
        if (qName.equalsIgnoreCase("alumno")) {
            alumno = new Alumno();
            String id = attributes.getValue("id");
            alumno = new Alumno();
            alumno.setId(Integer.parseInt(id));
        } else if (qName.equalsIgnoreCase("nombre")) {
            hasNombre = true;
        } else if (qName.equalsIgnoreCase("apellido")) {
            hasApellido = true;
        } else if (qName.equalsIgnoreCase("dni")) {
            hasDni = true;
        } else if (qName.equalsIgnoreCase("comentario")) {
            hasComentario = true;
        }
    }
    @Override
    public void endElement(String uri, String localName, String qName) {
        if (qName.equalsIgnoreCase("alumno")) {
            listaAlumno.add(alumno);
        }
    }
    @Override
    public void characters(char ch[], int start, int length) {
        if (hasNombre) {
            alumno.setNombre(new String(ch, start, length));
            hasNombre = false;
        } else if (hasApellido) {
            alumno.setApellido(new String(ch, start, length));
            hasApellido = false;
        } else if (hasDni) {
            alumno.setDni(new String(ch, start, length));
            hasDni = false;
        } else if (hasComentario) {
            alumno.setComentario(new String(ch, start, length));
            hasComentario = false;
        }
    }
}
