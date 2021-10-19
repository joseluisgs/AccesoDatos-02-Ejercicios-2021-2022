import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Alumno {
    private int id;
    private String nombre;
    private String apellido;
    private String dni;
    private String comentario;
}
