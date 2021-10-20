import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

@Data
@NoArgsConstructor
public class Noticia {
    private String titulo, uri, descripcion, contenido, fecha;
    private List<String> categorias;

    public String toString(){
        return "ultima hora: "+
                titulo +"\n"+
                "link: "+uri+"\n"+
                "\n"+descripcion+"\n"+
                contenido+"\n"+fecha;
    }
}
