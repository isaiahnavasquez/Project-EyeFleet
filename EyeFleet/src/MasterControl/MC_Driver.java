/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MasterControl;

import eyefleet.DatabaseConnection;
import eyefleet.ErrorTraps;
import eyefleet.LayoutController;
import java.sql.ResultSet;
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
 * @author jorge
 */
public class MC_Driver {
    
    private LayoutController controller;
    private TableView driver_tableview;
    private TableColumn driver_tblc_driver_id;
    private TableColumn driver_tblc_fname;
    private TableColumn driver_tblc_lname;
    private TableColumn driver_tblc_mname;
    private ComboBox driver_cbo_driver_id;
    private TextField driver_txt_fname;
    private TextField driver_txt_lname;
    private TextField driver_txt_mname;
    private Label driver_btn_add;
    private Label driver_btn_update;
    private Label driver_btn_refresh;
    private ObservableList<DataDriver> dataDrivers;
    ErrorTraps traps  = new ErrorTraps();

    public MC_Driver(LayoutController controller_param) {
        
       this.controller = controller_param;
       this.driver_tableview = controller_param.getDriver_tableview();
       this.driver_tblc_driver_id = controller_param.getDriver_tblc_driver_id();
       this.driver_tblc_fname = controller_param.getDriver_tblc_fname();
       this.driver_tblc_lname = controller_param.getDriver_tblc_lname();
       this.driver_tblc_mname = controller_param.getDriver_tblc_mname();
       this.driver_cbo_driver_id = controller_param.getDriver_cbo_driver_id();
       this.driver_txt_fname = controller_param.getDriver_txt_fname();
       this.driver_txt_lname = controller_param.getDriver_txt_lname();
       this.driver_txt_mname = controller_param.getDriver_txt_mname();
       this.driver_btn_add = controller_param.getDriver_btn_add();
       this.driver_btn_update = controller_param.getDriver_btn_update();
       this.driver_btn_refresh = controller_param.getDriver_btn_refresh();
       
       //initialized modules;
       initializedModules();
    }
    
    
    public void initializedModules(){
        tableColumns();
        setItems();
        cboDriver();
        cbodescription();
        cliktblDriver();
        btnAdd();
        btnUpdate();
        btnRefresh();
    }
    
    public void tableColumns(){
        dataDrivers = FXCollections.observableArrayList();
        driver_tblc_driver_id.setCellValueFactory(new PropertyValueFactory("driver_id"));
        driver_tblc_fname.setCellValueFactory(new PropertyValueFactory("fname"));
        driver_tblc_lname.setCellValueFactory( new PropertyValueFactory("lname"));
        driver_tblc_mname.setCellValueFactory(new PropertyValueFactory("mname"));
        driver_tableview.setItems(dataDrivers);
    }
    
    public void setItems(){
        DatabaseConnection dbitems = new DatabaseConnection("root", "localhost", "eyefleet", "ramjorgeyao");
        ResultSet rsitems = dbitems.setQuery("SELECT * FROM eyefleet.driver");
        try {
            while(rsitems.next()){
                dataDrivers.add(new DataDriver(rsitems.getString("driver_id"),
                        rsitems.getString("fname"),
                        rsitems.getString("lname"),
                        rsitems.getString("mname")));
                
            }
        } catch (Exception e) {
            System.out.print("Error on MC_Driver setItems " + e);
            System.exit(0);
        }
    }
    
    public void cboDriver(){
        //clear first the cbo items driver
        driver_cbo_driver_id.getItems().clear();
        
        DatabaseConnection dbcboDriver = new DatabaseConnection("root", "localhost", "eyefleet", "ramjorgeyao");
        ResultSet rsboDriver = dbcboDriver.setQuery("SELECT driver_id  FROM eyefleet.driver");
        try {
            while(rsboDriver.next()){
                driver_cbo_driver_id.getItems().add(rsboDriver.getString("driver_id"));
            }
        } catch (Exception e) {
            System.out.print("Error on MC_Driver cboDriver " + e);
            System.exit(0);      
        }
    }
    
    //display the discription of the selected name;
    public void cbodescription(){
        driver_cbo_driver_id.setOnAction((ActionEvent)->{
                   
                    try {
                         DatabaseConnection dbcbodescrition = new DatabaseConnection("root", "localhost", "eyefleet", "ramjorgeyao");
                        ResultSet rsdescription = dbcbodescrition.setQuery("SELECT fname, lname, mname  FROM eyefleet.driver "
                                + " WHERE driver_id ='" + driver_cbo_driver_id.getValue() +"'");
                        
                            while(rsdescription.next()){
                               driver_txt_fname.setText(rsdescription.getString("fname"));
                               driver_txt_lname.setText(rsdescription.getString("lname"));
                               driver_txt_mname.setText(rsdescription.getString("mname"));
                                
                            }
            } catch (Exception e) {
                System.out.print("Error on MC_Driver cbodescription " + e);
                System.exit(0);    
                
            }
                    
        });
        
    }
    
    public void cliktblDriver(){
       driver_tableview.setOnMouseClicked((MouseEvent)->{
        DataDriver dataDriverselect = (DataDriver)driver_tableview.getSelectionModel().getSelectedItem();
        if(dataDriverselect !=null){
            driver_cbo_driver_id.setValue(dataDriverselect.getDriver_id());
            driver_txt_fname.setText(dataDriverselect.getFname());
            driver_txt_lname.setText(dataDriverselect.getLname());
            driver_txt_mname.setText(dataDriverselect.getMname());
        }
       });
      
    }
    
    
    
