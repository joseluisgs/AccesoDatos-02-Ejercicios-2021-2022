import org.jdom2.JDOMException;

import java.io.File;
import java.io.IOException;

public class App {

    public static void main(String[] args) {
    String INPUT_XML = System.getProperty("user.dir") + File.separator + "Fichero" + File.separator + "alumno.xml";
    String OUTPUT_XML = System.getProperty("user.dir") + File.separator + "Fichero" + File.separator + "alumno_actualizado.xml";

        System.out.println(INPUT_XML);

        try {
            System.out.println("Cargamos nuestros Datos usando JDOM desde fichero");
            ControladorJdom controller = ControladorJdom.getInstance(INPUT_XML);
            controller.loadData();
            System.out.println("Sacamos a XML");
            controller.printXML();
            controller.writeXMLFile(OUTPUT_XML);
            System.out.println();
            System.out.println("--------------------------------------------------------------------------------------");
            System.out.println("Sacamos los alumnos de nuestro fichero:");
            controller.getAlumnos().forEach(System.out::println);
            System.out.println("--------------------------------------------------------------------------------------");
            System.out.println("Creamos nuevos alumnos a partir de 0");
            controller.initData();
            controller.addUser(new Alumno(1, "Saul", "Mellado", "000000001", "Estudiante"));
            controller.addUser(new Alumno(2, "Pedro", "Garcia", "000000002", "Trabajador"));
            controller.addUser(new Alumno(3, "Raquel", "Herrera", "000000003", "Estudiante"));
            controller.printXML();
            controller.writeXMLFile(OUTPUT_XML);
            System.out.println("--------------------------------------------------------------------------------------");
            System.out.println("Añadimos el campo curso y eliminamos el campo Apellido");
            controller.addElement("curso", "2ºDAM");
            controller.deleteElement("apellido");
            controller.writeXMLFile(OUTPUT_XML);
            controller.printXML();
            System.out.println();
            System.out.println("--------------------------------------------------------------------------------------");
            System.out.println("Sacamos solo los nombres usando XPATH");
            controller.getNombres().forEach(System.out::println);

        } catch (IOException | JDOMException e) {
            System.err.println("ERROR:" + e.getMessage());
        }

    }
}
