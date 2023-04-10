package entities;

import lombok.Data;

import java.sql.Time;
import java.time.LocalDateTime;

@Data

public class Consulta {

    private Integer codigo;

    private Time horario;

    private Veterinario veterinario;

    private Animal animal;

}
