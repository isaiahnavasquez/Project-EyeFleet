/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Maintenance;

import eyefleet.DatabaseConnection;
import eyefleet.ErrorTraps;
import eyefleet.LayoutController;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDate;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 *
 * @author IsaiahJan
 */
public class Issuance {

    private ErrorTraps traps = new ErrorTraps();
    private LayoutController controller;
    private TableView tbl_driver;
    private TableView tbl_issuance;
    private DatabaseConnection database = new DatabaseConnection("root", "localhost", "eyefleet", "ramjorgeyao");
    private ObservableList<DataDriver> dataDriver;
    private ObservableList<DataIssuance> dataIssuance;
    private ResultSet rs;
    private String selectedItem = "";
    
    
    
    public Issuance(LayoutController controller) {
        this.controller = controller;
        tbl_driver = this.controller.getIssuance_tbl_driver();
        tbl_issuance = this.controller.getIssuance_tbl_issuance();
        setTableColumns();
        setItemsOfTables();
        setFunctions();
        setEquipmentList();
        setButtonIssue();
        setUpdateRefresh();
    }
    
    //function for setting the property values of table columns
    public void setTableColumns(){
        //setting the table Driver
        dataDriver = FXCollections.observableArrayList();
        controller.getIssuance_tbl_driver_col_driver().setCellValueFactory(new PropertyValueFactory("name"));
        tbl_driver.setItems(dataDriver);
        
        //setting the table Issuance
        dataIssuance = FXCollections.observableArrayList();
        controller.getIssuance_tbl_issuance_col_date().setCellValueFactory(new PropertyValueFactory("date"));
        controller.getIssuance_tbl_issuance_col_description().setCellValueFactory(new PropertyValueFactory("description"));
        controller.getIssuance_tbl_issuance_col_validity().setCellValueFactory(new PropertyValueFactory("validity"));
        controller.getIssuance_tbl_issuance_col_date_until().setCellValueFactory(new PropertyValueFactory("date_until"));
        tbl_issuance.setItems(dataIssuance);
    }
    
    //function for setting the data of the tables
    public void setItemsOfTables(){
        rs = database.setQuery("select * from driver");
        try {
            while(rs.next()){
                dataDriver.add(new DataDriver(rs.getString("driver_id")));
            }
        } catch (Exception e) {
            System.out.print(e);
        }
    }
    
    //function for setting the functionalities of the nodes
    public void setFunctions(){
        
        tbl_driver.setOnMouseClicked((ActionEvent) -> {
            setDataOfIssuance();
        });
        
        tbl_issuance.setOnMouseClicked((MouseEvent) -> {
            DataIssuance issuance_selected = (DataIssuance) tbl_issuance.getSelectionModel().getSelectedItem();
            DatabaseConnection database_issuance = new DatabaseConnection("root", "localhost", "eyefleet", "isaiah");
            ResultSet rs_issuance = database_issuance.setQuery("select * from issuance where issuance_detail = " + issuance_selected.getId());
            DatabaseConnection database_equipment = new DatabaseConnection("root", "localhost", "eyefleet", "ramjorgeyao");
   
            
            try {
                while(rs_issuance.next()){
                    
                    //sets the values of the fields once issuance is selected
                    ResultSet rs_equipment = database_equipment.setQuery("select * from equipment where equipment_id = '" + issuance_selected.getDescription() +"'");
                    
                    while(rs_equipment.next())
                    controller.getIssuance_txt_description().setText(rs_equipment.getString("description"));
                    
                    controller.getIssuance_txt_validity().setText(rs_issuance.getString("issuance_validity"));
                    controller.getIssuance_txt_quantity().setText(rs_issuance.getString("quantity"));
                    controller.getIssuance_txt_remarks().setText(rs_issuance.getString("remarks"));
                }
            } catch (Exception e) {
                 System.out.print("ERROR ON Issuance  setFunctions" + e);
                 System.exit(0);
            }
            
            
        });
        
    }
    
