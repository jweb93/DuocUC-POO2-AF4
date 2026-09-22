package model;
/**
 * Representa un pedido que debe ser entregado a una dirección
 */
public class Pedido {
    private int idPedido;
    private Direccion direccionEntrega;
    private TipoPedido tipoPedido;
    private EstadoPedido estado; //PENDIENTE, EN_REPARTO, ENTREGADO

    // Constructor con valor inicial de estado
    public Pedido(int idPedido, Direccion direccionEntrega, TipoPedido tipoPedido) {
        this.idPedido = idPedido;
        this.direccionEntrega = direccionEntrega;
        this.tipoPedido = tipoPedido;
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

    public TipoPedido getTipoPedido() {
        return tipoPedido;
    }

    public void setTipoPedido(TipoPedido tipoPedido) {
        this.tipoPedido = tipoPedido;
    }

    @Override
    public String toString() {
        return "pedido #" + idPedido + " (" + tipoPedido.getDescripcion() + "). Destino: " + direccionEntrega.toString() + " . Estado: " + estado.getDescripcion();
    }
}
