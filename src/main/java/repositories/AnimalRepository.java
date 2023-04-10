package repositories;

import entities.Animal;
import entities.Proprietario;
import entities.Raca;
import factories.ConexaoFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AnimalRepository {

    private final String INSERIR = String.join(
            "\n",
            "insert into animal (nome,peso,raca_codigo,proprietario_codigo)",
            "values (?, ?, ?, ?)"
    );

    private final String LISTAR = String.join(
            "\n",
            "select a.codigo, a.nome, a.peso, p.codigo, p.nome, p.cpf, p.celular, r.codigo, r.nome",
            "from animal a ",
            "inner join proprietario e p.raca on p.codigo = a.proprietario_codigo,a.raca_codigo",
            "order by c.nome"
    );

    public void salvar(Animal animal) {
        try (Connection conexao = ConexaoFactory.getConexao();
             PreparedStatement comando = conexao.prepareStatement(INSERIR);) {

            comando.setString(1, animal.getNome());
            comando.setDouble(2, animal.getPeso());
            comando.setInt(3, animal.getProprietario().getCodigo());
            comando.setInt(4, animal.getRaca().getCodigo());

            comando.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Animal> listar() {
        try (Connection conexao = ConexaoFactory.getConexao();
             PreparedStatement comando = conexao.prepareStatement(LISTAR);
             ResultSet resultado = comando.executeQuery();) {

            List<Animal> animals = new ArrayList<>();

            while (resultado.next()) {
                Proprietario proprietario = new Proprietario();
                proprietario.setCodigo(resultado.getInt("p.codigo"));
                proprietario.setCpf(resultado.getString("p.cpf"));
                proprietario.setCelular(resultado.getString("p.celular"));

                Raca raca = new Raca();
                raca.setCodigo(resultado.getInt("r.codigo"));
                raca.setNome(resultado.getString("r.nome"));


                Animal animal = new Animal();
                animal.setCodigo(resultado.getInt("a.codigo"));
                animal.setPeso(resultado.getDouble("a.peso"));
                animal.setProprietario(proprietario);
                animal.setRaca(raca);

                animals.add(animal);
            }

            return animals;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) {

        Proprietario proprietario = new Proprietario();
        proprietario.setCodigo(2);

        Raca raca = new Raca();
        raca.setCodigo(2);

        Animal animal = new Animal();
        animal.setNome("Bili");
        animal.setPeso(10.4);
        animal.setRaca(raca);
        animal.setProprietario(proprietario);
        AnimalRepository animalRepository = new AnimalRepository();
        animalRepository.salvar(animal);

        animalRepository.listar();
    }

}
