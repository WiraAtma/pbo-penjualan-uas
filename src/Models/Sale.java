package Models;

public class Sale {
    private int id;
    private String productName;
    private int qty;
    private double price;
    private double total;


    // constructur
    public Sale(int id, String productName, int qty, double price) {
        this.id = id;
        this.productName = productName;
        this.qty = qty;
        this.price = price;
        this.total = price * qty;
    }

    public String getProductName() {return productName;}
    public int getQty() {return  qty;}
    public double getPrice() {return price;}
    public double getTotal() {return total;}

    // buat return untuk ke file
    public String toFileString() {
        return id + "," + productName + "," + qty + "," + price + "," + total ;
    }

    public Object getId() {
        return id;
    }
}
