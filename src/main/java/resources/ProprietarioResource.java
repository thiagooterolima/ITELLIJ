package resources;

import entities.Proprietario;
import jakarta.jws.WebService;
import repositories.ProprietarioRepository;

import java.util.List;

@WebService(endpointInterface = "resources.IProprietarioResource")
public class ProprietarioResource implements IProprietarioResource {

    private ProprietarioRepository proprietarioRepository = new ProprietarioRepository();
    @Override
    public void salvarProprietario(Proprietario proprietario) {
        proprietarioRepository.salvar(proprietario);
    }

    @Override
    public List<Proprietario> listarProprietario() {
        List<Proprietario> proprietarios = proprietarioRepository.listar();
        return  proprietarios;
    }

}