    public void setDataOfIssuance(){
        //clears the table issuance 
            dataIssuance.clear();
            
            //gets the selected data record in the table Driver
            DataDriver dataDriverSelected;
            dataDriverSelected = (DataDriver) tbl_driver.getSelectionModel().getSelectedItem();
            
            //retrieves the data for the table issuance
            //based from the driver selected
            rs = database.setQuery("select * from issuance where driver_id = '" + dataDriverSelected.getName() + "'");
            try {
                while(rs.next()){
                    
                    if (rs.getInt("issuance_validity") > 0)
                    dataIssuance.add(new DataIssuance(rs.getString("issuance_detail"),
                            rs.getDate("date_issued"),
                            rs.getString("equipment_id"),
                            rs.getInt("issuance_validity"),
                            rs.getString("date_until")));
                
                }
            } catch (Exception e) {
                System.out.print(e);
                System.exit(0);
            }
    }
    
    //function for setting the item list
    public void setEquipmentList(){
        
        //retrieves the List of all equipments
        rs = database.setQuery("select * from equipment");
        try {
            while(rs.next()){
                controller.getIssuance_cbo_equipment_id().getItems().add(rs.getString("equipment_id"));
            }
        } catch (Exception e) {
                 System.out.print("ERROR ON Issuance  setEquipmentList" + e);
            System.exit(0);
        }
        
        //retrieves the details of the equipment selected from the equipment list
        controller.getIssuance_cbo_equipment_id().setOnAction((ActionEvent) -> {
            
           
            try {
                 //gets the selected item from the equipment list
            selectedItem = controller.getIssuance_cbo_equipment_id().getSelectionModel().getSelectedItem().toString();
            
            //retrieves the details from the database
            ResultSet rs_equipment = database.setQuery("select * from equipment where equipment_id = '" + selectedItem +"'");
            
                while(rs_equipment.next()){
                controller.getIssuance_txt_description().setText(rs_equipment.getString("description"));
                controller.getIssuance_txt_validity().setText(rs_equipment.getString("equipment_validity"));
                }
                
                //calculates the date_until data of the equipment validity
                ResultSet rstemp = database.setQuery("select adddate(now(), (select equipment_validity"
                        + " from equipment"
                        + " where equipment_id = '" + selectedItem + "')) as 'date_until'");
                while(rstemp.next()){
                    LocalDate date_until = LocalDate.of(rstemp.getDate("date_until").getYear()+1900,
                                                    rstemp.getDate("date_until").getMonth()+1,
                                                    rstemp.getDate("date_until").getDate());
                    controller.getIssuance_dp_date_until().setValue(date_until);
                }
            } catch (Exception e) {
                System.out.print( "ERROR on issuance get the list "+e);
                System.exit(0);   
            }
        });
    }
    
