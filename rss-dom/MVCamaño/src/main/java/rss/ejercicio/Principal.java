package rss.ejercicio;

public class Principal {
    public static void main(String[] args) {

        String PATH = "C:\\Users\\mario\\IdeaProjects\\RSS\\src\\main\\resources\\portada.xml";
        DOMController dom = new DOMController(PATH);
        dom.portada();

    }
}
