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
import java.time.LocalDate;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Duration;

public class OrderStack {

    //controller
    private LayoutController controller;
    private ErrorTraps traps = new ErrorTraps();
    
    //table
    private TableView tbl_customer;
    private TableView tbl_order;
    private TableView tbl_vault_customer;
    private TableView tbl_vault_order;
    
    //columns
    private TableColumn col_customer;
    private TableColumn col_product;
    private TableColumn col_qty;
    private TableColumn col_schedule;
    private TableColumn tbl_vault_col_customer;
    private TableColumn tbl_vault_order_col_product;
    private TableColumn tbl_vault_order_col_qty;
    private TableColumn tbl_vault_order_col_schedule;
    
    //fields
    private ComboBox cbo_customer;
    private ComboBox cbo_product;
    private TextField txt_quantity;
    
    //button
    private Label btn_save;
    private Label btn_edit;
    private Label btn_clear;
    private Label btn_remove;
    private Label btn_put_back_to_vault;
    private Label btn_order_vault;
    private Label btn_order_stack;
    private Label btn_include_to_stack;
    
    //extra variables
    boolean on_edit = false;    
    
    //others
    ScrollPane scrl_pne;
    Label lbl_title;
    DatePicker dtp_schedule;
    
    //data
    ObservableList<DataVaultCustomer> data_vault_customer = FXCollections.observableArrayList();
    ObservableList<DataVaultOrder> data_vault_order = FXCollections.observableArrayList();
    ObservableList<DataStackOrder> data_stack_order = FXCollections.observableArrayList();
    ObservableList<DataStackCustomer> data_stack_customer = FXCollections.observableArrayList();
    
    public OrderStack(LayoutController controller) {
        //load controller
        this.controller = controller;
        
        //load tables
        tbl_customer = controller.getOrder_stack_tbl_customer();
        tbl_order = controller.getOrder_stack_tbl_order();
        tbl_vault_customer = controller.getOrder_stack_vault_tbl_customer();
        tbl_vault_order = controller.getOrder_stack_vault_tbl_order();
        
        //load columns
        col_customer = controller.getOrder_stack_tbl_customer_col_customer();
        col_product = controller.getOrder_stack_tbl_order_col_product();
        col_qty = controller.getOrder_stack_tbl_order_col_qty();
        col_schedule = controller.getOrder_stack_tbl_order_col_schedule();
        
        //load columns (order vault)
        tbl_vault_col_customer = controller.getOrder_stack_vault_tbl_col_customer();
        tbl_vault_order_col_product = controller.getOrder_stack_vault_tbl_order_col_product();
        tbl_vault_order_col_qty = controller.getOrder_stack_vault_tbl_order_col_qty();
        tbl_vault_order_col_schedule = controller.getOrder_stack_vault_tbl_order_col_schedule();
        
        //load fields
        cbo_customer = controller.getOrder_stack_cbo_customer();
        cbo_product = controller.getOrder_satck_cbo_product();
        txt_quantity = controller.getOrder_stack_txt_quantity();
        
        //load buttons
        btn_clear = controller.getOrder_stack_btn_clear();
        btn_edit = controller.getOrder_stack_btn_edit();
        btn_remove = controller.getOrder_stack_btn_remove();
        btn_save = controller.getOrder_stack_btn_save();
        btn_order_stack = controller.getOrder_stack_btn_order_stack();
        btn_order_vault = controller.getOrder_stack_btn_see_vault();
        btn_put_back_to_vault = controller.getOrder_stack_btn_order_vault();
        btn_include_to_stack = controller.getOrder_stack_btn_include_to_stack();
        
        //others
        scrl_pne = controller.getOrder_stack_scrl();
        lbl_title = controller.getOrder_stack_lbl_title();
        dtp_schedule = controller.getOrder_stack_dtp_sched();
        
        //initialize module
        initializeModule();
    }
    
