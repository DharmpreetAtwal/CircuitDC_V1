package application;


import java.util.Map;
import java.util.HashMap;
import javafx.scene.Group;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.transform.Rotate;


public abstract class CircuitComponent {
	protected Group container = new Group();
	protected Rectangle image = new Rectangle();
	protected ConnectionNode node1 = new ConnectionNode();
	protected ConnectionNode node2 = new ConnectionNode();
	protected double fixedX;
	protected double fixedY;
	protected double mouseXDiff;
	protected double mouseYDiff;
	
	public CircuitComponent(int fixedWidth, int fixedHeight) {
		this.container.getChildren().addAll(image, node1.getNode(), node2.getNode());
		
		this.image.setWidth(fixedWidth);
		this.image.setHeight(fixedHeight);
		
		updateNodePos();
		initializeMovementEvents();
		initializeRotationEvents(this.node1, 1);
		initializeRotationEvents(this.node2, -1);
	}

	protected void updateNodePos() {
		// Responsible for repositioning the nodes using the angle of the image
		double centerX = this.image.getLayoutX() + (this.image.getWidth()/2);
		double centerY = this.image.getLayoutY() + (this.image.getHeight()/2);
		double angleDeg = this.image.getRotate();
		double radius = this.image.getWidth()/2;
		
		// Creates a circle of rotation around an object
		// It's radius would be the width
		double xComp1 = radius * Math.cos(Math.toRadians(angleDeg));
		double yComp1 = radius * Math.sin(Math.toRadians(angleDeg));
		this.node1.setPosX(centerX + xComp1);
		this.node1.setPosY(centerY + yComp1);
		
		// 2nd node would move oppositely to the 1st
		double xComp2 = (-1) * radius * Math.cos(Math.toRadians(angleDeg));
		double yComp2 = (-1) * radius * Math.sin(Math.toRadians(angleDeg));
		this.node2.setPosX(centerX + xComp2);
		this.node2.setPosY(centerY + yComp2);
		
	}
	
	private void initializeMovementEvents() {;
		// Used to keep track of distance from dragged object's top left corner
		this.image.setOnMousePressed((MouseEvent e) -> {
			this.mouseXDiff = (int) ((this.image.getLayoutX())-(e.getSceneX()));
			this.mouseYDiff = (int) ((this.image.getLayoutY())-(e.getSceneY()));
		});
		
		// Set's image Coordinates = to mouse + the distance recorded at click
		// Adds on the mouseDiff
		this.image.setOnMouseDragged((MouseEvent e) -> {
			this.image.setLayoutX(e.getSceneX() + this.mouseXDiff);
			this.image.setLayoutY(e.getSceneY() + this.mouseYDiff);
			updateNodePos();
			
			this.node1.updateConnectedComponents();
			this.node2.updateConnectedComponents();
		});	
		
		
	}
	
	private void initializeRotationEvents(ConnectionNode node, int direction) {
		node.getNode().setOnMouseDragged((MouseEvent e)-> {
			// Multiplying my either 1 or -1 to change direction
			this.image.setRotate(this.image.getRotate() + (1 * direction));
		    updateNodePos();
	    });

	}
				
	public void setPos(double posX, double posY) {
		this.image.setLayoutX(posX);
		this.image.setLayoutY(posY);
		
		this.node1.setPosX(posX);
		this.node1.setPosY(posY + (this.image.getHeight()/2));
		
		this.node2.setPosX(posX + (this.image.getWidth()));
		this.node2.setPosY(posY + (this.image.getHeight()/2));
	}
	
	public abstract void updateImage(ConnectionNode node1, ConnectionNode node2);
	
	public double getPosX() {
		return this.image.getX();
	}

	public double getPosY() {
		return this.image.getY();
	}
	
	public Group getContainer() {
		return container;
	}

	public void setContainer(Group container) {
		this.container = container;
	}

	public Rectangle getImage() {
		return image;
	}

	public void setImage(Rectangle image) {
		this.image = image;
	}

	public ConnectionNode getNode1() {
		return node1;
	}
	
	public ConnectionNode getNode2() {
		return node2;
	}

	public double getMouseXDiff() {
		return mouseXDiff;
	}

	public void setMouseXDiff(double mouseXDiff) {
		this.mouseXDiff = mouseXDiff;
	}

	public double getMouseYDiff() {
		return mouseYDiff;
	}

	public void setMouseYDiff(double mouseYDiff) {
		this.mouseYDiff = mouseYDiff;
	}

}



