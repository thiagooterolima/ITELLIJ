import entities.Tipo_Animal;
import jakarta.xml.ws.Endpoint;
import resources.*;

public class Aplicacao {

    public static void main(String[] args) {
        Endpoint.publish(
                "http://127.0.0.1:8080/proprietario-ws",
                new ProprietarioResource()
        );

        Endpoint.publish(
                "http://127.0.0.1:8080/tipo_animal-ws",
                new Tipo_AnimalResource()
        );

        Endpoint.publish(
                "http://127.0.0.1:8080/veterinario-ws",
                new VeterinarioResource()
        );

        Endpoint.publish(
                "http://127.0.0.1:8080/raca-ws",
                new RacaResource()
        );

        Endpoint.publish(
                "http://127.0.0.1:8080/animal-ws",
                new AnimalResource()
        );

        Endpoint.publish(
                "http://127.0.0.1:8080/consulta-ws",
                new ConsultaResource()
        );

    }
}
