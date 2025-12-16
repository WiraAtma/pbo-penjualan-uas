package Services;

import Models.Sale;

import java.util.ArrayList;
import java.util.List;

public class SalesService {
    private final String FILE_NAME = "sales.txt";
    private final String HEADER = "#id,produk,qty,harga_satuan,total";

    public SalesService() {
        createFileIfNotExists();
    }

    private void createFileIfNotExists() {
        // bikin kode anggap file sales.txt ga ada kita buat aja juga cek kondisi jika ada maka gunakan itu
    }

    // load
    public List<Sale> loadSales() {
        List<Sale> list = new ArrayList<>();

        // buat kode dimana membaca data
        // tapi inget cek dulu header , headernya harus di skip biar bisa lihat data
        // data dipisahkan dengan "," tanda koma contoh : 1, ikan goreng , 20000
        // Bikin kondisi pakai try catch jika gagal maka tampilkan kalo Gagal Membaca file
        // Tanda Header diawali # contoh : #id, nama, harga, stok , ...

        return list;
    }

    public void appendSale(Sale sale) {
        // Buat untuk menambahkan sale bisa pakai BufferedWriter dia akan menambahkan di akhir file tidak menghapus data lama
    }

    // menampilkan id secara berurutan
    public int generateNextId(List<Sale> sales) {
        return sales.isEmpty() ? 1 : sales.size() + 1;
    }
}
