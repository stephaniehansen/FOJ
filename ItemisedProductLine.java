import java.util.*;
public class ItemisedProductLine extends ProductLine
{
    private List<String> serialNumbers;
    
    public ItemisedProductLine(String productCode, double recRetPrice, int quantity, ArrayList<String> numbers){
        super(productCode, recRetPrice, quantity);
        serialNumbers = new ArrayList<String>(numbers);
    }

    public void sell(int no) {
        for(int i = 0; i < no; i++){
            for(String serialNumber : serialNumbers){
                serialNumbers.remove(readSerialNumber());
                break;
            }
        }
        super.sell(no);
    }
    
    public void increase(int no) {
        super.increase(no);
        for(int i = 0; i < no; i++){
            for(String serialNumber : serialNumbers){
                serialNumbers.add(readSerialNumber());
                break;
            }
        }
    }
    
    private String readSerialNumber(){
        return IO.readLine("Enter serial number: ");
    }
}