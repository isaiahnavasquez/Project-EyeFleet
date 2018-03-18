/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package eyefleet;
//mc importss----------------------
import Orders.Scheduling;
import Maintenance.Equipments;
import Maintenance.Issuance;
import Maintenance.RepairServices;
import Maintenance.TruckPartInventory;
import Maintenance.TruckStatus;
import Maintenance.TruckpartReplacement;
import MasterControl.McTruckStatus;
import MasterControl.Mc_PPEquipment;
import MasterControl.Mc_PP_Issuance;
import MasterControl.Mc_Replacements;
import MasterControl.Mc_RepairServices;
import MasterControl.Mc_TruckParts;
import Orders.OrderSchedule;
import Orders.OrderStack;
import Orders.OrdersDeployment;
import Orders.OrdersOnDelivery;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.net.URL;
import java.time.LocalDateTime;
import java.util.ResourceBundle;
import javafx.animation.AnimationTimer;
import javafx.animation.FadeTransition;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.PasswordField;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.util.Duration;
//end of mc imports-----------------------

//isiaih imports;--------------------
import Orders.Scheduling;
import Maintenance.Equipments;
import Maintenance.Issuance;
import Maintenance.RepairServices;
import Maintenance.TruckPartInventory;
import Maintenance.TruckStatus;
import Maintenance.TruckpartReplacement;
import MasterControl.MC_CustomerAndProduct;
import MasterControl.MC_Driver;
import MasterControl.MC_Supplier;
import Orders.Delivery;
import Orders.FailedOrders;
import Orders.OrderFrequency;
import Orders.OrderSchedule;
import Orders.OrderStack;
import Orders.OrdersDeployment;
import Orders.OrdersOnDelivery;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.net.URL;
import java.time.LocalDateTime;
import java.util.ResourceBundle;
import javafx.animation.AnimationTimer;
import javafx.animation.FadeTransition;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.PasswordField;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.util.Duration;


//end of imports;


/**
 * FXML Controller class
 *
 * @author IsaiahJan
 */
public class LayoutController implements Initializable {
    
    
    
    // mc layoutcontroller--------------
    @FXML
    private AnchorPane pnl_main = new AnchorPane();
    @FXML
    private AnchorPane main_pnl_home = new AnchorPane();
    @FXML
    private Label main_menu_lbl_orders = new Label();
    @FXML
    private Label main_menu_lbl_maintenance = new Label();
    @FXML
    private Label main_menu_lbl_notifications = new Label();
    @FXML
    private Label main_menu_lbl_reports = new Label();
    @FXML
    private Label main_menu_lbl_exit = new Label();
    @FXML
    private AnchorPane pnl_orders = new AnchorPane();
    @FXML
    private Label orders_menu_lbl_order_stack = new Label();
    @FXML
    private Label orders_menu_lbl_on_delivery = new Label();
    @FXML
    private Label orders_menu_lbl_delivered = new Label();
    @FXML
    private Label orders_menu_lbl_manual = new Label();
    @FXML
    private AnchorPane orders_pnl_order_stack = new AnchorPane();
    @FXML
    private TextField order_stack_txt_quantity = new TextField();
    @FXML
    private Label order_stack_btn_save = new Label();
    @FXML
    private Label order_stack_btn_edit = new Label();
    @FXML
    private Label order_stack_btn_remove = new Label();
    @FXML
    private AnchorPane orders_pnl_on_delivery = new AnchorPane();
    @FXML
    private Label on_delivery_btn_set_as_delivered = new Label();
    @FXML
    private TextField on_delivery_txt_customer = new TextField();
    @FXML
    private TextField on_delivery_txt_product = new TextField();
    @FXML
    private TextField on_delivery_txt_quantity = new TextField();
    @FXML
    private AnchorPane orders_pnl_delivered = new AnchorPane();
    @FXML
    private AnchorPane orders_pnl_manual = new AnchorPane();
    @FXML
    private AnchorPane pnl_maintenance = new AnchorPane();
    @FXML
    private Label maintenance_lbl_status = new Label();
    @FXML
    private Label maintenance_lbl_replacements = new Label();
    @FXML
    private Label maintenance_lbl_repair_services = new Label();
    @FXML
    private Label maintenance_lbl_truck_parts = new Label();
    @FXML
    private Label maintenance_lbl_equipments = new Label();
    @FXML
    private Label maintenance_lbl_issuance = new Label();
    @FXML
    private AnchorPane maintenance_pnl_status = new AnchorPane();
    @FXML
    private TableView truck_status_tbl_trucks = new TableView();
    @FXML
    private TableColumn truck_status_tbl_trucks_col_truck_id  = new TableColumn();
    @FXML
    private TableColumn truck_status_tbl_trucks_col_capacity = new TableColumn();
    @FXML
    private TableColumn truck_status_tbl_trucks_col_status = new TableColumn();
    @FXML
    private TableView truck_status_tbl_partition = new TableView();
    
    @FXML
    private TableColumn truck_status_tbl_partition_col_partition_id = new TableColumn();
    
    @FXML
    private TableColumn truck_status_tbl_partition_col_capacity = new TableColumn();
    
    @FXML
    private TableColumn truck_status_tbl_partition_order_id = new TableColumn();
    
    
    @FXML
    private AnchorPane maintenance_pnl_replacements = new AnchorPane();
    
    @FXML
    private Label replacements_btn_set_as_repaired = new Label();
    
    @FXML
    private Label replacements_btn_save = new Label();
    
    @FXML
    private TableView replacements_tbl_replacements = new TableView();
    
    @FXML
    private TableColumn replacements_tbl_replacements_col_date = new TableColumn();
    
    @FXML
    private TableColumn replacements_tbl_replacements_col_truck_id = new TableColumn();
    
    @FXML
    private TableColumn replacements_tbl_replacements_col_truck_part = new TableColumn();
    
    @FXML
    private TableColumn replacements_tbl_replacements_col_status = new TableColumn();
    
    @FXML
    private AnchorPane maintenance_pnl_repair_services = new AnchorPane();
    
    @FXML
    private TableView services_tbl_services = new TableView();
    
    @FXML
    private TableColumn services_tbl_services_col_date = new TableColumn();
    
    @FXML
    private TableColumn services_tbl_services_col_truck_id = new TableColumn();
    
    @FXML
    private Label services_btn_save = new Label();
    
    @FXML
    private Label services_btn_set_as_repaired = new Label();
    
    @FXML
    private TextArea services_txt_description = new TextArea();
    
    @FXML
    private AnchorPane maintenance_pnl_truck_parts = new AnchorPane();
    
    @FXML
    private Label truck_parts_btn_save = new Label();
    
    @FXML
    private TableView truck_parts_tbl_truck_parts = new TableView();
    
    @FXML
    private TableColumn truck_parts_tbl_truck_parts_col_item_id = new TableColumn();
    
    @FXML
    private TableColumn truck_parts_tbl_truck_parts_col_description = new TableColumn();
    
    @FXML
    private TableColumn truck_parts_tbl_truck_parts_col_qty_on_hand = new TableColumn();
    
    @FXML
    private TextField truck_parts_txt_description = new TextField();
    
    @FXML
    private TextField truck_parts_txt_stock_in = new TextField();
    
    @FXML
    private AnchorPane maintenance_pnl_equipments = new AnchorPane();
    
    @FXML
    private TableView equipments_tbl_equipments = new TableView();
    
    @FXML
    private TableColumn equipments_tbl_equipments_col_equipment_id = new TableColumn();
    
    @FXML
    private TableColumn equipments_tbl_equipments_col_description = new TableColumn();
    
    @FXML
    private TableColumn equipments_tbl_equipments_col_qty_on_hand = new TableColumn();
    
    @FXML
    private Label equipment_btn_save = new Label();
    
    @FXML
    private Label equipment_btn_issue = new Label();
    
    @FXML
    private TextField equipment_txt_description = new TextField();
    
    @FXML
    private TextField equipment_txt_stock_in = new TextField();
    
    @FXML
    private AnchorPane maintenance_pnl_issuance = new AnchorPane();
    
    @FXML
    private Label issuance_btn_issue = new Label();
    
    @FXML
    private TableView issuance_tbl_driver = new TableView();
    
    @FXML
    private TableColumn issuance_tbl_driver_col_driver = new TableColumn();
    
    @FXML
    private TableView issuance_tbl_issuance = new TableView();
    
    @FXML
    private TableColumn issuance_tbl_issuance_col_date = new TableColumn();
    
    @FXML
    private TableColumn issuance_tbl_issuance_col_description = new TableColumn();
    
    @FXML
    private TableColumn issuance_tbl_issuance_col_validity = new TableColumn();
    
    @FXML
    private TableColumn issuance_tbl_issuance_col_date_until = new TableColumn();
    
    @FXML
    private TextField issuance_txt_description = new TextField();
    
    private TextField issuance_txt_date_until = new TextField();
    
    @FXML
    private TextField issuance_txt_validity = new TextField();
    
    @FXML
    private Pane main_menu_pane = new AnchorPane();
    
    
    private Timeline timeline;
    private Toolkit toolkit = Toolkit.getDefaultToolkit();
    private Dimension dim = toolkit.getScreenSize();
    private Double height = dim.getHeight();
    private Double width = dim.getWidth();
    
    
    @FXML
    private AnchorPane orders_menu_pane = new AnchorPane();
    
    @FXML
    private AnchorPane maintenance_menu_pane = new AnchorPane();
    
    @FXML
    private Label orders_menu_lbl_back = new Label();
    
    @FXML
    private Label maintenance_lbl_back = new Label();
    
    @FXML
    private Label main_menu_lbl_lock = new Label();
    
    @FXML
    private AnchorPane pnl_lock = new AnchorPane();
    
    @FXML
    private Label lock_time = new Label();
    
    @FXML
    private Label lock_date = new Label();
    
    @FXML
    private Label lock_btn_unlock = new Label();
    
    @FXML
    private Label main_title;
    
    @FXML
    private Pane main_title_pane;
    private BookOrders bookOrders;
    private BookMaintenance bookMaintenance;
    private AnimationTimer timeAndDate;
    
    @FXML
    private MenuItem truck_status_menu_replacement = new MenuItem();
    
    @FXML
    private MenuItem truck_status_menu_service_only = new MenuItem();
    
    @FXML
    private ComboBox replacements_cbo_truck = new ComboBox();
    
    @FXML
    private ComboBox replacements_cbo_truck_part = new ComboBox();
    
    @FXML
    private TableColumn services_tbl_services_col_status = new TableColumn();
    
    @FXML
    private ComboBox services_cbo_truck = new ComboBox();
    
    @FXML
    private ComboBox truck_parts_cbo_item_id = new ComboBox();
    @FXML
    private ComboBox equipment_cbo_equipment_id = new ComboBox();
    @FXML
    private ComboBox issuance_cbo_equipment_id = new ComboBox();
    @FXML
    private MenuButton truck_status_btn_set_as_for_repair = new MenuButton();
    @FXML
    private DatePicker issuance_dp_date_until = new DatePicker();
    @FXML
    private Label orders_menu_lbl_deployment = new Label();
    @FXML
    private Label manual_btn_see_deployment = new Label();
    private Label deployment_btn_cancel_deployment = new Label();
    private Label deployment_btn_print = new Label();
    private Label deployment_btn_orders_deployed = new Label();
    @FXML
    private TableColumn replacements_tbl_replacements_col_description = new TableColumn();
    @FXML
    private TextField replacements_txt_description = new TextField();
    @FXML
    private AnchorPane pnl_cloak = new AnchorPane();
    @FXML
    private AnchorPane pnl_user_account = new AnchorPane();
    @FXML
    private TextField user_account_txt_username = new TextField();
    @FXML
    private Label user_account_btn_log_in = new Label();
    @FXML
    private Label user_account_btn_cancel = new Label();
    @FXML
    private PasswordField user_account_txt_password = new PasswordField();
    @FXML
    private Label user_account_txt_title = new Label();
    @FXML
    private AnchorPane orders_pnl_deployment = new AnchorPane();
    
    private Issuance issuance;
    @FXML
    private ProgressIndicator issuance_progress = new ProgressIndicator();
    @FXML
    private TableView delivered_orders_tbl_orders = new TableView();
    @FXML
    private TableColumn delivered_orders_tbl_orders_col_customer = new TableColumn();
    @FXML
    private TableColumn delivered_orders_tbl_orders_col_product = new TableColumn();
    @FXML
    private TableColumn delivered_orders_tbl_orders_col_quantity = new TableColumn();
    @FXML
    private TableView delivered_orders_tbl_trucks = new TableView();
    @FXML
    private TableColumn delivered_orders_tbl_trucks_col_truck_id = new TableColumn();
    @FXML
    private TableColumn delivered_orders_tbl_trucks_col_driver = new TableColumn();
    private TableColumn delivered_orders_tbl_trucks_km_driven = new TableColumn();
    private Label delivered_orders_btn_delivery_of_the_day = new Label();
    @FXML
    private TableView deployment_tbl_truck = new TableView();
    @FXML
    private ComboBox deployment_cbo_driver = new ComboBox();
    @FXML
    private TableColumn replacements_tbl_replacements_col_location = new TableColumn();
    @FXML
    private TextField replacements_txt_location = new TextField();
    @FXML
    private AnchorPane pnl_status_reports = new AnchorPane();
    @FXML
    private AnchorPane status_reports_trucks_pnl_transactions = new AnchorPane();
    @FXML
    private AnchorPane status_reports_trucks_pnl_consumption = new AnchorPane();
    @FXML
    private AnchorPane status_reports_trucks_pnl_replacements = new AnchorPane();
    @FXML
    private AnchorPane status_reports_trucks_pnl_services = new AnchorPane();
    @FXML
    private AnchorPane status_reports_trucks_pnl_profile_details = new AnchorPane();
    @FXML
    private AnchorPane status_reports_drivers_pnl_deliveries = new AnchorPane();
    @FXML
    private AnchorPane status_reports_drivers_pnl_offences = new AnchorPane();
    @FXML
    private AnchorPane status_reports_drivers_pnl_equipments = new AnchorPane();
    @FXML
    private AnchorPane status_reports_drivers_pnl_profile = new AnchorPane();
    
