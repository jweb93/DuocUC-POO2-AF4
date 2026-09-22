package modelo;

public enum EstadoPedido {
    PENDIENTE("Pendiente"),
    EN_REPARTO("En reparto"),
    ENTREGADO("Entregado");

    private final String descripcion;

    EstadoPedido(String descripcion){
        this.descripcion = descripcion;
    }

    public String getDescripcion(){
        return descripcion;
    }
}