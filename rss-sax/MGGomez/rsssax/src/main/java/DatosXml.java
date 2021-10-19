public class DatosXml {

    public enum noticiasElement {

        PUBDATE("pubDate"), ITEM("item"), TITLE("title"),
        BODY("content:encoded"), BODYOPT("description"), AUTHOR("dc:creator"), LINK("link");
        private String name;

        private noticiasElement(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }

    }
}
