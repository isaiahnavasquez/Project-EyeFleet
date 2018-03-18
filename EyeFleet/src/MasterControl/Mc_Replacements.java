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
import javax.swing.JOptionPane;

/**
 *
 * @author jorge
 */
public class Mc_Replacements {

    private LayoutController controller;
   
    private TableView mc_replacement_tableview;
    private TableColumn mc_replacement_tblc_date;
    private TableColumn mc_replacement_tblc_truck_id;
    private TableColumn mc_replacement_tblc_truck_part;
    private TableColumn mc_replacement_tblc_desription;
    private TableColumn mc_replacement_tblc_location;
    
    //filter by month
    private ComboBox mc_replacements_cbo_filterbyMonth;
    private Label mc_replacements_btn_filter_by_month;
    
    //filter by range
    private DatePicker mc_replacements_from_datepicker;
    private DatePicker mc_replacements_to_datepicker;
    
    //combobox;
    private Label mc_replacements_btn_filter_by_range;
    private Label mc_replacements_btn_No_filter_by_range;
    
    private ComboBox mc_replacements_cbo_truck;
    private ComboBox mc_replacements_cbo_truck_part;
    
    private TextField mc_replacements_txt_description;
    private TextField mc_replacements_txt_location;
    
    private DatePicker mc_replacement_text_Date_datepcker;
    
    //btns
    private Label mc_replacement_btn_save;
    private Label mc_replacement_btn_edit;
    private Label mc_replacement_btn_delete;
    
    private ObservableList<DataMcReplacements> dataMcReplacementses;
    
    private String select ="";
    
    //Error traps class:
     ErrorTraps errorTrapping = new ErrorTraps();
    
    
    public Mc_Replacements(LayoutController controller_param) {
        this.controller = controller_param;
        
        this.mc_replacement_tableview = controller_param.getMc_replacement_tableview();
        this.mc_replacement_tblc_date = controller_param.getMc_replacement_tblc_date();
        this.mc_replacement_tblc_truck_id = controller_param.getMc_replacement_tblc_truck_id();
        this.mc_replacement_tblc_truck_part = controller_param.getMc_replacement_tblc_truck_part();
        this.mc_replacement_tblc_desription = controller_param.getMc_replacement_tblc_desription();
        this.mc_replacement_tblc_location = controller_param.getMc_replacement_tblc_location();
        
        //Filter by Month
        this.mc_replacements_cbo_filterbyMonth = controller_param.getMc_replacements_cbo_filterbyMonth();
        this.mc_replacements_btn_filter_by_month = controller_param.getMc_replacements_btn_filter_by_month();
        
        //Filter by Range
        this.mc_replacements_from_datepicker = controller_param.getMc_replacements_from_datepicker();
        this.mc_replacements_to_datepicker = controller_param.getMc_replacements_to_datepicker();
  
        this.mc_replacements_btn_filter_by_range = controller_param.getMc_replacements_btn_filter_by_range();
        this.mc_replacements_btn_No_filter_by_range = controller_param.getMc_replacements_btn_No_filter_by_range();
        
        //replacements fields;
        this.mc_replacements_cbo_truck = controller_param.getMc_replacements_cbo_truck();
        this.mc_replacements_cbo_truck_part = controller.getMc_replacements_cbo_truck_part();
        
        this.mc_replacements_txt_description = controller_param.getMc_replacements_txt_description();
        this.mc_replacements_txt_location = controller_param.getMc_replacements_txt_location();
        
        this.mc_replacement_text_Date_datepcker = controller_param.getMc_replacement_text_Date_datepcker();
        
        this.mc_replacement_btn_save = controller_param.getMc_replacement_btn_save();
        this.mc_replacement_btn_edit = controller_param.getMc_replacement_btn_edit();
        this.mc_replacement_btn_delete = controller_param.getMc_replacement_btn_delete();
        
        //initialized modules
        initializeModules();
        
    }
    
    
    public void initializeModules(){
        settablelumnsReplacements();
        cboTruck();
        cbotruckPart();
        cbofilterbyMonth();
        btnfilterbyMonth();
        
        
        
        //btnfilter by range;
        btnFilterbyRangeFilter();
        btnFilterbyRangeNoFilter();
        
        //btn 
        btnSave();
          
       //click tableview
       clicktable();
        
        //btnedit enabled
        btnEdit();
        
        //refresh btn
        btnRefresh();
    }
    
