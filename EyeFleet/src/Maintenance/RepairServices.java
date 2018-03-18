/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Maintenance;

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
import javafx.scene.control.TextArea;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

/**
 *
 * @author ()->Jorge{}
 */
public class RepairServices {
    //FOR CONTROLLER;
    private LayoutController main_module;
    
    //FOR TABLEVIEW;
    private TableView tbl_repairServices;
    
    //FOR TABLE COLUMNS;
    private TableColumn services_col_date;
    private TableColumn services_col_truck_id;
    private TableColumn services_col_status;
    
    // FOR COMBOBOX;
    private ComboBox Services_cbo_truck;
    
    //FOR TECTFIELD;
    private TextArea Services_txt_remarsk;
    
    //FOR LABEL BUTTON;
    private Label Services_btn_set_as_repaired;
    private Label Services_btn_save;
    
    //FOR OBSERVABLELIST;
    private ObservableList<DatarepairServices>dataRepaiServices;
    
    //FOR THE SELECTTED ITEM;
    private String selectedElement ="";
    
    public Label btnrefresh;
    
    public RepairServices(LayoutController main_module_param) {
        //FOR THE CONROLLER;
        this.main_module = main_module_param;
        
        //FOR THE TABLE SERVICES;
        this.tbl_repairServices = main_module_param.getServices_tbl_services();
        
        //FOR THE TABLE COLUMN;
        this.services_col_date = main_module_param.getServices_tbl_services_col_date();
        this.services_col_truck_id = main_module_param.getServices_tbl_services_col_truck_id();
        this.services_col_status = main_module_param.getServices_tbl_services_col_status();
        
         //FORCOMBOBOX;
        this.Services_cbo_truck = main_module_param.getServices_cbo_truck();
        
        //for textfield;
        this.Services_txt_remarsk = main_module_param.getServices_txt_description();
        
        //for button save;
        this.Services_btn_save = main_module_param.getServices_btn_save();
        this.Services_btn_set_as_repaired = main_module_param.getServices_btn_set_as_repaired();
       
        this.btnrefresh = main_module_param.getServices_btn_set_as_rfresh();
        //INITIALIZED  MODULE;
        initilizedModule();
    }
    
    public void initilizedModule(){
        //set the functionalities and prepare nodes
        setUptablecolumns();
        setUpitems();
        setUpcboTruckItems();
        setUpFunctionsbtnsave();
        setFunctiononrepair();
        setfunctionbtnRepaired();        
        
        btnrefresh();
    }
    
        
     public void setUptablecolumns(){
        //SET UP ALL TABLE COLUMNS;
        dataRepaiServices = FXCollections.observableArrayList();
        services_col_date.setCellValueFactory(new PropertyValueFactory("date"));
        services_col_truck_id .setCellValueFactory(new PropertyValueFactory("truck_id"));
        services_col_status.setCellValueFactory(new PropertyValueFactory("status"));
        tbl_repairServices.setItems(dataRepaiServices);
     } 
     
     public void setUpitems(){
         //CREATE DATABASE AND RESULTSET;
        DatabaseConnection dbselect = new DatabaseConnection("root", "localhost", "eyefleet", "ramjorgeyao");
        ResultSet rs_select = dbselect.setQuery("SELECT * FROM eyefleet.repair_service WHERE status= 'on repair' ");
        
        try {
            //GET THE VALUE OF THE RESULSET;
            while(rs_select.next()){
                //ADD THE TABLE NEW DATA IN OBSERVALELIST;
                dataRepaiServices.add(new DatarepairServices(rs_select.getString("repair_service_id"),
                                                             rs_select.getDate("date"),
                                                             rs_select.getString("truck_id"),
                                                             rs_select.getString("status"),
                                                             rs_select.getString("remarks")));
            }
        } catch (Exception e) {
            System.out.print("ERROR ON MODULE REPAIRSERVICES SETUP ITEMS: " +e);
            System.exit(0);
        }
    }
     
          //set up truckID of truck
     public void setUpcboTruckItems(){ 
         //CREATE DATABSE AND RESULSET;
        DatabaseConnection dbselect = new DatabaseConnection("root", "localhost", "eyefleet", "ramjorgeyao");
        ResultSet rs_select = dbselect.setQuery("SELECT * FROM eyefleet.truck");
        
        try {
            //GET THE VALUE OF THE RESUL;SET;
            while(rs_select.next()){
                Services_cbo_truck.getItems().add(rs_select.getString("truck_id"));
            }
        } catch (Exception e) {
            System.out.print("ERROR ON MODULE REPAIRSERVICES SETUP_CBOTRUCKITEMS: " +e);
            System.exit(0);
        }
    }
     
