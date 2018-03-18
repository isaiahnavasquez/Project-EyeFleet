/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Orders;

import eyefleet.DatabaseConnection;
import eyefleet.LayoutController;
import java.sql.ResultSet;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 *
 * @author IsaiahJan
 */
public class Delivery {

    //controller
    LayoutController controller;
    
    //tables
    TableView tbl_truck;
    TableView tbl_order;
    
    //columns
    TableColumn tbl_truck_col_truck;
    TableColumn tbl_truck_col_driver;
    TableColumn tbl_order_col_customer;
    TableColumn tbl_order_col_product;
    TableColumn tbl_order_col_qty;
    TableColumn tbl_order_col_status;
    
    //fields
    TextField txt_km_driven;
    TextField txt_fuel_consump;
    DatePicker dtp_date;
    TextArea txt_reason;
    
    //button
    Label btn_delivery_today;
    
    //data
    ObservableList<DataOrder> data_order = FXCollections.observableArrayList();
    ObservableList<DataTruck> data_truck = FXCollections.observableArrayList();
    
    public Delivery(LayoutController controller) {
        //controller
        this.controller = controller;
        
        //tables
        tbl_truck = controller.getDelivered_orders_tbl_trucks();
        tbl_order = controller.getDelivered_orders_tbl_orders();
        
        //columns
        tbl_order_col_customer = controller.getDelivered_orders_tbl_orders_col_customer();
        tbl_order_col_product = controller.getDelivered_orders_tbl_orders_col_product();
        tbl_order_col_qty = controller.getDelivered_orders_tbl_orders_col_quantity();
        tbl_order_col_status = controller.getDelivered_orders_tbl_orders_col_status();
        tbl_truck_col_driver = controller.getDelivered_orders_tbl_trucks_col_driver();
        tbl_truck_col_truck = controller.getDelivered_orders_tbl_trucks_col_truck_id();
        
        //fields
        txt_fuel_consump = controller.getDelivered_orders_txt_fuel_consumpt();
        txt_km_driven = controller.getDelivered_orders_txt_km_driven();
        dtp_date = controller.getDelivered_orders_dtp_date();
        txt_reason = controller.getDelivered_order_txt_reason();
    
        //button
        btn_delivery_today = controller.getDelivered_order_btn_delivery_today();
        
        //initialize module
        initializeModule();
    }
    
    public void initializeModule(){
        setTableColumns();
        setFieldFunctionDate();
        setTableFunctionTruck();
        setButtonFunctionDeliveryToday();
        setTableFunctionOrder();
    }
    
    public void setTableColumns(){
        tbl_order_col_customer.setCellValueFactory(new PropertyValueFactory("customer"));
        tbl_order_col_product.setCellValueFactory(new PropertyValueFactory("product"));
        tbl_order_col_qty.setCellValueFactory(new PropertyValueFactory("qty"));
        tbl_order_col_status.setCellValueFactory(new PropertyValueFactory("status"));
        tbl_truck_col_driver.setCellValueFactory(new PropertyValueFactory("driver"));
        tbl_truck_col_truck.setCellValueFactory(new PropertyValueFactory("truck"));
        tbl_order.setItems(data_order);
        tbl_truck.setItems(data_truck);
    }
    
    public void setFieldFunctionDate(){
        dtp_date.setOnAction((ActionEvent) -> {
            setTableDataTruck();
            controller.getMessage().shortToast("Orders Loaded.", 1000);
            data_order.clear();
        });
    }
    
    public void setTableDataTruck(){
        if(dtp_date.getValue() != null){
            data_truck.clear();
            DatabaseConnection db_truck = new DatabaseConnection("root", "localhost", "eyefleet", "isaiah");
            ResultSet rs_truck = db_truck.setQuery("select (select truck_id " +
                                    "from deployment_batch " +
                                    "where deployment_batch_id = delivery.deployment_batch_id) as truck, " +
                                    "deployment_batch_id, " +
                                    "(select driver_id " +
                                    "from deployment_batch " +
                                    "where deployment_batch_id = delivery.deployment_batch_id) as driver " +
                                    "from delivery " +
                                    "where date_delivered = '" + dtp_date.getValue().getYear() + "-" + 
                                                dtp_date.getValue().getMonthValue() + 
                                                "-" + dtp_date.getValue().getDayOfMonth() + "'");

            try {
                while(rs_truck.next()){
                    data_truck.add(new DataTruck(rs_truck.getString("deployment_batch_id"), 
                            rs_truck.getString("truck"), 
                            rs_truck.getString("driver")));
                }
            } catch (Exception e) {
                System.out.println("ERROR ON DELIVERY setTableDataTruck: " + e);
            }
            }
    }
    
