import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;

/**
 * apunte: inspiracion en el repositorio "https://github.com/joseluisgs/AccesoDatos-02-2021-2022.git" para poder recorrer los nodos en un for,
 * (no se me ocurrio usar un for de esa manera) y cambiar a la hora de meter calores dentro de la clase noticia un metodo mal puesto (getNodeValue() -> getTextValue())
 *
 * el uso de las clases para leer el xml lo saque de lo aprendido en el pull request malo que hice, que use las mismas clases para almacenar un xml, asi que no hay fallo sin
 * aprendizaje
 */
public class RSSReader {

    private String uri;

    private static RSSReader rr=null;

    public static RSSReader getInstance(String uri) {
        if(rr==null){
            rr=new RSSReader(uri);
        }
        return rr;
    }

    private RSSReader(String uri) {
        this.uri=uri;
    }

    /**
     * creamos un DocumentBuilderFactory con el que crearemos un DocumentBuilder que usaremos para poder leer el rss (en forma de xml) y almacenar
     * el DOM de este en la variable Document, con la que crearemos una lista de nodos "item" que recorreremos junto a sus nodos hijos, creando y printeando
     * una noticia por cada nodo "item" de la lista, para ello haremos uso de una clase Noticia en la ue almacenaremos de forma temporal y ordenada la informacion
     * de los nodos hijos para luego poder printearla con un solo toString cuando acabemos de recorrer el nodo "item" completo
     */
    public void leerRSS(){
        DocumentBuilderFactory dbFactory=DocumentBuilderFactory.newInstance();
        DocumentBuilder db;
        Document DOMDocument;

        try{

            db = dbFactory.newDocumentBuilder();
            DOMDocument = db.parse(uri);

            NodeList items = DOMDocument.getElementsByTagName("item");

            for (int i=0;i< items.getLength();i++){
                Node nodo = items.item(i);
                System.out.println("Noticia "+(i+1));

                String title= "No hay titulo discponible para mostrar",
                        url= "No hay enlace discponible para mostrar",
                        fecha= "No hay fecha discponible para mostrar",
                        description = "No hay descripcion discponible para mostrar",
                        content = "No hay contenido discponible para mostrar";

                for (Node node = nodo.getFirstChild();node!=null;node = node.getNextSibling()){


                    switch (node.getNodeName()){
                        case "title":
                            title=node.getTextContent();
                            break;
                        case "link":
                            url=node.getTextContent();
                            break;
                        case "description":
                            description=node.getTextContent();
                            break;
                        case "pubDate":
                            fecha=node.getTextContent();
                            break;
                        case "content:encode":
                            content=node.getTextContent();
                            break;
                        default:
                    }
                }
                Noticia noticia = Noticia.builder().titulo(title).enlace(url).fechaPubliacion(fecha).descripcion(description).contenido(content).build();
                System.out.println(noticia.toString());
            }

        } catch (ParserConfigurationException | IOException | SAXException e) {
            e.printStackTrace();
        }
    }
}