    //the function for setting the functionality of the issue button
    public void setButtonIssue(){
        
        //sets the function for saving an issuance
        controller.getIssuance_btn_issue().setOnMouseClicked((MouseEvent) -> {
            //error variable
            String error = "";
            
            DataDriver driver_selected_temp = (DataDriver) controller.getIssuance_tbl_driver().getSelectionModel().getSelectedItem();
            
//            if (driver_selected_temp == null) {
//                controller.getMessage().shortToast("please select a driver", controller.getPnl_maintenance());
//            }
        
             if (controller.getIssuance_cbo_equipment_id().getValue() == null){
                                 controller.getMessage().shortToast(" Please select an equipment.", 2000);

             }
            
            else if (controller.getIssuance_txt_quantity().getText().isEmpty() ||
                        !traps.isNumerical(controller.getIssuance_txt_quantity().getText())){
                                                 controller.getMessage().shortToast(" Please review the Equipment Quantity..", 2000);
              }
            
            else {//trap 1
            
            //checks the qty on hand of the  equipment selected
            DatabaseConnection db_equipment_qty = new DatabaseConnection("root", "localhost", "eyefleet", "ramjorgeyao");
            ResultSet rs__equipment_qty = db_equipment_qty.setQuery("select qty_on_hand from equipment where equipment_id = '" + selectedItem + "'");
            
            //variable for the qty_on_hand
            Integer qty_on_hand = 0;
            
            try {
                rs__equipment_qty.next();
                qty_on_hand = rs__equipment_qty.getInt("qty_on_hand");
            } catch (Exception e) {
                System.out.print("ERROR ON CHECKING QTY ON HAND OF THE EQUIPMENT @ISSUANCE MODULE" + e);
            }
            
            //prompts the user if the qty demanded is higher than the
            //qty on hand of the equipment
            if((qty_on_hand - Integer.parseInt(controller.getIssuance_txt_quantity().getText())) < 0){
                controller.getMessage().message("Not enough Inventory", "The quantity left for "
                        + "the equipment " + selectedItem + " is only: " + qty_on_hand + ". Please change your quantity "
                        + "or perform an in-stock.", "Okay");
            }
            
            
            //performs the issuance if there is enough quantity from the inventory
            else {//trap 2
                //gets the record of driver selected
                DataDriver dataDriverSelected;
                dataDriverSelected = (DataDriver) tbl_driver.getSelectionModel().getSelectedItem();
            
                //the query for inserting an issuance record for the driver
                database.updateDatabase("insert into issuance(date_issued," +
                                    " issuance_validity," +
                                    " equipment_id," +
                                    " driver_id," +
                                    " date_until,"
                                    + " quantity, remarks)" +
                                    " values (now()," +
                                    " (select equipment_validity" +
                                    " from equipment" +
                                    " where equipment_id = '" + selectedItem + "')," +
                                    " '" + selectedItem + "'," +
                                    " '" + dataDriverSelected.getName() + "'," +
                                    " (select adddate(now()," +
                                    " (select equipment_validity" +
                                    " from equipment" +
                                    " where equipment_id = '" + selectedItem + "'))),"
                                    + " " + controller.getIssuance_txt_quantity().getText() + ","
                                    + " '" + controller.getIssuance_txt_remarks().getText() + "')");
            
            
                //refreshes the issuance table after the insertion
                dataIssuance.clear();
                DataDriver dataDriverSelected2;
                dataDriverSelected2 = (DataDriver) tbl_driver.getSelectionModel().getSelectedItem();
                rs = database.setQuery("select * from issuance where driver_id = '" + dataDriverSelected2.getName() + "'");
                try {
                    while(rs.next()){
                        dataIssuance.add(new DataIssuance(
                                rs.getString("issuance_detail"),
                                rs.getDate("date_issued"),
                                rs.getString("equipment_id"),
                                rs.getInt("issuance_validity"),
                                rs.getString("date_until")));
                    }


                DatabaseConnection database_equipment = new DatabaseConnection("root", "localhost", "eyefleet", "ramjorgeyao");
                database_equipment.updateDatabase("update equipment "
                        + "set qty_on_hand = "
                        + "qty_on_hand - " + controller.getIssuance_txt_quantity().getText() + " "
                        + "where equipment_id = '" + selectedItem + "'");
                
                setDataOfIssuance();
                controller.getEquipments().setTableData();

                } catch (Exception e) {
                    System.out.print(e);
                    System.exit(0);
                }

            }//end of error trap 2
            }//end of error trap 1
            
        });
        
        
    }
    
    public void updateTableIssuance(){
        Task<ObservableList<DataIssuance>> task = new GetIssuanceData();
        controller.getIssuance_progress().progressProperty().bind(task.progressProperty());
        new Thread(task).start();
    }
    
    public void setUpdateRefresh(){
        controller.getIssuance_progress().setOnMouseClicked((MouseEvent) -> {
            updateTableIssuance();
        });
    }
    
    public class GetIssuanceData extends Task<ObservableList<DataIssuance>> {

        private ObservableList<DataIssuance> data = FXCollections.observableArrayList();
        private Connection conn_temp;
        private Connection conn_temp2;
        private Connection conn_temp3;
        private Statement st_temp;
        private Statement st_temp2;
        private Statement st_temp3;
        private ResultSet rs_temp;
        private ResultSet rs_days_passed;
        private ResultSet rs_temp3;

        @Override
        protected ObservableList<DataIssuance> call() throws Exception {
            updateData();
            return data;
        }
        