    @FXML
    private Label status_reports_pnl_menu_lbl_truck_replacements = new Label();
    @FXML
    private Label status_reports_pnl_menu_lbl_truck_transactions = new Label();
    @FXML
    private Label status_reports_pnl_menu_lbl_truck_consumption = new Label();
    @FXML
    private Label status_reports_pnl_menu_lbl_driver_deliveries = new Label();
    @FXML
    private Label status_reports_pnl_menu_back = new Label();
    @FXML
    private Label status_reports_pnl_menu_lbl_driver_equipments = new Label();
    @FXML
    private Label status_reports_pnl_menu_lbl_truck_services = new Label();
    @FXML
    private Label status_reports_pnl_menu_lbl_truck_profile_details = new Label();
    @FXML
    private Label status_reports_pnl_menu_lbl_driver_offences = new Label();
    @FXML
    private Label status_reports_pnl_menu_lbl_driver_profile_details = new Label();
    @FXML
    private AnchorPane status_reports_pnl_menu = new AnchorPane();
    
    private OrderStack order_stack;
    @FXML
    private Label order_stack_btn_clear = new Label();
    @FXML
    private ComboBox order_satck_cbo_product = new ComboBox();
    @FXML
    private ComboBox order_stack_cbo_customer = new ComboBox();
    private Label deployment_btn_save_details;
    private OrderSchedule order_schedule;
    private Label manual_btn_refresh = new Label();
    private Label manual_btn_cancel_assignment = new Label();
    
    private OrdersDeployment ordersDeployment;
    private OrdersOnDelivery orders_on_delivery;
    
    @FXML
    private TextField issuance_txt_quantity = new TextField();
    @FXML
    private TextField issuance_txt_remarks = new TextField();
    
    private Equipments equipments;
    @FXML
    private AnchorPane pnl_toast = new AnchorPane();
    @FXML
    private Label toast_message = new Label();

    private Messages message;
    @FXML
    private AnchorPane pnl_message = new AnchorPane();
    @FXML
    private Label message_title = new Label();
    @FXML
    private Label message_content = new Label();
    @FXML
    private Label message_btn_okay = new Label();
    
    private TruckStatus module_truckStatus;
    private TruckpartReplacement module_truckpartreplacement;
    private RepairServices module_repaiServices;
    private TruckPartInventory module_truckpartInventory;
     
    @FXML
    private TableColumn replacements_tbl_replacements_col_quantity = new TableColumn();
    @FXML
    private TextField replacements_txt_quantity = new TextField();
    @FXML
    private TableView order_stack_tbl_customer = new TableView();
    @FXML
    private TableColumn order_stack_tbl_customer_col_customer = new TableColumn();
    @FXML
    private TableView order_stack_tbl_order = new TableView();
    @FXML
    private TableColumn order_stack_tbl_order_col_product = new TableColumn();
    @FXML
    private TableColumn order_stack_tbl_order_col_qty = new TableColumn();
    @FXML
    private TableView on_delivery_tbl_trucks = new TableView();
    @FXML
    private TableColumn on_delivery_tbl_trucks_col_truck_id = new TableColumn();
    @FXML
    private TableColumn on_delivery_tbl_trucks_col_driver = new TableColumn();
    @FXML
    private TableView on_delivery_tbl_orders = new TableView();
    @FXML
    private TableColumn on_delivery_tbl_orders_col_partition_id = new TableColumn();
    private TableColumn on_delivery_tbl_orders_col_order_id = new TableColumn();
    @FXML
    private TextField on_delivery_txt_hobodometer = new TextField();
    @FXML
    private TableView manual_op_truck_tbl_partition = new TableView();
    @FXML
    private TableColumn manual_op_truck_tbl_partition_col_partition_id = new TableColumn();
    @FXML
    private TableColumn manual_op_truck_tbl_partition_col_capacity = new TableColumn();
    @FXML
    private TableColumn manual_op_truck_tbl_partition_col_customer = new TableColumn();
    @FXML
    private TableColumn manual_op_truck_tbl_partition_col_product = new TableColumn();
    @FXML
    private TableColumn manual_op_truck_tbl_partition_col_qty = new TableColumn();
    @FXML
    private TableView manual_ct_truck_tbl_truck = new TableView();
    @FXML
    private TableColumn manual_ct_truck_tbl_truck_col_truck_id = new TableColumn();
    @FXML
    private TableColumn manual_ct_truck_tbl_truck_col_capacity = new TableColumn();
    private Label manual_op_btn_proceed = new Label();
    @FXML
    private TableView manual_ct_customer_tbl_customer = new TableView();
    @FXML
    private TableColumn manual_ct_customer_tbl_customer_col_customer = new TableColumn();
    @FXML
    private TableColumn manual_ct_customer_tbl_customer_col_qty = new TableColumn();
    @FXML
    private TableView manual_ct_truck_tbl_customer = new TableView();
    @FXML
    private TableColumn manual_ct_truck_tbl_customer_col_customer = new TableColumn();
    @FXML
    private TableColumn manual_ct_truck_tbl_customer_col_qty = new TableColumn();
    @FXML
    private Label manual_op_btn_restart = new Label();
    @FXML
    private Label manual_btn_cancel_analysis = new Label();
    @FXML
    private Label manual_op_btn_unload = new Label();
    @FXML
    private Label manual_ct_btn_proceed = new Label();
    @FXML
    private Label manual_ct_btn_load = new Label();
    @FXML
    private TableView manual_op_customer_tbl_customer = new TableView();
    @FXML
    private TableColumn manual_op_customer_tbl_customer_col_customer = new TableColumn();
    @FXML
    private TableColumn manual_op_customer_tbl_customer_col_product = new TableColumn();
    @FXML
    private TableColumn manual_op_customer_tbl_customer_col_qty = new TableColumn();
    @FXML
    private TableView manual_op_truck_tbl_truck = new TableView();
    @FXML
    private TableColumn manual_op_truck_tbl_truck_col_truck_id = new TableColumn();
    @FXML
    private Label manual_ct_btn_restart = new Label();
    @FXML
    private Label manual_op_btn_load = new Label();
    @FXML
    private Label manual_ct_btn_unload = new Label();
    private Scheduling scheduling;
    private BookMasterControl bookMasterControl;
    @FXML
    private Label order_stack_btn_edit1;
    @FXML
    private Label order_stack_btn_edit111;
    @FXML
    private Label order_stack_btn_edit112;
    @FXML
    private Label order_stack_btn_edit11;
    @FXML
    private Label order_stack_btn_edit1121;
    @FXML
    private Label order_stack_btn_edit1111;
    @FXML
    private TextField order_stack_txt_customer1;
    @FXML
    private TextField order_stack_txt_customer11;
    @FXML
    private TextField order_stack_txt_customer111;
    @FXML
    private TextField order_stack_txt_customer1111;
    @FXML
    private TextField order_stack_txt_customer11111;
    @FXML
    private TextField order_stack_txt_customer111111;
    @FXML
    private TextField order_stack_txt_customer112;
    @FXML
    private TextField order_stack_txt_customer1121;
    @FXML
    private TextField order_stack_txt_customer11211;
    @FXML
    private TextField order_stack_txt_customer112111;
    @FXML
    private Label status_reports_title;
    @FXML
    private ComboBox status_reports_cbo_id;
    @FXML
    private ScrollPane manual_scrl;
    @FXML
    private AnchorPane pnl_master_control  = new AnchorPane();
    @FXML
    private AnchorPane mc_menu_pane  = new AnchorPane();
    @FXML
    private Label mc_menu_lbl_truck = new Label();
    @FXML
    private Label mc_menu_lbl_replacements = new Label();
    @FXML
    private Label mc_menu_lbl_repairservices = new Label();
    @FXML
    private Label mc_menu_lbl_truckparts = new Label();
    private Label mc_menu_lbl_iquipment = new Label();
    @FXML
    private Label mc_menu_lbl_back = new Label();
    @FXML
    private Label mc_menu_lbl_property_Equipment_Issunce = new Label();
    @FXML
    private Label mc_menu_lbl_property_Equipment = new Label();
    @FXML
    private AnchorPane mc_pnl_truck_status = new AnchorPane();
    @FXML
    private TableView  mc_tableview_status = new TableView();
    @FXML
    private TableColumn  mc_status_col_truck_id = new TableColumn();
    @FXML
    private TableColumn  mc_status_col_total_capacity = new TableColumn();
    @FXML
    private TableView  mc_partition_tableview = new TableView();
    @FXML
    private TableColumn  mc_partition_col_partition_id = new TableColumn();
    @FXML
    private TableColumn  mc_partition_col_partition_capacity = new TableColumn();
    @FXML
    private TableColumn  mc_partition_col_partition_position = new TableColumn();
    @FXML
    private TextField mc_status_text_truck_id = new TextField();
    @FXML
    private TextField mc_status_text_total_capacity = new TextField();
    @FXML
    private Label mc_status_btn_save = new Label();
    @FXML
    private Label mc_status_btn_edit = new Label();
    @FXML
    private Label mc_parition_btn_save = new Label();
    @FXML
    private Label mc_parition_btn_edit = new Label();
    @FXML
    private TextField mc_partition_text_partition_id = new TextField();
    @FXML
    private AnchorPane mc_pnl_replacements = new AnchorPane();
    @FXML
    private Label mc_replacement_btn_delete  = new Label();
    @FXML
    private Label mc_replacement_btn_edit = new Label();
    @FXML
    private Label mc_replacement_btn_save = new Label();
    @FXML
    private DatePicker mc_replacement_text_Date_datepcker = new DatePicker();
    @FXML
    private TextField mc_replacements_txt_location = new TextField();
    @FXML
    private TextField mc_replacements_txt_description = new TextField();
    @FXML
    private ComboBox mc_replacements_cbo_truck_part = new ComboBox();
    @FXML
    private ComboBox  mc_replacements_cbo_truck = new ComboBox();
    @FXML
    private TableView  mc_replacement_tableview = new TableView();
    @FXML
    private TableColumn  mc_replacement_tblc_date = new TableColumn();
    @FXML
    private TableColumn  mc_replacement_tblc_truck_id = new TableColumn();
    @FXML
    private TableColumn  mc_replacement_tblc_truck_part = new TableColumn();
    @FXML
    private TableColumn  mc_replacement_tblc_desription = new TableColumn();
    @FXML
    private TableColumn  mc_replacement_tblc_location = new TableColumn();
    @FXML
    private Label mc_replacements_btn_No_filter_by_range = new Label();
    @FXML
    private Label mc_replacements_btn_filter_by_range = new Label();
    @FXML
    private DatePicker mc_replacements_to_datepicker = new DatePicker();
    @FXML
    private DatePicker mc_replacements_from_datepicker = new DatePicker();
    @FXML
    private Label mc_replacements_btn_filter_by_month = new Label();
    private ComboBox  mc_replacements_cbo_truck1 = new ComboBox();
    @FXML
    private AnchorPane mc_pnl_repair_services = new AnchorPane();
    @FXML
    private Label mc_repairservices_btn_delete = new Label(); 
    @FXML
    private Label mc_repairservices_btn_edit = new Label();
    @FXML
    private Label mc_repairservices_btn_save = new Label();
    @FXML
    private DatePicker mc_repairservices_to_datepicker = new DatePicker();
    @FXML
    private DatePicker mc_repairservices_from_datepicker  = new DatePicker();
    @FXML
    private Label mc_repairservices_btn_No_filter_by_range = new Label();
    @FXML
    private Label mc_repairservices_btn_filter_by_range = new Label();
    @FXML
    private Label mc_repairservices_btn_filterby_month_filter = new Label();
    @FXML
    private TextArea mc_repairservices_desciption = new TextArea();
    @FXML
    private ComboBox mc_repairservices_cbo_truck = new ComboBox();
    @FXML
    private ComboBox  mc_repairservices_filterbymonth_cbo_truck = new ComboBox();
    @FXML
    private DatePicker mc_repairservices_date_datepicker = new DatePicker();
    @FXML
    private TableView  mc_repairservices_tableview = new TableView();
    @FXML
    private TableColumn  mc_repairservices_tblc_date = new TableColumn();
    @FXML
    private TableColumn  mc_repairserices_tblc_truck = new TableColumn();
    @FXML
    private AnchorPane mc_pnl_truck_parts = new AnchorPane();
    @FXML
    private TextField mc_truck_parts_text_quantity = new TextField();
    @FXML
    private TextField mc_truck_parts_text_description = new TextField();
    @FXML
    private TableView  mc_truck_parts_tableview = new TableView();
    @FXML
    private TableColumn  mc_truck_parts_tblc_item_id = new TableColumn();
    @FXML
    private TableColumn  mc_truck_parts_tblc_description = new TableColumn();
    @FXML
    private TableColumn  mc_truck_parts_tblc_qty_on_hand = new TableColumn();
    @FXML
    private ComboBox  mc_truck_parts_cbo_truck = new ComboBox();
    @FXML
    private Label mc_truck_parts_btn_delete = new Label();
    @FXML
    private Label mc_truck_parts_btn_edit = new Label();
    @FXML
    private Label mc_truck_parts_btn_save = new Label();
    @FXML
    private AnchorPane mc_pnl_issuance = new AnchorPane();
    @FXML
    private TextArea mc_protectice_equipment_issuance_textarea_remarks  = new TextArea();
    @FXML
    private TextField mc_protectice_equipment_issuance_text_description = new TextField();
    @FXML
    private ComboBox  mc_protectice_equipment_ssuance_cbo_equipment = new ComboBox();
    @FXML
    private TableView  mc_protectice_equipmen_issuance_tableview = new TableView();
    @FXML
    private TableColumn  mc_protectice_equipment_issuance_tblc_date = new TableColumn();
    @FXML
    private TableColumn  mc_protectice_equipment_issuance_tblc_equipment_id = new TableColumn();
    @FXML
    private TableView  mc_protectice_equipment_issuance_driver_tableview = new TableView();
    @FXML
    private TableColumn  mc_protectice_equipment_issuance_tblc_driver = new TableColumn();
    @FXML
    private ComboBox  mc_protectice_equipment_issuance_cbo_filterby_month = new ComboBox();
    @FXML
    private Label mc_protectice_equipment_issuance_btn_filterby_month_filter = new Label();
    @FXML
    private Label mc_protectice_equipment_issuance_btn_filter_by_range_filter = new Label();
    @FXML
    private Label mc_protectice_equipment_issuance_btn_No_filter_by_range = new Label();
    @FXML
    private DatePicker mc_protectice_equipment_issuance_from_datepicker = new DatePicker();
    @FXML
    private DatePicker mc_protectice_equipment_issuance_to_datepicker = new DatePicker();
    @FXML
    private Label mc_protective_equipment_issuance_btn_save = new Label();
    @FXML
    private Label mc_protective_equipment_issuance_btn_edit = new Label();
    @FXML
    private Label mc_protective_equipment_issuance_btn_delete = new Label();
    @FXML
    private AnchorPane mc_pnl_protective_equipment = new AnchorPane();
    @FXML
    private TableView  mc_protective_equipment_tableview = new TableView();
    @FXML
    private TableColumn  mc_protective_equipment_col_equipment_id = new TableColumn();
    @FXML
    private TableColumn  mc_protective_equipment_col_description = new TableColumn();
    @FXML
    private TableColumn  mc_protective_equipment_col_qty_on_hand = new TableColumn();
    @FXML
    private Label mc_protective_equipment_btn_delete = new Label();
    @FXML
    private Label mc_protective_equipment_btn_edit = new Label();
    @FXML
    private Label mc_protective_equipment_btn_save = new Label();
    @FXML
    private ComboBox  mc_protective_equipment_cbo_equipment = new ComboBox();
    @FXML
    private TextField mc_protective_equipment_txt_description = new TextField();
    @FXML
    private TextField mc_protective_equipment_txt_qty_on_hand = new TextField();
    @FXML
    private TextField mc_partition_text_capacity = new TextField();
    
