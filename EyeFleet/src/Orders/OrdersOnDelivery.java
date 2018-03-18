/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Orders;

import eyefleet.DatabaseConnection;
import eyefleet.ErrorTraps;
import eyefleet.LayoutController;
import java.sql.Date;
import java.sql.ResultSet;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.DepthTest;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 *
 * @author IsaiahJan
 */
public class OrdersOnDelivery {

    //controller
    LayoutController controller;
    ErrorTraps traps = new ErrorTraps();
    
    //tables
    TableView tbl_truck;
    TableView tbl_partition;
    
    //table columns
    TableColumn tbl_truck_col_truck;
    TableColumn tbl_truck_col_driver;
    TableColumn tbl_partition_col_partition;
    TableColumn tbl_partition_col_status;
    TableColumn tbl_partition_col_customer;
    TableColumn tbl_partition_col_qty;
    TableColumn tbl_partition_col_product;
    
    //fields
    TextField txt_customer;
    TextField txt_product;
    TextField txt_qty;
    TextField txt_hbdmtr;
    
    //others
    CheckBox cbx_failed;
    TextArea txt_reason;
    
    //buttons
    Label btn_set_as_delivered;
    
    //menu actions (contexts)
    MenuItem ctx_re_sched;
    MenuItem ctx_cancel;
    
    //menu
    MenuButton mnu_btn_action;
    
    //Data
    ObservableList<DataPartition> data_partition = FXCollections.observableArrayList();
    ObservableList<DataTruck> data_truck = FXCollections.observableArrayList();
    
    public OrdersOnDelivery(LayoutController controller) {
        //controller
        this.controller = controller;
        
        //tables
        tbl_partition = controller.getOn_delivery_tbl_orders();
        tbl_truck = controller.getOn_delivery_tbl_trucks();
        
        //columns
        tbl_partition_col_partition = controller.getOn_delivery_tbl_orders_col_partition_id();
        tbl_partition_col_status = controller.getOn_delivery_tbl_orders_col_status();
        tbl_truck_col_driver = controller.getOn_delivery_tbl_trucks_col_driver();
        tbl_truck_col_truck = controller.getOn_delivery_tbl_trucks_col_truck_id();
        tbl_partition_col_customer = controller.getOn_delivery_tbl_orders_col_customer();
        tbl_partition_col_product = controller.getOn_delivery_tbl_orders_col_product();
        tbl_partition_col_qty = controller.getOn_delivery_tbl_orders_col_qty();
        
        //others
        cbx_failed = controller.getOn_delivery_cbx_failed();
        txt_reason = controller.getOn_delvivery_txt_reason();
        
        //fields
        txt_customer = controller.getOn_delivery_txt_customer();
        txt_product = controller.getOn_delivery_txt_product();
        txt_qty = controller.getOn_delivery_txt_quantity();
        txt_hbdmtr = controller.getOn_delivery_txt_hobodometer();
        
        //menu actions (contexts)
        ctx_cancel = controller.getOn_delivery_ctx_cancel();
        ctx_re_sched = controller.getOn_delivery_ctx_re_sched();
        
        //menu
        mnu_btn_action = controller.getOn_delivery_mnu_action();
        
        //button
        btn_set_as_delivered = controller.getOn_delivery_btn_set_as_delivered();
        
        //initialize module
        initializeModule();
    }

    public void initializeModule(){
        setTableColumns();
        setTableDataTruck();
        setTableFunctionTruck();
        setTableFunctionPartition();
        setButtonFunctionFailed();
        setContextFunctionReSched();
        setContextFunctionCancel();
        setButtonFunctionSetAsDelivered();
   }
    
