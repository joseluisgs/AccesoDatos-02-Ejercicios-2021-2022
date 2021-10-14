package com.madirex;


import com.madirex.sax.SAXController;
import org.xml.sax.SAXException;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;

public class App {
    public static void main(String[] args) {
        String INPUT_XML = System.getProperty("user.dir") + File.separator + "data" + File.separator + "rss.xml";

        System.out.println(INPUT_XML);
        try {
            SAXController controller = SAXController.getInstance(INPUT_XML);
            controller.loadData();

            System.out.println("Listando todos:");
            controller.getPosts().forEach(System.out::println);

            System.out.println("Posts creados el sábado:");
            controller.getPosts().stream().filter(post -> post.getPubDate().startsWith("Sat")).forEach(System.out::println);

            System.out.println();

        } catch (SAXException | ParserConfigurationException | IOException e) {
            System.err.println("ERROR:" + e.getMessage());
        }


    }
}