    public void setTableFunctionTruck(){
        tbl_truck.setOnMouseClicked((MouseEvent) -> {
            DataTruck truck_selected  = (DataTruck) tbl_truck.getSelectionModel().getSelectedItem();
            
            if(truck_selected != null) {
                data_order.clear();
                DatabaseConnection db_order = new DatabaseConnection("root", "localhost", "eyefleet", "isaiah");
                ResultSet rs_order = db_order.setQuery("select (select order.product_id " +
                                    "from eyefleet.order " +
                                    "where order.order_id = order_assignment.order_id) as product, " +
                                    "qty, order_id, order_assignment_id, " +
                                    "order_assignment.status, " +
                                    "(select customer.name " +
                                    "from customer " +
                                    "where customer.customer_id = (select order.customer_id " +
                                    "from eyefleet.order " +
                                    "where order.order_id = order_assignment.order_id)) as customer " +
                                    "from order_assignment " +
                                    "where deployment_batch_id = " + truck_selected.getBatch_id());
                
                DatabaseConnection db_batch_details = new DatabaseConnection("root", "localhost", "eyefleet", "isaiah");
                ResultSet rs_batch_details = db_batch_details.setQuery("select final_count, km_driven " +
                                    "from delivery " +
                                    "where deployment_batch_id = " + truck_selected.getBatch_id());
                
                try {
                    while(rs_order.next()){
                        data_order.add(new DataOrder(rs_order.getString("order_id"), 
                                rs_order.getString("customer"), 
                                rs_order.getString("product"), 
                                rs_order.getString("qty"), 
                                rs_order.getString("status"),
                                rs_order.getString("order_assignment_id")));
                    }
                    
                    while(rs_batch_details.next()){
                        txt_fuel_consump.setText(rs_batch_details.getString("final_count"));
                        txt_km_driven.setText(rs_batch_details.getString("km_driven"));
                    }
                    
                } catch (Exception e) {
                    System.out.println("ERROR ON DELIVERY setTableFunctionTruck: " + e);
                }
                
                txt_reason.setText("");
            }
        });
    }
    
    public void setDeliveryToday(){
            data_truck.clear();
            DatabaseConnection db_truck = new DatabaseConnection("root", "localhost", "eyefleet", "isaiah");
            ResultSet rs_truck = db_truck.setQuery("select (select truck_id " +
                                    "from deployment_batch " +
                                    "where deployment_batch_id = delivery.deployment_batch_id) as truck, " +
                                    "deployment_batch_id, " +
                                    "(select driver_id " +
                                    "from deployment_batch " +
                                    "where deployment_batch_id = delivery.deployment_batch_id) as driver " +
                                    "from delivery " +
                                    "where date_delivered = curdate()");
            
            try {
                while(rs_truck.next()){
                    data_truck.add(new DataTruck(rs_truck.getString("deployment_batch_id"), 
                            rs_truck.getString("truck"), 
                            rs_truck.getString("driver")));
                }
            } catch (Exception e) {
                System.out.println("ERROR ON DELIVERY setTableDataTruck: " + e);
            }
            
            data_order.clear();
    }
    
    public void setTableFunctionOrder(){
        tbl_order.setOnMouseClicked((MouseEvent) -> {
            DataOrder order_selected = (DataOrder) tbl_order.getSelectionModel().getSelectedItem();

            String reason = "";
            String remarks = "";

            if(order_selected != null){
                if(order_selected.getStatus().equals("failed")){
                    DatabaseConnection db_order = new DatabaseConnection("root", "localhost", "eyefleet", "isaiah");
                    ResultSet rs_order = db_order.setQuery("select reason, action from failed_order where order_assignment_id = " + order_selected.getAssignment_id());
                    try {
                        while(rs_order.next()){
                            reason = rs_order.getString("reason");
                            remarks = rs_order.getString("action");
                            txt_reason.setText("REASON:\n" + reason + "\nACTION:\n" + remarks);
                        }
                    } catch (Exception e) {
                        System.out.println("ERROR ON DELIVERY setTableFunctionOrder: " + e);
                    }
                }
                else {
                    txt_reason.setText("");
                }
            }
        });
    }
    
    public void setButtonFunctionDeliveryToday(){
        btn_delivery_today.setOnMouseClicked((MouseEvent) -> {
            setDeliveryToday();
            controller.getMessage().shortToast("Orders Loaded.", 1000);
        });
    }
    
    public class DataTruck{

        private String batch_id;
        private String truck;
        private String driver;
        
        public DataTruck(
            String batch_id,
            String truck,
            String driver) {
            
            this.batch_id = batch_id;
            this.driver = driver;
            this.truck = truck;
            
        }

        public String getBatch_id() {
            return batch_id;
        }

        public void setBatch_id(String batch_id) {
            this.batch_id = batch_id;
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
        
    }
    
    public class DataOrder {

        private String order_id;
        private String customer;
        private String product;
        private String qty;
        private String status;
        private String assignment_id;
        
        public DataOrder(
            String order_id,
            String customer,
            String product,
            String qty,
            String status,
            String assignment_id) {
            
            this.customer = customer;
            this.order_id = order_id;
            this.product = product;
            this.qty = qty;
            this.status = status;
            this.assignment_id = assignment_id;
            
        }

        public String getOrder_id() {
            return order_id;
        }

        public void setOrder_id(String order_id) {
            this.order_id = order_id;
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

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        /**
         * @return the assignment_id
         */
        public String getAssignment_id() {
            return assignment_id;
        }

        /**
         * @param assignment_id the assignment_id to set
         */
        public void setAssignment_id(String assignment_id) {
            this.assignment_id = assignment_id;
        }
        
    }
    
}
