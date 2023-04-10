package resources;


import entities.Tipo_Animal;
import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;
import jakarta.jws.WebService;

import java.util.List;

@WebService
public interface ITipo_AnimalResource {

    @WebMethod
    void salvarTipo_Animal(@WebParam(name = "tipo_animal") Tipo_Animal tipo_animal);

    @WebMethod
    List<Tipo_Animal> listarTipo_Animal();

}
