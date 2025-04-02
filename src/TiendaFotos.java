/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author gerar
 */
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import static javax.swing.WindowConstants.EXIT_ON_CLOSE;

public class TiendaFotos extends JFrame {
    private JTextField txtCodigo, txtNombre, txtPrecio, txtCantidad;
    private JTable tabla;
    private DefaultTableModel modelo;

    public TiendaFotos() {
        setTitle("Tienda de Fotos");
        setSize(600, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        JPanel panelSuperior = new JPanel(new GridLayout(5, 2, 5, 5));
        panelSuperior.setBorder(BorderFactory.createTitledBorder("Manejo de Productos"));
        
        panelSuperior.add(new JLabel("Código:"));
        txtCodigo = new JTextField();
        panelSuperior.add(txtCodigo);
        
        panelSuperior.add(new JLabel("Nombre:"));
        txtNombre = new JTextField();
        panelSuperior.add(txtNombre);
        
        panelSuperior.add(new JLabel("Precio:"));
        txtPrecio = new JTextField();
        panelSuperior.add(txtPrecio);
        
        panelSuperior.add(new JLabel("Cantidad:"));
        txtCantidad = new JTextField();
        panelSuperior.add(txtCantidad);

        JButton btnAgregar = new JButton("Agregar");
        JButton btnEliminar = new JButton("Eliminar");
        
        panelSuperior.add(btnAgregar);
        panelSuperior.add(btnEliminar);
        
        add(panelSuperior, BorderLayout.NORTH);
        
        modelo = new DefaultTableModel();
        modelo.setColumnIdentifiers(new Object[]{"Código", "Nombre", "Precio", "Cantidad", "Total"});
        tabla = new JTable(modelo);
        add(new JScrollPane(tabla), BorderLayout.CENTER);

        btnAgregar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                agregarProducto();
            }
        });

        btnEliminar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                eliminarProducto();
            }
        });
    }

    private void agregarProducto() {
        String codigo = txtCodigo.getText();
        String nombre = txtNombre.getText();
        String precioStr = txtPrecio.getText();
        String cantidadStr = txtCantidad.getText();

        if (codigo.isEmpty() || nombre.isEmpty() || precioStr.isEmpty() || cantidadStr.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Todos los campos deben estar llenos.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try {
            double precio = Double.parseDouble(precioStr);
            int cantidad = Integer.parseInt(cantidadStr);
            double total = precio * cantidad;
            
            modelo.addRow(new Object[]{codigo, nombre, precio, cantidad, total});
            limpiarCampos();
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Precio y cantidad deben ser valores numéricos.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void eliminarProducto() {
        int filaSeleccionada = tabla.getSelectedRow();
        if (filaSeleccionada != -1) {
            modelo.removeRow(filaSeleccionada);
        } else {
            JOptionPane.showMessageDialog(this, "Seleccione un producto para eliminar.", "Advertencia", JOptionPane.WARNING_MESSAGE);
        }
    }

    private void limpiarCampos() {
        txtCodigo.setText("");
        txtNombre.setText("");
        txtPrecio.setText("");
        txtCantidad.setText("");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new TiendaFotos().setVisible(true));
    }
}
