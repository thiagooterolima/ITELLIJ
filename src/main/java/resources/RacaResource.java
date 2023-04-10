package resources;


import entities.Raca;
import jakarta.jws.WebService;
import repositories.RacaRepository;

import java.util.List;

@WebService(endpointInterface = "resources.IRacaResource")

public class RacaResource implements  IRacaResource {

    private RacaRepository racaRepository = new RacaRepository();
    @Override
    public void salvarRaca(Raca raca) {
        racaRepository.salvar(raca);
    }

    @Override
    public List<Raca> listarRaca() {
        List<Raca> racas = racaRepository.listar();
        return  racas;
    }

}
