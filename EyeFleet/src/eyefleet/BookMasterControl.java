/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package eyefleet;

import javafx.animation.FadeTransition;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.scene.control.Label;
import javafx.scene.effect.Glow;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;

/**
 *
 * @author IsaiahJan
 */
public class BookMasterControl {

    LayoutController controller;
    
    public BookMasterControl(LayoutController controller) {
        this.controller = controller;
        setMenuFunctions();
    }

    public void setMenuFunctions(){
        controller.getMc_menu_lbl_property_Equipment_Issunce().setOnMouseClicked((MouseEvent) -> {
            System.out.println("LOL");
            openIssuance();
        });
        controller.getMc_menu_lbl_property_Equipment().setOnMouseClicked((MouseEvent) -> {
            openPPE();
        });
        controller.getMc_menu_lbl_repairservices().setOnMouseClicked((MouseEvent) -> {
            openRepairServices();
        });
        controller.getMc_menu_lbl_replacements().setOnMouseClicked((MouseEvent) -> {
            openReplacements();
        });
        controller.getMc_menu_lbl_truckparts().setOnMouseClicked((MouseEvent) -> {
            openTruckParts();
        });
        controller.getMc_menu_lbl_truck().setOnMouseClicked((MouseEvent) -> {
            openTruckStatus();
        });
         
        controller.getMc_menu_lbl_customer_and_product().setOnMouseClicked((MouseEvent)->{
         openCustomerandProduct();
        });
        controller.getMc_menu_lbl_supplier().setOnMouseClicked((MouseEvent)->{
        openSupplier();
        });
        controller.getMc_menu_lbl_driver().setOnMouseClicked((MouseEvent)->{
        openDriver();
        
        });
    }
 
    public void closeBookMasterControl(){
        controller.getPnl_master_control().setVisible(false);
        closeAllPageInBookMasterControl();
    }
    
    public void closeAllPageInBookMasterControl(){
        controller.getMc_pnl_issuance().setVisible(false);
        controller.getMc_pnl_protective_equipment().setVisible(false);
        controller.getMc_pnl_repair_services().setVisible(false);
        controller.getMc_pnl_replacements().setVisible(false);
        controller.getMc_pnl_truck_parts().setVisible(false);
        controller.getMc_pnl_truck_status().setVisible(false);
        //--customer and producr
        controller.getPnl_customer_and_product().setVisible(false);
        //supplier
        controller.getPnl_supplier().setVisible(false);
        //driver
        controller.getPnl_driver().setVisible(false);
    }
    
    public void openPage(AnchorPane page){
        page.setTranslateX(80);
        page.setVisible(true);
        Timeline timeline = new Timeline();
        timeline.getKeyFrames().add(new KeyFrame(new Duration(250), new KeyValue(page.translateXProperty(), 0)));
        FadeTransition fade = new FadeTransition(new Duration(250), page);
        fade.setFromValue(0);
        fade.setToValue(1);
        timeline.play();
        fade.play();
    }
    
    public void openBookMasterControl(){
        controller.closeBookMain();
        controller.getBookMaintenance().closeBookMaintenance();
        controller.getBookOrders().closeBookOrders();
        controller.getPnl_master_control().setVisible(true);
    }
    
    public void setSelectedMenu(Label selectedMenu){
        controller.getMc_menu_lbl_iquipment().setEffect(null);
        controller.getMc_menu_lbl_property_Equipment().setEffect(null);
        controller.getMc_menu_lbl_property_Equipment_Issunce().setEffect(null);
        controller.getMc_menu_lbl_repairservices().setEffect(null);
        controller.getMc_menu_lbl_replacements().setEffect(null);
        controller.getMc_menu_lbl_truck().setEffect(null);
        controller.getMc_menu_lbl_truckparts().setEffect(null);
        //----customer and product
        controller.getMc_menu_lbl_customer_and_product().setEffect(null);
        //supplier
        controller.getMc_menu_lbl_supplier().setEffect(null);
        //driver
        controller.getMc_menu_lbl_driver().setEffect(null);
        selectedMenu.setEffect(new Glow(20));
    }
    
    public void openIssuance(){
        if(!controller.getMc_pnl_issuance().isVisible()){
            closeAllPageInBookMasterControl();
            openPage(controller.getMc_pnl_issuance());
                setSelectedMenu(this.controller.getMc_menu_lbl_property_Equipment_Issunce());
            }
    }
    
    public void openPPE(){
        if(!controller.getMc_pnl_protective_equipment().isVisible()){
            closeAllPageInBookMasterControl();
            openPage(controller.getMc_pnl_protective_equipment());
                setSelectedMenu(this.controller.getMc_menu_lbl_property_Equipment());
            }
    }
    
    public void openRepairServices(){
        if(!controller.getMc_pnl_repair_services().isVisible()){
            closeAllPageInBookMasterControl();
            openPage(controller.getMc_pnl_repair_services());
                setSelectedMenu(this.controller.getMc_menu_lbl_repairservices());
            }
    }
    
    public void openReplacements(){
        if(!controller.getMc_pnl_replacements().isVisible()){
            closeAllPageInBookMasterControl();
            openPage(controller.getMc_pnl_replacements());
                setSelectedMenu(this.controller.getMc_menu_lbl_replacements());
            }
    }
    
    public void openTruckParts(){
        if(!controller.getMc_pnl_truck_parts().isVisible()){
            closeAllPageInBookMasterControl();
            openPage(controller.getMc_pnl_truck_parts());
                setSelectedMenu(this.controller.getMc_menu_lbl_truckparts());
            }
    }
    
    public void openTruckStatus(){
        if(!controller.getMc_pnl_truck_status().isVisible()){
            closeAllPageInBookMasterControl();
            openPage(controller.getMc_pnl_truck_status());
                setSelectedMenu(this.controller.getMc_menu_lbl_truck());
            }
    }
    
    
    //-----------------Customer and Product
    
    public void openCustomerandProduct(){
        if(!controller.getPnl_customer_and_product().isVisible()){
            closeAllPageInBookMasterControl();
            openPage(controller.getPnl_customer_and_product());
            setSelectedMenu(this.controller.getMc_menu_lbl_customer_and_product());
            
        }
            
     }
    
    //supplier
    public void openSupplier(){
        if(!controller.getPnl_supplier().isVisible()){
                    closeAllPageInBookMasterControl();
                    openPage(controller.getPnl_supplier());
                    setSelectedMenu(this.controller.getMc_menu_lbl_supplier());
         }
     }
    
    //driver
    public void openDriver(){
        if(!controller.getPnl_driver().isVisible()){
                    closeAllPageInBookMasterControl();
                    openPage(controller.getPnl_driver());
                    setSelectedMenu(this.controller.getMc_menu_lbl_driver());
        }
    }
}
