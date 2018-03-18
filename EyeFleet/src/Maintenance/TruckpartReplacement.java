/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Maintenance;

import MasterControl.Mc_TruckParts;
import eyefleet.ErrorTraps;
import eyefleet.DatabaseConnection;
import eyefleet.LayoutController;
import java.sql.Date;
import java.sql.ResultSet;
import java.time.LocalDate;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

/**
 *
 * @author ()->Jorge{}
 */
public class TruckpartReplacement {
    
     
    //the controller;
    private LayoutController main_module;
    
    //Error traps class:
     
    ErrorTraps errorTrapping = new ErrorTraps();
    //assign the table for each variable;
    private TableView tbl_truckpartReplacement;
    private TableView  tbl_truckpartsInventory;

    //table column of dataReplacement;
    private TableColumn replacements_col_date;
    private TableColumn replacements_col_status;
    private TableColumn replacements_col_location;
    private TableColumn replacements_col_truck_id;
    private TableColumn replacements_col_truck_part;
    private TableColumn replacements_col_description;
    private TableColumn replacements_col_quantity;
    
    //assign the combobox;
    private ComboBox Replacements_cbo_truck;
    private ComboBox Replacements_cbo_truck_part;
    
    //assign the textfields;
    private TextField Replacements_txt_description;
    private TextField Replacements_txt_location;
    private TextField Replacements_txt_quantity;
    
    //assign the label button;
    private Label Replacements_btn_save;
    private Label Replacements_btn_set_as_repaired;

    //assign there observablelist  first in each DATA
    private ObservableList<DataReplacement> dataReplacements;

    //this is the variable of String 
    private String selectedElement ="";
    
    public Label btn_refresh;
    
    public TruckpartReplacement(LayoutController main_moduleparam) {
        //for controller;
        this.main_module = main_moduleparam;
        
         
        
        //for tableview;
        this.tbl_truckpartReplacement = main_moduleparam.getReplacements_tbl_replacements();
        this.tbl_truckpartsInventory = main_moduleparam.getTruck_parts_tbl_truck_parts(); 
        
        //for tablecolumns;
        this.replacements_col_date = main_moduleparam.getReplacements_tbl_replacements_col_date();
        this.replacements_col_status = main_moduleparam.getReplacements_tbl_replacements_col_status();
        this.replacements_col_location = main_moduleparam.getReplacements_tbl_replacements_col_location();
        this.replacements_col_truck_id = main_moduleparam.getReplacements_tbl_replacements_col_truck_id();
        this.replacements_col_truck_part = main_moduleparam.getReplacements_tbl_replacements_col_truck_part();
        this.replacements_col_description = main_moduleparam.getReplacements_tbl_replacements_col_description();
        this.replacements_col_quantity = main_moduleparam.getReplacements_tbl_replacements_col_quantity();
        
        //for combobox;
        this.Replacements_cbo_truck = main_moduleparam.getReplacements_cbo_truck();
        this.Replacements_cbo_truck_part = main_moduleparam.getReplacements_cbo_truck_part();
        
        //for textfileds
        this.Replacements_txt_description = main_moduleparam.getReplacements_txt_description();
        this.Replacements_txt_location = main_moduleparam.getReplacements_txt_location();
        this.Replacements_txt_quantity = main_moduleparam.getReplacements_txt_quantity();
        
        //for the label button;
        this.Replacements_btn_save = main_moduleparam.getReplacements_btn_save();
        this.Replacements_btn_set_as_repaired = main_moduleparam.getReplacements_btn_set_as_repaired();
        
         this.btn_refresh =  main_moduleparam.getReplacements_btn_refresh();
        //initialized Modules;
        initilizaModule();
        
    }
    public void initilizaModule(){
        //set the functionalities and prepare nodes
        SetUptablecolumns();
        setUpItems();
        setUpcboTruck();
        setUpItemscbotruckPart();
        setUpfunctionbtnreplacementSave();
        setUpfuntionasRepaired();
        
        btnRefresh();
   
    }
    
    
    
     public void SetUptablecolumns(){
        //for the table replacement observablelist;
        dataReplacements = FXCollections.observableArrayList();
        replacements_col_date.setCellValueFactory(new PropertyValueFactory("replacement_date"));
        replacements_col_status.setCellValueFactory(new PropertyValueFactory("status"));
        replacements_col_location.setCellValueFactory(new PropertyValueFactory("location"));
        replacements_col_truck_id.setCellValueFactory(new PropertyValueFactory("truck_id"));
        replacements_col_truck_part.setCellValueFactory(new PropertyValueFactory("truck_part_id"));
        replacements_col_description.setCellValueFactory(new PropertyValueFactory("description"));
        replacements_col_quantity.setCellValueFactory(new PropertyValueFactory("quantity"));
        tbl_truckpartReplacement.setItems(dataReplacements);
      }
    
