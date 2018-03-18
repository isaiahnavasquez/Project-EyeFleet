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
import java.time.LocalDate;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.ObservableListBase;
import javafx.event.ActionEvent;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javax.swing.text.TextAction;

/**
 *
 * @author jorge:
 */
public class Mc_PP_Issuance {

    private LayoutController controller;
    
    //driver data;
    private TableView    equipment_issuance_driver_tableview;
    private TableColumn  equipment_issuance_tblc_driver;
    
    //filter by month fileds
    private ComboBox  equipment_issuance_cbo_filterby_month;
    private Label     equipment_issuance_btn_filterby_month_filter;
    
    //filter by range fileds;
    private DatePicker  equipment_issuance_from_datepicker;
    private DatePicker  equipment_issuance_to_datepicker;
    private Label       equipment_issuance_btn_filter_by_range_filter;
    private Label       equipment_issuance_btn_No_filter_by_range;
    
    // equipment issuance data;
    private TableView    equipmen_issuance_tableview;
    private TableColumn  equipment_issuance_tblc_date;
    private TableColumn  equipment_issuance_tblc_equipment_id;
    
    //fields data;
    private ComboBox  equipment_ssuance_cbo_equipment;
    private TextField equipment_issuance_text_description;
    private TextArea  equipment_issuance_textarea_remarks;
    
    private Label equipment_issuance_btn_save;
    private Label equipment_issuance_btn_edit;
    private Label equipment_issuance_btn_delete;
    
    //observablelist;
    private ObservableList<DataMcDriver> dataMcDrivers;
    private ObservableList<DataMcppEquipmentIsssuance> dataMcppEquipmentIsssuances;
    
    //error trpas 
     //FOR ERROR TRAPPING
    ErrorTraps errorTrapping = new ErrorTraps();
    
    public Mc_PP_Issuance(LayoutController controller_param) {
        this.controller = controller_param;
        
        //driver tables
        this.equipment_issuance_driver_tableview = controller_param.getMc_protectice_equipment_issuance_driver_tableview();
        this.equipment_issuance_tblc_driver = controller_param.getMc_protectice_equipment_issuance_tblc_driver();
        
        //filter by month fileds
        this.equipment_issuance_cbo_filterby_month = controller_param.getMc_protectice_equipment_issuance_cbo_filterby_month();
        this.equipment_issuance_btn_filterby_month_filter = controller_param.getMc_protectice_equipment_issuance_btn_filterby_month_filter();
        
        //filter by range fileds;
        this.equipment_issuance_from_datepicker = controller_param.getMc_protectice_equipment_issuance_from_datepicker();
        this.equipment_issuance_to_datepicker = controller_param.getMc_protectice_equipment_issuance_to_datepicker();
        this.equipment_issuance_btn_filter_by_range_filter = controller_param.getMc_protectice_equipment_issuance_btn_filter_by_range_filter();
        this.equipment_issuance_btn_No_filter_by_range = controller_param.getMc_protectice_equipment_issuance_btn_No_filter_by_range();
        
        
        // equipment issuance data;;
        this.equipmen_issuance_tableview = controller_param.getMc_protectice_equipmen_issuance_tableview();
        this.equipment_issuance_tblc_date = controller_param.getMc_protectice_equipment_issuance_tblc_date();
        this.equipment_issuance_tblc_equipment_id = controller_param.getMc_protectice_equipment_issuance_tblc_equipment_id();
        
        //fields data;
        this.equipment_ssuance_cbo_equipment = controller_param.getMc_protectice_equipment_ssuance_cbo_equipment();
        this.equipment_issuance_text_description = controller_param.getMc_protectice_equipment_issuance_text_description();
        this.equipment_issuance_textarea_remarks = controller_param.getMc_protectice_equipment_issuance_textarea_remarks();
        
        //btn
        this.equipment_issuance_btn_save = controller_param.getMc_protective_equipment_btn_save();
        this.equipment_issuance_btn_edit = controller_param.getMc_protective_equipment_btn_edit();
        this.equipment_issuance_btn_delete = controller_param.getMc_protective_equipment_btn_delete();
        
        //initilaized modules;
        initializedModules();
    }
    
    
    public void initializedModules(){
        setTableColumns();
        setItemsdriver();
        setClickTabledriver();
        cbofilterbyMonth();
        btnFilterbyMonth();
        btnfilterbyrangefilter();
        btnNofilter();
        cboEquipment();
        clickTableEquipment();
    }
    
    
    public void setTableColumns(){
        //drivers table
        dataMcDrivers = FXCollections.observableArrayList();
            equipment_issuance_tblc_driver.setCellValueFactory(new PropertyValueFactory("driver_id"));
            equipment_issuance_driver_tableview.setItems(dataMcDrivers);
            
            
            //eqipment tables;
         dataMcppEquipmentIsssuances = FXCollections.observableArrayList();
            equipment_issuance_tblc_date.setCellValueFactory(new PropertyValueFactory("date_issued"));
            equipment_issuance_tblc_equipment_id.setCellValueFactory(new PropertyValueFactory("equipment_id"));
            equipmen_issuance_tableview.setItems(dataMcppEquipmentIsssuances);    
            
    }
    
     
    
