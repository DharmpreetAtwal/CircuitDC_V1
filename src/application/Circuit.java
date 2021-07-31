package application;

import javafx.scene.paint.Color;
import java.util.ArrayList;

public class Circuit {
	ArrayList<CircuitComponent> componentList = new ArrayList<>();
	private final int snapSensitivity = 5;
	
	public Circuit(CircuitComponent...componentList) {
		
		for(CircuitComponent component: componentList) {
			this.componentList.add(component);
		}
		
		initializeConnectionEvent();
	}
	
	// Have CircuitComponent initialize it's own connection event?
	// Pass parentCircuit and componentSelected as parameter?
	// Could enable functionality to choose which circuit the component is added to?
	public void initializeConnectionEvent() {
		// Add the eventListener to every component in the circuit
		for(CircuitComponent componentSelected : this.componentList) {
			
			componentSelected.getNode1().getNode().centerXProperty().addListener((observable, oldvalue, newvalue) ->{
				// For every Component in the circuit
				for(CircuitComponent componentChecked : this.componentList) {
					// That is not the same component in the list												
					if(this.componentList.indexOf(componentSelected) != this.componentList.indexOf(componentChecked)) {
						
						// For each possible Node Combination between the dragged component and check component
						if(checkSnapDistance(componentSelected.getNode1(), componentChecked.getNode1())) {
							System.out.println("1 - 1");
							componentSelected.getNode1().getNode().setFill(Color.BLACK);
							componentChecked.getNode1().getNode().setFill(Color.YELLOW);
							
							componentSelected.getNode1().getConnectionMap().put(componentChecked, componentChecked.getNode1());
							componentChecked.getNode1().getConnectionMap().put(componentSelected, componentSelected.getNode1());
							
						} 
						
						if(checkSnapDistance(componentSelected.getNode1(), componentChecked.getNode2())) { 
						 System.out.println("1 - 2");
							componentSelected.getNode1().getNode().setFill(Color.BLACK);
							componentChecked.getNode2().getNode().setFill(Color.YELLOW);
							
							componentSelected.getNode1().getConnectionMap().put(componentChecked, componentChecked.getNode2());
							componentChecked.getNode2().getConnectionMap().put(componentSelected, componentSelected.getNode1());
							
						} 
						
					}
				}	
			});
			
			componentSelected.getNode2().getNode().centerXProperty().addListener((observable, oldvalue, newvalue) ->{
				for(CircuitComponent componentChecked : this.componentList) {
					// That is not the same component in the list
						
					if(this.componentList.indexOf(componentSelected) != this.componentList.indexOf(componentChecked)) {
						
						if(checkSnapDistance(componentSelected.getNode2(), componentChecked.getNode1())) {
							System.out.println("2 - 1");
							componentSelected.getNode2().getNode().setFill(Color.BLACK);
							componentChecked.getNode1().getNode().setFill(Color.YELLOW);
							
							componentSelected.getNode2().getConnectionMap().put(componentChecked, componentChecked.getNode1());
							componentChecked.getNode1().getConnectionMap().put(componentSelected, componentSelected.getNode2());
							
						} 
				
						if(checkSnapDistance(componentSelected.getNode2(), componentChecked.getNode2())) {
							System.out.println("2 - 2");
							componentSelected.getNode2().getNode().setFill(Color.BLACK);
							componentChecked.getNode2().getNode().setFill(Color.YELLOW);
							
							componentSelected.getNode2().getConnectionMap().put(componentChecked, componentChecked.getNode2());
							componentChecked.getNode2().getConnectionMap().put(componentSelected, componentSelected.getNode2());
							
						} 
						
					}
				}
			});
			
		}
	}
	
	private boolean checkSnapDistance(ConnectionNode nodeA, ConnectionNode nodeB) {
		double xDistance = Math.abs(nodeA.getPosX() - nodeB.getPosX());
		double yDistance = Math.abs(nodeA.getPosY() - nodeB.getPosY());
		double distance = Math.sqrt(Math.pow(xDistance, 2) + Math.pow(yDistance, 2));

		if(distance < snapSensitivity) {
			return true;
		} else {
			return false;
		}
	}
	
	public ArrayList<CircuitComponent> getComponentList() {
		return componentList;
	}

	public void setComponentList(ArrayList<CircuitComponent> componentList) {
		this.componentList = componentList;
	}

	
}
