package gm.zona_fit.servicio;

import gm.zona_fit.modelo.Cliente;
import gm.zona_fit.repositorio.ClienteRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service // Anotar como la anotacion servicio de Spring 
public class ClienteServicio implements IClienteServicio{

    @Autowired   //Inyectar dependencia ClienteRepositorio (Todos los metodos)
    private ClienteRepositorio clienteRepositorio;

    @Override
    public List<Cliente> listarClientes() {
        List<Cliente> clientes = clienteRepositorio.findAll(); // Ejemplo implementacion para listar todos los clientes
        return clientes;
    }

    @Override
    public Cliente buscarClientePorId(Integer idCliente) {
        Cliente cliente = clienteRepositorio.findById(idCliente).orElse(null);  // Ejemplo implementacion buscar por id 
        return cliente;
    }

    @Override
    public void guardarCliente(Cliente cliente) {
        clienteRepositorio.save(cliente); // Ejemplo implementacion guardar un cliente 
    }

    @Override
    public void eliminarCliente(Cliente cliente) {
        clienteRepositorio.delete(cliente); // Ejemplo eliminar un cliente .
    }
}
