package inventario;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import java.awt.BorderLayout;
import java.awt.GridLayout;

public class VentanaInventario extends JFrame {

    private Inventario inventario;

    private JTextField txtCodigo;
    private JTextField txtNombre;
    private JTextField txtPrecio;
    private JTextField txtStock;

    private JTable tabla;

    private DefaultTableModel modeloTabla;


    public VentanaInventario() {

        inventario = new Inventario();

        configurarVentana();

        crearFormulario();

        crearTabla();

        crearBotones();

        setVisible(true);
    }


    private void configurarVentana() {

        setTitle("Sistema de Inventario");

        setSize(900, 600);

        setDefaultCloseOperation(
                JFrame.EXIT_ON_CLOSE
        );

        setLocationRelativeTo(null);

        setLayout(new BorderLayout());
    }


    private void crearFormulario() {

        JPanel panelFormulario =
                new JPanel();

        panelFormulario.setLayout(
                new GridLayout(2, 4)
        );


        JLabel lblCodigo =
                new JLabel("Código:");

        txtCodigo =
                new JTextField();


        JLabel lblNombre =
                new JLabel("Nombre:");

        txtNombre =
                new JTextField();


        JLabel lblPrecio =
                new JLabel("Precio:");

        txtPrecio =
                new JTextField();


        JLabel lblStock =
                new JLabel("Stock:");

        txtStock =
                new JTextField();


        panelFormulario.add(lblCodigo);
        panelFormulario.add(txtCodigo);

        panelFormulario.add(lblNombre);
        panelFormulario.add(txtNombre);

        panelFormulario.add(lblPrecio);
        panelFormulario.add(txtPrecio);

        panelFormulario.add(lblStock);
        panelFormulario.add(txtStock);


        add(
                panelFormulario,
                BorderLayout.NORTH
        );
    }


    private void crearTabla() {

        modeloTabla =
                new DefaultTableModel();

        modeloTabla.addColumn("Código");

        modeloTabla.addColumn("Producto");

        modeloTabla.addColumn("Precio");

        modeloTabla.addColumn("Stock");


        tabla =
                new JTable(modeloTabla);


        JScrollPane scrollPane =
                new JScrollPane(tabla);


        add(
                scrollPane,
                BorderLayout.CENTER
        );

        tabla.getSelectionModel().addListSelectionListener(e -> {

            if (!e.getValueIsAdjusting()) {

                int fila = tabla.getSelectedRow();

                if (fila != -1) {

                    txtCodigo.setText(
                            modeloTabla.getValueAt(fila, 0).toString()
                    );

                    txtNombre.setText(
                            modeloTabla.getValueAt(fila, 1).toString()
                    );

                    txtPrecio.setText(
                            modeloTabla.getValueAt(fila, 2).toString()
                    );

                    txtStock.setText(
                            modeloTabla.getValueAt(fila, 3).toString()
                    );
                }
            }
        });
    }