    public void initializeModule(){
        setTableColumns();
        setTableCustomerData();
        setTableCustomerFunction();
        setCboCustomerData();
        setCboProductData();
        setButtonSaveFunction();
        setButtonEditFunction();
        setButtonClearFunction();
        setButtonremoveFunction();
        setButtonFunctionOrderStack();
        setButtonFunctionOrderVault();
        setButtonIncludeToStackFunction();
        setTableDataOrderStackCustomer();
        setTableFunctionStackCustomer();
        setButtonFunctionPutToVault();
    }
    
    public void setTableColumns(){
        //table vault
        tbl_vault_col_customer.setCellValueFactory(new PropertyValueFactory("customer"));
        tbl_vault_order_col_product.setCellValueFactory(new PropertyValueFactory("product"));
        tbl_vault_order_col_qty.setCellValueFactory(new PropertyValueFactory("qty"));
        tbl_vault_order_col_schedule.setCellValueFactory(new PropertyValueFactory("schedule"));
        tbl_vault_customer.setItems(data_vault_customer);
        tbl_vault_order.setItems(data_vault_order);
        
        //table stack
        col_customer.setCellValueFactory(new PropertyValueFactory("customer"));
        col_product.setCellValueFactory(new PropertyValueFactory("product"));
        col_qty.setCellValueFactory(new PropertyValueFactory("qty"));
        col_schedule.setCellValueFactory(new PropertyValueFactory("schedule"));
        tbl_customer.setItems(data_stack_customer);
        tbl_order.setItems(data_stack_order);
    }
    
    public void setTableCustomerData(){
        data_vault_customer.clear();
        DatabaseConnection db_customer = new DatabaseConnection("root", "localhost", "eyefleet", "isaiah");
        ResultSet rs_customer = db_customer.setQuery("select (select customer.name " +
                                            "from customer " +
                                            "where customer.customer_id = order.customer_id) as 'customer' " +
                                            "from eyefleet.order where (order.status = 'received' or order.status = 're-schedule') and place = 'vault' " +
                                            "group by customer");
        try {
            while(rs_customer.next()){
                data_vault_customer.add(new DataVaultCustomer(rs_customer.getString("customer")));
            }
        } catch (Exception e) {
            System.out.println("ERROR ON ORDER STACK SETTABLECUSTOMERDATA: " + e);
        }
    }
    
    public void setTableCustomerFunction(){
        tbl_vault_customer.setOnMouseClicked((MouseEvent) -> {
            setTableOrderData();
        });
    }
    
    public void setTableOrderData(){
            DataVaultCustomer customer_selected = (DataVaultCustomer) tbl_vault_customer.getSelectionModel().getSelectedItem();
            if(customer_selected != null){
                data_vault_order.clear();
                
                DatabaseConnection db_order = new DatabaseConnection("root", "localhost", "eyefleet", "isaiah");
                ResultSet rs_order = db_order.setQuery("select * from eyefleet.order where order.customer_id = (select customer.customer_id\n" +
                                                       "from customer where customer.name = '" + customer_selected.getCustomer() + "') and "
                        +                              "(order.status = 'received' or order.status = 're-schedule') and order.place = 'vault'");
                
                try {
                    while(rs_order.next()){
                        data_vault_order.add(new DataVaultOrder(rs_order.getString("order_id") ,rs_order.getString("product_id"), rs_order.getString("quantity"),
                        rs_order.getDate("scheduled_date")));
                    }
                } catch (Exception e) {
                    System.out.println("ERROR ON ORDER STACK SETTABLECUSTOMERFUNCTION: " + e);
                }
            }
    }
    
    public void setCboCustomerData(){
        cbo_customer.getItems().clear();
        DatabaseConnection db_customer = new DatabaseConnection("root", "localhost", "eyefleet", "isaiah");
        ResultSet rs_customer = db_customer.setQuery("select customer.name from customer");
        
        try {
            while(rs_customer.next()){
                cbo_customer.getItems().add(rs_customer.getString("name"));
            }
        } catch (Exception e) {
            System.out.println("ERROR ON ORDER STACK setCboCustomerData: " + e);
        }
    }
    
