/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Orders;

import eyefleet.DatabaseConnection;
import eyefleet.ErrorTraps;
import eyefleet.LayoutController;
import java.sql.ResultSet;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 *
 * @author IsaiahJan
 */
public class OrdersDeployment {

    //controller
    LayoutController controller;
    ErrorTraps traps = new ErrorTraps();
    
    //tables
    TableView tbl_truck;
    TableView tbl_partition;
    
    //columns
    TableColumn tbl_truck_col_truck;
    TableColumn tbl_partition_col_partition;
    TableColumn tbl_partition_col_customer;
    TableColumn tbl_partition_col_product;
    TableColumn tbl_partition_col_qty;
    
    //fields
    ComboBox cbo_driver;
    TextField txt_hbdmtr;
    TextField txt_fuel_cons;
    
    //button
    Label btn_print_details;
    Label btn_truck_deployed;
    CheckBox cbx_failed;
    
    //contexts
    MenuItem ctx_abort_current;
    MenuItem ctx_abort_all;
    
    //data
    ObservableList<DataPartition> data_partition = FXCollections.observableArrayList();
    ObservableList<DataTruck> data_truck = FXCollections.observableArrayList();
    
    public OrdersDeployment(LayoutController controller) {
        //controller
        this.controller = controller;
        
        //tables
        tbl_partition = controller.getDeployment_tbl_partition();
        tbl_truck = controller.getDeployment_tbl_truck();
        
        //columns
        tbl_partition_col_customer = controller.getDeployment_tbl_partition_col_customer();
        tbl_partition_col_partition = controller.getDeployment_tbl_partition_col_partition();
        tbl_partition_col_product = controller.getDeployment_tbl_partition_col_product();
        tbl_partition_col_qty = controller.getDeployment_tbl_partition_col_qty();
        tbl_truck_col_truck = controller.getDeployment_tbl_truck_col_truck();
        
        //fields
        cbo_driver = controller.getDeployment_cbo_driver();
        txt_fuel_cons = controller.getDeployment_txt_fuel_cons();
        txt_hbdmtr = controller.getDeployment_txt_hbmtr();
        
        //buttons
        btn_print_details = controller.getDeployment_btn_print_details();
        btn_truck_deployed = controller.getDeployment_btn_truck_deployed();
        
        //menu items
        ctx_abort_all = controller.getDeployment_ctx_abort_all();
        ctx_abort_current = controller.getDeployment_ctx_abort_current();
        
        //initialize module
        initializeModule();
    }
    
    public void initializeModule(){
        setTableColumns();
        setTableFunctionTruck();
        setDataCBODriver();
        setButtonFunctionTruckDeployed();
        setMenuFunctionAbortAll();
        setMenuFunctionAbortCurrent();
    }
    
    public void setTableColumns(){
        tbl_partition_col_customer.setCellValueFactory(new PropertyValueFactory("customer"));
        tbl_partition_col_partition.setCellValueFactory(new PropertyValueFactory("partition"));
        tbl_partition_col_product.setCellValueFactory(new PropertyValueFactory("product"));
        tbl_partition_col_qty.setCellValueFactory(new PropertyValueFactory("qty"));
        tbl_partition.setItems(data_partition);
        
        tbl_truck_col_truck.setCellValueFactory(new PropertyValueFactory("truck"));
        tbl_truck.setItems(data_truck);
    }
    
    public void setTruckData(){
        data_truck.clear();
        DatabaseConnection db_truck = new DatabaseConnection("root", "localhost", "analysis", "isaiah");
        ResultSet rs_truck = db_truck.setQuery("select distinct truck from analysis.customer_truck_load");
        
        try {
            while(rs_truck.next()){
                data_truck.add(new DataTruck(rs_truck.getString("truck")));
            }
        } catch (Exception e) {
            System.out.println("ERROR FROM setTruckData " + e);
        }
    }
    
    public void setTableFunctionTruck(){
        tbl_truck.setOnMouseClicked((MouseEvent) -> {    
            data_partition.clear();
            DataTruck truck_selected = (DataTruck) tbl_truck.getSelectionModel().getSelectedItem();
            if(truck_selected != null){
                DatabaseConnection db_partition = new DatabaseConnection("root", "localhost", "analysis", "isaiah");
                ResultSet rs_partition = db_partition.setQuery("select * from analysis.order_partition_load where truck = '" + truck_selected.getTruck() + "'");

                try {
                    while(rs_partition.next()){
                        data_partition.add(new DataPartition(rs_partition.getString("partition_id"), 
                                rs_partition.getString("customer"), 
                                rs_partition.getString("product"), 
                                rs_partition.getString("qty"),
                                rs_partition.getString("order_id")));
                    }
                } catch (Exception e) {
                    System.out.println("ERROR FROM setTableFunctionTruck " + e);
                }
            }
        });
        
    }
    
