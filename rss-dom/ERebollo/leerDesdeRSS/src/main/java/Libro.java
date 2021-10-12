import lombok.Builder;
import lombok.Data;
import lombok.NonNull;

@Builder
@Data
public class Libro {

    @NonNull private String titulo, autor, numHojas;
}
