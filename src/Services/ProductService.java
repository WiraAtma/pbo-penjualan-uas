package Services;

import java.io.*;
import java.util.*;

import Models.Product;

public class ProductService {

    private final String FILE_NAME = "product.txt";
    private final String HEADER = "#id,kode,nama,harga,stok";

    public ProductService() {
        createFileIfNotExists();
    }

    // =========================
    // CREATE FILE JIKA BELUM ADA
    // =========================
    private void createFileIfNotExists() {
        File file = new File(FILE_NAME);

        try {
            if (!file.exists()) {
                BufferedWriter writer = new BufferedWriter(new FileWriter(file));
                writer.write(HEADER);
                writer.newLine();
                writer.close();
            }
        } catch (IOException e) {
            System.out.println("Gagal membuat file: " + e.getMessage());
        }
    }

    // =========================
    // LOAD DATA DARI FILE
    // =========================
    public List<Product> loadProducts() {
        List<Product> products = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;

            while ((line = reader.readLine()) != null) {

                // skip header & baris kosong
                if (line.startsWith("#") || line.trim().isEmpty()) {
                    continue;
                }

                String[] data = line.split(",");

                Product product = new Product(
                        Integer.parseInt(data[0].trim()),
                        data[1].trim(),
                        data[2].trim(),
                        Double.parseDouble(data[3].trim()),
                        Integer.parseInt(data[4].trim())
                );

                products.add(product);
            }

        } catch (IOException e) {
            System.out.println("Gagal membaca file: " + e.getMessage());
        }

        return products;
    }

    // =========================
    // ADD PRODUCT (APPEND)
    // =========================
    public void addProduct(Product p) {
        try (BufferedWriter writer = new BufferedWriter(
                new FileWriter(FILE_NAME, true))) {

            writer.write(p.toFileString());
            writer.newLine();

        } catch (IOException e) {
            System.out.println("Gagal menambahkan product: " + e.getMessage());
        }
    }

    // =========================
    // DELETE / REWRITE ALL
    // =========================
    public void deleteProduct(List<Product> products) {
        try (BufferedWriter writer = new BufferedWriter(
                new FileWriter(FILE_NAME))) {

            writer.write(HEADER);
            writer.newLine();

            for (Product p : products) {
                writer.write(p.toFileString());
                writer.newLine();
            }

        } catch (IOException e) {
            System.out.println("Gagal menghapus product: " + e.getMessage());
        }
    }

    // =========================
    // GENERATE ID AMAN (ID TERBESAR + 1)
    // =========================
    public int generateNextId(List<Product> products) {
        int maxId = 0;

        for (Product p : products) {
            if (p.getId() > maxId) {
                maxId = p.getId();
            }
        }
        return maxId + 1;
    }
}