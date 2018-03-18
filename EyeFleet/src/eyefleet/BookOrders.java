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
public class BookOrders {
    
    LayoutController controller;

    public BookOrders(LayoutController controller) {
        this.controller = controller;
        setMenuFunctions();
    }
 
    public void setMenuFunctions(){
        this.controller.getOrders_menu_lbl_order_stack().setOnMouseClicked((MouseEvent) -> {
            openOrderStack();
        });
        this.controller.getOrders_menu_lbl_delivered().setOnMouseClicked((MouseEvent) -> {
            openDeliveredOrders();
        });
        this.controller.getOrders_menu_lbl_manual().setOnMouseClicked((MouseEvent) -> {
            openManualAssignment();
        });
        this.controller.getOrders_menu_lbl_on_delivery().setOnMouseClicked((MouseEvent) -> {
            openOnDelivery();
        });
        this.controller.getOrders_menu_lbl_deployment().setOnMouseClicked((MouseEvent) -> {
            openDeployment();
        });
        this.controller.getOrders_menu_lbl_failed_orders().setOnMouseClicked((MouseEvent) -> {
            openFailedOrders();
        });
        this.controller.getOrders_menu_lbl_frequency().setOnMouseClicked((MouseEvent) -> {
            openFrequency();
        });
    }
    
    public void closeAllPageInBookOrders(){
        controller.getOrders_pnl_delivered().setVisible(false);
        controller.getOrders_pnl_manual().setVisible(false);
        controller.getOrders_pnl_on_delivery().setVisible(false);
        controller.getOrders_pnl_order_stack().setVisible(false);
        controller.getOrders_pnl_deployment().setVisible(false);
        controller.getOrders_pnl_failed_orders().setVisible(false);
        controller.getOrders_pnl_frequency().setVisible(false);
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
    
    public void openBookOrders(){
        controller.closeBookMain();
        controller.getBookMaintenance().closeBookMaintenance();
        //controller.getBookMasterControl().closeBookMasterControl();
        controller.getPnl_orders().setVisible(true);
    }
    
    public void closeBookOrders(){
        controller.getPnl_orders().setVisible(false);
        closeAllPageInBookOrders();
    }
    
    public void setSelectedMenu(Label selectedMenu){
        controller.getOrders_menu_lbl_delivered().setEffect(null);
        controller.getOrders_menu_lbl_manual().setEffect(null);
        controller.getOrders_menu_lbl_on_delivery().setEffect(null);
        controller.getOrders_menu_lbl_order_stack().setEffect(null);
        controller.getOrders_menu_lbl_deployment().setEffect(null);
        controller.getOrders_menu_lbl_failed_orders().setEffect(null);
        controller.getOrders_menu_lbl_frequency().setEffect(null);
        selectedMenu.setEffect(new Glow(20));
    }
    
    public void openOrderStack(){
        if(!controller.getOrders_pnl_order_stack().isVisible()){
            closeAllPageInBookOrders();
            openPage(controller.getOrders_pnl_order_stack());
                setSelectedMenu(this.controller.getOrders_menu_lbl_order_stack());
            }
    }
    
    public void openDeliveredOrders(){
         if(!controller.getOrders_pnl_delivered().isVisible()){
            closeAllPageInBookOrders();
            openPage(controller.getOrders_pnl_delivered());
                setSelectedMenu(this.controller.getOrders_menu_lbl_delivered());
            }
    }
    
    public void openManualAssignment(){
        if(!controller.getOrders_pnl_manual().isVisible()){
            closeAllPageInBookOrders();
            openPage(controller.getOrders_pnl_manual());
                setSelectedMenu(this.controller.getOrders_menu_lbl_manual());
            }
    }
    
    public void openOnDelivery(){
        if(!controller.getOrders_pnl_on_delivery().isVisible()){
            closeAllPageInBookOrders();
            openPage(controller.getOrders_pnl_on_delivery());
                setSelectedMenu(this.controller.getOrders_menu_lbl_on_delivery());
            }
    }
    
    public void openDeployment(){
        if(!controller.getOrders_pnl_deployment().isVisible()){
            closeAllPageInBookOrders();
            openPage(controller.getOrders_pnl_deployment());
                setSelectedMenu(this.controller.getOrders_menu_lbl_deployment());
            }
    }
    
    public void openFailedOrders(){
        if(!controller.getOrders_pnl_failed_orders().isVisible()){
            closeAllPageInBookOrders();
            openPage(controller.getOrders_pnl_failed_orders());
                setSelectedMenu(this.controller.getOrders_menu_lbl_failed_orders());
            }
    }
    
    public void openFrequency(){
        if(!controller.getOrders_pnl_frequency().isVisible()){
            closeAllPageInBookOrders();
            openPage(controller.getOrders_pnl_frequency());
                setSelectedMenu(this.controller.getOrders_menu_lbl_frequency());
            }
    }
    
}
