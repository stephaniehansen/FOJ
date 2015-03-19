import java.util.*;
import java.io.*;

public class Application
{
    public static void main(String[] args) {
        Application application = new Application(); 
        application.start();
    }
    
    private Inventory inventory;

    public Application() {
        inventory = new Inventory();
        start();
    }
    
    public void start() {
        char choice;
        while((choice = readChoice()) !='X') {
            execute(choice);
        }
    }
    
    private char readChoice(){   
        mainMenu();
        return IO.readChar("Enter choice: ");
    }
    
    private void mainMenu() {
        System.out.println("Options:");
        System.out.println("   1.    Point of Sale");
        System.out.println("   2.    Inventory Control");
        System.out.println("   3.    Price Control");
        System.out.println("   4.    End of Day");
        System.out.println();
        System.out.println("   X.    Exit");
    }
    
    private void execute(char choice){
        switch (choice) {
            case '1': pointOfSale(); break;
            case '2': inventoryControl(); break; 
            case '3': priceControl(); break;
            case '4': endOfDay(); break;
            case 'x': exit(); break;
            default: error(choice); break;
        }
    }

    private void pointOfSale(){
        String productCode = IO.readCode();
        ProductLine product = inventory.getProduct(productCode);
        if(product != null){
            int quantity = IO.readInt("Enter quantity: ");
            inventory.sellProduct(productCode, quantity);
        }
        else{
            System.out.println("No products with this code");
        }        
    }
    
    private void inventoryControl() {
        String productCode = IO.readCode();
        ProductLine product = inventory.getProduct(productCode);
        if(product == null){
            boolean response = IO.ask("New product [Y/N]? ");   
            if(response){
                addProduct(productCode);
                System.out.println("Product added!");
            }
            else{
                System.out.println("No products with this code");
            }
        }
        else{
            boolean response = IO.ask("Increase quantity [Y/N]? ");
            if(response){
                int quantity = IO.readInt("Quantity to increase by: ");
                inventory.increaseStock(productCode, quantity);
                IO.success();
            }
        }
    }
   
    private void addProduct(String productCode){
        double recRetPrice = IO.readRRP();    
        int quantity = IO.readInt("Initial Quantity: ");
        if(itemised()){
            ProductLine product = new ItemisedProductLine(productCode, recRetPrice, quantity, addSerialNumbers(quantity));
            inventory.addProduct(product);
        }
        else if(perishable()){
            int daysToExpiry = IO.readInt("Enter days to expiry: ");
            ProductLine product = new PerishableProductLine(productCode, recRetPrice, quantity, daysToExpiry);
            inventory.addProduct(product);
        }
        else{
            ProductLine product = new ProductLine(productCode, recRetPrice, quantity);
            inventory.addProduct(product);
        }
    }
    
    private boolean itemised(){
        return IO.ask("Product has serial number [Y/N]? ");
    }
    
    private ArrayList<String> addSerialNumbers(int quantity) {
        ArrayList<String> serialNumbers = new ArrayList<String>();
        for(int i = 0; i < quantity; i++){
            String serialNumber = IO.readLine("Enter serial number: ");
            serialNumbers.add(serialNumber);
        }
        return serialNumbers;
    }
    
    private boolean perishable(){
        return IO.ask("Product is perishable [Y/N]? ");
    }
    
    private void priceControl() {
        String productCode = IO.readCode();
        ProductLine product = inventory.getProduct(productCode);
        if(product != null){
            double recRetPrice = IO.readRRP();             
            inventory.updatePrice(productCode, recRetPrice);
            IO.success();
        }
        else{
            System.out.println("Invalid product code");
        }
    }
    
    private void endOfDay() {
        daysToExpiry();
        boolean response = IO.ask("Print reports [Y/N]? ");  
        if(response){
            inventory.print();
            exit();
        }
    }
    
    private void daysToExpiry(){
        boolean response = IO.ask("Decrement days to expiry [Y/N]? "); 
        if(response){
            inventory.decreaseDaysToExpiry();
            IO.success();
        }
    }
    
    private void exit() {
        System.exit(0);
    }
    
    private void error(char choice){
        System.err.println("Invalid choice: " + choice);
    }
}
