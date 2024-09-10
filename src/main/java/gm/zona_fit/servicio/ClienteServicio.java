package gm.zona_fit.servicio;

import gm.zona_fit.modelo.Cliente;
import gm.zona_fit.repositorio.ClienteRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ClienteServicio implements IClienteServicio{
    // Inyecto la dependencia que es la que obtiene los metodo
    @Autowired
    private ClienteRepositorio clienteRepositorio;

    @Override
    public List<Cliente> listarClientes() {
        // Dentro del extends de jpa obtiene el metodo finAll que son todo el listado
        List<Cliente> clientes = clienteRepositorio.findAll();
        return clientes;
    }

    @Override
    public Cliente buscarClientePorId(Integer idCliente) {
        // Dentro del Jpa repository hay un metodo del extends que busca el cliente or else si no hay ninguna
        // Coincidencia
        Cliente cliente = clienteRepositorio.findById(idCliente).orElse(null);
        return cliente;
    }

    @Override
    public void guardarCliente(Cliente cliente) {
        // En jpa hay un metodo llamado save que guarda el cliente
        clienteRepositorio.save(cliente);
    }

    @Override
    public void eliminarCliente(Cliente cliente) {
        // igual que delete el metodo que elimina el cliente
        clienteRepositorio.delete(cliente);
    }
}