        //funtions to setup all data in replacements;
    public void setUpItems(){
        
        //create database;
        DatabaseConnection dbconnect = new DatabaseConnection("root", "localhost", "eyefleet", "ramjorgeyao");
        ResultSet rs_select = dbconnect.setQuery("select replacement.replacement_id, replacement.replacement_date, replacement.truck_id, replacement.truck_part_id, replacement.status, " +
                                                 "(select truck_part.description from truck_part " +
                                                 "where replacement.truck_part_id = truck_part.truck_part_id)  as 'description', replacement.location, replacement.quantity " +
                                                 "from replacement WHERE status = 'on replace'");
        try { 
            //get the value of the resulset;
            while(rs_select.next()){
                
                //add the record to the table OF obervable list
                dataReplacements.add(new DataReplacement(rs_select.getString("replacement_id"),
                                                         rs_select.getDate("replacement_date"),
                                                         rs_select.getString("status"),
                                                         rs_select.getString("location"),
                                                         rs_select.getString("truck_id"),
                                                         rs_select.getString("truck_part_id"),
                                                         rs_select.getString("description"),
                                                         rs_select.getString("quantity")));
             }
        } catch (Exception e) {
            System.out.print("ERROR ON MODULE TRUCKPART REPLACEMENT SETUPITEMS " + e);
            System.exit(0);
        }
    }
    
      //SET UP THE COMBOBOX TRUCK;
     public void setUpcboTruck(){     
         //CREATE DATABASE and RESULSET;
         DatabaseConnection dbreplacementcon = new DatabaseConnection("root", "localhost", "eyefleet", "ramjorgeyao");
         ResultSet rs_select = dbreplacementcon.setQuery("SELECT * FROM eyefleet.truck;");
            try {
             
                //GET THE RESULSET VALUE;
                while(rs_select.next()){
                      Replacements_cbo_truck.getItems().add(rs_select.getString("truck_id"));
                }
            } catch (Exception e) {
                System.out.print( "ERROR ON MODULE TRUCKPART REPLACEMENT setUpcboTruck "+e);
                System.exit(0);
            }
     }
     
     //set up the itemlist of the cbotruckpart;
      public void setUpItemscbotruckPart(){
        //CREATE DATABASE AND RESULSET;
        DatabaseConnection dbreplacementcon = new DatabaseConnection("root", "localhost", "eyefleet", "ramjorgeyao");
        ResultSet rs_select = dbreplacementcon.setQuery("SELECT * FROM eyefleet.truck_part;");
        
        try {    
            //GET THE RESULSET VALUE;
            while(rs_select.next()){
                Replacements_cbo_truck_part.getItems().add(rs_select.getString("truck_part_id"));
            }
        } catch (Exception e) {
            System.out.print( "ERROR ON MODULE TRUCKPART REPLACEMENT setUpcboTruck " + e);
            System.exit(0);
        }
        
            //setup items cbo replacement FUNCTIONS;
            Replacements_cbo_truck_part.setOnAction((ActionEvent)-> {
            
            //GET THE SELECTED ITEMLIST IN THE COMBOBOX
            String  selectedElement;
            selectedElement = Replacements_cbo_truck_part.getSelectionModel().getSelectedItem().toString();
            ResultSet cboselect = dbreplacementcon.setQuery("SELECT * FROM eyefleet.truck_part where truck_part_id ="
                    + "'" + selectedElement +"'");
            try {
                while(cboselect.next()){
                    
                   //GET VALUE OF THE RESULTSET;
                    Replacements_txt_description.setText(cboselect.getString("description"));
                 }
            } catch (Exception e) {
            System.out.print("ERROR ON MODULE TRUCKPART REPLACEMENT setUpItemscbotruckPart " +e);
            System.exit(0);
            }
               
         });
     }
      
