/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Maintenance;

import eyefleet.DatabaseConnection;
import eyefleet.LayoutController;
import java.sql.ResultSet;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ComboBox;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javax.swing.JOptionPane;

/**
 *
 * @@author ()->Jorge{}
 */
public class TruckStatus {
        //the controller
        private LayoutController main_module;
         
        //assign there observablelist  first in each DATA
        private ObservableList<DatatruckStatus> datatruckStatus;
        private ObservableList<DataruckPartition> dataruckPartitions;
         
        //assign the table for each variable
        private TableView tbl_truckStatus;
        private TableView tbl_truckPartition;
         
        //table column of datatruckStatus
        private TableColumn trucks_col_truck_id;
        private TableColumn trucks_col_capacity;
        private TableColumn trucks_col_status;
         
        //tble column of dataruckPartitions
        private TableColumn partition_col_partition_id;
        private TableColumn partition_col_capacity;
        private TableColumn partition_order_id;
         
        //ContextMenuItem
        private MenuItem status_menu_replacement;
        private MenuItem status_menu_service_only;
         
        //coombobox of the repelce and repiar;
        private ComboBox Replacements_cbo_truck;
        private ComboBox Services_cbo_truck;
         
        //this is the variable of String 
        private String selectedcboTruckpart = "";
         
         
    public TruckStatus(LayoutController main_module_param) {
        //load the controller
        this.main_module = main_module_param;
        
        //assign the value of the table
        this.tbl_truckStatus = main_module_param.getTruck_status_tbl_trucks();
        this.tbl_truckPartition = main_module_param.getTruck_status_tbl_partition();
         
        //assigning the value of the table column of datatruckStatus
        this.trucks_col_truck_id =  main_module_param.getTruck_status_tbl_trucks_col_truck_id();
        this.trucks_col_capacity = main_module_param.getTruck_status_tbl_trucks_col_capacity();
        this.trucks_col_status = main_module_param.getTruck_status_tbl_trucks_col_status();
         
        //assigning the table column of the datatruckPrtition
        this.partition_col_partition_id = main_module_param.getTruck_status_tbl_partition_col_partition_id();
        this.partition_col_capacity = main_module_param.getTruck_status_tbl_partition_col_capacity();
        this.partition_order_id = main_module_param.getTruck_status_tbl_partition_order_id();
       
        //assign the comobox of the replacement combobox and the repair combobox;
        this.Replacements_cbo_truck = main_module_param.getReplacements_cbo_truck();
        this.Services_cbo_truck =   main_module_param.getServices_cbo_truck();
        
        //load the context menus
        this.status_menu_replacement = main_module.getTruck_status_menu_replacement();
        this.status_menu_service_only = main_module.getTruck_status_menu_service_only();
        
         //initilization of the Modules 
         initializeModule();
       }
    
    public void initializeModule(){
        //set the functionalities and prepare nodes
        setUptablecolumns();
        setUpitems();
        setUpfunctions();
        setUpclickContectmenureplacement();
        setUpclickContectmenuServiceonly();
     }
    
     public void setUptablecolumns(){
         // for the table truck status observablelist
        datatruckStatus = FXCollections.observableArrayList();
        trucks_col_truck_id.setCellValueFactory(new PropertyValueFactory("truck_id"));
        trucks_col_capacity.setCellValueFactory(new PropertyValueFactory("total_capacity"));
        trucks_col_status.setCellValueFactory(new PropertyValueFactory("status"));
        tbl_truckStatus.setItems(datatruckStatus);
         
        //for the table partition observablelist
        dataruckPartitions = FXCollections.observableArrayList();
        partition_col_partition_id.setCellValueFactory(new PropertyValueFactory("partition_id"));
        partition_col_capacity.setCellValueFactory(new PropertyValueFactory("capacity"));
        partition_order_id.setCellValueFactory(new PropertyValueFactory("truck_id"));
        tbl_truckPartition.setItems(dataruckPartitions);
     }
     
