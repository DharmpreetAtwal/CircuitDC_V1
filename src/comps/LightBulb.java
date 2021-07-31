package comps;

import application.CircuitComponent;
import application.ConnectionNode;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;

public class LightBulb extends CircuitComponent {
	final private double fixedWidth = 70.0;
	final private double fixedHeight = 70.0;
	
	public LightBulb() {
		super(70,70);
		this.getImage().setFill(Color.YELLOW);
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
