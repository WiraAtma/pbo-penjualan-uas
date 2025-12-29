import Views.ProductView;
import Views.SalesView;
import Views.MainGUI;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        while (true) {
            System.out.println("\nSELAMAT DATANG");
            System.out.println("1. Manajemen Produk");
            System.out.println("2. Penjualan");
            System.out.println("3. Lihat GUI");
            System.out.println("0. Keluar");
            System.out.print("Pilih Menu: ");

            String choice = input.nextLine();

            switch (choice) {
                case "1":
                    // CLI Manajemen Produk
                    new ProductView().showMainMenu();
                    break;

                case "2":
                    // CLI Penjualan
                    new SalesView().showMenu();
                    break;

                case "3":
                    // GUI
                    new MainGUI().start();
                    break;

                case "0":
                    System.out.println("Terima kasih.");
                    System.exit(0);

                default:
                    System.out.println("Menu tidak valid.");
            }
        }
    }
}
