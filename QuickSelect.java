package QuickSelect;

import java.util.Arrays;

public class Launcher {

	//static int[] array = {4, 3, 9, 15, 1, 19, 23, 2, 8, 20, 7};
	static int[] array ={8709, 5257, 4504, 1672, 8734, 8513, 6513, 5210, 7439, 2426, 7926, 8505, 5658, 5858, 5142, 8986, 3121, 2714, 6433,	6976};
	
	public static void tauscheElemente(int[] arr, int a, int b) {
		int tmp = arr[a];
		arr[a] = arr[b];
		arr[b] = tmp;		
	}
	
	public static int sortieren(int[] arr, int z1, int z2) {
		// Pivotelement bestimmen und an erste array[0] schreiben
		int pivotPos = (z2 - z1) / 2;
		System.out.println("Pivotelement = " + array[pivotPos] + " an Position " + pivotPos);
		
		tauscheElemente(array, z1, pivotPos);
		
		System.out.println("Pivotelement nach tausch an Pos[" + z1 + "] " + Arrays.toString(array));
		
		int start = z1;
		int ende = z2;
		int pivot = array[start];
		
		// Schritt 1: Prüfe, welche Elemente von links <= Pivot sind
		// Wenn <=, dann gehe einen Schritt nach rechts
		// Wenn >, dann warte und beginne von rechts zur Prüfen
		// Schritt 2: Prüfe, welche Elemente von rechts > Pivot sind
		// Wenn >, dann gehe einen Schritt nach links
		// Wenn <, dann tausche mit wartendem Element aus Schritt 1 (da > Pivot)
		// Bedingung: start < ende (Positionen im Array)
		
		while (start < ende) {
			// Schritt 1
			while ((array[start] <= pivot) && (start < ende)) {
				start++;				
			}
				
			// Schritt 2
			while ((array[ende] > pivot)) {
				ende--;
			}
						
			//Tausch der beiden Elemente
			if (start < ende) {
				tauscheElemente(arr, start, ende);
			}
		}
		
		// Verschiebe Pivotelement an richtige Stelle
		// = letzte ende Position
		tauscheElemente(arr, z1, ende);
		
		// Rückgabewert = Position des Pivotelementes im Array
		// ende = pivot
		return ende;
		
	}
	
	public static int quickSelect(int[] arr, int z1, int z2, int k) {
		System.out.println("");
		System.out.println("Input | z1: " + z1 + " | z2: " + z2 + " | k: " + k);
		int posPivot = sortieren(array, z1, z2);
		System.out.println("nach Sortierung: " + Arrays.toString(array));
		System.out.println("Pivotposition nach Sortierung: "  + posPivot);
		
		
		// Ermittlung auf welcher Seite weiter gesucht werden soll
		// links oder rechts von Pivotelement
		
		if (k < posPivot) {
			// linker Teil vom Pivotelemente relevant [0 ... posPivot - 1]
			System.out.println("k < posPivot (posPivot: " + posPivot + ")");
			return quickSelect(array, z1, (posPivot - 1), k);
		}
		
		if (k > posPivot) {
			// rechter Teil vom Pivotelement relevant [posPivot + 1 ... posPivot - 1]
			System.out.println("k > posPivot (posPivot: " + posPivot + ")");
			return quickSelect(array, (posPivot+1), z2, (k - posPivot - 1));
			
		}
		
		
		return posPivot;
	}
	
	public static void main(String[] args) {
		int z1 = 0;
		int z2 = (array.length - 1);
		int k = 8;
		System.out.println("Ausgangsarray: " + Arrays.toString(array));
		System.out.println("K = " + k);
		int output = quickSelect(array, z1, z2, k);
		System.out.println("");
		System.out.println("Ergebnis | Position: " + output + " | Wert: " + array[output]);
		System.out.println("Zielarray (teilsortiert): " + Arrays.toString(array));

		 
	}
	

}
