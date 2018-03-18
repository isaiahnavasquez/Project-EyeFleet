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
import java.awt.geom.Path2D;
import java.sql.ResultSet;
import java.time.LocalDate;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
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
public class TruckPartInventory {
    
    
    //FOR CONTROLLER;
    private LayoutController main_module;
    
    //FOR ERROR TRAPPING
    ErrorTraps errorTrapping = new ErrorTraps();
    //FOR TABLEVIEW;
    private TableView tbl_truckpartsInventory;
    
    //FOOR TABLE COLUMNS;
    private TableColumn truck_parts_col_item_id;
    private TableColumn truck_parts_col_description;
    private TableColumn truck_parts_col_qty_on_hand;
    private TableColumn truck_parts_col_price;
    
    //COMBOBO;
    private ComboBox Truck_parts_cbo_item_id;
    
    //FOR TEXTIELDS;
    private TextField Truck_parts_txt_description;
    private TextField Truck_parts_txt_stock_in;
    
    //for button save;
    private Label Truck_parts_btn_save;
    
    private Label truck_parts_btn_reresh;
    
    //FOR OBSERVALIST;
    private ObservableList<DatatruckpartsInventory>datatruckpartsInventorys;
    
    
    
    //FOR STRING SLELECTED;
    private String selectedElement ="";
    
    //for suplier observablelist;
    public ObservableList<DatainvoiceTruckpart> datainvoiceTruckparts;
    
    private ComboBox truck_parts_cbo_supplier;
    private TextField truck_parts_txt_amount;
    private TextField truck_parts_txt_invoice_number;
    
    
    //mc truckpart class
     Mc_TruckParts mc_TruckParts;
    
    
    public TruckPartInventory(LayoutController main_module_param) {
        //FOR CONTROLLER
        this.main_module = main_module_param;
        
        
        //mc truckpart class;
       this.mc_TruckParts = new Mc_TruckParts(main_module_param);
        
        //FOR TABLE VIEW TRUCKPARTS;
        this.tbl_truckpartsInventory = main_module_param.getTruck_parts_tbl_truck_parts();
        
        //FOR TABLE COLUMNS;
        this.truck_parts_col_item_id = main_module_param.getTruck_parts_tbl_truck_parts_col_item_id();
        this.truck_parts_col_description = main_module_param.getTruck_parts_tbl_truck_parts_col_description();
        this.truck_parts_col_qty_on_hand = main_module_param.getTruck_parts_tbl_truck_parts_col_qty_on_hand();
       
        //FOR COMBOBOX;
        this.Truck_parts_cbo_item_id = main_module_param.getTruck_parts_cbo_item_id();
        
        //FOR TEXTFIELDS;
        this.Truck_parts_txt_description = main_module_param.getTruck_parts_txt_description();
        this.Truck_parts_txt_stock_in = main_module_param.getTruck_parts_txt_stock_in();
        
        //FOR LABEL BUTTON BTN SAVE;
        this.Truck_parts_btn_save = main_module_param.getTruck_parts_btn_save();
        this.truck_parts_btn_reresh = main_module_param.getTruck_parts_btn_reresh();
        //fields for supplier invoice;
        this.truck_parts_cbo_supplier = main_module_param.getTruck_parts_cbo_supplier();
        this.truck_parts_txt_amount = main_module_param.getTruck_parts_txt_amount();
        this.truck_parts_txt_invoice_number = main_module_param.getTruck_parts_txt_invoice_number();
        this.truck_parts_col_price =  main_module_param.getTruck_parts_tbl_truck_parts_col_price();
       
        //INITIALIZED MODULES;
        initializeModule();
        
        //
               
                
      }
    
    public void initializeModule(){
         //INITIALIZED MODULES;
        setUptablecolumns();
        setupdatatuckParts();
        setUpitemstruckpartInventory();
        setUpbtnsavetruckPart();     
        checkAndAddSupplier("");
        
        btnRefresh();
        
        //cbo supplier;
        cboSupplierIInvoice();
        
        
       
    }
    
   
    
    
    
    
    
    
      public void setUptablecolumns(){
          //setup all tablecolumns
        datatruckpartsInventorys = FXCollections.observableArrayList();
        truck_parts_col_item_id.setCellValueFactory(new PropertyValueFactory("truck_part_id"));
        truck_parts_col_description.setCellValueFactory(new PropertyValueFactory("description"));
        truck_parts_col_qty_on_hand.setCellValueFactory(new PropertyValueFactory("quantity_on_hand"));
         tbl_truckpartsInventory.setItems(datatruckpartsInventorys);
    }
      
