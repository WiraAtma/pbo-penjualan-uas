package Views;

import Controllers.SalesController;
import Models.Product;
import Models.Sale;

import java.util.List;
import java.util.Scanner;

public class SalesView {

    private Scanner input = new Scanner(System.in);
    private SalesController controller = new SalesController();
    private String info = "";

    public void showMenu() {
        while (true) {
            System.out.println("\n=== PENJUALAN ===");
            if (!info.isEmpty()) {
                System.out.println(info);
                info = "";
            }

            System.out.println("1. Daftar Transaksi");
            System.out.println("2. Penjualan Produk");
            System.out.println("0. Kembali");
            System.out.print("Pilih Menu: ");

            String c = input.nextLine();

            switch (c) {
                case "1":
                    showTransactions();
                    break;
                case "2":
                    sellProduct();
                    break;
                case "0":
                    return;
            }
        }
    }

    private void showTransactions() {
        List<Sale> sales = controller.getSales();
        double grandTotal = 0;

        System.out.println("\n=== DAFTAR TRANSAKSI ===");
        System.out.println("\nNo | Produk | Qty | Harga | Total");
        System.out.println("--------------------------------------");

        int no = 1;
        for (Sale s : sales) {
            System.out.println(
                    no++ + " | " +
                            s.getProductName() + " | " +
                            s.getQty() + " | " +
                            s.getPrice() + " | " +
                            s.getTotal()
            );
            grandTotal += s.getTotal();
        }

        System.out.println("--------------------------------------");
        System.out.println("Total Semua : " + grandTotal);
        System.out.println("\nTekan Enter untuk kembali...");
        input.nextLine();
    }

    private void sellProduct() {
        List<Product> products = controller.getProducts();

        System.out.println("\n=== DAFTAR PRODUK ===");
        for (int i = 0; i < products.size(); i++) {
            Product p = products.get(i);
            System.out.println(
                    (i + 1) + ". " +
                            p.getNama() +
                            " | Harga: " + p.getHarga() +
                            " | Stok: " + p.getStok()
            );
        }

        System.out.print("\nPilih Nomor Produk (Enter untuk kembali): ");
        String pilih = input.nextLine();

        if (pilih.isEmpty()) return;

        int idx = Integer.parseInt(pilih) - 1;
        Product p = products.get(idx);

        System.out.println("\n" + p.getNama());
        System.out.print("Jumlah Yang Ingin Dibeli : ");
        String qtyInput = input.nextLine();

        if (qtyInput.isEmpty()) return;

        int qty = Integer.parseInt(qtyInput);

        double total = qty * p.getHarga();

        System.out.println("\n" + p.getNama() + " x " + qty);
        System.out.println("Total Harga : " + total);

        System.out.println("\nApakah Yakin Menyimpan Penjualan?");
        System.out.println("1. Ya");
        System.out.println("2. Tidak");
        System.out.print("Pilih: ");

        String confirm = input.nextLine();

        if (confirm.isEmpty()) return;

        if (confirm.equals("1")) {
            controller.saveSale(p, qty);
            info = "Penjualan berhasil. Cek Daftar Transaksi";
        } else {
            info = "Penjualan dibatalkan";
        }
    }
}
