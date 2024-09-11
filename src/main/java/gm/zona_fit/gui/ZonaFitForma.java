package gm.zona_fit.gui;

import gm.zona_fit.modelo.Cliente;
import gm.zona_fit.servicio.ClienteServicio;
import gm.zona_fit.servicio.IClienteServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
// Esto al usar spring boot
//@Component
public class ZonaFitForma extends JFrame {
    private JPanel panelPrincipal;
    private JTable clienteTable;
    private JTextField txtNombre;
    private JTextField txtApellido;
    private JTextField txtMembresia;
    private JButton btnGuardar;
    private JButton btnEliminar;
    private JButton btnLimpiar;
    private DefaultTableModel tablaModeloCliente;
    private Integer idCliente;
    // Esta contiene los metodos de listar actualizar y eliminar (un metodo que adentro tiene..
    // los metodo del jpaRepository cliente repositorio

    // Cuando voy a usar el metodo en main o interface grafica se llama al Interface
    IClienteServicio clienteServicio;

    @Autowired // esto es para que se inyecte al form
    public ZonaFitForma(ClienteServicio clienteServicio) {
        this.clienteServicio = clienteServicio; // para recuperar los datos de las dependencias
        iniciarForma();

        btnGuardar.addActionListener(e -> guardarCliente());
        clienteTable.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                cargarClienteSeleccionado();
            }
        });
        btnEliminar.addActionListener(e -> eliminarCliente());
        btnLimpiar.addActionListener(e -> limpiarFormualario());
    }

    private void iniciarForma() {

        this.setTitle("Zona Fit");
        this.setContentPane(panelPrincipal);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(900,700);
        setLocationRelativeTo(null);
    }

    private void createUIComponents() {
        // Creaccion de la tabla
       // this.tablaModeloCliente = new DefaultTableModel(0,4);
        this.tablaModeloCliente = new DefaultTableModel(0,4){
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
                // Evitamos que se pueda editar adentro
            }
        };
        String[] cabeceros ={"ID","Nombre","Apellido","Membresia"};
        this.tablaModeloCliente.setColumnIdentifiers(cabeceros);
        // Le hago la referencia del nuevo Jtable modelo
        this.clienteTable = new JTable(this.tablaModeloCliente);
        // Restringuimos la seleccion multiple Ctrl + click
        this.clienteTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        // Cargar Listado de clientes
        listarClientes();

    }

    private void listarClientes() {
        this.tablaModeloCliente.setRowCount(0);
        // Obtengo los valores del jparepository
        var clientes = this.clienteServicio.listarClientes();

        clientes.forEach(cliente -> {
            // Creamos un reglon (fila)
            Object[] rengloCliente ={cliente.getId(),cliente.getNombre(),cliente.getApellido(),cliente.getMembresia()};
            this.tablaModeloCliente.addRow(rengloCliente);
        });
    }
    private void guardarCliente() {
        if (txtNombre.getText().isEmpty()){
            mostrarMensaje("Proporciona un Nombre ");
            txtNombre.requestFocusInWindow();
            return;
        }
        if (txtApellido.getText().isEmpty()){
            mostrarMensaje("Proporciona un Apellido ");
            txtApellido.requestFocusInWindow();
            return;
        }
        if (txtMembresia.getText().isEmpty()){
            mostrarMensaje("Proporciona un Membresia ");
            txtMembresia.requestFocusInWindow();
            return;
        }
        // Recuperamos valores
        var nombre = txtNombre.getText();
        var apellido = txtApellido.getText();
        var membresia = Integer.parseInt(txtMembresia.getText());
        var clientenew = new Cliente(idCliente,nombre,apellido,membresia);

        this.clienteServicio.guardarCliente(clientenew); // Inserta  y actualizar como estamos trabajando con
        // Spring boot dependiendo si el id es null o no se hara una insercion o una modificacion

        // Ternario
        mostrarMensaje(idCliente == null ? "Se ha agregado un nuevo Registro Exitosamente!"
                : "Se ha actualizado un Registro Exitosamente! ");

        limpiarFormualario();
        listarClientes();
    }
    private void eliminarCliente() {
          if (idCliente != null){
              var cliente = new Cliente(idCliente,txtNombre.getText(),txtApellido.getText(),
                      Integer.parseInt(txtMembresia.getText())
                      );
              this.clienteServicio.eliminarCliente(cliente);
              mostrarMensaje("Se ha eliminado Correctamente el Cliente! " +cliente.getNombre() +cliente.getApellido());
          }
          else mostrarMensaje("Debe seleccionar un cliente para Eliminar!");

         listarClientes();
         limpiarFormualario();
    }
    private void cargarClienteSeleccionado() {
        var renglonTabla = this.clienteTable.getSelectedRows();

        if (renglonTabla.length !=-1) {
            var id = this.clienteTable.getModel().getValueAt(this.clienteTable.getSelectedRow(), 0).toString();
            idCliente = Integer.parseInt(id);
            var nombre = this.clienteTable.getModel().getValueAt(this.clienteTable.getSelectedRow(), 1).toString();
            txtNombre.setText(nombre);

            var apellido = this.clienteTable.getModel().getValueAt(this.clienteTable.getSelectedRow(), 2).toString();
            txtApellido.setText(apellido);

            var membresia = this.clienteTable.getModel().getValueAt(this.clienteTable.getSelectedRow(), 3).toString();
            txtMembresia.setText(membresia);
        }
    }

    private void mostrarMensaje(String mensaje) {
        JOptionPane.showMessageDialog(this, mensaje);
    }
    private void limpiarFormualario() {
            txtNombre.setText("");
            txtApellido.setText("");
            txtMembresia.setText("");
            this.idCliente = null;

            // Deselecionadamos el elemento de la tabla
            this.clienteTable.getSelectionModel().clearSelection();
    }

}