    public void setDataCBODriver(){
        DatabaseConnection db_driver = new DatabaseConnection("root", "localhost", "eyefleet", "isaiah");
        ResultSet rs_driver = db_driver.setQuery("select driver_id from driver");
        
        try {
            while(rs_driver.next()){
                cbo_driver.getItems().add(rs_driver.getString("driver_id"));
            }
        } catch (Exception e) {
            System.out.println("ERROR FROM setDataCBODriver " + e);
        }
    }
    
    public void setMenuFunctionAbortCurrent(){
        ctx_abort_current.setOnAction((ActionEvent) -> {
            DataTruck truck_selected = (DataTruck) tbl_truck.getSelectionModel().getSelectedItem();
            
            if(truck_selected != null){
                //deletes records from the analysis
                DatabaseConnection db_ct_load = new DatabaseConnection("root", "localhost", "analysis", "isaiah");
                db_ct_load.updateDatabase("delete from analysis.customer_truck_load where truck = '" + truck_selected.getTruck() + "'");

                DatabaseConnection db_op_customer = new DatabaseConnection("root", "localhost", "analysis", "isaiah");
                db_op_customer.updateDatabase("delete from analysis.op_customer where truck = '" + truck_selected.getTruck() + "'");

                DatabaseConnection db_op_load = new DatabaseConnection("root", "localhost", "analysis", "isaiah");
                db_op_load.updateDatabase("delete from order_partition_load where truck = '" + truck_selected.getTruck() + "'");

                setTruckData();
                data_partition.clear();
                controller.getMessage().shortToast("Truck Cancelled.", 1000);
            }
            else {
                controller.getMessage().shortToast("No Truck Selected to cancel.", 2000);
            }
        });
    }
    
    public void setMenuFunctionAbortAll(){
        ctx_abort_all.setOnAction((ActionEvent) -> {
            //deletes records from the analysis
            DatabaseConnection db_ct_load = new DatabaseConnection("root", "localhost", "analysis", "isaiah");
            db_ct_load.updateDatabase("delete from analysis.customer_truck_load");
            
            DatabaseConnection db_op_customer = new DatabaseConnection("root", "localhost", "analysis", "isaiah");
            db_op_customer.updateDatabase("delete from analysis.op_customer");
            
            DatabaseConnection db_op_load = new DatabaseConnection("root", "localhost", "analysis", "isaiah");
            db_op_load.updateDatabase("delete from order_partition_load");
            
            data_truck.clear();
            data_partition.clear();
            
            controller.getBookOrders().openManualAssignment();
            controller.getMessage().shortToast("All Trucks cancelled.", 2000);
        });
    }
    
