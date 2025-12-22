package Models;

public class Sale {
    private int id;
    private String productName;
    private int qty;
    private double price;
    private double total;


    // buat constructur
    public Sale(int id, String productName, int qty, double price, double total) { // tambah lainnya
        this.id = id;
        this.productName = productName;
        this.qty = qty;
        this.total = total;
        this.price = price;

    }

    public int getId() {
        return id;
    }
    public String getProductName() {return productName;}
    public int getQty() {return  qty;}
    public double getPrice() {return price;}
    public double getTotal() {return total;}



    // buat return untuk ke file
    public String toFileString() {
        return id + "," + productName + "," + qty + "," + price + "," + total ;
    }
}