      //set up the functionality of the btn SAVE;
     public void setUpfunctionbtnreplacementSave(){
       
     
        //SET FUNCTION FOR THE BTN SAVE;
         main_module.getReplacements_btn_save().setOnMouseClicked((MouseEvent)->{
                 
        //INITIALUZED THE VRIABLES OF THE DATE;
        LocalDate now = LocalDate.now();
        int year = now.getYear();
        int month = now.getMonthValue();
        int day = now.getDayOfMonth();
        
        //CREATE DATABASE;
        DatabaseConnection dbselect = new DatabaseConnection("root", "localhost", "eyefleet", "ramjorgeyao");
           
              //check the combobox if its have a value if true execute the message! if false proceed to next statement
       if(Replacements_cbo_truck.getValue()== null){
                     main_module.getMessage().shortToast( "Please select an item on  truck:", 2000);
             }
        
        //check  the combobox if its have a value if true execute the message! if false proceed to next statement
        else if(Replacements_cbo_truck_part.getValue()== null){
                                 main_module.getMessage().shortToast( "Please select an item on truck_part:", 2000);
          }
        
        //check the combobox if its have a value if true execute the message! if false proceed to next statement
        else if(Replacements_txt_location.getText().isEmpty() || errorTrapping.isNumerical(Replacements_txt_location.getText())){
                                 main_module.getMessage().shortToast( " Please Provide Location:", 2000);
          }
        
        //check the combobox if its have a value if true execute the message! if false proceed to next statement
        else if(Replacements_txt_quantity.getText().isEmpty() || !errorTrapping.isNumerical(Replacements_txt_quantity.getText()) ){
                                 main_module.getMessage().shortToast( "Please review your quantity:", 2000);
         }
        
        
        
        
             
        else{
         
             
                 //INITILAIZED THE VARIABLE OF THE  OLDQUANTITY AND NEWQUANTITY AND SUBTRACT 
                 //TO GET THE REULT AND TO UPDATE THE TRUCKPARTS INVENTORY;
                 Integer oldquantity =0;
                 Integer newquantity =0;
                 Integer Result = 0;
                 
                 //GET THE SELECTED ITEM IN THE COOMOBOX TRUCK PART;
                 selectedElement = Replacements_cbo_truck_part.getSelectionModel().getSelectedItem().toString();
                 
                 //CREATE DATABASE AND RESULSET;
                 DatabaseConnection dbselector = new DatabaseConnection("root", "loacalhost", "eyefleet", "ramjorgeyao");
                 
                 //get first the quantity on hand w/ the selected id
                 ResultSet rs_selector = dbselector.setQuery("SELECT quantity_on_hand  FROM  eyefleet.truck_part "
                                                           + " WHERE truck_part_id ='" + selectedElement +"'");
                 try {
                     while(rs_selector.next()){
                         
                         //COMPUTE THE SUBTRACTION OF THE TWO OLDQUANTITY OF THE TRUCKPART AND NEW QUANTITY YOU ENTERED;
                         oldquantity = rs_selector.getInt("quantity_on_hand");
                         newquantity = Integer.parseInt(Replacements_txt_quantity.getText().toString());
                         Result =(oldquantity - newquantity);//get the result
                     }
                  } catch (Exception e) {
                      
                   }
                 
                  //update the quantity of the inventory truck parts
                 //second error traps
                 //check if the qunatity on hand of the truck prt is not negative;
                  if ((oldquantity - Integer.parseInt(main_module.getReplacements_txt_quantity().getText()))< 0){
                             main_module.getMessage().message("Not enough Inventory", "The quantity left for "
                        + "the truck parts " + selectedElement + " is only: " + oldquantity + ". Please change your quantity "
                        + "or perform an in-stock.", "Okay");
                            
                        }
                   else{//
                        
                      
                      try {
             
                //INSERT AND UPDATE THE DATABASE;
                 dbselect.updateDatabase("INSERT INTO eyefleet.replacement(replacement_date, status, location, truck_id, truck_part_id, quantity)" +
                                          "VALUES('"+year+"-"+month+"-"+day+"', " +                                   
                                          "'"+ "on replace"+"', " +
                                          "'"+  Replacements_txt_location.getText().toString()+"'," +
                                          "'" + Replacements_cbo_truck.getValue()+"', "+
                                          "'" + Replacements_cbo_truck_part.getValue()+"'," +
                                          "'" + Replacements_txt_quantity.getText().toString() +"')");
              } catch (Exception e) {
            System.out.print("ERROR ON MODULE TRUCKPART REPLACEMENT setUpfunctionbtnreplacementSave ;" +e);
            System.exit(0);
              }
            
            //ths function is to clear the data so that the data would be patong.x
            dataReplacements.clear();
            
            //update the data
            setUpItems();
            
                  try {
                     //CREATE DATABSE AND UPDATE THE DATABASE RECORDS OF THE truck_part;
                     DatabaseConnection dbupdate =  new DatabaseConnection("root", "localhost", "eyefleet", "ramjorgeyao");
                     dbupdate.updateDatabase("UPDATE eyefleet.truck_part SET quantity_on_hand =" + Result + " WHERE truck_part_id ='" + selectedElement +"'");//update the table 
                     
                     //update the truckparts MODULES USING THIS FUNCTION;;
                     main_module.getModule_truckpartInventory().setupdatatuckParts();
                 } catch (Exception e) {
                     System.out.print("ERROR ON MODULE TRUCKPART REPLACEMENT setUpfunctionbtnreplacementSave " + e);
                     System.exit(0);
                 }
                   
                   //UPDATE the STATUS 
                      main_module.getModule_truckStatus().Setcheckstatus(Replacements_cbo_truck.getValue().toString());
        }
                  
        }//end of if trap 2
     });
         
         
         
         
        
  }
     //SET THE FUNCTIONALITY OF THE SET AS REPAIRED;
     public void setUpfuntionasRepaired(){
         
        //SET FUNTION FOR THE BTN SET AS REPAIRED;
        Replacements_btn_set_as_repaired.setOnMouseClicked((MouseEvent)->{
        
        //GET THE CALUE OF THE SELECTED ITEM IN THE TABLE REPLACEMENT;
        DataReplacement dataRep = (DataReplacement)tbl_truckpartReplacement.getSelectionModel().getSelectedItem();
        
        //CHECK THE DATA IF HAS A VALUE
        if(dataRep != null){
        //CREATE DATABASE AND UPDATE THE RECOORD OF THE REPLACEMENT;
        DatabaseConnection db_repaired = new DatabaseConnection("root", "localhost", "eyefleet", "ramjorgeyao");
        db_repaired.updateDatabase("UPDATE eyefleet.replacement SET status ='"+ "replaced" +"'"+
                                                          " WHERE replacement_id ='"+ dataRep.getReplacement_id() +"'");
         
        //update the TRUCKStatus MODULE  using this function;
        main_module.getModule_truckStatus().Setcheckstatus(dataRep.getTruck_id());
        
        //CLEAR THE DATA BEFORE BEFORE TO REFRESH;
         dataReplacements.clear();
         
         //REFRESH THE DATA;
         setUpItems();
        }
        
        //
        else{
         main_module.getMessage().shortToast("Please Select a record in the Table:", 2000);
            
        }
        });
     }
     
     
     public void btnRefresh(){
         btn_refresh.setOnMouseClicked((MouseEvent) ->{
             Replacements_cbo_truck.setValue(null);
             Replacements_cbo_truck_part.setValue(null);
             Replacements_txt_description.clear();
             Replacements_txt_location.clear();
             Replacements_txt_quantity.clear();
         
         });
         
     }
     
     
     
    
    //set up all the data of the replacements 
    public class DataReplacement{
         private String replacement_id;
         private Date replacement_date;
         private String status;
         private String location;
         private String truck_id;
         private String truck_part_id;
         private String description;
         private String quantity;
        public DataReplacement(String replacement_id,
                               Date replacement_date, 
                               String status,
                               String location,
                               String truck_id,
                               String truck_part_id,
                               String description,
                               String quantity) {
            
            this.replacement_id = replacement_id;
            this.replacement_date = replacement_date;
            this.status = status;
            this.location = location;
            this.truck_id = truck_id;
            this.truck_part_id = truck_part_id;
            this.description = description;
            this.quantity = quantity;
        }

        
        public Date getReplacement_date() {
            return replacement_date;
        }

         
        public void setReplacement_date(Date replacement_date) {
            this.replacement_date = replacement_date;
        }

         
        public String getStatus() {
            return status;
        }

         
        public void setStatus(String status) {
            this.status = status;
        }
 
        public String getLocation() {
            return location;
        }

         
        public void setLocation(String location) {
            this.location = location;
        }
 
        public String getTruck_id() {
            return truck_id;
        }

         
        public void setTruck_id(String truck_id) {
            this.truck_id = truck_id;
        }

        
        public String getTruck_part_id() {
            return truck_part_id;
        }

        
        public void setTruck_part_id(String truck_part_id) {
            this.truck_part_id = truck_part_id;
        }

        public String getDescription() {
            return description;
        }
 
        public void setDescription(String description) {
            this.description = description;
        }
 
        public String getQuantity() {
            return quantity;
        }

         
        public void setQuantity(String quantity) {
            this.quantity = quantity;
        }

            public String getReplacement_id() {
            return replacement_id;
        }

        public void setReplacement_id(String replacement_id) {
            this.replacement_id = replacement_id;
        }
        
        
     }
 
    
}
