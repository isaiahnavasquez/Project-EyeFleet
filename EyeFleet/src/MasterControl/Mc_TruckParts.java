/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package MasterControl;

import Maintenance.Issuance;
import Maintenance.TruckPartInventory;
import eyefleet.DatabaseConnection;
import eyefleet.ErrorTraps;
import eyefleet.LayoutController;
import java.sql.Date;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.time.Month;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

/**
 *
 * @author Jorge
 */
public class Mc_TruckParts {

     
    private LayoutController controller;
    
    private TableView mc_truck_parts_tableview;
    private TableColumn mc_truck_parts_tblc_item_id;
    private TableColumn mc_truck_parts_tblc_description;
    private TableColumn mc_truck_parts_tblc_qty_on_hand;
    private TableColumn mc_truck_parts_tblc_price;
    
    private ComboBox mc_truck_parts_cbo_truck;
    private TextField mc_truck_parts_text_description;
    private TextField mc_truck_parts_text_quantity;
    private TextField mc_truck_parts_text_price;
    
    
    private Label mc_truck_parts_btn_save;
    private Label mc_truck_parts_btn_edit;
    private Label mc_truck_parts_btn_delete;
    
    private ObservableList<DatamcTruckParts> datamcTruckPartses;
      
     //FOR ERROR TRAPPING
    ErrorTraps errorTrapping = new ErrorTraps();
    
    //truck part invoice data
    private TableView  tableview_supplier;
    private TableColumn  tblc_supplier ;
    private TableColumn  tblc_supplier_amount;
    private TableColumn  tblc_upplier_invoicenumber;
    private TableColumn  tblc_upplier_date;
    private TableColumn  tblc_supplier_truckpart_id;
    private TableColumn  tblc_supplier_quantity;
    
    private ComboBox  cbo_supplier;
    private TextField  text_supplier_amount;
    private TextField  text_supplier_voicenumber;
    private TextField   text_supplier_quantity;
    private DatePicker mc_truck_parts_supplier_date; 
    
    private Label btn_supplier_update;
    private Label btn_supplier_add;
    
    private LocalDate date;
    
    private ComboBox filterbymonth_cbo_truck;
    private Label  btn_filter_by_month;
    private DatePicker  from_datepicker;
    private DatePicker  to_datepicker;
    private Label  btn_filter_by_range;
    private Label  btn_no_filter;
    
