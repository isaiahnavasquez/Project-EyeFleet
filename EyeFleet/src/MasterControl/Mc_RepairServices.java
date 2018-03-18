/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package MasterControl;

import eyefleet.DatabaseConnection;
import eyefleet.DatabaseConnection;
import eyefleet.DatabaseConnection;
import eyefleet.ErrorTraps;
import eyefleet.LayoutController;
import eyefleet.LayoutController;
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
import javafx.scene.control.TextArea;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

/**
 *
 * @author jorge
 */
public class Mc_RepairServices {

    private LayoutController controller;
    
    private TableView mc_repairservices_tableview;
    private TableColumn mc_repairservices_tblc_date;
    private TableColumn mc_repairserices_tblc_truck;
    
    private ComboBox mc_repairservices_filterbymonth_cbo_truck;
    
    private Label mc_repairservices_btn_filterby_month_filter;
    
    private DatePicker mc_repairservices_from_datepicker;
    private DatePicker mc_repairservices_to_datepicker;
    
    private Label mc_repairservices_btn_filter_by_range;
    private Label mc_repairservices_btn_No_filter_by_range;
    
    private DatePicker mc_repairservices_date_datepicker;
    
    private ComboBox mc_repairservices_cbo_truck;
    
    private TextArea   mc_repairservices_desciption_remarks;
    
    private Label mc_repairservices_btn_save;
    private Label mc_repairservices_btn_edit;
    private Label mc_repairservices_btn_refresh;
    
    private ObservableList<DataMcRepairServices> dataMcRepairServiceses;
    
   LocalDate date;
   
