import javax.swing.SwingUtilities;

//Driver program de la app

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            DashboardModel model = new DashboardModel();
            DashboardGUIView view = new DashboardGUIView();
            new DashboardController(model, view);
            view.hacerVisible();
        });
    }
}