package util;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
public class Noticias {
    private List<Noticia> news;

    public Noticias() {
        news = new ArrayList();
    }

    public void imprimirNoticias(List<Noticia> noticias) {
        for (Noticia nt : noticias
        ) {
            System.out.println(nt.getTitle());
            if (nt.getBody() == null) {
                System.out.println("\t\t" + nt.getBodyopt());
            } else {
                System.out.println("\t" + nt.getBody());
            }
            System.out.println("\t\t - Fecha de publicación: "+ nt.getPubDate());
            //System.out.println("\t\t-Autor-Autores: " + nt.getAuthor());
            System.out.println("\t\t\t- Para más información, consulata el link de la publicación: " + nt.getLink());
            System.out.println("\n\n");
        }
    }
}
