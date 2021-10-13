package dam.dom.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class News {
    private String title;
    private String description;
    private String creator;
    private String link;
    private String category;
    private String guid;
}
