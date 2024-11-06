
package violon.it2e;

import java.util.Scanner;

public class main {

    public static void main(String[] args) {
        main();
    }
    
    public static void main(){
           Scanner in = new Scanner (System.in);
           boolean exit = true;
           do {
        System.out.println("Welcome to Tracking Order System!\n");
        System.out.println("[Select an Action to Begin]");
        System.out.println("[1] Carrier");
        System.out.println("[2] Order");
        System.out.println("[3] Exit");
        System.out.print("Input Action: ");
        
        int act = 0; 
        boolean validInput = true;

        
        while (validInput) {
            if (in.hasNextInt()) {
                act = in.nextInt();

                
                if (act >= 1 && act <= 3) {
                    validInput = false;
                } else {
                    System.out.println("[Invalid Action; Please enter a number between 1 and 5.]");
                }
            } else {
                System.out.println("[Invalid Action; No letters and symbols are allowed.]");
                in.next(); 
            }
            System.out.print("Input Action: ");
        }
 
        switch(act){
            case 1:
                input_carrier car = new input_carrier();
                car.crud();
                break;
            case 2:
                input_order ord = new input_order();
                ord.crud();
                break;
            case 3:
                System.out.print("EXIT Program? Type 'yes' to continue: ");
                String res = in.next();
                if(res.equalsIgnoreCase("yes")){
                exit = false;
            }
                break;
        }
           }while(exit);
    
}
}