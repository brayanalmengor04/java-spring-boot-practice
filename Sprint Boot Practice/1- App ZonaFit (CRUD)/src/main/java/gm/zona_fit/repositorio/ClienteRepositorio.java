package gm.zona_fit.repositorio;

import gm.zona_fit.modelo.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
// Esta Extiende de otra interfas que es la que tiene todos los metodos como (ListarTodo , Lista por ID , Guardar , Eliminar...)
public interface ClienteRepositorio extends JpaRepository<Cliente,Integer> {
}
