import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;

public class Archivo {
    public void procesarDoc() {
        // CARGO EL FICHERO XML DE LAS NOTICIAS
        File archivoRss = new File("C:\\Users\\JAVIER\\Desktop\\DAM 2\\2DAM\\AccesoDatos\\rss.xml");
        // UTILIZAMOS TRY-CATCH PARA LAS EXCEPCIONES Y USAMOS LAS SIGUIENTES CLASES PARA "PARSEAR" EL FICHERO
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document documento = builder.parse(archivoRss);
            // documento.normalize(); sería usado para eliminar espacios en blanco y etiquetas contiguas en caso de no conocer el xml o formato de rss
            // SELECCIONAMOS LOS ELEMENTOS CUYA ETIQUETA SEA LA DE ITEM (CADA NOTICIA) Y LOS ALMACENAMOS EN UNA LISTA DE NODOS
            NodeList listaNodos = documento.getElementsByTagName("item");
            System.out.println("[[[[[ BIENVENIDO A TU PORTAL DE LECTURA DE PADEL ]]]]]\n\t\t [[[ Tienes " + listaNodos.getLength() + " noticias por leer ]]]");

            // PROCEDEMOS A LEER CADA ITEM SABIENDO LA ESTRUCTURA QUE TIENE EL ARCHIVO XML DEL RSS
            for (int i = 0; i < listaNodos.getLength(); i++) {
                Node numeroNodo = listaNodos.item(i);
                // CADA NOTICIA ES UN NODO, POR LO QUE PARA CADA NOTICIA SACAMOS SUS NODOS HIJOS
                if (numeroNodo.getNodeType() == Node.ELEMENT_NODE) {
                    Element elemento = (Element) numeroNodo;
                    // VAMOS DESCRIBIENDO EL ELEMENTO COGIENDO EL TEXTO DE LA ETIQUETA QUE COINCIDA CON EL NOMBRE BUSCADO
                    System.out.println("\n*** Noticia " + (i + 1) + " ***");
                    // DADO QUE ES TEXTO, USAMOS EL GET TEXT. PARA ATRIBUTOS SERIA EL GET ATRIBUTE...
                    System.out.println("* Titulo: " + elemento.getElementsByTagName("title").item(0).getTextContent());
                    System.out.println("* Autor: " + elemento.getElementsByTagName("author").item(0).getTextContent());
                    System.out.println("* Más información: " + elemento.getElementsByTagName("link").item(0).getTextContent());
                    System.out.println("* Acceso a fotografía: " + elemento.getElementsByTagName("image").item(0).getTextContent());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}

