package Views;

import Controllers.ProductController;
import Models.Product;

import java.util.List;
import java.util.Scanner;

public class ProductView {

    private Scanner input = new Scanner(System.in);
    private ProductController controller = new ProductController();
    private String infoMessage = "";

    public void showMainMenu() {
        while (true) {
            System.out.println("\nManajemen Produk");
            if (!infoMessage.isEmpty()) {
                System.out.println(infoMessage);
                infoMessage = "";
            }

            System.out.println("1. Tampilkan Produk");
            System.out.println("2. Tambah Produk");
            System.out.println("3. Hapus Produk");
            System.out.println("0. Kembali ke Menu Utama");
            System.out.print("Pilih Menu: ");

            String choice = input.nextLine();

            switch (choice) {
                case "1":
                    showProducts();
                    break;
                case "2":
                    addProduct();
                    break;
                case "3":
                    deleteProduct();
                    break;
                case "0":
                    return;
                default:
                    System.out.println("Menu tidak valid.");
            }
        }
    }

    private void showProducts() {
        System.out.println("\n=== DAFTAR PRODUK ===");
        List<Product> products = controller.getAllProducts();

        if (products.isEmpty()) {
            System.out.println("Data kosong.");
        } else {
            System.out.println("\nNo | Kode | Nama Produk | Harga | Stok");
            System.out.println("-------------------------------------------");

            for (int i = 0; i < products.size(); i++) {
                Product p = products.get(i);
                System.out.println(
                        (i + 1) + " | " +
                                p.getKode() + " | " +
                                p.getNama() + " | " +
                                p.getHarga() + " | " +
                                p.getStok()
                );
            }
        }

        System.out.println("\nKlik Enter Untuk Kembali");
        input.nextLine();
    }

    private void addProduct() {
        try {
            System.out.println("\n=== TAMBAH PRODUK ===");
            System.out.print("Kode Produk : ");
            String kode = input.nextLine();

            System.out.print("Nama Produk : ");
            String nama = input.nextLine();

            System.out.print("Harga       : ");
            double harga = Double.parseDouble(input.nextLine());

            System.out.print("Stok        : ");
            int stok = Integer.parseInt(input.nextLine());

            controller.addProduct(kode, nama, harga, stok);
            infoMessage = "Produk Telah Ditambahkan. Silahkan cek 1. Tampilkan Data";

        } catch (Exception e) {
            System.out.println("Input tidak valid.");
        }
    }

    private void deleteProduct() {
        System.out.println("\n=== HAPUS PRODUK ===");
        List<Product> products = controller.getAllProducts();

        for (int i = 0; i < products.size(); i++) {
            Product p = products.get(i);
            System.out.println((i + 1) + ". " + p.getNama());
        }

        System.out.print("\nNomor produk yang ingin dihapus (Enter Tanpa Angka untuk kembali): ");
        String inputChoice = input.nextLine();

        if (inputChoice.isEmpty()) {
            return; // kembali ke main menu
        }

        try {
            int choice = Integer.parseInt(inputChoice);
            controller.deleteProductByIndex(choice - 1);
            infoMessage = "Hapus data berhasil. Cek di Tampilkan Data";
        } catch (Exception e) {
            System.out.println("Input tidak valid.");
        }

    }
}