    public void settablelumnsReplacements(){
        dataMcReplacementses = FXCollections.observableArrayList();
        mc_replacement_tblc_date.setCellValueFactory(new PropertyValueFactory("replacement_date"));
        mc_replacement_tblc_truck_id.setCellValueFactory(new PropertyValueFactory("truck_id"));
        mc_replacement_tblc_truck_part.setCellValueFactory(new PropertyValueFactory("truck_part_id"));
        mc_replacement_tblc_desription.setCellValueFactory(new PropertyValueFactory("description"));
        mc_replacement_tblc_location.setCellValueFactory(new PropertyValueFactory("location"));
        mc_replacement_tableview.setItems(dataMcReplacementses);
    }
    
    
    
    public void setItems(){
            //cler data;
           dataMcReplacementses.clear();
            
        //set items data
          DatabaseConnection dbsetitems = new DatabaseConnection("root", "localhost", "eyefleet", "ramjorgeyao");
          
          ResultSet rsselect = dbsetitems.setQuery("SELECT replacement.replacement_date, replacement.truck_id, replacement.truck_part_id, replacement.replacement_id, replacement.location, "
                                        + "(select truck_part.description from truck_part where truck_part.truck_part_id = replacement.truck_part_id) as description "
                                        + " from replacement"); 
           try {
                while(rsselect.next()){
                    dataMcReplacementses.add(new DataMcReplacements(rsselect.getString("location"),
                            rsselect.getDate("replacement_date"),
                            rsselect.getString("truck_id"),
                            rsselect.getString("truck_part_id"),
                            rsselect.getString("description"),
                            rsselect.getString("replacement_id")));//need confirmation
                 }
                
            } catch (Exception e) {
             System.out.print("ERROR ON MODULE MCTRUCK REPLACEMENT setItems " +e);
             }
       
    }
    
    public void cboTruck(){
            //truck items
            DatabaseConnection dbtruck = new DatabaseConnection("root", "localhost", "eyefleet", "ramjorgeyao");
            ResultSet rstruck = dbtruck.setQuery("SELECT * FROM eyefleet.truck");
                try {
                    while(rstruck.next()){
                        mc_replacements_cbo_truck.getItems().add(rstruck.getString("truck_id"));
                     }
            } catch (Exception e) {
                System.out.print( "ERROR ON MODULE MCTRUC2K REPLACEMENT cboTruck "+e);
             }
        
        
    }
    
    public void cbotruckPart(){
      
        try {
              //CREATE DATABASE AND RESULSET;
        DatabaseConnection dbreplacementcon = new DatabaseConnection("root", "localhost", "eyefleet", "ramjorgeyao");
        ResultSet rs_select = dbreplacementcon.setQuery("SELECT * FROM eyefleet.truck_part");
             
             //GET THE RESULSET VALUE;
            while(rs_select.next()){
                mc_replacements_cbo_truck_part.getItems().add(rs_select.getString("truck_part_id"));
            }
        } catch (Exception e) {
             System.out.print("ERROR ON MODULE MCTRUCK REPLACEMENT cbotruckPart " +e);
         }
        
        //select description from truck part   and get the description 
       mc_replacements_cbo_truck_part.setOnAction((ActionEvent)->{
                
            try {
                String cboselect;
                cboselect = mc_replacements_cbo_truck_part.getValue().toString();
                DatabaseConnection dbtruckpart = new DatabaseConnection("root", "localhost", "eyefleet", "password");
                ResultSet rstruckpart = dbtruckpart.setQuery("SELECT * FROM eyefleet.truck_part WHERE truck_part_id ='" + cboselect + "'");
                        
                while(rstruckpart.next()){
                            mc_replacements_txt_description.setText(rstruckpart.getString("description"));
                        }
                } catch (Exception e) {
                     
                }
            
            });
        
    }    
    
    
    
    
    
