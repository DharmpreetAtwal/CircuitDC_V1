package comps;

import application.CircuitComponent;
import application.ConnectionNode;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;

public class Wire extends CircuitComponent {

	public Wire() {
		super(200,20);
		this.image.setFill(Color.SADDLEBROWN);
		
		initializeRotationEvents(this.node1);
		initializeRotationEvents(this.node2);
	}

	public void initializeRotationEvents(ConnectionNode node) {
		// Responsible for both repositioning nodes, and image manipulation
		node.getNode().setOnMouseDragged((MouseEvent e)-> {
			// Node position = position of mouse
			node.setPosX(e.getSceneX());
			node.setPosY(e.getSceneY());
			
			// Updating the image to match the new coordinates of the nodes
			updateImage(this.node1, this.node2);
		});
	}
	
	public void updateImage(ConnectionNode node1, ConnectionNode node2) {
		// Calculates the rotation of the image based on the positions of the nodes 
		double xComp = this.node1.getPosX() - this.node2.getPosX();
		double yComp = this.node1.getPosY() - this.node2.getPosY(); 
		double angleRad = Math.atan(yComp / xComp);
		
		// Finds the point in between the nodes
		double centerXAvg = (this.node1.getPosX() + this.node2.getPosX())/2;
		double centerYAvg = (this.node1.getPosY() + this.node2.getPosY())/2;
		
		// Using the x and y diff between the nodes to find the magnitude
		double width = Math.sqrt(Math.pow(xComp, 2) + Math.pow(yComp, 2));
		this.image.setWidth(width);
		this.image.setRotate(Math.toDegrees(angleRad));
		
		// Positioning the image in between the two nodes
		this.image.setLayoutX(centerXAvg - (this.image.getWidth()/2));
		this.image.setLayoutY(centerYAvg - (this.image.getHeight()/2));
	}
	
	
	public void updateImage(double xComp, double yComp) {
		
	}
	
}