    //truckpartinvoice part;
    private ObservableList<DataSupplier>dataSuppliers;
   

    
    
        
    public Mc_TruckParts(LayoutController controller_param) {      
        this.controller = controller_param;
        
         
        this.mc_truck_parts_tableview = controller_param.getMc_truck_parts_tableview();
        this.mc_truck_parts_tblc_item_id = controller_param.getMc_truck_parts_tblc_item_id();
        this.mc_truck_parts_tblc_description = controller_param.getMc_truck_parts_tblc_description();
        this.mc_truck_parts_tblc_qty_on_hand = controller_param.getMc_truck_parts_tblc_qty_on_hand();
        this.mc_truck_parts_tblc_price = controller_param.getMc_truck_parts_tblc_price();
        
        this.mc_truck_parts_cbo_truck = controller_param.getMc_truck_parts_cbo_truck();
        this.mc_truck_parts_text_description = controller_param.getMc_truck_parts_text_description();
        this.mc_truck_parts_text_quantity = controller_param.getMc_truck_parts_text_quantity();
        this.mc_truck_parts_text_price = controller_param.getMc_truck_parts_text_price();
        
        this.mc_truck_parts_btn_save = controller_param.getMc_truck_parts_btn_save();
        this.mc_truck_parts_btn_edit = controller_param.getMc_truck_parts_btn_edit();
        this.mc_truck_parts_btn_delete = controller_param.getMc_truck_parts_btn_delete();
        
        //-------------------supplier truckpart invoice;
        //tables and colymns;
        this.tableview_supplier = controller_param.getMc_truck_parts_tableview_supplier();
        this.tblc_supplier = controller_param.getMc_truck_parts_tblc_supplier();
        this.tblc_supplier_amount = controller_param.getMc_truck_parts_tblc_supplier_amount();
        this.tblc_upplier_invoicenumber = controller_param.getMc_truck_parts_tblc_supplier_invoicenumber();
        this.tblc_upplier_date = controller_param.getMc_truck_parts_tblc_supplier_date();
        this.tblc_supplier_truckpart_id = controller_param.getMc_truck_parts_tblc_supplier_truckpart_id();
        this.tblc_supplier_quantity = controller_param.getMc_truck_parts_tblc_supplier_quantity();
        
        //fileds oftruckpartinvoice
        
        this.cbo_supplier = controller_param.getMc_truck_parts_cbo_supplier();
        this.text_supplier_amount = controller_param.getMc_truck_parts_text_supplier_amount();
        this.text_supplier_voicenumber = controller_param.getMc_truck_parts_text_supplier_voicenumber();
        this.text_supplier_quantity = controller_param.getMc_truck_parts_text_supplier_quantity();
        
        this.mc_truck_parts_supplier_date = controller_param.getMc_truck_parts_supplier_date();
        
        this.btn_supplier_add = controller_param.getMc_truck_parts_btn_supplier_add();
        this.btn_supplier_update = controller_param.getMc_truck_parts_btn_supplier_update();
        
        this.filterbymonth_cbo_truck = controller_param.getMc_truckpart_filterbymonth_cbo_truck();
        this.btn_filter_by_month = controller_param.getMc_truck_parts_btn_filter_by_month();
        this.from_datepicker = controller_param.getMc_truckpart_from_datepicker();
        this.to_datepicker = controller_param.getMc_truckpart_to_datepicker();
        this.btn_filter_by_range = controller_param.getMc_truck_parts_btn_filter_by_range();
        this.btn_no_filter = controller_param.getMc_truck_parts_btn_no_filter();
        
        //initializd modules;
        initiaizedModules();
        
        //initailaized truckpartinvoice;
        initializedTruckpartinvoice();
    }
    
    public void initiaizedModules(){
        setTablecolumns();
        setdataTruckParts();
        cboItems();
        cbitemsdescription();
        btnSave();
        btnEdit();
        clickTable();
        
        btnRefresh();
        
        
        //--------
        
         
    }
    
     
    
    
    public void setTablecolumns(){
        //set the observalleleist
        datamcTruckPartses = FXCollections.observableArrayList();
        mc_truck_parts_tblc_item_id.setCellValueFactory(new PropertyValueFactory("truck_part_id"));
        mc_truck_parts_tblc_description.setCellValueFactory(new PropertyValueFactory("description"));
        mc_truck_parts_tblc_qty_on_hand.setCellValueFactory(new PropertyValueFactory("quantity_on_hand"));
        mc_truck_parts_tblc_price.setCellValueFactory(new PropertyValueFactory("price"));
        mc_truck_parts_tableview.setItems(datamcTruckPartses);
        
    }
    
    public void setdataTruckParts(){
        //set all the data
        DatabaseConnection dbtruckparts = new DatabaseConnection("root", "localhost", "eyefleet", "ramjorgeyao");
        ResultSet rstruckparts = dbtruckparts.setQuery("SELECT * FROM eyefleet.truck_part");
        try {
            while(rstruckparts.next()){
                datamcTruckPartses.add(new DatamcTruckParts(rstruckparts.getString("truck_part_id"),
                                                            rstruckparts.getString("quantity_on_hand"),
                                                            rstruckparts.getString("description"),
                                                            rstruckparts.getString("price")));
             }
        } catch (Exception e) {
            System.out.print("ERROR ON MC TRUCKPART  setdataTruckParts; "+e);
            System.exit(0);
        }
        
    }
    
    
    
    public void cboItems(){
            try {
               
                //get the items og the cbo truckpart
             DatabaseConnection dbcoitems = new DatabaseConnection("root", "localhost", "eyefleet", "ramjorgeyao");
             ResultSet rscboitems = dbcoitems.setQuery("SELECT * FROM eyefleet.truck_part");
             
                 while(rscboitems.next()){
                      mc_truck_parts_cbo_truck.getItems().add(rscboitems.getString("truck_part_id"));
                 }
        } catch (Exception e) {
            System.out.print("ERROR ON MC TRUCKPART  cboItems;  "+e);
            System.exit(0);
        }
             
           
    }
    