    public void setItemsdriver(){
            //items for driver;
            DatabaseConnection dbItems = new DatabaseConnection("root", "localhost", "eyefleet", "ramjorgeyao");
            ResultSet  rsitems = dbItems.setQuery("SELECT * FROM eyefleet.driver;");
            try {
                while(rsitems.next()){
                    dataMcDrivers.add(new DataMcDriver(rsitems.getString("driver_id")));
                    
                }
        } catch (Exception e) {
            System.out.print("ERROR ON MC PP ISSUANCE setItemsdriver: " + e);
            System.exit(0);
        }
        
    }
    
    
    public void setItemsEquipment(){
        //data items of equipment;
         DatabaseConnection dbItemsEquipment = new DatabaseConnection("root", "localhost", "eyefleet", "ramjorgeyao");
           ResultSet  rsItemsEquipment = dbItemsEquipment.setQuery("SELECT * FROM eyefleet.issuance");
           try {
               while(rsItemsEquipment.next()){
                   dataMcppEquipmentIsssuances.add(new DataMcppEquipmentIsssuance(rsItemsEquipment.getDate("date_issued"),
                                                                rsItemsEquipment.getString("equipment_id")));
                   
               }
        } catch (Exception e) {
            System.out.print("ERROR ON MC PP ISSUANCE setItemsEquipment: " + e);
            System.exit(0);
        }
    }
    
    
    public void setClickTabledriver(){
        equipment_issuance_driver_tableview.setOnMouseClicked((MouseEvent)->{
            //clear data;
            dataMcppEquipmentIsssuances.clear();
            
            //data of equipments;
             DataMcDriver dataMcDriverselect = (DataMcDriver)equipment_issuance_driver_tableview.getSelectionModel().getSelectedItem();
             DatabaseConnection dbclick = new DatabaseConnection("root", "localhost", "eyefleet", "ramjorgeyao");
                if(dataMcDriverselect !=null){
                        try {
                            ResultSet rsclick  = dbclick.setQuery("SELECT * FROM eyefleet.issuance WHERE driver_id ='"+ dataMcDriverselect.getDriver_id() +"'");
                                while(rsclick.next()){
                                    dataMcppEquipmentIsssuances.add(new DataMcppEquipmentIsssuance( 
                                                                                 rsclick.getDate("date_issued"),
                                                                                 rsclick.getString("equipment_id")));
                                    
                                }
                            
                    } catch (Exception e) {
                         System.out.print("ERROR ON MC PP ISSUANCE setClickTabledriver: " + e);
                         System.exit(0);
                    }
                }
        });
    }
    
    
    public void cbofilterbyMonth(){
        
        //add items on cbo ilter by month;
        equipment_issuance_cbo_filterby_month.getItems().add("January");
        equipment_issuance_cbo_filterby_month.getItems().add("Febuary");
        equipment_issuance_cbo_filterby_month.getItems().add("March");
        equipment_issuance_cbo_filterby_month.getItems().add("April");
        equipment_issuance_cbo_filterby_month.getItems().add("May");
        equipment_issuance_cbo_filterby_month.getItems().add("June");
        equipment_issuance_cbo_filterby_month.getItems().add("July");
        equipment_issuance_cbo_filterby_month.getItems().add("August");
        equipment_issuance_cbo_filterby_month.getItems().add("September");
        equipment_issuance_cbo_filterby_month.getItems().add("October");
        equipment_issuance_cbo_filterby_month.getItems().add("November");
        equipment_issuance_cbo_filterby_month.getItems().add("december");
        
       
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
    
    
    
    public void btnFilterbyMonth(){
         //filter the month and get the 
        equipment_issuance_btn_filterby_month_filter.setOnMouseClicked((MouseEvent)->{
            
            if(equipment_issuance_cbo_filterby_month.getValue()==null){
                controller.getMessage().message("ERROR ON MC Issuance", "Please Select a Month a Filter records;", "Back");
             }
            
            else{
                    //else;
                    try {
                         DatabaseConnection dbfilterbymonth = new DatabaseConnection("root", "localhost", "eyefleet", "ramjorgeyao");
                         ResultSet rsfilterbymonth = dbfilterbymonth.setQuery("SELECT issuance.date_issued, issuance.equipment_id"
                             + " FROM issuance WHERE month(date_issued) =" + getMonthValue(equipment_issuance_cbo_filterby_month.getValue().toString()));
                        
                        while(rsfilterbymonth.next()){
                            dataMcppEquipmentIsssuances.add(new DataMcppEquipmentIsssuance( 
                                                                         rsfilterbymonth.getDate("date_issued"),
                                                                         rsfilterbymonth.getString("equipment_id")));
                            
                        }
                        //clear data;
                    dataMcppEquipmentIsssuances.clear();
                    //refrresh table  equimpment;
                    setItemsEquipment();
                        
            } catch (Exception e) {
                 System.out.print("ERROR ON MC PP ISSUANCE btnFilterbyMonth: " + e);
                 System.exit(0);
            }
                    
                    
            }//end of if;
        
        });
    }
    
    
    
    public void btnfilterbyrangefilter(){
        //filter the records of the database;
        equipment_issuance_btn_filter_by_range_filter.setOnMouseClicked((MouseEvent)->{
             
            //set the value of the datepicker
             LocalDate date_from = equipment_issuance_from_datepicker.getValue();
             LocalDate date_to = equipment_issuance_to_datepicker.getValue();
             
             if(equipment_issuance_from_datepicker.getValue()==null){
                 controller.getMessage().message("ERROR ON MC Issuance", "Please Select a Date Fronm;", "Back");

              }
             
             else if(equipment_issuance_to_datepicker.getValue()==null){
                 controller.getMessage().message("ERROR ON MC Issuance", "Please Select a Date To;", "Back");
             }
             
             else{
             try {
                  //create database and query and get the date of its validity;
                DatabaseConnection dbbtnfilterbyrange = new DatabaseConnection("root", "localhost", "eyefleet", "ramjorgeyao");
                ResultSet rsbtnfilterbyrange = dbbtnfilterbyrange.setQuery("SELECT issuance.date_issued, issuance.equipment_id"
                        + " FROM issuance WHERE date_issued >='" + date_from.getYear()+1900+ "-" +date_from.getMonthValue()+1 +"-"+ date_from.getDayOfMonth() +"'"
                        + "AND date_issued <='"+ date_to.getYear()+1900 + "-" + date_to.getMonthValue()+1 +"-" + date_to.getDayOfMonth() +"'");
                
                while(rsbtnfilterbyrange.next()){
                    dataMcppEquipmentIsssuances.add(new DataMcppEquipmentIsssuance(rsbtnfilterbyrange.getDate("date_issued"),
                                                                 rsbtnfilterbyrange.getString("equipment_id")));
                    
                }
                
                //clear data;
                    dataMcppEquipmentIsssuances.clear();
                    //refrresh table  equimpment;
                    setItemsEquipment();
                 
            } catch (Exception e) {
                System.out.print("ERROR ON MC PP ISSUANCE btnfilterbyrangefilter: " + e);
                System.exit(0);
            }
                     
             }//end of if;
        });
    }
    
    
    public void btnNofilter(){
        //select all items from issuance;
        equipment_issuance_btn_No_filter_by_range.setOnMouseClicked((MouseEvent)->{
             DatabaseConnection dbbtnofilterbyrange = new DatabaseConnection("root", "localhost", "eyefleet", "ramjorgeyao");
             ResultSet rsnofilterbyrange = dbbtnofilterbyrange.setQuery("SELECT * FROM eyefleet.issuance");
             try {
                while(rsnofilterbyrange.next()){
                    dataMcppEquipmentIsssuances.add(new DataMcppEquipmentIsssuance(rsnofilterbyrange.getDate("date_issued"),
                                                                 rsnofilterbyrange.getString("equipment_id")));
                    
                }
                    //clear data;
                    dataMcppEquipmentIsssuances.clear();
                    //refrresh table  equimpment;
                    setItemsEquipment();
            } catch (Exception e) {
                System.out.print("ERROR ON MC PP ISSUANCE btnNofilter: " + e);
                System.exit(0);
            }
            
        });
        
    }
    
    public void cboEquipment(){
            //select all item from eqipment
            DatabaseConnection dbcboEquipment = new DatabaseConnection("root", "localhost", "eyefleet", "ramjorgeyao");
             ResultSet rscboEquipment = dbcboEquipment.setQuery("SELECT * FROM eyefleet.equipment");
            try {
                while(rscboEquipment.next()){
                    equipment_ssuance_cbo_equipment.getItems().add(rscboEquipment.getString("equipment_id"));
                }
        } catch (Exception e) {
                    System.out.print("ERROR ON MC PP ISSUANCE cboEquipment: " + e);
                    System.exit(0);
            
        }
            //get the description of the selected ewuipment id;
        equipment_ssuance_cbo_equipment.setOnAction((ActionEvent)->{
                    String cboselect;
                    cboselect = equipment_ssuance_cbo_equipment.getValue().toString();
                    ResultSet rscboequipemnt =dbcboEquipment.setQuery("SELECT * FROM eyefleet.equipment WHERE equipment_id ='"+ cboselect +"'");
                     try {
                        while(rscboequipemnt.next()){
                            equipment_issuance_text_description.setText(rscboequipemnt.getString("description"));
                        }
                    } catch (Exception e) {
                        System.out.print("ERROR ON MC PP ISSUANCE cboEquipment2: " + e);
                        System.exit(0);
                    }
                 
            });
        
    }
    
    
    
    public void clickTableEquipment(){
        equipmen_issuance_tableview.setOnMouseClicked((MouseEvent)->{
            DatabaseConnection  dbselect = new DatabaseConnection("root", "localhost", "eyefleet", "ramjorgeyao");
            DataMcppEquipmentIsssuance dataMcppEquipmentIsssuanceselect = (DataMcppEquipmentIsssuance)equipmen_issuance_tableview.getSelectionModel().getSelectedItem();
            
            if(dataMcppEquipmentIsssuanceselect != null){
                equipment_ssuance_cbo_equipment.setValue(dataMcppEquipmentIsssuanceselect.getEquipment_id());
                
            }
        
        });
    }
    
    
    
    
    public void btnSave(){
        equipment_issuance_btn_save.setOnMouseClicked((MouseEvent)->{
            DatabaseConnection dbSave = new DatabaseConnection("root", "localhost", "eyefleet", "ramjorgeyao");
//            dbSave.updateDatabase("INSERT INTO eyefleet.issuance(date_issued, equipment_id)" +
//                                       "VALUES('" + eq + "'"
//                                            "<{date_issued: }>");
        
        
        });
        
    }
    
    
    
    
    
    
    
    
    
    
    
    public class DataMcDriver{
            private String driver_id;
        
        public DataMcDriver(String driver_id) {
            this.driver_id = driver_id;
        }

        public String getDriver_id() {
            return driver_id;
        }

        public void setDriver_id(String driver_id) {
            this.driver_id = driver_id;
        }
        
        
    }
    
    
    public class DataMcppEquipmentIsssuance{
             private Date date_issued;
             private String equipment_id;
        public DataMcppEquipmentIsssuance( 
                                 Date date_issued,
                                 String equipment_id) {
            this.date_issued = date_issued;
            this.equipment_id = equipment_id;
        }

 
        public Date getDate_issued() {
            return date_issued;
        }

        public void setDate_issued(Date date_issued) {
            this.date_issued = date_issued;
        }

         

        public String getEquipment_id() {
            return equipment_id;
        }

        public void setEquipment_id(String equipment_id) {
            this.equipment_id = equipment_id;
        }
        
    }
    
    
    
    
}
