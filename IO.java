public class IO{
    public static boolean ask(String prompt){
        return readChar(prompt) == 'Y';
    }
    
    public static String readCode(){   
        System.out.print("Enter product code: ");
        return In.nextLine();
    }
    
    public static double readRRP(){
        System.out.print("RRP: ");
        return In.nextDouble();    
    }
    
    public static char readChar(String prompt){
        System.out.print(prompt);
        return In.nextLine().toUpperCase().charAt(0);
    }
    
    public static String readLine(String prompt){
        System.out.print(prompt);
        return In.nextLine();
    }
    
    public static int readInt(String prompt){
        System.out.print(prompt);
        return In.nextInt();
    }    
    
    public static void success(){
        System.out.println("Updated!");
    }
}