    public void cbitemsdescription(){
         //SET UP  THE FUNCTION OF THE TRUCKPART COMBOBOX;
                mc_truck_parts_cbo_truck.setOnAction((ActionEvent)->{
                            try {
                                DatabaseConnection dbdescription = new DatabaseConnection("root", "localhost", "eyefleet", "ramjorgeyao");
                                
                                String selectedElement="";
                       selectedElement = mc_truck_parts_cbo_truck.getValue().toString();
                       //GET THE RESULSET VALUE OF THE SELECTED ITEM;
                        ResultSet rs_selection = dbdescription.setQuery("SELECT * FROM eyefleet.truck_part where truck_part_id ='"+ selectedElement +"'");
                       
                                while(rs_selection.next()){
                                    mc_truck_parts_text_description.setText(rs_selection.getString("description"));
                                
                            }
                    } catch (Exception e) {
                         System.out.print("ERROR ON MC TRUCKPART  cboItems; "+e);
                         System.exit(0);
                    }
                       });
    }
    
    
    public void btnSave(){
        //SET UP FUNCTION FOR BTN SAVE TRUCKPART
          //error trappings:
        mc_truck_parts_btn_save.setOnMouseClicked((MouseEvent)->{
            
            
              //check if the truck id is exist;        
             boolean exist = false;
             DatabaseConnection dbcbo = new DatabaseConnection("root", "localhost", "eyefleet", "ramjorgeyao");
             ResultSet rscbo = dbcbo.setQuery("SELECT * FROM eyefleet.truck_part ");
             try {
                while(rscbo.next()){
                 if( mc_truck_parts_cbo_truck.getValue().equals(rscbo.getString("truck_part_id")))  {
                    exist = true;
                     break;
            }
                 }
                
                 } catch (Exception e) {
             }
             
             
              if(exist ==true){
                              controller.getMessage().shortToast( "Item Id  already Exist::", 2000);

            }//end of error trps;
            
            
            else  if(mc_truck_parts_cbo_truck.getValue()== null){
                                              controller.getMessage().shortToast( "Please provide Item", 2000);

              }
             else if(mc_truck_parts_text_description.getText().isEmpty() || errorTrapping.isNumerical(mc_truck_parts_text_description.getText())){
                                             controller.getMessage().shortToast( "IPlease provide Description", 2000);

                 
            }
             else  if(mc_truck_parts_text_quantity.getText().isEmpty() || !errorTrapping.isNumerical(mc_truck_parts_text_quantity.getText())){
                                             controller.getMessage().shortToast( "IPlease provide quanity:", 2000);

                 
            }
              
            
             else{
            //INITIALIZED THE VARIABLES OF THE DATE;
              Integer quantity_on_hand = 0;
              Integer stock_in = 0;
              Integer Result = 0;
               
               //GET THE SELECTED ITEM OF THE TRUCKPARTS COMBOBOX;
           String selectedElement = mc_truck_parts_cbo_truck.getSelectionModel().getSelectedItem().toString();
           //CREATE DATABASE AND RESULSET;
              DatabaseConnection db_con = new DatabaseConnection("root", "localhost", "eyefleet", "ramjorgeyao");
              ResultSet rs_con  = db_con.setQuery("SELECT quantity_on_hand FROM eyefleet.truck_part WHERE truck_part_id = '" + selectedElement +"'");
              try {
                  
                  //GET HE VALUE OF THE RESULSET;
                  while(rs_con.next()){
                    
                      //GET THE QUANTITYONHAND AND STOCKIN THEN SUBTRACT TO GET THE RESULT;
                    quantity_on_hand = rs_con.getInt("quantity_on_hand");
                    stock_in =Integer.parseInt(mc_truck_parts_text_quantity.getText());
                    Result = (quantity_on_hand + stock_in);

                   }
                           } catch (Exception e){
                              System.out.print("ERROR ON MODULE TRUCKPART INVENTORY setUpbtnsavetruckPart: "+e);
                              System.exit(0);
              }
              
              
              //ADDING NEW RECORDS OR NEW TRUCK PARTS  PRICE INTO TABLE TRUCKPARTS
                 try {
                    
                     
                         
                     DatabaseConnection dbinsert = new DatabaseConnection("root", "localhost", "eyefleet", "ramjorgeyao");
                      dbinsert.updateDatabase("INSERT INTO eyefleet.truck_part(truck_part_id, quantity_on_hand ,description )"
                                             +"VALUES('" +mc_truck_parts_cbo_truck.getValue().toString() + "', "
                                                +"'" + mc_truck_parts_text_quantity.getText().toString() +"', " 
                                                + "'" + mc_truck_parts_text_description.getText().toString() + "')");
                         
                      //CLEAR THE DATA;
                     datamcTruckPartses.clear();
                     
                     
                     //REFRESH THE TABLE TRUCKPARTS;
                     setdataTruckParts();  
                     //refersh cbo truck id;
                     cboItems();
                      
                 } catch (Exception e) {
                     System.out.print("EROR ON ADDING PRICE " + e);
                     System.exit(0);
                 }
              
              
        
               //CREATE DATABASE AND UPDATE THE RECORDS ;
                  DatabaseConnection dbcon2 = new DatabaseConnection("root", "localhost", "eyefleet", "ramjorgeyao");
                     
                    //UPDATE THE RECORDS OF THE DATATABSE;
                     dbcon2.updateDatabase("UPDATE eyefleet.truck_part SET quantity_on_hand = " + Result + " WHERE truck_part_id ='" + selectedElement+ "'");
                   
                     //CLEAR THE DATA;
                     datamcTruckPartses.clear();
                     
                     //REFRESH THE TABLE TRUCKPARTS;
                     setdataTruckParts();
        
             }//end of if
        });
        
           
    }
    
    
    public void btnEdit(){
       // error traps
            mc_truck_parts_btn_edit.setOnMouseClicked((MouseEvent)->{
                 if(mc_truck_parts_cbo_truck.getValue()== null){
                                     controller.getMessage().shortToast( "Please provide Item", 2000);
                   }
                  else if(mc_truck_parts_text_description.getText().isEmpty() || errorTrapping.isNumerical(mc_truck_parts_text_description.getText())){
                                                     controller.getMessage().shortToast( "Please provide Description", 2000);
                    }
                  else if(mc_truck_parts_text_quantity.getText().isEmpty() || !errorTrapping.isNumerical(mc_truck_parts_text_quantity.getText())){
                                                           controller.getMessage().shortToast( "Please provide  Correct quantity", 2000);
               }
                    
                    
                    else{//update records using this function;

              try{
                  
                 String selectedElement = mc_truck_parts_cbo_truck.getSelectionModel().getSelectedItem().toString();
                 DatabaseConnection dbupdate =new DatabaseConnection("root", "localhost", "eyefleet", "password");
                 dbupdate.updateDatabase("UPDATE eyefleet.truck_part SET truck_part_id ='" + mc_truck_parts_cbo_truck.getValue().toString()+"',"
                                                                            + "description ='" + mc_truck_parts_text_description.getText().toString() + "',"
                                                                            + "quantity_on_hand ='" + mc_truck_parts_text_quantity.getText().toString()+"' "
                                                                             + " WHERE truck_part_id ='" + selectedElement + "'");
                
            
                //CLEAR THE DATA;
                     datamcTruckPartses.clear();
                     
                     //REFRESH THE TABLE TRUCKPARTS;
                     setdataTruckParts();
                } catch (Exception e) {
                    System.out.print("Error on Update @ MC Truck parts btnEdit: " + e);
                 }
                    }//end of if;
            });
        
    }
    
    
    