    public void cbofilterbyMonth(){
        
        //add items on cbo ilter by month;
        mc_replacements_cbo_filterbyMonth.getItems().add("January");
        mc_replacements_cbo_filterbyMonth.getItems().add("Febuary");
        mc_replacements_cbo_filterbyMonth.getItems().add("March");
        mc_replacements_cbo_filterbyMonth.getItems().add("April");
        mc_replacements_cbo_filterbyMonth.getItems().add("May");
        mc_replacements_cbo_filterbyMonth.getItems().add("June");
        mc_replacements_cbo_filterbyMonth.getItems().add("July");
        mc_replacements_cbo_filterbyMonth.getItems().add("August");
        mc_replacements_cbo_filterbyMonth.getItems().add("September");
        mc_replacements_cbo_filterbyMonth.getItems().add("October");
        mc_replacements_cbo_filterbyMonth.getItems().add("November");
        mc_replacements_cbo_filterbyMonth.getItems().add("december");
        
       
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
         
                
        //function for getting the resulst of the converted value of the getMonthValue method;
        mc_replacements_btn_filter_by_month.setOnMouseClicked((MouseEvent)->{
            //data clear;
            dataMcReplacementses.clear();
            
            if(mc_replacements_cbo_filterbyMonth.getValue()==null){
                                  controller.getMessage().shortToast("Please select a Month to filter the records:", 2000);

              }
            
            else{
            //else
            
            DatabaseConnection dbfilterbymonth = new DatabaseConnection("root", "localhost", "eyefleet", "ramjorgeyao");
            ResultSet rsfilterbymonth = dbfilterbymonth.setQuery("SELECT replacement.replacement_date, replacement.truck_id, replacement.truck_part_id, replacement.replacement_id, replacement.location, "
                                        + "(select truck_part.description from truck_part where truck_part.truck_part_id = replacement.truck_part_id) as description "
                                        + " from replacement "
                                        + " WHERE month(replacement_date) = " + getMonthValue(mc_replacements_cbo_filterbyMonth.getValue().toString()) + " and status = 'replaced'");
             
             
            try {
                while(rsfilterbymonth.next()){
                     dataMcReplacementses.add(new DataMcReplacements(rsfilterbymonth.getString("location"),
                            rsfilterbymonth.getDate("replacement_date"),
                            rsfilterbymonth.getString("truck_id"),
                            rsfilterbymonth.getString("truck_part_id"),
                            rsfilterbymonth.getString("description"),
                            rsfilterbymonth.getString("replacement_id")));
                     
                }
                
                 
            } catch (Exception e) {
             System.out.print("ERROR ON MODULE MCTRUCK REPLACEMENT btnfilterbyMonth " +e);
             
            }
            } //end of if;
        });
      }    
        
      
      
    
  
    public void btnFilterbyRangeFilter(){
                  
        //filter the records of the database;
        mc_replacements_btn_filter_by_range.setOnMouseClicked((MouseEvent)->{
              
            //set the value of the datepicker
            LocalDate date_from = mc_replacements_from_datepicker.getValue();
            LocalDate date_to = mc_replacements_to_datepicker.getValue();
             
            if(mc_replacements_from_datepicker.getValue()==null){
                                                  controller.getMessage().shortToast("Please select a Date From:", 2000);

              }
            else if(mc_replacements_to_datepicker.getValue()==null){
                                                                  controller.getMessage().shortToast("Please select a Date To:", 2000);

              }
           
            else{
            //else
            try {
                
                //clear the data;..
                dataMcReplacementses.clear();
                
                 //create database and query
            DatabaseConnection dbfiter = new DatabaseConnection("root", "localhost", "eyefleet", "ramjorgeyao");
            ResultSet rsfilter = dbfiter.setQuery("SELECT replacement.replacement_date, replacement.truck_id, replacement.truck_part_id, replacement.replacement_id, replacement.location, " +
                            "(select truck_part.description from truck_part where truck_part.truck_part_id = replacement.truck_part_id) as description  from replacement " +
                              " WHERE replacement_date >='" +date_from.getYear()+ "-" +date_from.getMonthValue() +"-"+ date_from.getDayOfMonth() +"'" +
                                 " AND replacement_date <= '"+ date_to.getYear()+ "-" +date_to.getMonthValue() +"-" +date_to.getDayOfMonth()+"'" + "and status = 'replaced' ");
            
                //get the rcords  //System.out.println("queried"); 
                while(rsfilter.next()){
                      dataMcReplacementses.add(new DataMcReplacements(rsfilter.getString("location"),
                            rsfilter.getDate("replacement_date"),
                            rsfilter.getString("truck_id"),
                            rsfilter.getString("truck_part_id"),
                            rsfilter.getString("description"),
                            rsfilter.getString("replacement_id")));
                 }
                      
            } catch (Exception e) {
                System.out.print("ERROR ON MODULE MCTRUCK REPLACEMENT btnFilterbyRangeFilter " +e);
                 
            }
            }//end oof if;
        });
        
    }
    