    public void setTableColumns(){
        tbl_partition_col_partition.setCellValueFactory(new PropertyValueFactory("partition"));
        tbl_partition_col_status.setCellValueFactory(new PropertyValueFactory("status"));
        tbl_partition_col_customer.setCellValueFactory(new PropertyValueFactory("customer"));
        tbl_partition_col_product.setCellValueFactory(new PropertyValueFactory("product"));
        tbl_partition_col_qty.setCellValueFactory(new PropertyValueFactory("qty"));
        tbl_partition.setItems(data_partition);
        
        tbl_truck_col_driver.setCellValueFactory(new PropertyValueFactory("truck"));
        tbl_truck_col_truck.setCellValueFactory(new PropertyValueFactory("driver"));
        tbl_truck.setItems(data_truck);
    }
    
    public void setTableDataTruck(){
        data_truck.clear();
        DatabaseConnection db_truck = new DatabaseConnection("root", "localhost", "eyefleet", "isaiah");
        ResultSet rs_truck = db_truck.setQuery("select truck_id, driver_id, deployment_batch_id " +
                                        "from eyefleet.deployment_batch " +
                                        "where status = 'on_delivery'");
        
        try {
            while(rs_truck.next()){
                data_truck.add(new DataTruck(rs_truck.getString("truck_id"), 
                        rs_truck.getString("driver_id"),
                        rs_truck.getString("deployment_batch_id")));
            }
        } catch (Exception e) {
            System.out.println("ERROR FROM ORDERS ON DELIVERY @ setTableDataTruck: " + e);
        }
    }
    
    public void setTableFunctionTruck(){
        tbl_truck.setOnMouseClicked((MouseEvent) -> {
           setTableDataPartition(); 
           cbx_failed.setSelected(false);
           txt_reason.setDisable(true); 
           mnu_btn_action.setDisable(true);
        });
    }
    
    public void setTableDataPartition(){
        data_partition.clear();
        DataTruck truck_selected = (DataTruck) tbl_truck.getSelectionModel().getSelectedItem();
        
        if(truck_selected != null) {
            DatabaseConnection db_partition = new DatabaseConnection("root", "localhost", "eyefleet", "isaiah");
            ResultSet rs_partition = db_partition.setQuery("select order_id, partition_id, " +
                                                    "order_assignment.status, " +
                                                    "order_assignment_id, " +
                                                    "(select customer.name " +
                                                    "from customer " +
                                                    "where customer_id = (select customer_id " +
                                                    "from eyefleet.order " +
                                                    "where order.order_id = order_assignment.order_id)) as customer, " +
                                                    "(select product_id " +
                                                    "from eyefleet.order " +
                                                    "where order.order_id = order_assignment.order_id) as product, " +
                                                    "qty " +
                                                    "from order_assignment " +
                                                    "where deployment_batch_id = " + truck_selected.getBatch_id());

            try {
                while(rs_partition.next()){
                    data_partition.add(new DataPartition(rs_partition.getString("partition_id"),
                            rs_partition.getString("status"),
                            rs_partition.getString("order_assignment_id"),
                            rs_partition.getString("customer"),
                            rs_partition.getString("product"),
                            rs_partition.getString("qty"),
                            rs_partition.getString("order_id")));
                }
            } catch (Exception e) {
                System.out.println("ERROR FROM ORDERS ON DELIVERY @ setTableDataPartition: " + e);
            }    
        }
    }
    
    public void setButtonFunctionFailed(){
        cbx_failed.setOnMouseClicked((MouseEvent) -> {
            DataPartition partition_selected = (DataPartition) tbl_partition.getSelectionModel().getSelectedItem();
            
            if(cbx_failed.isSelected()){
                if(partition_selected != null){
                    txt_reason.setDisable(false); 
                    mnu_btn_action.setDisable(false);
                    controller.getMessage().shortToast("Warning: you cannot undo when failing order.", 3000);
                }
                else {
                    controller.getMessage().shortToast("Please select an order to fail.", 1000);
                    cbx_failed.setSelected(false);
                }
            }
            else{
                txt_reason.setDisable(true); 
                mnu_btn_action.setDisable(true);
            }
            
        });
    }
    