    public void btnRefresh(){
        //refresn table truckparts
        mc_truck_parts_btn_delete.setOnMouseClicked((MouseEvent)->{
             try {
                 //truckpart parts;
                 datamcTruckPartses.clear();
                 
                 //clear fields mc truckpart;
                 //refresh cbo items
                  mc_truck_parts_text_description.clear();
                 mc_truck_parts_text_price.clear();
                 mc_truck_parts_text_quantity.clear();
                
                 //refresh table
                 setdataTruckParts();
                 
                 //truckpart invoice part
                 dataSuppliers.clear();
                 //trucpartenvoice fileds
                 text_supplier_amount.clear();
                 text_supplier_quantity.clear();
                 text_supplier_voicenumber.clear();
                 mc_truck_parts_supplier_date.setValue(null);
                 
                  setItemsTruckpartInvoice();
                  
                 
            } catch (Exception e) {
            }
        
        });
    }
    
    
    public void clickTable(){
        mc_truck_parts_tableview.setOnMouseClicked((MouseEvent)->{
            try {
                 DatamcTruckParts datamcTruckPartsSelect;
                 datamcTruckPartsSelect = (DatamcTruckParts)mc_truck_parts_tableview.getSelectionModel().getSelectedItem();
                 if(datamcTruckPartsSelect != null){
                     mc_truck_parts_cbo_truck.setValue(datamcTruckPartsSelect.getTruck_part_id());
                     mc_truck_parts_text_description.setText(datamcTruckPartsSelect.getDescription());
                     mc_truck_parts_text_quantity.setText(datamcTruckPartsSelect.getQuantity_on_hand());
                     mc_truck_parts_text_price.setText(datamcTruckPartsSelect.getPrice());
            }
            } catch (Exception e) {
            }
        
        
        });
    }
    
    
    
    
    //-------------------------------------------------trucpart invoice;
    
