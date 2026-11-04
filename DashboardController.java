import java.util.List;

public class DashboardController {
    private DashboardModel model;
    private DashboardGUIView view;

    public DashboardController(DashboardModel model, DashboardGUIView view) {
        this.model = model;
        this.view = view;
        inicializarEventos();
        // Carga inicial automatica en la tabla
        this.view.actualizarTabla(this.model.obtenerWidgets());
    }

    private void inicializarEventos() {
        // listar todos los widgets
        view.getBtnListar().addActionListener(e -> {
            view.actualizarTabla(model.obtenerWidgets());
            view.mostrarDetalle("Mostrando catalogo completo (" + model.obtenerWidgets().size() + " widgets activos).");
        });

        //ordenar por consumo de memoria (KB)
        view.getBtnOrdenar().addActionListener(e -> {
            model.ordenarPorMemoria();
            view.actualizarTabla(model.obtenerWidgets());
            view.mostrarDetalle("Widgets ordenados ascendentemente por consumo de memoria (KB).");
        });

        //buscar por ID o Nombre (Sobrecarga)
        view.getBtnBuscar().addActionListener(e -> ejecutarBusqueda());

        // ejecutar interaccion polimorfica segura
        view.getBtnInteractuar().addActionListener(e -> ejecutarInteraccion());
    }

    private void ejecutarBusqueda() {
        String criterio = view.getTextoBusqueda().trim();
        if (criterio.isEmpty()) {
            view.mostrarError("Por favor ingrese un ID o nombre para buscar");
            return;
        }

        // intenta busqueda por ID exacto
        Widget hallado = model.buscarWidget(criterio);
        if (hallado != null) {
            view.actualizarTabla(List.of(hallado));
            view.mostrarDetalle("BUSQUEDA POR ID EXACTO:\n" + hallado.obtenerDetallesConfiguracion());
            return;
        }

        // intenta busqueda por coincidencia de nombre
        List<Widget> coincidencias = model.buscarWidget(criterio, true);
        if (!coincidencias.isEmpty()) {
            view.actualizarTabla(coincidencias);
            StringBuilder sb = new StringBuilder("COINCIDENCIAS POR NOMBRE (" + coincidencias.size() + "):\n");
            for (Widget w : coincidencias) {
                sb.append("- ").append(w.obtenerDetallesConfiguracion()).append("\n");
            }
            view.mostrarDetalle(sb.toString());
        } else {
            view.mostrarError("No se encontraron widgets que coincidan con: " + criterio);
        }
    }

    private void ejecutarInteraccion() {
        int filaSeleccionada = view.getTablaWidgets().getSelectedRow();
        if (filaSeleccionada == -1) {
            view.mostrarError("Seleccione un widget de la tabla para probar la interaccion.");
            return;
        }

        String id = (String) view.getTablaWidgets().getValueAt(filaSeleccionada, 0);
        Widget w = model.buscarWidget(id);

        if (w != null) {
            // Verificacion polimorfica segura del tipo de interfaz
            if (w instanceof Interactivo) {
                Interactivo interactivo = (Interactivo) w;
                String resClic = interactivo.manejarClic();
                String resActualizacion = interactivo.actualizarTiempoReal();
                view.mostrarDetalle("INTERACCION EXITOSA (" + w.getNombre() + "):\n" +
                        "1. Accion Clic: " + resClic + "\n" +
                        "2. Actualizacion: " + resActualizacion);
                view.actualizarTabla(model.obtenerWidgets());
            } else {
                view.mostrarDetalle("ELEMENTO ESTATICO (" + w.getNombre() + "):\n" +
                        "Este widget es de tipo estatico y no soporta la interfaz Interactivo" +
                        "El sistema reproducio sobre el de forma segura sin colapsar.");
            }
        }
    }
}
