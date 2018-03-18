/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package eyefleet;

import javafx.animation.Animation;
import javafx.animation.AnimationTimer;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;

/**
 *
 * @author IsaiahJan
 */
public class Messages {

    LayoutController controller;
    AnchorPane pane;
    Label message;
    int second = 3;
    AnimationTimer timer;
    
    public Messages(LayoutController controller) {
        //load the controller
        this.controller = controller;
        
        //load the components
        pane = controller.getPnl_toast();
        message = controller.getToast_message();
    }
    
    public void shortToast(String message, int second){
        controller.getPnl_toast().setVisible(true);
        controller.getToast_message().setText(message);
        
        Timeline timeline = new Timeline(new KeyFrame(new Duration(second), new KeyValue(controller.getPnl_toast().opacityProperty(), 1)));
        timeline.play();
        timeline.setOnFinished((ActionEvent) -> {
            Timeline timeline_temp = new Timeline(new KeyFrame(new Duration(1000), new KeyValue(controller.getPnl_toast().opacityProperty(), 0)));
            timeline_temp.play();
            timeline_temp.setOnFinished(new EventHandler<ActionEvent>() {

                public void handle(ActionEvent ActionEvent) {
                    controller.getPnl_toast().setVisible(false);
                    controller.getPnl_toast().setOpacity(1);
                }
            });
            
        });
        
    }
 
    public void message(String title, String content, String buttonConfirmationText){
    
        controller.getPnl_cloak().setVisible(true);
        controller.getPnl_message().setVisible(true);
        controller.getMessage_title().setText(title);
        controller.getMessage_content().setText(content);
        controller.getMessage_btn_okay().setText(buttonConfirmationText);
        
        controller.getMessage_btn_okay().setOnMouseClicked((MouseEvent) -> {
            controller.getPnl_cloak().setVisible(false);
            controller.getPnl_message().setVisible(false);
        });
    
    }
    
}
