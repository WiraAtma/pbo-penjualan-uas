package Views;

import Controllers.ProductController;
import Controllers.SalesController;
import Models.Product;
import Models.Sale;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import java.awt.*;

public class MainGUI {

    private JFrame frame;

    private ProductController productController;
    private SalesController salesController;

    private DefaultTableModel productTableModel;
    private DefaultTableModel salesTableModel;

    private TableRowSorter<DefaultTableModel> productSorter;
    private TableRowSorter<DefaultTableModel> salesSorter;

    private JLabel totalLabel;

    public MainGUI() {
        productController = new ProductController();
        salesController = new SalesController();
    }

    public void start() {
        frame = new JFrame("Sistem Manajemen Produk & Penjualan");
        frame.setSize(1100, 750);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);

        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBackground(Color.WHITE);

        mainPanel.add(createTopPanel(), BorderLayout.NORTH);
        mainPanel.add(createCenterPanel(), BorderLayout.CENTER);
        mainPanel.add(createBottomPanel(), BorderLayout.SOUTH);

        frame.add(mainPanel);
        frame.setVisible(true);

        refreshAll();
    }

    /* ===================== TOP PANEL ===================== */

    private JPanel createTopPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(new Color(240, 240, 240));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 15, 10, 15));

        JLabel titleLabel = new JLabel("Sistem Manajemen Produk & Penjualan");
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 16));

        JButton refreshBtn = new JButton("Refresh");
        refreshBtn.addActionListener(e -> refreshAll());
        styleButton(refreshBtn, new Color(100, 150, 200));

        panel.add(titleLabel, BorderLayout.WEST);
        panel.add(refreshBtn, BorderLayout.EAST);

        return panel;
    }

    /* ===================== CENTER PANEL ===================== */

    private JSplitPane createCenterPanel() {
        JSplitPane splitPane = new JSplitPane(
                JSplitPane.VERTICAL_SPLIT,
                createProductPanel(),
                createSalesPanel()
        );

        splitPane.setDividerLocation(350);
        splitPane.setResizeWeight(0.5);
        splitPane.setBorder(null);

        return splitPane;
    }

    private JPanel createProductPanel() {
        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBackground(Color.WHITE);
        mainPanel.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));

        // Header Panel
        JPanel headerPanel = new JPanel(new BorderLayout());
        headerPanel.setBackground(Color.WHITE);
        headerPanel.setBorder(BorderFactory.createEmptyBorder(5, 5, 10, 5));

        JLabel title = new JLabel("Daftar Produk");
        title.setFont(new Font("Segoe UI", Font.BOLD, 14));

        // Button Panel
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 5, 0));
        buttonPanel.setBackground(Color.WHITE);

        JButton addProductBtn = new JButton("Tambah");
        JButton deleteProductBtn = new JButton("Hapus");

        addProductBtn.addActionListener(e -> showAddProductDialog());
        deleteProductBtn.addActionListener(e -> deleteSelectedProduct());

        styleButton(addProductBtn, new Color(80, 180, 100));
        styleButton(deleteProductBtn, new Color(220, 80, 80));

        buttonPanel.add(addProductBtn);
        buttonPanel.add(deleteProductBtn);

        headerPanel.add(title, BorderLayout.WEST);
        headerPanel.add(buttonPanel, BorderLayout.EAST);

        // Search Panel
        JPanel searchPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 5));
        searchPanel.setBackground(Color.WHITE);

        JLabel lblSearch = new JLabel("Search:");
        JTextField txtSearch = new JTextField(25);
        txtSearch.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(200, 200, 200)),
                BorderFactory.createEmptyBorder(5, 8, 5, 8)
        ));

        searchPanel.add(lblSearch);
        searchPanel.add(txtSearch);

        // Table
        String[] columns = {"No", "Kode", "Nama", "Harga", "Stok"};
        productTableModel = new DefaultTableModel(columns, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        JTable table = new JTable(productTableModel);
        styleTable(table);

        productSorter = new TableRowSorter<>(productTableModel);
        table.setRowSorter(productSorter);

        txtSearch.addCaretListener(e -> {
            String text = txtSearch.getText();
            if (text.trim().length() == 0) {
                productSorter.setRowFilter(null);
            } else {
                productSorter.setRowFilter(RowFilter.regexFilter("(?i)" + text));
            }
        });

        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBorder(BorderFactory.createLineBorder(new Color(220, 220, 220)));

        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.setBackground(Color.WHITE);
        topPanel.add(headerPanel, BorderLayout.NORTH);
        topPanel.add(searchPanel, BorderLayout.SOUTH);

        mainPanel.add(topPanel, BorderLayout.NORTH);
        mainPanel.add(scrollPane, BorderLayout.CENTER);

        return mainPanel;
    }

    private JPanel createSalesPanel() {
        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBackground(Color.WHITE);
        mainPanel.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));

        // Header Panel
        JPanel headerPanel = new JPanel(new BorderLayout());
        headerPanel.setBackground(Color.WHITE);
        headerPanel.setBorder(BorderFactory.createEmptyBorder(5, 5, 10, 5));

        JLabel title = new JLabel("Daftar Transaksi");
        title.setFont(new Font("Segoe UI", Font.BOLD, 14));

        headerPanel.add(title, BorderLayout.WEST);

        // Search Panel
        JPanel searchPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 5));
        searchPanel.setBackground(Color.WHITE);

        JLabel lblSearch = new JLabel("Search:");
        JTextField txtSearch = new JTextField(25);
        txtSearch.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(200, 200, 200)),
                BorderFactory.createEmptyBorder(5, 8, 5, 8)
        ));

        searchPanel.add(lblSearch);
        searchPanel.add(txtSearch);

        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.setBackground(Color.WHITE);
        topPanel.add(headerPanel, BorderLayout.NORTH);
        topPanel.add(searchPanel, BorderLayout.SOUTH);

        // Table
        String[] columns = {"No", "Produk", "Qty", "Harga", "Total"};
        salesTableModel = new DefaultTableModel(columns, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        JTable table = new JTable(salesTableModel);
        styleTable(table);

        salesSorter = new TableRowSorter<>(salesTableModel);
        table.setRowSorter(salesSorter);

        txtSearch.addCaretListener(e -> {
            String text = txtSearch.getText();
            if (text.trim().length() == 0) {
                salesSorter.setRowFilter(null);
            } else {
                salesSorter.setRowFilter(RowFilter.regexFilter("(?i)" + text));
            }
        });

        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBorder(BorderFactory.createLineBorder(new Color(220, 220, 220)));

        mainPanel.add(topPanel, BorderLayout.NORTH);
        mainPanel.add(scrollPane, BorderLayout.CENTER);

        // Button Panel
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 8));
        buttonPanel.setBackground(Color.WHITE);

        JButton addSalesBtn = new JButton("Transaksi Baru");
        addSalesBtn.addActionListener(e -> showSalesDialog());
        styleButton(addSalesBtn, new Color(100, 140, 200));

        buttonPanel.add(addSalesBtn);
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);

        return mainPanel;
    }

    /* ===================== BOTTOM PANEL ===================== */

    private JPanel createBottomPanel() {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 15, 10));
        panel.setBackground(new Color(240, 240, 240));
        panel.setBorder(BorderFactory.createMatteBorder(1, 0, 0, 0, new Color(200, 200, 200)));

        JLabel label = new JLabel("Total Penjualan:");
        label.setFont(new Font("Segoe UI", Font.PLAIN, 13));

        totalLabel = new JLabel("Rp 0");
        totalLabel.setFont(new Font("Segoe UI", Font.BOLD, 15));
        totalLabel.setForeground(new Color(80, 180, 100));

        panel.add(label);
        panel.add(totalLabel);
        return panel;
    }

    /* ===================== STYLING HELPERS ===================== */

    private void styleTable(JTable table) {
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        table.setRowHeight(28);
        table.setFont(new Font("Segoe UI", Font.BOLD, 12));
        table.setShowGrid(true);
        table.setGridColor(new Color(230, 230, 230));
        table.setSelectionBackground(new Color(184, 207, 229));
        table.setSelectionForeground(Color.BLACK);

        // Header - gray background
        table.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 12));
        table.getTableHeader().setBackground(new Color(220, 220, 220));
        table.getTableHeader().setForeground(Color.BLACK);
        table.getTableHeader().setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, new Color(180, 180, 180)));

        // Alternating row colors
        table.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value,
                                                           boolean isSelected, boolean hasFocus, int row, int column) {
                Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);

                // Make text bold
                setFont(new Font("Segoe UI", Font.BOLD, 12));

                if (!isSelected) {
                    if (row % 2 == 0) {
                        c.setBackground(Color.WHITE);
                    } else {
                        c.setBackground(new Color(248, 248, 248));
                    }
                }
                return c;
            }
        });
    }

    private void styleButton(JButton button, Color bgColor) {
        button.setBackground(bgColor);
        button.setForeground(Color.WHITE);
        button.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createEmptyBorder(6, 15, 6, 15));
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
    }

    /* ===================== REFRESH ALL ===================== */

    private void refreshAll() {
        loadProducts();
        loadSales();
    }

    /* ===================== LOGIC - PRODUCT ===================== */

    private void loadProducts() {
        productTableModel.setRowCount(0);

        int no = 1;
        for (Product p : productController.getAllProducts()) {
            productTableModel.addRow(new Object[]{
                    no++,
                    p.getKode(),
                    p.getNama(),
                    "Rp " + String.format("%,d", (long)p.getHarga()),
                    p.getStok()
            });
        }
    }

    private void showAddProductDialog() {
        JDialog dialog = new JDialog(frame, "Tambah Produk", true);
        dialog.setSize(420, 280);
        dialog.setLocationRelativeTo(frame);
        dialog.setLayout(new BorderLayout());

        JPanel contentPanel = new JPanel(new BorderLayout());
        contentPanel.setBackground(Color.WHITE);

        JPanel formPanel = new JPanel(new GridLayout(4, 2, 10, 12));
        formPanel.setBorder(BorderFactory.createEmptyBorder(20, 25, 20, 25));
        formPanel.setBackground(Color.WHITE);

        JLabel lblKode = new JLabel("Kode:");
        JTextField txtKode = new JTextField();
        styleTextField(txtKode);

        JLabel lblNama = new JLabel("Nama:");
        JTextField txtNama = new JTextField();
        styleTextField(txtNama);

        JLabel lblHarga = new JLabel("Harga:");
        JTextField txtHarga = new JTextField();
        styleTextField(txtHarga);

        JLabel lblStok = new JLabel("Stok:");
        JTextField txtStok = new JTextField();
        styleTextField(txtStok);

        formPanel.add(lblKode);
        formPanel.add(txtKode);
        formPanel.add(lblNama);
        formPanel.add(txtNama);
        formPanel.add(lblHarga);
        formPanel.add(txtHarga);
        formPanel.add(lblStok);
        formPanel.add(txtStok);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 15));
        buttonPanel.setBackground(new Color(245, 245, 245));

        JButton btnSave = new JButton("Simpan");
        JButton btnCancel = new JButton("Batal");

        styleButton(btnSave, new Color(80, 180, 100));
        styleButton(btnCancel, new Color(160, 160, 160));

        btnSave.addActionListener(e -> {
            try {
                String kode = txtKode.getText().trim();
                String nama = txtNama.getText().trim();
                double harga = Double.parseDouble(txtHarga.getText().trim());
                int stok = Integer.parseInt(txtStok.getText().trim());

                if (kode.isEmpty() || nama.isEmpty()) {
                    JOptionPane.showMessageDialog(dialog, "Kode dan Nama tidak boleh kosong!");
                    return;
                }

                productController.addProduct(kode, nama, harga, stok);
                refreshAll();
                JOptionPane.showMessageDialog(dialog, "Produk berhasil ditambahkan!");
                dialog.dispose();

            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(dialog, "Harga dan Stok harus berupa angka!");
            }
        });

        btnCancel.addActionListener(e -> dialog.dispose());

        buttonPanel.add(btnSave);
        buttonPanel.add(btnCancel);

        contentPanel.add(formPanel, BorderLayout.CENTER);
        contentPanel.add(buttonPanel, BorderLayout.SOUTH);

        dialog.add(contentPanel);
        dialog.setVisible(true);
    }

    private void styleTextField(JTextField field) {
        field.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(200, 200, 200)),
                BorderFactory.createEmptyBorder(5, 8, 5, 8)
        ));
    }

    private void deleteSelectedProduct() {
        int selectedRow = getSelectedRowFromTable(productTableModel);

        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(frame, "Pilih produk yang ingin dihapus!");
            return;
        }

        String productName = productTableModel.getValueAt(selectedRow, 2).toString();
        int confirm = JOptionPane.showConfirmDialog(frame,
                "Hapus produk: " + productName + "?",
                "Konfirmasi",
                JOptionPane.YES_NO_OPTION);

        if (confirm == JOptionPane.YES_OPTION) {
            productController.deleteProductByIndex(selectedRow);
            refreshAll();
            JOptionPane.showMessageDialog(frame, "Produk berhasil dihapus!");
        }
    }

    private int getSelectedRowFromTable(DefaultTableModel model) {
        JTable table = null;

        for (Component comp : frame.getContentPane().getComponents()) {
            if (comp instanceof JPanel) {
                table = findTableInComponent((JPanel) comp, model);
                if (table != null) break;
            }
        }

        return table != null ? table.getSelectedRow() : -1;
    }

    private JTable findTableInComponent(Container container, DefaultTableModel model) {
        for (Component comp : container.getComponents()) {
            if (comp instanceof JScrollPane) {
                JScrollPane scrollPane = (JScrollPane) comp;
                Component view = scrollPane.getViewport().getView();
                if (view instanceof JTable) {
                    JTable table = (JTable) view;
                    if (table.getModel() == model) {
                        return table;
                    }
                }
            } else if (comp instanceof JSplitPane) {
                JSplitPane splitPane = (JSplitPane) comp;
                JTable found = findTableInComponent((Container) splitPane.getLeftComponent(), model);
                if (found != null) return found;
                found = findTableInComponent((Container) splitPane.getRightComponent(), model);
                if (found != null) return found;
            } else if (comp instanceof Container) {
                JTable found = findTableInComponent((Container) comp, model);
                if (found != null) return found;
            }
        }
        return null;
    }

    /* ===================== LOGIC - SALES ===================== */

    private void loadSales() {
        salesTableModel.setRowCount(0);
        double total = 0;

        int no = 1;
        for (Sale s : salesController.getSales()) {
            salesTableModel.addRow(new Object[]{
                    no++,
                    s.getProductName(),
                    s.getQty(),
                    "Rp " + String.format("%,d", (long)s.getPrice()),
                    "Rp " + String.format("%,d", (long)s.getTotal())
            });
            total += s.getTotal();
        }

        totalLabel.setText("Rp " + String.format("%,d", (long)total));
    }

    private void showSalesDialog() {
        java.util.List<Product> products = salesController.getProducts();

        if (products.isEmpty()) {
            JOptionPane.showMessageDialog(frame, "Tidak ada produk tersedia!");
            return;
        }

        JDialog dialog = new JDialog(frame, "Transaksi Penjualan", true);
        dialog.setSize(650, 550);
        dialog.setLocationRelativeTo(frame);
        dialog.setLayout(new BorderLayout());

        JPanel contentPanel = new JPanel(new BorderLayout(0, 10));
        contentPanel.setBackground(Color.WHITE);
        contentPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

        // Search Panel
        JPanel searchPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
        searchPanel.setBackground(Color.WHITE);

        JLabel lblSearch = new JLabel("Search:");
        lblSearch.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 10));
        JTextField txtSearch = new JTextField(30);
        styleTextField(txtSearch);

        searchPanel.add(lblSearch);
        searchPanel.add(txtSearch);

        // Product Table
        String[] columns = {"Kode", "Nama", "Harga", "Stok"};
        DefaultTableModel productDialogModel = new DefaultTableModel(columns, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        JTable productTable = new JTable(productDialogModel);
        styleTable(productTable);

        for (Product p : products) {
            productDialogModel.addRow(new Object[]{
                    p.getKode(),
                    p.getNama(),
                    "Rp " + String.format("%,d", (long)p.getHarga()),
                    p.getStok()
            });
        }

        TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<>(productDialogModel);
        productTable.setRowSorter(sorter);

        txtSearch.addCaretListener(e -> {
            String text = txtSearch.getText();
            if (text.trim().length() == 0) {
                sorter.setRowFilter(null);
            } else {
                sorter.setRowFilter(RowFilter.regexFilter("(?i)" + text));
            }
        });

        JScrollPane tableScrollPane = new JScrollPane(productTable);
        tableScrollPane.setBorder(BorderFactory.createLineBorder(new Color(220, 220, 220)));
        tableScrollPane.setPreferredSize(new Dimension(600, 250));

        // Bottom Panel
        JPanel bottomPanel = new JPanel(new BorderLayout(0, 10));
        bottomPanel.setBackground(Color.WHITE);

        JPanel qtyPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
        qtyPanel.setBackground(Color.WHITE);

        JLabel lblQty = new JLabel("Jumlah:");
        lblQty.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 10));
        JTextField txtQty = new JTextField(12);
        styleTextField(txtQty);

        qtyPanel.add(lblQty);
        qtyPanel.add(txtQty);

        JTextArea infoArea = new JTextArea(4, 40);
        infoArea.setEditable(false);
        infoArea.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        infoArea.setBackground(new Color(250, 250, 250));
        infoArea.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(220, 220, 220)),
                BorderFactory.createEmptyBorder(10, 10, 10, 10)
        ));

        Runnable updateInfo = () -> {
            int selectedRow = productTable.getSelectedRow();
            if (selectedRow >= 0) {
                try {
                    int modelRow = productTable.convertRowIndexToModel(selectedRow);
                    Product p = products.get(modelRow);
                    int qty = txtQty.getText().isEmpty() ? 0 : Integer.parseInt(txtQty.getText());
                    double total = qty * p.getHarga();

                    infoArea.setText(String.format(
                            "Produk: %s\nJumlah: %d x Rp %,d\nTotal: Rp %,d",
                            p.getNama(), qty, (long)p.getHarga(), (long)total
                    ));
                } catch (NumberFormatException e) {
                    infoArea.setText("Masukkan jumlah yang valid");
                }
            }
        };

        productTable.getSelectionModel().addListSelectionListener(e -> updateInfo.run());
        txtQty.addCaretListener(e -> updateInfo.run());

        bottomPanel.add(qtyPanel, BorderLayout.NORTH);
        bottomPanel.add(infoArea, BorderLayout.CENTER);

        contentPanel.add(searchPanel, BorderLayout.NORTH);
        contentPanel.add(tableScrollPane, BorderLayout.CENTER);
        contentPanel.add(bottomPanel, BorderLayout.SOUTH);

        // Button Panel
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 15));
        buttonPanel.setBackground(new Color(245, 245, 245));

        JButton btnSave = new JButton("Proses");
        JButton btnCancel = new JButton("Batal");

        styleButton(btnSave, new Color(100, 140, 200));
        styleButton(btnCancel, new Color(160, 160, 160));

        btnSave.addActionListener(e -> {
            int selectedRow = productTable.getSelectedRow();

            if (selectedRow < 0) {
                JOptionPane.showMessageDialog(dialog, "Pilih produk terlebih dahulu!");
                return;
            }

            try {
                int qty = Integer.parseInt(txtQty.getText().trim());
                int modelRow = productTable.convertRowIndexToModel(selectedRow);
                Product selectedProduct = products.get(modelRow);

                if (qty <= 0) {
                    JOptionPane.showMessageDialog(dialog, "Jumlah harus lebih dari 0!");
                    return;
                }

                if (qty > selectedProduct.getStok()) {
                    JOptionPane.showMessageDialog(dialog,
                            "Stok tidak cukup! Tersedia: " + selectedProduct.getStok());
                    return;
                }

                double total = qty * selectedProduct.getHarga();
                int confirm = JOptionPane.showConfirmDialog(dialog,
                        String.format("%s\nJumlah: %d\nTotal: Rp %,d\n\nProses?",
                                selectedProduct.getNama(), qty, (long)total),
                        "Konfirmasi",
                        JOptionPane.YES_NO_OPTION);

                if (confirm == JOptionPane.YES_OPTION) {
                    salesController.saveSale(selectedProduct, qty);
                    refreshAll();
                    JOptionPane.showMessageDialog(dialog, "Transaksi berhasil!");
                    dialog.dispose();
                }

            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(dialog, "Jumlah harus berupa angka!");
            }
        });

        btnCancel.addActionListener(e -> dialog.dispose());

        buttonPanel.add(btnSave);
        buttonPanel.add(btnCancel);

        dialog.add(contentPanel, BorderLayout.CENTER);
        dialog.add(buttonPanel, BorderLayout.SOUTH);

        dialog.setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new MainGUI().start());
    }
}