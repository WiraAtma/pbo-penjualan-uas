package Controllers;

import Models.Product;
import Services.ProductService;

import java.util.List;

public class ProductController {
    private ProductService service = new ProductService();

    public List<Product> getAllProducts() {
        return service.loadProducts();
    }

    public void addProduct(String kode, String nama, double harga, int stok) {
        // nambahin produk baru sesuai input user pake generateNextId dari service buat id
        // pakai append di bagian service
    }

    public void deleteProductByIndex(int index) {
        // di load dulu productnya terus remove sesuai index / angka di panel nantinya
    }
}
