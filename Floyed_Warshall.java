import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import spiffy.core.util.TwoDHashMap;


public class Floyed_Warshall {

	public static void main(String[] args) {
		
		int vertex;
		int iteration;
		//int k;
		
		vertex = 4;
		iteration = vertex - 1;
		//k = vertex - 1;
		
		TwoDHashMap<Integer, Integer, Integer> mapD = new TwoDHashMap<Integer, Integer, Integer>();
		TwoDHashMap<Integer, Integer, Integer> mapS = new TwoDHashMap<Integer, Integer, Integer>();
		
		// Fülle mapD
		mapD.set(1, 2, 2);
		mapD.set(2, 1, 2);
		mapD.set(1, 3, 4);
		mapD.set(3, 1, 4);
		mapD.set(3, 1, 4);
		mapD.set(1, 4, Integer.MAX_VALUE);
		mapD.set(4, 1, Integer.MAX_VALUE);
		mapD.set(2, 3, 1);
		mapD.set(3, 2, 1);
		mapD.set(2, 4, 5);
		mapD.set(4, 2, 5);
		mapD.set(3, 4, 3);
		mapD.set(4, 3, 3);

		mapD.set(1, 1, null);
		mapD.set(2, 2, null);
		mapD.set(3, 3, null);
		mapD.set(4, 4, null);
	
		
		System.out.println("mapD0 - gefüllt mit Ausgangsdaten");
		for (int i = 1; i <= 4; i++) {
			System.out.print("[ ");
			for (int j = 1; j <= 4; j++) {
				System.out.print(mapD.get(i, j) + " ");
			}
			System.out.print("]");
			System.out.println("");
		}

		// fülle mapS
		for (int i = 1; i <= 4; i++) {
			for (int j = 1; j <= 4; j++) {
				if (i == j) {
					mapS.set(i, j, null);
				
				} else {
					mapS.set(i, j, j);
				}
			}
		}
		
		System.out.println("");
		System.out.println("mapS0 - gefüllt mit Ausgangsdaten");
		for (int i = 1; i <= 4; i++) {
			System.out.print("[ ");
			for (int j = 1; j <= 4; j++) {
				System.out.print(mapS.get(i, j) + " ");
			}
			System.out.print("]");
			System.out.println("");
		}
		
		// neue Map anlegen, Zeile/Spalte der Iteration kopieren
		// Zeile/Spalte 1 aus d0/s0 kopieren
		TwoDHashMap<Integer, Integer, Integer> mapD1 = new TwoDHashMap<Integer, Integer, Integer>();
		TwoDHashMap<Integer, Integer, Integer> mapS1 = new TwoDHashMap<Integer, Integer, Integer>();
		
		// fülle mapD1
		for (int i = 1; i <= 4; i++) {
			for (int j = 1; j <= 4; j++) {
				if (i == j) {
					mapD1.set(i, j, -1);
				
				} else if ((i == 1) || (j == 1) ) {
					mapD1.set(i, j, mapD.get(i, j));
				} else {
					mapD1.set(i, j, 0);
				}
				
			}
		}
		
		System.out.println("");
		System.out.println("mapD1 - Spalte 1/ Zeile1 aus D0 kopiert");
		for (int i = 1; i <= 4; i++) {
			System.out.print("[ ");
			for (int j = 1; j <= 4; j++) {
				System.out.print(mapD1.get(i, j) + " ");
			}
			System.out.print("]");
			System.out.println("");
		}
		
		// fülle mapS1
		for (int i = 1; i <= 4; i++) {
			for (int j = 1; j <= 4; j++) {
				if (i == j) {
					mapS1.set(i, j, -1);
				
				} else if ((i == 1) || (j == 1) ) {
					mapS1.set(i, j, mapS.get(i, j));
				} else {
					mapS1.set(i, j, 0);
				}
				
			}
		}
		
		System.out.println("");
		System.out.println("mapS1  - Spalte 1/ Zeile1 aus S0 kopiert");
		for (int i = 1; i <= 4; i++) {
			System.out.print("[ ");
			for (int j = 1; j <= 4; j++) {
				System.out.print(mapS1.get(i, j) + " ");
			}
			System.out.print("]");
			System.out.println("");
		}
		
		// Berechnung zum füllen von D1
		// C(ij) aus D(k) = 0 (Feld ist frei), dann
		// C(ij) aus D(k-1) > ( C(ik) + C(kj) ) aus D(k-1)
		// JA: C(ij) aus D(k) = ( C(ik) + C(kj) ) aus D(k-1)
		// NEIN: C(ij) aus D(k) = C(ij) aus D(k-1)
		
		// Berechnung zum füllen von S1
		// Wenn C(ij) aus D(k-1) und C(ij) aus D(k) gleich sind, dann S(ij) von S(k-1) nach S(ij) von S(k) kopieren
		// Wenn C(ij) aus D(k-1) und C(ij) aus D(k) ungleich sind, dann S(ij) von S(k) = k
		
		int var1 = 0;
		int var2 = 0;
		int var3 = 0;
		int var4 = 0;
		int var5 = 0;
		
		int k = 1;
		
		for (int i = 1; i <= 4; i++) {
			for (int j = 1; j <= 4; j++) {
				//System.out.println(i + " " + j);
				if (mapD1.get(i, j) == 0) {
					//System.out.println(mapD1.get(i, j));
					
					// Felder auslesen
					var1 = mapD.get(i, j);
					var2 = ( mapD.get(i, k) + mapD.get(k, j) );
					
					// Wenn einer der beiden Werte (i,k) oder (k,j) oo ist, dann wird var2 auf oo gesetzt
					if ( (mapD.get(i, k) == Integer.MAX_VALUE) || (mapD.get(k, j) == Integer.MAX_VALUE) ) {
						var2 = Integer.MAX_VALUE;
					}

					if (var1 > var2) {
						mapD1.set(i, j, var2);
					} else {
						mapD1.set(i, j, var1);
					}
					
					// fülle S(i,j)
					var3 = mapD.get(i, j);
					var4 = mapD1.get(i, j);
					var5 = mapS.get(i, j);
					
					if ( var3 == var4 ) {
						mapS1.set(i, j, var5);
					} else {
						mapS1.set(i, j, k);
					}
				}
				
				
			}
		}
		
		System.out.println("");
		System.out.println("mapD1 nach Berechnung");
		for (int i = 1; i <= 4; i++) {
			System.out.print("[ ");
			for (int j = 1; j <= 4; j++) {
				System.out.print(mapD1.get(i, j) + " ");
			}
			System.out.print("]");
			System.out.println("");
		}
		
		System.out.println("");
		System.out.println("mapS1 nach Berechnung");
		for (int i = 1; i <= 4; i++) {
			System.out.print("[ ");
			for (int j = 1; j <= 4; j++) {
				System.out.print(mapS1.get(i, j) + " ");
			}
			System.out.print("]");
			System.out.println("");
		}
	}
	
}