
package violon.it2e;

import java.util.Scanner;

public class implementation {
    Scanner in = new Scanner(System.in);
    public void report(){
        boolean back = true;
        do {
        System.out.println("------------------------------------------------------------");
         System.out.println("[1] Summary Report of Carriers");
         // para makahinomdon ko:Name of Carrier, Total of Orders, Type of Delivery Service
         System.out.println("[2] Order in Detail Reports");
          // para makahinomdon ko:Name of Carrier, tagsa2 nga Orders, Name of Product, Adrdress, Name of Client, Date of Delivery, Status, Delivery Service
          System.out.println("[3] Back");
         System.out.print("Enter Action: ");
         
        int act = 0; 
        boolean validInput = true;

        
        while (validInput) {
            if (in.hasNextInt()) {
                act = in.nextInt();
              
                if (act >= 1 && act <= 3) {
                    validInput = false;
                } else {
                    System.out.print("[Enter a number ranging from 1 and 3 only] Try Again: ");
                }
            } else {
                System.out.print("[No letters and symbols are allowed] Try Again: ");
                in.next(); 
            }
        }
        input_carrier car = new input_carrier();
        switch(act){
            case 1:
                 if (car.hasRecords("tbl_carrier")) {
                       summary();
                    } else {
                        System.out.println("[No carriers found in the database]");
                    }
                break;
            case 2:
                if (car.hasRecords("tbl_carrier")) {
                       detail();
                    } else {
                        System.out.println("[No carriers found in the database]");
                    }
                break;
            case 3:
                back = false;
                break;
        }
        }while(back);
    }
  public void summary() {
    config conf = new config();
  
    String summaryQuery = "SELECT c.c_name, COUNT(o.o_id) AS total_orders, c.c_delservice " +
                          "FROM tbl_carrier c " +
                          "LEFT JOIN tbl_order o ON o.c_id = c.c_id " +
                          "GROUP BY c.c_name, c.c_delservice";
    
    String[] summaryHeaders = {"Carrier Name", "Total Orders", "Delivery Service"};
    String[] summaryColumns = {"c_name", "total_orders", "c_delservice"};
    
    
    conf.viewRecords(summaryQuery, summaryHeaders, summaryColumns);
}


  
  
    
    
 public void detail() {
    Scanner sc = new Scanner(System.in);
    int carrierId = 0;
    config conf = new config();
    input_carrier c = new input_carrier();
    c.viewCarrier();
    
  while(true){
        System.out.print("Enter Carrier ID to view detailed orders: ");
        if (sc.hasNextInt()) {
            carrierId = sc.nextInt();
            String checkQuery = "SELECT COUNT(*) FROM tbl_carrier WHERE c_id = ?";
            if (conf.getSingleValue(checkQuery, carrierId) > 0) {
                break; 
            } else {
                System.out.println("[Invalid Carrier ID]");
            }
        } else {
            System.out.println("[Invalid Input]");
            sc.next(); 
        }
    }

   
    String orderCountQuery = "SELECT COUNT(*) FROM tbl_order WHERE c_id = ?";
    if (conf.getSingleValue(orderCountQuery, carrierId) == 0) { 
        String carrierNameQuery = "SELECT c_name FROM tbl_carrier WHERE c_id = ?";
        String carrierName = conf.getSingleValueAsString(carrierNameQuery, carrierId);
        
        if (carrierName == null || carrierName.isEmpty()) {
            System.out.println("[Error: Carrier not found]");
            return;
        }

        System.out.println("------------------------------------");
        System.out.println("| Carrier Name                     |");
        System.out.println("------------------------------------");
        System.out.println("| " + carrierName + "\t\t\t           |");
        System.out.println("------------------------------------");
        System.out.println("| No Records of Order              |");
        System.out.println("------------------------------------");
   
     
    
    }else {
   
    String detailQuery = "SELECT o.o_name, o.o_address, o.o_contact, o.o_status, c.c_name, c.c_delservice, o.o_date " +
                         "FROM tbl_order o " +
                         "JOIN tbl_carrier c ON o.c_id = c.c_id " +
                         "WHERE c.c_id = ?";

   
    String[] detailHeaders = {"Customer Name", "Address", "Contact", "Status", "Carrier Name", "Delivery Service", "Order Date"};
    String[] detailColumns = {"o_name", "o_address", "o_contact", "o_status", "c_name", "c_delservice", "o_date"};

    
    conf.viewCarOrder(detailQuery, detailHeaders, detailColumns, carrierId);
    main m = new main();
 }
 }
}
    



    
 

