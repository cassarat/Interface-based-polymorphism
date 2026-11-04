public class ClimaWidget extends Widget implements Interactivo {
    private String ciudad;
    private double temperatura;

    public ClimaWidget(String id, String nombre, int consumoMemoriaKB, String ciudad, double temperatura) {
        super(id, nombre, consumoMemoriaKB);
        this.ciudad = ciudad;
        this.temperatura = temperatura;
    }

    @Override
    public String renderizar() {
        return "Clima en " + ciudad + ": " + temperatura + "°C";
    }

    @Override
    public String manejarClic() {
        return "Cambiando unidad de temperatura para " + ciudad + "...";
    }

    @Override
    public String actualizarTiempoReal() {
        this.temperatura += (Math.random() * 2 - 1); //simula variacion termica 
        return "Temperatura actualizada en " + ciudad + ": " + String.format("%.1f", temperatura) + "°C";
    }

    @Override
    public String obtenerDetallesConfiguracion() {
        return "Tipo: Visor de Clima | Ciudad: " + ciudad + " | Temp actual: " + temperatura + "°C";
    }
}
