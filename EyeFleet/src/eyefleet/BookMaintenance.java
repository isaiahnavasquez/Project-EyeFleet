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
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;

/**
 *
 * @author IsaiahJan
 */
public class BookMaintenance {

    LayoutController controller;
    
    public BookMaintenance(LayoutController controller) {
        this.controller = controller;
        setMenuFunctions();
    }
    
    public void setMenuFunctions(){
        this.controller.getMaintenance_lbl_equipments().setOnMouseClicked((MouseEvent) -> {
           openEquipments();
        });
        this.controller.getMaintenance_lbl_issuance().setOnMouseClicked((MouseEvent) -> {
            openIssuance();
        });
        this.controller.getMaintenance_lbl_repair_services().setOnMouseClicked((MouseEvent) -> {
           openRepairServices();
        });
        this.controller.getMaintenance_lbl_replacements().setOnMouseClicked((MouseEvent) -> {
            openReplacements();
        });
        this.controller.getMaintenance_lbl_status().setOnMouseClicked((MouseEvent) -> {
            openStatus();
        });
        this.controller.getMaintenance_lbl_truck_parts().setOnMouseClicked((MouseEvent) -> {
            openTruckParts();
        });
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
    
    public void closeAllPageInBookMaintenance(){
        controller.getMaintenance_pnl_equipments().setVisible(false);
        controller.getMaintenance_pnl_issuance().setVisible(false);
        controller.getMaintenance_pnl_repair_services().setVisible(false);
        controller.getMaintenance_pnl_replacements().setVisible(false);
        controller.getMaintenance_pnl_status().setVisible(false);
        controller.getMaintenance_pnl_truck_parts().setVisible(false);
    }
    
    public void openBookMaintenance(){
        controller.closeBookMain();
        controller.getBookOrders().closeBookOrders();
        controller.getBookMasterControl().closeBookMasterControl();
        controller.getPnl_maintenance().setVisible(true);
    }
    
    public void closeBookMaintenance(){
        controller.getPnl_maintenance().setVisible(false);
        closeAllPageInBookMaintenance();
    }
    
    public void setSelectedMenu(Label selectedMenu){
        controller.getMaintenance_lbl_equipments().setEffect(null);
        controller.getMaintenance_lbl_issuance().setEffect(null);
        controller.getMaintenance_lbl_repair_services().setEffect(null);
        controller.getMaintenance_lbl_replacements().setEffect(null);
        controller.getMaintenance_lbl_status().setEffect(null);
        controller.getMaintenance_lbl_truck_parts().setEffect(null);
        selectedMenu.setEffect(new Glow(20));
    }
    
    public void openEquipments(){
         if (!this.controller.getMaintenance_pnl_equipments().isVisible()){
                closeAllPageInBookMaintenance();
                openPage(this.controller.getMaintenance_pnl_equipments());
                setSelectedMenu(this.controller.getMaintenance_lbl_equipments());
            }
    }
    
    public void openIssuance(){
         if (!this.controller.getMaintenance_pnl_issuance().isVisible()){
                closeAllPageInBookMaintenance();
                openPage(this.controller.getMaintenance_pnl_issuance());
                setSelectedMenu(this.controller.getMaintenance_lbl_issuance());
            }
    }
    
    public void openRepairServices(){
        if (!this.controller.getMaintenance_pnl_repair_services().isVisible()){
                closeAllPageInBookMaintenance();
                openPage(this.controller.getMaintenance_pnl_repair_services());
                setSelectedMenu(this.controller.getMaintenance_lbl_repair_services());
            }
    }
    
    public void openReplacements(){
        if (!this.controller.getMaintenance_pnl_replacements().isVisible()){
                closeAllPageInBookMaintenance();
                openPage(this.controller.getMaintenance_pnl_replacements());
                setSelectedMenu(this.controller.getMaintenance_lbl_replacements());
            }
    }
    
    public void openStatus(){
        if (!this.controller.getMaintenance_pnl_status().isVisible()){
                closeAllPageInBookMaintenance();
                openPage(this.controller.getMaintenance_pnl_status());
                setSelectedMenu(this.controller.getMaintenance_lbl_status());
            }
    }
    
    public void openTruckParts(){
        if (!this.controller.getMaintenance_pnl_truck_parts().isVisible()){
                closeAllPageInBookMaintenance();
                openPage(this.controller.getMaintenance_pnl_truck_parts());
                setSelectedMenu(this.controller.getMaintenance_lbl_truck_parts());
            }
    }
}
