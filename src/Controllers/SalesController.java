package Controllers;

import Models.Product;
import Services.ProductService;
import Services.SalesService;

public class SalesController {
    private ProductService productService = new ProductService();
    private SalesService salesService = new SalesService();

    // load disini cek product controller sama aja

    // getProducts

    // getSales

    // panggil di service

    public void saveSale(Product p, int qty) {
        // pas save inget ubah di product juga soalnya penjualan
        // qty stok saat ini bisa pakai get stok dikurangi qty stok yang diinput (stock_file - qty input)
    }


}