   //error trapos
   ErrorTraps errorTrapping = new ErrorTraps();

    
    public Mc_RepairServices(LayoutController controller_param) {
        
        this.controller = controller_param;
        
        this.mc_repairservices_tableview = controller_param.getMc_repairservices_tableview();
        this.mc_repairservices_tblc_date = controller_param.getMc_repairservices_tblc_date();
        this.mc_repairserices_tblc_truck = controller_param.getMc_repairserices_tblc_truck();
        
        this.mc_repairservices_filterbymonth_cbo_truck = controller_param.getMc_repairservices_filterbymonth_cbo_truck();
     
        this.mc_repairservices_btn_filterby_month_filter = controller_param.getMc_repairservices_btn_filterby_month_filter();
        
        this.mc_repairservices_from_datepicker = controller_param.getMc_repairservices_from_datepicker();
        this.mc_repairservices_to_datepicker = controller_param.getMc_repairservices_to_datepicker();
        
        this.mc_repairservices_btn_filter_by_range = controller_param.getMc_repairservices_btn_filter_by_range();
        this.mc_repairservices_btn_No_filter_by_range = controller_param.getMc_repairservices_btn_No_filter_by_range();
        
        this.mc_repairservices_date_datepicker = controller_param.getMc_repairservices_date_datepicker();
        
        this.mc_repairservices_cbo_truck = controller_param.getMc_repairservices_cbo_truck();
        
        this.mc_repairservices_desciption_remarks = controller_param.getMc_repairservices_desciption();
        
        this.mc_repairservices_btn_save = controller_param.getMc_repairservices_btn_save();
        this.mc_repairservices_btn_edit = controller_param.getMc_repairservices_btn_edit();
        this.mc_repairservices_btn_refresh = controller_param.getMc_repairservices_btn_delete();
        
        //initialized modules;
        
        initializedmodules();
    }
    
    
    public void initializedmodules(){
        settablecolumns();
        cbofilterbyMonth();
        btnfilterbymomnth();
        
        //btn filter by range
        btnFilterbyRangeFilter();
        
        //btn no filter
        btnNofilter();
        
        //date
        datepickerValue();
        
        //cbo truck items
        cbtruck();
        
        //btn save
        btnSave();
        
        //btnrefresh
        btnRefresh();
        
        
        //btnEdit
         btnEdit();
        
        //click table
        clicktable();
    }
    
    
    public void settablecolumns(){
        dataMcRepairServiceses = FXCollections.observableArrayList();
        mc_repairservices_tblc_date.setCellValueFactory(new PropertyValueFactory("date"));
        mc_repairserices_tblc_truck.setCellValueFactory(new PropertyValueFactory("truck_id"));
        mc_repairservices_tableview.setItems(dataMcRepairServiceses);
    }
    
    
    public void setitems(){
         //CREATE DATABASE AND RESULTSET;
             DatabaseConnection dbselect = new DatabaseConnection("root", "localhost", "eyefleet", "ramjorgeyao");
             ResultSet rs_select = dbselect.setQuery("SELECT * FROM eyefleet.repair_service");
        
        try {
            //GET THE VALUE OF THE RESULSET;
            while(rs_select.next()){
                //ADD THE TABLE NEW DATA IN OBSERVALELIST;
                dataMcRepairServiceses.add(new DataMcRepairServices( 
                                                                     rs_select.getDate("date"),
                                                                     rs_select.getString("truck_id"),
                                                                     rs_select.getString("remarks"),
                                                                     rs_select.getString("status"),
                                                                     rs_select.getString("repair_service_id")));
            }
        } catch (Exception e) {
            System.out.print("ERROR ON Master control  REPAIRSERVICES setitems: " +e);
            System.exit(0);
        }
        
        
    }
    
    
    public void cbofilterbyMonth(){
        
        //add items on cbo ilter by month;
        mc_repairservices_filterbymonth_cbo_truck.getItems().add("January");
        mc_repairservices_filterbymonth_cbo_truck.getItems().add("February");
        mc_repairservices_filterbymonth_cbo_truck.getItems().add("March");
        mc_repairservices_filterbymonth_cbo_truck.getItems().add("April");
        mc_repairservices_filterbymonth_cbo_truck.getItems().add("May");
        mc_repairservices_filterbymonth_cbo_truck.getItems().add("June");
        mc_repairservices_filterbymonth_cbo_truck.getItems().add("July");
        mc_repairservices_filterbymonth_cbo_truck.getItems().add("August");
        mc_repairservices_filterbymonth_cbo_truck.getItems().add("September");
        mc_repairservices_filterbymonth_cbo_truck.getItems().add("October");
        mc_repairservices_filterbymonth_cbo_truck.getItems().add("November");
        mc_repairservices_filterbymonth_cbo_truck.getItems().add("December");
        
       
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
    
    //underconatruction need assistment;
    public void btnfilterbymomnth(){
        //get the filter month 
        mc_repairservices_btn_filterby_month_filter.setOnMouseClicked((MouseEvent)->{
            //ERROR TRAPPING
            
             //clear data;
               dataMcRepairServiceses.clear();
            
           if(mc_repairservices_filterbymonth_cbo_truck.getValue()==null){
                            controller.getMessage().shortToast( "Please select a Month To filter:", 2000);

           }
            
           else{
            //else
            try {
                 DatabaseConnection dbfilterbymonth = new DatabaseConnection("root", "localhost", "eyefleet", "ramjorgeyao");
                    ResultSet rsfilterbymonth = dbfilterbymonth.setQuery("SELECT  repair_service.date, repair_service.truck_id, repair_service.remarks, repair_service.status, repair_service.repair_service_id  from  eyefleet.repair_service"
                                                                       + " WHERE month(date) = " + getMonthValue(mc_repairservices_filterbymonth_cbo_truck.getValue().toString())
                                                                       + " and status = 'repaired'");
                 
                
                while(rsfilterbymonth.next()){
                    dataMcRepairServiceses.add(new DataMcRepairServices(rsfilterbymonth.getDate("date"), 
                                                                        rsfilterbymonth.getString("truck_id"),
                                                                        rsfilterbymonth.getString("remarks"),
                                                                        rsfilterbymonth.getString("status"),
                                                                        rsfilterbymonth.getString("repair_service_id")));
                    
                }
                
               
                //refreshtable
               // setitems();
                
                
            } catch (Exception e) {
                System.out.print("ERROR ON MODULE MC MCTRUCK repaire services btnfilterbymomnth " +e);
                System.exit(0);
            }
                    }//end of if;
        });
    }
    
    
    //get the value of the filtered month
    public void btnFilterbyRangeFilter(){
          mc_repairservices_btn_filter_by_range.setOnMouseClicked((MouseEvent)->{
             LocalDate date_from = mc_repairservices_from_datepicker.getValue();
             LocalDate date_to = mc_repairservices_to_datepicker.getValue();
             
             //ERROR TRAPPING
             if(mc_repairservices_to_datepicker.getValue()==null){
                                             controller.getMessage().shortToast( "Please select a Date To:", 2000);

               }
             
             else if(mc_repairservices_from_datepicker.getValue()==null){
                                             controller.getMessage().shortToast( "Please select a Date From:", 2000);

                }
             
             else{
             //else
           
            try {
                 //clear data
                  dataMcRepairServiceses.clear();
                
                   //CALCULATE THE DATE;
                 DatabaseConnection dbfilter = new DatabaseConnection("root", "localhost", "eyefleet", "ramjorgeyao");
                 ResultSet rsfilter = dbfilter.setQuery("SELECT  repair_service.date, repair_service.truck_id, repair_service.remarks, repair_service.status, repair_service.repair_service_id  from  eyefleet.repair_service"
                         + " WHERE  date >= '" + date_from.getYear()+ "-" +date_from.getMonthValue()+"-"+ date_from.getDayOfMonth() +"'" +
                                                    " AND  date <= '"+ date_to.getYear()+ "-" + date_to.getMonthValue() +"-" +date_to.getDayOfMonth() + "' and status = 'repaired'" );
               
                  
                 while(rsfilter.next()){
                    dataMcRepairServiceses.add(new DataMcRepairServices(rsfilter.getDate("date"), 
                                                                        rsfilter.getString("truck_id"),
                                                                        rsfilter.getString("remarks"),
                                                                        rsfilter.getString("status"),
                                                                        rsfilter.getString("repair_service_id")));
                }
                
                                                                       
                                                                        
             } catch (Exception e) {
                 System.out.print("ERROR ON MODULE MC MCTRUCK repaire services btnFilterbyRangeFilter " +e);
                System.exit(0);
            }
             
             };//end of if
        });
    }
    
    
    public void btnNofilter(){
        //view all items 
        mc_repairservices_btn_No_filter_by_range.setOnMouseClicked((MouseEvent)->{
             try {
                  DatabaseConnection dbnofilter = new DatabaseConnection("root", "localhost", "eyefleet", "ramjorgeyao");
                  ResultSet rsnofilter = dbnofilter.setQuery("SELECT * FROM eyefleet.repair_service WHERE status = 'repaired'");
                 
                while(rsnofilter.next()){
                        dataMcRepairServiceses.add(new DataMcRepairServices(rsnofilter.getDate("date"),
                                                                            rsnofilter.getString("truck_id"),
                                                                            rsnofilter.getString("remarks"),
                                                                            rsnofilter.getString("status"),
                                                                            rsnofilter.getString("repair_service_id")));
                }
                //data clear
                dataMcRepairServiceses.clear();
                //refresh table
                setitems();
            } catch (Exception e) {
            }
         
        });
    }
    
    
    //get the value of the date
    public void datepickerValue(){
              mc_repairservices_date_datepicker.setOnAction((ActionEvent)->{
              date = mc_repairservices_date_datepicker.getValue();
        
        });
    }
    
    //get the value of the cbo truck
    public void cbtruck(){  
                 try {
                     DatabaseConnection dbCbotruck = new DatabaseConnection("root", "localhost", "eyefleet", "ramjorgeyao");
                     ResultSet rscbotruck = dbCbotruck.setQuery("SELECT * FROM eyefleet.truck ");
                    
                    while(rscbotruck.next()){
                            mc_repairservices_cbo_truck.getItems().add(rscbotruck.getString("truck_id"));
                        
                    }
            } catch (Exception e) {
                 System.out.print("ERROR ON MODULE MC MCTRUCK repaire services cbtruck " +e);
                 System.exit(0);
            }
           
    }
    
    
    
    
    
    //insert and save
    public void btnSave(){
        mc_repairservices_btn_save.setOnMouseClicked((MouseEvent)->{
            //error traps 
            boolean exist = false;
             DatabaseConnection dbcbo = new DatabaseConnection("root", "localhost", "eyefleet", "ramjorgeyao");
             ResultSet rscbo = dbcbo.setQuery("SELECT * FROM eyefleet.repair_service;");
             try {
                while(rscbo.next()){
                 if(mc_repairservices_cbo_truck.getValue().equals(rscbo.getString("truck_id")))  {
                    exist = true;
                     break;
            }
                 }
                
                 } catch (Exception e) {
             }
             
             
              if(exist ==true){
            controller.getMessage().shortToast("Truck Id  already Exist::", 2000);
           }//end of error trps;
            
            
            
            
              else if(mc_repairservices_date_datepicker.getValue()==null){
                                                               controller.getMessage().shortToast( "Please select a Date::", 2000);

              }
            else if(mc_repairservices_cbo_truck.getValue()==null){
                                                             controller.getMessage().shortToast( "Please select a Truck:", 2000);

                 
            }
            else if(mc_repairservices_desciption_remarks.getText().isEmpty() || errorTrapping.isNumerical(mc_repairservices_desciption_remarks.getText()) ){
                                                            controller.getMessage().shortToast( "Please provide description:", 2000);

 
            }
            
            else{
            //ELES;
            try {
                DatabaseConnection dbSave = new DatabaseConnection("root", "localhost", "eyefleet", "ramjorgeyao");
                dbSave.updateDatabase("INSERT INTO eyefleet.repair_service(date, truck_id, remarks,status)"
                                        + "VALUES('" + mc_repairservices_date_datepicker.getValue() +"', "
                                        + "'" + mc_repairservices_cbo_truck.getValue().toString()+ "', "
                                        + "'" + mc_repairservices_desciption_remarks.getText().toString()+ "', "
                                        + "'" + "repaired" +"')");
            
                //clear data;
                dataMcRepairServiceses.clear();
                //refresh table
                setitems();
                
        
            } catch (Exception e) {
                System.out.print("ERROR ON MODULE MC MCTRUCK repaire services btnSave " +e);
                System.exit(0);
            }
            };
        });
    }
    
                
    
    public void btnEdit(){
        //UPDATE OR EDIT THE DATA;
            mc_repairservices_btn_edit.setOnMouseClicked((MouseEvent)->{
                try {
                    DataMcRepairServices dataMcRepairServicebtnEdit = (DataMcRepairServices)mc_repairservices_tableview.getSelectionModel().getSelectedItem();
                    DatabaseConnection dbEdit = new DatabaseConnection("root", "localhost", "eyefleet", "ramjorgeyao");
                    dbEdit.updateDatabase("UPDATE eyefleet.repair_service SET date='"+ mc_repairservices_date_datepicker.getValue().toString()+"', "
                                                                      + "truck_id ='"+ mc_repairservices_cbo_truck.getValue().toString()+"', "
                                                                      + "remarks ='"+ mc_repairservices_desciption_remarks.getText().toString()+"'"
                                                                      + " WHERE repair_service_id ="+ dataMcRepairServicebtnEdit.getRepair_service_id());
                                                   //clear data
                                                      dataMcRepairServiceses.clear();
                                                   //refresh table
                                                      setitems();
                } catch (Exception e) {
                    
                }
            });
                                                            
    }
    
    
    
    
    
    
    //delete value
    public void btnRefresh(){
        mc_repairservices_btn_refresh.setOnMouseClicked((MouseEvent)->{
            try {
                              //clear data;
                                  dataMcRepairServiceses.clear();
                                  //clear fileds
                                  mc_repairservices_desciption_remarks.clear();
                                 mc_repairservices_date_datepicker.setValue(null);
                                 mc_repairservices_cbo_truck.setValue(null);
                            //refresh table
                                     setitems();
            } catch (Exception e) {
            }
        });
    }
    
    
    
    public void clicktable(){
        mc_repairservices_tableview.setOnMouseClicked((MouseEvent)->{
                 DataMcRepairServices dataMcRepairServicesSelect = (DataMcRepairServices)mc_repairservices_tableview.getSelectionModel().getSelectedItem();
                 if(dataMcRepairServicesSelect != null){
                        mc_repairservices_date_datepicker.setValue(LocalDate.of(dataMcRepairServicesSelect.getDate().getYear()+1900, dataMcRepairServicesSelect.getDate().getMonth()+1, dataMcRepairServicesSelect.getDate().getDate()));
                        mc_repairservices_cbo_truck.setValue(dataMcRepairServicesSelect.getTruck_id());
                        mc_repairservices_desciption_remarks.setText(dataMcRepairServicesSelect.getRemarks());
            
        }
        
        
        
        
        });
        
        
    }
    
    
    
    
    
    
    
    
    
    public class DataMcRepairServices{

        private Date date;
        private String truck_id;
        private String remarks;
        private String status;
        private String repair_service_id;
        
        public DataMcRepairServices(Date date,
                                    String truck_id, 
                                     String remarks,
                                    String status,
                                    String repair_service_id) {
            this.date = date;
            this.truck_id = truck_id;
            this.remarks = remarks;
            this.status = status;
            this.repair_service_id = repair_service_id;
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

        public String getRemarks() {
            return remarks;
        }

        public void setRemarks(String remarks) {
            this.remarks = remarks;
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
        
        
    }
    
    
    
    
    
    
    
}
