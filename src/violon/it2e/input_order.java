
package violon.it2e;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class input_order {
    
      public void crud(){
      boolean back = true;
      do {
        Scanner in = new Scanner (System.in);
        System.out.println("----------------");
        System.out.println("[ORDER CRUD MENU]");
        System.out.println("[1] Add\n[2] View\n[3] Update\n[4] Delete\n[5] Back");
        System.out.println("----------------");
        System.out.print("Input Action: ");
       
        
         int act = 0; 
        boolean validInput = false;

        
        while (!validInput) {
            if (in.hasNextInt()) {
                act = in.nextInt();

                
                if (act >= 1 && act <= 3) {
                    validInput = true;
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
                addOrder();
                viewOrder();
                break;
            case 2:
                viewOrder();
                break;
            case 3: 
                viewOrder();
                updateOrder();
                break;
            case 4:
                viewOrder();
                deleteOrder();
                break;
            case 5:
                back = false;
                break;
        }
        }while(back);
      }
    
    public static void addOrder(){
        
        Scanner sc = new Scanner(System.in);
        config conf = new config();
        LocalDate currdate = LocalDate.now();
        DateTimeFormatter format = DateTimeFormatter.ofPattern("MM/dd/yyyy");
        String date = currdate.format(format);
        
        System.out.print("Name of Client: ");
        String name = sc.next();
        System.out.print("Adrress: ");
        String addr = sc.next();
        System.out.print("Contact No.: ");
        String phone = sc.next();
     
        input_carrier carr = new input_carrier();
        carr.viewCarrier();
        
        System.out.print("Enter name of carrier: ");
        int id = sc.nextInt();
        
        System.out.print("[Status: Shipping Parcel]");
        String status = "Shipping";
             
            
        String sql = "INSERT INTO tbl_order (o_date, o_name, o_address, o_contact, o_status, carrier_name) VALUES (?, ?, ?, ?, ?, ?)";


        conf.addRecord(sql, date, name, addr, phone, status, id);


        
    }
    
    
     public void viewOrder() {
       
        String votersQuery = "SELECT o_id, o_date, o_name, o_address, o_contact, o_status, c_name FROM tbl_order "
                + "LEFT JOIN tbl_carrier ON tbl_carrier.c_id = tbl_order.o_id";
        String[] votersHeaders = {"ID", "Date", "Name", "Address", "Phone Number", "Status", "Carrier Name"};
        String[] votersColumns = {"o_id", "o_date", "o_name", "o_address", "o_contact", "o_status","c_name"};
        
        config conf = new config ();
        conf.viewRecords(votersQuery, votersHeaders, votersColumns);
    }
          config con = new config();
          
        public void updateOrder() {     
        Scanner sc = new Scanner (System.in);
        config dbConfig = new config();
        System.out.print("Enter Order ID to Update: ");         
        int id = sc.nextInt();
        
           String osql = "Select o_id FROM tbl_order WHERE o_id = ?";
        while(con.getSingleValue(osql, id) == 0){
            System.out.print("Carier does not exist, Select Again: ");
            id = sc.nextInt(); 
        }
        System.out.print("Name of Client: ");
        String newName = sc.next();
        System.out.print("Adrress: ");
        String newAddr = sc.next();
        System.out.print("Contact No.: ");
        String newPhone = sc.next();
      
        input_carrier carr = new input_carrier();
        carr.viewCarrier();
        System.out.print("Enter ID of carrier: ");
        int c_id = sc.nextInt();
         
            String csql = "Select c_id FROM tbl_carrier WHERE c_id = ?";
        while(con.getSingleValue(csql, c_id) == 0){
            System.out.print("Carier does not exist, Select Again: ");
            id = sc.nextInt(); 
        }
        
        System.out.print("\nStatus: \n[1] Shipping\n[2] Delivered\n");
        System.out.print("Input Status: ");
        String newStatus = sc.next();
        
        String sqlUpdate = "UPDATE tbl_order SET o_name = ?, o_address = ?, o_contact = ?, o_status = ?, carrier_id = ? WHERE o_id = ?";
        dbConfig.updateRecord(sqlUpdate, newName, newAddr, newPhone, newStatus, c_id, id);
    
  }
        
        
        public void deleteOrder(){
        Scanner in = new Scanner (System.in);
        config dbConfig = new config();
        System.out.print("Enter Order ID to Delete: ");
        int id = in.nextInt();
        
          String dsql = "Select o_id FROM tbl_order WHERE o_id = ?";
        while(con.getSingleValue(dsql, id) == 0){
            System.out.print("Carier does not exist, Select Again: ");
            id = in.nextInt(); 
        }
        
        String sqlDelete = "DELETE FROM tbl_order WHERE o_id = ?";
        dbConfig.deleteRecord(sqlDelete, id);
    }
    
        
        
     
}
