/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Orders;

import eyefleet.DatabaseConnection;
import eyefleet.LayoutController;
import java.sql.ResultSet;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Duration;

/**
 *
 * @author IsaiahJan
 */
public class Scheduling {

    //controller
    LayoutController controller;
    
    //tables customer-truck
    TableView ct_tbl_truck;
    TableView ct_tbl_partition;
    TableView ct_tbl_customer;
    
    //columns customer-truck
    TableColumn ct_tbl_truck_col_truck_id;
    TableColumn ct_tbl_truck_col_capacity;
    TableColumn ct_tbl_partition_col_customer;
    TableColumn ct_tbl_partition_col_qty;
    TableColumn ct_tbl_customer_col_customer;
    TableColumn ct_tbl_customer_col_qty;
    
    //tables order-partition
    TableView op_tbl_truck;
    TableView op_truck_tbl_partition;
    TableView op_customer_tbl_customer;
    
    //columns order-partition
    TableColumn op_tbl_truck_col_truck_id;
    TableColumn op_tbl_partition_col_partition_id;
    TableColumn op_tbl_partition_col_capacity;
    TableColumn op_tbl_partition_col_customer;
    TableColumn op_tbl_partition_col_product;
    TableColumn op_tbl_partition_col_qty;
    TableColumn op_tbl_customer_col_customer;
    TableColumn op_tbl_customer_col_product;
    TableColumn op_tbl_customer_col_qty;
    
    //buttons customer-truck
    Label btn_ct_load;
    Label btn_ct_unload;
    Label btn_ct_proceed;
    Label btn_ct_restart;
    
    //buttons order-partition
    Label btn_op_load;
    Label btn_op_unload;
    Label btn_op_proceed;
    Label btn_op_restart;
    Label btn_op_cancel;
    
    //button
    Label btn_see_deployment;
    Button btn_cancel_analysis;
    
    //data customer-truck
    ObservableList<CTDataCustomer> ct_data_customer = FXCollections.observableArrayList();
    ObservableList<CTDataPartition> ct_data_partition = FXCollections.observableArrayList();
    ObservableList<CTDataTruck> ct_data_truck = FXCollections.observableArrayList();
    
    //data order-partition
    ObservableList<OPCustomer> op_data_customer = FXCollections.observableArrayList();
    ObservableList<OPDataPartition> op_data_partition = FXCollections.observableArrayList();
    ObservableList<OPDataTruck> op_data_truck = FXCollections.observableArrayList();
    
    
    public Scheduling(LayoutController controller) {
        //load controller
        this.controller = controller;
        
        //load tables customer-truck
        ct_tbl_customer = controller.getManual_ct_customer_tbl_customer();
        ct_tbl_partition = controller.getManual_ct_truck_tbl_customer();
        ct_tbl_truck = controller.getManual_ct_truck_tbl_truck();
        
        //load tables order-partition
        op_customer_tbl_customer = controller.getManual_op_customer_tbl_customer();
        op_tbl_truck = controller.getManual_op_truck_tbl_truck();
        op_truck_tbl_partition = controller.getManual_op_truck_tbl_partition();
        
        //load columns customer-truck
        ct_tbl_customer_col_customer = controller.getManual_ct_customer_tbl_customer_col_customer();
        ct_tbl_customer_col_qty = controller.getManual_ct_customer_tbl_customer_col_qty();
        ct_tbl_partition_col_customer = controller.getManual_ct_truck_tbl_customer_col_customer();
        ct_tbl_partition_col_qty = controller.getManual_ct_truck_tbl_customer_col_qty();
        ct_tbl_truck_col_capacity = controller.getManual_ct_truck_tbl_truck_col_capacity();
        ct_tbl_truck_col_truck_id = controller.getManual_ct_truck_tbl_truck_col_truck_id();
        
        //load columns order-partition
        op_tbl_customer_col_customer = controller.getManual_op_customer_tbl_customer_col_customer();
        op_tbl_customer_col_product = controller.getManual_op_customer_tbl_customer_col_product();
        op_tbl_customer_col_qty = controller.getManual_op_customer_tbl_customer_col_qty();
        op_tbl_partition_col_capacity = controller.getManual_op_truck_tbl_partition_col_capacity();
        op_tbl_partition_col_customer = controller.getManual_op_truck_tbl_partition_col_customer();
        op_tbl_partition_col_partition_id = controller.getManual_op_truck_tbl_partition_col_partition_id();
        op_tbl_partition_col_product = controller.getManual_op_truck_tbl_partition_col_product();
        op_tbl_partition_col_qty = controller.getManual_op_truck_tbl_partition_col_qty();
        op_tbl_truck_col_truck_id = controller.getManual_op_truck_tbl_truck_col_truck_id();
        
        //load buttons customer-truck
        btn_ct_load = controller.getManual_ct_btn_load();
        btn_ct_proceed = controller.getManual_ct_btn_proceed();
        btn_ct_restart = controller.getManual_ct_btn_restart();
        btn_ct_unload = controller.getManual_ct_btn_unload();
        
        //load buttons for order-partition
        btn_op_load = controller.getManual_op_btn_load();
        btn_op_proceed = controller.getManual_op_btn_proceed();
        btn_op_restart = controller.getManual_op_btn_restart();
        btn_op_unload = controller.getManual_op_btn_unload();
        btn_op_cancel = controller.getmanual_op_btn_cancel();
        
        
        //button
        btn_see_deployment = controller.getManual_btn_see_deployment();
        
        //initialize module
        initializeModule();
    }
    
