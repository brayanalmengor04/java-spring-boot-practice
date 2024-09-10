package gm.zona_fit.repositorio;

import gm.zona_fit.modelo.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
// ESTO ES LO QUE OBTIENE CON EL EXTENS LAS FUNCIONES PRINCIPALES COMO
// INSERT , UPDATE , DELETE ...
public interface ClienteRepositorio extends JpaRepository<Cliente,Integer> {
}
