package resources;


import entities.Veterinario;
import jakarta.jws.WebService;
import repositories.VeterinarioRepository;

import java.util.List;

@WebService(endpointInterface = "resources.IVeterinarioResource")

public class VeterinarioResource implements IVeterinarioResource {

    private VeterinarioRepository veterinarioRepository = new VeterinarioRepository();
    @Override
    public void salvarVeterinario(Veterinario veterinario) {
        veterinarioRepository.salvar(veterinario);
    }

    @Override
    public List<Veterinario> listarVeterinario() {
        List<Veterinario> veterinarios = veterinarioRepository.listar();
        return  veterinarios;
    }

}
