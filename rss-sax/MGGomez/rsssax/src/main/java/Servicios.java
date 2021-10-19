import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
public class Servicios {
    public void imprimirNoticia(List <Noticia> noticias) {
        for (Noticia noticia : noticias) {
            System.out.println(noticia.getTitle());
            if (noticia.getBody() == null) {
                System.out.println("\t\t" + noticia.getBodyopt());
            } else {
                System.out.println("\t" + noticia.getBody());
            }

            System.out.println("\t\t-Autor-Autores: " + noticia.getAuthor());
            System.out.println("\t\t\t- Para más información, consulata el link de la publicación: " + noticia.getLink());
            System.out.println("\n\n");
        }
    }
}