    public void setCboProductData(){
        DatabaseConnection db_product = new DatabaseConnection("root", "localhost", "eyefleet", "isaiah");
        ResultSet rs_product = db_product.setQuery("select product_id from eyefleet.product");
        
        try {
            while(rs_product.next()){
                cbo_product.getItems().add(rs_product.getString("product_id"));
            }
        } catch (Exception e) {
            System.out.println("ERROR ON ORDER STACK setCboProductData: " + e);
        }
    }
    
    public void setButtonSaveFunction(){
        btn_save.setOnMouseClicked((MouseEvent) -> {
            
            if(cbo_customer.getValue() == null){
                controller.getMessage().shortToast("Please select a customer.", 2000);
            }else if(cbo_product.getValue() == null){
                controller.getMessage().shortToast("Please select a product.", 2000);
            }else if(txt_quantity.getText().isEmpty()){
                controller.getMessage().shortToast("Please input quantity of order.", 2000);
            }else if(!traps.isNumerical(txt_quantity.getText())){
                controller.getMessage().shortToast("Please review order quantity. It must be Numerical.", 2000);
            }else if(dtp_schedule.getValue() == null){
                controller.getMessage().shortToast("Please select the schedule of the order", 2000);
            }else{
                if(on_edit == false){
                checkAndAddCustomer(cbo_customer.getValue().toString());
                DatabaseConnection db_save = new DatabaseConnection("root", "localhost", "eyefleet", "isaiah");
                db_save.updateDatabase("insert into eyefleet.order (date_ordered, status, quantity, customer_id, product_id, scheduled_date, place) " +
                                "values (now(), 'received', " + txt_quantity.getText().toString() + ", (select customer.customer_id " +
                                "from customer " +
                                "where customer.name = '" + cbo_customer.getValue().toString() + "'), "
                                + "'" + cbo_product.getValue().toString() + "', '" + dtp_schedule.getValue().getYear() + "-" + 
                                                                            dtp_schedule.getValue().getMonthValue() + "-" + 
                                                                            dtp_schedule.getValue().getDayOfMonth() + "', 'vault')");
                setTableOrderData();
                setTableCustomerData();
                controller.getMessage().shortToast("Order saved.", 1200);
                }
                else {
                    DataVaultOrder order_selected = (DataVaultOrder) tbl_vault_order.getSelectionModel().getSelectedItem();
                    DatabaseConnection db_edit = new DatabaseConnection("root", "localhost", "eyefleet", "isaiah");
                    db_edit.updateDatabase("update eyefleet.order "
                            + "set customer_id = (select customer_id from customer where customer.name = '" + cbo_customer.getValue().toString() + "'), "
                            + "product_id = '" + cbo_product.getValue().toString() + "', "
                            + "quantity = " + txt_quantity.getText() + ", "
                            + "scheduled_date = '" + dtp_schedule.getValue().getYear() + "-" + dtp_schedule.getValue().getMonthValue() + "-" + dtp_schedule.getValue().getDayOfMonth() + "' "
                            + "where order_id = " + order_selected.getId());
                    btn_clear.setDisable(false);
                    btn_edit.setDisable(false);
                    btn_remove.setDisable(false);
                    on_edit = false;

                    setTableOrderData();
                    setTableCustomerData();
                    controller.getMessage().shortToast("Order edited.", 1200);
                }
            }
            
        });
    }
    
    public void setButtonEditFunction(){
        btn_edit.setOnMouseClicked((MouseEvent) -> {
            DataVaultCustomer customer_selected = (DataVaultCustomer) tbl_vault_customer.getSelectionModel().getSelectedItem();
            DataVaultOrder order_selected = (DataVaultOrder) tbl_vault_order.getSelectionModel().getSelectedItem();
            if(order_selected != null){
                cbo_customer.setValue(customer_selected.getCustomer());
                cbo_product.setValue(order_selected.getProduct());
                txt_quantity.setText(order_selected.getQty());
                dtp_schedule.setValue(LocalDate.of(order_selected.getSchedule().getYear()+1900, order_selected.getSchedule().getMonth()+1, order_selected.getSchedule().getDate()));
                
                txt_quantity.setEditable(true);
                btn_clear.setDisable(true);
                btn_edit.setDisable(true);
                btn_remove.setDisable(true);
                on_edit = true;
                controller.getMessage().shortToast("Click Save to submit changes.", 3000);
            }
            else{
                controller.getMessage().shortToast("Please select an order to edit.", 2000);
            }
        });
    }
    
