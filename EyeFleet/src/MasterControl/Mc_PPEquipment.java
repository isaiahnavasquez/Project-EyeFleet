/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package MasterControl;

import eyefleet.DatabaseConnection;
import eyefleet.ErrorTraps;
import eyefleet.LayoutController;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.Month;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
 * @author jorge
 */
public class Mc_PPEquipment {
    private LayoutController controller;
    
    //tables ;
    private TableView equipment_tableview;
    private TableColumn equipment_col_equipment_id;
    private TableColumn equipment_col_description;
    private TableColumn equipment_col_qty_on_hand;
    
    //fileds area;
    private ComboBox equipment_cbo_equipment;
    private TextField equipment_txt_description;
    private TextField equipment_txt_qty_on_hand;
    
    //btn fields
    private Label equipment_btn_save;
    private Label equipment_btn_edit;
    private Label equipment_btn_delete;
    //observablelist;
    private ObservableList<DataEquipment> dataEquipments;
    
    //error traps
    ErrorTraps traps = new ErrorTraps();
 
    
    //data equipment invoice;
    private TableView    equipment_tableview_supplier;
    private TableColumn  col_supplier;
    private TableColumn  col_supplier_amount;
    private TableColumn  col_supplier_voicenumber;
    private TableColumn  col_supplier_date;
    private TableColumn  col_supplier_equipment_id;
    private TableColumn  col_supplier_quantity;
    //fields
    private ComboBox cbo_supplier;
    private TextField txt_supplier_amount;
    private TextField txt_supplier_voicenumber;
    private TextField txt_supplier_quantity;
    private DatePicker  equipment_date_supplier;
    //btns
    private Label btn_supplier_add;
    private Label btn_supplier_update;
    
    private LocalDate date;
    private ObservableList<DataequipmentInvoice>dataequipmentInvoices;
    
    private  ComboBox   filterbymonth_cbo_truck;
    private Label  btn_filter_by_month;
    private DatePicker from_datepicker;
    private DatePicker  to_datepicker;
    private Label  btn_filter_by_range;
    private Label btn_no_filter;
    
    public Mc_PPEquipment(LayoutController controller_param) {
        this.controller = controller_param;
        //table and clumns;
        this.equipment_tableview = controller_param.getMc_protective_equipment_tableview();
        this.equipment_col_equipment_id = controller_param.getMc_protective_equipment_col_equipment_id();
        this.equipment_col_description = controller_param.getMc_protective_equipment_col_description();
        this.equipment_col_qty_on_hand = controller_param.getMc_protective_equipment_col_qty_on_hand();
        //fileds area;
        this.equipment_cbo_equipment = controller_param.getMc_protective_equipment_cbo_equipment();
        this.equipment_txt_description = controller_param.getMc_protective_equipment_txt_description();
        this.equipment_txt_qty_on_hand = controller_param.getMc_protective_equipment_txt_qty_on_hand();
        //btn fields;
        this.equipment_btn_save = controller_param.getMc_protective_equipment_btn_save();
        this.equipment_btn_edit = controller_param.getMc_protective_equipment_btn_edit();
        this.equipment_btn_delete = controller_param.getMc_protective_equipment_btn_delete();
        
        //data equipment datas;
       this.equipment_tableview_supplier = controller_param.getMc_protective_equipment_tableview_supplier();
       this.col_supplier = controller_param.getMc_protective_equipment_col_supplier();
       this.col_supplier_amount = controller_param.getMc_protective_equipment_col_supplier_amount();
       this.col_supplier_voicenumber = controller_param.getMc_protective_equipment_col_supplier_voicenumber();
       this.col_supplier_date = controller_param.getMc_protective_equipment_col_supplier_date();
       this.col_supplier_equipment_id = controller_param.getMc_protective_equipment_col_supplier_equipment_id();
       this.col_supplier_quantity = controller_param.getMc_protective_equipment_col_supplier_quantity();
       
       this.cbo_supplier = controller_param.getMc_protective_equipment_cbo_supplier();
       this.txt_supplier_amount = controller_param.getMc_protective_equipment_txt_supplier_amount();
       this.txt_supplier_voicenumber = controller_param.getMc_protective_equipment_txt_supplier_voicenumber();
       this.equipment_date_supplier = controller_param.getMc_protective_equipment_date_supplier();
       this.txt_supplier_quantity = controller_param.getMc_protective_equipment_txt_supplier_quantity();
       this.btn_supplier_add = controller_param.getMc_protective_equipment_btn_supplier_add();
       this.btn_supplier_update = controller_param.getMc_protective_equipment_btn_supplier_update();
       
       this.filterbymonth_cbo_truck = controller_param.getMc_equipment_filterbymonth_cbo_truck();
       this.btn_filter_by_month = controller_param.getMc_equipment_btn_filter_by_month();
       this.from_datepicker = controller_param.getMc_equipment_from_datepicker();
       this.to_datepicker = controller_param.getMc_equipment_to_datepicker1();
       this.btn_filter_by_range = controller_param.getMc_equipment_btn_filter_by_range();
       this.btn_no_filter = controller_param.getMc_equipment_btn_no_filter();
       
        //initialized part of equipment;
        initialized();
        
        //initialized part of equipment invoice;
        InitializedEquipmentinvoice();
        
    }
    
