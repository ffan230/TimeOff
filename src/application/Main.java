package application;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			// Read file fxml and draw interface.
			Parent root = FXMLLoader.load(getClass().getResource("/application/JieMian.fxml"));

			primaryStage.setTitle("定时关机");
			primaryStage.setScene(new Scene(root));
			primaryStage.show();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

//	@Override
//	  public void start(Stage stage) {
//	    Scene scene = new Scene(new Group(), 327, 303);
//	    
//	    TabPane tp = new TabPane();
//	    Tab tab1 = new Tab();
//	    
//	    HBox hb1 = new HBox();
//	    
//	    Label label1 = new Label("时");
//	    label1.setLayoutX(100);
//	    label1.setLayoutY(81);
//	    
//	    Label label2 = new Label("分");
//	    
//	    Label label3 = new Label("秒");	
//	    
//	    TextField XC_shi = new TextField ("0");
//	    XC_shi.setPrefWidth(53);
//	    XC_shi.setPrefHeight(36);
//	    
//	    TextField XC_fen = new TextField ("0");
//	    XC_fen.setPrefWidth(53);
//	    XC_fen.setPrefHeight(36);
//	    
//	    TextField XC_miao = new TextField ("0");
//	    XC_miao.setPrefWidth(53);
//	    XC_miao.setPrefHeight(36);
//	    
//	    hb1.getChildren().addAll(label1, XC_shi,label2, XC_fen,label3, XC_miao);
//
//	    HBox hb2 = new HBox();
//	    
//	    Label label4 = new Label(":");
//	    
//	    TextField JT_shi = new TextField ("0");
//	    JT_shi.setPrefWidth(53);
//	    JT_shi.setPrefHeight(36);
//	    
//	    TextField JT_fen = new TextField ("0");
//	    JT_fen.setPrefWidth(53);
//	    JT_fen.setPrefHeight(36);
//	    
//	    hb2.getChildren().addAll(JT_shi, label4,JT_fen);
//	    
//	    Group root = (Group) scene.getRoot();
//	    tp.getTabs().add(tab1);
////	    root.getChildren().add(tp);
//	    root.getChildren().add(hb2);
//	    stage.setTitle("定时关机");
//	    stage.setScene(scene);
//	    stage.show();
//	  }
	
	public static void main(String[] args) {
		launch(args);
	}
}