package vista;

import controlador.GestorDatos;
import modelo.Pedido;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class VentanaListaPedidos extends JFrame {
    private GestorDatos gestorDatos;
    private DefaultTableModel modeloTabla;
    private JTable tabla;
    private JScrollPane scroll;

    private JButton btnCerrar;
    private JButton btnActualizar;

    public VentanaListaPedidos(GestorDatos gestorDatos){
        this.gestorDatos = gestorDatos;

        // Configuración base de la ventana
        setTitle("Listado de pedidos - SpeedFast");
        setSize(800, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10, 10));

        // Scroll central con tabla de datos
        modeloTabla = new DefaultTableModel(
                new String[]{"ID", "Calle", "Número", "Comuna", "Tipo del pedido", "Estado"},
                0
        );

        tabla = new JTable(modeloTabla);

        scroll = new JScrollPane(tabla);
        add(scroll, BorderLayout.CENTER);

        // Panel inferior con botoneras
        JPanel panelBotones = new JPanel(new GridLayout(1, 2));
        panelBotones.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        btnActualizar = new JButton("Actualizar");
        btnCerrar = new JButton("Cerrar");

        panelBotones.add(btnActualizar);
        panelBotones.add(btnCerrar);

        add(panelBotones, BorderLayout.SOUTH);

        btnActualizar.addActionListener(e -> actualizarListado());
        btnCerrar.addActionListener(e-> dispose());

        actualizarListado();
    }

    public void actualizarListado(){
        // Primero borramos los registros cargados
        modeloTabla.setRowCount(0);

        // Luego se cargan los registros
        for(Pedido pedido : gestorDatos.getPedidos()){
            modeloTabla.addRow(new Object[]{
                    pedido.getIdPedido(),
                    pedido.getDireccionEntrega().getCalle(),
                    pedido.getDireccionEntrega().getNumero(),
                    pedido.getDireccionEntrega().getComuna(),
                    pedido.getTipoPedido().getDescripcion(),
                    pedido.getEstado()
            });
        }
    }
}