    public void initialized(){
        setTablecolumns();
        setItems();
        clickTable();
        cboEquipment();
    //    cboequipmentDescription();
        btnSAve();
        btnEdit();
        btnReresh();
        setItems();
     
        
    }
    
    public void setTablecolumns(){
        //observable data;
        dataEquipments = FXCollections.observableArrayList();
        equipment_col_equipment_id.setCellValueFactory(new PropertyValueFactory("equipment_id"));
        equipment_col_description.setCellValueFactory(new PropertyValueFactory("description"));
        equipment_col_qty_on_hand.setCellValueFactory(new PropertyValueFactory("qty_on_hand"));
        equipment_tableview.setItems(dataEquipments);
    }
    
    
    public void setItems(){
        //data clear;
        dataEquipments.clear();
        
        //set all item from equipment;
        DatabaseConnection dbitems = new DatabaseConnection("root", "localhost", "eyefleet", "ramjorgeyao");
        ResultSet rsItems = dbitems.setQuery("SELECT * FROM eyefleet.equipment");
        try {
            while(rsItems.next()){
                dataEquipments.add(new DataEquipment(rsItems.getString("equipment_id"), 
                        rsItems.getString("qty_on_hand"),
                        rsItems.getString("description")));
                
            }
            
        } catch (Exception e) {
            System.out.print("ERROR ON MC PPEquipment setItems:  " +  e);
            System.exit(0);
        }
        
    }
    
    //click the table if it has a value ;
    public void clickTable(){
        equipment_tableview.setOnMouseClicked((MouseEvent)->{
        DataEquipment dataEquipmentselect = (DataEquipment)equipment_tableview.getSelectionModel().getSelectedItem();
            if(dataEquipmentselect != null){
                equipment_cbo_equipment.setValue(dataEquipmentselect.getEquipment_id());
                equipment_txt_description.setText(dataEquipmentselect.getDescription());
                equipment_txt_qty_on_hand.setText(dataEquipmentselect.getQty_on_hand());
            }
        
        
        });
    }
    
    
    public void cboEquipment(){
            //cbo equipment items
        //clear cbo
            equipment_cbo_equipment.getItems().clear();
            
            DatabaseConnection dbcbo = new DatabaseConnection("root", "localhost", "eyefleet", "ramjorgeyao");
            ResultSet rscbo = dbcbo.setQuery("SELECT * FROM eyefleet.equipment");
            try {
                while(rscbo.next()){
                     equipment_cbo_equipment.getItems().add(rscbo.getString("equipment_id"));
                }
                  
            } catch (Exception e) {
                System.out.print("ERROR ON MC PPEquipment cboEquipment:  " +  e);
                     System.exit(0);
           
            }
            
            //get the description of the cbo equipment;
            equipment_cbo_equipment.setOnAction((ActionEvent)->{
               
                try {
                  String cbo ;
                cbo = equipment_cbo_equipment.getValue().toString();
                  DatabaseConnection dbcbodescription = new DatabaseConnection("root", "localhost", "eyefleet", "ramjorgeyao");
                ResultSet rscbodescription = dbcbodescription.setQuery("SELECT * FROM eyefleet.equipment WHERE equipment_id ='"+ cbo+"'");
                    
                while(rscbodescription.next()){
                        
                        equipment_txt_description.setText(rscbodescription.getString("description"));
                    }
                } catch (Exception e) {
                 
                }
            
            });
            
            
    }
    
