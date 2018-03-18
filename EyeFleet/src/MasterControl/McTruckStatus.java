/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package MasterControl;

import eyefleet.DatabaseConnection;
import eyefleet.LayoutController;
import java.sql.ResultSet;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

/**
 *
 * @author maritess
 */
public class McTruckStatus {

    private LayoutController controller;
    
        //set the components of the parts of status;
    private TableView mc_tableview_status;
    private TableColumn mc_status_col_truck_id;
    private TableColumn mc_status_col_total_capacity;
    private TextField mc_status_text_truck_id;
    private TextField mc_status_text_total_capacity;
    private Label mc_status_btn_save;
    private Label mc_status_btn_edit;
    private Label mc_status_btn_update;
    
    
    //set the components of the parts of partition 
    private TableView mc_partition_tableview;
    private TableColumn mc_partition_col_partition_id;
    private TableColumn mc_partition_col_partition_capacity;
    private TableColumn mc_partition_col_partition_position;
    private TextField mc_partition_text_partition_id;
    private TextField mc_partition_text_capacity;
    private TextField mc_partition_text_position;
    private Label mc_parition_btn_save;
    private Label mc_parition_btn_edit;
    private Label mc_parition_btn_update;
    private TextField mc_partition_text_truckid;
            
    
    //the observable 
    private ObservableList<DataMcStatus>dataMcStatuses;
    private ObservableList<DataPartition>dataPartitions;
    
    private String select ="";
     
    public McTruckStatus(LayoutController controller_param) {
        this.controller = controller_param;
        
        //components of status
        this.mc_tableview_status = controller_param.getMc_tableview_status();
        this.mc_status_col_truck_id = controller_param.getMc_status_col_truck_id();
        this.mc_status_col_total_capacity = controller_param.getMc_status_col_total_capacity();
        this.mc_status_text_truck_id = controller_param.getMc_status_text_truck_id();
        this.mc_status_text_total_capacity =controller_param.getMc_status_text_total_capacity();
        this.mc_status_btn_save = controller_param.getMc_status_btn_save();
        this.mc_status_btn_edit = controller_param.getMc_status_btn_edit();
        this.mc_status_btn_update = controller_param.getMc_status_btn_update();
        
        //components of partition;
        this.mc_partition_tableview = controller_param.getMc_partition_tableview();
        this.mc_partition_col_partition_id = controller_param.getMc_partition_col_partition_id();
        this.mc_partition_col_partition_capacity = controller_param.getMc_partition_col_partition_capacity();
        this.mc_partition_col_partition_position = controller_param.getMc_partition_col_partition_position();
        this.mc_partition_text_partition_id =controller_param.getMc_partition_text_partition_id();
        this.mc_partition_text_capacity = controller_param.getMc_partition_text_capacity();
        this.mc_partition_text_position = controller_param.getMc_partition_text_position();
        this.mc_parition_btn_save = controller_param.getMc_parition_btn_save();
        this.mc_parition_btn_edit = controller_param.getMc_parition_btn_edit();
        this.mc_parition_btn_update = controller_param.getMc_parition_btn_update();
        this.mc_partition_text_truckid = controller_param.getMc_partition_text_truck_id();
        //initialized MODULES;
        initializedModules();
    }
    
    
    public void initializedModules(){
        //status fields
        settablecolumnsStatus();
        setitemsStatus();
        setfunctionStatus();
        setbtnstatusSave();
        setbtnstatusEdit();
        setbtnstatusUpdate();
        
        //disabled btn
        setdisabledButtonall();
       // setdisabledButtonall();
        
        //partition fields;
         settablePartition();
         setbtnpartitionEdit();
          
        //
         setpartitionbtnSave();
    
        
    }
    
    public void settablecolumnsStatus(){
        //table status
        dataMcStatuses = FXCollections.observableArrayList();
        mc_status_col_truck_id.setCellValueFactory(new PropertyValueFactory("truck_id"));
        mc_status_col_total_capacity.setCellValueFactory(new PropertyValueFactory("total_capacity"));
        mc_tableview_status.setItems(dataMcStatuses);
        
        //table partition
        dataPartitions = FXCollections.observableArrayList();
        mc_partition_col_partition_id.setCellValueFactory(new PropertyValueFactory("partition_id"));
        mc_partition_col_partition_capacity.setCellValueFactory(new PropertyValueFactory("capacity"));
        mc_partition_col_partition_position.setCellValueFactory(new PropertyValueFactory("position"));
        mc_partition_tableview.setItems(dataPartitions);
        
        
    }
    