    private McTruckStatus Mc_truckStatus;
    @FXML
    private Label mc_status_btn_update = new Label();
    @FXML
    private Label mc_parition_btn_update = new Label();
    @FXML
    private TextField mc_partition_text_position = new TextField();
    @FXML
    private ComboBox mc_replacements_cbo_filterbyMonth = new ComboBox();
    @FXML
    private Label main_menu_lbl_master_control = new Label();
    private TextField mc_partition_text_truck_id = new TextField();
   
    private  Mc_Replacements mcReplacements;
    private Mc_RepairServices mcRepairservices;
    private Mc_TruckParts mcTruckparts;
    private Mc_PP_Issuance mcPPIssuance;
    private Mc_PPEquipment mcPPEquipment;
    @FXML
    private ComboBox  truck_parts_cbo_supplier = new ComboBox();
    @FXML
    private TextField truck_parts_txt_amount = new TextField();
    @FXML
    private TextField truck_parts_txt_invoice_number = new TextField();
    @FXML
    private ComboBox equipment_cbo_supplier = new ComboBox();
    @FXML
    private TextField equipment_txt_amount = new TextField();
    @FXML
    private TextField equipment_txt_invooice_number = new TextField();
    @FXML
    private TableView  mc_truck_parts_tableview_supplier = new TableView();
    @FXML
    private TableColumn  mc_truck_parts_tblc_supplier = new TableColumn();
    @FXML
    private TableColumn  mc_truck_parts_tblc_supplier_amount = new TableColumn();
    @FXML
    private TableColumn  mc_truck_parts_tblc_supplier_invoicenumber = new TableColumn();
    @FXML
    private TextField mc_truck_parts_text_supplier_amount = new TextField();
    @FXML
    private ComboBox  mc_truck_parts_cbo_supplier = new ComboBox();
    @FXML
    private Label mc_truck_parts_btn_supplier_update = new Label();
    @FXML
    private TextField mc_truck_parts_text_supplier_voicenumber = new TextField();
    private Label mc_truck_parts_btn_supplier_add = new Label();
    @FXML
    private TextField mc_protective_equipment_txt_supplier_voicenumber = new TextField();
    @FXML
    private TextField mc_protective_equipment_txt_supplier_amount = new TextField();
    @FXML
    private ComboBox  mc_protective_equipment_cbo_supplier = new ComboBox();
    private Label mc_protective_equipment_btn_supplier_add = new Label();
    @FXML
    private Label mc_protective_equipment_btn_supplier_update = new Label();
    @FXML
    private TableView  mc_protective_equipment_tableview_supplier = new TableView();
    @FXML
    private TableColumn  mc_protective_equipment_col_supplier = new TableColumn();
    @FXML
    private TableColumn  mc_protective_equipment_col_supplier_amount = new TableColumn();
    @FXML
    private TableColumn  mc_protective_equipment_col_supplier_voicenumber = new TableColumn();
    //mc controller-----------------------------------------------------------------------------------
    
    //isiaih conreoller;-------------------------------
    
    @FXML
    private Label manual_op_btn_cancel = new Label();
    @FXML
    private TableColumn deployment_tbl_truck_col_truck = new TableColumn();
    @FXML
    private TableView deployment_tbl_partition = new TableView();
    @FXML
    private TableColumn deployment_tbl_partition_col_partition = new TableColumn();
    @FXML
    private TableColumn deployment_tbl_partition_col_customer = new TableColumn();
    @FXML
    private TableColumn deployment_tbl_partition_col_product = new TableColumn();
    @FXML
    private TableColumn deployment_tbl_partition_col_qty = new TableColumn();
    @FXML
    private TextField deployment_txt_hbmtr = new TextField();
    @FXML
    private TextField deployment_txt_fuel_cons = new TextField();
    @FXML
    private Label deployment_btn_print_details = new Label();
    @FXML
    private Label deployment_btn_truck_deployed = new Label();
    @FXML
    private MenuButton deployment_ctx_abort = new MenuButton();
    @FXML
    private MenuItem deployment_ctx_abort_current = new MenuItem();
    @FXML
    private MenuItem deployment_ctx_abort_all = new Menu();
    @FXML
    private TableColumn on_delivery_tbl_orders_col_status = new TableColumn();
    @FXML
    private CheckBox on_delivery_cbx_failed = new CheckBox();
    @FXML
    private TextArea on_delvivery_txt_reason = new TextArea();
    @FXML
    private MenuItem on_delivery_ctx_re_sched = new MenuItem();
    @FXML
    private MenuItem on_delivery_ctx_cancel = new MenuItem();
    @FXML
    private MenuButton on_delivery_mnu_action = new MenuButton();
    
    private Delivery delivery;
    @FXML
    private Label orders_menu_lbl_frequency = new Label();
    @FXML
    private Label orders_menu_lbl_failed_orders = new Label();
    @FXML
    private AnchorPane orders_pnl_frequency = new AnchorPane();
    @FXML
    private DatePicker frequency_dtp_from = new DatePicker();
    @FXML
    private DatePicker frequency_dtp_until = new DatePicker();
    @FXML
    private LineChart<String, Number> frequency_chart;
    @FXML
    private NumberAxis frequency_chart_number_axis = new NumberAxis();
    @FXML
    private CategoryAxis frequency_chart_category_axis = new CategoryAxis();
    @FXML
    private AnchorPane orders_pnl_failed_orders = new AnchorPane();
    @FXML
    private DatePicker failed_orders_dtp_until = new DatePicker();
    @FXML
    private DatePicker failed_orders_dtp_from = new DatePicker();
    @FXML
    private TableView failed_orders_tbl_failed = new TableView();
    @FXML
    private TableColumn failed_orders_tbl_failed_col_date = new TableColumn();
    @FXML
    private TableColumn failed_orders_tbl_failed_col_customer = new TableColumn();
    @FXML
    private TableColumn failed_orders_tbl_failed_col_product = new TableColumn();
    @FXML
    private TableColumn failed_orders_tbl_failed_col_qty = new TableColumn();
    @FXML
    private TableColumn failed_orders_tbl_failed_col_remarks = new TableColumn();
    @FXML
    private TextArea failed_orders_txt_remarks = new TextArea();
    @FXML
    private ScrollPane order_stack_scrl = new ScrollPane();
    @FXML
    private TableView order_stack_vault_tbl_customer = new TableView();
    @FXML
    private TableColumn order_stack_vault_tbl_col_customer = new TableColumn();
    @FXML
    private Label order_stack_btn_order_stack = new Label();
    @FXML
    private TableColumn order_stack_tbl_order_col_schedule = new TableColumn();
    @FXML
    private Label order_stack_btn_order_vault = new Label();
    @FXML
    private TableView order_stack_vault_tbl_order = new TableView();
    @FXML
    private TableColumn order_stack_vault_tbl_order_col_product = new TableColumn();
    @FXML
    private TableColumn order_stack_vault_tbl_order_col_qty = new TableColumn();
    @FXML
    private TableColumn order_stack_vault_tbl_order_col_schedule = new TableColumn();
    @FXML
    private Label order_stack_btn_see_vault = new Label();
    @FXML
    private Label order_stack_lbl_title = new Label();
    @FXML
    private DatePicker order_stack_dtp_sched = new DatePicker();
    @FXML
    private Label order_stack_btn_include_to_stack = new Label();
    @FXML
    private TableColumn on_delivery_tbl_orders_col_customer = new TableColumn();
    @FXML
    private TableColumn on_delivery_tbl_orders_col_qty = new TableColumn();
    @FXML
    private TableColumn on_delivery_tbl_orders_col_product = new TableColumn();
    @FXML
    private TextField delivered_orders_txt_km_driven = new TextField();
    @FXML
    private TextField delivered_orders_txt_fuel_consumpt = new TextField();
    @FXML
    private DatePicker delivered_orders_dtp_date = new DatePicker();
    @FXML
    private TableColumn delivered_orders_tbl_orders_col_status = new TableColumn();
    private TextField truck_parts_txt_quantity = new TextField();
    private TextField truck_parts_txt_invoice_num = new TextField();
    private TextField equipment_txt_qty = new TextField();
   
    private TextField equipment_txt_description1;
    private TextField equipment_txt_invoice_num = new TextField();
    
    private OrderFrequency order_frequency;
    @FXML
    private Label frequency_btn_filter = new Label();
    @FXML
    private Label failed_orders_btn_filter = new Label();
    