     public void btnFilterbyRangeNoFilter(){
        mc_replacements_btn_No_filter_by_range.setOnMouseClicked((MouseEvent)->{
                //select all items from replacements
                  DatabaseConnection dbnofilter = new DatabaseConnection("root", "localhost", "eyefleet", "ramjorgeyao");
                  ResultSet rsnofilter = dbnofilter.setQuery("SELECT replacement.replacement_date, replacement.truck_id, replacement.truck_part_id, replacement.replacement_id, replacement.location, "
                                        + "(select truck_part.description from truck_part where truck_part.truck_part_id = replacement.truck_part_id) as description "
                                        + " from replacement WHERE status = 'replaced'");
                  try {
                      while(rsnofilter.next()){
                            dataMcReplacementses.add(new DataMcReplacements(rsnofilter.getString("location"),
                            rsnofilter.getDate("replacement_date"),
                            rsnofilter.getString("truck_id"),
                            rsnofilter.getString("truck_part_id"),
                            rsnofilter.getString("description"),
                            rsnofilter.getString("replacement_id")));
                      }
                      
                      //clear all items;
                 dataMcReplacementses.clear();;
                //efresh tabl;
                setItems();
                
            } catch (Exception e) {
                   System.out.print("ERROR ON MODULE MCTRUCK REPLACEMENT btnFilterbyRangeNoFilter " +e);
             }
        
        });
        
    }
    
    
    
    
     
     public void btnSave(){
         //function for Button Save;
         mc_replacement_btn_save.setOnMouseClicked((MouseEvent)->{
             
             boolean exist = false;
             DatabaseConnection dbcbo = new DatabaseConnection("root", "localhost", "eyefleet", "ramjorgeyao");
             ResultSet rscbo = dbcbo.setQuery("SELECT * FROM eyefleet.replacement");
             try {
                while(rscbo.next()){
                 if(mc_replacements_cbo_truck.getValue().equals(rscbo.getString("truck_id")))  {
                    exist = true;
                     break;
            }
                 }
                
                 } catch (Exception e) {
             }
             
             
              if(exist ==true){
            controller.getMessage().message("Error on Adding", "Truck Id  already Exist::", "Back");
           }//end of error trps;
             
             
              
              else if(mc_replacements_cbo_truck.getValue()==null){
                                                                    controller.getMessage().shortToast("Please select a Truck Id", 2000);

                }
             else if(mc_replacements_cbo_truck_part.getValue()==null){
                                                                                     controller.getMessage().shortToast("Please select a Truck part", 2000);

               }
             else if(mc_replacements_txt_location.getText().isEmpty() || errorTrapping.isNumerical(mc_replacements_txt_location.getText())){
                                                                                     controller.getMessage().shortToast("Please select a Location", 2000);
 
                  
             }
             else  if(mc_replacement_text_Date_datepcker.getValue()==null){
                                                                                     controller.getMessage().shortToast("Please input a Date", 2000);

                }
              
             
              
             else{
             //else
             
                    try {
                        //INSERT NEWRECORdS:
                     DatabaseConnection dbSave = new DatabaseConnection("root", "localhost", "eyefleet", "password");
                     dbSave.updateDatabase("INSERT INTO eyefleet.replacement(location, replacement_date, truck_id, truck_part_id, status)"
                                                                +  "VALUES('"  + mc_replacements_txt_location.getText().toString() + "', "
                                                                               + "'"+ mc_replacement_text_Date_datepcker.getValue().toString()+"',"
                                                                               + "'"+ mc_replacements_cbo_truck.getValue().toString()+"',"
                                                                               + "'"+ mc_replacements_cbo_truck_part.getValue().toString()+"',"
                                                                               + "'"+ "replaced"+ "')");
                                                                              
                                                                                 //clear data
                                                                                    dataMcReplacementses.clear();
                                                                                //refresh table
                                                                                    setItems();
                     
             } catch (Exception e) {
                   System.out.print("ERROR ON MODULE MCTRUCK REPLACEMENT btnSave " +e);
              }
             }//end of if;
         });
     }
     
     
     
     
    
    
    public void btnRefresh(){
        //delete button
        mc_replacement_btn_delete.setOnMouseClicked((MouseEvent)->{
                try {
                    
                    //clear data
                     dataMcReplacementses.clear();
                   //refresh table
                     setItems();
                     
                     mc_replacements_cbo_truck.setValue(null);
                     mc_replacements_cbo_truck_part.setValue(null);
                    mc_replacements_txt_description.clear();
                    mc_replacements_txt_location.clear();
                    mc_replacement_text_Date_datepcker.setValue(null);
            } catch (Exception e) {
            }
        });
    }
    