    public void initializedTruckpartinvoice(){
        setTablecolumnsTruckpartinvoice();
       // setItemsTruckpartInvoice();
        cbosupplier();
        supplierDate();
        clicktableSupplier();
        btnUpdate();
        
        cbofilterbyMonth();
        btnfilterbyMonth();
        btnFilterbyRangeFilter();
        btnNofilter();
    }
    
    
    public void setTablecolumnsTruckpartinvoice(){
        dataSuppliers = FXCollections.observableArrayList();
        tblc_supplier.setCellValueFactory(new PropertyValueFactory("supplier_id"));
        tblc_supplier_amount.setCellValueFactory(new PropertyValueFactory("amount"));
        tblc_supplier_quantity.setCellValueFactory(new PropertyValueFactory("qty"));
        tblc_supplier_truckpart_id.setCellValueFactory(new PropertyValueFactory("truck_part_id"));
        tblc_upplier_date.setCellValueFactory(new PropertyValueFactory("date"));
        tblc_upplier_invoicenumber.setCellValueFactory(new PropertyValueFactory("invoice_number"));
        tableview_supplier.setItems(dataSuppliers);
        
    }
    
    public void setItemsTruckpartInvoice(){
        DatabaseConnection dbinvoice = new DatabaseConnection("root", "localhost", "eyefleet", "ramjorgeyao");
        ResultSet rsinvoice = dbinvoice.setQuery("SELECT * FROM eyefleet.truck_part_invoice");
        try {
            while(rsinvoice.next()){
                dataSuppliers.add(new DataSupplier(rsinvoice.getString("supplier_id"),
                        rsinvoice.getString("amount"),
                        rsinvoice.getString("invoice_number"), 
                        rsinvoice.getDate("date"), 
                        rsinvoice.getString("truck_part_id"), 
                        rsinvoice.getString("qty"),
                        rsinvoice.getString("truck_part_invoice")));
                
            }
        } catch (Exception e) {
            System.out.print("Error on trucjpart invoice  setItemsTruckpartInvoice " + e);
            System.exit(0);
        }
                
        
    }
    
    
   public void cbosupplier(){
       //clear the cbo spplier os that no duplication of items;
       cbo_supplier.getItems().clear();
       
       DatabaseConnection dbcboSupplier = new DatabaseConnection("root", "localhost", "eyefleet", "ramjorgeyao");
       ResultSet rscbosupplier = dbcboSupplier.setQuery("SELECT * FROM eyefleet.supplier");
       try {
           while(rscbosupplier.next()){
               cbo_supplier.getItems().add(rscbosupplier.getString("supplier_id"));
            }
               
       } catch (Exception e) {
            System.out.print("Error on trucjpart invoice  cbosupplier " + e);
            System.exit(0);    
       }
   }
    
