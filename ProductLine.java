import java.text.*;
import java.io.*;

public class ProductLine implements Comparable<ProductLine>
{
    private String productCode;
    private double recRetPrice;
    private int quantity;
    private int quantitySold;
    
    public ProductLine(String productCode, double recRetPrice, int quantity){
        this.productCode = productCode;
        this.recRetPrice = recRetPrice;
        this.quantity = quantity;
    }

    public void sell(int no) {
        if(no <= quantity){
            quantitySold += no;
            quantity -= no;
            double total = no * recRetPrice;
            System.out.println("Total price: $" + formatted(total));
        }
        else{
            System.out.println("Not enough stock. Please purchase " + quantity + " or less.");
        }
    }
    
    public void increase(int no){
        quantity += no;
    }
    
    public void decrease(){
        
    }
    
    public void updatePrice(double price){
        this.recRetPrice = price;
    }
    
    public boolean hasExpiry(ProductLine product){
        return false;
    }
    
    public String getProductCode(){
        return productCode;
    }
    
    public int getQuantity(){
        return quantity;
    }
    
    public int getQuantitySold(){
        return quantitySold;
    }
    
    public int getDaysToExpiry(){
        return 0;
    }
    
    private String formatted(double total){
         DecimalFormat form = new DecimalFormat("###,##0.00"); 
         return form.format(total); 
    }
    
    public int compareTo(ProductLine pl){
        return this.productCode.compareTo(pl.productCode);
    }
}