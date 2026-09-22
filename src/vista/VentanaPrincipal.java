package vistas;

import controlador.GestorDatos;
import model.ZonaDeCarga;

import javax.swing.*;
import java.awt.*;

public class VentanaPrincipal extends JFrame {
    private JButton btnRegistrarPedido;
    private JButton btnListarPedidos;
    private JButton btnIniciarEntregas;
    private GestorDatos gestorDatos;

    public VentanaPrincipal(GestorDatos gestorDatos){
        this.gestorDatos = gestorDatos;

        // Configuración base de la ventana
        setTitle("Gestor de pedidos - SpeedFast");
        setSize(400, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // En su interior sólo tendrá un panel central con botones
        setLayout(new BorderLayout(10, 10));

        // El panel de botones tendrá una estructura de 3 filas y 1 columna
        JPanel panelBotones = new JPanel(new GridLayout(3, 1));
        panelBotones.setBorder(BorderFactory.createEmptyBorder(20, 30, 20, 30));

        btnRegistrarPedido = new JButton("Registrar nuevo pedido");
        btnListarPedidos = new JButton("Ver lista de pedidos");
        btnIniciarEntregas = new JButton("Iniciar entregas");

        panelBotones.add(btnRegistrarPedido);
        panelBotones.add(btnListarPedidos);
        panelBotones.add(btnIniciarEntregas);

        add(panelBotones, BorderLayout.CENTER);

        // Acciones de los botones

        btnRegistrarPedido.addActionListener(e -> {
            SwingUtilities.invokeLater(() -> {
                VentanaRegistroPedido ventas = new VentanaRegistroPedido(gestorDatos);
                ventas.setLocationRelativeTo(this);
                ventas.setVisible(true);
            });
        });

        btnListarPedidos.addActionListener(e -> {
            SwingUtilities.invokeLater(() -> {
                VentanaListaPedidos ventas = new VentanaListaPedidos(gestorDatos);
                ventas.setLocationRelativeTo(this);
                ventas.setVisible(true);
            });
        });

        btnIniciarEntregas.addActionListener(e -> {});

    }

}