          //setup the data of the truckparts
     public void setupdatatuckParts(){
         
         //CLEAR THE DATA;
        datatruckpartsInventorys.clear();
        
        //CREATE DATABASE AND RESULSET;
        DatabaseConnection dbselect = new DatabaseConnection("root", "localhost", "eyefleet", "ramjorgeyao");
        ResultSet rs_select = dbselect.setQuery("SELECT * FROM eyefleet.truck_part;");
        try {
            
            //GET THE VALUE OF THE RESULTSET;
            while(rs_select.next()){
                
                //ADD NEW DATA FROM DatatruckpartsInventory;
                datatruckpartsInventorys.add(new DatatruckpartsInventory(rs_select.getString("truck_part_id"),
                                                                         rs_select.getString("description"),
                                                                         rs_select.getString("quantity_on_hand")));
            }
        } catch (Exception e) {
            System.out.print("ERROR ON MODULE TRUCKPART INVENTORY setupdatatuckParts; "+e);
            System.exit(0);
        }
     }
      
     
          //FNCTION FOR setUpitemstruckpartInventory;
     public void setUpitemstruckpartInventory(){
         
         //CREATE DATABASE ANDRESULSET;
        DatabaseConnection dbselectcon = new DatabaseConnection("root", "localhost", "eyefleet", "ramjorgeyao");
        ResultSet rs_select = dbselectcon.setQuery("SELECT * FROM eyefleet.truck_part");
           try {
               
               //GET THE VALUE OF THE RESULSET;
             while(rs_select.next()){
                 Truck_parts_cbo_item_id.getItems().add(rs_select.getString("truck_part_id"));
              }
         } catch (Exception e) {
             System.out.print("ERROR ON MODULE TRUCKPART INVENTORY setUpitemstruckpartInventory;" +e);
             System.exit(0);
          }
           
           //SET UP  THE FUNCTION OF THE TRUCKPART COMBOBOX;
         Truck_parts_cbo_item_id.setOnAction((ActionEvent)->{
           
          //GET THE SELECTED ITEM IN THE COMBOBOX;
          selectedElement = Truck_parts_cbo_item_id.getSelectionModel().getSelectedItem().toString();
          
          //GET THE RESULSET VALUE OF THE SELECTED ITEM;
          ResultSet rs_selection = dbselectcon.setQuery("SELECT * FROM eyefleet.truck_part where truck_part_id ='"+ selectedElement +"'");
              try {
                  while(rs_selection.next()){
                      Truck_parts_txt_description.setText(rs_selection.getString("description"));
                  }
              } catch (Exception e) {
             System.out.print("ERROR ON MODULE TRUCKPART INVENTORY setUpitemstruckpartInventory;" +e);
             System.exit(0);
              }
          });
      }
     
     
     //CBO SUPPLIER  get all the ites from supplier;
     public void cboSupplierIInvoice(){
    
   truck_parts_cbo_supplier.setOnMouseClicked((MouseEvent)->{
   
        //clear the cbo invoice
  truck_parts_cbo_supplier.getItems().clear();
         
             DatabaseConnection dbcbosuppplier = new DatabaseConnection("root","localhost", "eyefleet", "ramjorgeyao");
             ResultSet rscbosupplier = dbcbosuppplier.setQuery("SELECT name from supplier ");
             try {
                 while(rscbosupplier.next()){
                     truck_parts_cbo_supplier.getItems().add(rscbosupplier.getString("name"));
                     
                 }
             } catch (Exception e) {
                 System.out.print("Error on  module truckpartInventory cboSupplierIInvoice; " + e);
                 System.exit(0);
             }
   
   
   });
     
     }
     
     
     
     
     