    public void cboequipmentDescription(){
        
    }
     
    
    
    
    public void btnSAve(){
        //insert new data records;
        equipment_btn_save.setOnMouseClicked((MouseEvent)->{
            
            //error traps;
            boolean exist = false;
             DatabaseConnection dbcbo = new DatabaseConnection("root", "localhost", "eyefleet", "ramjorgeyao");
             ResultSet rscbo = dbcbo.setQuery("SELECT * FROM eyefleet.equipment");
             try {
                while(rscbo.next()){
                 if(equipment_cbo_equipment.getValue().equals(rscbo.getString("equipment_id")) || equipment_txt_description.getText().equals(rscbo.getString("description"))){
                    exist = true;
                     break;
            }
                 }
                
                 } catch (Exception e) {
             }
               if(exist == true){
                               controller.getMessage().shortToast("Equipment Id already Exist::", 2000);

                   
            }//end of error trps;
            
             else if (equipment_cbo_equipment.getValue()==null){
                                                controller.getMessage().shortToast("Please provide Equipment:", 2000);

              }
            
            else if(equipment_txt_description.getText().isEmpty()){
                                               controller.getMessage().shortToast("Please provide description", 2000);

                 
            }
            else if(equipment_txt_qty_on_hand.getText().isEmpty() || !traps.isNumerical(equipment_txt_qty_on_hand.getText())){
                                               controller.getMessage().shortToast("Please provide Quantity::", 2000);

                  
            }
            
           
            
            else{
            //else
            DatabaseConnection dbSave = new DatabaseConnection("root", "localhost", "eyefleet", "ramjorgeyao");
            try {
                dbSave.updateDatabase("INSERT INTO eyefleet.equipment(equipment_id, qty_on_hand , description)"
                    + "VALUES('" + equipment_cbo_equipment.getValue().toString() +"', " +
                            "'"  + equipment_txt_qty_on_hand.getText().toString() + "', " +
                            "'"  + equipment_txt_description.getText().toString() + "')");
                              
                            //clear data 
                             dataEquipments.clear();
                            //refrsh table
                            setItems();
                            
                           //cbo eqpment refresh
                            cboEquipment();;
                           
            } catch (Exception e) {
                 System.out.print("ERROR ON MC PPEquipment btnSAve:  " +  e);
                 System.exit(0);
            } 
   
            }//end of if;
            
         });
    }
    
    
    public void btnEdit(){
        //update data recprds;
        equipment_btn_edit.setOnMouseClicked((MouseEvent)->{
            
              if (equipment_cbo_equipment.getValue()==null){
                                                                 controller.getMessage().shortToast("Please provide Equipment", 2000);

              }
            
            else if(equipment_txt_description.getText().isEmpty()|| traps.isNumerical(equipment_txt_description.getText())){
                                                               controller.getMessage().shortToast("Please provide description:", 2000);

                 
            }
            else if(equipment_txt_qty_on_hand.getText().isEmpty() || !traps.isNumerical(equipment_txt_qty_on_hand.getText())){
                                                               controller.getMessage().shortToast("Please provide Quantity::", 2000);

                  
            }
            
            else{
            try {
                DataEquipment dataEquipmentselect = (DataEquipment)equipment_tableview.getSelectionModel().getSelectedItem();
                DatabaseConnection dbEdit = new DatabaseConnection("root", "localhost", "eyefleet", "ramjorgeyao");
                dbEdit.updateDatabase("UPDATE eyefleet.equipment SET equipment_id ='" + equipment_cbo_equipment.getValue() + "', "
                                                        + "qty_on_hand = " + equipment_txt_qty_on_hand.getText() +", "
                                                        +"description = '"+ equipment_txt_description.getText()+"'"
                                                        +" WHERE equipment_id = '"+ dataEquipmentselect.getEquipment_id()+ "'");
                                                           
                             //clear data 
                             dataEquipments.clear();
                            //refrsh table
                            setItems();
            } catch (Exception e) {
                
            }
            }//end of iif;
        });
    }
    
    
    
    
    public void btnReresh(){
        //refresh table
        equipment_btn_delete.setOnMouseClicked((MouseEvent)->{
            try {
                
              // equipment data's
                //clear data 
                 dataEquipments.clear();
                 //equipment cbo
                
                 equipment_txt_description.clear();
                 equipment_txt_qty_on_hand.clear();
                
                 //refrsh table
                  setItems();
                  
                  //equipment invoice data's
                  dataequipmentInvoices.clear();
                     
                  cbo_supplier.getItems().clear();
                  cboSupplier();
                  txt_supplier_amount.clear();
                  txt_supplier_quantity.clear();
                  txt_supplier_voicenumber.clear();
                  equipment_date_supplier.setValue(null);
                  //refresh table equipment  invoioce;
                  setItemsequipmentEnvoice();
            
                  
            } catch (Exception e) {
                 System.out.print("ERROR ON MC PPEquipment btnReresh:  " +  e);
                 System.exit(0);
            }
        
        });
    }
    
    
    //----------------Data equipment invoice;
            