    public void setContextFunctionReSched(){
        ctx_re_sched.setOnAction((ActionEvent) -> {
            DataTruck truck_selected = (DataTruck) tbl_truck.getSelectionModel().getSelectedItem();
            DataPartition partition_selected = (DataPartition) tbl_partition.getSelectionModel().getSelectedItem();
            
            //change the status of the order_assignment via its id
            DatabaseConnection db_order_ass = new DatabaseConnection("root", "localhost", "eyefleet", "isaiah");
            db_order_ass.updateDatabase("update order_assignment " +
                                "set order_assignment.status = 'failed' " +
                                "where order_assignment_id = " + partition_selected.getAssignment_id());
            
            //save an instance of the failed order to the failed_order table
            DatabaseConnection db_failed_order = new DatabaseConnection("root", "localhost", "eyefleet", "isaiah");
            db_failed_order.updateDatabase("insert into failed_order(date, reason, failed_order.action, order_assignment_id, deployment_batch_id)\n" +
                                "values ( " +
                                "now(), " +
                                "'" + txt_reason.getText() + "', " +
                                "'re-schedule', " +
                                "" + partition_selected.getAssignment_id() + ", " +
                                truck_selected.getBatch_id()    +
                                ")");
            
            //get the detaiils of the order for inserting a new one
            DatabaseConnection db_details = new DatabaseConnection("root", "localhost", "eyefleet", "isaiah");
            ResultSet rs_details = db_details.setQuery("select * from eyefleet.order where order_id = " + partition_selected.order_id);
            
            Date date_ordered = null;
            String quantity = "";
            Date scheduled_date = null;
            String customer_id = "";
            String product_id = "";
            
            try {
                while(rs_details.next()){
                    date_ordered = rs_details.getDate("date_ordered");
                    scheduled_date = rs_details.getDate("scheduled_date");
                    customer_id = rs_details.getString("customer_id");
                    product_id = rs_details.getString("product_id");
                }
            } catch (Exception e) {
                System.out.println("ERROR FROM ORDERS ON DELIVERY @ setContextFunctionReSched: " + e);
            }
            
            //insert an order via details of the failed one
            DatabaseConnection db_new_order = new DatabaseConnection("root", "localhost", "eyefleet", "isaiah");
            db_new_order.updateDatabase("insert into eyefleet.order (date_ordered, status, quantity, scheduled_date, place, customer_id, product_id) " +
                                "values( " +
                                "'" + (date_ordered.getYear()+1900) + "-" + (date_ordered.getMonth()+1) + "-" + date_ordered.getDate() + "', " +
                                "'re-schedule', " +
                                " " + partition_selected.getQty() + ", " +
                                "'" + (scheduled_date.getYear()+1900) + "-" + (scheduled_date.getMonth()+1) + "-" + scheduled_date.getDate() + "', " +
                                "'stack'," +
                                " " + customer_id + ", " +
                                "'" + product_id + "' " +
                                ")");
            
            txt_reason.setDisable(true);
            cbx_failed.setDisable(true);
            mnu_btn_action.setDisable(true);
            
            //refresh the partition
            setTableDataPartition();
            
            //refreshes the order stack in its module
            controller.getOrder_stack().setTableOrderData();
            controller.getOrder_stack().data_vault_order.clear();

            controller.getOrder_stack().setTableCustomerData();
            controller.getOrder_stack().setTableDataOrderStackCustomer();
            controller.getOrder_stack().data_stack_order.clear();
            
            controller.getScheduling().setTableDataCTCustomer();
            controller.getMessage().shortToast("Order Failed and saved to stack for Re-scheduling.", 3000);
        });
    }
    