    public void updateData(){
           
           //set a driver for database
           try {
               
            //set a connection
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            conn_temp = DriverManager.getConnection("jdbc:mysql://localhost/eyefleet?user=root"+ 
		    "&password=ramjorgeyao");
            conn_temp2 = DriverManager.getConnection("jdbc:mysql://localhost/eyefleet?user=root"+ 
		    "&password=ramjorgeyao");
            conn_temp3 = DriverManager.getConnection("jdbc:mysql://localhost/eyefleet?user=root"+ 
		    "&password=ramjorgeyao");
            st_temp = conn_temp.createStatement();
            st_temp2 = conn_temp2.createStatement();
            st_temp3 = conn_temp3.createStatement();
            
        } catch (Exception e) {
            System.out.print(e);
            System.exit(0);
        }
           
           //declare extra variables
           Integer issuance_validity = 0;
           Date date_issued;
           Integer days_passed = 0;
           Integer ID = 0;
           Integer progress = 1;
           Integer until = 0;
           Integer equipment_validity = 0;
           String equipment;
           
           try {
               
               rs_temp3 = st_temp3.executeQuery("select count(*) as 'total_record' from issuance");
               
               while (rs_temp3.next())
                   until = rs_temp3.getInt("total_record");
               
               if(until <= 0){
                   updateProgress(1, 1);
               }
               
               //executes only if there are record in the table
               if (until > 0) {
              
               //execute query for getting the whole record
               rs_temp = st_temp.executeQuery("select issuance_detail,"
                       + " date_issued,"
                       + " issuance_validity,"
                       + " equipment_id,"
                       + " driver_id,"
                       + " date_until"
                       + " from issuance");
               
               //reads the table top to bottom
               while(rs_temp.next()){
                   
                   //get the ID of the record
                   ID = rs_temp.getInt("issuance_detail");
                   
                   //get the equipment
                   equipment = rs_temp.getString("equipment_id");
                   
                   //update only records that has an issuance_validity greater than zero
                   if(rs_temp.getInt("issuance_validity") > 0){
                       
                       //setting variables from the record
                       issuance_validity = rs_temp.getInt("issuance_validity");
                       date_issued = rs_temp.getDate("date_issued");
                       
                       //calculate the days_passed using another resultset via new statement
                       rs_days_passed = st_temp2.executeQuery("select datediff(now(), '" + (date_issued.getYear()+1900) + "-" + (date_issued.getMonth()+1) + "-" + date_issued.getDate() + "') as 'days_passed'"
                               + ", (select equipment_validity from equipment where equipment_id = '" + equipment + "') as 'equipment_validity'");
                       
                       //getting the result of calculation for the variable days_passed
                       while(rs_days_passed.next()){
                       days_passed = rs_days_passed.getInt("days_passed");
                       equipment_validity = rs_days_passed.getInt("equipment_validity");
                       }
                       
                       //updates the issuance_validity of the record
                       st_temp2.executeUpdate("update issuance "
                               + "set issuance_validity = " + (equipment_validity - days_passed)
                               + " where issuance_detail = " + ID);
                   }//after the statement when the date_until is greater than 0
                   
                   //whenever the validity days is negative
                   else {
                       //update the validity into zero
                       st_temp2.executeUpdate("update issuance "
                               + "set issuance_validity = 0"
                               + " where issuance_detail = " + ID);
                   }
                   
                       updateProgress(progress, until);
                       progress++;
               }
               
               
               
               
               }//after until statement
               
           } catch (Exception e) {
               System.out.println(e);
               System.exit(0);
           }
           
           
       }
       
    }
    
    //class for Data Issuance Record
    public class DataIssuance {
        
        private String id;
        private Date date;
        private String description;
        private Integer validity;
        private String date_until;
        
        public DataIssuance(
            String id,
            Date date,
            String description,
            Integer validity,
            String date_until) {
            
            this.id = id;
            this.date = date;
            this.date_until = date_until;
            this.description = description;
            this.validity = validity;
            
        }

        public Date getDate() {
            return date;
        }

        public void setDate(Date date) {
            this.date = date;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public Integer getValidity() {
            return validity;
        }

        public void setValidity(Integer validity) {
            this.validity = validity;
        }

        public String getDate_until() {
            return date_until;
        }

        public void setDate_until(String date_until) {
            this.date_until = date_until;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }
        
        
    }
    
    //class for the Data Driver Record
    public class DataDriver{

        private String name;
        
        public DataDriver(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
        
    }
    
}
