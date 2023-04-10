package repositories;

import entities.Raca;
import entities.Tipo_Animal;
import factories.ConexaoFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RacaRepository {

    private final String INSERIR = String.join(
            "\n",
            "insert into raca (nome, tipo_animal_codigo)",
            "values (?, ?)"
    );

    private final String LISTAR = String.join(
            "\n",
            "select r.codigo, r.nome, t.codigo, t.descricao",
            "from raca r ",
            "inner join tipo_animal e on t.codigo = r.tipo_animal_codigo",
            "order by r.nome"
    );

    public void salvar(Raca raca) {
        try (Connection conexao = ConexaoFactory.getConexao();
             PreparedStatement comando = conexao.prepareStatement(INSERIR);) {

            comando.setString(1, raca.getNome());
            comando.setInt(2, raca.getTipo_animal().getCodigo());


            comando.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Raca> listar() {
        try (Connection conexao = ConexaoFactory.getConexao();
             PreparedStatement comando = conexao.prepareStatement(LISTAR);
             ResultSet resultado = comando.executeQuery();) {

            List<Raca> racas = new ArrayList<>();

            while (resultado.next()) {
                Tipo_Animal tipo_animal = new Tipo_Animal();
                tipo_animal.setCodigo(resultado.getInt("t.codigo"));
                tipo_animal.setDescricao(resultado.getString("t.descricao"));


                Raca raca = new Raca();
                raca.setCodigo(resultado.getInt("r.codigo"));
                raca.setNome(resultado.getString("r.nome"));
                raca.setTipo_animal(tipo_animal);

                racas.add(raca);
            }
            return racas;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
  }

    public static void main(String[] args) {

        Tipo_Animal tipo_animal = new Tipo_Animal();
        tipo_animal.setCodigo(1);

        Raca raca = new Raca();
        raca.setNome("Pastor");
        raca.setTipo_animal(tipo_animal);

        RacaRepository racaRepository = new RacaRepository();
        racaRepository.salvar(raca);

        racaRepository.listar();
    }

}