    public void InitializedEquipmentinvoice() {
        SetcolumnsEquipmentInvoice();
       // setItemsequipmentEnvoice();
        date();
        clickTableequipmentinvoice();
        btnUpdateSupplier();
        cboSupplier();
        cbofilterbyMonth();
        btnfilterbyMonth();
        btnFilterbyRangeFilter();
        btnNofilter();
    }
    
    //set table and columns;
    public void SetcolumnsEquipmentInvoice(){
        dataequipmentInvoices = FXCollections.observableArrayList();
        col_supplier.setCellValueFactory(new PropertyValueFactory("supplier_id"));
        col_supplier_amount.setCellValueFactory(new PropertyValueFactory("amount"));
        col_supplier_date.setCellValueFactory(new PropertyValueFactory("date"));
        col_supplier_equipment_id.setCellValueFactory(new PropertyValueFactory("equipment_id"));
        col_supplier_quantity.setCellValueFactory(new PropertyValueFactory("qty"));
        col_supplier_voicenumber.setCellValueFactory(new PropertyValueFactory("invoice_number"));
        equipment_tableview_supplier.setItems(dataequipmentInvoices);
        
    }
    
    //set all the items of equipment invoice;
    public void setItemsequipmentEnvoice() {
        //create database and resultset;
        DatabaseConnection dbequipmentEnvoice = new DatabaseConnection("root", "localhost", "eyefleet", "ramjorgeyao");
        ResultSet rsequipmentEnvoice = dbequipmentEnvoice.setQuery("SELECT * FROM eyefleet.equipment_invoice");
        try {
            while(rsequipmentEnvoice.next()){
              dataequipmentInvoices.add(new DataequipmentInvoice(rsequipmentEnvoice.getString("supplier_id"), 
                      rsequipmentEnvoice.getString("amount"),
                      rsequipmentEnvoice.getString("invoice_number"),
                      rsequipmentEnvoice.getDate("date"),
                      rsequipmentEnvoice.getString("qty"),
                      rsequipmentEnvoice.getString("equipment_id"),
                      rsequipmentEnvoice.getString("equipment_invoice_id")));
             }
          } catch (Exception e) {
            System.out.print("Error on mc_ppequipment setItemsequipmentEnvoice " + e);
            System.exit(0);
        }
       
         
    }
    
    //function of date 
    public void date(){
        date = equipment_date_supplier.getValue();
    }
    
    //function  for table if null   
    public void clickTableequipmentinvoice(){
        equipment_tableview_supplier.setOnMouseClicked((MouseEvent)->{
        
         DataequipmentInvoice equipmentInvoiceselect ;
        equipmentInvoiceselect = (DataequipmentInvoice)equipment_tableview_supplier.getSelectionModel().getSelectedItem();
        try {
                if(equipmentInvoiceselect !=null){
                    cbo_supplier.setValue(equipmentInvoiceselect.getSupplier_id());
                    txt_supplier_amount.setText(equipmentInvoiceselect.getAmount());
                    txt_supplier_voicenumber.setText(equipmentInvoiceselect.getInvoice_number());
                    txt_supplier_quantity.setText(equipmentInvoiceselect.getQty());
                    equipment_date_supplier.setValue(LocalDate.of(equipmentInvoiceselect.getDate().getYear()+1900, 
                            equipmentInvoiceselect.getDate().getMonth()+1, equipmentInvoiceselect.getDate().getDate()));
                }
        } catch (Exception e) {
         }
    
        
        });
        
    }
    
    //select all   supplier
    public void cboSupplier(){
          DatabaseConnection dbcboSupplier = new DatabaseConnection("root", "localhost", "eyefleet", "ramjorgeyao");
         ResultSet rscboSupplier = dbcboSupplier.setQuery("SELECT * FROM eyefleet.supplier");
            try {
                while(rscboSupplier.next()){
                    cbo_supplier.getItems().add(rscboSupplier.getString("supplier_id"));
                 }
                
             } catch (Exception e) {
                System.out.print("ERROR on module MC_PPEquipment cboSupplier " + e);
                System.exit(0);
                
            }
        
    }
    
    
    
