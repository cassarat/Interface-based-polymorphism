public class TareasWidget extends Widget implements Interactivo {
    private int totalTareasPendientes;

    public TareasWidget(String id, String nombre, int consumoMemoriaKB, int totalTareasPendientes) {
        super(id, nombre, consumoMemoriaKB);
        this.totalTareasPendientes = totalTareasPendientes;
    }

    @Override
    public String renderizar() {
        return "Tareas pendientes: " + totalTareasPendientes;
    }

    @Override
    public String manejarClic() {
        if (totalTareasPendientes > 0) {
            totalTareasPendientes--;
            return "Tarea completada. Tareas pendientes restantes: " + totalTareasPendientes;
        }
        return "No hay tareas pendientes por completar!";
    }

    @Override
    public String actualizarTiempoReal() {
        return "Sincronizando lista de tareas pendientes...";
    }

    @Override
    public String obtenerDetallesConfiguracion() {
        return "Tipo: Lista de Tareas | Pendientes: " + totalTareasPendientes;
    }
}
