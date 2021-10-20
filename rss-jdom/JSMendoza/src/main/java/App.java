import model.Band;
import org.jdom2.JDOMException;
import util.JDOMController;

import java.io.File;
import java.io.IOException;

public class App {
    public static void main(String[] args) {
        String INPUT_XML = System.getProperty("user.dir") + File.separator + "data" + File.separator + "bands.xml";
        String OUTPUT_XML = System.getProperty("user.dir") + File.separator + "data" + File.separator + "bands_update" +
                ".xml";

        System.out.println(INPUT_XML);

        try {
            System.out.println("Cargamos nuestros Datos usando JDOM desde fichero");
            JDOMController controller = JDOMController.getInstance(INPUT_XML);
            controller.loadData();

            System.out.println("Exportamos los datos a un XML");
            controller.printXML();
            controller.writeXMLFile(OUTPUT_XML);
            System.out.println();

            System.out.println("*** JDOM XML *** ");
            System.out.println("Listado");
            System.out.println("Todos");
            controller.getBands().forEach(System.out::println);

            System.out.println("Todos Mayores de 40 de carrera");
            controller.getBands().stream().filter(user -> user.getPeriodActivity() >= 40).forEach(System.out::println);
            System.out.println();

            System.out.println("Operaciones CRUD");

            System.out.println("Iniciamos un modelo de datos vacío y lo rellenamos nosotros");
            controller.initData();
            controller.addBand(new Band(1, "Nirvana", "Seattle", 7, "Grunge"));
            controller.addBand(new Band(2, "Alice in Chains", "Seattle", 31, "Grunge"));
            controller.addBand(new Band(3, "Pearl Jam", "Seattle", 31, "Grunge"));
            controller.addBand(new Band(4,"Soundgarden","Seattle",22,"Grunge"));
            controller.printXML();
            controller.writeXMLFile(OUTPUT_XML);

            System.out.println("Operaciones XPATH");
            System.out.println("Obtenemos todos los nombres");
           controller.getAllnames().forEach(System.out::println);
            System.out.println("Obtenemos todos los Ids");
           controller.getAllIds().forEach(System.out::println);
            System.out.println("Obtenemos el nombre del 2º elemento");
            System.out.println(controller.getGenre(3));
            System.out.println("Obtenemos el nombre del elemento con id 3M");
            System.out.println(controller.getNameById("3M"));
        } catch (IOException | JDOMException e) {
            System.err.println("Error:"+e.getMessage());
        }
    }
}
