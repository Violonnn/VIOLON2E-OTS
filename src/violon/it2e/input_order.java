
package violon.it2e;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;




public class input_order {
      config conf = new config();
      public void crud(){
      boolean back = true;
      do {
        Scanner in = new Scanner (System.in);
        System.out.println("----------------------------------");
        System.out.println("[ORDER CRUD MENU]");
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
                addOrder();
                viewOrder();
                break;
            case 2:
                  if (hasData("tbl_order")) {
                        viewOrder();
                    } else {
                        System.out.println("[No orders found in the database]");
                    }
                break;
            case 3: 
                if (hasData("tbl_order")) {
                        viewOrder();
                        updateOrder();
                    } else {
                        System.out.println("[No orders found in the database]");
                    }
                break;
            case 4:
                if (hasData("tbl_order")) {
                        viewOrder();
                        deleteOrder();
                    } else {
                        System.out.println("[No orders found in the database]");
                    }
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
        
         String name = "";
    while (name.isEmpty()) {
        System.out.print("Customer Name: ");
        name = sc.nextLine().trim();  
                if (name.isEmpty()) {
            System.out.println("[Carrier Name cannot be empty]");
        }
    }
         String addr = "";
    while (addr.isEmpty()) {
        System.out.print("Address: ");
        addr = sc.nextLine().trim();  
                if (addr.isEmpty()) {
            System.out.println("[Address cannot be empty]");
        }
    }
    
    
          String phone = "";
    while (true) {
        System.out.print("Contact No: ");
        phone = sc.nextLine().trim();

        if (phone.matches("^09\\d{9}$")) { 
            break;
        } else {
            System.out.println("[Phone Number must start with '09' and contain exactly 11 digits]");
        }
    }
    
     String product = "";
    while (true){
        System.out.print("Product Name: ");
        product = sc.nextLine().trim();
                if (product.isEmpty()) {
            System.out.println("[Carrier Name cannot be empty]");
        }
                else {
                     break;
                }
    }
    
   
     
        input_carrier carr = new input_carrier();
        carr.viewCarrier();
        
        int id = 0;
    String csql = "SELECT COUNT(*) FROM tbl_carrier WHERE c_id = ?";
    while (true) {
        System.out.print("Carrier ID to Deliver your Product: ");
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
        
        System.out.print("[Status: Shipping Parcel]");
        String status = "For Shipment";
             
            
        String sql = "INSERT INTO tbl_order (o_date, o_name, o_address, o_contact, o_product, o_status, c_id) VALUES (?, ?, ?, ?, ?, ?, ?)";


        conf.addRecord(sql, date, name, addr, phone, product, status, id);


    
    }
    
    
     public void viewOrder() {
       
        String votersQuery = "SELECT o_id, o_date, o_name, o_address, o_contact, o_product, c_name, o_status FROM tbl_order "
                + "LEFT JOIN tbl_carrier ON tbl_carrier.c_id = tbl_order.c_id";
        String[] votersHeaders = {"ID", "Date", "Name", "Address", "Phone Number","Product","Carrier Name", "Status"};
        String[] votersColumns = {"o_id", "o_date", "o_name", "o_address", "o_contact","o_product","c_name","o_status"};
        
     
        conf.viewRecords(votersQuery, votersHeaders, votersColumns);
        
     
    }
     
     
     
     
     
     
        config con = new config();
          
    public void updateOrder() {
        Scanner sc = new Scanner(System.in);
        int orderId = 0;
        String orderExistQuery = "SELECT COUNT(*) FROM tbl_order WHERE o_id = ?";
        while (true) {
            System.out.print("Enter Order ID to update: ");
            if (sc.hasNextInt()) {
                orderId = sc.nextInt();
                if (conf.getSingleValue(orderExistQuery, orderId) > 0) {
                    break;
                } else {
                    System.out.println("[Order ID not found. Please try again]");
                }
            } else {
                System.out.println("[Invalid input. Please enter a valid Order ID]");
                sc.next();
            }
        }

   
        String statusQuery = "SELECT o_status FROM tbl_order WHERE o_id = ?";
        String orderStatus = conf.getSingleValueAsString(statusQuery, orderId);

      
        boolean back = false;
        while (!back) {
            System.out.println("\nSelect an attribute to update:");
            System.out.println("[1] Name");
            System.out.println("[2] Address");
            System.out.println("[3] Contact");
            System.out.println("[4] Product");
            System.out.println("[5] Back");
            System.out.print("Choose an action: ");

            int action = 0;
            if (sc.hasNextInt()) {
                action = sc.nextInt();
                sc.nextLine();  
            } else {
                System.out.println("[Invalid input. Please enter a number from 1 to 5]");
                sc.next();
                continue;
            }

            switch (action) {
                case 1:
                   
                    System.out.print("Enter new Customer Name: ");
                    String newName = sc.nextLine().trim();
                    if (!newName.isEmpty()) {
                        String updateNameQuery = "UPDATE tbl_order SET o_name = ? WHERE o_id = ?";
                        conf.updateRecord(updateNameQuery, newName, orderId);
                        System.out.println("Customer Name updated successfully.");
                    } else {
                        System.out.println("[Customer Name cannot be empty]");
                    }
                    break;

                case 2:
                    
                    System.out.print("Enter new Address: ");
                    String newAddr = sc.nextLine().trim();
                    if (!newAddr.isEmpty()) {
                        String updateAddrQuery = "UPDATE tbl_order SET o_address = ? WHERE o_id = ?";
                        conf.updateRecord(updateAddrQuery, newAddr, orderId);
                        System.out.println("Address updated successfully.");
                    } else {
                        System.out.println("[Address cannot be empty]");
                    }
                    break;

                case 3:
                   
                    System.out.print("Enter new Contact No (must start with '09' and be 11 digits): ");
                    String newPhone = sc.nextLine().trim();
                    if (newPhone.matches("^09\\d{9}$")) {
                        String updatePhoneQuery = "UPDATE tbl_order SET o_contact = ? WHERE o_id = ?";
                        conf.updateRecord(updatePhoneQuery, newPhone, orderId);
                        System.out.println("Contact updated successfully.");
                    } else {
                        System.out.println("[Invalid phone number. Must start with '09' and be 11 digits]");
                    }
                    break;

                case 4:
                   
                    if ("For Shipment".equals(orderStatus)) {
                        System.out.print("Enter new Product Name: ");
                        String newProduct = sc.nextLine().trim();
                        if (!newProduct.isEmpty()) {
                           
                            LocalDate currdate = LocalDate.now();
                            DateTimeFormatter format = DateTimeFormatter.ofPattern("MM/dd/yyyy");
                            String currentDate = currdate.format(format);

                            String updateProductQuery = "UPDATE tbl_order SET o_product = ?, o_date = ? WHERE o_id = ?";
                            conf.updateRecord(updateProductQuery, newProduct, currentDate, orderId);
                            System.out.println("Product and Order Date updated successfully.");
                        } else {
                            System.out.println("[Product Name cannot be empty]");
                        }
                    } else if ("Out for Delivery".equals(orderStatus)) {
                        System.out.println("[Product is already Out for Delivery. Cannot update Product]");
                    } else if ("Delivered".equals(orderStatus)) {
                        System.out.println("[Product has already been Delivered. Cannot update Product]");
                    }
                    break;

                case 5:
                   
                    back = true;
                    break;

                default:
                    System.out.println("[Invalid action. Please select a number from 1 to 5]");
            }
        }
    }
  
        
        
        public void deleteOrder(){
              Scanner in = new Scanner(System.in);
    config dbConfig = new config();

    boolean validID = false;
    int id = 0;
    
    while (!validID) {
        System.out.print("Enter Order ID to Delete: ");
        
     
        if (in.hasNextInt()) {
            id = in.nextInt();

          
            String checkQuery = "SELECT COUNT(*) FROM tbl_order WHERE o_id = ?";
            if (dbConfig.getSingleValue(checkQuery, id) > 0) {
                validID = true; 
            } else {
                System.out.println("[Order ID does not exist. Please try again]");
            }
        } else {
           
            System.out.println("[Please enter a numeric Order ID]");
            in.next();  
        }
    }
    
  
    System.out.print("Are you sure you want to delete Order ID " + id + "? (yes/no): ");
    String confirmDelete = in.next();


    if (confirmDelete.equalsIgnoreCase("yes")) {
       
        String sqlDelete = "DELETE FROM tbl_order WHERE o_id = ?";
        dbConfig.deleteRecord(sqlDelete, id);

       
        String checkQueryAfterDelete = "SELECT COUNT(*) FROM tbl_order";
        if (dbConfig.getSingleValue(checkQueryAfterDelete) > 0) {
          
            System.out.print("Do you want to delete another Order? (yes/no): ");
            String continueDelete = in.next();

            if (!continueDelete.equalsIgnoreCase("yes")) {
                System.out.println("Returning to the CRUD menu...");
             
            }
        } else {
            
            System.out.println("No orders left in the database. Returning to the CRUD menu...");
               }
    } else {
        System.out.println("Deletion canceled. Returning to the CRUD menu...");
         
    }
        }
    
   private boolean hasData(String tableName) {
        String query = "SELECT COUNT(*) FROM " + tableName;
        return conf.getSingleValue(query) > 0;     
        
     
}
}
