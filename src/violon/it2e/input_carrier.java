package violon.it2e;

import java.util.Scanner;







public class input_carrier {
    config conf = new config();
    public void crud(){
        boolean back = true;
        do{
        Scanner in = new Scanner (System.in);
        System.out.println("----------------");
        System.out.println("[CARRIER CRUD MENU]");
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
                addCarrier();
                viewCarrier();
                break;
            case 2:
                   String checkQuery = "SELECT COUNT(*) FROM tbl_carrier";
                if (conf.getSingleValue(checkQuery) > 0) {
                    viewCarrier();
                } else {
                    System.out.println("No carriers found in the database.");
                }
                break;
            case 3: 
                viewCarrier();
                updateCarrier();
                break;
            case 4:
                viewCarrier();
                deleteCarrier();
                break;
            case 5:
                back = false;
                break;
        }
        }while(back);
    }
    
    
   private static final String[] SERVICES = {
       "Standard",  
       "Express",  
       "Overnight" 
    };

    
    
    public void addCarrier(){
        
        Scanner sc = new Scanner(System.in);
        
        System.out.print("Carrier Name: ");
        String name = sc.next();
        System.out.print("Email: : ");
        String email = sc.next();   
        System.out.print("Phone Number: ");
        String phone = sc.next();
        System.out.print("\nDelivery Service\n");
        System.out.println("[1] Standard\n[2] Express\n[3] Overnight");
        System.out.print("Input Service: ");
        int Iservice = sc.nextInt();
        
        
        
        while (Iservice < 1 || Iservice > 3) {
            System.out.println("[Invalid choice. Please select a number between 1 and 3]");
            System.out.print("Input Service: ");
            Iservice = sc.nextInt();
        }
        
        String service = SERVICES[Iservice - 1];

        String sql = "INSERT INTO tbl_carrier (c_name, c_email, c_phone, c_delservice) VALUES (?, ?, ?, ?)";


        conf.addRecord(sql, name, email, phone, service);
    }
    
    
     public void viewCarrier() {
        String votersQuery = "SELECT * FROM tbl_carrier";
        String[] votersHeaders = {"ID", "Name", "Email", "Phone Contact", "Delivery Service"};
        String[] votersColumns = {"c_id", "c_name", "c_email", "c_phone", "c_delservice"};
     
        conf.viewRecords(votersQuery, votersHeaders, votersColumns);
    }
    
    
     public void updateCarrier()
    {     
        Scanner sc = new Scanner (System.in);
        config dbConfig = new config();
        System.out.print("Enter Carrier ID to Update: ");
        int id = sc.nextInt();
        
          String csql = "Select c_id FROM tbl_carrier WHERE c_id = ?";
        while(dbConfig.getSingleValue(csql, id) == 0){
            System.out.print("Carier does not exist, Select Again: ");
            id = sc.nextInt(); 
        }
        System.out.print("Carrier Name: ");
        String newName = sc.next();
        System.out.print("Email: : ");
        String newEmail = sc.next();
        System.out.print("Phone Number: ");
        String newPhone = sc.next();
        System.out.print("\nDelivery Service\n");
        System.out.println("[1] Standard\n[2] Express\n[3] Overnight");
        System.out.print("Input Service: ");
        int Iservice = sc.nextInt();
        while (Iservice < 1 || Iservice > 3) {
            System.out.println("[Invalid choice. Please select a number between 1 and 3]");
            System.out.print("Input Service: ");
            Iservice = sc.nextInt();
        }
        
        String newService = SERVICES[Iservice - 1];
        
        String sqlUpdate = "UPDATE tbl_carrier SET c_name = ?, c_email = ?, c_phone = ?, c_delservice = ? WHERE c_id = ?";
        dbConfig.updateRecord(sqlUpdate, newName, newEmail, newPhone, newService, id);
    
  }
    public void deleteCarrier(){
        Scanner in = new Scanner (System.in);
        config con = new config();
        config dbConfig = new config();
        System.out.print("Enter Carrier ID to Delete: ");
        int id = in.nextInt();
        
        String csql = "Select c_id FROM tbl_carrier WHERE c_id = ?";
        while(con.getSingleValue(csql, id) == 0){
            System.out.print("Carier does not exist, Select Again: ");
            id = in.nextInt(); 
        }
       
        String sqlDelete = "DELETE FROM tbl_carrier WHERE c_id = ?";
        dbConfig.deleteRecord(sqlDelete, id);
    }
    
}
