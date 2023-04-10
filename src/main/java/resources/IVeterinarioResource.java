package resources;


import entities.Veterinario;
import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;
import jakarta.jws.WebService;

import java.util.List;

@WebService
public interface IVeterinarioResource {

    @WebMethod
    void salvarVeterinario(@WebParam(name = "veterinario") Veterinario veterinario);

    @WebMethod
    List<Veterinario> listarVeterinario();



}
