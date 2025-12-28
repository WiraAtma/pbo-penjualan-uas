package Services;

import Models.Sale;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class SalesService {
    private final String FILE_NAME = "sales.txt";
    private final String HEADER = "#id,produk,qty,harga_satuan,total";

    public SalesService() {
        createFileIfNotExists();
    }

    private void createFileIfNotExists() {
        File file = new File("sales.txt");

        if (!file.exists()) {
            try {
                boolean created = file.createNewFile();
                if (created) {
                    System.out.println("File sales.txt berhasil dibuat.");
                }
            } catch (IOException e) {
                System.out.println("Gagal membuat file sales.txt");
                e.printStackTrace();
            }
        } else {
            System.out.println("File sales.txt sudah ada, menggunakan file yang ada.");
        }
    }

    // load
    public List<Sale> loadSales() {
        List<Sale> list = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader("sales.txt"))) {
            String line;

            while ((line = br.readLine()) != null) {

                // pisahkan data dengan #
                if (line.startsWith("#")) {
                    continue;
                }

                // pisahkan data dengan koma
                String[] data = line.split(",");

                int id = Integer.parseInt(data[0].trim());
                String productName = data[1].trim();
                int qty = Integer.parseInt(data[2].trim());
                double price = Double.parseDouble(data[3].trim());
                double total = Double.parseDouble(data[4].trim());

                Sale sale = new Sale(id, productName, qty, price, total);
                list.add(sale);
            }

        } catch (IOException | NumberFormatException e) {
            System.out.println("Gagal Membaca file");
        }

        return list;
    }

    public void addSale(Sale sale) {
        // Buat untuk menambahkan sale bisa pakai BufferedWriter dia akan menambahkan di akhir file tidak menghapus data lama dia akan mengappend sale
    }

    // menampilkan id secara berurutan
    public int generateNextId(List<Sale> sales) {
        return sales.isEmpty() ? 1 : sales.size() + 1;
    }
}
