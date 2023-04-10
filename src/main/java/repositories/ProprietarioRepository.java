package repositories;

import entities.Proprietario;
import factories.ConexaoFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProprietarioRepository {

    private final String INSERIR = String.join(
            "\n",
            "insert into proprietario (nome, cpf, celular)",
            "values (?, ?, ?)"
    );

    private final String LISTAR = String.join(
            "\n",
            "select *",
            "from proprietario",
            "order by nome"
    );

    public void salvar(Proprietario proprietario) {
        try (Connection conexao = ConexaoFactory.getConexao();
             PreparedStatement comando = conexao.prepareStatement(INSERIR);) {

            comando.setString(1, proprietario.getNome());
            comando.setString(2, proprietario.getCpf());
            comando.setString(3, proprietario.getCelular());

            comando.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Proprietario> listar() {
        try (Connection conexao = ConexaoFactory.getConexao();
             PreparedStatement comando = conexao.prepareStatement(LISTAR);
             ResultSet resultado = comando.executeQuery();) {

            List<Proprietario> proprietarios = new ArrayList<>();

            while (resultado.next()) {
                Proprietario proprietario = new Proprietario();
                proprietario.setCodigo(resultado.getInt("codigo"));
                proprietario.setCelular(resultado.getString("cpf"));
                proprietario.setNome(resultado.getString("nome"));
                proprietario.setCelular(resultado.getString("celular"));

                proprietarios.add(proprietario);
            }

            return proprietarios;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) {
        Proprietario proprietario = new Proprietario();
        proprietario.setNome("Felipe");
        proprietario.setCpf("023.115.599-35");
        proprietario.setCelular("(29)9-9322-4471");


        ProprietarioRepository proprietarioRepository = new ProprietarioRepository();
        proprietarioRepository.salvar(proprietario);

        proprietarioRepository.listar();
    }

}
