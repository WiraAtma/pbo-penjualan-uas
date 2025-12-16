package Services;

import Models.Product;

import java.util.ArrayList;
import java.util.List;

public class ProductService {
    private final String FILE_NAME = "product.txt"; // final dikarenakan ga akan berubah alias konstan
    private final String HEADER = "#id,produk,qty,harga_satuan,total"; // format di product.txt

    public ProductService() {
        createFileIfNotExists(); // ngecek dulu
    }

    private void createFileIfNotExists() {
        // bikin kode anggap file product.txt ga ada kita buat aja juga cek kondisi jika ada maka gunakan itu
    }

    // Load

    public List<Product> loadProducts() {
        List<Product> products = new ArrayList<>();

        // buat kode dimana membaca data
        // tapi inget cek dulu header , headernya harus di skip biar bisa lihat data
        // data dipisahkan dengan "," tanda koma contoh : 1, ikan goreng , 20000
        // Bikin kondisi pakai try catch jika gagal maka tampilkan kalo Gagal Membaca file
        // Tanda Header diawali # contoh : #id, nama, harga, stok , ...

        return products;
    }

    // ADD

    public void appendProduct(Product p) {
        // Buat untuk menambahkan product bisa pakai BufferedWriter dia akan menambahkan di akhir file tidak menghapus data lama
    }

    // Delete
    public void rewriteAll(List<Product> products) {
        // Menulis ulang seluruh isi file berdasarkan list produk terbaru.
    }

    // menampilkan id secara berurutan
    public int generateNextId(List<Product> products) {
        return products.isEmpty()
                ? 1
                : products.get(products.size() - 1).getId() + 1;
    }
}