     //SET UP FUNCTION FOR BTN SAVE TRUCKPART;
     public void setUpbtnsavetruckPart(){
          //SET UP FUNCTION FOR THE  BUTTON SAVE TRUCKPART;
         Truck_parts_btn_save.setOnMouseClicked((MouseEvent)->{
             
             //CHECK THE COMBOX TUCKPART IF IT HAS A VALUE;
             if(Truck_parts_cbo_item_id.getValue()== null){
                   main_module.getMessage().shortToast("Please select an item on  truck ", 2000);

              }
              
             else if(Truck_parts_txt_description.getText().isEmpty() || errorTrapping.isNumerical(Truck_parts_txt_description.getText())){
                  main_module.getMessage().shortToast("Please Provide a Description of the Item ", 2000);
  
              }
             //CHECK THE COMBOX TUCKPART IF IT HAS A VALUE AND GET THE ERROR TRAP CLASS;
             else if(Truck_parts_txt_stock_in.getText().isEmpty() || !errorTrapping.isNumerical(Truck_parts_txt_stock_in.getText()) ){
                    main_module.getMessage().shortToast("Pleasereview your quantity ", 2000);
 
              }
             
             else if (truck_parts_cbo_supplier.getValue()==null){
                 main_module.getMessage().shortToast("Pleasereview your supplier ", 2000);
                 
             }
             
             else if (truck_parts_txt_amount.getText().isEmpty() || !errorTrapping.isNumerical(truck_parts_txt_amount.getText())){
                        main_module.getMessage().shortToast("Please review your amount", 2000);

           }
             else if (truck_parts_txt_invoice_number.getText().isEmpty() || !errorTrapping.isNumerical(truck_parts_txt_invoice_number.getText())){
                           main_module.getMessage().shortToast("Please review your invoice number", 2000);
            }
             
            
             
             else{
             //----else
                   //INITIALUZED THE VRIABLES OF THE DATE;
                    LocalDate now = LocalDate.now();
                    int year = now.getYear();
                    int month = now.getMonthValue();
                    int day = now.getDayOfMonth();
                 
                 //add and check the user
                 checkAndAddSupplier(truck_parts_cbo_supplier.getValue().toString());
                 
                 
                 try {
                     
                       //insert into table truck part inventory invoice;
                 DatabaseConnection dbnvoice = new DatabaseConnection("root", "localhost", "eyefleet", "ramjorgeyao");
                 dbnvoice.updateDatabase("INSERT INTO eyefleet.truck_part_invoice(qty, amount, date, invoice_number, truck_part_id, supplier_id)" +
                                    "VALUES('" +Truck_parts_txt_stock_in.getText().toString() + "', "+
                                               "'" + truck_parts_txt_amount.getText().toString() + "', " +
                                               "'" + year+"-"+month+"-"+day + "', " +
                                               "'" + truck_parts_txt_invoice_number.getText().toString() + "', "+ 
                                               "'" + Truck_parts_cbo_item_id.getValue().toString() + "', " +
                                               "'" + truck_parts_cbo_supplier.getValue().toString()+"')" );
                  
                 
                //clear the cbo supplier invoice
               //  truck_parts_cbo_supplier.getItems().clear();
                 
                //refresh cbo supplier invoice; chek if the new supplier is being add in the cbo supplier;
                 cboSupplierIInvoice();
                 
                 //refresh mctruckparts
                
                 
                 
                 } catch (Exception e) {
                     System.out.print("Error on truckpartinventory inserting supplier imvoice; " + e);
                     System.exit(0);
                 }
 
                 
                
                 
             
             //INITIALIZED THE VARIABLES OF THE DATE;
              Integer quantity_on_hand = 0;
              Integer stock_in = 0;
              Integer Result = 0;
              
              //GET THE SELECTED ITEM OF THE TRUCKPARTS COMBOBOX;
              selectedElement = Truck_parts_cbo_item_id.getSelectionModel().getSelectedItem().toString();
              
              //CREATE DATABASE AND RESULSET;
              DatabaseConnection db_con = new DatabaseConnection("root", "localhost", "eyefleet", "ramjorgeyao");
              ResultSet rs_con  = db_con.setQuery("SELECT quantity_on_hand FROM eyefleet.truck_part WHERE truck_part_id = '" + selectedElement +"'");
              try {
                  
                  //GET HE VALUE OF THE RESULSET;
                  while(rs_con.next()){
                    
                      //GET THE QUANTITYONHAND AND STOCKIN THEN SUBTRACT TO GET THE RESULT;
                    quantity_on_hand = rs_con.getInt("quantity_on_hand");
                    stock_in =Integer.parseInt(Truck_parts_txt_stock_in.getText());
                    Result = (quantity_on_hand + stock_in);

                   }
                           } catch (Exception e){
                              System.out.print("ERROR ON MODULE TRUCKPART INVENTORY setUpbtnsavetruckPart: "+e);
                              System.exit(0);
              }
            
               
              
              
              
              
              
              
              
                  //CREATE DATABASE AND UPDATE THE RECORDS ;
                  DatabaseConnection dbcon2 = new DatabaseConnection("root", "localhost", "eyefleet", "ramjorgeyao");
                  
                    //UPDATE THE RECORDS OF THE DATATABSE;
                     dbcon2.updateDatabase("UPDATE eyefleet.truck_part SET quantity_on_hand = " + Result + ","
                                                                                  + " price ='"+ truck_parts_txt_amount.getText() +"'"
                                                                                  + " WHERE truck_part_id ='" + selectedElement+ "'");
                    
                     //CLEAR THE DATA;
                     datatruckpartsInventorys.clear();
                     
                     //REFRESH THE TABLE TRUCKPARTS;
                     setupdatatuckParts();
             }//END OF ELSE STATEMENT;
                     
          
               });
       }
     
     
     
