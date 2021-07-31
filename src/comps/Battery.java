package comps;

import application.CircuitComponent;
import application.ConnectionNode;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;

public class Battery extends CircuitComponent {
	final private double fixedWidth = 100.0;
	final private double fixedHeight = 40.0;
	
	public Battery() {
		super(100,40);
		this.getImage().setFill(Color.ORANGE);
	}

	public void updateImage() {
		this.image.setWidth(fixedWidth);
		this.image.setHeight(fixedHeight);
	}
	
	@Override
	public void updateImage(ConnectionNode node1, ConnectionNode node2) {
		double xComp = this.node1.getPosX() - this.node2.getPosX();
		double yComp = this.node1.getPosY() - this.node2.getPosY(); 
		double angleRad = Math.atan(yComp / xComp);
		
		this.image.setRotate(Math.toDegrees(angleRad));
		
	}

}
