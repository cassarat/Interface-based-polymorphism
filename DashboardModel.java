import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class DashboardModel {
    //lista polimorfica
    private List<Widget> widgets;

    public DashboardModel() {
        this.widgets = new ArrayList<>();
        cargarCatalogoInicial();
    }
     //carga inicial en memoria con 10 widgets diversa
    
    public void cargarCatalogoInicial() {
        widgets.add(new ClimaWidget("W01", "Clima Guatemala", 512, "Guatemala", 22.5));
        widgets.add(new TareasWidget("W02", "Pendientes POO", 128, 5));
        widgets.add(new AlertaWidget("W03", "Alerta Servidor", 256, "Alta", "Uso de CPU al 90%"));
        widgets.add(new RelojWidget("W04", "Reloj Principal", 64, true));
        widgets.add(new NotasWidget("W05", "Recordatorio Entrega", 32, "Subir repositorio antes de medianoche"));
        widgets.add(new ClimaWidget("W06", "Clima Quetzaltenango", 480, "Quetzaltenango", 14.0));
        widgets.add(new TareasWidget("W07", "Tareas Hogar", 96, 2));
        widgets.add(new AlertaWidget("W08", "Alerta Backup", 192, "Media", "Copia de seguridad completada"));
        widgets.add(new RelojWidget("W09", "Reloj UTC", 64, false));
        widgets.add(new NotasWidget("W10", "Notas de Clase", 48, "Revisar uso de interfaces y polymorfismo"));
    }

    public void agregarWidget(Widget w) {
        if (w != null) {
            widgets.add(w);
        }
    }

    public List<Widget> obtenerWidgets() {
        return widgets;
    }
     //Sobrecarga 1 que es busqueda exacta de ID 
     
    public Widget buscarWidget(String id) {
        for (Widget w : widgets) {
            if (w.getId().equalsIgnoreCase(id.trim())) {
                return w;
            }
        }
        return null;
    }
    //Sobrecarga 2 busqueda por coincidencia parcial en el nombre
    
    public List<Widget> buscarWidget(String nombre, boolean coincidenciaParcial) {
        List<Widget> resultados = new ArrayList<>();
        for (Widget w : widgets) {
            if (coincidenciaParcial) {
                if (w.getNombre().toLowerCase().contains(nombre.toLowerCase().trim())) {
                    resultados.add(w);
                }
            } else {
                if (w.getNombre().equalsIgnoreCase(nombre.trim())) {
                    resultados.add(w);
                }
            }
        }
        return resultados;
    }
    //ordena la lista polimorfica por medio de la interfaz 
    
    public void ordenarPorMemoria() {
        Collections.sort(widgets);
    }
}