     //setUp the items of the table truckstatus />
      public void setUpitems(){
        //Create database connection   
        DatabaseConnection dbtruckstatus0 = new DatabaseConnection("root", "localost", "eyefleet", "ramjorgeyao");
        ResultSet rs = dbtruckstatus0.setQuery("SELECT * FROM eyefleet.truck");
      
            try {
                while(rs.next()){
                    //get the data of the resultset
                    datatruckStatus.add(new DatatruckStatus(rs.getString("truck_id"), 
                                                        rs.getString("total_capacity"),
                                                        rs.getString("status")));
                }
            
            } catch (Exception e) {
                System.out.print("ERROR ON MODULE TRUCKSTATUS SETUPITEMS" + e);
                System.exit(0);
            }
     }
      
     public void setUpfunctions(){
            //function for tbl_truckStatus
            tbl_truckStatus.setOnMouseClicked((MouseEventv)->{
            DatabaseConnection dbtruckstatus1 = new DatabaseConnection("root", "localost", "eyefleet", "ramjorgeyao");
            
            //clear the data;
            dataruckPartitions.clear();
            
            //set the item list of the selected item;
            DatatruckStatus datatruckStatus_select;;
            datatruckStatus_select = (DatatruckStatus)tbl_truckStatus.getSelectionModel().getSelectedItem();
            
            //check the table if it have an item seleted; start of IF;
             if(datatruckStatus_select != null){
                 
                 try {
                 //set the resulset;
                 ResultSet rs_select = dbtruckstatus1.setQuery("SELECT * FROM eyefleet.partition where truck_id = '" + datatruckStatus_select.getTruck_id() +"'");

                 //get the data of the resulset;
                 while(rs_select.next()){ 
                     
                      dataruckPartitions.add(new DataruckPartition(rs_select.getString("partition_id"),
                                                                  rs_select.getString("capacity"), 
                                                                  rs_select.getString("truck_id")));
                         }
                 
                  
             } catch (Exception e) {
             System.out.print("ERROR ON MODULE TRUCKSTATUS SETUPFUNCTIONS " + e);
             System.exit(0);
             }
                
             }
                 
               
             
         });
      }
     
     public void setUpclickContectmenureplacement(){
            //function for contextMenu replacement
            status_menu_replacement.setOnAction((ActionEvent)->{
             
            //select the data in the selected table;
            DatatruckStatus datatruckStatus_select = (DatatruckStatus)tbl_truckStatus.getSelectionModel().getSelectedItem();
            
            //START OF IF STATEMENT CHECK IF THE COMBOBOX REPLACEMENT IS NULL
            if(datatruckStatus_select == null){
                main_module.getMessage().message("Replacement!", "No Record Selected Please Select a Record:", "Back");
             }
            
            //ERRROR trapping
            else{
            //open the bookmaintenance and open the replacement module;
            main_module.getBookMaintenance().openReplacements();
            
            //get the value of the combobox in the replacement combobox;              
             Replacements_cbo_truck.setValue(datatruckStatus_select.getTruck_id()); 
            
                    }
          });
      }
     
    public void setUpclickContectmenuServiceonly(){
            //function for contextMeneu services
            status_menu_service_only.setOnAction((ActionEvent)->{
            
            //select the data in the selected table;
            DatatruckStatus datatruckStatus_select = (DatatruckStatus)tbl_truckStatus.getSelectionModel().getSelectedItem();
            
            //CHECK THE DATA IF ITS HAVE VALUE IF NULL EXECUTE THE CODE, START OF IF STATEMENT;
            if(datatruckStatus_select == null){
                main_module.getMessage().message("Repair Services!", "No Record Selected Please Select a Record:", "Back");
            }
            else{
             //open the bookmaintenance and open the services module;
            main_module.getBookMaintenance().openRepairServices();
           
            //get the value of the combobox in the replacement combobox;            
            Services_cbo_truck.setValue(datatruckStatus_select.getTruck_id());
            }
          });
      } 
    
