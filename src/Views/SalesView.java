package Views;

import Controllers.SalesController;
import Models.Sale;

import java.util.List;

public class SalesView {
    private SalesController controller = new SalesController();
    // buat input disini

    public void showMenu() {
        // nampilin semua menu bagian sales
        // --- UI Design ---
        // Penjualan
        // 1. Daftar Transaksi
        // 2. Penjualan Produk (Input Penjualan)
        // 0. Kembali (Kembali ke Main Menu)
        // Pilih Menu :

        // Pakai Switch Case dan While
    }

    private void showTransactions() {
        // ambil sales dari controller

        // --- UI Design ---
        // Daftar Transaksi
        // | No | Produk | Qty | Harga | Total
        // | 1  | Es Tea | 3   | 4000  | 12000
        // .....
        // Total Semua : total += total
        // Tekan Enter Untuk kembali

        // Jika Player tekan Enter maka kembali ke Menu Sales
    }

    private void sellProduct() {
        // Input sales
        // Ada beberapa menu yang muncul :
        // --- UI Design ---
        // Daftar Produk
        // < Tampilin Semua Produk >
        // Pilih nomor produk (Enter Kosong untuk Kembali) :
        //
        // < Tampilkan Nama Produk Yang Dipilih >
        // Jumlah Yang Ingin Dibeli :
        //
        // Konfirmasi Penjualan
        // < Tampilkan Nama dan Jumlah >
        // Apakah Anda Yakin Menyimpan Penjualan?
        // 1. Iya
        // 2. Tidak
        // Pilih Menu :

        // Jika Iya Message Penjualan Berhasil jika Tidak Penjualan dibatalkan
    }
}
