package com.madirex;

import com.madirex.rss.RSSController;

public class App {
    public static void main(String[] args) {
        System.out.println("*** LECTOR RSS ***");
        RSSController rss = new RSSController("src/main/resources/rss.xml");
        rss.mostrar();
    }

}
