/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package violon.it2e;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 *
 * @author Admin
 */
public class NewClass {
    public void viewOrdersWithCarrierName() {
    String sql = "SELECT o.o_date, o.o_name, o.o_address, o.o_contact, o.o_status, c.c_name " +
                 "FROM tbl_order o " +
                 "JOIN tbl_carrier c ON o.carrier_id = c.c_id"; // Join orders with carriers

    try (Connection conn = this.connectDB();
         PreparedStatement pstmt = conn.prepareStatement(sql);
         ResultSet rs = pstmt.executeQuery()) {

        // Print headers
        System.out.println("Order Date | Client Name | Address | Contact No. | Status | Carrier Name");
        System.out.println("----------- | ----------- | ------- | ------------ | ------ | ------------");
        
        // Loop through the results and print them
        while (rs.next()) {
            String orderDate = rs.getString("o_date");
            String clientName = rs.getString("o_name");
            String address = rs.getString("o_address");
            String contactNo = rs.getString("o_contact");
            String status = rs.getString("o_status");
            String carrierName = rs.getString("c_name");
            
            System.out.printf("%-11s | %-11s | %-7s | %-12s | %-6s | %s%n", 
                              orderDate, clientName, address, contactNo, status, carrierName); // Format output
        }
    } catch (SQLException e) {
        System.out.println("Error retrieving orders: " + e.getMessage());
    }
}

}
