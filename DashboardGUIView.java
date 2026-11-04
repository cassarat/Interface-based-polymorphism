import javax.swing.*;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.util.List;

//vista moderna del dashboard
public class DashboardGUIView extends JFrame {
    private JPanel panelTarjetas;
    private JScrollPane scrollPane;
    private JButton btnListar;
    private JButton btnBuscar;
    private JButton btnOrdenar;
    private JTextField txtBusqueda;
    private JTextArea txtDetalles;

    public DashboardGUIView() {
        setTitle("Dashboard Modular Enterprise - CC2008 POO");
        setSize(1000, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(15, 15));
        getContentPane().setBackground(new Color(240, 242, 245));

        // Panel Superior: Barra de navegación y controles
        JPanel panelNavegacion = new JPanel(new FlowLayout(FlowLayout.LEFT, 15, 12));
        panelNavegacion.setBackground(Color.WHITE);
        panelNavegacion.setBorder(new LineBorder(new Color(220, 224, 230), 1));

        btnListar = crearBotonEstilizado("Listar Todos", new Color(52, 152, 219));
        btnOrdenar = crearBotonEstilizado("Ordenar por Memoria (KB)", new Color(155, 89, 182));
        
        txtBusqueda = new JTextField(14);
        txtBusqueda.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        btnBuscar = crearBotonEstilizado("Buscar", new Color(46, 204, 113));

        panelNavegacion.add(btnListar);
        panelNavegacion.add(btnOrdenar);
        panelNavegacion.add(new JLabel(" | Buscar ID/Nombre: "));
        panelNavegacion.add(txtBusqueda);
        panelNavegacion.add(btnBuscar);

        add(panelNavegacion, BorderLayout.NORTH);

        // el panel central que son las grillas de tarjetas que son e columnas 
        panelTarjetas = new JPanel(new GridLayout(0, 3, 15, 15));
        panelTarjetas.setBackground(new Color(240, 242, 245));
        panelTarjetas.setBorder(new EmptyBorder(10, 15, 10, 15));

        scrollPane = new JScrollPane(panelTarjetas);
        scrollPane.setBorder(null);
        scrollPane.getVerticalScrollBar().setUnitIncrement(16);
        add(scrollPane, BorderLayout.CENTER);

        // panel inferior de  consola de estado e interacciones
        JPanel panelInferior = new JPanel(new BorderLayout());
        panelInferior.setBorder(BorderFactory.createTitledBorder("Consola de diagnostico e interacciones"));
        txtDetalles = new JTextArea(4, 50);
        txtDetalles.setEditable(false);
        txtDetalles.setFont(new Font("Consolas", Font.PLAIN, 13));
        txtDetalles.setBackground(new Color(28, 35, 45));
        txtDetalles.setForeground(new Color(236, 240, 241));
        panelInferior.add(new JScrollPane(txtDetalles), BorderLayout.CENTER);

        add(panelInferior, BorderLayout.SOUTH);
    }

    public void hacerVisible() {
        setVisible(true);
    }

   
     //Construye y renderiza las tarjetas visuales para cada Widget
   
    public void actualizarTarjetas(List<Widget> widgets, java.util.function.Consumer<Widget> callbackInteraccion) {
        panelTarjetas.removeAll();

        for (Widget w : widgets) {
            JPanel card = crearTarjetaWidget(w, callbackInteraccion);
            panelTarjetas.add(card);
        }

        panelTarjetas.revalidate();
        panelTarjetas.repaint();
    }

    private JPanel crearTarjetaWidget(Widget w, java.util.function.Consumer<Widget> callbackInteraccion) {
        JPanel card = new JPanel(new BorderLayout(8, 8));
        boolean esInteractivo = (w instanceof Interactivo);

        // Color segun tipo de widget
        Color colorEncabezado = esInteractivo ? new Color(41, 128, 185) : new Color(127, 140, 141);
        card.setBackground(Color.WHITE);
        card.setBorder(new CompoundBorder(
                new LineBorder(new Color(218, 225, 231), 1, true),
                new EmptyBorder(12, 12, 12, 12)
        ));

        // Encabezado de la tarjeta ID + Nombre
        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.setOpaque(false);

        JLabel lblId = new JLabel("[" + w.getId() + "] " + w.getNombre());
        lblId.setFont(new Font("Segoe UI", Font.BOLD, 14));
        lblId.setForeground(colorEncabezado);

        JLabel lblMemoria = new JLabel(w.getConsumoMemoriaKB() + " KB");
        lblMemoria.setFont(new Font("Segoe UI", Font.BOLD, 11));
        lblMemoria.setForeground(new Color(149, 165, 166));

        topPanel.add(lblId, BorderLayout.WEST);
        topPanel.add(lblMemoria, BorderLayout.EAST);

        // Cuerpo de la tarjeta
        JTextArea areaRender = new JTextArea(w.renderizar());
        areaRender.setWrapStyleWord(true);
        areaRender.setLineWrap(true);
        areaRender.setEditable(false);
        areaRender.setFocusable(false);
        areaRender.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        areaRender.setBackground(new Color(248, 249, 250));
        areaRender.setBorder(new EmptyBorder(8, 8, 8, 8));

        // Pie de la tarjeta (Tipo e Interacción)
        JPanel bottomPanel = new JPanel(new BorderLayout());
        bottomPanel.setOpaque(false);

        JLabel lblTipo = new JLabel(esInteractivo ? "● Interactivo" : "○ Estático");
        lblTipo.setFont(new Font("Segoe UI", Font.ITALIC, 11));
        lblTipo.setForeground(esInteractivo ? new Color(39, 174, 96) : new Color(149, 165, 166));

        JButton btnAccion = new JButton(esInteractivo ? "Interactuar" : "Detalles");
        btnAccion.setFont(new Font("Segoe UI", Font.PLAIN, 11));
        btnAccion.addActionListener(e -> callbackInteraccion.accept(w));

        bottomPanel.add(lblTipo, BorderLayout.WEST);
        bottomPanel.add(btnAccion, BorderLayout.EAST);

        card.add(topPanel, BorderLayout.NORTH);
        card.add(areaRender, BorderLayout.CENTER);
        card.add(bottomPanel, BorderLayout.SOUTH);

        return card;
    }

    private JButton crearBotonEstilizado(String texto, Color bg) {
        JButton btn = new JButton(texto);
        btn.setFont(new Font("Segoe UI", Font.BOLD, 12));
        btn.setBackground(bg);
        btn.setForeground(Color.WHITE);
        btn.setFocusPainted(false);
        return btn;
    }

    public void mostrarDetalle(String mensaje) {
        txtDetalles.setText(mensaje);
    }

    public void mostrarError(String mensaje) {
        JOptionPane.showMessageDialog(this, mensaje, "Atención", JOptionPane.WARNING_MESSAGE);
    }

    public JButton getBtnListar() { return btnListar; }
    public JButton getBtnBuscar() { return btnBuscar; }
    public JButton getBtnOrdenar() { return btnOrdenar; }
    public String getTextoBusqueda() { return txtBusqueda.getText(); }
}