    public void initializeModule(){
        setTabelColumns();
        setTableDataCTCustomer();
        setTableDataCTTruck();
        setTableFunctionCTruck();
        setButtonFunctionCTLoad();
        setButtonFunctionCTunload();
        setButtonFunctionCTRestart();
        setButtonFunctionProceed();
        setButtonFunctionOPLoad();
        setButtonFunctionOPUnload();
        setButtonFunctionOPCancel();
        setButtonFunctionOPRestart();
        setButtonFunctionSeeDeployment();
        OPReset();
    }
    
    public void setTabelColumns(){
        //load table columns order-partition
        op_tbl_customer_col_customer.setCellValueFactory(new PropertyValueFactory("customer"));
        op_tbl_customer_col_product.setCellValueFactory(new PropertyValueFactory("product"));
        op_tbl_customer_col_qty.setCellValueFactory(new PropertyValueFactory("qty"));
        op_tbl_partition_col_capacity.setCellValueFactory(new PropertyValueFactory("capacity"));
        op_tbl_partition_col_customer.setCellValueFactory(new PropertyValueFactory("customer"));
        op_tbl_partition_col_partition_id.setCellValueFactory(new PropertyValueFactory("partition_id"));
        op_tbl_partition_col_product.setCellValueFactory(new PropertyValueFactory("product"));
        op_tbl_partition_col_qty.setCellValueFactory(new PropertyValueFactory("qty"));
        op_tbl_truck_col_truck_id.setCellValueFactory(new PropertyValueFactory("truck_id"));
        op_customer_tbl_customer.setItems(op_data_customer);
        op_tbl_truck.setItems(op_data_truck);
        op_truck_tbl_partition.setItems(op_data_partition);
        
        //load table columns customer-truck
        ct_tbl_customer_col_customer.setCellValueFactory(new PropertyValueFactory("customer"));
        ct_tbl_customer_col_qty.setCellValueFactory(new PropertyValueFactory("qty"));
        ct_tbl_partition_col_customer.setCellValueFactory(new PropertyValueFactory("customer"));
        ct_tbl_partition_col_qty.setCellValueFactory(new PropertyValueFactory("qty"));
        ct_tbl_truck_col_capacity.setCellValueFactory(new PropertyValueFactory("capacity"));
        ct_tbl_truck_col_truck_id.setCellValueFactory(new PropertyValueFactory("truck_id"));
        ct_tbl_customer.setItems(ct_data_customer);
        ct_tbl_partition.setItems(ct_data_partition);
        ct_tbl_truck.setItems(ct_data_truck);
    }
    
    public void setTableDataCTCustomer(){
        ct_data_customer.clear();
        DatabaseConnection db_customer = new DatabaseConnection("root", "localhost", "eyefleet", "isaiah");
        ResultSet rs_customer = db_customer.setQuery("select (select customer.name "
                + "from customer "
                + "where customer.customer_id = order.customer_id) as 'customer', "
                + "sum(quantity) as 'qty'\n" +
                    "from eyefleet.order " +
                    "where (status = 'received' or status = 're-schedule') and place = 'stack' " +
                    "group by customer_id");
        try {
            while(rs_customer.next()){
                ct_data_customer.add(new CTDataCustomer(rs_customer.getString("customer"), rs_customer.getString("qty")));
            }
        } catch (Exception e) {
            System.out.println("ERROR FROM SCHEDULING AT setTableDataCTCustomer");
        }
    }
    
