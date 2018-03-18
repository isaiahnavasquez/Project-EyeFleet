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

/**
 *
 * @author jorge
 */
public class MC_CustomerAndProduct {

    private LayoutController controller;
    //error traps;
        ErrorTraps traps = new ErrorTraps();

     //customer
    private TableView customer_tableview;
    private TableColumn customer_tblc_name;
    private ComboBox customer_cbo_cus_name;
    private Label customer_btn_add;
    private Label customer_btn_update;
    private Label customer_btn_refresh;
    private ObservableList<DataCustomer>dataCustomers;
    

    //product
    private TableView product_tableview;
    private TableColumn product_tblc_product_id;
    private TableColumn product_tblc_description;
    private ComboBox product_cbo_product_id;
    private TextField product_txt_description;
    private Label product_btn_add;
    private Label product_btn_update;
    private ObservableList<DataProduct>dataProducts;

    public MC_CustomerAndProduct(LayoutController controller_param) {
        this.controller = controller_param;
        //customer
        this.customer_tableview = controller_param.getCustomer_tableview();
        this.customer_tblc_name = controller_param.getCustomer_tblc_name();
        this.customer_cbo_cus_name = controller_param.getCustomer_cbo_cus_name();
        this.customer_btn_add = controller_param.getCustomer_btn_add();
        this.customer_btn_update = controller_param.getCustomer_btn_update();
        this.customer_btn_refresh = controller_param.getCustomer_btn_refresh();
        
        //product
        this.product_tableview = controller_param.getProduct_tableview();
        this.product_tblc_product_id = controller_param.getProduct_tblc_product_id();
        this.product_tblc_description = controller_param.getProduct_tblc_description();
        this.product_cbo_product_id = controller_param.getProduct_cbo_product_id();
        this.product_txt_description = controller_param.getProduct_txt_description();
        this.product_btn_add = controller_param.getProduct_btn_add();
        this.product_btn_update = controller_param.getProduct_btn_update();
        
        //initialized modules;
        initializedCustomer();
        
        //initialized prouct
        initializedProduct();
    }

    //initialized modules
    public void initializedCustomer(){
        setTableColumnsCustomer();
        setitemsCustomer();
        setCbocustomer();
        clickCustmertbl();
        btnCustomerAdd();
        btnCustomerUpdate();
        btnCustomerRefresh();
    }
    
    public void setTableColumnsCustomer(){
        dataCustomers = FXCollections.observableArrayList();
        customer_tblc_name.setCellValueFactory(new PropertyValueFactory("name"));
        customer_tableview.setItems(dataCustomers);
        
    }
    
    public  void setitemsCustomer(){
        DatabaseConnection dbcustomer = new DatabaseConnection("root", "localhost", "eyefleet", "ramjorgeyao");
        ResultSet rscustomer = dbcustomer.setQuery("SELECT * FROM eyefleet.customer");
        try {
            while(rscustomer.next()){
                            dataCustomers.add(new DataCustomer(rscustomer.getString("customer_id"), 
                                    rscustomer.getString("name")));
              }
            
        } catch (Exception e) {
            
        }
    } 
    
    public void setCbocustomer(){
                  try {
                     //clear data first;
                     customer_cbo_cus_name.getItems().clear();
                     
                      DatabaseConnection dbCbocustomer = new DatabaseConnection("root", "localhost", "eyefleet", "ramjorgeyao");
                      ResultSet rsCbocustomer  = dbCbocustomer.setQuery("SELECT  name FROM eyefleet.customer");
                       while(rsCbocustomer.next()){
                                      customer_cbo_cus_name.getItems().add(rsCbocustomer.getString("name"));
                       }
                       
                   
                   
              } catch (Exception e) {
                 System.out.print("Error on MC_CustomerandProduct setCbocustomer " + e);
                 System.exit(0);
        }
    }
    
    public void clickCustmertbl(){
        customer_tableview.setOnMouseClicked((MouseEvent)->{
            DataCustomer dataCustomerselect = (DataCustomer)customer_tableview.getSelectionModel().getSelectedItem();
                if(dataCustomerselect != null){
                customer_cbo_cus_name.setValue(dataCustomerselect.getName());
         }
        });
    }
    
    
    public void btnCustomerAdd(){
    
        customer_btn_add.setOnMouseClicked((MouseEvent)->{
            //check if the truck id is exist;        
                    boolean exist = false;
                    DatabaseConnection dbcbo = new DatabaseConnection("root", "localhost", "eyefleet", "ramjorgeyao");
                    ResultSet rscbo = dbcbo.setQuery("SELECT * FROM eyefleet.customer;");
                 try {
                         while(rscbo.next()){
                              if( customer_cbo_cus_name.getValue().equals(rscbo.getString("name")))  {
                                exist = true;
                             break;
                     }
                        }
                  } catch (Exception e) {
             }
             
             
              if(exist ==true){
                              controller.getMessage().shortToast( "Customer Name   already Exist::", 2000);

            }//end of error trps;
            
            
              else   if(customer_cbo_cus_name.getValue()==null  || traps.isNumerical(customer_cbo_cus_name.getValue().toString()) ){
                   controller.getMessage().shortToast("Please Provide a  Customer", 2000);
            
        }
                  
                else{
            try {
                DatabaseConnection dbadd = new DatabaseConnection("root", "localhost", "eyefleet", "ramjorgeyao");
                 dbadd.updateDatabase("INSERT INTO eyefleet.customer(name)"
                         + "  VALUES ('"+ customer_cbo_cus_name.getValue()+"')" );
                 //clear data first and refresh table
                 dataCustomers.clear();
                 //refresh table
                 setitemsCustomer();
                
                  //refresh cbo
                 setCbocustomer();
                
                 
            } catch (Exception e) {
                  System.out.print("Error on MC_CustomerandProduct btnCustomerAdd " + e);
                 System.exit(0);
            }
                }//end of if;
        });
        
    }
    