  //function for checking the status the name of this function is the Scanning 
    public void Setcheckstatus(String truck){
        
        //assign the logical variables od the replace and repair;
        boolean replace = false; 
        boolean repair = false;
         
        //assign a variable for String ;
        String status = "";
        
        //database connection and resultset;
        DatabaseConnection dbupdatereplace = new DatabaseConnection("root", "localhost", "eyefleet", "ramjorgeyao");
        ResultSet rsupdate = dbupdatereplace.setQuery("SELECT * FROM eyefleet.replacement;");
        
        try {
            //get the value of the resultset;
            while(rsupdate.next()){
                
                // get the truck_id and status
                if(rsupdate.getString("truck_id").equals(truck) && 
                   rsupdate.getString("status").equals("on replace")){
                    replace = true;
                    break;
               } 
            }
        } catch (Exception e) {
            System.out.print("ERROR ON MODULE TRUCKSTATUS SETCHECKSTATUS: " +e);
            System.exit(0);
        }
        
         try {
             //CREATE DATABASE ADN RESULSET;
            DatabaseConnection dbepair = new DatabaseConnection("root", "localhost", "eyefleet", "ramjorgeyao");//second database for repair 
            ResultSet rsrepair = dbepair.setQuery("SELECT * FROM eyefleet.repair_service");
                
                 //get the value of the resultset;
                while(rsrepair.next()){//same as the replce
                     if(rsrepair.getString("truck_id").equals(truck) && 
                       rsrepair.getString("status").equals("on repair")){
                        repair = true;
                        break;
                     }
                }
           
        } catch (Exception e) {
            System.out.print( "ERROR ON MODULE TRUCKSTATUS SETCHECKSTATUS: "+ e);
            System.exit(0);
        }
        
         //this is the conditional statement and the algorithm of the truck rules
         if(replace == true){
             status = "on replace";
         }
         if(repair == true){
             status = "on repair";
         }
         if(replace == true && repair == true){
             status = "not available";
         }
         if(replace == false && repair == false){
             status = "available";
             
         }
         
      
         try {
            //UPDATE the NEW STATUS:
            DatabaseConnection dbupdatestat = new DatabaseConnection("root", "localhost", "eyefleet", "ramjorgeyao");
            dbupdatestat.updateDatabase("UPDATE eyefleet.truck SET status = '" + status + "' WHERE truck_id ='" + truck + "'");
           
            //clear the data;
            datatruckStatus.clear();
            
            //refresh the tabel status;
            setUpitems();
               
        } catch (Exception e) {
            System.out.print("ERROR ON MODULE TRUCKSTATUS SETCHECKSTATUS: "+e);
            System.exit(0);
        }
    }
    
    
    public class DatatruckStatus{
        
    private String truck_id;
    private String total_capacity;
    private String status; 
    
        public DatatruckStatus(String truck_id,
                               String total_capacity,
                               String status) {
            
        this.truck_id = truck_id;
        this.total_capacity = total_capacity;
        this.status = status;
            
        }

         
        public String getTruck_id() {
            return truck_id;
        }

         
        public void setTruck_id(String truck_id) {
            this.truck_id = truck_id;
        }

          
        public String getTotal_capacity() {
            return total_capacity;
        }

         
        public void setTotal_capacity(String total_capacity) {
            this.total_capacity = total_capacity;
        }

         
        public String getStatus() {
            return status;
        }

         
        public void setStatus(String status) {
            this.status = status;
        }
        
    }
    public class DataruckPartition {
    
    private String partition_id;
    private String capacity;
    private String truck_id;

        public DataruckPartition(String partition_id,
                                 String capacity,
                                 String truck_id) {
        this.partition_id = partition_id;
        this.capacity = capacity;
        this.truck_id = truck_id;
            
        }
          
        public String getPartition_id() {
            return partition_id;
        }
          
        public void setPartition_id(String partition_id) {
            this.partition_id = partition_id;
        }
          
        public String getCapacity() {
            return capacity;
        }

         public void setCapacity(String capacity) {
            this.capacity = capacity;
        }

         public String getTruck_id() {
            return truck_id;
        }
          public void setTruck_id(String truck_id) {
            this.truck_id = truck_id;
        }

     
    }
}
