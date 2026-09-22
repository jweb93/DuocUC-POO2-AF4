package model;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Representa la zona de carga de pedidos mediante el uso de BlockingQueue.
 * Importante: La Guía de trabajo indica el uso de synchronized, sin embargo, BlockingQueue
 * ya incorpora la gestión segura de sus elementos. Adicionalmente, si pensamos que en un futuro los
 * objetos de la cola (Pedidos) llegarán concurrentemente a la vez que son repartidos concurrentemente,
 * se podría generar un bloqueo erróneo si un onceavo pedido llega y bloquea la cola (al ser un recurso
 * sincronizado) impidiendo que repartidores disponibles intenten retirar pedidos.
 */
public class ZonaDeCarga {
    private BlockingQueue<Pedido> pedidos = new ArrayBlockingQueue<>(10);

    public void agregarPedido(Pedido p) throws InterruptedException {
        pedidos.put(p);
    }

    public Pedido retirarPedido() throws InterruptedException {
        return pedidos.poll(); // Si la cola está vacía, se retorna null
    }
}