    public void btnCustomerUpdate(){
       
        customer_btn_update.setOnMouseClicked((MouseEvent)->{
              if(customer_cbo_cus_name.getValue()==null  ){
            controller.getMessage().shortToast("Please Provide a Name of the Customer", 2000);
            
        }
             else   if(customer_cbo_cus_name.getValue()==null  || traps.isNumerical(customer_cbo_cus_name.getValue().toString()) ){
                   controller.getMessage().shortToast("Please Provide a  Customer", 2000);
            
        }
              else{
            try {
                 DataCustomer dataCustomerselect = (DataCustomer)customer_tableview.getSelectionModel().getSelectedItem();
                 DatabaseConnection dbupdate = new DatabaseConnection("root", "localhost", "eyefleet", "ramjorgeyao");
                dbupdate.updateDatabase("UPDATE eyefleet.customer SET name ='"+customer_cbo_cus_name.getValue()+"' "
                        + " WHERE  customer_id ='" + dataCustomerselect.getCustomer_id()+"'" );
              
                 //clear data 
                      dataCustomers.clear();
                //refresh table
                                 setitemsCustomer();

            } catch (Exception e) {
              }
              }//end of if;
        });
     }
    //refresh and clear area
    public void btnCustomerRefresh(){
        customer_btn_refresh.setOnMouseClicked((MouseEvent)->{
            
            //clear data 
            dataCustomers.clear();
              //clear items from cbo
            customer_cbo_cus_name.setValue(null);
             //refresh tale customer
             setitemsCustomer();
             //refresh cbo items
             setCbocustomer();
             //product fields
             dataProducts.clear();
             product_cbo_product_id.setValue(null);
             cboProduct();
             product_txt_description.clear();
             setItemsProducts();
             
             
        });
       
        
    }
    
    
    public void initializedProduct(){
        setTableProduct();
        setItemsProducts();
        clicktblProduct();
        cboProduct();
        cboproductdescription();
        btnProductAdd();
        btnProductUpdate();
    }
    
    public void setTableProduct(){
        dataProducts = FXCollections.observableArrayList();
        product_tblc_product_id.setCellValueFactory(new PropertyValueFactory("product_id"));
        product_tblc_description.setCellValueFactory(new PropertyValueFactory("description"));
        product_tableview.setItems(dataProducts);
         
    }
    
    //ietms of product
    public void setItemsProducts(){
        DatabaseConnection  dbpoduct = new DatabaseConnection("root", "localhost", "eyefleet", "ramjorgeyao");
        ResultSet rsproduct = dbpoduct.setQuery("SELECT * FROM eyefleet.product");
        try {
            while(rsproduct.next()){
                dataProducts.add(new DataProduct(rsproduct.getString("product_id"),
                        rsproduct.getString("description")));
                
            }
        } catch (Exception e) {
               System.out.print("Error on MC_CustomerandProduct setItemsProducts " + e);
                 System.exit(0);
           
        }
    }
    
    public void clicktblProduct(){
      product_tableview.setOnMouseClicked((MouseEvent)->{
         DataProduct dataProductselect =(DataProduct)product_tableview.getSelectionModel().getSelectedItem();
        if(dataProductselect != null){
            product_cbo_product_id.setValue(dataProductselect.getProduct_id());
            product_txt_description.setText(dataProductselect.getDescription());
         }
      });
    }
    
    
    
    
    //items of combobox
    public void cboProduct(){
            //clear cbo product
                product_cbo_product_id.getItems().clear();
                        
                    DatabaseConnection  dbcbopoduct = new DatabaseConnection("root", "localhost", "eyefleet", "ramjorgeyao");
                    ResultSet rscboproducr = dbcbopoduct.setQuery("SELECT product_id FROM eyefleet.product");
                    try {
                        while(rscboproducr.next()){
                            product_cbo_product_id.getItems().add(rscboproducr.getString("product_id"));
                            
                        }
                     
        } catch (Exception e) {
            System.out.print("Error on MC_CustomerandProduct cboProduct " + e);
                 System.exit(0);
        }
                    
    }
    
