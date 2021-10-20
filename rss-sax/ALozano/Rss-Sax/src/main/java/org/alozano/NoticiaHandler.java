package org.alozano;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.util.ArrayList;
import java.util.List;

public class NoticiaHandler extends DefaultHandler {

    private boolean hasTitulo;
    private boolean hasCategoria;
    private boolean hasDescripcion;
    private boolean hasFecha;
    private boolean hasEnlace;
    private boolean hasAuthor;

    private boolean enEntry = false;
    private List<Noticia> noticiasList = null;
    private Noticia noticias = null;

    public List<Noticia> getNoticias() {
        return noticiasList;
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {

        if (qName.equalsIgnoreCase("item")){
            enEntry = true;
        }
        if(enEntry){
            if (noticiasList == null)
                noticiasList = new ArrayList<>();
            if (qName.equalsIgnoreCase("item")) {
                noticias = new Noticia();
            } else if (qName.equalsIgnoreCase("title")) {
                hasTitulo = true;
            } else if (qName.equalsIgnoreCase("category")) {
                hasCategoria = true;
            } else if (qName.equalsIgnoreCase("description")) {
                hasDescripcion = true;
            } else if (qName.equalsIgnoreCase("pubDate")) {
                hasFecha = true;
            } else if (qName.equalsIgnoreCase("link")) {
                hasEnlace = true;
            } else if (qName.equalsIgnoreCase("dc:creator")) {
                  hasAuthor = true;
            }
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        if (qName.equalsIgnoreCase("item")) {
            noticiasList.add(noticias);
        }
    }

    @Override
    public void characters(char ch[], int start, int length) throws SAXException {
        if (hasTitulo) {
            noticias.setTitulo(new String(ch, start, length));
            hasTitulo = false;
        } else if (hasCategoria) {
            noticias.setCategoria(new String(ch, start, length));
            hasCategoria = false;
        } else if (hasDescripcion) {
            noticias.setDescripcion(new String(ch, start, length));
            hasDescripcion = false;
        }else if (hasFecha) {
            noticias.setFecha(new String(ch, start, length));
            hasFecha = false;
        } else if (hasEnlace) {
            noticias.setEnlace(new String(ch, start, length));
            hasEnlace = false;
        } else if (hasAuthor) {
            noticias.setAuthor(new String(ch, start, length));
            hasAuthor = false;
        }
    }
}