package application;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class ConnectionNode {
	private Map<CircuitComponent, ConnectionNode> connectionMap = new HashMap<>();
	private Circle node = new Circle();
	
	public ConnectionNode() {
		this.node.setRadius(5.0);
		this.node.setFill(Color.AQUA);
	}
	
	public void connectComponent(CircuitComponent comp, ConnectionNode node) {
		this.connectionMap.put(comp, node);
	}
	
	public void updateConnectedComponents() {	
		for(Map.Entry mapElement : this.connectionMap.entrySet()) {
			CircuitComponent keyComponent = (CircuitComponent) mapElement.getKey();
			ConnectionNode valueNode = (ConnectionNode) mapElement.getValue();
			
			valueNode.setPosX(this.node.getCenterX());
			valueNode.setPosY(this.node.getCenterY());
			
			keyComponent.updateImage(keyComponent.getNode1(), keyComponent.getNode2());
		}
	}
	
	public Map<CircuitComponent, ConnectionNode> getConnectionMap() {
		return connectionMap;
	}

	public void setConnectionMap(Map<CircuitComponent, ConnectionNode> connectionMap) {
		this.connectionMap = connectionMap;
	}

	public Circle getNode() {
		return node;
	}

	public void setNode(Circle node) {
		this.node = node;
	}
	
	public double getPosX() {
		return this.node.getCenterX();
	}

	public void setPosX(double posX) {
		this.node.setCenterX(posX);
	}

	public double getPosY() {
		return this.node.getCenterY();
	}

	public void setPosY(double posY) {
		this.node.setCenterY(posY);
	}

}