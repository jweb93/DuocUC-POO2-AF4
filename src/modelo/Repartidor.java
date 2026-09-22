package modelo;

/**
 * Representa un repartidor que recibirá pedido y los despachará
 */

public class Repartidor implements Runnable{
    private String nombre;
    private ZonaDeCarga zonaDeCarga;

    public Repartidor(String nombre, ZonaDeCarga zonaDeCarga) {
        this.nombre = nombre;
        this.zonaDeCarga = zonaDeCarga;
    }

    public String getNombre() {
        return nombre;
    }

    @Override
    public void run(){
        try {
            Pedido pedidoRetirado;
            while((pedidoRetirado = zonaDeCarga.retirarPedido()) != null){
                pedidoRetirado.setEstado(EstadoPedido.EN_REPARTO);
                System.out.println("[Repartidor - " + nombre + "] Repartiendo " + pedidoRetirado.toString());
                Thread.sleep(3000);

                pedidoRetirado.setEstado(EstadoPedido.ENTREGADO);
                System.out.println("[Repartidor - " + nombre + "] Entregó " + pedidoRetirado.toString());
                Thread.sleep(1000); //Debe regresar a la zona de reparto a verificar si hay más pedidos
            }

        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        System.out.println("[Repartidor - " + nombre + "] Termina su jornada.");
    }
}