   public void supplierDate(){
      date = mc_truck_parts_supplier_date.getValue();
   }
   
   
   public void clicktableSupplier(){
       tableview_supplier.setOnMouseClicked((MouseEvent)->{
         DataSupplier dataSupplierselect = (DataSupplier)tableview_supplier.getSelectionModel().getSelectedItem();

           if(dataSupplierselect !=null){
           cbo_supplier.setValue(dataSupplierselect.getSupplier_id());
           text_supplier_amount.setText(dataSupplierselect.getAmount());
           text_supplier_quantity.setText(dataSupplierselect.getQty());
           text_supplier_voicenumber.setText(dataSupplierselect.getInvoice_number());
           mc_truck_parts_supplier_date.setValue(LocalDate.of(dataSupplierselect.getDate().getYear()+1900, dataSupplierselect.getDate().getMonth()+1,dataSupplierselect.getDate().getDate()));
           
       }
      
       });
       
   }
   
   
   public void btnUpdate(){
       
       btn_supplier_update.setOnMouseClicked((MouseEvent)->{
           //error trapping
           if(cbo_supplier.getValue()==null){
                  controller.getMessage().shortToast("Please provide Supplier", 2000);
             }
           else if(text_supplier_amount.getText().isEmpty() || !errorTrapping.isNumerical(text_supplier_amount.getText())){
                         controller.getMessage().shortToast("Please provide correct amount", 2000);
              }
           else if(text_supplier_quantity.getText().isEmpty() || !errorTrapping.isNumerical(text_supplier_quantity.getText())){
                                 controller.getMessage().shortToast("Please provide correct quantity", 2000);

            }
           else  if(text_supplier_voicenumber.getText().isEmpty() || !errorTrapping.isNumerical(text_supplier_voicenumber.getText())){
                                 controller.getMessage().shortToast("Please provide correct invoice number", 2000);

             }
           
           
           else{
           //create database and query ;
         DataSupplier dataSupplierselect = (DataSupplier)tableview_supplier.getSelectionModel().getSelectedItem();
         DatabaseConnection dbupdateSupplier = new DatabaseConnection("root", "localhost", "eyefleet", "ramjorgeyao");
           try {
               
               
                    //update te records with query;
                    dbupdateSupplier.updateDatabase("UPDATE eyefleet.truck_part_invoice SET qty  ='"+  text_supplier_quantity.getText()+"', "
                                                                                                                    +  "amount  ='" +  text_supplier_amount.getText() +"', "
                                                                                                                    + "date  ='" +  mc_truck_parts_supplier_date.getValue().toString()+"', "
                                                                                                                    + "invoice_number  ='" +  text_supplier_voicenumber.getText() +"', "
                                                                                                                     + "supplier_id ='" + cbo_supplier.getValue().toString()+ "' "
                                                                                                                    + "  WHERE truck_part_invoice  ='" +  dataSupplierselect.getTruck_part_invoice()+ "'" );
       
               //clear data
               dataSuppliers.clear();
               
               //refersh table supplier
               setItemsTruckpartInvoice();
               
               
           } catch (Exception e) {
             }
           
           }//end of if ;
       });
   }
   
   
    public void cbofilterbyMonth(){
        
        //add items on cbo filter by month;
        filterbymonth_cbo_truck.getItems().add("January");
        filterbymonth_cbo_truck.getItems().add("February");
        filterbymonth_cbo_truck.getItems().add("March");
        filterbymonth_cbo_truck.getItems().add("April");
        filterbymonth_cbo_truck.getItems().add("May");
        filterbymonth_cbo_truck.getItems().add("June");
        filterbymonth_cbo_truck.getItems().add("July");
        filterbymonth_cbo_truck.getItems().add("August");
        filterbymonth_cbo_truck.getItems().add("September");
        filterbymonth_cbo_truck.getItems().add("October");
        filterbymonth_cbo_truck.getItems().add("November");
        filterbymonth_cbo_truck.getItems().add("December");
        
       
    }
    