    public void setButtonClearFunction(){
        btn_clear.setOnMouseClicked((MouseEvent) -> {
            cbo_customer.setEditable(true);
            cbo_customer.setValue("");
            cbo_product.setValue("");
            txt_quantity.setText("");
            txt_quantity.setEditable(true);
            dtp_schedule.setValue(null);
        });
    }
    
    public void setButtonremoveFunction(){
        btn_remove.setOnMouseClicked((MouseEvent) -> {
            DataVaultOrder order_selected = (DataVaultOrder) tbl_vault_order.getSelectionModel().getSelectedItem();
            if(order_selected != null){
                DatabaseConnection db_remove = new DatabaseConnection("root", "localhost", "eyefleet", "isaiah");
                db_remove.updateDatabase("delete from eyefleet.order where order_id = " + order_selected.getId());
                setTableOrderData();
                setTableCustomerData();
                controller.getMessage().shortToast("Order removed.", 1200);
            }
            else{
                controller.getMessage().shortToast("Please select a record to remove.", 2000);
            }
        });
    }
    
    public void checkAndAddCustomer(String name){
        boolean exists = false;
        DatabaseConnection db_customer = new DatabaseConnection("root", "localhost", "eyefleet", "isaiah");
        ResultSet rs_customer = db_customer.setQuery("select * from customer");
        
        try {
            while(rs_customer.next()){
                if(rs_customer.getString("name").equals(name)){
                    exists = true;
                    break;
                }
            }
            
            if(exists == false){
                DatabaseConnection db_customer_add = new DatabaseConnection("root", "localhost", "eyefleet", "isaiah");
                db_customer_add.updateDatabase("insert into customer (name) values('" + name + "')");
                setCboCustomerData();
            }
        } catch (Exception e) {
            System.out.println("ERROR ON ORDER STACK checkAndAddCustomer: " + e);
        }
    }
    
    public void setButtonFunctionOrderVault(){
        btn_order_vault.setOnMouseClicked((MouseEvent) -> {
            Timeline timeline = new Timeline(new KeyFrame(new Duration(700), new KeyValue(scrl_pne.hvalueProperty(), 1)));
            timeline.play();
            lbl_title.setText("    Order Vault");
        });
    }
    
    public void setButtonFunctionOrderStack(){
        btn_order_stack.setOnMouseClicked((MouseEvent) -> {
            Timeline timeline = new Timeline(new KeyFrame(new Duration(700), new KeyValue(scrl_pne.hvalueProperty(), 0)));
            timeline.play();
            lbl_title.setText("    Order Stack");
        });
    }
    
    public void setTableDataOrderStackCustomer(){
        data_stack_customer.clear();
        DatabaseConnection db_customer = new DatabaseConnection("root", "localhost", "eyefleet", "isaiah");
        ResultSet rs_customer = db_customer.setQuery("select distinct (select customer.name " +
                                                "from customer " +
                                                "where customer_id = order.customer_id) as customer " +
                                                "from eyefleet.order " +
                                                "where place = 'stack' and (status = 're-schedule' or status = 'received')");
        try {
            while(rs_customer.next()){
                data_stack_customer.add(new DataStackCustomer(rs_customer.getString("customer")));
            }
        } catch (Exception e) {
            System.out.println("ERROR ON ORDER STACK setTableDataOrderStackCustomer: " + e);
        }
    }
    
