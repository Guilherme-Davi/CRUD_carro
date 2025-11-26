import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
           TelaCarro tela = new TelaCarro();
           tela.setVisible(true);
        });
    }
}