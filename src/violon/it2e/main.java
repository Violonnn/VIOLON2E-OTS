
package violon.it2e;

import java.util.Scanner;

public class main {

    public static void main(String[] args) {     
        main(); 
    }
    
    public static void main(){
    Scanner in = new Scanner(System.in);
    boolean exit = true;
          do {
            System.out.println("------------------------------------------------------------");
            System.out.println("Welcome to Tracking Order System!");
            System.out.println("[1] ADMIN\n[2] ORDER\n[3] EXIT");
            System.out.println("[Select an Action to Begin]");
            System.out.print("Input Action: ");
            
            int act = 0;   
      
        boolean validInput = true;

        
        while (validInput) {
            if (in.hasNextInt()) {
                act = in.nextInt();
              
                if (act >= 1 && act <= 3) {
                    validInput = false;
                } else {
                    System.out.print("[Enter a number ranging from 1 to 3 only] Try Again: ");
                }
            } else {
                System.out.print("[No letters and symbols are allowed] Try Again: ");
                in.next(); 
            }
        }
            input_carrier car = new input_carrier();
            switch (act) {
                case 1: 
                    String correctPassword = "1234";
                    boolean val = false;
                    
                    while (!val) {
                        System.out.print("Password: ");
                        String inputPassword = in.next();
                        
                        if (inputPassword.equals(correctPassword)) {
                            val = true;
                            System.out.println("------------------------------------------------------------");                  
                            admin();
                        } else {
                            System.out.print("[Incorrect password] Type 'yes' to continue: ");
                            String retry = in.next();
                            
                            if (!retry.equalsIgnoreCase("yes")) {
                                System.out.println("Returning to main menu.");
                                break;
                            }
                        }
                    }
                    break;
                    
                case 2: 
                     if (car.hasRecords("tbl_carrier")) {
                        input_order ord = new input_order();
                        ord.crud();
                    } else {
                        System.out.println("[No carriers available]");
                    }
                    break;          
                case 3:
                    System.out.println("Exiting Tracking Order System...");
                    exit = false;
                    break;
            }
        } while(exit);
    
}
    
    public static void admin (){
        Scanner in = new Scanner(System.in);
        boolean back = true;
        do {
        System.out.println("------------------------------------------------------------");  
        System.out.println("!! ADMIN Section !!");
        System.out.println("[1] Carrier");
        System.out.println("[2] Reports");
        System.out.println("[3] Update Order Status");
        System.out.println("[4] Back");
        System.out.println("[Select an action to proceed]");
        System.out.print("Enter action: ");
        
        int act = 0; 
        boolean validInput = true;

        
        while (validInput) {
            if (in.hasNextInt()) {
                act = in.nextInt();
              
                if (act >= 1 && act <= 4) {
                    validInput = false;
                } else {
                    System.out.print("[Enter a number ranging from 1 to 4 only] Try Again: ");
                }
            } else {
                System.out.print("[No letters and symbols are allowed] Try Again: ");
                in.next(); 
            }
        }
        
        input_carrier car = new input_carrier();
        implementation imp = new implementation();
        main m = new main();
        
        switch(act){
            case 1:            
                car.crud();
                break;
            case 2:              
                imp.report();
                break;
            case 3:
                 if (car.hasRecords("tbl_carrier")) {
                        car.updateOrder();
                    } else {
                        System.out.println("[No carriers available]");
                    }
                break;
            case 4:               
                back = false;
                break;
        }
      
    }while(back);
    
    
}
}