        //funtionality of the btnSave
    public void setUpFunctionsbtnsave(){    
        //GET THE BTNS SAVE AND CREATE THE FUNCTIONALITIES;
         try {
        Services_btn_save.setOnMouseClicked((MouseEvent)->{
        
            //check if combobox if it has a value;
              if(Services_cbo_truck.getValue()== null){
                    main_module.getMessage().shortToast( "Please select an item on   truck:", 2000);
            }
            //check  textfields desription if it has a value;
           else if(Services_txt_remarsk.getText().isEmpty()){
                                   main_module.getMessage().shortToast( "Please Provide a Desription of the Item::", 2000);
             }
             
           else{
        //INITIALIZED THE VARIABLE OF THE DATE;    
        LocalDate now = LocalDate.now();
        int year = now.getYear();
        int month = now.getMonthValue();
        int day = now.getDayOfMonth();
        
        //CREATE DATABASE AND RESULSET;
        DatabaseConnection dbselect = new DatabaseConnection("root", "localhost", "eyefleet", "ramjorgeyao");
         dbselect.updateDatabase("INSERT INTO eyefleet.repair_service(date, truck_id, status, remarks)"+
                                "VALUES('"+year+"-"+month+"-"+day+"', " +
                                        "'" +Services_cbo_truck.getValue()+"', " +
                                        "'" +"on repair"+"'," + 
                                        "'" +Services_txt_remarsk.getText()+"')");
         
         //CLEAR THE DATA
         dataRepaiServices.clear();
         
         //REFRESH THE TABLE REPAIR SERVICES;
         setUpitems();
         
        //UPDATE THE STATUS; 
        main_module.getModule_truckStatus().Setcheckstatus(Services_cbo_truck.getValue().toString());

           }//end of else;
        });
        } catch (Exception e) {
            System.out.print("ERROR ON MODULE REPAIRSERVICES SETUP_FUNCTIONBTNSAVE " +e);
         }
         
    }
        
        
    public void setFunctiononrepair(){
        //GET THE VALUE OF THE TBL REPAIRSERVICES;
        tbl_repairServices.setOnMouseClicked((MouseEvent)->{
        DatarepairServices dataservices = (DatarepairServices)tbl_repairServices.getSelectionModel().getSelectedItem();
        
        //CHECK THE TABLE REPAIRSERVICES IF IT HAS NO VALUE;
        if(dataservices != null){
            Services_cbo_truck.setValue(dataservices.getTruck_id());
            Services_txt_remarsk.setText(dataservices.getRemarks().toString());
        }
        
        });
     }
     
    //FUNCTION FOR BTN REPAIRED;
    public void setfunctionbtnRepaired(){
        
        //GET THE BTN SET AS REPAIRED SET AS ON ACTION;
        Services_btn_set_as_repaired.setOnMouseClicked((MouseEvent)->{
        
        //GET THE SLECTED ITEMS IN THE TABLE;
        DatarepairServices dataservices = (DatarepairServices)tbl_repairServices.getSelectionModel().getSelectedItem();
        if(dataservices != null){
         
        //CREATE DATABASE AND RESULSET;
        DatabaseConnection db_services = new DatabaseConnection("root", "localhost", "eyefleet", "ramjorgeyao");
        db_services.updateDatabase("UPDATE eyefleet.repair_service SET status ='" +"repaired"+"'" +
                                        " WHERE repair_service_id ='"+ dataservices.getRepair_service_id()+ "'");
        
        //GET THE MODULE TRUSCK STATUS THEN GET THE FUNCTION OF THE SETCHECKSTATUS AND GET THE TRUCK ID;
         main_module.getModule_truckStatus().Setcheckstatus(dataservices.getTruck_id());
        
        //CLEAR THE DATA;
        dataRepaiServices.clear();
        
        //REFRESH THE TABLE ;
        setUpitems();
        }//end if
        
        else{
                        main_module.getMessage().shortToast("Please Select a record in the Table:", 2000);
         }
            

        });
    }
    
    
    public void btnrefresh(){
        btnrefresh.setOnMouseClicked((MouseEvent)->{
            Services_cbo_truck.setValue(null);
            Services_txt_remarsk.clear();
        
        });
    }
    
    
    
        //data of repairservices
     public class DatarepairServices{
        private String repair_service_id;
        private Date date;
        private String truck_id;
        private String status;
        private String remarks;
        
        public DatarepairServices(String repair_service_id,
                                  Date date,
                                  String truck_id,
                                  String status,
                                  String remarks
                                  ) {
            this.repair_service_id = repair_service_id;
            this.date = date;
            this.truck_id = truck_id;
            this.status = status;
            this.remarks = remarks;
         }
  
        public Date getDate() {
            return date;
        }

        
        public void setDate(Date date) {
            this.date = date;
        }

        
        public String getTruck_id() {
            return truck_id;
        }

        
        public void setTruck_id(String truck_id) {
            this.truck_id = truck_id;
        }

        
        public String getStatus() {
            return status;
        }

        
        public void setStatus(String status) {
            this.status = status;
        }

        
        public String getRepair_service_id() {
            return repair_service_id;
        }

        
        public void setRepair_service_id(String repair_service_id) {
            this.repair_service_id = repair_service_id;
        }

        public String getRemarks() {
            return remarks;
        }

        public void setRemarks(String remarks) {
            this.remarks = remarks;
        }

        
         

         
        
    }
}