    public void setitemsStatus(){
        //set the items of status
        DatabaseConnection dbitemsStatus = new DatabaseConnection("root", "localhost", "eyefleet", "ramjorgeyao");
        ResultSet rsitemStatus = dbitemsStatus.setQuery("SELECT * FROM eyefleet.truck");
        try {
            while(rsitemStatus.next()){
                dataMcStatuses.add(new DataMcStatus(rsitemStatus.getString("truck_id"),
                                                    rsitemStatus.getString("total_capacity")));
                
            }
        } catch (Exception e) {
            System.out.print("ERROR ON MASTER COBTROL TRUCKSTATUS setitemsStatus" + e);
            System.exit(0);
        }
    }
    
    public void setfunctionStatus(){
        //funtion for tabeview status
        mc_tableview_status.setOnMouseClicked((MouseEvent)->{
            
            //clear data
           dataPartitions.clear();
            
           DatabaseConnection dbtruckstatus1 = new DatabaseConnection("root", "localost", "eyefleet", "ramjorgeyao");
           
           //data of status table
           DataMcStatus dataMcStatus_select;
           dataMcStatus_select = (DataMcStatus)mc_tableview_status.getSelectionModel().getSelectedItem();
          
            //if dataMcStatus_select is not null execute the function
           if(dataMcStatus_select != null){
               try {
                     ResultSet rs_select = dbtruckstatus1.setQuery("SELECT * FROM eyefleet.partition where truck_id = '" + dataMcStatus_select.getTruck_id() +"'"
                                                                    +"ORDER BY position");
                     
                     while(rs_select.next()){
                         dataPartitions.add(new DataPartition(rs_select.getString("partition_id"),
                                                              rs_select.getString("capacity"),
                                                              rs_select.getString("position")));
                         
                     }
               
               } catch (Exception e) {
                 System.out.print("ERROR ON MASTER CONTROL TRUCKSTATUS setfunctionStatus" + e);
                 System.exit(0);
               }
               
               //textfields of status;
               mc_status_text_truck_id.setText(dataMcStatus_select.truck_id);
               mc_status_text_total_capacity.setText(dataMcStatus_select.total_capacity);
           
                 
              
               
             //enaled status fields
               mc_status_text_truck_id.setDisable(false);
               mc_status_text_total_capacity.setDisable(false);
               mc_status_btn_edit.setDisable(false);
//               
//              
//
//             //disabled partiton textfields
//               mc_partition_text_partition_id.setDisable(true);
//               mc_partition_text_capacity.setDisable(true);
//               mc_partition_text_position.setDisable(true);
               mc_parition_btn_edit.setDisable(false);
              mc_parition_btn_save.setDisable(true);
              mc_parition_btn_update.setDisable(true);
              //disabled btn status
               
           }
            
        
        });
    }
    
    public void settablePartition(){
        //funtion for tableview partition;
        mc_partition_tableview.setOnMouseClicked((MouseEvent)->{
        
        DataPartition dataPartition_select;
        dataPartition_select= (DataPartition)mc_partition_tableview.getSelectionModel().getSelectedItem();
        
        //if datapartiton is not null execute the function
        if(dataPartition_select != null){
            mc_partition_text_partition_id.setText(dataPartition_select.partition_id);
            mc_partition_text_capacity.setText(dataPartition_select.capacity);
            mc_partition_text_position.setText(dataPartition_select.position);
            
             
           //enable partition
           mc_partition_text_partition_id.setDisable(false);
           mc_partition_text_capacity.setDisable(false);
           mc_partition_text_position.setDisable(false);
           mc_parition_btn_edit.setDisable(false);
          
           //disable partition btn
             
            
            //disable status fields
            mc_status_text_truck_id.setDisable(true);
            mc_status_text_total_capacity.setDisable(true);;
            mc_status_btn_save.setDisable(true);
            mc_status_btn_edit.setDisable(false);
            mc_status_btn_update.setDisable(true);
        }
        
        
            
        });
    }
    
    
    
    public void setbtnstatusSave(){
        //funtion for btn status save;
        mc_status_btn_save.setOnMouseClicked((MouseEvent)->{
          DatabaseConnection dbbtnstatus = new DatabaseConnection("root", "localhost", "eyefleet", "password");
            try {
                //insert new truck in table status
                dbbtnstatus.updateDatabase("INSERT INTO eyefleet.truck(truck_id, total_capacity, status)" +
                  "VALUES('" + mc_status_text_truck_id.getText().toString() +"', "
                                + "'"+ mc_status_text_total_capacity.getText().toString() +"', " 
                                  + "'" + "available" + "')");
         
                   //clearfirst the data
                   dataMcStatuses.clear();
                   //refresh the tables;
                   setitemsStatus();
            } catch (Exception e) {
                 System.out.print("ERROR ON MASTER CONTROL TRUCKSTATUS setbtnstatusSave" + e);
                 System.exit(0);
            }
            
           
            
        });
        
    }
    