    public void setTableDataOrderStackOrder(){
        data_stack_order.clear();
        DataStackCustomer customer_selected = (DataStackCustomer) tbl_customer.getSelectionModel().getSelectedItem();
        
        if(customer_selected != null){
            DatabaseConnection db_order = new DatabaseConnection("root", "localhost", "eyefleet", "isaiah");
            ResultSet rs_order = db_order.setQuery("select order_id, product_id, quantity, scheduled_date " +
                                            "from eyefleet.order " +
                                            "where place = 'stack' and customer_id = (select customer_id " +
                                            "from customer " +
                                            "where customer.name = '" + customer_selected.getCustomer() + "') and (status = 're-schedule' or status = 'received')");
            try {
                while(rs_order.next()){
                    data_stack_order.add(new DataStackOrder(rs_order.getString("ordeR_id"),
                            rs_order.getString("product_id"),
                            rs_order.getString("quantity"), 
                            rs_order.getString("scheduled_date")));
                }
            } catch (Exception e) {
                System.out.println("ERROR ON ORDER STACK setTableDataOrderStackOrder: " + e);
            }
            
        }
    }
    
    public void setButtonIncludeToStackFunction(){
        
        btn_include_to_stack.setOnMouseClicked((MouseEvent) -> {
            DataVaultOrder order_selected = (DataVaultOrder) tbl_vault_order.getSelectionModel().getSelectedItem();
            
            if(order_selected != null){
                DatabaseConnection db_order = new DatabaseConnection("root", "localhost", "eyefleet", "isaiah");
                db_order.updateDatabase("update eyefleet.order\n" +
                                    "set place = 'stack'\n" +
                                    "where order_id = " + order_selected.getId());
                
                setTableOrderData();
                setTableCustomerData();

                setTableDataOrderStackOrder();
                setTableDataOrderStackCustomer();

                controller.getScheduling().setTableDataCTCustomer();
                controller.getMessage().shortToast("Order included.", 1200);
            }
            else {
                controller.getMessage().shortToast("Please select an order to include.", 2000);
            }
        });
    }
    
    public void setTableFunctionStackCustomer(){
        tbl_customer.setOnMouseClicked((MouseEvent) -> {
            setTableDataOrderStackOrder();
        });
    }
    
    public void setButtonFunctionPutToVault(){
        btn_put_back_to_vault.setOnMouseClicked((MouseEvent) -> {
            DataStackOrder order_selected = (DataStackOrder) tbl_order.getSelectionModel().getSelectedItem();
            DatabaseConnection db_order = new DatabaseConnection("root", "localhost", "eyefleet", "isaiah");

            if(order_selected != null){
                db_order.updateDatabase("update eyefleet.order\n" +
                                        "set place = 'vault'\n" +
                                        "where order_id = " + order_selected.getId());

                setTableOrderData();
                setTableCustomerData();

                setTableDataOrderStackOrder();
                setTableDataOrderStackCustomer();
                controller.getMessage().shortToast("Order included.", 1200);
            }
            else {
                controller.getMessage().shortToast("Please select a record to include", 2000);
            }
        });
    }
    
    public class DataStackCustomer {

        private String customer;
        
        public DataStackCustomer(String customer) {
            this.customer = customer;
        }

        public String getCustomer() {
            return customer;
        }

        public void setCustomer(String customer) {
            this.customer = customer;
        }
        
    }
    
    public class DataStackOrder {
        
        private String id;
        private String product;
        private String qty;
        private String schedule;
        
        public DataStackOrder(String id,
                        String product,
                        String qty,
                        String schedule) {
            this.id = id;
            this.product = product;
            this.qty = qty;
            this.schedule = schedule;
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

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getSchedule() {
            return schedule;
        }

        public void setSchedule(String schedule) {
            this.schedule = schedule;
        }
        
    }
    
    public class DataVaultCustomer {

        private String customer;
        
        public DataVaultCustomer(String customer) {
            this.customer = customer;
        }

        public String getCustomer() {
            return customer;
        }

        public void setCustomer(String customer) {
            this.customer = customer;
        }
        
    }
    
    public class DataVaultOrder {
        
        private String id;
        private String product;
        private String qty;
        private Date schedule;
        
        public DataVaultOrder(String id,
                        String product,
                        String qty,
                        Date schedule) {
            this.id = id;
            this.product = product;
            this.qty = qty;
            this.schedule = schedule;
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

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public Date getSchedule() {
            return schedule;
        }

        public void setSchedule(Date schedule) {
            this.schedule = schedule;
        }

        
    }
    
}