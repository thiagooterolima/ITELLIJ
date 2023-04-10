package resources;

import entities.Proprietario;
import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;
import jakarta.jws.WebService;

import java.util.List;

@WebService
public interface IProprietarioResource {


    @WebMethod
    void salvarProprietario(@WebParam(name = "proprietario")Proprietario proprietario);

    @WebMethod
    List<Proprietario> listarProprietario();

}
