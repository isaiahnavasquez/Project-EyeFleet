/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Orders;

import eyefleet.DatabaseConnection;
import eyefleet.LayoutController;
import java.io.File;
import java.sql.ResultSet;
import java.util.ArrayList;
import javafx.embed.swing.SwingFXUtils;
import javafx.print.PageLayout;
import javafx.print.Printer;
import javafx.print.PrinterJob;
import javafx.scene.SnapshotParameters;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javax.imageio.ImageIO;

/**
 *
 * @author IsaiahJan
 */
public class OrderFrequency {

    //controller
    LayoutController controller;
    
    //date pickers
    DatePicker dtp_from;
    DatePicker dtp_until;
    
    //chart
    LineChart<String, Number> chart;
    
    //Panel
    Pane chart_pane;
    
    //button
    Label btn_print;
    
    //button
    Label btn_filter;
    
    public OrderFrequency(LayoutController controller) {
        //controller
        this.controller = controller;
        
        //chart
        chart = controller.getFrequency_chart();
        dtp_from = controller.getFrequency_dtp_from();
        dtp_until = controller.getFrequency_dtp_until();
        
        //button
        btn_filter = controller.getFrequency_btn_filter();
        chart_pane = controller.getFrequency_pnl_chart();
        btn_print = controller.getFrequency_btn_print();
        
        //initialize Module
        setButtonFunctionFilter();
        setChartFunction();
        setButtonFunctionPrint();
    }
    
    public void setChartFunction(){
        chart.setOnMouseClicked((MouseEvent) -> {
            FileChooser chooser = new FileChooser();
            WritableImage image = chart_pane.snapshot(new SnapshotParameters(), null);
            File file = chooser.showSaveDialog(chart_pane.getParent().getScene().getWindow());
            
            if(file != null){
                try {
                    ImageIO.write(SwingFXUtils.fromFXImage(image, null), "png", file);
                } catch (Exception e) {
                    System.out.println("Error saving " + e);
                }    
            } 
        });
    }
    
    public void setButtonFunctionPrint(){
        btn_print.setOnMouseClicked((MouseEvent) -> {
            WritableImage image_write = chart_pane.snapshot(new SnapshotParameters(), null);
            File file = new File("temp.png");
            try {
                    ImageIO.write(SwingFXUtils.fromFXImage(image_write, null), "png", file);
                } catch (Exception e) {
                    System.out.println("Error saving " + e);
                }
            
            ImageView view = new ImageView(new Image("file:temp.png"));
            
            
            VBox pane_temp = new VBox();
            Label label = new Label("Product Frequency Report");
            label.setStyle("-fx-font-family: \"Segoe UI Light\"; -fx-font-size: 40px");
            
            Printer printer;
            PrinterJob job = PrinterJob.createPrinterJob();
            job.showPrintDialog(btn_filter.getParent().getScene().getWindow());
            printer = job.getPrinter();
            job.setPrinter(printer);
            job.showPageSetupDialog(btn_filter.getParent().getScene().getWindow());
            PageLayout layout = job.getJobSettings().getPageLayout();
            
            view.setFitWidth(layout.getPrintableWidth());
            view.setFitHeight(layout.getPrintableHeight()/2);
            pane_temp.setPrefHeight(layout.getPrintableHeight());
            pane_temp.setPrefWidth(layout.getPrintableWidth());
            pane_temp.setSpacing(30);
            pane_temp.getChildren().addAll(label, view);
            
            if(job != null){
                if(job.printPage(layout, pane_temp)){
                    job.endJob();
                }
            }
            
            job.endJob();
        });
    }
    
    public void setButtonFunctionFilter(){
        btn_filter.setOnMouseClicked((MouseEvent) -> {
            ArrayList<String> day_category = new ArrayList<>();
            //clear chart
            chart.getData().clear();
            
            //prepare the variables
            int from = 0;
            int until = 0;
            
            //do the charting
            if(dtp_until.getValue() != null){
                //get the counter values from the date
                from = dtp_from.getValue().getDayOfMonth();
                until = dtp_until.getValue().getDayOfMonth();
                
                //set the category by date
                for(int counter = from; counter <= until; counter++){
                    //instantiate the category
                    day_category.add("Day " + counter);
                }
                
                //get all the product list
                DatabaseConnection db_product = new DatabaseConnection("root", "localhost", "eyefleet", "isaiah");
                ResultSet rs_product = db_product.setQuery("select distinct product_id from product");
                
                try {
                    while(rs_product.next()){
                        //create a serie
                        XYChart.Series<String, Number> serie = new XYChart.Series<>();
                        serie.setName(rs_product.getString("product_id"));
                        
                        //index for category
                        int category_index = 0;
                        
                        //get the from until count
                        for(int counter = from; counter <= until; counter++){
                            //reference the query per product
                            DatabaseConnection db_qty = new DatabaseConnection("root", "localhost", "eyefleet", "isaiah");
                            ResultSet rs_qty = db_qty.setQuery("select product_id, sum(quantity) as qty " +
                                                        "from eyefleet.order " +
                                                        "where product_id = '" + rs_product.getString("product_id") + 
                                                        "' and date_ordered = '" + dtp_from.getValue().getYear() + 
                                                        "-" + dtp_from.getValue().getMonthValue() + 
                                                        "-" + counter + "' " +
                                                        "group by product_id");
                            
                            //adds the data collected to the serie
                            while(rs_qty.next()){
                                serie.getData().add(new XYChart.Data<String, Number>(day_category.toArray()[category_index].toString(), ((Integer)(rs_qty.getInt("qty")))));
                            }
                            
                            //proceeds to the next category
                            category_index++;
                        }
                        
                        //adds the created serie
                        chart.getData().add(serie);
                    }
                } catch (Exception e) {
                    System.out.println("error in fetching order frequency report data " + e);
                }
                
            }
        });
    }
    
}
