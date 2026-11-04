public class NotasWidget extends Widget {
    private String notaRapida;

    public NotasWidget(String id, String nombre, int consumoMemoriaKB, String notaRapida) {
        super(id, nombre, consumoMemoriaKB);
        this.notaRapida = notaRapida;
    }

    @Override
    public String renderizar() {
        return "Nota: " + notaRapida;
    }

    @Override
    public String obtenerDetallesConfiguracion() {
        return "Tipo: Nota Rapida Estatica | Contenido: " + notaRapida;
    }
}