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
import javax.xml.bind.DataBindingException;

/**
 *
 * @author jorge
 */
public class MC_Supplier {
 
private LayoutController controller; 
private TableView supplier_tableview;
private TableColumn supplier_id_tblc;
private TableColumn supplier_name_tblc;
private ComboBox Supplier_cbo_id;
private TextField supplier_name;
private Label supplier_btn_add;
private Label supplier_btn_update;
private Label supplier_btn_refresh;
private ObservableList<DataSupplier>dataSuppliers;
private ErrorTraps traps = new ErrorTraps();
    
    public MC_Supplier(LayoutController controller_param) {
        this.controller = controller_param;
        this.supplier_tableview = controller_param.getSupplier_tableview();
        this.supplier_id_tblc = controller_param.getSupplier_id_tblc();
        this.supplier_name_tblc = controller_param.getSupplier_name_tblc();
        this.Supplier_cbo_id = controller_param.getSupplier_cbo_id();
        this.supplier_name = controller_param.getSupplier_name();
        this.supplier_btn_add = controller_param.getSupplier_btn_add();
        this.supplier_btn_update = controller_param.getSupplier_btn_update();
        this.supplier_btn_refresh = controller_param.getSupplier_btn_refresh();
        
        //initialized module;
        initializedModulesSupplier();
        
    }
    
    public void initializedModulesSupplier(){
        setTablecoloumns();
        setitemsSupplier();
        cboSupplier();;
        cboSupplierdescription();
        clicktblsupplier();
        btnSupplierAdd();
        btnUpdate();
        btnSupplierRefresh();
    }
    
    //set table andcolumns 
   public void setTablecoloumns(){
       dataSuppliers = FXCollections.observableArrayList();
       supplier_id_tblc.setCellValueFactory(new PropertyValueFactory("supplier_id"));
       supplier_name_tblc.setCellValueFactory(new PropertyValueFactory("name"));
       supplier_tableview.setItems(dataSuppliers);
   } 
   
   //set all items from supplier
    public void setitemsSupplier(){
                    DatabaseConnection dbsupplier = new DatabaseConnection("root", "localhost", "eyefleet", "ramjorgeyao");
                    ResultSet rssupplier = dbsupplier.setQuery("SELECT * FROM eyefleet.supplier");
                    try {
                        while(rssupplier.next()){
                            dataSuppliers.add(new DataSupplier(rssupplier.getString("supplier_id"), 
                                    rssupplier.getString("name")));
                            
                        }
        } catch (Exception e) {
            System.out.print("ERROR on MC Supplier setitemsSupplier" + e);
            System.exit(0);
        }
    }
    
    
    //select all data from supplier w/ combobox
    public void cboSupplier(){
        //data clear cbosuppier;
            Supplier_cbo_id.getItems().clear();
            
            DatabaseConnection dbcbosupplier = new DatabaseConnection("root", "localhost", "eyefleet", "ramjorgeyao");
            ResultSet rscboSupplier = dbcbosupplier.setQuery("SELECT supplier_id  FROM eyefleet.supplier");
                    try {
                            while(rscboSupplier.next()){
                                Supplier_cbo_id.getItems().add(rscboSupplier.getString("supplier_id"));
                                
                            }
        } catch (Exception e) {
             System.out.print("ERROR on MC Supplier cboSupplier" + e);
            System.exit(0);
            
        }
    }
    
    //display the descrription of the supplier in the selected combobox;
    public void cboSupplierdescription(){
        Supplier_cbo_id.setOnAction((ActionEvent)->{
         
            try {
                  DatabaseConnection dbsupdescription = new DatabaseConnection("root", "localhost", "eyefleet", "ramjorgeyao");
                 ResultSet rsupdescription = dbsupdescription.setQuery("SELECT  name  FROM eyefleet.supplier"
                    + " WHERE supplier_id ='" + Supplier_cbo_id.getValue()+ "'");
                while(rsupdescription.next()){
                    supplier_name.setText(rsupdescription.getString("name"));
                    
                }
            } catch (Exception e) {
                System.out.print("ERROR on MC Supplier cboSupplierdescription" + e);
                 
            }
        
        });
        
    }
    
