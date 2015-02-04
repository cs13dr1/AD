package Tree;

public class NatSuchbaum {

	
	public static int[] natSuchbaumArray;
	
	
	Node root;

	private void add(int key, String name) {
		
		// neues Element erstellen
		// key = Wert, name = Elementposition im Array
		
		Node newElement = new Node(key, name);
		
		// Wenn das root-Element leer ist, dann setze das aktuelle
		// Element als root-Element
		
		System.out.println("");
		System.out.println("add Element: " + newElement);
		
		if (root == null) {
			root = newElement;
			System.out.println("root: " + root);
		
		} else {
			
			// Vergleich für jedes neue Element beginnt bei Root
						
			Node focusElement = root;
			Node parent;
			
			while (true) {
				
				parent = focusElement;
				System.out.println("Parent: " + parent);
				
				if (key < focusElement.key) {
					// neues Element kleiner als Elternelement
					
					focusElement = focusElement.leftChild;
					// Fokus wird auf linkes Kindelement gesetzt
					
					if (focusElement == null) {
						// links
						// Wenn kein weiteres Kindelement vorhanden
						// dann wird aktuelles Element neues Kind
						parent.leftChild = newElement;
						return;
					}
				
				} else {
					// neues Element größer als Elternelement
					// Fokus wird auf rechtes Kindeelement gesetzt
					focusElement = focusElement.rightChild;
					
					if (focusElement == null) {
						// rechts
						// Wenn kein weiteres Kindelement vorhanden
						// dann wird aktuelles Element neues Kind
						parent.rightChild = newElement;
						return;
					}
				}
			}
		}
		
	}
	
	public static void main(String[] args) {
		NatSuchbaum baum = new NatSuchbaum();
		int[] sourceArray = {54,22,76,40,15,18,4,13,16,77,28,38};
		
		for (int i = 0; i < sourceArray.length - 1; i++) {
			String name = Integer.toString(i);
			baum.add(sourceArray[i], name);
		}
		
	}


}

class Node {
	int key;
	String name;
	
	Node leftChild;
	Node rightChild;
	
	Node (int key, String name) {
		this.key = key;
		this.name = name;
	}
	
	public String toString() {
		return name + " key: " + key;
	}
}
