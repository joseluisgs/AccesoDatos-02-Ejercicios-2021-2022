import java.io.File;

public class Main {

    public static void main(String[] args) {
        String XMLInput = System.getProperty("user.dir")+ File.separator+"data"+File.separator+"biblioteca.xml";
        String XMLOutput = System.getProperty("user.dir")+ File.separator+"data"+File.separator+"biblioteca2.xml";

        System.out.println("Cargamos nuestros Datos y DOM desde fichero");
        DOMReader dr = DOMReader.getInstance(XMLInput);

        System.out.println("aniadimos un nuevo libro que acabamos de comprar");

        Libro viajesFantasticos = Libro.builder().
                titulo("Viajes Fantasticos").
                autor("Geronimo Stilton").
                numHojas("238").
                build();

        dr.aniadirLibro(viajesFantasticos);

        System.out.println("creamos un nuevo xml que contenga el nuevo libro");
        dr.crearXML(XMLOutput);
    }
}
