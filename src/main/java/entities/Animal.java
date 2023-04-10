package entities;

import lombok.Data;

@Data
public class Animal {

    private Integer codigo;

    private String nome;

    private Double peso;

    private Raca raca;

    private Proprietario proprietario;
}
