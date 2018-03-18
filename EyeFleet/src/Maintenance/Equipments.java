/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Maintenance;

import eyefleet.DatabaseConnection;
import eyefleet.ErrorTraps;
import eyefleet.LayoutController;
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
 * @author IsaiahJan
 */
public class Equipments {
    
    //error traps
    ErrorTraps traps = new ErrorTraps();
    
    //controller
    LayoutController controller;
   
    //tables
    TableView tbl_equipment;
    
    //fields
    ComboBox cbo_equipment;
    TextField txt_description;
    TextField txt_stock_in;
    
    //Buttons
    Label btn_save;
    Label btn_issue;
    Label  btn_refreshh;
    
    //columns
    TableColumn col_equipment_id;
    TableColumn col_description;
    TableColumn col_qty_on_hand;
    
    //Data
    ObservableList<DataEquipment> data_equipment = FXCollections.observableArrayList();
   
    //supplier fields
    private ComboBox equipment_cbo_supplier;
    private TextField equipment_txt_amount;
    private TextField equipment_txt_invoice_number;
    ObservableList<DataSuppplierequiment>dataSuppplierequiments;
    
    public Equipments(LayoutController controller) {
        
        //load controller
        this.controller = controller;
        
        //load tables
        this.tbl_equipment = controller.getEquipments_tbl_equipments();
        
        //load columns
        this.col_description = controller.getEquipments_tbl_equipments_col_description();
        this.col_equipment_id = controller.getEquipments_tbl_equipments_col_equipment_id();
        this.col_qty_on_hand = controller.getEquipments_tbl_equipments_col_qty_on_hand();
        
        //load buttons
        this.btn_issue = controller.getEquipment_btn_issue();
        this.btn_save = controller.getEquipment_btn_save();
        this.btn_refreshh = controller.getEquipment_btn_refreshh();
        
        //load fields
        this.txt_description = controller.getEquipment_txt_description();
        this.txt_stock_in = controller.getEquipment_txt_stock_in();
        this.cbo_equipment = controller.getEquipment_cbo_equipment_id();
        
        //supplier fileds;
        this.equipment_cbo_supplier = controller.getEquipment_cbo_supplier();
        this.equipment_txt_amount = controller.getEquipment_txt_amount();
        this.equipment_txt_invoice_number = controller.getEquipment_txt_invooice_number();
        
        
        //initialize module
        initializeModule();
    }
    
    public void initializeModule(){
        //set the functionalities and prepare nodes
        setTableColumns();
        setTableData();
        setButtonSaveFunction();
        setItemListForEquipment();
        setItemListSelected();
        setButtonIssueFunction();
        btnRefresh();
        //supplier fields
        checkAndAddSupplier("");
        cboSupplierEquipments();
        
    }
    
    public void setTableColumns(){
        //set the table columns and data
        col_description.setCellValueFactory(new PropertyValueFactory("description"));
        col_equipment_id.setCellValueFactory(new PropertyValueFactory("equipment_id"));
        col_qty_on_hand.setCellValueFactory(new PropertyValueFactory("qty_on_hand"));
        tbl_equipment.setItems(data_equipment);
    }
    
    public void setTableData(){
        //clears the data of the table
        data_equipment.clear();
        
        //prepare the database connection for equipments
        DatabaseConnection database_equipment = new DatabaseConnection("root", "localhost", "eyefleet", "isaiah");
        ResultSet rs_equipment = database_equipment.setQuery("select * from equipment");
        
        try {
            
            //get the data of the resultset
            while(rs_equipment.next()){
                
                //add the record to the table or obervable list
                data_equipment.add(new DataEquipment(rs_equipment.getString("equipment_id"),
                        rs_equipment.getString("description"),
                        rs_equipment.getString("qty_on_hand")));
            }
        } catch (Exception e) {
            System.out.print("ERROR ON GETTING DATA OF EQUIPMENT:" + e);
            System.exit(0);
        }
    }
    
    public void setButtonIssueFunction(){
        //
        btn_issue.setOnMouseClicked((MouseEvent) -> {
            //
            controller.getBookMaintenance().openIssuance();
        });
    }
    
    public void setItemListForEquipment(){
        //
        cbo_equipment.getItems().clear();
        
        DatabaseConnection database_equipment = new DatabaseConnection("root", "localhost", "eyefleet", "isaiah");
        ResultSet rs_equipment = database_equipment.setQuery("select equipment_id from equipment");
        
        try {
            //
            while(rs_equipment.next()){
                //
                cbo_equipment.getItems().add(rs_equipment.getString("equipment_id"));
            }
        } catch (Exception e) {
            System.out.print("ERROR ON MODULE EQUIPMENTS SETITEMLISTFOREQUIPMENTS: " + e);
            System.exit(0);
        }
    }
    