    public void setTableDataCTTruck(){
            ct_data_truck.clear();
            DatabaseConnection db_truck = new DatabaseConnection("root", "localhost", "eyefleet", "isaiah");
            ResultSet rs_truck = db_truck.setQuery("select truck_id, total_capacity " +
                                                    "from truck " +
                                                    "where truck.status = 'available'");
            
            try {
                while(rs_truck.next()){
                    ct_data_truck.add(new CTDataTruck(rs_truck.getString("truck_id"), rs_truck.getString("total_capacity")));
                }
        } catch (Exception e) {
            System.out.println("ERROR FROM SCHEDULING AT setTableDataCTTruck");
        }
    }
    
    public void setTableFunctionCTruck(){
        ct_tbl_truck.setOnMouseClicked((MouseEvent) -> {
            setTableDataCTPartition();
        });
    }
    
    public void setButtonFunctionCTLoad(){
        btn_ct_load.setOnMouseClicked((MouseEvent) -> {
            CTDataCustomer customer_selected = (CTDataCustomer) ct_tbl_customer.getSelectionModel().getSelectedItem();
            CTDataTruck truck_selected = (CTDataTruck) ct_tbl_truck.getSelectionModel().getSelectedItem();
           
            if (customer_selected != null && truck_selected != null){
                
                int total_capacity = 0;
                
                for (int counter = 0; counter < ct_data_partition.size(); counter++){
                    total_capacity += Integer.parseInt(ct_data_partition.get(counter).getQty());
                }
                
                if(Integer.parseInt(truck_selected.getCapacity()) >= (Integer.parseInt(customer_selected.getQty()) + total_capacity)){
                    DatabaseConnection db_partition = new DatabaseConnection("root", "localhost", "analysis", "isaiah");
                        db_partition.updateDatabase("insert into analysis.customer_truck_load(customer, qty, truck) " +
                                    "values ('" + customer_selected.getCustomer() + "', "
                                + "" + customer_selected.getQty() + ", "
                                + "'" + truck_selected.getTruck_id() + "')");
                        
                        ct_data_customer.remove(ct_tbl_customer.getSelectionModel().getSelectedIndex());
                        setTableDataCTPartition();
                        controller.getMessage().shortToast("Loaded.", 1000);
                }
                else{
                    controller.getMessage().shortToast("Cannot Load. Customer's quantity is larger than the Truck's capacity.", 1000);
                }
                
            }
            else{
                controller.getMessage().shortToast("Please select Truck and the Customer to Load.", 1000);
            }
        });
    }
    
    public void setTableDataCTPartition(){
        ct_data_partition.clear();
        CTDataTruck truck_selected = (CTDataTruck) ct_tbl_truck.getSelectionModel().getSelectedItem();
        if(truck_selected != null){
           DatabaseConnection db_partition = new DatabaseConnection("root", "localhost", "analysis", "isaiah");
           ResultSet rs_partition = db_partition.setQuery("select * from customer_truck_load where truck = '" + truck_selected.getTruck_id() + "'" );
               try {
                   while(rs_partition.next()){
                       ct_data_partition.add(new CTDataPartition(rs_partition.getString("truck"),
                               rs_partition.getString("customer"), rs_partition.getString("qty")));
                   }
               } catch (Exception e) {
                   System.out.println("ERROR FROM SCHEDULING AT setButtonFunctionCTLoad");
               }
        }
    }
    
    public void setButtonFunctionCTunload(){
        btn_ct_unload.setOnMouseClicked((MouseEvent) -> {
            DatabaseConnection db_partition = new DatabaseConnection("root", "localhost", "analysis", "isaiah");
            CTDataPartition partition_selected = (CTDataPartition) ct_tbl_partition.getSelectionModel().getSelectedItem();
            if(partition_selected != null) {
                ct_data_customer.add(new CTDataCustomer(partition_selected.getCustomer(), partition_selected.getQty()));
                db_partition.updateDatabase("delete from customer_truck_load where "
                        + "customer = '" + partition_selected.getCustomer() + "' AND "
                        + "qty = " + partition_selected.getQty() + " AND "
                        + "truck = '" + partition_selected.getTruck() + "'");

                setTableDataCTPartition();
                controller.getMessage().shortToast("Customer Unloaded.", 1000);
            }
            else{
                controller.getMessage().shortToast("Please select a Customer to unload.", 2000);
            }
        });
    }
    
