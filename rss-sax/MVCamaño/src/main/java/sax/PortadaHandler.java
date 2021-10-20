package sax;

import model.Portada;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.util.ArrayList;
import java.util.List;

public class PortadaHandler extends DefaultHandler {

    private List<Portada> portadaList;
    private Portada portada;

    private boolean hasTitle = false;
    private boolean hasLink = false;
    private boolean hasDescription = false;
    private boolean hasPubDate = false;
    private boolean hasCategory = false;
    private boolean hasAuthor = false;


    public List<Portada> getPortada() {
        return portadaList;
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        if (portadaList == null) {
            portadaList = new ArrayList<>();
            //Consultamos los elemementos para ver que existen correctamente
        } else if (qName.equalsIgnoreCase("title")) {
            hasTitle = true;
            portada = new Portada();
        } else if (qName.equalsIgnoreCase("link")) {
            hasLink = true;
        } else if (qName.equalsIgnoreCase("description")) {
            hasDescription = true;
        } else if (qName.equalsIgnoreCase("author")) {
            //Mi rss no tiene author, por eso su valor es null
            hasAuthor = true;
        } else if (qName.equalsIgnoreCase("pubDate")) {
            hasPubDate = true;
        } else if (qName.equalsIgnoreCase("category")) {
            hasCategory = true;
        }


    }


    @Override
    public void characters(char ch[], int start, int length) throws SAXException {

        if (hasTitle) {
            portada.setTitle(new String(ch, start, length));
            hasTitle = false;
        } else if (hasLink) {
            portada.setLink(new String(ch, start, length));
            hasLink = false;
        } else if (hasDescription) {
            portada.setDescription(new String(ch, start, length));
            hasDescription = false;
        } else if (hasCategory) {
            portada.setCategory(new String(ch, start, length));
            hasCategory = false;
        } else if (hasPubDate) {
            portada.setPubDate(new String(ch, start, length));
            hasPubDate = false;
        } else if (hasAuthor) {
            portada.setAuthor(new String(ch, start, length));
            hasAuthor = false;
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName) {
        if (qName.equalsIgnoreCase("item")) {
            //Añadimos un objeto portada a la lista
            portadaList.add(portada);

        }
    }
}