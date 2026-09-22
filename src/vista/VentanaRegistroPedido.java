package vista;

import controlador.GestorDatos;
import modelo.Direccion;
import modelo.TipoPedido;

import javax.swing.*;
import java.awt.*;

public class VentanaRegistroPedido extends JFrame {
    private GestorDatos gestorDatos;
    private JTextField calle;
    private JTextField numero;
    private JTextField comuna;
    private JComboBox<TipoPedido> tipoPedido;

    private JButton btnCancelar;
    private JButton btnGuardar;

    public VentanaRegistroPedido(GestorDatos gestorDatos){
        this.gestorDatos = gestorDatos;

        // Configuración base de la ventana
        setTitle("Registrar nuevo pedido - SpeedFast");
        setSize(400, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10, 10));

        // Panel de datos
        JPanel panelDatos = new JPanel(new GridLayout(0, 2));
        panelDatos.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        panelDatos.add(new JLabel("▸ DIRECCIÓN DEL PEDIDO"));
        panelDatos.add(new JLabel(""));

        panelDatos.add(new JLabel("Calle:"));
        calle = new JTextField(20);
        panelDatos.add(calle);

        panelDatos.add(new JLabel("Número:"));
        numero = new JTextField(10);
        panelDatos.add(numero);

        panelDatos.add(new JLabel("Comuna:"));
        comuna = new JTextField(10);
        panelDatos.add(comuna);

        panelDatos.add(new JLabel(""));
        panelDatos.add(new JLabel(""));

        panelDatos.add(new JLabel("▸ TIPO DE PEDIDO"));
        panelDatos.add(new JLabel(""));

        panelDatos.add(new JLabel("Tipo:"));
        tipoPedido = new JComboBox<>(TipoPedido.values());
        panelDatos.add(tipoPedido);

        add(panelDatos, BorderLayout.CENTER);

        // Panel de botones
        JPanel panelBotones = new JPanel(new GridLayout(1, 2));
        panelBotones.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        btnCancelar = new JButton("Cancelar");
        btnGuardar = new JButton("Guardar");
        panelBotones.add(btnCancelar);
        panelBotones.add(btnGuardar);

        add(panelBotones, BorderLayout.SOUTH);

        btnGuardar.addActionListener(e -> crearPedido());

        btnCancelar.addActionListener(e-> dispose());
    }

    public void crearPedido() {
        //Validar campos
        if (!validarCampos()) {
            return;
        }

        // Capturamos los datos en el objeto turista de la reserva
        Direccion direccionIngresada = new Direccion(
                calle.getText().trim(),
                Integer.parseInt(numero.getText().trim()),
                comuna.getText().trim()
        );
        TipoPedido tipoPedidoIngresado = (TipoPedido) tipoPedido.getSelectedItem();

        // Enviamos el nuevo pedido al controlador
        gestorDatos.agregarNuevoPedido(direccionIngresada, tipoPedidoIngresado);

        // Informamos operación exitosa
        operacionExitosa("Pedido creado correctamente");
        dispose();
    }

    public boolean validarCampos(){
        // Validación de calle no vacía
        if (calle.getText().trim().isEmpty()){
            alertarInconsistencia("Debe completar la calle del domicilio de la persona");
            return false;
        }

        // Validación de numero no vacío, entero y positivo
        if (numero.getText().trim().isEmpty()){
            alertarInconsistencia("Debe completar el numero del domicilio de de la persona");
            return false;
        } else {
            try{
                int valor = Integer.parseInt(numero.getText().trim());
                if (valor <= 0){
                    alertarInconsistencia("El numero del domicilio debe ser un número entero positivo.");
                    return false;
                }
            }catch (NumberFormatException e){
                alertarInconsistencia("El numero del domicilio debe ser un número entero positivo.");
                return false;
            }
        }

        // Validación de comuna no vacía
        if (comuna.getText().trim().isEmpty()){
            alertarInconsistencia("Debe completar la comuna del domicilio de la persona");
            return false;
        }

        return true;
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