     // get the value of the cbofilterbyMonth and convert the string into integer using this function;
    public Integer getMonthValue(String month){
        int monthVaue = 0;
        
        switch(month){
            case "January":
                monthVaue = 1;
                break;
            case "February":
                monthVaue = 2;
                break;
            case "March":
                monthVaue = 3;
                break;
            case "April":
                monthVaue = 4;
                break;
            case "May":
                monthVaue = 5;
                break;
            case "June":
                monthVaue = 6;
                break;
            case "July":
                monthVaue = 7;
                break;
            case "August":
                monthVaue = 8;
                break;
            case "September":
                monthVaue = 9;
                break;
            case "October":
                monthVaue = 10;
                break;
            case "November":
                monthVaue = 11;
                break;
            case "December":
                monthVaue = 12;
                break;
        }
        
        return monthVaue;
    }
    
    
    public void btnfilterbyMonth(){
        btn_filter_by_month.setOnMouseClicked((MouseEvent)->{
            
            //clear data
            dataSuppliers.clear();
            
            //check the combobox filter by month if it has a value;
            if(filterbymonth_cbo_truck.getValue()==null){
                                            controller.getMessage().shortToast( "Please select a Month To filter:", 2000);
             }
            
            else{
            try {
                 
               DatabaseConnection dbfilterbymonth = new DatabaseConnection("root", "localhost", "eyefleet", "ramjorgeyao");
               ResultSet rsfilterbymonth = dbfilterbymonth.setQuery("SELECT truck_part_invoice.supplier_id, truck_part_invoice.amount, "
                       + "truck_part_invoice.invoice_number, truck_part_invoice.date, truck_part_invoice.truck_part_id, truck_part_invoice. qty, truck_part_invoice.truck_part_invoice FROM eyefleet.truck_part_invoice "
                       + " WHERE month(date) = " + getMonthValue( filterbymonth_cbo_truck.getValue().toString()));
                       
                       while(rsfilterbymonth.next()){
                           dataSuppliers.add(new DataSupplier(rsfilterbymonth.getString("supplier_id"), 
                                   rsfilterbymonth.getString("amount"),
                                   rsfilterbymonth.getString("invoice_number"),
                                   rsfilterbymonth.getDate("date"),
                                   rsfilterbymonth.getString("truck_part_id"),
                                   rsfilterbymonth.getString("qty"),
                                   rsfilterbymonth.getString("truck_part_invoice")));
                           
                       }
                       
            } catch (Exception e) {
                 System.out.print("Error on truckpart invoice  btnfilterbyMonth " + e);
                 System.exit(0);
            }
            }//end of if;
        });
    }
    
    //function for btnFilterbyRangeFilter
    public void btnFilterbyRangeFilter(){
       btn_filter_by_range.setOnMouseClicked((MouseEvent) ->{ 
           // the date of each 
             LocalDate date_from = from_datepicker.getValue();
             LocalDate date_to = to_datepicker.getValue();
       
             if(from_datepicker.getValue()==null ){
                                             controller.getMessage().shortToast( "Please select a Date To:", 2000);
              }
             else if (to_datepicker.getValue()==null){
                                             controller.getMessage().shortToast( "Please select a Date To:", 2000);
             }
              
             else{
             try {
                 //clear data 
                 dataSuppliers.clear();
                 
                 //database and query  and get the date from to date to ;
               DatabaseConnection dbfilterbyrange= new DatabaseConnection("root", "localhost", "eyefleet", "ramjorgeyao");
               ResultSet rsfilterbyrange = dbfilterbyrange.setQuery("SELECT truck_part_invoice.supplier_id, truck_part_invoice.amount, "
                       + "truck_part_invoice.invoice_number, truck_part_invoice.date, truck_part_invoice.truck_part_id, truck_part_invoice. qty, truck_part_invoice.truck_part_invoice FROM eyefleet.truck_part_invoice"
                       + "  WHERE  date >= '" + date_from.getYear()+ "-" +date_from.getMonthValue()+"-"+ date_from.getDayOfMonth() +"'" +
                                                    " AND  date <= '"+ date_to.getYear()+ "-" + date_to.getMonthValue() +"-" +date_to.getDayOfMonth() +"'");
               
               while(rsfilterbyrange.next()){
                    dataSuppliers.add(new DataSupplier(rsfilterbyrange.getString("supplier_id"), 
                                   rsfilterbyrange.getString("amount"),
                                   rsfilterbyrange.getString("invoice_number"),
                                   rsfilterbyrange.getDate("date"),
                                   rsfilterbyrange.getString("truck_part_id"),
                                   rsfilterbyrange.getString("qty"),
                                   rsfilterbyrange.getString("truck_part_invoice")));
                   
               }
           } catch (Exception e) {
                   System.out.print("Error on truckpart invoice  btnFilterbyRangeFilter " + e);
                  System.exit(0);
           }
             }//end of if
       });
       
    }
    
    
    public void btnNofilter(){
                    btn_no_filter.setOnMouseClicked((MouseEvent)->{
                        
                      DatabaseConnection dbfilterbyrange= new DatabaseConnection("root", "localhost", "eyefleet", "ramjorgeyao");
                      ResultSet rsfilter = dbfilterbyrange.setQuery("SELECT * FROM eyefleet.truck_part_invoice");
                        try {
                            while(rsfilter.next()){
                                dataSuppliers.add(new DataSupplier(rsfilter.getString("supplier_id"), 
                                   rsfilter.getString("amount"),
                                   rsfilter.getString("invoice_number"),
                                   rsfilter.getDate("date"),
                                   rsfilter.getString("truck_part_id"),
                                   rsfilter.getString("qty"),
                                   rsfilter.getString("truck_part_invoice")));
                                
                               //clear data
                                dataSuppliers.clear();
                                //refresh data;
                                setItemsTruckpartInvoice();
                                
                            }
                        } catch (Exception e) {
                             System.out.print("Error on truckpart invoice  btnNofilter " + e);
                             System.exit(0);
                        }
                    
                    });
      
    }
    
    
    
    
    
    
    public class DatamcTruckParts{
        private String truck_part_id;
        private String quantity_on_hand;
        private String description;
        private String price;
        