    public void setButtonFunctionTruckDeployed(){
        btn_truck_deployed.setOnMouseClicked((MouseEvent) -> {
            
            //save a new deployment batch with parameters
            DataTruck truck_selected = (DataTruck) tbl_truck.getSelectionModel().getSelectedItem();
            
            if(truck_selected == null){
                controller.getMessage().shortToast("Please select a truck to deploy.", 2000);
            } else if (cbo_driver.getValue() == null){
                controller.getMessage().shortToast("Please select a driver.", 2000);
            } else if (txt_hbdmtr.getText().isEmpty()){
                controller.getMessage().shortToast("Please input starting Hobodometer Count.", 3000);
            } else if (!traps.isNumerical(txt_hbdmtr.getText())){
                controller.getMessage().shortToast("The Hobodometer count must be numerical.", 2000);
            } else if (txt_fuel_cons.getText().isEmpty()){
                controller.getMessage().shortToast("Please input starting Fuel Consumption count.", 2000);
            } else if (!traps.isNumerical(txt_fuel_cons.getText())){
                controller.getMessage().shortToast("Please make sure that the Fuel Consumption count is numerical", 2000);
            } else {
                DatabaseConnection db_deployment_batch = new DatabaseConnection("root", "localhost", "eyefleet", "isaiah");
                db_deployment_batch.updateDatabase("insert into deployment_batch(date_deployed, "
                        + "fuel_consumption, "
                        + "initial_count, "
                        + "status, "
                        + "driver_id, "
                        + "truck_id) "
                        + "values ("
                        + "now(), "
                        + "" + txt_fuel_cons.getText() + ", "
                        + "" + txt_hbdmtr.getText() + ", "
                        + "'on_delivery', "
                        + "'" + cbo_driver.getValue().toString() + "', "
                        + "'" + truck_selected.getTruck() + "')");

                //get the deployment id
                DatabaseConnection db_deployment_id = new DatabaseConnection("root", "localhost", "eyefleet", "isaiah");
                ResultSet rs_deployment_id = db_deployment_id.setQuery("select deployment_batch_id from deployment_batch " +
                                            "where initial_count = " + txt_hbdmtr.getText() + " AND " +
                                            "deployment_batch.status = 'on_delivery' AND " +
                                            "fuel_consumption = " + txt_fuel_cons.getText() + " AND " +
                                            "driver_id = '" + cbo_driver.getValue().toString() + "' AND " +
                                            "truck_id = '" + truck_selected.getTruck() + "'");

                String deployment_id = "";

                try {
                    while(rs_deployment_id.next()){
                        deployment_id = rs_deployment_id.getString("deployment_batch_id");
                    }
                } catch (Exception e) {
                    System.out.println("ERROR FROM setButtonFunctionTruckDeployed @ getting deployment_id " + e);
                }

                //saves each order belong in the deployment batch
                for(int counter = 0; counter < data_partition.size(); counter++){
                    DatabaseConnection db_assignment = new DatabaseConnection("root", "localhost", "eyefleet", "isaiah");



                    //if and only if the order contains something to be saved
                    if(data_partition.get(counter).getCustomer() != null){

                        db_assignment.updateDatabase("insert into order_assignment(date_assigned, " +
                                                    "order_assignment.status, " +
                                                    "partition_id, " +
                                                    "deployment_batch_id, " +
                                                    "order_id, "
                                                    + " qty) " +
                                                    "values " +
                                                    "(now(), " +
                                                    "'on_delivery', " +
                                                    "'" +  data_partition.get(counter).getPartition()+ "', " +
                                                    "" + deployment_id + ", " +
                                                    "" + data_partition.get(counter).getOrder_id() + ", "
                                                    + "" + data_partition.get(counter).getQty() + ")");
                    }


                }

                //updates all of the order status from the order stack to 'on_deliver'
                for(int counter = 0; counter < data_partition.size(); counter++){
                    DatabaseConnection db_update_orders = new DatabaseConnection("root", "localhost", "eyefleet", "isaiah");
                    db_update_orders.updateDatabase("update eyefleet.order " +
                                                    "set order.status = 'on_delivery' " +
                                                    "where order_id = " + data_partition.get(counter).getOrder_id());
                }

                //opens the order schedule module
                controller.getOrders_on_delivery().initializeModule();

                //refreshes order stack table
                controller.getOrder_stack().initializeModule();

                //deletes records from the analysis
                DatabaseConnection db_ct_load = new DatabaseConnection("root", "localhost", "analysis", "isaiah");
                db_ct_load.updateDatabase("delete from analysis.customer_truck_load where truck = '" + truck_selected.getTruck() + "'");

                DatabaseConnection db_op_customer = new DatabaseConnection("root", "localhost", "analysis", "isaiah");
                db_op_customer.updateDatabase("delete from analysis.op_customer where truck = '" + truck_selected.getTruck() + "'");

                DatabaseConnection db_op_load = new DatabaseConnection("root", "localhost", "analysis", "isaiah");
                db_op_load.updateDatabase("delete from order_partition_load where truck = '" + truck_selected.getTruck() + "'");

                setTruckData();
                data_partition.clear();

                //opens on delivery module
                controller.getBookOrders().openOnDelivery();

                txt_fuel_cons.setText("");
                txt_hbdmtr.setText("");
                cbo_driver.setValue("");
                
                //resets the scheduling module
                controller.getScheduling().setTableDataCTCustomer();
                controller.getScheduling().setTableDataCTPartition();
                controller.getScheduling().setTableDataCTTruck();
                controller.getScheduling().orderToProductReprocess();
                controller.getScheduling().op_data_partition.clear();
            }
            
        });
    }
    
    public class DataTruck{

        private String truck;
        
        public DataTruck(String truck) {
            this.truck = truck;
        }

        public String getTruck() {
            return truck;
        }

        public void setTruck(String truck) {
            this.truck = truck;
        }
        
    }
    
    public class DataPartition{

        private String partition;
        private String customer;
        private String product;
        private String qty;
        private String order_id;
        
        public DataPartition(
                String partition,
                String customer,
                String product,
                String qty,
                String order_id) {
            
            this.customer = customer;
            this.partition = partition;
            this.product = product;
            this.qty = qty;
            this.order_id = order_id;
            
        }

        public String getPartition() {
            return partition;
        }

        public void setPartition(String partition) {
            this.partition = partition;
        }

        public String getCustomer() {
            return customer;
        }

        public void setCustomer(String customer) {
            this.customer = customer;
        }

        public String getProduct() {
            return product;
        }

        public void setProduct(String product) {
            this.product = product;
        }

        public String getQty() {
            return qty;
        }

        public void setQty(String qty) {
            this.qty = qty;
        }

        public String getOrder_id() {
            return order_id;
        }

        public void setOrder_id(String order_id) {
            this.order_id = order_id;
        }
        
    }
    
}