    public void cboproductdescription(){
         //display the descripion of the selected items;
       

                    product_cbo_product_id.setOnAction((ActionEvent)->{
                              try {
                                 DatabaseConnection  dbcbodescription= new DatabaseConnection("root", "localhost", "eyefleet", "ramjorgeyao");
                                   ResultSet rscbodescription = dbcbodescription.setQuery("SELECT description FROM eyefleet.product "
                                    + " WHERE product_id ='" +product_cbo_product_id.getValue() + "'");
                                   
                                 while(rscbodescription.next()){
                                     product_txt_description.setText(rscbodescription.getString("description"));
                                 }
                                 
                        } catch (Exception e) {
                               System.out.print("Error on MC_CustomerandProduct cboProduct @description " + e);
                               System.exit(0);
                         }
                     });
    }
    
    //add new records 
    public void btnProductAdd(){
                  product_btn_add.setOnMouseClicked((MouseEvent)->{
                     //check if the truck id is exist;        
                    boolean exist = false;
                    DatabaseConnection dbcbo = new DatabaseConnection("root", "localhost", "eyefleet", "ramjorgeyao");
                    ResultSet rscbo = dbcbo.setQuery("SELECT * FROM eyefleet.product;");
                 try {
                         while(rscbo.next()){
                              if( product_cbo_product_id.getValue().equals(rscbo.getString("product_id")))  {
                                exist = true;
                             break;
                     }
                        }
                  } catch (Exception e) {
             }
             
             
              if(exist ==true){
                              controller.getMessage().shortToast( "Product Id  already Exist::", 2000);

            }//end of error trps;
             
                      
              else if(product_cbo_product_id.getValue()==null){
                                     controller.getMessage().shortToast("Please Provide a Product Id of the Product", 2000);
                                 }
              else if (product_cbo_product_id.getValue().equals( " ")){
                                                       controller.getMessage().shortToast("Please Provide a Product Id of the Product", 2000);

                  
              }
                                else if(product_txt_description.getText().isEmpty() || traps.isNumerical(product_txt_description.getText())){
                                     controller.getMessage().shortToast("Please Provide a Correct description of the Product", 2000);
                                }
                                
                                else{
                        try {
                            DatabaseConnection  dbproductadd= new DatabaseConnection("root", "localhost", "eyefleet", "ramjorgeyao");
                             dbproductadd.updateDatabase("INSERT INTO eyefleet.product(product_id, description)"
                                                                                   + "  VALUES('"+ product_cbo_product_id.getValue()+"', "
                                                                                    + "'" + product_txt_description.getText().toString()+"')");
                             //data clear
                             dataProducts.clear();
                             //refresh table product
                             setItemsProducts();;
                             //refresh cbo product
                             cboProduct();
                             
                             
                         } catch (Exception e) {
                               System.out.print("Error on MC_CustomerandProduct cbtnProductAdd " + e);
                  }
                                }//end of if;
                            
              });
                            
    }
    
    public void btnProductUpdate(){
            product_btn_update.setOnMouseClicked((MouseEvent)->{
                  
                 if(product_cbo_product_id.getValue()==null){
                                     controller.getMessage().shortToast("Please Provide a Product Id of the Product", 2000);
                                 }
                   else if (product_cbo_product_id.getValue().equals(" ")){
                                                       controller.getMessage().shortToast("Please Provide a Product Id of the Product", 2000);
                }
                                else if(product_txt_description.getText().isEmpty() || traps.isNumerical(product_txt_description.getText())){
                                     controller.getMessage().shortToast("Please Provide a Correct description of the Product", 2000);
                                }
                                
                                
                                else{
                try {
                            DataProduct dataProductelect = (DataProduct)product_tableview.getSelectionModel().getSelectedItem();
                            DatabaseConnection dbupdateproduct = new DatabaseConnection("root", "localhost", "eyefleet", "ramjorgeyao");
                            dbupdateproduct.updateDatabase("UPDATE eyefleet.product SET description ='" + product_txt_description.getText()+"' "
                                                                                                                           + "  WHERE product_id  ='"+  dataProductelect.getProduct_id()+"'" );
                                   //clear data
                                   dataProducts.clear();
                                   
                                    //refresh table;
                                   setItemsProducts();;
                                   
                
                
                } catch (Exception e) {
                  }
                
                                }//end of if
            
            });
        
    }
    
    
    
    
    
 
    public class DataCustomer {

        String customer_id;
        String name;

        public DataCustomer(String customer_id, String name) {
            this.customer_id = customer_id;
            this.name = name;
        }

        public String getCustomer_id() {
            return customer_id;
        }

        public void setCustomer_id(String customer_id) {
            this.customer_id = customer_id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

    }

    
    
    public class DataProduct {

        private String product_id;
        private String description;

        public DataProduct(String product_id, String description) {
            this.product_id = product_id;
            this.description = description;
        }

        public String getProduct_id() {
            return product_id;
        }

        public void setProduct_id(String product_id) {
            this.product_id = product_id;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

    }

}
