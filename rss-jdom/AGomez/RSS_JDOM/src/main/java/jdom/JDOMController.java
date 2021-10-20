package jdom;

import jdom.model.Noticia;
import lombok.NonNull;
import org.jdom2.*;
import org.jdom2.filter.Filters;
import org.jdom2.input.SAXBuilder;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;
import org.jdom2.xpath.XPathExpression;
import org.jdom2.xpath.XPathFactory;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class JDOMController {

    private static JDOMController controller;
    private final String uri;
    private Document data;

    private JDOMController(String uri) {
        this.uri = uri;
    }

    public static JDOMController getInstance(@NonNull String uri) {
        if (controller == null)
            controller = new JDOMController(uri);
        return controller;
    }

    private static Element crearNoticia(Noticia noticia) {
        Element elemetoNoticia = new Element("noticia");
        elemetoNoticia.setAttribute(new Attribute("id", Integer.toString(noticia.getId())));
        elemetoNoticia.addContent(new Element("titulo").setText(noticia.getTitulo()));
        elemetoNoticia.addContent(new Element("link").setText(noticia.getLink()));
        elemetoNoticia.addContent(new Element("creador").setText(noticia.getCreador()));
        elemetoNoticia.addContent(new Element("descripcion").setText(noticia.getDescripcion()));
        elemetoNoticia.addContent(new Element("fecha_publicacion").setText(noticia.getFechaPublicacion()));
        elemetoNoticia.addContent(new Element("categorias").setText(noticia.getCategorias().toString()));

        return elemetoNoticia;

    }


    public void loadData() throws IOException, JDOMException {

        SAXBuilder builder = new SAXBuilder();
        File xmlFile = new File(this.uri);
        this.data = (Document) builder.build(this.uri);

    }

    //Creacion de documento vacío con elemento raiz.
    public void initData() {
        this.data = new Document();
        this.data.setRootElement(new Element("noticias"));
    }

    public void writeXMLFile(String uri) throws IOException {
        XMLOutputter xmlOutput = new XMLOutputter();
        xmlOutput.setFormat(Format.getPrettyFormat());
        xmlOutput.output(this.data, new FileWriter(uri));
        System.out.println("Fichero XML generado con éxito");
    }

    public List<Noticia> getNoticias() {

        Element rootNode = this.data.getRootElement();
        List<Element> listchanel = rootNode.getChildren("channel");
        List<Element> listanoticias = listchanel.get(0).getChildren("item");

        int random= (int) (Math.random() * 20);
        List<Noticia> noticias = new ArrayList<>();

        listanoticias.forEach(noticiaElemento -> {

                Noticia noticia = new Noticia();
                noticia.setId((int) (Math.random() * listanoticias.size()));
                noticia.setTitulo(noticiaElemento.getChildText("title"));
                noticia.setLink(noticiaElemento.getChildText("link"));
                noticia.setCreador(noticiaElemento.getChildText("dc:creator"));
                noticia.setDescripcion(noticiaElemento.getChildText("description"));
                noticia.setFechaPublicacion(noticiaElemento.getChildText("pubDate"));
                noticia.setCategorias(Collections.singletonList(noticiaElemento.getChildText("category")));
                noticias.add(noticia);
                noticia.printData();

        });
        return noticias;
    }


    //Métodos demostrativos de que podemos editar el archivo.
    public void añadirNoticia(Noticia noticia) {
        Element root = (Element) this.data.getRootElement();
        root.addContent(crearNoticia(noticia));
    }

    public void añadirCampo(String etiqueta, String valor) {
        Element rootElement = this.data.getRootElement();
        List<Element> listaNoticiaElemento = rootElement.getChildren("noticia");
        listaNoticiaElemento.forEach(noticiaElemento -> {
            noticiaElemento.addContent(new Element(etiqueta).setText(valor));
        });
    }


    // Consultas con XPATH

    public List<String> getCreadores() {
        XPathFactory xpath = XPathFactory.instance();
        XPathExpression<Element> expr = xpath.compile("//noticia/creador", Filters.element());
        List<Element> noticias = expr.evaluate(this.data);
        List<String> creadores = new ArrayList<String>();
        noticias.forEach(user -> creadores.add(user.getValue().trim()));
        return creadores;
    }



    public String getNoticiaPorId(int id) {
        XPathFactory xpath = XPathFactory.instance();
        XPathExpression<Element> expr = xpath.compile("//noticia[@id='" + id + "']/titulo", Filters.element());
        Element titulo = expr.evaluateFirst(this.data);
        return titulo.getValue();
    }
}