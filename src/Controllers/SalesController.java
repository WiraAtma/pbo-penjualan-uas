package Controllers;

import Models.Product;
import Models.Sale;
import Services.ProductService;
import Services.SalesService;

import java.util.ArrayList;
import java.util.List;

public class SalesController {

    private ProductService productService = new ProductService();
    private SalesService salesService = new SalesService();

    public List<Product> getProducts() {
        return productService.loadProducts();
    }

    public List<Sale> getSales() {
        return salesService.loadSales();
    }

    public void saveSale(Product p, int qty) {
        // 1. Generate ID untuk sale baru
        List<Sale> sales = salesService.loadSales();
        int saleId = salesService.generateNextId(sales);

        // 2. Tambahkan transaksi penjualan
        salesService.addSale(new Sale(saleId, p.getNama(), qty, p.getHarga()));

        // 3. Update stok produk
        List<Product> products = productService.loadProducts();
        List<Product> updatedProducts = new ArrayList<>();

        for (Product product : products) {
            if (product.getId() == p.getId()) {
                // Kurangi stok untuk produk yang terjual
                Product updatedProduct = new Product(
                        product.getId(),
                        product.getKode(),
                        product.getNama(),
                        product.getHarga(),
                        product.getStok() - qty
                );
                updatedProducts.add(updatedProduct);
            } else {
                updatedProducts.add(product);
            }
        }

        // 4. Simpan kembali semua produk dengan stok yang sudah diupdate
        productService.deleteProduct(updatedProducts);
    }
}