package repositories;

import entities.Animal;
import entities.Consulta;
import entities.Veterinario;
import factories.ConexaoFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class ConsultaRepository {

    private final String INSERIR = String.join(
            "\n",
            "insert into consulta (horario,veterinario_codigo,animal_codigo)",
            "values (?, ?, ?)"
    );

    private final String LISTAR = String.join(
            "\n",
            "select c.codigo, c.horario, a.codigo, a.nome, a.peso, v.codigo, v.nome, v.cpf, v.crmv ",
            "from consulta c ",
            "inner join consulta e on a.codigo = c.animal_codigo",
            "inner join consulta e on v.codigo = c.veterinario_codigo",

            "order by c.codigo"
    );

    public void salvar(Consulta consulta) {
        try (Connection conexao = ConexaoFactory.getConexao();
             PreparedStatement comando = conexao.prepareStatement(INSERIR);) {

            comando.setTime(1, consulta.getHorario());
            comando.setInt(2, consulta.getVeterinario().getCodigo());
            comando.setInt(2, consulta.getAnimal().getCodigo());


            comando.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Consulta> listar() {
        try (Connection conexao = ConexaoFactory.getConexao();
             PreparedStatement comando = conexao.prepareStatement(LISTAR);
             ResultSet resultado = comando.executeQuery();) {

            List<Consulta> consultas = new ArrayList<>();

            while (resultado.next()) {
                Veterinario veterinario = new Veterinario();
                veterinario.setCodigo(resultado.getInt("v.codigo"));
                veterinario.setNome(resultado.getString("v.nome"));
                veterinario.setCpf(resultado.getString("v.cpf"));
                veterinario.setCrmv(resultado.getString("v.crmv"));

                Animal animal = new Animal();
                animal.setCodigo(resultado.getInt("a.codigo"));
                animal.setNome(resultado.getString("a.nome"));
                animal.setPeso(resultado.getDouble("a.peso"));

                Consulta consulta = new Consulta();
                consulta.setCodigo(resultado.getInt("c.codigo"));
                consulta.setHorario(resultado.getTime("c.horario"));
                consulta.setAnimal(animal);
                consulta.setVeterinario(veterinario);

                consultas.add(consulta);
            }

            return consultas;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) {

        Animal animal = new Animal();
        animal.setCodigo(1);

        Veterinario veterinario = new Veterinario();
        veterinario.setCodigo(1);

        Consulta consulta = new Consulta();
        consulta.setHorario();
        consulta.setAnimal(animal);
        consulta.setVeterinario(veterinario);

        ConsultaRepository consultaRepository = new ConsultaRepository();
        consultaRepository.salvar(consulta);

        consultaRepository.listar();
    }


}