    private void crearBotones() {

        JPanel panelBotones =
                new JPanel();


        JButton btnAgregar =
                new JButton("Agregar");

        JButton btnBuscar =
                new JButton("Buscar");

        JButton btnActualizar =
                new JButton("Actualizar");

        JButton btnEliminar =
                new JButton("Eliminar");

        JButton btnLimpiar =
                new JButton("Limpiar");


        panelBotones.add(btnAgregar);

        panelBotones.add(btnBuscar);

        panelBotones.add(btnActualizar);

        panelBotones.add(btnEliminar);

        panelBotones.add(btnLimpiar);


        add(
                panelBotones,
                BorderLayout.SOUTH
        );


        // BOTÓN AGREGAR

        btnAgregar.addActionListener(e -> {

            try {

                int codigo =
                        Integer.parseInt(
                                txtCodigo.getText()
                        );


                String nombre =
                        txtNombre.getText();


                double precio =
                        Double.parseDouble(
                                txtPrecio.getText()
                        );


                int stock =
                        Integer.parseInt(
                                txtStock.getText()
                        );


                if (nombre.isEmpty()) {

                    JOptionPane.showMessageDialog(
                            this,
                            "Debe ingresar el nombre."
                    );

                    return;
                }


                Producto producto =
                        new Producto(
                                codigo,
                                nombre,
                                precio,
                                stock
                        );


                boolean agregado =
                        inventario.agregarProducto(
                                producto
                        );


                if (agregado) {

                    JOptionPane.showMessageDialog(
                            this,
                            "Producto agregado correctamente."
                    );

                    actualizarTabla();

                    limpiarCampos();

                } else {

                    JOptionPane.showMessageDialog(
                            this,
                            "No se pudo agregar el producto.",
                            "Error",
                            JOptionPane.ERROR_MESSAGE
                    );
                }


            } catch (NumberFormatException ex) {

                JOptionPane.showMessageDialog(
                        this,
                        "Código, precio y stock deben ser números.",
                        "Error",
                        JOptionPane.ERROR_MESSAGE
                );
            }
        });


        // BOTÓN BUSCAR

        btnBuscar.addActionListener(e -> {

            try {

                int codigo =
                        Integer.parseInt(
                                txtCodigo.getText()
                        );


                Producto producto =
                        inventario.buscarProducto(
                                codigo
                        );


                if (producto != null) {

                    txtNombre.setText(
                            producto.getNombre()
                    );

                    txtPrecio.setText(
                            String.valueOf(
                                    producto.getPrecio()
                            )
                    );

                    txtStock.setText(
                            String.valueOf(
                                    producto.getStock()
                            )
                    );

                } else {

                    JOptionPane.showMessageDialog(
                            this,
                            "Producto no encontrado."
                    );
                }


            } catch (NumberFormatException ex) {

                JOptionPane.showMessageDialog(
                        this,
                        "Ingrese un código válido.",
                        "Error",
                        JOptionPane.ERROR_MESSAGE
                );
            }
        });


        // BOTÓN ACTUALIZAR

        btnActualizar.addActionListener(e -> {

            try {

                int codigo =
                        Integer.parseInt(
                                txtCodigo.getText()
                        );


                int nuevoStock =
                        Integer.parseInt(
                                txtStock.getText()
                        );


                boolean actualizado =
                        inventario.actualizarStock(
                                codigo,
                                nuevoStock
                        );


                if (actualizado) {

                    JOptionPane.showMessageDialog(
                            this,
                            "Stock actualizado correctamente."
                    );

                    actualizarTabla();

                    limpiarCampos();

                } else {

                    JOptionPane.showMessageDialog(
                            this,
                            "No se pudo actualizar el stock.",
                            "Error",
                            JOptionPane.ERROR_MESSAGE
                    );
                }


            } catch (NumberFormatException ex) {

                JOptionPane.showMessageDialog(
                        this,
                        "Código y stock deben ser números.",
                        "Error",
                        JOptionPane.ERROR_MESSAGE
                );
            }
        });


        // BOTÓN ELIMINAR

        btnEliminar.addActionListener(e -> {

            try {

                int codigo =
                        Integer.parseInt(
                                txtCodigo.getText()
                        );


                boolean eliminado =
                        inventario.eliminarProducto(
                                codigo
                        );


                if (eliminado) {

                    JOptionPane.showMessageDialog(
                            this,
                            "Producto eliminado correctamente."
                    );

                    actualizarTabla();

                    limpiarCampos();

                } else {

                    JOptionPane.showMessageDialog(
                            this,
                            "Producto no encontrado.",
                            "Error",
                            JOptionPane.ERROR_MESSAGE
                    );
                }


            } catch (NumberFormatException ex) {

                JOptionPane.showMessageDialog(
                        this,
                        "Ingrese un código válido.",
                        "Error",
                        JOptionPane.ERROR_MESSAGE
                );
            }
        });


        // BOTÓN LIMPIAR

        btnLimpiar.addActionListener(e -> {

            limpiarCampos();

        });
    }


    private void actualizarTabla() {

        modeloTabla.setRowCount(0);


        for (Producto producto :
                inventario.getProductos()) {


            Object[] fila = {

                    producto.getCodigo(),

                    producto.getNombre(),

                    producto.getPrecio(),

                    producto.getStock()
            };


            modeloTabla.addRow(fila);
        }
    }


    private void limpiarCampos() {

        txtCodigo.setText("");

        txtNombre.setText("");

        txtPrecio.setText("");

        txtStock.setText("");

        txtCodigo.requestFocus();
    }
}