    public void setContextFunctionCancel(){
        ctx_cancel.setOnAction((ActionEvent) -> {
            DataTruck truck_selected = (DataTruck) tbl_truck.getSelectionModel().getSelectedItem();
            DataPartition partition_selected = (DataPartition) tbl_partition.getSelectionModel().getSelectedItem();
            
            //change the status of the order_assignment via its id
            DatabaseConnection db_order_ass = new DatabaseConnection("root", "localhost", "eyefleet", "isaiah");
            db_order_ass.updateDatabase("update order_assignment " +
                                "set order_assignment.status = 'failed' " +
                                "where order_assignment_id = " + partition_selected.getAssignment_id());
            
            //save an instance of the failed order to the failed_order table
            DatabaseConnection db_failed_order = new DatabaseConnection("root", "localhost", "eyefleet", "isaiah");
            db_failed_order.updateDatabase("insert into failed_order(date, reason, failed_order.action, order_assignment_id, deployment_batch_id)\n" +
                                "values ( " +
                                "now(), " +
                                "'" + txt_reason.getText() + "', " +
                                "'cancel', " +
                                "" + partition_selected.getAssignment_id() + ", " +
                                truck_selected.getBatch_id()    +
                                ")");
            
            txt_reason.setDisable(true);
            cbx_failed.setDisable(true);
            mnu_btn_action.setDisable(true);
            
            //refresh the partition
            setTableDataPartition();
            
            //refreshes the order stack in its module
            controller.getOrder_stack().setTableOrderData();
            controller.getOrder_stack().data_vault_order.clear();

            controller.getOrder_stack().setTableCustomerData();
            controller.getOrder_stack().setTableDataOrderStackCustomer();
            controller.getOrder_stack().data_stack_order.clear();
            
            controller.getScheduling().setTableDataCTCustomer();
            controller.getMessage().shortToast("Order set as failed.", 2000);
        });
    }
    
    public void setTableFunctionPartition(){
        tbl_partition.setOnMouseClicked((MouseEvent) -> {
            DataPartition partition_selected = (DataPartition) tbl_partition.getSelectionModel().getSelectedItem();
            
            if(partition_selected != null){
                    DatabaseConnection db_partition = new DatabaseConnection("root", "localhost", "eyefleet", "isaiah");
                    ResultSet rs_partition = db_partition.setQuery("select status, (select customer.name " +
                                                "from customer " +
                                                "where customer_id = (select customer_id " +
                                                "from eyefleet.order " +
                                                "where order_id = order_assignment.order_id)) as customer, " +
                                                "(select order.product_id " +
                                                "from eyefleet.order " +
                                                "where order_id = order_assignment.order_id) as product, " +
                                                "qty " +
                                                "from order_assignment " +
                                                "where order_assignment_id = '" + partition_selected.getAssignment_id() + "'");

                    try {
                        while(rs_partition.next()){
                            txt_customer.setText(rs_partition.getString("customer"));
                            txt_product.setText(rs_partition.getString("product"));
                            txt_qty.setText(rs_partition.getString("qty"));
                            if(rs_partition.getString("status").equals("failed")){
                                cbx_failed.setDisable(true);
                                txt_reason.setDisable(false);
                                DatabaseConnection db_reason = new DatabaseConnection("root", "localhost", "eyefleet", "isaiah");
                                ResultSet rs_reason = db_reason.setQuery("select reason from eyefleet.failed_order " +
                                                "where order_assignment_id = " + partition_selected.getAssignment_id());
                                rs_reason.next();
                                txt_reason.setText("ORDER FAILURE REASON:\n" + rs_reason.getString("reason"));
                            }
                            else{
                                cbx_failed.setDisable(false);
                                cbx_failed.setSelected(false);
                                txt_reason.setDisable(true);
                                txt_reason.setText("");
                            }
                        }
                        
                        mnu_btn_action.setDisable(true);
                    } catch (Exception e) {
                        System.out.println("ERROR FROM ORDERS ON DELIVERY @ setTableFunctionPartition: " + e);
                    }    
                    }
        });
    }
    
