package controlador;

import model.Direccion;
import model.Pedido;
import model.TipoPedido;
import model.ZonaDeCarga;

import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Clase que controla los arreglos internos de informacion del programa.
 */
public class PedidoControlador {
    private ArrayList<Pedido> pedidos;
    AtomicInteger contador;
    ZonaDeCarga zonaDeCarga = new ZonaDeCarga();

    public PedidoControlador(){
        pedidos = new ArrayList<>();
        contador = new AtomicInteger(0);
    }

    public ArrayList<Pedido> getPedidos(){
        return pedidos;
    }

    public void agregarNuevoPedido(Direccion direccion, TipoPedido tipoPedido){
        pedidos.add(new Pedido(contador.incrementAndGet(), direccion, tipoPedido));
    }



}