    //click table function
    public void clicktblsupplier(){
     supplier_tableview.setOnMouseClicked((MouseEvent)->{
        DataSupplier dataSupplierselect = (DataSupplier)supplier_tableview.getSelectionModel().getSelectedItem();
        if(dataSupplierselect != null){
            Supplier_cbo_id.setValue(dataSupplierselect.getSupplier_id());
            supplier_name.setText(dataSupplierselect.getName());
            
        }
    
        });
        
    }
    
    
    public void btnSupplierAdd(){
        supplier_btn_add.setOnMouseClicked((MouseEvent)->{
            //check if the truck id is exist;        
                    boolean exist = false;
                    DatabaseConnection dbcbo = new DatabaseConnection("root", "localhost", "eyefleet", "ramjorgeyao");
                    ResultSet rscbo = dbcbo.setQuery("SELECT * FROM eyefleet.supplier;");
                 try {
                         while(rscbo.next()){
                              if( Supplier_cbo_id.getValue().equals(rscbo.getString("supplier_id")))  {
                                exist = true;
                             break;
                     }
                        }
                  } catch (Exception e) {
             }
             
             
              if(exist ==true){
                              controller.getMessage().shortToast( "Supplier Id  already Exist::", 2000);

            }//end of error trps;
            
              else if (Supplier_cbo_id.getValue()==null){
                              controller.getMessage().shortToast( "Please Provide Supplier ID:", 2000);
                  
              }
              else if (supplier_name.getText().isEmpty() || traps.isNumerical(supplier_name.getText())){
                              controller.getMessage().shortToast( "Please Provide  Correct name:", 2000);
              }
              
              else{
            
            try {
             DatabaseConnection dbadd = new DatabaseConnection("root", "localhost", "eyefleet", "ramjorgeyao");
            dbadd.updateDatabase("INSERT INTO eyefleet.supplier(supplier_id,  name)"
                    + "VALUES('" + Supplier_cbo_id.getValue()+ "', "
                                       + "'" +supplier_name.getText() + "')");
            
            //clear data
            dataSuppliers.clear();
            //refresh table
            setitemsSupplier();
            //combobox refresh
            cboSupplier();
            } catch (Exception e) {
                 System.out.print("ERROR on MC Supplier btnSupplierAdd" + e);
             }
              }//end of if;
        });
    }
    
    public void btnUpdate(){
        supplier_btn_update.setOnMouseClicked((MouseEvent)->{
                if (Supplier_cbo_id.getValue()==null){
                              controller.getMessage().shortToast( "Please Provide Supplier ID:", 2000);
                  
              }
              else if (supplier_name.getText().isEmpty() || traps.isNumerical(supplier_name.getText())){
                              controller.getMessage().shortToast( "Please Provide  Correct name:", 2000);
              }
              
              
              else{
            try {
                 DataSupplier dataSupplierselect = (DataSupplier)supplier_tableview.getSelectionModel().getSelectedItem();
                DatabaseConnection dbupdate =  new DatabaseConnection("root", "localhost", "eyefleet", "ramjorgeyao");
                dbupdate.updateDatabase("UPDATE eyefleet.supplier SET name ='" + supplier_name.getText()+"' "
                    + " WHERE  supplier_id  ='"+  dataSupplierselect.getSupplier_id()+ "'");

                //clear data first
                dataSuppliers.clear();
                //refreh table 
                setitemsSupplier();
                
            } catch (Exception e) {
                 
            }
              }//end of if;
        });
    }
    
    public void btnSupplierRefresh(){
        supplier_btn_refresh.setOnMouseClicked((MouseEvent)->{
            //cleat cbo items
            Supplier_cbo_id.setValue(null);
             //clear name textfields
            supplier_name.clear();
            
            //clear table
            dataSuppliers.clear();
            //refresh table
            setitemsSupplier();
            //refresh cbo items
            cboSupplier();
            
            
           
        });
    }
    
    
    
    
    
    
    public class DataSupplier{
        private String supplier_id;
        private String name;
         public DataSupplier(String supplier_id,  String name ) {
            this.supplier_id = supplier_id;
            this.name = name;
         }

        public String getSupplier_id() {
            return supplier_id;
        }

        public void setSupplier_id(String supplier_id) {
            this.supplier_id = supplier_id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

   
        
        
        
    }
    
    
}
