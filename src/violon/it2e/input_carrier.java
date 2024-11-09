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
      
        boolean validInput = true;

        
        while (validInput) {
            if (in.hasNextInt()) {
                act = in.nextInt();
              
                if (act >= 1 && act <= 5) {
                    validInput = false;
                } else {
                    System.out.print("[Enter a number ranging from 1 to 5 only] Try Again: ");
                }
            } else {
                System.out.print("[No letters and symbols are allowed] Try Again: ");
                in.next(); 
            }
        }
       
        switch(act){
            case 1:
                addCarrier();
                viewCarrier();
                break;
            case 2:
                if (hasRecords("tbl_carrier")) {
                        viewCarrier();
                    } else {
                        System.out.println("[No carriers found in the database]");
                    }
                break;
            case 3: 
                  if (hasRecords("tbl_carrier")) {
                        viewCarrier();
                        updateCarrier();
                    } else {
                        System.out.println("[No carriers found in the database]");
                    }
                break;
            case 4:
                if (hasRecords("tbl_carrier")) {
                        viewCarrier();
                        deleteCarrier();
                    } else {
                        System.out.println("[No carriers found in the database]");
                    }
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

    
    
   
   
   
   
   
   public void addCarrier() {
    Scanner sc = new Scanner(System.in);

   
    String name = "";
    while (name.isEmpty()) {
        System.out.print("Carrier Name: ");
        name = sc.nextLine().trim();  
        if (name.isEmpty()) {
            System.out.println("[Carrier Name cannot be empty]");
        }
    }

   
    String email = "";
    while (true) {
        System.out.print("Email: ");
        email = sc.nextLine().trim();
        if (email.contains("@gmail") && email.matches("[A-Za-z0-9_]+@[A-Za-z0-9]+\\.com")) {
            break;
        } else {
            System.out.println("[Email must end with '@gmail' and contain only letters, numbers, or underscores]");
        }
    }

    
     String phone = "";
    while (true) {
        System.out.print("Phone Number: ");
        phone = sc.nextLine().trim();

        if (phone.matches("^09\\d{9}$")) { 
            break;
        } else {
            System.out.println("[Phone Number must start with '09' and contain exactly 11 digits]");
        }
    }

   
    String service = "";
        System.out.println("Select Delivery Service:");
        System.out.println("[1] Standard\n[2] Express\n[3] Overnight");
    while (true) {
        System.out.print("Input Service: ");
        int choice = 0;

        if (sc.hasNextInt()) {
            choice = sc.nextInt();
            if (choice >= 1 && choice <= 3) {
                String[] services = {"Standard", "Express", "Overnight"};
                service = services[choice - 1];
                break;
            } else {
                System.out.println("[Please select a number between 1 and 3]");
            }
        } else {
            System.out.println("[Please enter a valid number]");
            sc.next();  
        }
    }

    String sql = "INSERT INTO tbl_carrier (c_name, c_email, c_phone, c_delservice) VALUES (?, ?, ?, ?)";
    conf.addRecord(sql, name, email, phone, service);
}
    
   
   
   
   
   
   
   
     public void viewCarrier() {
        String votersQuery = "SELECT * FROM tbl_carrier";
        String[] votersHeaders = {"ID", "Name", "Email", "Phone Contact", "Delivery Service"};
        String[] votersColumns = {"c_id", "c_name", "c_email", "c_phone", "c_delservice"};
     
        conf.viewRecords(votersQuery, votersHeaders, votersColumns);
        
    }
    
    
     
     
     
     
     
     
     
     
     
  public void updateCarrier() {
    Scanner sc = new Scanner(System.in);
    config dbConfig = new config();

   
    int id = 0;
    String csql = "SELECT COUNT(*) FROM tbl_carrier WHERE c_id = ?";
    while (true) {
        System.out.print("Carrier ID to Update: ");
        if (sc.hasNextInt()) {
            id = sc.nextInt();
            if (conf.getSingleValue(csql, id) > 0) {
                break;
            } else {
                System.out.println("[Carrier ID not found. Please try again]");
            }
        } else {
            System.out.println("[Please enter a valid Carrier ID]");
            sc.next(); 
        }
    }
    
    
    while (true) {
        System.out.println("\nSelect the field to update:");
        System.out.println("[1] Carrier Name");
        System.out.println("[2] Email");
        System.out.println("[3] Phone Number");
        System.out.println("[4] Delivery Service");
        System.out.println("[5] Exit");
        System.out.print("Input Action): ");
        
        int choice = 0;
        if (sc.hasNextInt()) {
            choice = sc.nextInt();
            sc.nextLine(); 
        }

        if (choice < 1 || choice > 5) {
            System.out.println("[Please select a number between 1 and 5]");
            continue;
        }

        String updatedValue = "";
        switch (choice) {
            case 1:
              
                while (updatedValue.isEmpty()) {
                    System.out.print("Updated Carrier Name: ");
                    updatedValue = sc.nextLine().trim();
                }
                break;
            case 2:
        
                while (true) {
                    System.out.print("Updated Carrier Email: ");
                    updatedValue = sc.nextLine().trim();
                    if (updatedValue.contains("@gmail") && updatedValue.matches("[A-Za-z0-9_]+@[A-Za-z0-9]+\\.com")) {
                        break;
                    } else {
                        System.out.println("[Email must end with '@gmail' and contain only letters, numbers, or underscores]");
                    }
                }
                break;
            case 3:
              
                while (true) {
                    System.out.print("Updated Phone Number: ");
                    updatedValue = sc.nextLine().trim();

                    if (updatedValue.matches("^09\\d{9}$")) { 
                        break;
                    } else {
                        System.out.println("[Phone Number must start with '09' and contain exactly 11 digits]");
                    }
                }
                break;
            case 4:
             
                while (true) {
                    System.out.println("Select Delivery Service:");
                    System.out.println("[1] Standard\n[2] Express\n[3] Overnight");
                    System.out.print("Input Service: ");
                    int serviceChoice = 0;

                    if (sc.hasNextInt()) {
                        serviceChoice = sc.nextInt();
                        if (serviceChoice >= 1 && serviceChoice <= 3) {
                            String[] services = {"Standard", "Express", "Overnight"};
                            updatedValue = services[serviceChoice - 1];
                            break;
                        } else {
                            System.out.println("[Please select a number between 1 and 3]");
                        }
                    } else {
                        System.out.println("[Please enter a valid number]");
                        sc.next();  
                    }
                }
                break;
            case 5:
                return;  
        }

        System.out.print("Are you sure you want to update this field? (yes/no): ");
        String confirm = sc.nextLine().trim();
        
        if (confirm.equalsIgnoreCase("yes")) {
            String sqlUpdate = "";
            switch (choice) {
                case 1:
                    sqlUpdate = "UPDATE tbl_carrier SET c_name = ? WHERE c_id = ?";
                    dbConfig.updateRecord(sqlUpdate, updatedValue, id);
                    break;
                case 2:
                    sqlUpdate = "UPDATE tbl_carrier SET c_email = ? WHERE c_id = ?";
                    dbConfig.updateRecord(sqlUpdate, updatedValue, id);
                    break;
                case 3:
                    sqlUpdate = "UPDATE tbl_carrier SET c_phone = ? WHERE c_id = ?";
                    dbConfig.updateRecord(sqlUpdate, updatedValue, id);
                    break;
                case 4:
                    sqlUpdate = "UPDATE tbl_carrier SET c_delservice = ? WHERE c_id = ?";
                    dbConfig.updateRecord(sqlUpdate, updatedValue, id);
                    break;
            }
            System.out.println("Record updated successfully.");
        } else {
            System.out.println("Update canceled.");
        }
    }
}
   public void updateOrder() {
    viewCarrier(); 
    Scanner sc = new Scanner(System.in);
    config conf = new config();

    int carrierId = 0;
    String carrierQuery = "SELECT COUNT(*) FROM tbl_carrier WHERE c_id = ?";  
    boolean val = true;
    boolean vali = true;
        do{
        System.out.print("Input Carrier ID to update Orders: ");
        
        if (sc.hasNextInt()) {
            carrierId = sc.nextInt();
            if (conf.getSingleValue(carrierQuery, carrierId) > 0) {
                if (conf.hasOrders(carrierId)) {
                    break;  
                } else {
                    System.out.println("[No orders found for this carrier]");
                }
            } else {
                System.out.println("[Carrier ID not found. Try again]");
            }
        } else {
            System.out.println("[Invalid input. Please enter a number]");
            sc.next(); 
        }
            System.out.print("Enter 'y' to continue: ");
            String y = sc.next();
            if (!y.equalsIgnoreCase("y")){
                val = false;
                vali = false;
            }
    }while(val);

    while(vali){
    String orderQuery = "SELECT o_id, o_name, o_product, o_address, o_status FROM tbl_order WHERE c_id = ?";
    String[] headers = {"Order ID", "Customer Name", "Product", "Address", "Status"};
    String[] columns = {"o_id", "o_name", "o_product", "o_address", "o_status"};
    conf.viewCarOrder(orderQuery, headers, columns, carrierId);

    int orderId = 0;
    boolean sure = true;
    do{
        System.out.print("Enter Order ID to update: ");
        
        if (sc.hasNextInt()) {
            orderId = sc.nextInt();
            String statusQuery = "SELECT o_status FROM tbl_order WHERE o_id = ?";
            String status = conf.getSingleValueAsString(statusQuery, orderId);
            
            if (status != null) {
                if ("Delivered".equals(status)) {
                    System.out.println("[Product has already been delivered. No update allowed.]");
                }
                break; 
            } else {
                System.out.println("[Order ID not found. Try again]");
            }
        } else {
            System.out.println("[Invalid input. Please enter a valid Order ID]");
            sc.next(); 
        }
        System.out.print("Enter 'y' to continue: ");
         String y = sc.next();
            if (!y.equalsIgnoreCase("y")){
                sure = false;
            }
    } while(sure);

    
    String currentStatus = conf.getSingleValueAsString("SELECT o_status FROM tbl_order WHERE o_id = ?", orderId);
    String newStatus = "";
    
    if ("For Shipment".equals(currentStatus)) {
        System.out.println("\nCurrent status: For Shipment");
        System.out.println("[1] Out for Delivery");
        System.out.println("[2] Delivered");
        
        while (true) {
            System.out.print("Select new status: ");
            int choice = sc.nextInt();
            
            if (choice == 1) {
                newStatus = "Out for Delivery";
                break;
            } else if (choice == 2) {
                newStatus = "Delivered";
                break;
            } else {
                System.out.println("[Invalid choice. Please select 1 or 2]");
            }
        }
        
    } else if ("Out for Delivery".equals(currentStatus)) {
        System.out.println("Current status: Out for Delivery");
        System.out.println("[1] Confirm Delivered");
        System.out.println("[2] No Action");
        
        while (true) {
            System.out.print("Do you want to update the status to Delivered? ");
            int confirmChoice = sc.nextInt();
            
            if (confirmChoice == 1) {
                newStatus = "Delivered";
                break;
            } else if (confirmChoice == 2) {
                System.out.println("[No updates were made.]");
                return; 
            } else {
                System.out.println("[Invalid choice. Please select 1 or 2]");
            }
        }
        
    } else {
        System.out.println("[Order cannot be updated, returning to crud.]");
        return;
    }

   
    if (!newStatus.isEmpty()) {
        String updateStatusQuery = "UPDATE tbl_order SET o_status = ? WHERE o_id = ?";
        conf.updateRecord(updateStatusQuery, newStatus, orderId);
        System.out.println("[Order status updated successfully to " + newStatus + "]");
        break;
    }
}
   }


         
 
     
     
     
  
  
  
     
    public void deleteCarrier(){
    Scanner in = new Scanner(System.in);
    config dbConfig = new config();

    boolean validID = false;
    int id = 0;
    
    while (!validID) {
        System.out.print("Enter Carrier ID to Delete: ");
        
     
        if (in.hasNextInt()) {
            id = in.nextInt();

          
            String checkQuery = "SELECT COUNT(*) FROM tbl_carrier WHERE c_id = ?";
            if (dbConfig.getSingleValue(checkQuery, id) > 0) {
                validID = true; 
            } else {
                System.out.println("[Carrier ID does not exist. Please try again]");
            }
        } else {
           
            System.out.println("[Please enter a numeric Carrier ID]");
            in.next();  
        }
    }
    
  
    System.out.print("Are you sure you want to delete Carrier ID " + id + "? (yes/no): ");
    String confirmDelete = in.next();


    if (confirmDelete.equalsIgnoreCase("yes")) {
       
        String sqlDelete = "DELETE FROM tbl_carrier WHERE c_id = ?";
        dbConfig.deleteRecord(sqlDelete, id);

       
        String checkQueryAfterDelete = "SELECT COUNT(*) FROM tbl_carrier";
        if (dbConfig.getSingleValue(checkQueryAfterDelete) > 0) {
          
            System.out.print("Do you want to delete another Carrier? (yes/no): ");
            String continueDelete = in.next();

            if (!continueDelete.equalsIgnoreCase("yes")) {
                System.out.println("Returning to the CRUD menu...");
             
            }
        } else {
            
            System.out.println("No carriers left in the database. Returning to the CRUD menu...");
               }
    } else {
        System.out.println("Deletion canceled. Returning to the CRUD menu...");
         
    }
}
    
    
    
    
    
    
    
     public boolean hasRecords(String tableName) {
        String query = "SELECT COUNT(*) FROM " + tableName;
        return conf.getSingleValue(query) > 0;
    }
    
}