    //update the records of the equipment inboice;
    public void btnUpdateSupplier(){
        btn_supplier_update.setOnMouseClicked((MouseEvent)->{
           if(cbo_supplier.getValue()==null){
               controller.getMessage().shortToast("Please provide Supplier", 2000);
            }
            else if (txt_supplier_amount.getText().isEmpty() || !traps.isNumerical(txt_supplier_amount.getText())) {
                  controller.getMessage().shortToast(" Please provide correct amount", 2000); 
            }
            else if (txt_supplier_voicenumber.getText().isEmpty() || !traps.isNumerical(txt_supplier_voicenumber.getText())){
                  controller.getMessage().shortToast("Please provide Invoice number", 2000);
            }
            else if(txt_supplier_quantity.getText().isEmpty() || !traps.isNumerical(txt_supplier_quantity.getText())){
                 controller.getMessage().shortToast("Please provide correct quantity", 2000);    
            }
            else if(equipment_date_supplier.getValue()==null){
                 controller.getMessage().shortToast("Please provide Date", 2000); 
            }
          
            
            else{
            
             try {
                 DataequipmentInvoice dataequipmentInvoiceselect ;
                dataequipmentInvoiceselect= (DataequipmentInvoice)equipment_tableview_supplier.getSelectionModel().getSelectedItem();
                DatabaseConnection dbupdate = new DatabaseConnection("root", "localhost", "eyefleet", "ramjorgeyao");
         
                dbupdate.updateDatabase("UPDATE eyefleet.equipment_invoice SET  qty ='" +txt_supplier_quantity.getText()+"', "
                                                                                    + "amount ='"+ txt_supplier_amount.getText()+"', "
                                                                                    + "date  ='"+equipment_date_supplier.getValue() +"', "
                                                                                    + "invoice_number  ='" +txt_supplier_voicenumber.getText().toString()+"', "
                                                                                    + "supplier_id ='" + cbo_supplier.getValue()+"'  "
                                                                                     + "  WHERE  equipment_invoice_id  = '" + dataequipmentInvoiceselect.getEquipment_invoice_id() +"'");
                //clear data 
                dataequipmentInvoices.clear();
                //rfresh table;
                setItemsequipmentEnvoice();
                
             } catch (Exception e) {
                 
            }
            }//end of  if 
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
            dataequipmentInvoices.clear();
            
            //check the combobox filter by month if it has a value;
            if(filterbymonth_cbo_truck.getValue()==null){
                                            controller.getMessage().shortToast( "Please select a Month To filter:", 2000);
             }
            
            else{
                         try {
                             DatabaseConnection dbfilterbymonth = new DatabaseConnection("root", "localhost", "eyefleet", "ramjorgeyao");
                           ResultSet rsfilterbymonth = dbfilterbymonth.setQuery("SELECT  equipment_invoice.supplier_id, equipment_invoice.amount,"
                                   + "equipment_invoice.invoice_number, equipment_invoice.date, equipment_invoice.qty,"
                                   + "equipment_invoice.equipment_id, equipment_invoice.equipment_invoice_id FROM eyefleet.equipment_invoice"
                                   + " WHERE month(date) = " + getMonthValue( filterbymonth_cbo_truck.getValue().toString()));

                           while(rsfilterbymonth.next()){
                                dataequipmentInvoices.add(new DataequipmentInvoice(rsfilterbymonth.getString("supplier_id"), 
                                      rsfilterbymonth.getString("amount"),
                                      rsfilterbymonth.getString("invoice_number"),
                                      rsfilterbymonth.getDate("date"),
                                      rsfilterbymonth.getString("qty"),
                                      rsfilterbymonth.getString("equipment_id"),
                                     rsfilterbymonth.getString("equipment_invoice_id")));
                               
                           }
            } catch (Exception e) {
                System.out.print("ERROR on MC PPEquipment btnfilterbyMonth" + e);
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
                 dataequipmentInvoices.clear();
                 
                 //database and query  and get the date from to date to ;
               DatabaseConnection dbfilterbyrange= new DatabaseConnection("root", "localhost", "eyefleet", "ramjorgeyao");
               ResultSet rsfilterbyrange = dbfilterbyrange.setQuery("SELECT  equipment_invoice.supplier_id, equipment_invoice.amount, equipment_invoice.invoice_number, "
                       + "equipment_invoice.date, equipment_invoice.qty, equipment_invoice.equipment_id, equipment_invoice.equipment_invoice_id FROM eyefleet.equipment_invoice"
                       + "  WHERE  date >= '" + date_from.getYear()+ "-" +date_from.getMonthValue()+"-"+ date_from.getDayOfMonth() +"'" +
                                                    " AND  date <= '"+ date_to.getYear()+ "-" + date_to.getMonthValue() +"-" +date_to.getDayOfMonth() +"'");
               
               while(rsfilterbyrange.next()){
                     dataequipmentInvoices.add(new DataequipmentInvoice(rsfilterbyrange.getString("supplier_id"), 
                      rsfilterbyrange.getString("amount"),
                      rsfilterbyrange.getString("invoice_number"),
                      rsfilterbyrange.getDate("date"),
                      rsfilterbyrange.getString("qty"),
                      rsfilterbyrange.getString("equipment_id"),
                      rsfilterbyrange.getString("equipment_invoice_id")));
                     
                   
               }
           } catch (Exception e) {
                   System.out.print("Error on truckpart invoice  btnFilterbyRangeFilter " + e);
                  System.exit(0);
           }
             }//end of if
       });
       
    }
    
    
    public void btnNofilter(){
        btn_no_filter.setOnMouseClicked((MouseEvent) ->{
               DatabaseConnection dbfilterbyrange= new DatabaseConnection("root", "localhost", "eyefleet", "ramjorgeyao");
               ResultSet rsfilterbyrange  = dbfilterbyrange.setQuery("SELECT * FROM eyefleet.equipment_invoice");
               try {
                while(rsfilterbyrange.next()){
                    dataequipmentInvoices.add(new DataequipmentInvoice(rsfilterbyrange.getString("supplier_id"), 
                      rsfilterbyrange.getString("amount"),
                      rsfilterbyrange.getString("invoice_number"),
                      rsfilterbyrange.getDate("date"),
                      rsfilterbyrange.getString("qty"),
                      rsfilterbyrange.getString("equipment_id"),
                      rsfilterbyrange.getString("equipment_invoice_id")));
                    
                    //clear data;
                    dataequipmentInvoices.clear();
                    //refrsh table
                    setItemsequipmentEnvoice();
                    
                }
            } catch (Exception e) {
            }
        
        
        });
    }
    
    
    
    
    public class DataEquipment{
                private String equipment_id;
                private String qty_on_hand;
                private String description;
         public DataEquipment( String equipment_id,
                               String qty_on_hand,
                               String description) {
             this.equipment_id = equipment_id;
             this.qty_on_hand = qty_on_hand;
             this.description = description;
        }

