import java.util.List;

public class DashboardController {
    private DashboardModel model;
    private DashboardGUIView view;

    public DashboardController(DashboardModel model, DashboardGUIView view) {
        this.model = model;
        this.view = view;
        inicializarEventos();
        refrescarVistaTarjetas(this.model.obtenerWidgets());
    }

    private void inicializarEventos() {
        // enlista todos los widgets
        view.getBtnListar().addActionListener(e -> {
            refrescarVistaTarjetas(model.obtenerWidgets());
            view.mostrarDetalle("Mostrando catálogo completo (" + model.obtenerWidgets().size() + " widgets activos).");
        });

        // ordena por consumo de memoria (KB) 
        view.getBtnOrdenar().addActionListener(e -> {
            model.ordenarPorMemoria();
            refrescarVistaTarjetas(model.obtenerWidgets());
            view.mostrarDetalle("Widgets ordenados de menor a mayor consumo de memoria (KB).");
        });

        // busca por ID o Nombre que es sobrecarga
        view.getBtnBuscar().addActionListener(e -> ejecutarBusqueda());
    }

    private void refrescarVistaTarjetas(List<Widget> lista) {
        view.actualizarTarjetas(lista, this::manejarClicEnTarjeta);
    }

    private void manejarClicEnTarjeta(Widget w) {
        if (w instanceof Interactivo) {
            Interactivo interactivo = (Interactivo) w;
            String resClic = interactivo.manejarClic();
            String resActualización = interactivo.actualizarTiempoReal();
            
            refrescarVistaTarjetas(model.obtenerWidgets()); // Re-renderiza cambios en tiempo real
            view.mostrarDetalle("INTERACCIÓN EXITOSA [" + w.getNombre() + "]:\n" +
                    "• Acción Clic: " + resClic + "\n" +
                    "• Actualización: " + resActualización + "\n" +
                    "• Detalles: " + w.obtenerDetallesConfiguracion());
        } else {
            view.mostrarDetalle("ELEMENTO ESTÁTICO [" + w.getNombre() + "]:\n" +
                    "• Este widget no soporta la interfaz Interactivo. El sistema iteró sobre él de forma segura sin colapsar.\n" +
                    "• Detalles: " + w.obtenerDetallesConfiguracion());
        }
    }

    private void ejecutarBusqueda() {
        String criterio = view.getTextoBusqueda().trim();
        if (criterio.isEmpty()) {
            view.mostrarError("Por favor ingrese un ID o nombre para buscar.");
            return;
        }

        // Búsqueda por ID exacto sobrecarga1 
        Widget hallado = model.buscarWidget(criterio);
        if (hallado != null) {
            refrescarVistaTarjetas(List.of(hallado));
            view.mostrarDetalle("BÚSQUEDA POR ID EXACTO:\n" + hallado.obtenerDetallesConfiguracion());
            return;
        }

        // Búsqueda por coincidencia de nombre sobrecarga2
        List<Widget> coincidencias = model.buscarWidget(criterio, true);
        if (!coincidencias.isEmpty()) {
            refrescarVistaTarjetas(coincidencias);
            StringBuilder sb = new StringBuilder("COINCIDENCIAS POR NOMBRE (" + coincidencias.size() + "):\n");
            for (Widget w : coincidencias) {
                sb.append("• ").append(w.obtenerDetallesConfiguracion()).append("\n");
            }
            view.mostrarDetalle(sb.toString());
        } else {
            view.mostrarError("No se encontraron widgets que coincidan con: " + criterio);
        }
    }
}
