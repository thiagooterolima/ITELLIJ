package repositories;

import entities.Tipo_Animal;
import factories.ConexaoFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Tipo_AnimalRepository {
    private final String INSERIR = String.join(
            "\n",
            "insert into tipo_animal (descricao)",
            "values (?)"
    );

    private final String LISTAR = String.join(
            "\n",
            "select *",
            "from tipo_animal",
            "order by codigo"
    );

    public void salvar(Tipo_Animal tipo_animal) {
        try (Connection conexao = ConexaoFactory.getConexao();
             PreparedStatement comando = conexao.prepareStatement(INSERIR);) {

            comando.setString(1, tipo_animal.getDescricao());

            comando.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Tipo_Animal> listar() {
        try (Connection conexao = ConexaoFactory.getConexao();
             PreparedStatement comando = conexao.prepareStatement(LISTAR);
             ResultSet resultado = comando.executeQuery();) {

            List<Tipo_Animal> tipo_animals = new ArrayList<>();

            while (resultado.next()) {
                Tipo_Animal tipo_animal = new Tipo_Animal();
                tipo_animal.setCodigo(resultado.getInt("codigo"));
                tipo_animal.setDescricao(resultado.getString("descricao"));


                tipo_animals.add(tipo_animal);
            }

            return tipo_animals;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) {
        Tipo_Animal tipo_animal = new Tipo_Animal();
        tipo_animal.setDescricao("Agitado");

        Tipo_AnimalRepository tipo_animalRepository = new Tipo_AnimalRepository();
        tipo_animalRepository.salvar(tipo_animal);

        tipo_animalRepository.listar();
    }
}
