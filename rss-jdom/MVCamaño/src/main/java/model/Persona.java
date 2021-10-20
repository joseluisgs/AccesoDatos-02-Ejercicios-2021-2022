package model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Persona {
    private int id;
    private String firstName;
    private String lastName;
    private int age;
    private String direccion;

    @Override
    public String toString() {
        return "Persona{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", age=" + age +
                ", direccion='" + direccion + '\'' +
                '}';
    }


}
