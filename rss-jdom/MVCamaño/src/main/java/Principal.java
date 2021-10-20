import model.JDomController;
import model.Persona;
import org.jdom2.JDOMException;

import java.io.File;
import java.io.IOException;

public class Principal {
    public static void main(String[] args) {
        String INPUT_XML = "src" + File.separator + "main" + File.separator + "resources" + File.separator + "persona.xml";
        String OUTPUT_XML = "src" + File.separator + "main" + File.separator + "resources" + File.separator + "personaUpdate.xml";
        System.out.println("*** LECTOR DE NOTICIAS RSS ***");

        System.out.println(INPUT_XML);

        try {
            System.out.println("*** Cargamos nuestros Datos y DOM desde fichero ***");
            JDomController controller = JDomController.getInstance(INPUT_XML);
            controller.loadData();
            System.out.println("Exportamos los datos a un RSS");
            controller.printXML();
            controller.writeXMLFile(OUTPUT_XML);
            System.out.println();

            System.out.println("*** jDOM RSS *** ");

            System.out.println("Listando todos los elementos");
            controller.getPersonas().forEach(System.out::println);

            System.out.println("Se inicia el modelo vacío y se rellena con los datos pasados");
            controller.initData();
            controller.addPersona(new Persona(4, "Carlos", "Tévez", 45, "calle sagrada"));
            controller.addPersona(new Persona(5, "Sofía", "Reyes", 30, "Av. de las lágrimas"));
            controller.addPersona(new Persona(6, "Arturo", "Valls", 55, "Calle de Toledo"));
            controller.addPersona(new Persona(7, "Fernando", "Torres", 35, "calle del mejor"));

            controller.printXML();
            controller.writeXMLFile(OUTPUT_XML);

            System.out.println("Añadimos el elemento GUID");
            controller.addElement("Ciudad", "Madrid");

            System.out.println("Eliminamos direccion");
            controller.deleteElement("direccion");
            controller.writeXMLFile(OUTPUT_XML);
            controller.printXML();
            controller.loadData();
            System.out.println("*** Operaciones XPATH ***");
            System.out.println("-- Se obtienen todos los nombres --");
            //controller.getAllNames().forEach(System.out::println);
            System.out.println("-- Se obtienen todas las id --");
            //controller.getAllIds().forEach(System.out::println);
            System.out.println("-- Se obtiene el 5º elemento nombre --");
            //System.out.println(controller.getLastName(5));
        } catch (IOException | JDOMException e) {
            System.err.println("ERROR:" + e.getMessage());
        }
    }
}

