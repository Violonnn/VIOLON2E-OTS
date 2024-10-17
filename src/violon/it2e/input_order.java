
package violon.it2e;

import java.util.Scanner;

public class input_order {
    
      public void crud(){
        Scanner in = new Scanner (System.in);
        System.out.println("----------------");
        System.out.println("[ORDER CRUD MENU]");
        System.out.println("[1] Add\n[2] View\n[3] Update\n[4] Delete\n[5] Back");
        System.out.println("----------------");
        System.out.print("Input Action: ");
        int act = in.nextInt();
        
       
        switch(act){
            case 1:
                addOrder();
                break;
            case 2:
                viewOrder();
                break;
            case 3: 
                updateOrder();
                break;
            case 4:
                deleteOrder();
                break;
            case 5:
                break;
        }
    }
    
    public static void addOrder(){
        
        Scanner sc = new Scanner(System.in);
        config conf = new config();
        System.out.print("Order Date: ");
        String date = sc.next();
        System.out.print("Name of Client: ");
        String name = sc.next();
        System.out.print("Adrress: ");
        String addr = sc.next();
        System.out.print("Contact No.: ");
        String phone = sc.next();
        System.out.print("\nStatus: \n[1] Shipping\n[2] Delivered\n");
        System.out.print("Input Status: ");
        String status = sc.next();
        System.out.print("Enter ID of carrier: ");
        int id = sc.nextInt();

        String sql = "INSERT INTO tbl_order (o_date, o_name, o_address, o_contact, o_status, carrier_id) VALUES (?, ?, ?, ?, ?, ?)";


        conf.addRecord(sql, date, name, addr, phone, status, id);


        
    }
    
    
     public void viewOrder() {
        String votersQuery = "SELECT * FROM tbl_order";
        String[] votersHeaders = {"ID", "Date", "Name", "Address", "Phone Number", "Status", "Carrier ID"};
        String[] votersColumns = {"o_id", "o_date", "o_name", "o_address", "o_contact", "o_status","carrier_id"};
        
        config conf = new config ();
        conf.viewRecords(votersQuery, votersHeaders, votersColumns);
    }
     
     
        public void updateOrder() {     
        Scanner sc = new Scanner (System.in);
        config dbConfig = new config();
        System.out.print("Enter Order ID to Update: ");
        int id = sc.nextInt();
        System.out.print("Order Date: ");
        String newDate = sc.next();
        System.out.print("Name of Client: ");
        String newName = sc.next();
        System.out.print("Adrress: ");
        String newAddr = sc.next();
        System.out.print("Contact No.: ");
        String newPhone = sc.next();
        System.out.print("\nStatus: \n[1] Shipping\n[2] Delivered\n");
        System.out.print("Input Status: ");
        String newStatus = sc.next();
        System.out.print("Enter ID of carrier: ");
        int c_id = sc.nextInt();

        
        String sqlUpdate = "UPDATE tbl_order SET o_date = ?, o_name = ?, o_address = ?, o_contact = ?, o_status = ?, carrier_id = ? WHERE o_id = ?";
        dbConfig.updateRecord(sqlUpdate, newDate, newName, newAddr, newPhone, newStatus, c_id, id);
    
  }
        
        
        public void deleteOrder(){
        Scanner in = new Scanner (System.in);
        config dbConfig = new config();
        System.out.print("Enter Order ID to Delete: ");
        int id = in.nextInt();
        String sqlDelete = "DELETE FROM tbl_order WHERE o_id = ?";
        dbConfig.deleteRecord(sqlDelete, id);
    }
    
        
        
     
}
