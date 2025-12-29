package Views;

import Controllers.ProductController;
import Controllers.SalesController;
import Models.Product;
import Models.Sale;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class MainGUI {

    private JFrame frame;

    private ProductController productController;
    private SalesController salesController;

    private DefaultTableModel productTableModel;
    private DefaultTableModel salesTableModel;

    private JLabel totalLabel;

    public MainGUI() {
        productController = new ProductController();
        salesController = new SalesController();
    }

    public void start() {
        frame = new JFrame("Manajemen Produk & Penjualan");
        frame.setSize(800, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);

        JPanel mainPanel = new JPanel(new BorderLayout());

        mainPanel.add(createTopPanel(), BorderLayout.NORTH);
        mainPanel.add(createCenterPanel(), BorderLayout.CENTER);
        mainPanel.add(createBottomPanel(), BorderLayout.SOUTH);

        frame.add(mainPanel);
        frame.setVisible(true);
    }

    /* ===================== TOP PANEL ===================== */

    private JPanel createTopPanel() {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT));

        JButton loadProductBtn = new JButton("Load Produk");
        JButton loadSalesBtn = new JButton("Load Penjualan");

        loadProductBtn.addActionListener(e -> loadProducts());
        loadSalesBtn.addActionListener(e -> loadSales());

        panel.add(loadProductBtn);
        panel.add(loadSalesBtn);

        return panel;
    }

    /* ===================== CENTER PANEL ===================== */

    private JSplitPane createCenterPanel() {
        JSplitPane splitPane = new JSplitPane(
                JSplitPane.VERTICAL_SPLIT,
                createProductTablePanel(),
                createSalesTablePanel()
        );

        splitPane.setDividerLocation(250);
        splitPane.setResizeWeight(0.5);

        return splitPane;
    }

    private JScrollPane createProductTablePanel() {
        String[] columns = {"ID", "Kode", "Nama", "Harga", "Stok"};
        productTableModel = new DefaultTableModel(columns, 0);
        JTable table = new JTable(productTableModel);

        JPanel panel = new JPanel(new BorderLayout());
        panel.add(new JLabel(" DAFTAR PRODUK"), BorderLayout.NORTH);
        panel.add(new JScrollPane(table), BorderLayout.CENTER);

        return new JScrollPane(panel);
    }

    private JScrollPane createSalesTablePanel() {
        String[] columns = {"ID", "Produk", "Qty", "Harga", "Total"};
        salesTableModel = new DefaultTableModel(columns, 0);
        JTable table = new JTable(salesTableModel);

        JPanel panel = new JPanel(new BorderLayout());
        panel.add(new JLabel(" DAFTAR TRANSAKSI"), BorderLayout.NORTH);
        panel.add(new JScrollPane(table), BorderLayout.CENTER);

        return new JScrollPane(panel);
    }

    /* ===================== BOTTOM PANEL ===================== */

    private JPanel createBottomPanel() {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        totalLabel = new JLabel("TOTAL PENJUALAN = 0");
        panel.add(totalLabel);
        return panel;
    }

    /* ===================== LOGIC ===================== */

    private void loadProducts() {
        productTableModel.setRowCount(0);

        for (Product p : productController.getAllProducts()) {
            productTableModel.addRow(new Object[]{
                    p.getId(),
                    p.getKode(),
                    p.getNama(),
                    p.getHarga(),
                    p.getStok()
            });
        }
    }

    private void loadSales() {
        salesTableModel.setRowCount(0);
        double total = 0;

        for (Sale s : salesController.getSales()) {
            salesTableModel.addRow(new Object[]{
                    s.getId(),
                    s.getProductName(),
                    s.getQty(),
                    s.getPrice(),
                    s.getTotal()
            });
            total += s.getTotal();
        }

        totalLabel.setText("TOTAL PENJUALAN = " + total);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new MainGUI().start());
    }
}
