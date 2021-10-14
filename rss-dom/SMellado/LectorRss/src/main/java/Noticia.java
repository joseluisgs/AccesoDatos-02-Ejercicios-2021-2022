import lombok.Data;

import java.io.Serializable;

@Data
    public class Noticia implements Serializable {
        private String alumnos;
        private String alumno;
        private String datos;
        private String nombre;
        private String apellido;
        private String dni;
        private String comentario;

    @Override
    public String toString() {
        return "Noticia{" +
                "alumnos='" + alumnos + '\'' +
                ", alumno='" + alumno + '\'' +
                ", datos='" + datos + '\'' +
                ", nombre='" + nombre + '\'' +
                ", apellido='" + apellido + '\'' +
                ", dni='" + dni + '\'' +
                ", comentario='" + comentario + '\'' +
                '}';
    }
}
