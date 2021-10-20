package model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Band {
        private int id;
        private String name;
        private String origin;
        private int periodActivity;
        private String genre;
}