    public void clicktable(){
         // click table
        mc_replacement_tableview.setOnMouseClicked((MouseEvent)->{
               DataMcReplacements dataMcReplacementsselect = (DataMcReplacements)mc_replacement_tableview.getSelectionModel().getSelectedItem();
               if(dataMcReplacementsselect != null){
                   mc_replacements_cbo_truck.setValue(dataMcReplacementsselect.getTruck_id());
                   controller.getMc_replacements_cbo_truck_part().setValue(dataMcReplacementsselect.getTruck_part_id());
                   mc_replacements_txt_description.setText(dataMcReplacementsselect.getDescription());
                   mc_replacements_txt_location.setText(dataMcReplacementsselect.getLocation());
                   mc_replacement_text_Date_datepcker.setValue(LocalDate.of(dataMcReplacementsselect.getReplacement_date().getYear()+1900, dataMcReplacementsselect.getReplacement_date().getMonth()+1, dataMcReplacementsselect.getReplacement_date().getDate()));
               }
           
        });
        
    }
    
     
    public void btnEdit(){
         
            //EDIT OR UPDATE THE RECORDS 
            mc_replacement_btn_edit.setOnMouseClicked((MouseEvent)->{
                
                
              if(mc_replacements_cbo_truck.getValue()==null){
                                                                                                       controller.getMessage().shortToast("Please select a Truck Id", 2000);

                }
             else if(mc_replacements_cbo_truck_part.getValue()==null){
                                                                                                      controller.getMessage().shortToast("Please select  Truck part", 2000);

               }
             else if(mc_replacements_txt_location.getText().isEmpty() || errorTrapping.isNumerical(mc_replacements_txt_location.getText())){
                                                                                                      controller.getMessage().shortToast("Please input a Location", 2000);

                  
             }
             else  if(mc_replacement_text_Date_datepcker.getValue()==null){
                                                                                                      controller.getMessage().shortToast("Please input a Date", 2000);

                }
                
             else{
                try {
                     DataMcReplacements dataMcReplacementsselect = (DataMcReplacements)mc_replacement_tableview.getSelectionModel().getSelectedItem();
                     DatabaseConnection dbEdit = new DatabaseConnection("root", "localhost", "eyefleet", "ramjorgeyao");
                     dbEdit.updateDatabase("UPDATE eyefleet.replacement SET truck_id ='" + mc_replacements_cbo_truck.getValue().toString() +"', "
                                + "truck_part_id ='"+ mc_replacements_cbo_truck_part.getValue().toString()+"', "             
                                + "location ='" + mc_replacements_txt_location.getText().toString()+"', "
                                + "replacement_date ='"+ mc_replacement_text_Date_datepcker.getValue().toString()+"'"
                                + " WHERE replacement_id = "+ dataMcReplacementsselect.getReplacement_id());
            
                    //clear data
                     dataMcReplacementses.clear();
                   //refresh table
                     setItems();
            
                } catch (Exception e) {
                }
                
             }//end of if;
            });
         
           
    }
        
    
    
    
    
    
    
    public class DataMcReplacements{
         private Date replacement_date;
         private String truck_id;
         private String truck_part_id;
         private String description;
         private String location;
         private String replacement_id;
        
         
         
          
        public DataMcReplacements(String location,
                                Date replacement_date,
                                String truck_id,
                                String truck_part_id,
                                String description,
                                String replacement_id
                                ) {
            
            this.location = location;
            this.replacement_date = replacement_date;
            this.truck_id = truck_id;
            this.truck_part_id = truck_part_id;
            this.description = description;
            this.replacement_id = replacement_id;
             
        }

        public Date getReplacement_date() {
            return replacement_date;
        }

        public void setReplacement_date(Date replacement_date) {
            this.replacement_date = replacement_date;
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

        public String getLocation() {
            return location;
        }

        public void setLocation(String location) {
            this.location = location;
        }

        public String getReplacement_id() {
            return replacement_id;
        }

        public void setReplacement_id(String replacement_id) {
            this.replacement_id = replacement_id;
        }
        
        
  
    }
    
}
