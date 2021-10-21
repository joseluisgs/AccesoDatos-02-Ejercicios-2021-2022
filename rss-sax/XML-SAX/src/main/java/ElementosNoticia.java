/*
    Creamos una enumeracion de noticias en la que metemos sus elementos hijos
 */
public enum ElementosNoticia {
    ITEM("itemRaiz"), TITLE("title"), IMAGE("image"), LINK("link"), AUTHOR("author"), GUID("guid"), PUBDATE("pubDate");

    private String name;

    private ElementosNoticia(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

}

