
package violon.it2e;

import java.util.Scanner;

public class main {

    public static void main(String[] args) {
        Scanner in = new Scanner (System.in);
        
        System.out.println("Welcome to Tracking Order System!\n");
        System.out.println("[Select an Action to Begin]");
        System.out.println("[1] Carrier");
        System.out.println("[2] Order");
        System.out.print("Input Action: ");
        int act = in.nextInt();
        
        
        
        
        switch(act){
            case 1:
                input_carrier car = new input_carrier();
                car.crud();
                break;
            case 2:
                input_order ord = new input_order();
                ord.crud();
                break;
        }
        
    }
    
}