    public void setButtonFunctionCTRestart(){
        btn_ct_restart.setOnMouseClicked((MouseEvent) -> {
            CTRestart();
            controller.getMessage().shortToast("Loads cleared.", 1000);
        });
    }
    
    public void CTRestart(){
        DatabaseConnection db_partition = new DatabaseConnection("root", "localhost", "analysis", "isaiah");
            db_partition.updateDatabase("delete from customer_truck_load");
            setTableDataCTCustomer();
            setTableDataCTTruck();
            setTableDataCTPartition();
    }
    
    public void setButtonFunctionProceed(){
        btn_ct_proceed.setOnMouseClicked((MouseEvent) -> {
            Timeline timeline = new Timeline(new KeyFrame(new Duration(500), new KeyValue(controller.getManual_scrl().hvalueProperty(), .63)));
            timeline.play();
            timeline.setOnFinished((ActionEvent) -> {
                orderToProductReprocess();
            });
        });
    }
    
    public void orderToProductReprocess(){
        controller.getMessage().shortToast("Calculating", 1000);
        setTableDataOPTruck();
        setTableFunctionOPTruck();
        initializePartitionData();
        initializeOPCustomerData();
        controller.getMessage().shortToast("Done", 1000);
    }
    
    public void initializePartitionData(){
        DatabaseConnection db_partition_load_reset = new DatabaseConnection("root", "localhost", "analysis", "isaiah");
        db_partition_load_reset.updateDatabase("delete from order_partition_load");
        
        DatabaseConnection db_ct_analysis = new DatabaseConnection("root", "localhost", "analysis", "isaiah");
        ResultSet rs_ct_analysis = db_ct_analysis.setQuery("select truck from customer_truck_load "
                + "group by truck");
        
        try {
            while(rs_ct_analysis.next()){
                
                DatabaseConnection db_partition = new DatabaseConnection("root", "localhost", "eyefleet", "isaiah");
                ResultSet rs_partition = db_partition.setQuery("select partition_id, truck_id, capacity from partition where truck_id = '" + rs_ct_analysis.getString("truck") + "' order by partition.position");
                
                while(rs_partition.next()){
                    DatabaseConnection db_insert_partition = new DatabaseConnection("root", "localhost", "analysis", "isaiah");
                    db_insert_partition.updateDatabase("insert into order_partition_load(truck, partition_id, capacity) "
                            + "values ('" + rs_partition.getString("truck_id") + "', '" + rs_partition.getString("partition_id") + "', "
                            + "" + rs_partition.getString("capacity") + ")");
                }
                
            }
        } catch (Exception e) {
            System.out.println("ERROR FROM SCHEDULING AT initializePartitionData");
        }
    }
    
    public void setTableDataOPPartition(){
        op_data_partition.clear();
        OPDataTruck truck_selected = (OPDataTruck) op_tbl_truck.getSelectionModel().getSelectedItem();
        if(truck_selected != null) {
            DatabaseConnection db_partition = new DatabaseConnection("root", "localhost", "analysis", "isaiah");
            ResultSet rs_partition = db_partition.setQuery("SELECT * FROM analysis.order_partition_load where truck = '" + truck_selected.getTruck_id() + "'");
            
            try {
                while(rs_partition.next()) {
                    op_data_partition.add(new OPDataPartition(
                            rs_partition.getString("order_id"),
                            rs_partition.getString("truck"),
                            rs_partition.getString("partition_id"),
                            rs_partition.getString("capacity"),
                            rs_partition.getString("customer"),
                            rs_partition.getString("product"),
                            rs_partition.getString("qty")));
                }
            } catch (Exception e) {
                setTableDataOPTruck();
            }
        }
        
    }
    
    public void setTableDataOPTruck(){
        op_data_truck.clear();
        DatabaseConnection db_truck = new DatabaseConnection("root", "localhost", "analysis", "isaiah");
        ResultSet rs_truck = db_truck.setQuery("select distinct truck from analysis.customer_truck_load");
        
        try {
            while(rs_truck.next()){
                op_data_truck.add(new OPDataTruck(rs_truck.getString("truck")));
            }
        } catch (Exception e) {
            System.out.println("ERROR FROM SCHEDULING AT setTableDataOPTruck");
        }
    }
    
