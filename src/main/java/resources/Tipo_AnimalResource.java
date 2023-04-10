package resources;


import entities.Tipo_Animal;
import jakarta.jws.WebService;
import repositories.Tipo_AnimalRepository;

import java.util.List;

@WebService(endpointInterface = "resources.ITipo_AnimalResource")
public class Tipo_AnimalResource implements ITipo_AnimalResource {

    private Tipo_AnimalRepository tipo_AnimalRepository = new Tipo_AnimalRepository();
    @Override
    public void salvarTipo_Animal(Tipo_Animal tipo_animal) {
        tipo_AnimalRepository.salvar(tipo_animal);
    }

    @Override
    public List<Tipo_Animal> listarTipo_Animal() {
        List<Tipo_Animal> tipo_animals = tipo_AnimalRepository.listar();
        return  tipo_animals;
    }
}
