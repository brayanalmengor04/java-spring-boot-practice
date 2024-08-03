package gm.zona_fit.modelo;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

@Entity // Capa Entity anotacion la clase es una entidad
@Data   // Esto simplifica la generacion de Getters and Setters            LOMBOK
@NoArgsConstructor // Un constructor vacio sin argumentos      
@AllArgsConstructor // Un Constructor con todos los argumentos    
@ToString   // Metodo ToString  
@EqualsAndHashCode   // Comparar Equals y Hascode
public class Cliente { 
    
    @Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY) //Asignacion de estrategia Generaion tipo Identy 
    private Integer id;
    private String nombre;
    private String apellido;
    private Integer membresia;
}