    public void setTableFunctionOPTruck(){
        op_tbl_truck.setOnMouseClicked((MouseEvent) -> {
            setTableDataOPPartition();
            setTableDataOPCustomer();
        });
    }
    
    public void setButtonFunctionOPLoad(){
        btn_op_load.setOnMouseClicked((MouseEvent) -> {
            OPCustomer order_selected = (OPCustomer) op_customer_tbl_customer.getSelectionModel().getSelectedItem();
            OPDataPartition partition_selected = (OPDataPartition) op_truck_tbl_partition.getSelectionModel().getSelectedItem();
            
            if (partition_selected != null && order_selected != null){
            
                int capacity = Integer.parseInt(partition_selected.getCapacity());
                int quantity = Integer.parseInt(order_selected.getQty());

                //if it fits
                if(quantity <= capacity){
                        DatabaseConnection db_op_load = new DatabaseConnection("root", "localhost", "analysis", "isaiah");
                        db_op_load.updateDatabase("update order_partition_load "
                                + "set customer = '" + order_selected.getCustomer() + "', "
                                + "product = '" + order_selected.getProduct() + "', "
                                + "qty = " + order_selected.getQty() + ", "
                                + "order_id = '" + order_selected.getOrder_id() + "' "
                                + "where partition_id = '" + partition_selected.getPartition_id() + "'");

                        DatabaseConnection db_update_order = new DatabaseConnection("root", "localhost", "analysis", "isaiah");
                        db_update_order.updateDatabase("update analysis.op_customer " +
                                        "set op_customer.status = 'loaded', "
                                        + "qty = 0 " +
                                        "where customer = '" + order_selected.getCustomer() + "' AND " +
                                        "product = '" + order_selected.getProduct() + "' AND " +
                                        "qty = '" + order_selected.getQty() + "'");
                        setTableDataOPCustomer();
                        setTableDataOPPartition();
                        controller.getMessage().shortToast("Order Loaded.", 1000);
                }

                //if it does not
                if(quantity > capacity){
                        DatabaseConnection db_partition = new DatabaseConnection("root", "localhost", "analysis", "isaiah");
                        db_partition.updateDatabase("update order_partition_load "
                                + "set customer = '" + order_selected.getCustomer() + "', "
                                + "product = '" + order_selected.getProduct() + "', "
                                + "qty = " + capacity + ", "
                                + "order_id = '" + order_selected.getOrder_id() + "' "
                                + "where partition_id = '" + partition_selected.getPartition_id() + "'");

                        DatabaseConnection db_order = new DatabaseConnection("root", "localhost", "analysis", "isaiah");
                        db_order.updateDatabase("update analysis.op_customer " +
                                        "set qty = qty - " + capacity + " " +
                                        "where customer = '" + order_selected.getCustomer() + "' AND " +
                                        "product = '" + order_selected.getProduct() + "' AND " +
                                        "qty = '" + order_selected.getQty() + "'");
                        setTableDataOPCustomer();
                        setTableDataOPPartition();
                        controller.getMessage().shortToast("Order Loaded (Splitted)", 1000);
                }
                } else {
                
                controller.getMessage().shortToast("Please select Partition and an Order to load.", 2000);
            }
            
        });
    }
    
