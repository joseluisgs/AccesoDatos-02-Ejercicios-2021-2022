package Lector.SAX;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ControladorNoticias extends DefaultHandler {

    private boolean titulo = false;
    private boolean categoria = false;
    private boolean description = false;
    private boolean fechaPublicacion = false;

    public Noticias news = new Noticias();
    public List<Noticias> listaNoticia = null;

    public List<Noticias> getNews() {
        return listaNoticia;
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        if (listaNoticia == null)
            listaNoticia = new ArrayList<>();
        if (qName.equalsIgnoreCase("item")) {
            news = new Noticias();
        } else if (qName.equalsIgnoreCase("title")) {
            titulo = true;
        } else if (qName.equalsIgnoreCase("category")) {
            categoria = true;
        } else if (qName.equalsIgnoreCase("description")) {
            description = true;
        } else if (qName.equalsIgnoreCase("pubDate")) {
            fechaPublicacion = true;
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        if (qName.equalsIgnoreCase("item")) {
            listaNoticia.add(news);
        }
    }
    @Override
    public void characters(char ch[], int start, int length) {
        if (titulo) {
            news.setTitulo(new String(ch, start, length));
            titulo = false;
        } else if (categoria) {
            news.setCategoria(new String(ch, start, length));
            categoria = false;
        } else if (description) {
            news.setDescripcion(new String(ch, start, length));
            description = false;
        } else if (fechaPublicacion) {
            news.setFechapublicacion(new String(ch, start, length));
            fechaPublicacion = false;
        }
    }
}
