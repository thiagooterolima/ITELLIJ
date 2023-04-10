package resources;


import entities.Animal;
import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;
import jakarta.jws.WebService;

import java.util.List;

@WebService
public interface IAnimalResource {

    @WebMethod
    void salvarAnimal(@WebParam(name = "animal") Animal animal);

    @WebMethod
    List<Animal> listarAnimal();

}
