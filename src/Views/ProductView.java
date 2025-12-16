package Views;

import Controllers.ProductController;

public class ProductView {
    private ProductController controller = new ProductController();
    // panggil input disini

    public void showMainMenu() {
        // buat biar berulang pakai while
        // Pakai Switch case saat mencoba

        // --- UI Design ---
        // Manajemen Produk
        // 1. Tampilkan Produk (Berisi Menampilkan Semua produk)
        // 2. Tambah Produk
        // 3. Hapus Produk
        // 0. Kembali ke Main Menu (Main.java)
        // Pilih Menu :
    }

    private void showProducts() {
        // tampilan semua produk
        // --- UI Design ---
        // Daftar Produk
        //| No | Kode | Nama Produk | Harga | Stok |
        // | 1 | P001 | Susu UHT    | 5000  | 4    |
        // ......

        // no pakai index jangan id
    }

    private void addProduct() {
        // tampilan menambah produk Per Input muncul ketika User sudah Menginput

        // --- UI Design ---
        // Tambah Produk
        // Kode Produk : ..
        // Nama Produk : ..
        // Harga (Rp) : ..
        // Stok       : ..

        // kalo sukses kirim message ke menu product bagian awal
    }

    private void deleteProduct() {
        // Tampilan Untuk Menghapus Produk
        // --- UI Design ---
        // < Nampilin Semua Produk Biar User tau nomor >
        // Nomor Produk Yang Ingin Dihapus (Enter Tanpa Angka Untuk Kembali) : Input

        // isi kondisi Jika isEmpty maka kembali ke menu, Jika Ada Maka lanjutkan dan juga jika nomor ga ada di product beritahu pesan kalau nomor ga ada

        // kirim message Jika Sudah Berhasil Hapus
    }
}