    public void setbtnstatusEdit(){
                //when i click the btn edito ostatus the two btn will enabled
        mc_status_btn_edit.setOnMouseClicked((MouseEvent)->{
                mc_status_btn_save.setDisable(false);
                mc_status_btn_update.setDisable(false);
        });
        
        
    }
    
    public void setbtnstatusUpdate(){
        //funtion for btn update status;
        mc_status_btn_update.setOnMouseClicked((MouseEvent)->{
           
            try {
               DataMcStatus dataMcStatusselect = (DataMcStatus)mc_tableview_status.getSelectionModel().getSelectedItem();
             //crate databse
            DatabaseConnection dbstatusUpdate = new DatabaseConnection("root", "localhost", "eyefleet", "ramjorgeyao");
           
            //update records;
            dbstatusUpdate.updateDatabase("UPDATE eyefleet.truck SET "
                                                 + "truck_id ='" + mc_status_text_truck_id.getText().toString() + "', "
                                                 + "total_capacity ='" + mc_status_text_total_capacity.getText().toString()+ "', "
                                                 + "status ='" + "available" + "'"
                                                 + " WHERE truck_id ='"+ dataMcStatusselect.getTruck_id() + "'");
 
                 //clear data
                 dataMcStatuses.clear();
                //refresh table status;
                setitemsStatus();
            } catch (Exception e) {
                 System.out.print("ERROR ON MASTER CONTROL TRUCKSTATUS setbtnstatusUpdate" + e);
                 System.exit(0);
            }
        
        });
    }
    
    
    public void setdisabledButtonall(){
        //status btn;
        mc_status_btn_save.setDisable(true);
        mc_status_btn_edit.setDisable(true);
        mc_status_btn_update.setDisable(true);
        //partion btn
        mc_parition_btn_save.setDisable(true);
        mc_parition_btn_edit.setDisable(true);
        mc_parition_btn_update.setDisable(true);
    }
     
        public void setpartitionbtnSave(){
            //function for partition btn save
            mc_parition_btn_save.setOnMouseClicked((MouseEvent)->{
                    try {
                         DataMcStatus dataMcStatus_select;
                        dataMcStatus_select = (DataMcStatus)mc_tableview_status.getSelectionModel().getSelectedItem();
                        DatabaseConnection dbpartitionSave = new DatabaseConnection("root", "localhost", "eyefleet", "ramjorgeyao");
                        
                       // THIS AREA IS UNDERCONSTRUCTION 
                            dbpartitionSave.updateDatabase("INSERT INTO eyefleet.partition(partition_id, capacity, position, truck_id)"
                                                            + "VALUES('"+ mc_partition_text_partition_id.getText().toString()+ "', "
                                                                        + "'" + mc_partition_text_capacity.getText().toString()+ "', "
                                                                        + "'" + mc_partition_text_position.getText().toString()+ "'"
                                                                        + " WHERE truck_id ='"+ dataMcStatus_select.getTruck_id() + "')");
                                                                        
                        
                        
                } catch (Exception e) {
                 System.out.print("ERROR ON MASTER CONTROL TRUCKSTATUS setpartitionbtnSave" + e);
                 System.exit(0);
                }
                
            
            });
        }
    
    
    
    
    public void setbtnpartitionEdit(){
        //when i click the btn edito partition the two btn will enabled;
        mc_parition_btn_edit.setOnMouseClicked((MouseEvent)->{
            mc_parition_btn_save.setDisable(false);
            mc_parition_btn_update.setDisable(false);

        
        });
    }
    
    
    
    
     
    
     
    
    
    
    
    
    
    public class DataMcStatus{
        private String truck_id;
        private String total_capacity;
        public DataMcStatus(String truck_id,
                            String total_capacity) {
            this.truck_id = truck_id;
            this.total_capacity = total_capacity;
         
        }

        public String getTruck_id() {
            return truck_id;
        }

        public void setTruck_id(String truck_id) {
            this.truck_id = truck_id;
        }

        public String getTotal_capacity() {
            return total_capacity;
        }

        public void setTotal_capacity(String total_capacity) {
            this.total_capacity = total_capacity;
        }
        
    }
    
    
    public class DataPartition{
        private String partition_id;
        private String capacity;
        private String position;
        public DataPartition(String partition_id,
                             String capacity,
                             String position) {
            this.partition_id = partition_id;
            this.capacity = capacity;
            this.position = position;
        }

        public String getPartition_id() {
            return partition_id;
        }

        public void setPartition_id(String partition_id) {
            this.partition_id = partition_id;
        }

        public String getCapacity() {
            return capacity;
        }

        public void setCapacity(String capacity) {
            this.capacity = capacity;
        }

        public String getPosition() {
            return position;
        }

        public void setPosition(String position) {
            this.position = position;
        }
        
    }
    
    
    

}