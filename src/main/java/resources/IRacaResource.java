package resources;

import entities.Raca;
import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;
import jakarta.jws.WebService;

import java.util.List;

@WebService
public interface IRacaResource {

    @WebMethod
    void salvarRaca(@WebParam(name = "raca") Raca raca);

    @WebMethod
    List<Raca> listarRaca();


}
