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
        List<Product> products = service.loadProducts();
        int id = service.generateNextId(products);

        Product newProduct = new Product(id, kode, nama, harga, stok);
        service.addProduct(newProduct);
    }

    public void deleteProductByIndex(int index) {
        List<Product> products = service.loadProducts();

        if (index >= 0 && index < products.size()) {
            products.remove(index);
            service.deleteProduct(products);
        }
    }

}
