package controlador;

import modelo.*;

import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Clase que controla los arreglos internos de informacion del programa (pedidos, repartidores, zona de carga).
 */
public class GestorDatos {
    private ArrayList<Pedido> pedidos;
    private AtomicInteger contador;
    private ZonaDeCarga zonaDeCarga;
    private ArrayList<Repartidor> repartidores;

    public GestorDatos(){
        pedidos = new ArrayList<>(); // Guarda el registro completo de pedidos
        contador = new AtomicInteger(0);
        zonaDeCarga = new ZonaDeCarga(); // Guarda los pedidos que faltan por despachar
        repartidores = new ArrayList<>();

        // Para esta entrega predefinimos 3 repartidores. A futuro podemos crear un mantenedore de Repartidores.
        agregarNuevoRepartidor("Arnold Schwar");
        agregarNuevoRepartidor("Rocky Balboa");
        agregarNuevoRepartidor("Chuck Norris");
    }

    public ArrayList<Pedido> getPedidos(){
        return pedidos;
    }

    public void agregarNuevoPedido(Direccion direccion, TipoPedido tipoPedido){
        Pedido pedido = new Pedido(contador.incrementAndGet(), direccion, tipoPedido);
        pedidos.add(pedido);

        try {
            zonaDeCarga.agregarPedido(pedido);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    public ZonaDeCarga getZonaDeCarga() {
        return zonaDeCarga;
    }

    public ArrayList<Repartidor> getRepartidores() {
        return repartidores;
    }

    public void agregarNuevoRepartidor(String nombre){
        repartidores.add(new Repartidor(nombre, zonaDeCarga));
    }

    public String despacharPedidos(){
        ExecutorService executor = Executors.newFixedThreadPool(3); // Objeto que administrará 3 hilos concurrentes
        for(Repartidor r : repartidores){
            executor.execute(r);
        }
        executor.shutdown();
        try{
            boolean termino = executor.awaitTermination(1, TimeUnit.MINUTES);
            if(termino){
                return "Todos los pedidos han sido entregados correctamente";
            }else{
                return "Aun existen repartidores trabajando";
            }
        }catch (InterruptedException e){
            Thread.currentThread().interrupt();
            return "Ha ocurrido una interrupción del servicio";
        }
    }
}
