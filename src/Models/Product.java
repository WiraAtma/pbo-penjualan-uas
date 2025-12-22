package Models;

public class Product {
    private int id;
    private String kode;
    private String nama;
    private double harga;
    private int stok;

    // constructor
    public Product(int id, String kode, String nama, double harga, int stok) {
        this.id = id;
        this.kode = kode;
        this.nama = nama;
        this.harga = harga;
        this.stok = stok;
    }

    // getter
    public int getId() {
        return id;
    }

    public String getKode() {
        return kode;
    }

    public String getNama() {
        return nama;
    }

    public double getHarga() {
        return harga;
    }

    public int getStok() {
        return stok;
    }

    // untuk ke file / atau ke tabel
    public String toFileString() {
        return id + "," + kode + "," + nama + "," + harga + "," + stok;
    }
}