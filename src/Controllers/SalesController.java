package Controllers;

import Models.Product;
import Models.Sale;
import Services.ProductService;
import Services.SalesService;

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
        List<Sale> sales = salesService.loadSales();
        int id = salesService.generateNextId(sales);

        // kurangi stok
        p = new Product(
                p.getId(),
                p.getKode(),
                p.getNama(),
                p.getHarga(),
                p.getStok() - qty
        );

        Product finalP = p;
        productService.deleteProduct(
                productService.loadProducts().stream()
                        .map(prod -> prod.getId() == finalP.getId() ? finalP : prod)
                        .toList()
        );

        salesService.addSale(new Sale(id, p.getNama(), qty, p.getHarga()));
    }
}
