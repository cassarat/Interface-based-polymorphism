import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class DashboardGUIView extends JFrame {
    private JTable tablaWidgets;
    private DefaultTableModel tableModel;
    private JButton btnListar;
    private JButton btnBuscar;
    private JButton btnOrdenar;
    private JButton btnInteractuar;
    private JTextField txtBusqueda;
    private JTextArea txtDetalles;

    public DashboardGUIView() {
        setTitle("Dashboard Modular Enterprise - CC2008 POO");
        setSize(850, 550);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10, 10));

        // panel de controles y busqueda
        JPanel panelControl = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 10));
        btnListar = new JButton("Listar Activos");
        btnOrdenar = new JButton("Ordenar por Memoria (KB)");
        btnInteractuar = new JButton("Probar Interaccion");
        
        txtBusqueda = new JTextField(12);
        btnBuscar = new JButton("Buscar (ID/Nombre)");

        panelControl.add(btnListar);
        panelControl.add(btnOrdenar);
        panelControl.add(new JLabel(" | Buscar:"));
        panelControl.add(txtBusqueda);
        panelControl.add(btnBuscar);
        panelControl.add(btnInteractuar);
        add(panelControl, BorderLayout.NORTH);

        // tabla central que muestra la lsita de widgets
        String[] columnas = {"ID", "Nombre Widget", "Memoria (KB)", "Tipo / Interactivo", "Renderizado"};
        tableModel = new DefaultTableModel(columnas, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        tablaWidgets = new JTable(tableModel);
        add(new JScrollPane(tablaWidgets), BorderLayout.CENTER);

        //panel que detalla la configuracion e interacciones
        JPanel panelDetalles = new JPanel(new BorderLayout());
        panelDetalles.setBorder(BorderFactory.createTitledBorder("Detalles de Configuración e Interacción"));
        txtDetalles = new JTextArea(5, 50);
        txtDetalles.setEditable(false);
        txtDetalles.setFont(new Font("Monospaced", Font.PLAIN, 12));
        panelDetalles.add(new JScrollPane(txtDetalles), BorderLayout.CENTER);
        add(panelDetalles, BorderLayout.SOUTH);
    }

    public void hacerVisible() {
        setVisible(true);
    }

    public void actualizarTabla(List<Widget> listaWidgets) {
        tableModel.setRowCount(0);
        for (Widget w : listaWidgets) {
            String esInteractivo = (w instanceof Interactivo) ? "Si (Interactivo)" : "No (Estatico)";
            Object[] fila = {
                w.getId(),
                w.getNombre(),
                w.getConsumoMemoriaKB() + " KB",
                esInteractivo,
                w.renderizar()
            };
            tableModel.addRow(fila);
        }
    }

    public void mostrarDetalle(String mensaje) {
        txtDetalles.setText(mensaje);
    }

    public void mostrarError(String mensaje) {
        JOptionPane.showMessageDialog(this, mensaje, "Atencion", JOptionPane.WARNING_MESSAGE);
    }

    public JTable getTablaWidgets() { return tablaWidgets; }
    public JButton getBtnListar() { return btnListar; }
    public JButton getBtnBuscar() { return btnBuscar; }
    public JButton getBtnOrdenar() { return btnOrdenar; }
    public JButton getBtnInteractuar() { return btnInteractuar; }
    public String getTextoBusqueda() { return txtBusqueda.getText(); }
}