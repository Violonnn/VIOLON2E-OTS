package violon.it2e;

import java.util.Scanner;







public class input_carrier {
    
    public void crud(){
        Scanner in = new Scanner (System.in);
        System.out.println("----------------");
        System.out.println("[CARRIER CRUD MENU]");
        System.out.println("[1] Add\n[2] View\n[3] Update\n[4] Delete\n[5] Back");
        System.out.println("----------------");
        System.out.print("Input Action: ");
        int act = in.nextInt();
        
       
        switch(act){
            case 1:
                addCarrier();
                break;
            case 2:
                viewCarrier();
                break;
            case 3: 
                updateCarrier();
                break;
            case 4:
                deleteCarrier();
                break;
            case 5:
                break;
        }
    }
    
    
    
    
    
    public void addCarrier(){
        
        Scanner sc = new Scanner(System.in);
        config conf = new config();
        System.out.print("Carrier Name: ");
        String name = sc.next();
        System.out.print("Email: : ");
        String email = sc.next();
        System.out.print("Phone Number: ");
        String phone = sc.next();
        System.out.print("\nDelivery Service\n");
        System.out.println("[1] Standard\n[2] Express\n[3] Overnight");
        System.out.print("Input Service: ");
        String service = sc.next();

        String sql = "INSERT INTO tbl_carrier (c_name, c_email, c_phone, c_delservice) VALUES (?, ?, ?, ?)";


        conf.addRecord(sql, name, email, phone, service);
    }
    
    
     public void viewCarrier() {
        String votersQuery = "SELECT * FROM tbl_carrier";
        String[] votersHeaders = {"ID", "Name", "Email", "Phone Contact", "Delivery Service"};
        String[] votersColumns = {"c_id", "c_name", "c_email", "c_phone", "c_delservice"};
        
        config conf = new config ();
        conf.viewRecords(votersQuery, votersHeaders, votersColumns);
    }
    
    
     public void updateCarrier()
    {     
        Scanner sc = new Scanner (System.in);
        config dbConfig = new config();
        System.out.print("Enter Carrier ID to Update: ");
        int id = sc.nextInt();
        System.out.print("Carrier Name: ");
        String newName = sc.next();
        System.out.print("Email: : ");
        String newEmail = sc.next();
        System.out.print("Phone Number: ");
        String newPhone = sc.next();
        System.out.print("\nDelivery Service\n");
        System.out.println("[1] Standard\n[2] Express\n[3] Overnight");
        System.out.print("Input Service: ");
        String newService = sc.next();
        String sqlUpdate = "UPDATE tbl_carrier SET c_name = ?, c_email = ?, c_phone = ?, c_delservice = ? WHERE c_id = ?";
        dbConfig.updateRecord(sqlUpdate, newName, newEmail, newPhone, newService, id);
    
  }
    public void deleteCarrier(){
        Scanner in = new Scanner (System.in);
        config dbConfig = new config();
        System.out.print("Enter Carrier ID to Delete: ");
        int id = in.nextInt();
        String sqlDelete = "DELETE FROM tbl_carrier WHERE c_id = ?";
        dbConfig.deleteRecord(sqlDelete, id);
    }
    
}
