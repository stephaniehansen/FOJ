import java.util.*;
public class Inventory
{
    private List<ProductLine> products;
    
    public Inventory(){
        products = new ArrayList<ProductLine>();
        ArrayList<String> serialNumbers = new ArrayList<String>();
        serialNumbers.add("AAAA111111");
        serialNumbers.add("BBBB222222");
        addProduct(new PerishableProductLine("C0010", 3.0, 1000, 16));
        addProduct(new PerishableProductLine("B0010", 12.0, 125, 5));
        addProduct(new ItemisedProductLine("D1000", 2600.0, 2, serialNumbers));
        addProduct(new ProductLine("A0001", 10.90, 100));
    }

    public void addProduct(ProductLine pl) {
        products.add(pl);
    }
    
    public void sellProduct(String productCode, int no) {
        ProductLine product = getProduct(productCode);
        product.sell(no);
    }
    
    public void increaseStock(String productCode, int no) {
        ProductLine product = getProduct(productCode);  
        product.increase(no);
    }
    
    public void updatePrice(String productCode, double price){
        ProductLine product = getProduct(productCode);  
        product.updatePrice(price);
    }
    
    public void decreaseDaysToExpiry(){
        for(ProductLine product : products){
            product.decrease();
        }
    }
    
    public ProductLine getProduct(String productCode) {
        for(ProductLine product : products){
            if(product.getProductCode().equals(productCode)){
                return product;}
        }
        return null;
    }
    
    public void print(){
        Collections.sort(products);
        System.out.println("\nProducts sold:\n");
        for(ProductLine product : products){
            System.out.printf("%-8s %-8s %15s %n", "Code ", "Units sold ", "Units left ");
            System.out.printf("%-8s %10s %15s %n", product.getProductCode(), product.getQuantitySold(), product.getQuantity());
        }
        System.out.println("\nPerishable products expiry date:\n");
        for(ProductLine product : products){
             if(product instanceof PerishableProductLine){
                 System.out.printf("%-8s %-8s %23s %n", "Code ", "Units left ", "Days before expiry ");
                 System.out.printf("%-8s %10s %23s %n", product.getProductCode(), product.getQuantity(), product.getDaysToExpiry());
            }
        }
    }
}