    public void initializeOPCustomerData(){
        op_data_customer.clear();
                DatabaseConnection db_truck = new DatabaseConnection("root", "localhost", "analysis", "isaiah");
                ResultSet rs_truck = db_truck.setQuery("select distinct truck from customer_truck_load");
                
                try {
                    
                    while(rs_truck.next()){
                            DatabaseConnection db_customer = new DatabaseConnection("root", "localhost", "analysis", "isaiah");
                            ResultSet rs_customer = db_customer.setQuery("select customer from customer_truck_load where\n" +
                                                        "truck = '" + rs_truck.getString("truck") + "' group by customer");
                        
                                while(rs_customer.next()){
                                        DatabaseConnection db_order = new DatabaseConnection("root", "localhost", "eyefleet", "isaiah");
                                        ResultSet rs_order = db_order.setQuery("select order_id, order.product_id, order.quantity " +
                                                            "from eyefleet.order " +
                                                            "where order.customer_id = (select eyefleet.customer.customer_id " +
                                                            "from eyefleet.customer " +
                                                            "where eyefleet.customer.name = '" + rs_customer.getString("customer") + "' and "
                                                            + "(status = 'received' or status = 're-schedule') and place = 'stack' )");
                                
                                            while(rs_order.next()){
                                                    DatabaseConnection db_insert_order = new DatabaseConnection("root", "localhost", "analysis", "isaiah");
                                                    db_insert_order.updateDatabase("insert into op_customer (op_customer_id, customer, qty, product, status, truck) "
                                                            + "values (" + rs_order.getString("order_id") + ", '" + rs_customer.getString("customer") + "', "
                                                            + "" + rs_order.getString("quantity") + ","
                                                            + " '" + rs_order.getString("product_id") + "', "
                                                            + "'unloaded', '" + rs_truck.getString("truck") + "')");
                                        }
                        }
                    }
                } catch (Exception e) {
                    System.out.println("ERROR FROM SCHEDULING AT setTableFunctionOPTruck" + e);
                }
    }
    
    public void setTableDataOPCustomer(){
        op_data_customer.clear();
        
        OPDataTruck truck_selected = (OPDataTruck) op_tbl_truck.getSelectionModel().getSelectedItem();
        if(truck_selected != null){
            DatabaseConnection db_order = new DatabaseConnection("root", "localhost", "analysis", "isaiah");
            ResultSet rs_order = db_order.setQuery("select * from analysis.op_customer "
                    + "where truck = '" + truck_selected.getTruck_id() + "' AND status = 'unloaded'");
            
            try {
                while(rs_order.next()){
                    op_data_customer.add(new OPCustomer(
                            rs_order.getString("op_customer_id"),
                            rs_order.getString("customer"),
                            rs_order.getString("product"), 
                            rs_order.getString("qty")));
                }
            } catch (Exception e) {
                System.out.println("ERROR FROM SCHEDULING AT setTableDataOPCustomer" + e);
            }
        }
    }
    
    public void setButtonFunctionOPUnload(){
        btn_op_unload.setOnMouseClicked((MouseEvent) -> {
                OPDataPartition partition_selected = (OPDataPartition) op_truck_tbl_partition.getSelectionModel().getSelectedItem();
                if(partition_selected != null){
                    
                    DatabaseConnection db_partition = new DatabaseConnection("root", "localhost", "analysis", "isaiah");
                    db_partition.updateDatabase("update analysis.order_partition_load "
                            + "set customer = null, "
                            + "product = null, "
                            + "qty = null, "
                            + "order_id = null "
                            + "where partition_id = '" + partition_selected.getPartition_id() + "'");
                    
                    DatabaseConnection db_order = new DatabaseConnection("root", "localhost", "analysis", "isaiah");
                    db_order.updateDatabase("update analysis.op_customer "
                            + "set qty = qty + " + partition_selected.getQty() + ", "
                            + "status = 'unloaded' "
                            + "where op_customer_id = " + partition_selected.getOrder_id());
                    
                    setTableDataOPCustomer();
                    setTableDataOPPartition();

                }    
        });
    }
    
    public void setButtonFunctionOPCancel(){ 
        btn_op_cancel.setOnMouseClicked((MouseEvent) -> {
            OPDataTruck truck_selected = (OPDataTruck) op_tbl_truck.getSelectionModel().getSelectedItem();
            Timeline timeline = new Timeline(new KeyFrame(new Duration(500), new KeyValue(controller.getManual_scrl().hvalueProperty(), 0)));
            timeline.play();
            timeline.setOnFinished((ActionEvent) -> {
                DatabaseConnection db_partition = new DatabaseConnection("root", "localhost", "analysis", "isaiah");
                db_partition.updateDatabase("delete from customer_truck_load\n" +
                                        "where truck = '" + truck_selected.getTruck_id() + "'");
                
                DatabaseConnection db_part = new DatabaseConnection("root", "localhost", "analysis", "isaiah");
                db_part.updateDatabase("delete from order_partition_load");

                DatabaseConnection db_customer = new DatabaseConnection("root", "localhost", "analysis", "isaiah");
                db_customer.updateDatabase("delete from op_customer");
                
                op_data_partition.clear();
                setTableDataOPTruck();
                setTableFunctionOPTruck();
                initializePartitionData();
                initializeOPCustomerData();
                
                controller.getMessage().shortToast("Analysis Cancelled.", 1200);
            });
        });
    }
    
