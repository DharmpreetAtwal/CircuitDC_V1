package application;
	
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Rectangle;
import comps.*;

public class Main extends Application {
	
	public void start(Stage primaryStage) {
		BorderPane root = new BorderPane();
		Scene scene = new Scene(root,400,400);
		scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		
		Circuit defaultCircuit = new Circuit();
		
		Rectangle wireBox = new Rectangle(50,50);
		wireBox.setFill(Color.SADDLEBROWN);
		wireBox.setOnMouseClicked((MouseEvent e)->{
			Wire wire = new Wire();
			wire.setPos(100, 100);
			
			defaultCircuit.getComponentList().add(wire);
			root.getChildren().add(wire.getContainer());
			defaultCircuit.initializeConnectionEvent();
		});
		
		Rectangle batteryBox = new Rectangle(50,50);
		batteryBox.setFill(Color.ORANGE);
		batteryBox.setOnMouseClicked((MouseEvent e)->{
			Battery battery = new Battery();
			battery.setPos(100, 100);
			
			defaultCircuit.getComponentList().add(battery);
			root.getChildren().add(battery.getContainer());
			defaultCircuit.initializeConnectionEvent();
		});

		
		Rectangle lightBulbBox = new Rectangle(50,50);
		lightBulbBox.setFill(Color.YELLOW);
		
		lightBulbBox.setOnMouseClicked((MouseEvent e)->{
			LightBulb lightBulb = new LightBulb();
			lightBulb.setPos(100, 100);
			
			defaultCircuit.getComponentList().add(lightBulb);
			root.getChildren().add(lightBulb.getContainer());
			defaultCircuit.initializeConnectionEvent();
		});
		
		VBox selectionMenu = new VBox();
		selectionMenu.getChildren().addAll(wireBox, batteryBox, lightBulbBox);
		
		root.getChildren().add(selectionMenu);
	
		/*
		Wire wire1 = new Wire();
		Wire wire2 = new Wire();
		Wire wire3 = new Wire();
		Wire wire4 = new Wire();
				
		Battery battery1 = new Battery();

		LightBulb lightBulb1 = new LightBulb();
		
		Circuit defaultCircuit = new Circuit(wire1, wire2, wire3, wire4, battery1, lightBulb1);
		root.getChildren().addAll(wire1.getContainer(), battery1.getContainer(), lightBulb1.getContainer());
		root.getChildren().addAll(wire2.getContainer(), wire3.getContainer(), wire4.getContainer());
		*/
		primaryStage.setScene(scene);
		primaryStage.show();
		
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}

