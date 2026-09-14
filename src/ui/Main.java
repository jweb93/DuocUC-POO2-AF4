package ui;

import model.Direccion;
import model.Pedido;
import model.Repartidor;
import model.ZonaDeCarga;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Clase principal para ejecutar el programa
 */

public class Main {

    public static void main(String[] args) {
        ZonaDeCarga zonaDeCarga = new ZonaDeCarga();
        AtomicInteger contador = new AtomicInteger(0);

        // Agregamos 5 pedidos con try y catch ya que la cola podría estar llena
        System.out.println("----------- Agregando Pedidos ...");
        try{
            zonaDeCarga.agregarPedido(new Pedido(contador.incrementAndGet(),
                    new Direccion("Avenida Matta", 1042, "Santiago")));
            Thread.sleep(1000);

            zonaDeCarga.agregarPedido(new Pedido(contador.incrementAndGet(),
                    new Direccion("Avenida Central", 987, "Maipú")));
            Thread.sleep(1000);

            zonaDeCarga.agregarPedido(new Pedido(contador.incrementAndGet(),
                    new Direccion("Avenida Presidente Riesco", 777, "Las Condes")));
            Thread.sleep(1000);

            zonaDeCarga.agregarPedido(new Pedido(contador.incrementAndGet(),
                    new Direccion("Avenida Irarrázaval", 2450, "Ñuñoa")));
            Thread.sleep(1000);

            zonaDeCarga.agregarPedido(new Pedido(contador.incrementAndGet(),
                    new Direccion("Gran Avenida", 5320, "San Miguel")));
            Thread.sleep(1000);

        }catch (InterruptedException e){
            Thread.currentThread().interrupt();
            System.out.println("La espera en zona de carga fue interrumpida");
        }

        // Agregamos 3 repartidores usando
        System.out.println();
        System.out.println("----------- Buscando Repartidores disponibles ...");


        Repartidor repartidor1 = new Repartidor("Arnold Schwar", zonaDeCarga);
        Repartidor repartidor2 = new Repartidor("Rocky Balboa", zonaDeCarga);
        Repartidor repartidor3 = new Repartidor("Chuck Norris", zonaDeCarga);

        System.out.println();
        System.out.println("----------- Se encontraron repartidores ...");

        ExecutorService executor = Executors.newFixedThreadPool(3); // Objeto que administrará 3 hilos concurrentes
        executor.execute(repartidor1);
        executor.execute(repartidor2);
        executor.execute(repartidor3);

        executor.shutdown();

        try{
            boolean termino = executor.awaitTermination(1, TimeUnit.MINUTES);

            if(termino){
                System.out.println("Todos los pedidos han sido entregados correctamente");
            }else{
                System.out.println("Aun existen repartidores trabajando");
            }
        }catch (InterruptedException e){
            Thread.currentThread().interrupt();
        }

    }
}
