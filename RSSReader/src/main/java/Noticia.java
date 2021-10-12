import lombok.Builder;
import lombok.NonNull;

@Builder
public class Noticia {

    @NonNull private String titulo, enlace, descripcion, fechaPubliacion;
   private String contenido;

    public String toString(){

        return "\n"+titulo+"\n"+
                "uri: "+enlace+"\n"+
                descripcion+"\n"+
                contenido+"\n"+
                fechaPubliacion+"\n";
    }
}