    public void setButtonFunctionSetAsDelivered(){
        btn_set_as_delivered.setOnMouseClicked((MouseEvent) -> {
            DataTruck truck_selected = (DataTruck) tbl_truck.getSelectionModel().getSelectedItem();
            
            if(truck_selected == null){
                controller.getMessage().shortToast("No trucks selected.", 1000);
            } else if(txt_hbdmtr.getText().isEmpty()){
                controller.getMessage().shortToast("Please input Hob Odometer count.", 2000);
            } else if(!traps.isNumerical(txt_hbdmtr.getText())){
                controller.getMessage().shortToast("Hob Odometer must be numerical.", 2000);
            }
            else {
                DatabaseConnection db_truck = new DatabaseConnection("root", "localhost", "eyefleet", "isaiah");
                //insert record for the delivery record based from the deploymet batch arrived
                //with details
                db_truck.updateDatabase("insert into eyefleet.delivery(final_count, " +
                                "km_driven, " +
                                "date_delivered, " +
                                "delivery.status, " +
                                "deployment_batch_id)" +
                                "values (" + txt_hbdmtr.getText() + ", " +
                                "" + txt_hbdmtr.getText() + " - (select initial_count " +
                                "from deployment_batch " +
                                "where deployment_batch_id = " + truck_selected.getBatch_id() + "), " +
                                "now(), " +
                                "'delivered'," +
                                "" + truck_selected.getBatch_id() + ")");

                //update the status of the batch set as delivered
                DatabaseConnection db_batch = new DatabaseConnection("root", "localhost", "eyefleet", "isaiah");
                db_batch.updateDatabase("update eyefleet.deployment_batch " +
                                "set status = 'delivered' " +
                                "where deployment_batch_id = '" + truck_selected.getBatch_id() + "'");

                //change all the status of the orders belong based from the order_assignment
                DatabaseConnection db_orders = new DatabaseConnection("root", "localhost", "eyefleet", "isaiah");
                ResultSet rs_orders = db_orders.setQuery("select order_id from order_assignment " +
                                "where deployment_batch_id = " + truck_selected.getBatch_id());

                try {
                    while(rs_orders.next()){
                        DatabaseConnection db_ass = new DatabaseConnection("root", "localhost", "eyefleet", "isaiah");
                        db_ass.updateDatabase("update eyefleet.order " +
                                "set status = 'delivered' " +
                                "where order_id = " + rs_orders.getString("order_id"));
                    }
                } catch (Exception e) {
                    System.out.println("ERROR FROM ORDERS ON DELIVERY @ setButtonFunctionSetAsDelivered: " + e);
                }

                //traces the order assignmnent belong to the batch and
                //sets the status into delivered
                DatabaseConnection db_order_assign = new DatabaseConnection("root", "localhost", "eyefleet", "isaiah");
                db_order_assign.updateDatabase("update order_assignment " +
                                "set order_assignment.status = 'delivered' " +
                                "where order_assignment.status = 'on_delivery' and " +
                                "deployment_batch_id = " + truck_selected.getBatch_id());

                setTableDataTruck();
                setTableDataPartition();

                controller.getDelivery().setDeliveryToday();
                controller.getMessage().shortToast("Delivery set as Delivered. Review it at Delivery Module.", 3000);
            }
        });
        
    }
    
    public class DataTruck{

        private String truck;
        private String driver;
        private String batch_id;
        
        public DataTruck(String truck,
                String driver,
                String batch_id) {
            
            this.batch_id = batch_id;
            this.driver = driver;
            this.truck = truck;
            
        }

        public String getTruck() {
            return truck;
        }

        public void setTruck(String truck) {
            this.truck = truck;
        }

        public String getDriver() {
            return driver;
        }

        public void setDriver(String driver) {
            this.driver = driver;
        }

        public String getBatch_id() {
            return batch_id;
        }

        public void setBatch_id(String batch_id) {
            this.batch_id = batch_id;
        }
        
    }
 
    public class DataPartition{

        private String partition;
        private String status;
        private String assignment_id;
        private String customer;
        private String product;
        private String qty;
        private String order_id;
        
        public DataPartition(String partition,
                String status,
                String assignment_id,
                String customer,
                String product,
                String qty,
                String order_id) {
            
            this.partition = partition;
            this.status = status;
            this.assignment_id = assignment_id;
            this.customer = customer;
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

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getAssignment_id() {
            return assignment_id;
        }

        public void setAssignment_id(String assignment_id) {
            this.assignment_id = assignment_id;
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