    public void setItemListSelected(){
        cbo_equipment.setOnAction((ActionEvent) -> {
            DatabaseConnection database_equipment = new DatabaseConnection("root", "localhost", "eyefleet", "isaiah");
            ResultSet rs_equipment = database_equipment.setQuery("select description from equipment where equipment_id = '" + cbo_equipment.getValue().toString() + "'");
        
            try {
                while(rs_equipment.next()){
                    txt_description.setText(rs_equipment.getString("description"));
                }
            } catch (Exception e) {
                System.out.print("ERROR ON GETTING DATA OF EQUIPMENT:" + e);
                System.exit(0);
                }
            
         });
    }
    
    
    //function for cboSupplierEquipments;
    public void cboSupplierEquipments(){
        
        //clear cbo seqipent invoice;
        equipment_cbo_supplier.getItems().clear();
        
          DatabaseConnection dbcbosuppplier = new DatabaseConnection("root","localhost", "eyefleet", "ramjorgeyao");
             ResultSet rscbosupplier = dbcbosuppplier.setQuery("SELECT name from supplier ");
             try {
                 while(rscbosupplier.next()){
                     equipment_cbo_supplier.getItems().add(rscbosupplier.getString("name"));
                     
                 }
             } catch (Exception e) {
                 System.out.print("Error on  module equipment cboSupplierEquipments; " + e);
                 System.exit(0);
             }
        
    }
    
    
    
    
    
    public void setButtonSaveFunction(){
        btn_save.setOnMouseClicked((MouseEvent) -> {
            
            //----else
                   //INITIALUZED THE VRIABLES OF THE DATE;
                    LocalDate now = LocalDate.now();
                    int year = now.getYear();
                    int month = now.getMonthValue();
                    int day = now.getDayOfMonth();
                 
            
            
            
            if (cbo_equipment.getValue() == null){
             controller.getMessage().shortToast("Please select an equipment.", 2000);
            }
            else if (txt_stock_in.getText().isEmpty() || !traps.isNumerical(txt_stock_in.getText())) {
              controller.getMessage().shortToast("Please review your in-stock quantity.", 2000);
              }
            
            else if (equipment_cbo_supplier.getValue()==null){
              controller.getMessage().shortToast("Please review your supplier", 2000);
              }
            else if (equipment_txt_amount.getText().isEmpty() || !traps.isNumerical(equipment_txt_amount.getText())){
               controller.getMessage().shortToast("Please review your amount", 2000);
             }
            else if (equipment_txt_invoice_number.getText().isEmpty() ||!traps.isNumerical(equipment_txt_invoice_number.getText()) ){
                           controller.getMessage().shortToast("Please review your invoice number", 2000);

        }
            
            
            
            
            else {
                
                ////add and check the user
                checkAndAddSupplier(equipment_cbo_supplier.getValue().toString());
                //insert records in equipment ivoice;
                try {
                    DatabaseConnection dbsupplier = new DatabaseConnection("root", "localhost", "eyefleet", "ramjorgeyao");
                    dbsupplier.updateDatabase("INSERT INTO eyefleet.equipment_invoice(qty, amount, date, invoice_number ,equipment_id, supplier_id)" +
                                               "VALUES('" + txt_stock_in.getText().toString() + "', " +
                                                       "'" + equipment_txt_amount.getText().toString()+ "', " +
                                                       "'" + year+"-"+month+"-"+day  +"', " +
                                                       "'" + equipment_txt_invoice_number.getText().toString() +"', "+
                                                       "'" + cbo_equipment.getValue().toString() +"', " +
                                                           "'" + equipment_cbo_supplier.getValue().toString() +"')" );

                    //refresh cboequipmntssuppier;
                    cboSupplierEquipments();
                    
                } catch (Exception e) {
                    System.out.print("Error on  saving inserting in cbo supplier setButtonSaveFunction; "  +e);
                    
                }
         
            //updte equipment records
            DatabaseConnection database_equipment = new DatabaseConnection("root", "localhost", "eyefleet", "isaiah");
            database_equipment.updateDatabase("update equipment "
                    + "set qty_on_hand = "
                    + "qty_on_hand + " + txt_stock_in.getText() + " "
                    + "where equipment_id = '" + cbo_equipment.getValue().toString() + "'");
            
            //refresh table;
                      setTableData();
            }
        });
    }
    
    
    
    //checks the supplier if exist;
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
              
                cboSupplierEquipments();
            }
        } catch (Exception e) {
            System.out.println("ERROR ON  checkAndAddSupplier: " + e);
        }
    }
    
    
    
    public void btnRefresh(){
        btn_refreshh.setOnMouseClicked((MouseEvent)->{
            
            //equipment fields
             txt_description.clear();
            txt_stock_in.clear();
            
        //equipment supplier fileds
             equipment_txt_amount.clear();
            equipment_txt_invoice_number.clear();
            
        //refresh table
            setTableData();
            
            //
         
            
            
            
        });
        
    }
    
    public class DataEquipment {

        private String equipment_id;
        private String description;
        private String qty_on_hand;
        
        public DataEquipment(
            String equipment_id,
            String description,
            String qty_on_hand) {
            
            this.description = description;
            this.equipment_id = equipment_id;
            this.qty_on_hand = qty_on_hand;
            
        }

        public String getEquipment_id() {
            return equipment_id;
        }

        public void setEquipment_id(String equipment_id) {
            this.equipment_id = equipment_id;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public String getQty_on_hand() {
            return qty_on_hand;
        }

        public void setQty_on_hand(String qty_on_hand) {
            this.qty_on_hand = qty_on_hand;
        }
     
    }
    
    public class DataSuppplierequiment{
         private String  supplier_id;
         private String  amount;
         private String  invoice_number;

        public DataSuppplierequiment(String equipment_supplier_id,
               String equipment_amount,
               String equipment_invoice_number) {
            this. supplier_id =  supplier_id;
            this. amount =  amount;
            this. invoice_number = invoice_number;
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