    public void btnAdd(){
        driver_btn_add.setOnMouseClicked((MouseEvent)->{
             //check if the truck id is exist;        
                    boolean exist = false;
                    DatabaseConnection dbcbo = new DatabaseConnection("root", "localhost", "eyefleet", "ramjorgeyao");
                    ResultSet rscbo = dbcbo.setQuery("SELECT * FROM eyefleet.driver");
                 try {
                         while(rscbo.next()){
                              if( driver_cbo_driver_id.getValue().equals(rscbo.getString("driver_id")))  {
                                exist = true;
                             break;
                     }
                        }
                  } catch (Exception e) {
             }
             
             
              if(exist ==true){
                              controller.getMessage().shortToast( "Driver  ID  already Exist::", 2000);

            }//end of error trps;
            
              
              else if(driver_cbo_driver_id.getValue()==null){
                  controller.getMessage().shortToast("Please provide a Driver ID", 2000);
                  
              }
              
              else if (driver_txt_fname.getText().isEmpty() || traps.isNumerical(driver_txt_fname.getText())){
                              controller.getMessage().shortToast( "Please Provide Correct First name:", 2000);
                  
              }
              else if (driver_txt_lname.getText().isEmpty() || traps.isNumerical(driver_txt_lname.getText())){
                              controller.getMessage().shortToast( "Please Provide Correct Last name:", 2000);
              }
              else if (driver_txt_mname.getText().isEmpty()||  traps.isNumerical(driver_txt_mname.getText())){
                              controller.getMessage().shortToast( "Please Provide Correct Middle name:", 2000);
              }
              
              
              else{
                        try {
                         DatabaseConnection dbadd = new DatabaseConnection("root", "localhost", "eyefleet", "ramjorgeyao");
                         dbadd.updateDatabase("INSERT INTO eyefleet.driver (driver_id,  fname , lname , mname)"
                                 + " VALUES('" +driver_cbo_driver_id.getValue()+"', "
                                                           +"'" + driver_txt_fname.getText()+ "', "
                                                             +"'" +driver_txt_lname.getText()+"', "
                                                             +"'" + driver_txt_mname.getText() + "')");
                         //clear table first;
                         dataDrivers.clear();
                         //refresh tables
                         setItems();
                         //cbo item refresh
                         cboDriver();

            } catch (Exception e) {
                  System.out.print("Error on MC_Driver btnAdd " + e);
                    System.exit(0);    
                
            }
              }//end of if;
        });
        
    }
    
    
    
    public void btnUpdate(){
        driver_btn_update.setOnMouseClicked((MouseEvent)->{
            
             if(driver_cbo_driver_id.getValue()==null){
                  controller.getMessage().shortToast("Please provide a Driver ID", 2000);
                  
              }
              
              else if (driver_txt_fname.getText().isEmpty() || traps.isNumerical(driver_txt_fname.getText())){
                              controller.getMessage().shortToast( "Please Provide Correct First name:", 2000);
                  
              }
              else if (driver_txt_lname.getText().isEmpty() || traps.isNumerical(driver_txt_lname.getText())){
                              controller.getMessage().shortToast( "Please Provide Correct Last name:", 2000);
              }
              else if (driver_txt_mname.getText().isEmpty()||  traps.isNumerical(driver_txt_mname.getText())){
                              controller.getMessage().shortToast( "Please Provide Correct Middle name:", 2000);
              }
              else{
            try {
              DatabaseConnection dbupdate = new DatabaseConnection("root", "localhost", "eyefleet", "ramjorgeyao");
              dbupdate.updateDatabase("UPDATE eyefleet.driver SET  fname ='"+driver_txt_fname.getText()+"', "
                                                                                                                 +"lname ='"+ driver_txt_lname.getText()+"', "
                                                                                                                  +"mname ='"+ driver_txt_mname.getText()+"' "
                                                                                                                   +"  WHERE  driver_id ='"+ driver_cbo_driver_id.getValue().toString()+"'");
              
              //clear table
              dataDrivers.clear();
              //refresh table
              setItems();
            } catch (Exception e) {
                  System.out.print("Error on MC_Driver btnUpdate " + e);
                    System.exit(0);    
                
            }
              }//end of if;
        });
    }
    
    
    public void btnRefresh(){
        driver_btn_refresh.setOnMouseClicked((MouseEvent)->{
            
            //clear fields
            driver_cbo_driver_id.setValue(null);
            driver_txt_fname.clear();
            driver_txt_lname.clear();
            driver_txt_mname.clear();
            
            dataDrivers.clear();
            //cbo refresh
            cboDriver();
            //refresh table
            setItems();
        });
    }
    
    
    
    
    
    public class DataDriver{
        private String driver_id;
        private String fname;
        private String lname;
        private String mname;
         
        public DataDriver(String driver_id,
        String fname,
        String lname,
        String mname) {
            
            this.driver_id = driver_id;
            this.fname = fname;
            this.lname = lname;
            this.mname = mname;
        }

        public String getDriver_id() {
            return driver_id;
        }

        public void setDriver_id(String driver_id) {
            this.driver_id = driver_id;
        }

        public String getFname() {
            return fname;
        }

        public void setFname(String fname) {
            this.fname = fname;
        }

        public String getLname() {
            return lname;
        }

        public void setLname(String lname) {
            this.lname = lname;
        }

        public String getMname() {
            return mname;
        }

        public void setMname(String mname) {
            this.mname = mname;
        }
        
        
    }
    
}
