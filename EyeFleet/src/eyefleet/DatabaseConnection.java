/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package eyefleet;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 *
 * @author Student
 */
public class DatabaseConnection {

    Connection conn;
    Statement st;
    ResultSet rs;
    
    String user;
    String server;
    String schema;
    String password;
    
    public DatabaseConnection(
            String user,
            String server,
            String schema,
            String password) {
        
        this.password = password;
        this.schema = schema;
        this.server = server;
        this.user = user;
        
        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            conn = DriverManager.getConnection("jdbc:mysql://localhost/" + schema + "?user=" + user + 
		    "&password=ramjorgeyao");
            st = conn.createStatement();
        } catch (Exception e) {
            System.out.print(e);
            System.exit(0);
        }
        
    }
    
    public ResultSet setQuery(String query){
       
        try {
             rs = st.executeQuery(query);
        } catch (Exception e) {
            System.out.print(e);
            System.exit(0);
        }
        
        return rs;
    }
    
    public void updateDatabase(String updateQuery){
         
        try {
             st.executeUpdate(updateQuery);
        } catch (Exception e) {
            System.out.print(e);
            System.exit(0);
        }
    }
    
}