    private FailedOrders failed_orders;
    @FXML
    private Label delivered_order_btn_delivery_today = new Label();
    @FXML
    private TextArea delivered_order_txt_reason = new TextArea();
    @FXML
    private Pane frequency_pnl_chart = new Pane();
    @FXML
    private Label frequency_btn_print = new Label();
     private TableColumn  truck_parts_tbl_truck_parts_col_price = new TableColumn();
    private TableColumn   equipments_tbl_equipments_col_price = new TableColumn();
    private TextField mc_truck_parts_text_price = new TextField();
    @FXML
    private TableColumn  mc_truck_parts_tblc_supplier_date = new TableColumn();
    @FXML
    private TableColumn  mc_truck_parts_tblc_supplier_truckpart_id = new TableColumn();
    @FXML
    private TableColumn  mc_truck_parts_tblc_supplier_quantity = new TableColumn();
    @FXML
    private TextField mc_truck_parts_text_supplier_quantity = new TextField();
    private TableColumn  mc_truck_parts_tblc_price = new TableColumn();
    @FXML
    private DatePicker mc_truck_parts_supplier_date = new DatePicker();
    private TableColumn  mc_protective_equipment_col_price = new TableColumn();
    @FXML
    private DatePicker mc_protective_equipment_date_supplier = new DatePicker();
    private TextField mc_protective_equipment_txt_price = new TextField();
    @FXML
    private TableColumn  mc_protective_equipment_col_supplier_date = new TableColumn();
    @FXML
    private TableColumn  mc_protective_equipment_col_supplier_equipment_id = new TableColumn();
    @FXML
    private TableColumn  mc_protective_equipment_col_supplier_quantity = new TableColumn();
    @FXML
    private Label truck_parts_btn_reresh = new Label(); 
    @FXML
    private TextField mc_protective_equipment_txt_supplier_quantity = new TextField();
    @FXML
    private Label equipment_btn_refreshh = new Label();
    private AnchorPane pnl_customer = new AnchorPane();
    @FXML
    private ComboBox mc_truckpart_filterbymonth_cbo_truck = new ComboBox();
    @FXML
    private DatePicker mc_truckpart_from_datepicker = new DatePicker();
    @FXML
    private DatePicker mc_truckpart_to_datepicker = new DatePicker();
    @FXML
    private Label mc_truck_parts_btn_filter_by_month = new Label();
    @FXML
    private Label mc_truck_parts_btn_filter_by_range = new Label();
    @FXML
    private Label mc_truck_parts_btn_no_filter = new Label();
    @FXML
    private Label mc_equipment_btn_no_filter  = new Label();
    @FXML
    private Label mc_equipment_btn_filter_by_range = new Label();
    @FXML
    private Label mc_equipment_btn_filter_by_month = new Label();
    @FXML
    private DatePicker mc_equipment_from_datepicker = new DatePicker();
    @FXML
    private DatePicker mc_equipment_to_datepicker1 = new DatePicker();;
    @FXML
    private ComboBox  mc_equipment_filterbymonth_cbo_truck = new ComboBox();
    @FXML
    private Label product_btn_add = new Label();
    @FXML
    private Label customer_btn_update = new Label();
    @FXML
    private ComboBox  customer_cbo_cus_name = new ComboBox();
    @FXML
    private Label customer_btn_refresh = new Label();
    @FXML
    private TableView  customer_tableview = new TableView();
    @FXML
    private TableColumn    customer_tblc_name = new TableColumn();
    @FXML
    private ComboBox  product_cbo_product_id = new ComboBox();
    @FXML
    private Label customer_btn_add  = new Label();
    @FXML
    private TableView  product_tableview = new TableView();
    @FXML
    private TableColumn    product_tblc_product_id = new TableColumn();
    @FXML
    private TableColumn    product_tblc_description = new TableColumn();
    @FXML
    private Label product_btn_update = new Label();
    @FXML
    private TextField product_txt_description = new TextField();
    @FXML
    private AnchorPane pnl_customer_and_product = new AnchorPane();
    @FXML
    private Label mc_menu_lbl_customer_and_product = new Label();
    @FXML
    private Label mc_menu_lbl_supplier = new Label();
    @FXML
    private Label mc_menu_lbl_driver = new Label();
    @FXML
    private AnchorPane pnl_supplier = new AnchorPane();
    @FXML
    private TableView  supplier_tableview = new TableView();
    @FXML
    private TableColumn    supplier_id_tblc = new TableColumn();
    @FXML
    private TableColumn    supplier_name_tblc = new TableColumn();
    @FXML
    private ComboBox  Supplier_cbo_id = new ComboBox();
    @FXML
    private TextField supplier_name = new TextField();
    @FXML
    private Label supplier_btn_add = new Label();
    @FXML
    private Label supplier_btn_update = new Label();
    @FXML
    private Label supplier_btn_refresh = new Label();
    @FXML
    private AnchorPane pnl_driver  = new AnchorPane();
    @FXML
    private TableView  driver_tableview  = new TableView();
    @FXML
    private TableColumn    driver_tblc_driver_id = new TableColumn();
    @FXML
    private TableColumn    driver_tblc_fname = new TableColumn();
    @FXML
    private TableColumn    driver_tblc_lname = new TableColumn();
    @FXML
    private TableColumn    driver_tblc_mname = new TableColumn();
    @FXML
    private ComboBox  driver_cbo_driver_id= new ComboBox();
    @FXML
    private TextField driver_txt_fname = new TextField();
    @FXML
    private TextField driver_txt_lname = new TextField();
    @FXML
    private TextField driver_txt_mname = new TextField();
    @FXML
    private Label driver_btn_add = new Label();
    @FXML
    private Label driver_btn_update = new Label();
    @FXML
    private Label driver_btn_refresh = new Label();
    
    private MC_CustomerAndProduct mcCustomerAndroduct;
    private MC_Supplier mcSupplier;
    private MC_Driver mcDriver ;
    @FXML
    private Label replacements_btn_refresh = new Label();
    @FXML
    private Label services_btn_set_as_rfresh  = new Label();
    
    /////end of controller-------------------------
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        //system startup
        lockSystem();
        setFunctions();
        getPnl_main().setVisible(true);
        getMain_pnl_home().setVisible(true);
        getMain_title_pane().setVisible(true); //
        
        //set parameters for books
        bookOrders = new BookOrders(this);
        bookMaintenance = new BookMaintenance(this);
        bookMasterControl = new BookMasterControl(this);
        
        //set parameters for modules
        issuance = new Issuance(this);
        order_stack = new OrderStack(this);
        equipments = new Equipments(this);
        message = new Messages(this);
        module_repaiServices = new RepairServices(this);
        module_truckStatus = new TruckStatus(this);
        module_truckpartInventory = new TruckPartInventory(this);
        module_truckpartreplacement = new TruckpartReplacement(this);
        scheduling = new Scheduling(this);
        mcPPEquipment = new Mc_PPEquipment(this);
        
        
        