     public void checkAndAddSupplier(String name){
        boolean exists = false;
        DatabaseConnection db_customer = new DatabaseConnection("root", "localhost", "eyefleet", "isaiah");
        ResultSet rs_supplier = db_customer.setQuery("select * from supplier");
        
        try {
            while(rs_supplier.next()){
                if(rs_supplier.getString("supplier_id").equals(name)){
                    exists = true;
                    break;
                }
            }
            
            if(exists == false){
                DatabaseConnection db_customer_add = new DatabaseConnection("root", "localhost", "eyefleet", "isaiah");
                db_customer_add.updateDatabase("insert into supplier (supplier_id, name) values('" + name + "', '" + name + "')");
                cboSupplierIInvoice();//update data;
            }
        } catch (Exception e) {
            System.out.println("ERROR ON ORDER STACK checkAndAddCustomer: " + e);
        }
    }
      
     
     //refrresh table truckparts;
     public void btnRefresh(){
         truck_parts_btn_reresh.setOnMouseClicked((MouseEvent)->{
                      setupdatatuckParts();
                      
                      
                      Truck_parts_cbo_item_id.setValue(null);
                      Truck_parts_txt_description.clear();
                      Truck_parts_txt_stock_in.clear();
                      
                      truck_parts_cbo_supplier.setValue(null);
                      truck_parts_txt_amount.clear();
                      truck_parts_txt_invoice_number.clear();
                      
          });
     }
 
     
     
     
     public void cliktblTruckpartinventory(){
         
     }
     
     
     
     
     
     
     
     
     
     
     
     //set data for truckpartsInventory;
     public class DatatruckpartsInventory{
        private String truck_part_id;
        private String description;
        private String quantity_on_hand;
         public DatatruckpartsInventory(String truck_part_id,
                                       String description,
                                       String  quantity_on_hand
                                     ) {
             this.truck_part_id = truck_part_id;
             this.description = description;
             this.quantity_on_hand = quantity_on_hand;
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

       
        public String getQuantity_on_hand() {
            return quantity_on_hand;
        }
 
        public void setQuantity_on_hand(String quantity_on_hand) {
            this.quantity_on_hand = quantity_on_hand;
        }

         
     
        
    }
     
     
     
     
     public class DatainvoiceTruckpart{
         private String supplier_id;
         private String amount;
         private String invoice_number;

        public DatainvoiceTruckpart(String supplier_id,
                String amount,
                String invoice_number) {
            
            this.supplier_id = supplier_id;
            this.amount = amount;
            this.invoice_number = invoice_number;
            
        }

        public String getSupplier_id() {
            return supplier_id;
        }

        public void setSupplier_id(String supplier_id) {
            this.supplier_id = supplier_id;
        }

        public String getAmount() {
            return amount;
        }

        public void setAmount(String amount) {
            this.amount = amount;
        }

        public String getInvoice_number() {
            return invoice_number;
        }

        public void setInvoice_number(String invoice_number) {
            this.invoice_number = invoice_number;
        }

         
        
         
         
         
     }
}
