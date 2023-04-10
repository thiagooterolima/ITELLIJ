package repositories;

import entities.Veterinario;
import factories.ConexaoFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class VeterinarioRepository {

    private final String INSERIR = String.join(
            "\n",
            "insert into veterinario (nome, cpf, crmv)",
            "values (?,?,?)"
    );

    private final String LISTAR = String.join(
            "\n",
            "select *",
            "from veterinario",
            "order by nome"
    );

    public void salvar(Veterinario veterinario) {
        try (Connection conexao = ConexaoFactory.getConexao();
             PreparedStatement comando = conexao.prepareStatement(INSERIR);) {

            comando.setString(1, veterinario.getNome());
            comando.setString(2, veterinario.getCpf());
            comando.setString(3, veterinario.getCrmv());

            comando.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Veterinario> listar() {
        try (Connection conexao = ConexaoFactory.getConexao();
             PreparedStatement comando = conexao.prepareStatement(LISTAR);
             ResultSet resultado = comando.executeQuery();) {

            List<Veterinario> veterinarios = new ArrayList<>();

            while (resultado.next()) {
                Veterinario veterinario = new Veterinario();
                veterinario.setCodigo(resultado.getInt("codigo"));
                veterinario.setNome(resultado.getString("nome"));
                veterinario.setCpf(resultado.getString("cpf"));
                veterinario.setCrmv(resultado.getString("crmv"));

                veterinarios.add(veterinario);
            }

            return veterinarios;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) {
        Veterinario veterinario = new Veterinario();
        veterinario.setNome("Luiz");
        veterinario.setCpf("050.009.123-98");
        veterinario.setCrmv("77820-VP");


        VeterinarioRepository veterinarioRepository = new VeterinarioRepository();
        veterinarioRepository.salvar(veterinario);

        veterinarioRepository.listar();
    }

}