        //Master Control;
        Mc_truckStatus = new McTruckStatus(this);
        mcReplacements = new Mc_Replacements(this);
        mcRepairservices = new Mc_RepairServices(this);
        mcTruckparts = new Mc_TruckParts(this);
        mcPPIssuance = new Mc_PP_Issuance(this);
        mcCustomerAndroduct = new MC_CustomerAndProduct(this);
        mcSupplier= new MC_Supplier(this);
        mcDriver = new MC_Driver(this);
        
        
         //set parameters for modules
        orders_on_delivery = new OrdersOnDelivery(this);
        ordersDeployment = new OrdersDeployment(this);
        issuance = new Issuance(this);
        order_stack = new OrderStack(this);
        equipments = new Equipments(this);
        message = new Messages(this);
        module_repaiServices = new RepairServices(this);
        module_truckStatus = new TruckStatus(this);
        module_truckpartInventory = new TruckPartInventory(this);
        module_truckpartreplacement = new TruckpartReplacement(this);
        scheduling = new Scheduling(this);
        delivery = new Delivery(this);
        order_frequency = new OrderFrequency(this);
        failed_orders = new FailedOrders(this);
        
        
    }
    
    public void setFunctions(){
        
        //set the functions of button unlock
        //lets the user log in with his account
        getLock_btn_unlock().setOnMouseClicked((MouseEvent) -> {
            getPnl_main().setVisible(true);
            getMain_pnl_home().setVisible(true);
            getMain_title_pane().setVisible(true);
            getTimeAndDate().stop();
            FadeTransition fade = new FadeTransition(new Duration(380), getPnl_lock());
            fade.setFromValue(1);
            fade.setToValue(0);
            fade.play();
            fade.setOnFinished((ActionEvent) -> {
                getPnl_cloak().setVisible(true);
                getPnl_user_account().setVisible(true);
                getPnl_lock().setVisible(false);
            });
        });
        
        //opens the system verified with the user's account
        getUser_account_btn_log_in().setOnMouseClicked((ActionEvent) -> {
            if(getUser_account_txt_username().getText().toString().equals("") &&
                    getUser_account_txt_password().getText().toString().equals("")){    
                openBookMain();
                getPnl_cloak().setVisible(false);
                getPnl_user_account().setVisible(false);
                getUser_account_txt_title().setText("Hello sir. Your Account Please.");
                getUser_account_txt_username().clear();
                getUser_account_txt_password().clear();
            }
            else{
                getUser_account_txt_title().setText("Invalid Account. Please try again.");
                getUser_account_txt_username().clear();
                getUser_account_txt_password().clear();
            }
        });
        
        //locks the system
        getMain_menu_lbl_lock().setOnMouseClicked((MouseEvent) -> {
            lockSystem();
        });
        
        //shuts down the entire system
        getMain_menu_lbl_exit().setOnMouseClicked((MouseEvent) -> {
            System.exit(0);
        });
        
        //opens the book orders
        getMain_menu_lbl_orders().setOnMouseClicked((MouseEvent) ->{
            getBookOrders().openBookOrders();
            getBookOrders().openPage(getOrders_pnl_order_stack());
            getBookOrders().setSelectedMenu(getOrders_menu_lbl_order_stack());
        });
        
        //returns to the main panel from the book orders
        getOrders_menu_lbl_back().setOnMouseClicked((MouseEvent) -> {
            getBookOrders().closeBookOrders();
            openBookMain();
        });
        
        //opens the book maintenance
        getMain_menu_lbl_maintenance().setOnMouseClicked((MouseEvent) -> {
            closeBookMain();
            getBookMaintenance().openBookMaintenance();
            getBookMaintenance().openPage(getMaintenance_pnl_status());
            getBookMaintenance().setSelectedMenu(getMaintenance_lbl_status());
        });
       
        //returns to the main panel from the book maintenance
        getMaintenance_lbl_back().setOnMouseClicked((MouseEvent) -> {
            getBookMaintenance().closeBookMaintenance();
            openBookMain();
        });
        
        //returns to the main panel from the book master control
        getMc_menu_lbl_back().setOnMouseClicked((MouseEvent) -> {
            getBookMasterControl().closeBookMasterControl();
            openBookMain();
        });
        
        //opens the book master control
        getMain_menu_lbl_master_control().setOnMouseClicked((MouseEvent) -> {
            closeBookMain();
            getBookMasterControl().openBookMasterControl();
            getBookMasterControl().openPage(getMc_pnl_truck_parts());
            getBookMasterControl().setSelectedMenu(getMc_menu_lbl_truck());
            
            
        });
        
        
    }
    
    
    
    
    
        
    
    //function for opening the book main
    public void openBookMain(){
            getPnl_main().setVisible(true);
            getMain_title_pane().setTranslateX(80);
            getMain_title_pane().setVisible(true);
            Timeline timeline = new Timeline();
            timeline.getKeyFrames().add(new KeyFrame(new Duration(250), new KeyValue(getMain_title_pane().translateXProperty(), 0)));
            timeline.play();
    }
    
    //function for locking the system
    public void lockSystem(){
        setLockTimeAndDate();
        getPnl_lock().setOpacity(1);
        getPnl_lock().setVisible(true);
        getPnl_main().setVisible(false);
        getPnl_maintenance().setVisible(false);
        getPnl_orders().setVisible(false);
    }
    
    //function for closing the book main
    public void closeBookMain(){
        getMain_pnl_home().setVisible(false);
        getMain_title_pane().setVisible(false);
        getPnl_main().setVisible(false);
    }
    
    //function for running the time when the system locks
    public void setLockTimeAndDate(){
        timeAndDate = new AnimationTimer() {
            @Override
            public void handle(long now) {
                LocalDateTime local = LocalDateTime.now();
                getLock_time().setText(local.getHour() + ":" + local.getMinute());
                getLock_date().setText(local.getMonthValue() + "." + local.getDayOfMonth() + "." + local.getYear());
            }
        };
        getTimeAndDate().handle(100000);
        getTimeAndDate().start();
    }
    
    
    
    @FXML
    private void fireMaintenance(MouseEvent event) {
        
    }

    @FXML
    private void fireOrders(MouseEvent event) {
        
    }

    public AnchorPane getPnl_main() {
        return pnl_main;
    }

    public AnchorPane getMain_pnl_home() {
        return main_pnl_home;
    }

    public Label getMain_menu_lbl_orders() {
        return main_menu_lbl_orders;
    }

    public Label getMain_menu_lbl_maintenance() {
        return main_menu_lbl_maintenance;
    }

    public Label getMain_menu_lbl_notifications() {
        return main_menu_lbl_notifications;
    }

    public Label getMain_menu_lbl_reports() {
        return main_menu_lbl_reports;
    }

    public Label getMain_menu_lbl_exit() {
        return main_menu_lbl_exit;
    }

    public AnchorPane getPnl_orders() {
        return pnl_orders;
    }

    public Label getOrders_menu_lbl_order_stack() {
        return orders_menu_lbl_order_stack;
    }

    public Label getOrders_menu_lbl_on_delivery() {
        return orders_menu_lbl_on_delivery;
    }

    public Label getOrders_menu_lbl_delivered() {
        return orders_menu_lbl_delivered;
    }

    public Label getOrders_menu_lbl_manual() {
        return orders_menu_lbl_manual;
    }

    public AnchorPane getOrders_pnl_order_stack() {
        return orders_pnl_order_stack;
    }

    public TextField getOrder_stack_txt_quantity() {
        return order_stack_txt_quantity;
    }

    public Label getOrder_stack_btn_save() {
        return order_stack_btn_save;
    }

    public Label getOrder_stack_btn_edit() {
        return order_stack_btn_edit;
    }

    public Label getOrder_stack_btn_remove() {
        return order_stack_btn_remove;
    }

    public AnchorPane getOrders_pnl_on_delivery() {
        return orders_pnl_on_delivery;
    }

    public Label getOn_delivery_btn_set_as_delivered() {
        return on_delivery_btn_set_as_delivered;
    }

    public TextField getOn_delivery_txt_customer() {
        return on_delivery_txt_customer;
    }

    public TextField getOn_delivery_txt_product() {
        return on_delivery_txt_product;
    }

    public TextField getOn_delivery_txt_quantity() {
        return on_delivery_txt_quantity;
    }

    public AnchorPane getOrders_pnl_delivered() {
        return orders_pnl_delivered;
    }

    public AnchorPane getOrders_pnl_manual() {
        return orders_pnl_manual;
    }

    public AnchorPane getPnl_maintenance() {
        return pnl_maintenance;
    }

    public Label getMaintenance_lbl_status() {
        return maintenance_lbl_status;
    }

    public Label getMaintenance_lbl_replacements() {
        return maintenance_lbl_replacements;
    }

    public Label getMaintenance_lbl_repair_services() {
        return maintenance_lbl_repair_services;
    }

    public Label getMaintenance_lbl_truck_parts() {
        return maintenance_lbl_truck_parts;
    }

    public Label getMaintenance_lbl_equipments() {
        return maintenance_lbl_equipments;
    }

    public Label getMaintenance_lbl_issuance() {
        return maintenance_lbl_issuance;
    }

    public AnchorPane getMaintenance_pnl_status() {
        return maintenance_pnl_status;
    }

    public TableView getTruck_status_tbl_trucks() {
        return truck_status_tbl_trucks;
    }

    public TableColumn getTruck_status_tbl_trucks_col_truck_id() {
        return truck_status_tbl_trucks_col_truck_id;
    }

    public TableColumn getTruck_status_tbl_trucks_col_capacity() {
        return truck_status_tbl_trucks_col_capacity;
    }

    public TableColumn getTruck_status_tbl_trucks_col_status() {
        return truck_status_tbl_trucks_col_status;
    }

    public TableView getTruck_status_tbl_partition() {
        return truck_status_tbl_partition;
    }

    public TableColumn getTruck_status_tbl_partition_col_partition_id() {
        return truck_status_tbl_partition_col_partition_id;
    }

    public TableColumn getTruck_status_tbl_partition_col_capacity() {
        return truck_status_tbl_partition_col_capacity;
    }

    public TableColumn getTruck_status_tbl_partition_order_id() {
        return truck_status_tbl_partition_order_id;
    }

    public AnchorPane getMaintenance_pnl_replacements() {
        return maintenance_pnl_replacements;
    }

    public Label getReplacements_btn_set_as_repaired() {
        return replacements_btn_set_as_repaired;
    }

    public Label getReplacements_btn_save() {
        return replacements_btn_save;
    }

    public TableView getReplacements_tbl_replacements() {
        return replacements_tbl_replacements;
    }

    public TableColumn getReplacements_tbl_replacements_col_date() {
        return replacements_tbl_replacements_col_date;
    }

    public TableColumn getReplacements_tbl_replacements_col_truck_id() {
        return replacements_tbl_replacements_col_truck_id;
    }

    public TableColumn getReplacements_tbl_replacements_col_truck_part() {
        return replacements_tbl_replacements_col_truck_part;
    }

    public TableColumn getReplacements_tbl_replacements_col_status() {
        return replacements_tbl_replacements_col_status;
    }

    public AnchorPane getMaintenance_pnl_repair_services() {
        return maintenance_pnl_repair_services;
    }

    public TableView getServices_tbl_services() {
        return services_tbl_services;
    }

    public TableColumn getServices_tbl_services_col_date() {
        return services_tbl_services_col_date;
    }

    public TableColumn getServices_tbl_services_col_truck_id() {
        return services_tbl_services_col_truck_id;
    }

    public Label getServices_btn_save() {
        return services_btn_save;
    }

    public Label getServices_btn_set_as_repaired() {
        return services_btn_set_as_repaired;
    }

    public TextArea getServices_txt_description() {
        return services_txt_description;
    }

    public AnchorPane getMaintenance_pnl_truck_parts() {
        return maintenance_pnl_truck_parts;
    }

    public Label getTruck_parts_btn_save() {
        return truck_parts_btn_save;
    }

    public TableView getTruck_parts_tbl_truck_parts() {
        return truck_parts_tbl_truck_parts;
    }

    public TableColumn getTruck_parts_tbl_truck_parts_col_item_id() {
        return truck_parts_tbl_truck_parts_col_item_id;
    }

    public TableColumn getTruck_parts_tbl_truck_parts_col_description() {
        return truck_parts_tbl_truck_parts_col_description;
    }

    public TableColumn getTruck_parts_tbl_truck_parts_col_qty_on_hand() {
        return truck_parts_tbl_truck_parts_col_qty_on_hand;
    }

    public TextField getTruck_parts_txt_description() {
        return truck_parts_txt_description;
    }

    public TextField getTruck_parts_txt_stock_in() {
        return truck_parts_txt_stock_in;
    }

    public AnchorPane getMaintenance_pnl_equipments() {
        return maintenance_pnl_equipments;
    }

    public TableView getEquipments_tbl_equipments() {
        return equipments_tbl_equipments;
    }

    public TableColumn getEquipments_tbl_equipments_col_equipment_id() {
        return equipments_tbl_equipments_col_equipment_id;
    }

    public TableColumn getEquipments_tbl_equipments_col_description() {
        return equipments_tbl_equipments_col_description;
    }

    public TableColumn getEquipments_tbl_equipments_col_qty_on_hand() {
        return equipments_tbl_equipments_col_qty_on_hand;
    }

    public Label getEquipment_btn_save() {
        return equipment_btn_save;
    }

    public Label getEquipment_btn_issue() {
        return equipment_btn_issue;
    }

    public TextField getEquipment_txt_description() {
        return equipment_txt_description;
    }

    public TextField getEquipment_txt_stock_in() {
        return equipment_txt_stock_in;
    }

    public AnchorPane getMaintenance_pnl_issuance() {
        return maintenance_pnl_issuance;
    }

    public Label getIssuance_btn_issue() {
        return issuance_btn_issue;
    }

    public TableView getIssuance_tbl_driver() {
        return issuance_tbl_driver;
    }

    public TableColumn getIssuance_tbl_driver_col_driver() {
        return issuance_tbl_driver_col_driver;
    }

    public TableView getIssuance_tbl_issuance() {
        return issuance_tbl_issuance;
    }

    public TableColumn getIssuance_tbl_issuance_col_date() {
        return issuance_tbl_issuance_col_date;
    }

    public TableColumn getIssuance_tbl_issuance_col_description() {
        return issuance_tbl_issuance_col_description;
    }

    public TableColumn getIssuance_tbl_issuance_col_validity() {
        return issuance_tbl_issuance_col_validity;
    }

    public TableColumn getIssuance_tbl_issuance_col_date_until() {
        return issuance_tbl_issuance_col_date_until;
    }

    public TextField getIssuance_txt_description() {
        return issuance_txt_description;
    }

    public TextField getIssuance_txt_date_until() {
        return issuance_txt_date_until;
    }

    public TextField getIssuance_txt_validity() {
        return issuance_txt_validity;
    }

    public Pane getMain_menu_pane() {
        return main_menu_pane;
    }

    public Timeline getTimeline() {
        return timeline;
    }

    public Toolkit getToolkit() {
        return toolkit;
    }

    public Dimension getDim() {
        return dim;
    }

    public Double getHeight() {
        return height;
    }

    public Double getWidth() {
        return width;
    }

    public AnchorPane getOrders_menu_pane() {
        return orders_menu_pane;
    }

    public AnchorPane getMaintenance_menu_pane() {
        return maintenance_menu_pane;
    }

    public Label getOrders_menu_lbl_back() {
        return orders_menu_lbl_back;
    }

    public Label getMaintenance_lbl_back() {
        return maintenance_lbl_back;
    }

    public Label getMain_menu_lbl_lock() {
        return main_menu_lbl_lock;
    }

    public AnchorPane getPnl_lock() {
        return pnl_lock;
    }

    public Label getLock_time() {
        return lock_time;
    }

    public Label getLock_date() {
        return lock_date;
    }

    public Label getLock_btn_unlock() {
        return lock_btn_unlock;
    }

    public Label getMain_title() {
        return main_title;
    }

    public Pane getMain_title_pane() {
        return main_title_pane;
    }

    public BookOrders getBookOrders() {
        return bookOrders;
    }

    public BookMaintenance getBookMaintenance() {
        return bookMaintenance;
    }

    public AnimationTimer getTimeAndDate() {
        return timeAndDate;
    }

    public MenuItem getTruck_status_menu_replacement() {
        return truck_status_menu_replacement;
    }

    public MenuItem getTruck_status_menu_service_only() {
        return truck_status_menu_service_only;
    }

    public ComboBox getReplacements_cbo_truck() {
        return replacements_cbo_truck;
    }

    public ComboBox getReplacements_cbo_truck_part() {
        return replacements_cbo_truck_part;
    }

    public TableColumn getServices_tbl_services_col_status() {
        return services_tbl_services_col_status;
    }

    public ComboBox getServices_cbo_truck() {
        return services_cbo_truck;
    }

    public ComboBox getTruck_parts_cbo_item_id() {
        return truck_parts_cbo_item_id;
    }

    public ComboBox getEquipment_cbo_equipment_id() {
        return equipment_cbo_equipment_id;
    }

    public ComboBox getIssuance_cbo_equipment_id() {
        return issuance_cbo_equipment_id;
    }

    public MenuButton getTruck_status_btn_set_as_for_repair() {
        return truck_status_btn_set_as_for_repair;
    }

    public DatePicker getIssuance_dp_date_until() {
        return issuance_dp_date_until;
    }

    public Label getOrders_menu_lbl_deployment() {
        return orders_menu_lbl_deployment;
    }

    public Label getDeployment_btn_cancel_deployment() {
        return deployment_btn_cancel_deployment;
    }

    public Label getDeployment_btn_print() {
        return deployment_btn_print;
    }

    public Label getDeployment_btn_orders_deployed() {
        return deployment_btn_orders_deployed;
    }

    public TableColumn getReplacements_tbl_replacements_col_description() {
        return replacements_tbl_replacements_col_description;
    }

    public TextField getReplacements_txt_description() {
        return replacements_txt_description;
    }

    public AnchorPane getPnl_cloak() {
        return pnl_cloak;
    }

    public AnchorPane getPnl_user_account() {
        return pnl_user_account;
    }

    public TextField getUser_account_txt_username() {
        return user_account_txt_username;
    }

    public Label getUser_account_btn_log_in() {
        return user_account_btn_log_in;
    }

    public Label getUser_account_btn_cancel() {
        return user_account_btn_cancel;
    }

    public PasswordField getUser_account_txt_password() {
        return user_account_txt_password;
    }

    public Label getUser_account_txt_title() {
        return user_account_txt_title;
    }

    public AnchorPane getOrders_pnl_deployment() {
        return orders_pnl_deployment;
    }

    public Issuance getIssuance() {
        return issuance;
    }

    public ProgressIndicator getIssuance_progress() {
        return issuance_progress;
    }

    public TableView getDelivered_orders_tbl_orders() {
        return delivered_orders_tbl_orders;
    }

    public TableColumn getDelivered_orders_tbl_orders_col_customer() {
        return delivered_orders_tbl_orders_col_customer;
    }

    public TableColumn getDelivered_orders_tbl_orders_col_product() {
        return delivered_orders_tbl_orders_col_product;
    }

    public TableColumn getDelivered_orders_tbl_orders_col_quantity() {
        return delivered_orders_tbl_orders_col_quantity;
    }

    public TableView getDelivered_orders_tbl_trucks() {
        return delivered_orders_tbl_trucks;
    }

    public TableColumn getDelivered_orders_tbl_trucks_col_truck_id() {
        return delivered_orders_tbl_trucks_col_truck_id;
    }

    public TableColumn getDelivered_orders_tbl_trucks_col_driver() {
        return delivered_orders_tbl_trucks_col_driver;
    }

    public TableColumn getDelivered_orders_tbl_trucks_km_driven() {
        return delivered_orders_tbl_trucks_km_driven;
    }

    public Label getDelivered_orders_btn_delivery_of_the_day() {
        return delivered_orders_btn_delivery_of_the_day;
    }

    public TableView getDeployment_tbl_truck() {
        return deployment_tbl_truck;
    }

    public ComboBox getDeployment_cbo_driver() {
        return deployment_cbo_driver;
    }

    public TableColumn getReplacements_tbl_replacements_col_location() {
        return replacements_tbl_replacements_col_location;
    }

    public TextField getReplacements_txt_location() {
        return replacements_txt_location;
    }

    public AnchorPane getPnl_status_reports() {
        return pnl_status_reports;
    }

    public AnchorPane getStatus_reports_trucks_pnl_transactions() {
        return status_reports_trucks_pnl_transactions;
    }

    public AnchorPane getStatus_reports_trucks_pnl_consumption() {
        return status_reports_trucks_pnl_consumption;
    }

    public AnchorPane getStatus_reports_trucks_pnl_replacements() {
        return status_reports_trucks_pnl_replacements;
    }

    public AnchorPane getStatus_reports_trucks_pnl_services() {
        return status_reports_trucks_pnl_services;
    }

    public AnchorPane getStatus_reports_trucks_pnl_profile_details() {
        return status_reports_trucks_pnl_profile_details;
    }

    public AnchorPane getStatus_reports_drivers_pnl_deliveries() {
        return status_reports_drivers_pnl_deliveries;
    }

    public AnchorPane getStatus_reports_drivers_pnl_offences() {
        return status_reports_drivers_pnl_offences;
    }

    public AnchorPane getStatus_reports_drivers_pnl_equipments() {
        return status_reports_drivers_pnl_equipments;
    }

    public AnchorPane getStatus_reports_drivers_pnl_profile() {
        return status_reports_drivers_pnl_profile;
    }

    public Label getStatus_reports_pnl_menu_lbl_truck_replacements() {
        return status_reports_pnl_menu_lbl_truck_replacements;
    }

    public Label getStatus_reports_pnl_menu_lbl_truck_transactions() {
        return status_reports_pnl_menu_lbl_truck_transactions;
    }

    public Label getStatus_reports_pnl_menu_lbl_truck_consumption() {
        return status_reports_pnl_menu_lbl_truck_consumption;
    }

    public Label getStatus_reports_pnl_menu_lbl_driver_deliveries() {
        return status_reports_pnl_menu_lbl_driver_deliveries;
    }

    public Label getStatus_reports_pnl_menu_back() {
        return status_reports_pnl_menu_back;
    }

    public Label getStatus_reports_pnl_menu_lbl_driver_equipments() {
        return status_reports_pnl_menu_lbl_driver_equipments;
    }

    public Label getStatus_reports_pnl_menu_lbl_truck_services() {
        return status_reports_pnl_menu_lbl_truck_services;
    }

    public Label getStatus_reports_pnl_menu_lbl_truck_profile_details() {
        return status_reports_pnl_menu_lbl_truck_profile_details;
    }

    public Label getStatus_reports_pnl_menu_lbl_driver_offences() {
        return status_reports_pnl_menu_lbl_driver_offences;
    }

    public Label getStatus_reports_pnl_menu_lbl_driver_profile_details() {
        return status_reports_pnl_menu_lbl_driver_profile_details;
    }

    public AnchorPane getStatus_reports_pnl_menu() {
        return status_reports_pnl_menu;
    }

    public OrderStack getOrder_stack() {
        return order_stack;
    }

    public Label getOrder_stack_btn_clear() {
        return order_stack_btn_clear;
    }

    public ComboBox getOrder_satck_cbo_product() {
        return order_satck_cbo_product;
    }

    public ComboBox getOrder_stack_cbo_customer() {
        return order_stack_cbo_customer;
    }

    public OrderSchedule getOrder_schedule() {
        return order_schedule;
    }

    public Label getManual_btn_refresh() {
        return manual_btn_refresh;
    }

    public Label getManual_btn_cancel_assignment() {
        return manual_btn_cancel_assignment;
    }

    public OrdersDeployment getOrdersDeployment() {
        return ordersDeployment;
    }

    public OrdersOnDelivery getOrders_on_delivery() {
        return orders_on_delivery;
    }

    public TextField getIssuance_txt_quantity() {
        return issuance_txt_quantity;
    }

    public TextField getIssuance_txt_remarks() {
        return issuance_txt_remarks;
    }

    public Equipments getEquipments() {
        return equipments;
    }

    public AnchorPane getPnl_toast() {
        return pnl_toast;
    }

    public Label getToast_message() {
        return toast_message;
    }

    public Messages getMessage() {
        return message;
    }

    public AnchorPane getPnl_message() {
        return pnl_message;
    }

    public Label getMessage_title() {
        return message_title;
    }

    public Label getMessage_content() {
        return message_content;
    }

    public Label getMessage_btn_okay() {
        return message_btn_okay;
    }

    public TruckStatus getModule_truckStatus() {
        return module_truckStatus;
    }

    public TruckpartReplacement getModule_truckpartreplacement() {
        return module_truckpartreplacement;
    }

    public RepairServices getModule_repaiServices() {
        return module_repaiServices;
    }

    public TruckPartInventory getModule_truckpartInventory() {
        return module_truckpartInventory;
    }

    public TableColumn getReplacements_tbl_replacements_col_quantity() {
        return replacements_tbl_replacements_col_quantity;
    }

    public TextField getReplacements_txt_quantity() {
        return replacements_txt_quantity;
    }

    public TableView getOrder_stack_tbl_customer() {
        return order_stack_tbl_customer;
    }

    public TableColumn getOrder_stack_tbl_customer_col_customer() {
        return order_stack_tbl_customer_col_customer;
    }

    public TableView getOrder_stack_tbl_order() {
        return order_stack_tbl_order;
    }

    public TableColumn getOrder_stack_tbl_order_col_product() {
        return order_stack_tbl_order_col_product;
    }

    public TableColumn getOrder_stack_tbl_order_col_qty() {
        return order_stack_tbl_order_col_qty;
    }

    public Label getManual_btn_see_deployment() {
        return manual_btn_see_deployment;
    }

    public Label getDeployment_btn_save_details() {
        return deployment_btn_save_details;
    }

    public TableView getOn_delivery_tbl_trucks() {
        return on_delivery_tbl_trucks;
    }

    public TableColumn getOn_delivery_tbl_trucks_col_truck_id() {
        return on_delivery_tbl_trucks_col_truck_id;
    }

    public TableColumn getOn_delivery_tbl_trucks_col_driver() {
        return on_delivery_tbl_trucks_col_driver;
    }

    public TableView getOn_delivery_tbl_orders() {
        return on_delivery_tbl_orders;
    }

    public TableColumn getOn_delivery_tbl_orders_col_partition_id() {
        return on_delivery_tbl_orders_col_partition_id;
    }

    public TableColumn getOn_delivery_tbl_orders_col_order_id() {
        return on_delivery_tbl_orders_col_order_id;
    }

    public TextField getOn_delivery_txt_hobodometer() {
        return on_delivery_txt_hobodometer;
    }

    public TableView getManual_op_truck_tbl_partition() {
        return manual_op_truck_tbl_partition;
    }

    public TableColumn getManual_op_truck_tbl_partition_col_partition_id() {
        return manual_op_truck_tbl_partition_col_partition_id;
    }

    public TableColumn getManual_op_truck_tbl_partition_col_capacity() {
        return manual_op_truck_tbl_partition_col_capacity;
    }

    public TableColumn getManual_op_truck_tbl_partition_col_customer() {
        return manual_op_truck_tbl_partition_col_customer;
    }

    public TableColumn getManual_op_truck_tbl_partition_col_product() {
        return manual_op_truck_tbl_partition_col_product;
    }

    public TableColumn getManual_op_truck_tbl_partition_col_qty() {
        return manual_op_truck_tbl_partition_col_qty;
    }

    public TableView getManual_ct_truck_tbl_truck() {
        return manual_ct_truck_tbl_truck;
    }

    public TableColumn getManual_ct_truck_tbl_truck_col_truck_id() {
        return manual_ct_truck_tbl_truck_col_truck_id;
    }

    public TableColumn getManual_ct_truck_tbl_truck_col_capacity() {
        return manual_ct_truck_tbl_truck_col_capacity;
    }

    public Label getManual_op_btn_proceed() {
        return manual_op_btn_proceed;
    }

    public TableView getManual_ct_customer_tbl_customer() {
        return manual_ct_customer_tbl_customer;
    }

    public TableColumn getManual_ct_customer_tbl_customer_col_customer() {
        return manual_ct_customer_tbl_customer_col_customer;
    }

    public TableColumn getManual_ct_customer_tbl_customer_col_qty() {
        return manual_ct_customer_tbl_customer_col_qty;
    }

    public TableView getManual_ct_truck_tbl_customer() {
        return manual_ct_truck_tbl_customer;
    }

    public TableColumn getManual_ct_truck_tbl_customer_col_customer() {
        return manual_ct_truck_tbl_customer_col_customer;
    }

    public TableColumn getManual_ct_truck_tbl_customer_col_qty() {
        return manual_ct_truck_tbl_customer_col_qty;
    }

    public Label getManual_op_btn_restart() {
        return manual_op_btn_restart;
    }

    public Label getManual_btn_cancel_analysis() {
        return manual_btn_cancel_analysis;
    }

    public Label getManual_op_btn_unload() {
        return manual_op_btn_unload;
    }

    public Label getManual_ct_btn_proceed() {
        return manual_ct_btn_proceed;
    }

    public Label getManual_ct_btn_load() {
        return manual_ct_btn_load;
    }

    public TableView getManual_op_customer_tbl_customer() {
        return manual_op_customer_tbl_customer;
    }

    public TableColumn getManual_op_customer_tbl_customer_col_customer() {
        return manual_op_customer_tbl_customer_col_customer;
    }

    public TableColumn getManual_op_customer_tbl_customer_col_product() {
        return manual_op_customer_tbl_customer_col_product;
    }

    public TableColumn getManual_op_customer_tbl_customer_col_qty() {
        return manual_op_customer_tbl_customer_col_qty;
    }

    public TableView getManual_op_truck_tbl_truck() {
        return manual_op_truck_tbl_truck;
    }

    public TableColumn getManual_op_truck_tbl_truck_col_truck_id() {
        return manual_op_truck_tbl_truck_col_truck_id;
    }

    public Label getManual_ct_btn_restart() {
        return manual_ct_btn_restart;
    }

    public Label getManual_op_btn_load() {
        return manual_op_btn_load;
    }

    public Label getManual_ct_btn_unload() {
        return manual_ct_btn_unload;
    }

    public AnchorPane getPnl_master_control() {
        return pnl_master_control;
    }

    public AnchorPane getMc_menu_pane() {
        return mc_menu_pane;
    }

    public Label getMc_menu_lbl_truck() {
        return mc_menu_lbl_truck;
    }

    public Label getMc_menu_lbl_replacements() {
        return mc_menu_lbl_replacements;
    }

    public Label getMc_menu_lbl_repairservices() {
        return mc_menu_lbl_repairservices;
    }

    public Label getMc_menu_lbl_truckparts() {
        return mc_menu_lbl_truckparts;
    }

    public Label getMc_menu_lbl_iquipment() {
        return mc_menu_lbl_iquipment;
    }

    public Label getMc_menu_lbl_back() {
        return mc_menu_lbl_back;
    }

    public Label getMc_menu_lbl_property_Equipment_Issunce() {
        return mc_menu_lbl_property_Equipment_Issunce;
    }

    public Label getMc_menu_lbl_property_Equipment() {
        return mc_menu_lbl_property_Equipment;
    }

    public AnchorPane getMc_pnl_truck_status() {
        return mc_pnl_truck_status;
    }

    public TableView getMc_tableview_status() {
        return mc_tableview_status;
    }

    public TableColumn getMc_status_col_truck_id() {
        return mc_status_col_truck_id;
    }

    public TableColumn getMc_status_col_total_capacity() {
        return mc_status_col_total_capacity;
    }

    public TableView getMc_partition_tableview() {
        return mc_partition_tableview;
    }

    public TableColumn getMc_partition_col_partition_id() {
        return mc_partition_col_partition_id;
    }

    public TableColumn getMc_partition_col_partition_capacity() {
        return mc_partition_col_partition_capacity;
    }

    public TableColumn getMc_partition_col_partition_position() {
        return mc_partition_col_partition_position;
    }

    public TextField getMc_status_text_truck_id() {
        return mc_status_text_truck_id;
    }

    public TextField getMc_status_text_total_capacity() {
        return mc_status_text_total_capacity;
    }

    public Label getMc_status_btn_save() {
        return mc_status_btn_save;
    }

    public Label getMc_status_btn_edit() {
        return mc_status_btn_edit;
    }

    public Label getMc_parition_btn_save() {
        return mc_parition_btn_save;
    }

    public Label getMc_parition_btn_edit() {
        return mc_parition_btn_edit;
    }

    public TextField getMc_partition_text_partition_id() {
        return mc_partition_text_partition_id;
    }

     

    public AnchorPane getMc_pnl_replacements() {
        return mc_pnl_replacements;
    }

    public Label getMc_replacement_btn_delete() {
        return mc_replacement_btn_delete;
    }

    public Label getMc_replacement_btn_edit() {
        return mc_replacement_btn_edit;
    }

    public Label getMc_replacement_btn_save() {
        return mc_replacement_btn_save;
    }

    public DatePicker getMc_replacement_text_Date_datepcker() {
        return mc_replacement_text_Date_datepcker;
    }

    public TextField getMc_replacements_txt_location() {
        return mc_replacements_txt_location;
    }

    public TextField getMc_replacements_txt_description() {
        return mc_replacements_txt_description;
    }

    public ComboBox getMc_replacements_cbo_truck_part() {
        return mc_replacements_cbo_truck_part;
    }

    public ComboBox getMc_replacements_cbo_truck() {
        return mc_replacements_cbo_truck;
    }

    public TableView getMc_replacement_tableview() {
        return mc_replacement_tableview;
    }

    public TableColumn getMc_replacement_tblc_date() {
        return mc_replacement_tblc_date;
    }

    public TableColumn getMc_replacement_tblc_truck_id() {
        return mc_replacement_tblc_truck_id;
    }

    public TableColumn getMc_replacement_tblc_truck_part() {
        return mc_replacement_tblc_truck_part;
    }

    public TableColumn getMc_replacement_tblc_desription() {
        return mc_replacement_tblc_desription;
    }

    public TableColumn getMc_replacement_tblc_location() {
        return mc_replacement_tblc_location;
    }

    public Label getMc_replacements_btn_No_filter_by_range() {
        return mc_replacements_btn_No_filter_by_range;
    }

    public Label getMc_replacements_btn_filter_by_range() {
        return mc_replacements_btn_filter_by_range;
    }

    public DatePicker getMc_replacements_to_datepicker() {
        return mc_replacements_to_datepicker;
    }

    public DatePicker getMc_replacements_from_datepicker() {
        return mc_replacements_from_datepicker;
    }

    public Label getMc_replacements_btn_filter_by_month() {
        return mc_replacements_btn_filter_by_month;
    }

    public ComboBox getMc_replacements_cbo_truck1() {
        return mc_replacements_cbo_truck1;
    }

    public AnchorPane getMc_pnl_repair_services() {
        return mc_pnl_repair_services;
    }

    public Label getMc_repairservices_btn_delete() {
        return mc_repairservices_btn_delete;
    }

    public Label getMc_repairservices_btn_edit() {
        return mc_repairservices_btn_edit;
    }

    public Label getMc_repairservices_btn_save() {
        return mc_repairservices_btn_save;
    }

    public DatePicker getMc_repairservices_to_datepicker() {
        return mc_repairservices_to_datepicker;
    }

    public DatePicker getMc_repairservices_from_datepicker() {
        return mc_repairservices_from_datepicker;
    }

    public Label getMc_repairservices_btn_No_filter_by_range() {
        return mc_repairservices_btn_No_filter_by_range;
    }

    public Label getMc_repairservices_btn_filter_by_range() {
        return mc_repairservices_btn_filter_by_range;
    }

    public Label getMc_repairservices_btn_filterby_month_filter() {
        return mc_repairservices_btn_filterby_month_filter;
    }

    public TextArea getMc_repairservices_desciption() {
        return mc_repairservices_desciption;
    }

    public ComboBox getMc_repairservices_cbo_truck() {
        return mc_repairservices_cbo_truck;
    }

    public ComboBox getMc_repairservices_filterbymonth_cbo_truck() {
        return mc_repairservices_filterbymonth_cbo_truck;
    }

    public DatePicker getMc_repairservices_date_datepicker() {
        return mc_repairservices_date_datepicker;
    }

    public TableView getMc_repairservices_tableview() {
        return mc_repairservices_tableview;
    }

    public TableColumn getMc_repairservices_tblc_date() {
        return mc_repairservices_tblc_date;
    }

    public TableColumn getMc_repairserices_tblc_truck() {
        return mc_repairserices_tblc_truck;
    }

    public AnchorPane getMc_pnl_truck_parts() {
        return mc_pnl_truck_parts;
    }

    public TextField getMc_truck_parts_text_quantity() {
        return mc_truck_parts_text_quantity;
    }

    public TextField getMc_truck_parts_text_description() {
        return mc_truck_parts_text_description;
    }

    public TableView getMc_truck_parts_tableview() {
        return mc_truck_parts_tableview;
    }

    public TableColumn getMc_truck_parts_tblc_item_id() {
        return mc_truck_parts_tblc_item_id;
    }

    public TableColumn getMc_truck_parts_tblc_description() {
        return mc_truck_parts_tblc_description;
    }

    public TableColumn getMc_truck_parts_tblc_qty_on_hand() {
        return mc_truck_parts_tblc_qty_on_hand;
    }

    public ComboBox getMc_truck_parts_cbo_truck() {
        return mc_truck_parts_cbo_truck;
    }

    public Label getMc_truck_parts_btn_delete() {
        return mc_truck_parts_btn_delete;
    }

    public Label getMc_truck_parts_btn_edit() {
        return mc_truck_parts_btn_edit;
    }

    public Label getMc_truck_parts_btn_save() {
        return mc_truck_parts_btn_save;
    }

    public AnchorPane getMc_pnl_issuance() {
        return mc_pnl_issuance;
    }

    public TextArea getMc_protectice_equipment_issuance_textarea_remarks() {
        return mc_protectice_equipment_issuance_textarea_remarks;
    }

    public TextField getMc_protectice_equipment_issuance_text_description() {
        return mc_protectice_equipment_issuance_text_description;
    }

    public ComboBox getMc_protectice_equipment_ssuance_cbo_equipment() {
        return mc_protectice_equipment_ssuance_cbo_equipment;
    }

    public TableView getMc_protectice_equipmen_issuance_tableview() {
        return mc_protectice_equipmen_issuance_tableview;
    }

    public TableColumn getMc_protectice_equipment_issuance_tblc_date() {
        return mc_protectice_equipment_issuance_tblc_date;
    }

    public TableColumn getMc_protectice_equipment_issuance_tblc_equipment_id() {
        return mc_protectice_equipment_issuance_tblc_equipment_id;
    }

    public TableView getMc_protectice_equipment_issuance_driver_tableview() {
        return mc_protectice_equipment_issuance_driver_tableview;
    }

    public TableColumn getMc_protectice_equipment_issuance_tblc_driver() {
        return mc_protectice_equipment_issuance_tblc_driver;
    }

    public ComboBox getMc_protectice_equipment_issuance_cbo_filterby_month() {
        return mc_protectice_equipment_issuance_cbo_filterby_month;
    }

    public Label getMc_protectice_equipment_issuance_btn_filterby_month_filter() {
        return mc_protectice_equipment_issuance_btn_filterby_month_filter;
    }

    public Label getMc_protectice_equipment_issuance_btn_filter_by_range_filter() {
        return mc_protectice_equipment_issuance_btn_filter_by_range_filter;
    }

    public Label getMc_protectice_equipment_issuance_btn_No_filter_by_range() {
        return mc_protectice_equipment_issuance_btn_No_filter_by_range;
    }

    public DatePicker getMc_protectice_equipment_issuance_from_datepicker() {
        return mc_protectice_equipment_issuance_from_datepicker;
    }

    public DatePicker getMc_protectice_equipment_issuance_to_datepicker() {
        return mc_protectice_equipment_issuance_to_datepicker;
    }

    public Label getMc_protective_equipment_issuance_btn_save() {
        return mc_protective_equipment_issuance_btn_save;
    }

    public Label getMc_protective_equipment_issuance_btn_edit() {
        return mc_protective_equipment_issuance_btn_edit;
    }

    public Label getMc_protective_equipment_issuance_btn_delete() {
        return mc_protective_equipment_issuance_btn_delete;
    }

    public AnchorPane getMc_pnl_protective_equipment() {
        return mc_pnl_protective_equipment;
    }

    public TableView getMc_protective_equipment_tableview() {
        return mc_protective_equipment_tableview;
    }

    public TableColumn getMc_protective_equipment_col_equipment_id() {
        return mc_protective_equipment_col_equipment_id;
    }

    public TableColumn getMc_protective_equipment_col_description() {
        return mc_protective_equipment_col_description;
    }

    public TableColumn getMc_protective_equipment_col_qty_on_hand() {
        return mc_protective_equipment_col_qty_on_hand;
    }

    public Label getMc_protective_equipment_btn_delete() {
        return mc_protective_equipment_btn_delete;
    }

    public Label getMc_protective_equipment_btn_edit() {
        return mc_protective_equipment_btn_edit;
    }

    public Label getMc_protective_equipment_btn_save() {
        return mc_protective_equipment_btn_save;
    }

    public ComboBox getMc_protective_equipment_cbo_equipment() {
        return mc_protective_equipment_cbo_equipment;
    }

    public TextField getMc_protective_equipment_txt_description() {
        return mc_protective_equipment_txt_description;
    }

    public TextField getMc_protective_equipment_txt_qty_on_hand() {
        return mc_protective_equipment_txt_qty_on_hand;
    }

    public TextField getMc_partition_text_capacity() {
        return mc_partition_text_capacity;
    }

    public BookMasterControl getBookMasterControl() {
        return bookMasterControl;
    }

    public Label getMc_parition_btn_update() {
        return mc_parition_btn_update;
    }

    public Label getMc_status_btn_update() {
        return mc_status_btn_update;
    }

    public McTruckStatus getMc_truckStatus() {
        return Mc_truckStatus;
    }

    public TextField getMc_partition_text_position() {
        return mc_partition_text_position;
    }

    public ComboBox getMc_replacements_cbo_filterbyMonth() {
        return mc_replacements_cbo_filterbyMonth;
    }

    public Mc_RepairServices getMcRepairservices() {
        return mcRepairservices;
    }

    public Mc_Replacements getMcReplacements() {
        return mcReplacements;
    }

    public TextField getMc_partition_text_truck_id() {
        return mc_partition_text_truck_id;
    }

    public Mc_TruckParts getMcTruckparts() {
        return mcTruckparts;
    }

    public Scheduling getScheduling() {
        return scheduling;
    }

    public Label getOrder_stack_btn_edit1() {
        return order_stack_btn_edit1;
    }

    public Label getOrder_stack_btn_edit111() {
        return order_stack_btn_edit111;
    }

    public Label getOrder_stack_btn_edit112() {
        return order_stack_btn_edit112;
    }

    public Label getOrder_stack_btn_edit11() {
        return order_stack_btn_edit11;
    }

    public Label getOrder_stack_btn_edit1121() {
        return order_stack_btn_edit1121;
    }

    public Label getOrder_stack_btn_edit1111() {
        return order_stack_btn_edit1111;
    }

    public TextField getOrder_stack_txt_customer1() {
        return order_stack_txt_customer1;
    }

    public TextField getOrder_stack_txt_customer11() {
        return order_stack_txt_customer11;
    }

    public TextField getOrder_stack_txt_customer111() {
        return order_stack_txt_customer111;
    }

    public TextField getOrder_stack_txt_customer1111() {
        return order_stack_txt_customer1111;
    }

    public TextField getOrder_stack_txt_customer11111() {
        return order_stack_txt_customer11111;
    }

    public TextField getOrder_stack_txt_customer111111() {
        return order_stack_txt_customer111111;
    }

    public TextField getOrder_stack_txt_customer112() {
        return order_stack_txt_customer112;
    }

    public TextField getOrder_stack_txt_customer1121() {
        return order_stack_txt_customer1121;
    }

    public TextField getOrder_stack_txt_customer11211() {
        return order_stack_txt_customer11211;
    }

    public TextField getOrder_stack_txt_customer112111() {
        return order_stack_txt_customer112111;
    }

    public Label getStatus_reports_title() {
        return status_reports_title;
    }

    public ComboBox getStatus_reports_cbo_id() {
        return status_reports_cbo_id;
    }

    public ScrollPane getManual_scrl() {
        return manual_scrl;
    }

    public Label getMain_menu_lbl_master_control() {
        return main_menu_lbl_master_control;
    }

    public Mc_PP_Issuance getMcPPIssuance() {
        return mcPPIssuance;
    }

    public Mc_PPEquipment getMcPPEquipment() {
        return mcPPEquipment;
    }

    public ComboBox getTruck_parts_cbo_supplier() {
        return truck_parts_cbo_supplier;
    }

    public TextField getTruck_parts_txt_amount() {
        return truck_parts_txt_amount;
    }

    public TextField getTruck_parts_txt_invoice_number() {
        return truck_parts_txt_invoice_number;
    }

    public ComboBox getEquipment_cbo_supplier() {
        return equipment_cbo_supplier;
    }

    public TextField getEquipment_txt_amount() {
        return equipment_txt_amount;
    }

    public TextField getEquipment_txt_invooice_number() {
        return equipment_txt_invooice_number;
    }

    public TableView getMc_truck_parts_tableview_supplier() {
        return mc_truck_parts_tableview_supplier;
    }

    public TableColumn getMc_truck_parts_tblc_supplier() {
        return mc_truck_parts_tblc_supplier;
    }

    public TableColumn getMc_truck_parts_tblc_supplier_amount() {
        return mc_truck_parts_tblc_supplier_amount;
    }

    public TableColumn getMc_truck_parts_tblc_supplier_invoicenumber() {
        return mc_truck_parts_tblc_supplier_invoicenumber;
    }

    public TextField getMc_truck_parts_text_supplier_amount() {
        return mc_truck_parts_text_supplier_amount;
    }

    public ComboBox getMc_truck_parts_cbo_supplier() {
        return mc_truck_parts_cbo_supplier;
    }

    public Label getMc_truck_parts_btn_supplier_update() {
        return mc_truck_parts_btn_supplier_update;
    }

    public TextField getMc_truck_parts_text_supplier_voicenumber() {
        return mc_truck_parts_text_supplier_voicenumber;
    }

    public Label getMc_truck_parts_btn_supplier_add() {
        return mc_truck_parts_btn_supplier_add;
    }

    public TextField getMc_protective_equipment_txt_supplier_voicenumber() {
        return mc_protective_equipment_txt_supplier_voicenumber;
    }

    public TextField getMc_protective_equipment_txt_supplier_amount() {
        return mc_protective_equipment_txt_supplier_amount;
    }

    public ComboBox getMc_protective_equipment_cbo_supplier() {
        return mc_protective_equipment_cbo_supplier;
    }

    public Label getMc_protective_equipment_btn_supplier_add() {
        return mc_protective_equipment_btn_supplier_add;
    }

    public Label getMc_protective_equipment_btn_supplier_update() {
        return mc_protective_equipment_btn_supplier_update;
    }

    public TableView getMc_protective_equipment_tableview_supplier() {
        return mc_protective_equipment_tableview_supplier;
    }

    public TableColumn getMc_protective_equipment_col_supplier() {
        return mc_protective_equipment_col_supplier;
    }

    public TableColumn getMc_protective_equipment_col_supplier_amount() {
        return mc_protective_equipment_col_supplier_amount;
    }

    public TableColumn getMc_protective_equipment_col_supplier_voicenumber() {
        return mc_protective_equipment_col_supplier_voicenumber;
    }

    //end of mc setters and getters--------------
    
    
    
    //isiaih setters and getters;--------
     
    
    
    public Label getmanual_op_btn_cancel(){
        return getManual_op_btn_cancel();
    }

    

    public Label getManual_op_btn_cancel() {
        return manual_op_btn_cancel;
    }

    public TableColumn getDeployment_tbl_truck_col_truck() {
        return deployment_tbl_truck_col_truck;
    }

    public TableView getDeployment_tbl_partition() {
        return deployment_tbl_partition;
    }

    public TableColumn getDeployment_tbl_partition_col_partition() {
        return deployment_tbl_partition_col_partition;
    }

    public TableColumn getDeployment_tbl_partition_col_customer() {
        return deployment_tbl_partition_col_customer;
    }

    public TableColumn getDeployment_tbl_partition_col_product() {
        return deployment_tbl_partition_col_product;
    }

    public TableColumn getDeployment_tbl_partition_col_qty() {
        return deployment_tbl_partition_col_qty;
    }

    public TextField getDeployment_txt_hbmtr() {
        return deployment_txt_hbmtr;
    }

    public TextField getDeployment_txt_fuel_cons() {
        return deployment_txt_fuel_cons;
    }

    public Label getDeployment_btn_print_details() {
        return deployment_btn_print_details;
    }

    public Label getDeployment_btn_truck_deployed() {
        return deployment_btn_truck_deployed;
    }

    public MenuButton getDeployment_ctx_abort() {
        return deployment_ctx_abort;
    }

    public MenuItem getDeployment_ctx_abort_current() {
        return deployment_ctx_abort_current;
    }

    public MenuItem getDeployment_ctx_abort_all() {
        return deployment_ctx_abort_all;
    }

    public TableColumn getOn_delivery_tbl_orders_col_status() {
        return on_delivery_tbl_orders_col_status;
    }

    public CheckBox getOn_delivery_cbx_failed() {
        return on_delivery_cbx_failed;
    }

    public TextArea getOn_delvivery_txt_reason() {
        return on_delvivery_txt_reason;
    }

    public MenuItem getOn_delivery_ctx_re_sched() {
        return on_delivery_ctx_re_sched;
    }

    public MenuItem getOn_delivery_ctx_cancel() {
        return on_delivery_ctx_cancel;
    }

    public MenuButton getOn_delivery_mnu_action() {
        return on_delivery_mnu_action;
    }

    public Delivery getDelivery() {
        return delivery;
    }

    public Label getOrders_menu_lbl_frequency() {
        return orders_menu_lbl_frequency;
    }

    public Label getOrders_menu_lbl_failed_orders() {
        return orders_menu_lbl_failed_orders;
    }

    public AnchorPane getOrders_pnl_frequency() {
        return orders_pnl_frequency;
    }

    public DatePicker getFrequency_dtp_from() {
        return frequency_dtp_from;
    }

    public DatePicker getFrequency_dtp_until() {
        return frequency_dtp_until;
    }

    public LineChart getFrequency_chart() {
        return frequency_chart;
    }

    public NumberAxis getFrequency_chart_number_axis() {
        return frequency_chart_number_axis;
    }

    public CategoryAxis getFrequency_chart_category_axis() {
        return frequency_chart_category_axis;
    }

    public AnchorPane getOrders_pnl_failed_orders() {
        return orders_pnl_failed_orders;
    }

    public DatePicker getFailed_orders_dtp_until() {
        return failed_orders_dtp_until;
    }

    public DatePicker getFailed_orders_dtp_from() {
        return failed_orders_dtp_from;
    }

    public TableView getFailed_orders_tbl_failed() {
        return failed_orders_tbl_failed;
    }

    public TableColumn getFailed_orders_tbl_failed_col_date() {
        return failed_orders_tbl_failed_col_date;
    }

    public TableColumn getFailed_orders_tbl_failed_col_customer() {
        return failed_orders_tbl_failed_col_customer;
    }

    public TableColumn getFailed_orders_tbl_failed_col_product() {
        return failed_orders_tbl_failed_col_product;
    }

    public TableColumn getFailed_orders_tbl_failed_col_qty() {
        return failed_orders_tbl_failed_col_qty;
    }

    public TableColumn getFailed_orders_tbl_failed_col_remarks() {
        return failed_orders_tbl_failed_col_remarks;
    }

    public TextArea getFailed_orders_txt_remarks() {
        return failed_orders_txt_remarks;
    }

    public ScrollPane getOrder_stack_scrl() {
        return order_stack_scrl;
    }

    public TableView getOrder_stack_vault_tbl_customer() {
        return order_stack_vault_tbl_customer;
    }

    public TableColumn getOrder_stack_vault_tbl_col_customer() {
        return order_stack_vault_tbl_col_customer;
    }

    public Label getOrder_stack_btn_order_stack() {
        return order_stack_btn_order_stack;
    }

    public TableColumn getOrder_stack_tbl_order_col_schedule() {
        return order_stack_tbl_order_col_schedule;
    }

    public Label getOrder_stack_btn_order_vault() {
        return order_stack_btn_order_vault;
    }

    public TableView getOrder_stack_vault_tbl_order() {
        return order_stack_vault_tbl_order;
    }

    public TableColumn getOrder_stack_vault_tbl_order_col_product() {
        return order_stack_vault_tbl_order_col_product;
    }

    public TableColumn getOrder_stack_vault_tbl_order_col_qty() {
        return order_stack_vault_tbl_order_col_qty;
    }

    public TableColumn getOrder_stack_vault_tbl_order_col_schedule() {
        return order_stack_vault_tbl_order_col_schedule;
    }

    public Label getOrder_stack_btn_see_vault() {
        return order_stack_btn_see_vault;
    }

    public Label getOrder_stack_lbl_title() {
        return order_stack_lbl_title;
    }

    public DatePicker getOrder_stack_dtp_sched() {
        return order_stack_dtp_sched;
    }

    public Label getOrder_stack_btn_include_to_stack() {
        return order_stack_btn_include_to_stack;
    }

    public TableColumn getOn_delivery_tbl_orders_col_customer() {
        return on_delivery_tbl_orders_col_customer;
    }

    public TableColumn getOn_delivery_tbl_orders_col_qty() {
        return on_delivery_tbl_orders_col_qty;
    }

    public TableColumn getOn_delivery_tbl_orders_col_product() {
        return on_delivery_tbl_orders_col_product;
    }

    public TextField getDelivered_orders_txt_km_driven() {
        return delivered_orders_txt_km_driven;
    }

    public TextField getDelivered_orders_txt_fuel_consumpt() {
        return delivered_orders_txt_fuel_consumpt;
    }

    public DatePicker getDelivered_orders_dtp_date() {
        return delivered_orders_dtp_date;
    }

    public TableColumn getDelivered_orders_tbl_orders_col_status() {
        return delivered_orders_tbl_orders_col_status;
    }

    public TextField getTruck_parts_txt_quantity() {
        return truck_parts_txt_quantity;
    }

     

    public TextField getTruck_parts_txt_invoice_num() {
        return truck_parts_txt_invoice_num;
    }

    public TextField getEquipment_txt_qty() {
        return equipment_txt_qty;
    }

     

    public TextField getEquipment_txt_description1() {
        return equipment_txt_description1;
    }

    public TextField getEquipment_txt_invoice_num() {
        return equipment_txt_invoice_num;
    }

    public Label getFailed_orders_btn_filter() {
        return failed_orders_btn_filter;
    }

    public Label getFrequency_btn_filter() {
        return frequency_btn_filter;
    }

    /**
     * @return the order_frequency
     */
    public OrderFrequency getOrder_frequency() {
        return order_frequency;
    }

    /**
     * @return the failed_orders
     */
    public FailedOrders getFailed_orders() {
        return failed_orders;
    }

    /**
     * @return the delivered_order_btn_delivery_today
     */
    public Label getDelivered_order_btn_delivery_today() {
        return delivered_order_btn_delivery_today;
    }

    /**
     * @return the delivered_order_txt_reason
     */
    public TextArea getDelivered_order_txt_reason() {
        return delivered_order_txt_reason;
    }

    public Pane getFrequency_pnl_chart() {
        return frequency_pnl_chart;
    }

    public Label getFrequency_btn_print() {
        return frequency_btn_print;
    }

     

      
 

    public TableColumn getTruck_parts_tbl_truck_parts_col_price() {
        return truck_parts_tbl_truck_parts_col_price;
    }

    public TableColumn getEquipments_tbl_equipments_col_price() {
        return equipments_tbl_equipments_col_price;
    }

    public TextField getMc_truck_parts_text_price() {
        return mc_truck_parts_text_price;
    }

    public TableColumn getMc_truck_parts_tblc_supplier_date() {
        return mc_truck_parts_tblc_supplier_date;
    }

    public TableColumn getMc_truck_parts_tblc_supplier_truckpart_id() {
        return mc_truck_parts_tblc_supplier_truckpart_id;
    }

    public TableColumn getMc_truck_parts_tblc_supplier_quantity() {
        return mc_truck_parts_tblc_supplier_quantity;
    }

    public TextField getMc_truck_parts_text_supplier_quantity() {
        return mc_truck_parts_text_supplier_quantity;
    }

    public TableColumn getMc_truck_parts_tblc_price() {
        return mc_truck_parts_tblc_price;
    }

    public DatePicker getMc_truck_parts_supplier_date() {
        return mc_truck_parts_supplier_date;
    }

    public TableColumn getMc_protective_equipment_col_price() {
        return mc_protective_equipment_col_price;
    }

    public DatePicker getMc_protective_equipment_date_supplier() {
        return mc_protective_equipment_date_supplier;
    }

    public TextField getMc_protective_equipment_txt_price() {
        return mc_protective_equipment_txt_price;
    }

    public TableColumn getMc_protective_equipment_col_supplier_date() {
        return mc_protective_equipment_col_supplier_date;
    }

    public TableColumn getMc_protective_equipment_col_supplier_equipment_id() {
        return mc_protective_equipment_col_supplier_equipment_id;
    }

    public TableColumn getMc_protective_equipment_col_supplier_quantity() {
        return mc_protective_equipment_col_supplier_quantity;
    }

    public Label getTruck_parts_btn_reresh() {
        return truck_parts_btn_reresh;
    }

    public TextField getMc_protective_equipment_txt_supplier_quantity() {
        return mc_protective_equipment_txt_supplier_quantity;
    }

    public Label getEquipment_btn_refreshh() {
        return equipment_btn_refreshh;
    }

    public Label getMc_truck_parts_btn_filter_by_month() {
        return mc_truck_parts_btn_filter_by_month;
    }

    public Label getMc_truck_parts_btn_filter_by_range() {
        return mc_truck_parts_btn_filter_by_range;
    }

    public Label getMc_truck_parts_btn_no_filter() {
        return mc_truck_parts_btn_no_filter;
    }

    public ComboBox getMc_truckpart_filterbymonth_cbo_truck() {
        return mc_truckpart_filterbymonth_cbo_truck;
    }

    public DatePicker getMc_truckpart_from_datepicker() {
        return mc_truckpart_from_datepicker;
    }

    public DatePicker getMc_truckpart_to_datepicker() {
        return mc_truckpart_to_datepicker;
    }

    public Label getMc_equipment_btn_filter_by_month() {
        return mc_equipment_btn_filter_by_month;
    }

    public Label getMc_equipment_btn_filter_by_range() {
        return mc_equipment_btn_filter_by_range;
    }

    public Label getMc_equipment_btn_no_filter() {
        return mc_equipment_btn_no_filter;
    }

    public ComboBox getMc_equipment_filterbymonth_cbo_truck() {
        return mc_equipment_filterbymonth_cbo_truck;
    }

    public DatePicker getMc_equipment_from_datepicker() {
        return mc_equipment_from_datepicker;
    }

    public DatePicker getMc_equipment_to_datepicker1() {
        return mc_equipment_to_datepicker1;
    }

    public Label getProduct_btn_add() {
        return product_btn_add;
    }

    public Label getCustomer_btn_update() {
        return customer_btn_update;
    }

    public ComboBox getCustomer_cbo_cus_name() {
        return customer_cbo_cus_name;
    }

    public Label getCustomer_btn_refresh() {
        return customer_btn_refresh;
    }

    public TableView getCustomer_tableview() {
        return customer_tableview;
    }

    public TableColumn getCustomer_tblc_name() {
        return customer_tblc_name;
    }

    public ComboBox getProduct_cbo_product_id() {
        return product_cbo_product_id;
    }

    public Label getCustomer_btn_add() {
        return customer_btn_add;
    }

    public TableView getProduct_tableview() {
        return product_tableview;
    }

    public TableColumn getProduct_tblc_product_id() {
        return product_tblc_product_id;
    }

    public TableColumn getProduct_tblc_description() {
        return product_tblc_description;
    }

    public Label getProduct_btn_update() {
        return product_btn_update;
    }

    public TextField getProduct_txt_description() {
        return product_txt_description;
    }

    public AnchorPane getPnl_customer() {
        return pnl_customer;
    }

    public AnchorPane getPnl_customer_and_product() {
        return pnl_customer_and_product;
    }

    public Label getMc_menu_lbl_customer_and_product() {
        return mc_menu_lbl_customer_and_product;
    }

    public Label getMc_menu_lbl_supplier() {
        return mc_menu_lbl_supplier;
    }

    public Label getMc_menu_lbl_driver() {
        return mc_menu_lbl_driver;
    }

    public AnchorPane getPnl_supplier() {
        return pnl_supplier;
    }

    public TableView getSupplier_tableview() {
        return supplier_tableview;
    }

    public TableColumn getSupplier_id_tblc() {
        return supplier_id_tblc;
    }

    public TableColumn getSupplier_name_tblc() {
        return supplier_name_tblc;
    }

    public ComboBox getSupplier_cbo_id() {
        return Supplier_cbo_id;
    }

    public TextField getSupplier_name() {
        return supplier_name;
    }

    public Label getSupplier_btn_add() {
        return supplier_btn_add;
    }

    public Label getSupplier_btn_update() {
        return supplier_btn_update;
    }

    public Label getSupplier_btn_refresh() {
        return supplier_btn_refresh;
    }

    public AnchorPane getPnl_driver() {
        return pnl_driver;
    }

    public TableView getDriver_tableview() {
        return driver_tableview;
    }

    public TableColumn getDriver_tblc_driver_id() {
        return driver_tblc_driver_id;
    }

    public TableColumn getDriver_tblc_fname() {
        return driver_tblc_fname;
    }

    public TableColumn getDriver_tblc_lname() {
        return driver_tblc_lname;
    }

    public TableColumn getDriver_tblc_mname() {
        return driver_tblc_mname;
    }

    public ComboBox getDriver_cbo_driver_id() {
        return driver_cbo_driver_id;
    }

    public TextField getDriver_txt_fname() {
        return driver_txt_fname;
    }

    public TextField getDriver_txt_lname() {
        return driver_txt_lname;
    }

    public TextField getDriver_txt_mname() {
        return driver_txt_mname;
    }

    public Label getDriver_btn_add() {
        return driver_btn_add;
    }

    public Label getDriver_btn_update() {
        return driver_btn_update;
    }

    public Label getDriver_btn_refresh() {
        return driver_btn_refresh;
    }

    public MC_CustomerAndProduct getMcCustomerAndroduct() {
        return mcCustomerAndroduct;
    }

    public MC_Supplier getMcSupplier() {
        return mcSupplier;
    }

    public MC_Driver getMcDriver() {
        return mcDriver;
    }

    public Label getReplacements_btn_refresh() {
        return replacements_btn_refresh;
    }

    public Label getServices_btn_set_as_rfresh() {
        return services_btn_set_as_rfresh;
    }
    
 
    
    
}
    
    
    ///end of isiaih setters and getters;

    
    
