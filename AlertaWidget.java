public class AlertaWidget extends Widget implements Interactivo {
    private String nivelUrgencia;
    private String mensajeAlerta;

    public AlertaWidget(String id, String nombre, int consumoMemoriaKB, String nivelUrgencia, String mensajeAlerta) {
        super(id, nombre, consumoMemoriaKB);
        this.nivelUrgencia = nivelUrgencia;
        this.mensajeAlerta = mensajeAlerta;
    }

    @Override
    public String renderizar() {
        return "ALERTA [" + nivelUrgencia.toUpperCase() + "]: " + mensajeAlerta;
    }

    @Override
    public String manejarClic() {
        return "Alerta confirmada y marcada como leida por el usuario.";
    }

    @Override
    public String actualizarTiempoReal() {
        return "Verificando nuevas alertas de seguridad en el sistema...";
    }

    @Override
    public String obtenerDetallesConfiguracion() {
        return "Tipo: Notificacion de Alerta | Urgencia: " + nivelUrgencia + " | Mensaje: " + mensajeAlerta;
    }
}