    public void setButtonFunctionOPRestart(){
        btn_op_restart.setOnMouseClicked((MouseEvent) -> {
            OPReset();
            
            op_data_customer.clear();
            op_data_partition.clear();
            op_data_truck.clear();
            
            setTableDataOPTruck();
            controller.getMessage().shortToast("Analysis cleared.", 1200);
        });
    }
    
    public void OPReset(){
            DatabaseConnection db_truck = new DatabaseConnection("root", "localhost", "analysis", "isaiah");
            db_truck.updateDatabase("delete from op_customer");
            
            DatabaseConnection db_partition = new DatabaseConnection("root", "localhost", "analysis", "isaiah");
            db_partition.updateDatabase("delete from order_partition_load");
            
            DatabaseConnection db_customer = new DatabaseConnection("root", "localhost", "analysis", "isaiah");
            db_customer.updateDatabase("delete from customer_truck_load");
    }
    
    public void setButtonFunctionSeeDeployment(){
        btn_see_deployment.setOnMouseClicked((MouseEvent) -> {
            controller.getBookOrders().openDeployment();
            controller.getOrdersDeployment().setTruckData();
        });
    }
    
    public class CTDataTruck {

        private String truck_id;
        private String capacity;
        
        public CTDataTruck(String truck_id,
                String capacity) {
            this.truck_id = truck_id;
            this.capacity = capacity;
        }

        public String getTruck_id() {
            return truck_id;
        }

        public void setTruck_id(String truck_id) {
            this.truck_id = truck_id;
        }

        public String getCapacity() {
            return capacity;
        }

        public void setCapacity(String capacity) {
            this.capacity = capacity;
        }
        
    }
    
    public class CTDataPartition {

        private String truck;
        private String customer;
        private String qty;
        
        public CTDataPartition(
            String truck,
            String customer,
            String qty) {
            
            this.truck = truck;
            this.customer = customer;
            this.qty = qty;
            
        }

        public String getCustomer() {
            return customer;
        }

        public void setCustomer(String customer) {
            this.customer = customer;
        }

        public String getQty() {
            return qty;
        }

        public void setQty(String qty) {
            this.qty = qty;
        }

        public String getTruck() {
            return truck;
        }

        public void setTruck(String truck) {
            this.truck = truck;
        }
        
    }
    
    public class CTDataCustomer {

        private String customer;
        private String qty;
        
        public CTDataCustomer(String customer,
                String qty) {
            
            this.customer = customer;
            this.qty = qty;
            
        }

        public String getCustomer() {
            return customer;
        }

        public void setCustomer(String customer) {
            this.customer = customer;
        }

        public String getQty() {
            return qty;
        }

        public void setQty(String qty) {
            this.qty = qty;
        }
        
    }
    
    public class OPDataTruck {

        private String truck_id;
        
        public OPDataTruck(String truck_id) {
            this.truck_id = truck_id;
        }

        public String getTruck_id() {
            return truck_id;
        }

        public void setTruck_id(String truck_id) {
            this.truck_id = truck_id;
        }
        
    }
    
    public class OPDataPartition {
        
        private String order_id;
        private String truck;
        private String partition_id;
        private String capacity;
        private String customer;
        private String product;
        private String qty;
        
        public OPDataPartition(
                String order_id,
                String truck,
                String partition_id,
                String capacity,
                String customer,
                String product,
                String qty) {
            
            this.order_id = order_id;
            this.truck = truck;
            this.capacity = capacity;
            this.customer = customer;
            this.partition_id = partition_id;
            this.product = product;
            this.qty = qty;
            
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

        public String getTruck() {
            return truck;
        }

        public void setTruck(String truck) {
            this.truck = truck;
        }

        public String getOrder_id() {
            return order_id;
        }

        public void setOrder_id(String order_id) {
            this.order_id = order_id;
        }
        
    }
    
    public class OPCustomer {

        private String order_id;
        private String customer;
        private String product;
        private String qty;
        
        public OPCustomer(
                String order_id,
                String customer,
                String product,
                String qty) {
            
            this.order_id = order_id;
            this.customer = customer;
            this.product = product;
            this.qty = qty;
            
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
