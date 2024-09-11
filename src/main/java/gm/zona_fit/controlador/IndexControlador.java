package gm.zona_fit.controlador;

import gm.zona_fit.modelo.Cliente;
import gm.zona_fit.servicio.IClienteServicio;
import jakarta.annotation.PostConstruct;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.faces.view.ViewScoped;
import lombok.Data;
import org.primefaces.PrimeFaces;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.List;


@Component
@Data
@ViewScoped
public class IndexControlador {

    @Autowired
    IClienteServicio clienteServicio;
    private Cliente clienteSeleccionado;
    private List<Cliente> clienteList;
    // Para imprimir en la consola
    private static final Logger logger= LoggerFactory.getLogger(IndexControlador.class);


    // No se puede usar algo como un constructor porque jsf lo hace pero podemos usar algo parecido
    @PostConstruct
    public void init() {
        cargarDatosBD();
    }

    public void cargarDatosBD() {
        this.clienteList = clienteServicio.listarClientes();
        this.clienteList.forEach(cliente -> logger.info(cliente.toString()));
    }

    public void agregarCliente() {
        this.clienteSeleccionado = new Cliente();

    }

    public void guardarCliente(){
        logger.info("cliente a guardar: " + this.clienteSeleccionado);
        // Agregar
        if(this.clienteSeleccionado.getId() == null){
            this.clienteServicio.guardarCliente(this.clienteSeleccionado);
            this.clienteList.add(this.clienteSeleccionado);
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage("Cliente Agregado"));
        }


        // MoDIFICAR
        else{
            this.clienteServicio.guardarCliente(this.clienteSeleccionado);
            FacesContext.getCurrentInstance().addMessage(null,new FacesMessage("Cliente Actualizado"));
        }

        // Ocultar la ventana modal
        PrimeFaces.current().executeScript("PF('ventanaModalCliente').hide()");
        // Actualizar la tabla usando ajax
        PrimeFaces.current().ajax().update("forma-clientes:mensajes",
                "forma-clientes:clientes-tabla");
        // Reset del objeto cliente seleccionado
        this.clienteSeleccionado = null;
    }

    public void eliminarCliente() {
        
        logger.info("cliente a eliminar: " + this.clienteSeleccionado);
        this.clienteServicio.eliminarCliente(this.clienteSeleccionado);
        // Eliminar el registro de la lista de cliente
        this.clienteList.remove(this.clienteSeleccionado);

        // Refres del objeto seleccionado
        this.clienteSeleccionado = null;

        // Mostrar mensaje
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Cliente eliminado"));
        PrimeFaces.current().ajax().update("forma-clientes:mensajes","forma-clientes:clientes-tabla");
    }


}
