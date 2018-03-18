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
import javafx.scene.control.cell.PropertyValueFactory;

/**
 *
 * @author IsaiahJan
 */
public class FailedOrders {

    //controller
    LayoutController controller;
    
    //table
    TableView tbl_orders;
    
    //columns
    TableColumn tbl_orders_col_date;
    TableColumn tbl_orders_col_customer;
    TableColumn tbl_orders_col_product;
    TableColumn tbl_orders_col_qty;
    TableColumn tbl_orders_col_remarks;
    
    //fields
    DatePicker dtp_from;
    DatePicker dtp_until;
    TextArea txt_reason;
    Label btn_filter;
    
    //data
    ObservableList<DataOrder> data_order = FXCollections.observableArrayList();
    
    public FailedOrders(LayoutController controller) {
        //controller
        this.controller = controller;
        
        //table
        tbl_orders = controller.getFailed_orders_tbl_failed();
        
        //columns
        tbl_orders_col_customer = controller.getFailed_orders_tbl_failed_col_customer();
        tbl_orders_col_date = controller.getFailed_orders_tbl_failed_col_date();
        tbl_orders_col_product = controller.getFailed_orders_tbl_failed_col_product();
        tbl_orders_col_qty = controller.getFailed_orders_tbl_failed_col_qty();
        tbl_orders_col_remarks = controller.getFailed_orders_tbl_failed_col_remarks();
        
        //fields
        dtp_from = controller.getFailed_orders_dtp_from();
        dtp_until = controller.getFailed_orders_dtp_until();
        btn_filter = controller.getFailed_orders_btn_filter();
        txt_reason = controller.getFailed_orders_txt_remarks();
        
        //initialize module
        initializeModule();
    }
    
    public void initializeModule(){
        setTableColumns();
        setButtonFunctionFilter();
        setTableFunctionOrders();
    }
    
    public void setTableColumns(){
        tbl_orders_col_customer.setCellValueFactory(new PropertyValueFactory("customer"));
        tbl_orders_col_date.setCellValueFactory(new PropertyValueFactory("date"));
        tbl_orders_col_product.setCellValueFactory(new PropertyValueFactory("product"));
        tbl_orders_col_qty.setCellValueFactory(new PropertyValueFactory("qty"));
        tbl_orders_col_remarks.setCellValueFactory(new PropertyValueFactory("remarks"));
        tbl_orders.setItems(data_order);
    }
    
   public void setButtonFunctionFilter(){
       btn_filter.setOnMouseClicked((MouseEvent) -> {
            if(dtp_from.getValue() != null && dtp_until.getValue() != null){
                DatabaseConnection db_order = new DatabaseConnection("root", "localhost", "eyefleet", "isaiah");
                ResultSet rs_order = db_order.setQuery("select failed_order.date, " +
                                        "reason, " +
                                        "failed_order.action as remarks, " +
                                        "(select customer.name " +
                                        "from customer " +
                                        "where customer_id = (select order.customer_id " +
                                        "from eyefleet.order " +
                                        "where order.order_id = (select order_assignment.order_id " +
                                        "from order_assignment " +
                                        "where order_assignment.order_assignment_id = failed_order.order_assignment_id))) as customer, " +
                                        "(select order.product_id " +
                                        "from eyefleet.order " +
                                        "where order.order_id = (select order_assignment.order_id " +
                                        "from order_assignment " +
                                        "where order_assignment.order_assignment_id = failed_order.order_assignment_id)) as product, " +
                                        "(select order_assignment.qty " +
                                        "from order_assignment " +
                                        "where order_assignment.order_assignment_id = failed_order.order_assignment_id) as qty " +
                                        "from failed_order " +
                                        "where failed_order.date >= '" + dtp_from.getValue().getYear() + "-" + dtp_from.getValue().getMonthValue() + "-" + dtp_from.getValue().getDayOfMonth() + "' and " +
                                        "failed_order.date <= '" + dtp_until.getValue().getYear() + "-" + dtp_until.getValue().getMonthValue() + "-" + dtp_until.getValue().getDayOfMonth() + "'");
                
                try {
                    while(rs_order.next()){
                        data_order.add(new  DataOrder(rs_order.getString("date"), 
                                rs_order.getString("customer"), 
                                rs_order.getString("product"), 
                                rs_order.getString("qty"), 
                                rs_order.getString("remarks"),
                                rs_order.getString("reason")));
                    }
                } catch (Exception e) {
                    System.out.println("ERROR FROM FAILED ORDERS @ setButtonFunctionFilter " + e);
                }
            }
            
        });
   }
    
   public void setTableFunctionOrders(){
       tbl_orders.setOnMouseClicked((MouseEvent) -> {
           DataOrder order_selected = (DataOrder) tbl_orders.getSelectionModel().getSelectedItem();
           if (order_selected != null){
               txt_reason.setText(order_selected.getReason());
           }
       });
   }
   
    public class DataOrder{

        private String date;
        private String customer;
        private String product;
        private String qty;
        private String remarks;
        private String reason;
        
        public DataOrder(
                String date,
                String customer,
                String product,
                String qty,
                String remarks,
                String reason) {
            
            this.customer = customer;
            this.date = customer;
            this.product = product;
            this.qty = qty;
            this.remarks = remarks;
            this.reason = reason;
            
        }

        public String getDate() {
            return date;
        }

        public void setDate(String date) {
            this.date = date;
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

        public String getRemarks() {
            return remarks;
        }

        public void setRemarks(String remarks) {
            this.remarks = remarks;
        }

        public String getReason() {
            return reason;
        }

        public void setReason(String reason) {
            this.reason = reason;
        }
        
    }
    
}