        public DatamcTruckParts(String truck_part_id,
                                 String quantity_on_hand,
                                 String description,
                                 String price) {
            
            this.truck_part_id = truck_part_id;
            this.quantity_on_hand = quantity_on_hand;
            this.description = description;
            this.price = price;
            
        }

        public String getTruck_part_id() {
            return truck_part_id;
        }

        public void setTruck_part_id(String truck_part_id) {
            this.truck_part_id = truck_part_id;
        }

        public String getQuantity_on_hand() {
            return quantity_on_hand;
        }

        public void setQuantity_on_hand(String quantity_on_hand) {
            this.quantity_on_hand = quantity_on_hand;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public String getPrice() {
            return price;
        }

        public void setPrice(String price) {
            this.price = price;
        }
         
    }
    
    //truckpart invoice data;
    public class DataSupplier{
        private String supplier_id;
        private String amount;
        private String invoice_number;
        private Date date;
        private String truck_part_id;
        private String qty;
        private String truck_part_invoice;

        
        public DataSupplier(String supplier_id,
                String amount,
                String invoice_number,
                Date date,
                String truck_part_id,
                String qty,
                String truck_part_invoice) {
            
            this.supplier_id = supplier_id;
            this.amount = amount;
            this.invoice_number = invoice_number;
            this.date = date;
            this.truck_part_id = truck_part_id;
            this.qty = qty;
            this.truck_part_invoice = truck_part_invoice;
        }

        public String getSupplier_id() {
            return supplier_id;
        }

        public String getAmount() {
            return amount;
        }

        public String getInvoice_number() {
            return invoice_number;
        }

        public Date getDate() {
            return date;
        }

        public String getTruck_part_id() {
            return truck_part_id;
        }

        public String getQty() {
            return qty;
        }

        public String getTruck_part_invoice() {
            return truck_part_invoice;
        }

        public void setTruck_part_invoice(String truck_part_invoice) {
            this.truck_part_invoice = truck_part_invoice;
        }

        public void setSupplier_id(String supplier_id) {
            this.supplier_id = supplier_id;
        }

        public void setAmount(String amount) {
            this.amount = amount;
        }

        public void setInvoice_number(String invoice_number) {
            this.invoice_number = invoice_number;
        }

        public void setDate(Date date) {
            this.date = date;
        }

        public void setTruck_part_id(String truck_part_id) {
            this.truck_part_id = truck_part_id;
        }

        public void setQty(String qty) {
            this.qty = qty;
        }
        
        
    }
    
}