        public String getEquipment_id() {
            return equipment_id;
        }

        public void setEquipment_id(String equipment_id) {
            this.equipment_id = equipment_id;
        }

        public String getQty_on_hand() {
            return qty_on_hand;
        }

        public void setQty_on_hand(String qty_on_hand) {
            this.qty_on_hand = qty_on_hand;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }
        
    }
    
    
    //equipment invoice data;
    public  class DataequipmentInvoice{
            private String supplier_id;
            private String amount;
            private String invoice_number;
            private Date date;
            private String qty;
            private String equipment_id;
            private String equipment_invoice_id;
             
            
        public DataequipmentInvoice(String supplier_id,
                String amount,
                String invoice_number,
                Date date,
                 String qty,
                 String equipment_id,
                 String equipment_invoice_id)
        {
            
            this.supplier_id = supplier_id;
            this.amount = amount;
            this.invoice_number = invoice_number;
            this.date = date;
            this.qty= qty;
            this.equipment_id = equipment_id;
            this.equipment_invoice_id = equipment_invoice_id;
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

        public Date getDate() {
            return date;
        }

        public void setDate(Date date) {
            this.date = date;
        }

          

        public String getQty() {
            return qty;
        }

        public void setQty(String qty) {
            this.qty = qty;
        }

        public String getEquipment_id() {
            return equipment_id;
        }

        public void setEquipment_id(String equipment_id) {
            this.equipment_id = equipment_id;
        }

        
        
        public String getEquipment_invoice_id() {
            return equipment_invoice_id;
        }

        public void setEquipment_invoice_id(String equipment_invoice_id) {
            this.equipment_invoice_id = equipment_invoice_id;
        }

        
        
}
    
}
