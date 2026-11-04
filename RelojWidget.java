public class RelojWidget extends Widget {
    private boolean formato24Horas;

    public RelojWidget(String id, String nombre, int consumoMemoriaKB, boolean formato24Horas) {
        super(id, nombre, consumoMemoriaKB);
        this.formato24Horas = formato24Horas;
    }

    @Override
    public String renderizar() {
        return "Hora actual: " + (formato24Horas ? "14:30:00" : "02:30:00 PM");
    }

    @Override
    public String obtenerDetallesConfiguracion() {
        return "Tipo: Reloj | Formato: " + (formato24Horas ? "24 Horas" : "12 Horas (AM/PM)");
    }
}
