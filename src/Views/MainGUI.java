package Views;

import javax.swing.*;
import java.awt.*;

public class MainGUI {

    private JFrame frame;

    public void start() {
        frame = new JFrame("Penjualan");
        frame.setSize(700, 500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);

        // Canvas kosong bisa di costum nanti disini
        JPanel canvas = new JPanel();
        canvas.setBackground(Color.WHITE);
        canvas.setLayout(null); // costum drawing disini yaa

        frame.add(canvas);
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new MainGUI().start());
    }
}
