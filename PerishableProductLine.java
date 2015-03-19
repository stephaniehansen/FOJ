public class PerishableProductLine extends ProductLine
{
    private int daysToExpiry;

    public PerishableProductLine(String productCode, double recRetPrice, int quantity, int daysToExpiry){
        super(productCode, recRetPrice, quantity);
        this.daysToExpiry = daysToExpiry;
    }
    
    public void decrease(){
        --daysToExpiry;
    }
    
    public int getDaysToExpiry(){
        return daysToExpiry;
    }
    
    public boolean hasExpiry(){
        return true;
    }
}