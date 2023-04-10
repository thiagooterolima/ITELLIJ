package resources;


import entities.Animal;
import jakarta.jws.WebService;
import repositories.AnimalRepository;

import java.util.List;

@WebService(endpointInterface = "resources.IAnimalResource")
public class AnimalResource implements  IAnimalResource{

    private AnimalRepository animalRepository = new AnimalRepository();
    @Override
    public void salvarAnimal(Animal animal) {
        animalRepository.salvar(animal);
    }

    @Override
    public List<Animal> listarAnimal() {
        List<Animal> animals = animalRepository.listar();
        return  animals;
    }

}
