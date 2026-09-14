package model;
/**
 * Representa un pedido que debe ser entregado a una dirección
 */
public class Pedido {
    private int idPedido;
    private Direccion direccionEntrega;
    private EstadoPedido estado; //PENDIENTE, EN_REPARTO, ENTREGADO

    // Constructor con valor inicial de estado
    public Pedido(int idPedido, Direccion direccionEntrega) {
        this.idPedido = idPedido;
        this.direccionEntrega = direccionEntrega;
        this.estado = EstadoPedido.PENDIENTE;
        System.out.println("Pedido #" + idPedido + " agregado. Destino: " + direccionEntrega.toString());
    }

    //Getter and Setter
    public int getIdPedido() {
        return idPedido;
    }

    public void setIdPedido(int idPedido) {
        this.idPedido = idPedido;
    }

    public Direccion getDireccionEntrega() {
        return direccionEntrega;
    }

    public void setDireccionEntrega(Direccion direccionEntrega) {
        this.direccionEntrega = direccionEntrega;
    }

    public EstadoPedido getEstado() {
        return estado;
    }

    public void setEstado(EstadoPedido estado) {
        this.estado = estado;
    }

    @Override
    public String toString() {
        return "pedido #" + idPedido + ". Destino: " + direccionEntrega.toString() + " . Estado: " + estado.getDescripcion();
    }
}
