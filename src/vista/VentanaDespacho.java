package vista;

import controlador.GestorDatos;
import modelo.Pedido;
import modelo.Repartidor;

import javax.swing.*;
import java.awt.*;

/**
 * Se agrega esta vista para aprovechar el botón solicitado y reutilizar el codigo trabajado en entregas anteriores
 */

public class VentanaDespacho extends JFrame {
    private GestorDatos gestorDatos;

    private JTextArea areaRepartidores;
    private JTextArea areaZonaDeCarga;
    private JTextArea areaActividad;

    private JButton btnCerrar;
    private JButton btnDespacharPedidos;

    private Boolean despachando = false;


    public VentanaDespacho(GestorDatos gestorDatos){
        this.gestorDatos = gestorDatos;

        // Configuración base de la ventana
        setTitle("Despacho de pedidos - SpeedFast");
        setSize(1000, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Estará conformada por 3 paneles centrales con Repartidores, ZonaDeCarga y Actividad, y un panel inferior de botones
        setLayout(new BorderLayout(10, 10));

        // Sección central
        JPanel panelCentral = new JPanel(new GridLayout(1, 3, 10, 0));

        // Sección central - 1. Repartidores
        JPanel panelRepartidores = new JPanel(new BorderLayout());
        areaRepartidores = new JTextArea();
        areaRepartidores.setEditable(false);
        areaRepartidores.setOpaque(false);
        areaRepartidores.setLineWrap(true);
        panelRepartidores.add(new JScrollPane(areaRepartidores));

        panelCentral.add(panelRepartidores);

        // Sección central - 2. Zona de Carga
        JPanel panelZonaDeCarga = new JPanel(new BorderLayout());
        areaZonaDeCarga = new JTextArea();
        areaZonaDeCarga.setEditable(false);
        areaZonaDeCarga.setOpaque(false);
        areaZonaDeCarga.setLineWrap(true);
        panelZonaDeCarga.add(new JScrollPane(areaZonaDeCarga));

        panelCentral.add(panelZonaDeCarga);

        // Sección central - 3. Actividad
        JPanel panelActividad = new JPanel(new BorderLayout());
        areaActividad = new JTextArea();
        areaActividad.setEditable(false);
        areaActividad.setOpaque(false);
        areaActividad.setLineWrap(true);
        panelActividad.add(new JScrollPane(areaActividad));

        panelCentral.add(panelActividad);

        add(panelCentral, BorderLayout.CENTER);

        // Sección inferior - Botones
        JPanel panelBotones = new JPanel(new GridLayout(1, 2));
        panelBotones.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        btnDespacharPedidos = new JButton("Despachar Pedidos");
        btnCerrar = new JButton("Cerrar");

        panelBotones.add(btnDespacharPedidos);
        panelBotones.add(btnCerrar);

        add(panelBotones, BorderLayout.SOUTH);

        btnDespacharPedidos.addActionListener(e -> despacharPedidos());
        btnCerrar.addActionListener(e-> dispose());

        // Carga inicial de datos
        cargaRepartidores();
        cargaZonaDeCarga();

    }
    public void cargaRepartidores(){
        // Primero se limpia el listado
        areaRepartidores.setText("");

        // Luego se cargan los registros usando un StringBuilder
        StringBuilder texto = new StringBuilder();
        texto.append("--- LISTA DE REPARTIDORES ---\n");
        int i = 1;
        for(Repartidor repartidor : gestorDatos.getRepartidores()){
            texto.append(i).append(". ").append(repartidor.getNombre()).append('\n');
            i++;
        }
        areaRepartidores.setText(texto.toString());
    }

    public void cargaZonaDeCarga(){
        // Primero se limpia el listado
        areaZonaDeCarga.setText("");

        // Luego se cargan los registros usando un StringBuilder
        StringBuilder texto = new StringBuilder();
        texto.append("--- PEDIDOS EN ZONA DE CARGA ---\n");
        int i = 1;
        for(Pedido pedido : gestorDatos.getZonaDeCarga().getPedidos()){
            texto.append(i).append(". ").append(pedido.toString()).append('\n');
            i++;
        }
        areaZonaDeCarga.setText(texto.toString());
    }

    public void despacharPedidos(){
        if(!despachando){ // Si no estamos despachando podemos realizar despacho
            areaActividad.setText("Despachando pedidos ...");
            despachando = true;

            // Si simulamos el despacho concurrente directamente en este hilo (EDT), la impresión "Despachando pedidos ..."
            // podría no imprimirse. PAra resolverlo, creamos otro hilo para ejecutar el despacho concurrente, y luego
            // con SwingUtilities.invokeLater el hilo EDT retoma sus acciones.
            Thread hiloDespacho = new Thread(() -> {
                String resultado = gestorDatos.despacharPedidos();
                SwingUtilities.invokeLater(() -> {
                    areaActividad.setText(resultado);
                    despachando = false;
                });
            });
            hiloDespacho.start();
        }else{
            alertarInconsistencia("El despacho actual debe finalizar para iniciar uno nuevo");
        }
    }

    public void alertarInconsistencia(String alerta) {
        JOptionPane.showMessageDialog(
                this,
                alerta,
                "Alerta",
                JOptionPane.WARNING_MESSAGE
        );
    }

    private void operacionExitosa(String mensaje) {
        JOptionPane.showMessageDialog(
                this,
                mensaje,
                "Resultado",
                JOptionPane.INFORMATION_MESSAGE
        );
    }


}
