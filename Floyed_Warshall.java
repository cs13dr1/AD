package A8;
import java.awt.DisplayMode;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import spiffy.core.util.TwoDHashMap;


public class Floyed_Warshall {
	
	public static TwoDHashMap<Integer, Integer, Integer> mapD = new TwoDHashMap<Integer, Integer, Integer>();
	public static TwoDHashMap<Integer, Integer, Integer> mapS = new TwoDHashMap<Integer, Integer, Integer>();
	
	public static TwoDHashMap<Integer, Integer, Integer> mapDTmp = new TwoDHashMap<Integer, Integer, Integer>();
	public static TwoDHashMap<Integer, Integer, Integer> mapSTmp = new TwoDHashMap<Integer, Integer, Integer>();
	
	public static int vertex;
	public static int iteration;
	public static int iterationMax;
	public static int var1 = 0;
	public static int var2 = 0;
	public static int var3 = 0;
	public static int var4 = 0;
	public static int var5 = 0;
	
	public static void readFile() {
		
		String cells[] = null;
		
		try 
		{
			File in = new File("src\\A8\\Daten8A.txt");
			FileReader fr = new FileReader(in);
		    BufferedReader br = new BufferedReader(fr);
			
		    String line = null;
		    
		    while ((line = br.readLine()) != null) 
		    {
		         cells = line.split("\t");                          
		        //System.out.println(cells.length);
		        //System.out.println(line);
		        
		    }
		    br.close();
		    for (int i = 0; i < cells.length; i++) {
		    	//System.out.print(cells[i] + "");
		    }
		} 
		catch (IOException e) 
		{
		    e.printStackTrace();
		}
	}
	
	public static void calculateMaps() {
		// Berechnung zum füllen von D1
		// C(ij) aus D(k) = 0 (Feld ist frei), dann
		// C(ij) aus D(k-1) > ( C(ik) + C(kj) ) aus D(k-1)
		// JA: C(ij) aus D(k) = ( C(ik) + C(kj) ) aus D(k-1)
		// NEIN: C(ij) aus D(k) = C(ij) aus D(k-1)
				
		// Berechnung zum füllen von S1
		// Wenn C(ij) aus D(k-1) und C(ij) aus D(k) gleich sind, dann S(ij) von S(k-1) nach S(ij) von S(k) kopieren
		// Wenn C(ij) aus D(k-1) und C(ij) aus D(k) ungleich sind, dann S(ij) von S(k) = k
		
		for (int i = 1; i <= vertex; i++) {
			for (int j = 1; j <= vertex; j++) {
				
				if ( (i == iteration) || (j == iteration) ) {
					// mache nichts, wenn i/j zur k. Iteration gehören
				}
				else if (mapDTmp.get(i, j) == 0) {
						
						// Felder auslesen
						var1 = mapD.get(i, j);
						var2 = ( mapD.get(i, iteration) + mapD.get(iteration, j) );
						
						// Wenn einer der beiden Werte (i,k) oder (k,j) oo ist, dann wird var2 auf oo gesetzt
						if ( (mapD.get(i, iteration) == Integer.MAX_VALUE) || (mapD.get(iteration, j) == Integer.MAX_VALUE) ) {
							var2 = Integer.MAX_VALUE;
						}
	
						if (var1 > var2) {
							// YES
							mapDTmp.set(i, j, var2);
							mapDTmp.set(j, i, var2);
						} else {
							// NO
							mapDTmp.set(i, j, var1);
							mapDTmp.set(j, i, var1);
						}
						
						// fülle S(i,j)
						var3 = mapD.get(i, j);
						var4 = mapDTmp.get(i, j);
						var5 = mapS.get(i, j);
						
						if ( var3 == var4 ) {
							mapSTmp.set(i, j, var5);
							mapSTmp.set(j, i, var5);
						} else {
							mapSTmp.set(i, j, iteration);
							mapSTmp.set(j, i, iteration);
						}
					}
									
				}
		}
		
		// copy mapDTmp / mapSTmp -> mapD / mapS for next iteration
		for (int i = 1; i <= vertex; i++) {
			for (int j = 1; j <= vertex; j++) {
				mapD.set(i, j, mapDTmp.get(i,j));
				mapS.set(i, j, mapSTmp.get(i,j));
			}
		}
	}
	
	public static void fillMaps() {
		// Map mapDTmp / mapSTmp, Zeile/Spalte der Iteration k aus mapD / mapS kopieren
		// Zeile/Spalte k aus d(k-1)/s(k-1) kopieren
				
				// fülle mapDTmp
				for (int i = 1; i <= vertex; i++) {
					for (int j = 1; j <= vertex; j++) {
						if (i == j) {
							mapDTmp.set(i, j, -1);
						
						} else if ((i == iteration) || (j == iteration) ) {
							mapDTmp.set(i, j, mapD.get(i, j));
						} else {
							mapDTmp.set(i, j, 0);
						}
					}
				}
				
				System.out.println("");
				System.out.println("Iteration: " + iteration + " / " + iterationMax);
				System.out.println("mapDTmp - " + iteration + ". Spalte/Zeile aus D" + (iteration-1) + " kopiert");
				for (int i = 1; i <= vertex; i++) {
					System.out.print("[ ");
					for (int j = 1; j <= vertex; j++) {
						System.out.print(mapDTmp.get(i, j) + " ");
					}
					System.out.print("]");
					System.out.println("");
				}
				
				// fülle mapSTmp
				for (int i = 1; i <= vertex; i++) {
					for (int j = 1; j <= vertex; j++) {
						if (i == j) {
							mapSTmp.set(i, j, -1);
						
						} else if ((i == iteration) || (j == iteration) ) {
							mapSTmp.set(i, j, mapS.get(i, j));
						} else {
							mapSTmp.set(i, j, 0);
						}
						
					}
				}
				
				System.out.println("");
				System.out.println("Iteration: " + iteration + " / " + iterationMax);
				System.out.println("mapSTmp - " + iteration + ". Spalte/Zeile aus S" + (iteration-1) + " kopiert");
				for (int i = 1; i <= vertex; i++) {
					System.out.print("[ ");
					for (int j = 1; j <= vertex; j++) {
						System.out.print(mapSTmp.get(i, j) + " ");
					}
					System.out.print("]");
					System.out.println("");
				}
	}
	
	public static void displayMaps() {
		
		System.out.println("");
		System.out.println("mapDTmp nach Berechnung");
		System.out.println("Iteration: " + iteration + " / " + iterationMax);
		for (int i = 1; i <= vertex; i++) {
			System.out.print("[ ");
			for (int j = 1; j <= vertex; j++) {
				System.out.print(mapDTmp.get(i, j) + " ");
			}
			System.out.print("]");
			System.out.println("");
		}
		
		System.out.println("");
		System.out.println("mapSTmp nach Berechnung");
		System.out.println("Iteration: " + iteration + " / " + iterationMax);
		for (int i = 1; i <= vertex; i++) {
			System.out.print("[ ");
			for (int j = 1; j <= vertex; j++) {
				System.out.print(mapSTmp.get(i, j) + " ");
			}
			System.out.print("]");
			System.out.println("");
		}
	}

	public static void main(String[] args) {
		
		// ToDo
		// - remove all loops
		// - remove all parallel edges between vertexes, keep the one with the lowest
		// - calculateMap darf nicht k. Zeile/Speile durchlaufen
		
//		int[] source = {0, 1,2,2, 2,1,2, 1,3,4, 3,1,4, 1,4,Integer.MAX_VALUE, 4,1,Integer.MAX_VALUE, 2,3,1, 3,2,1, 2,4,5, 4,2,5, 3,4,3, 4,3,3};
//		vertex = 4;
		
		int[] source = {0, 1,2,3, 2,1,3, 1,4,1, 4,1,1, 1,5,2, 5,1,2, 2,3,6, 3,2,6, 4,5,4, 5,4,4, 4,6,5, 6,4,5, 5,2,0, 2,5,0, 5,6,3, 6,5,3, 6,2,5, 2,6,5, 6,3,2, 3,6,2};
		vertex = 6;
		
		//vertex = 0;
		iteration = 0;
		iterationMax = vertex - 1;
				
		// Fülle mapD
//		mapD.set(1, 2, 2);
//		mapD.set(2, 1, 2);
//		mapD.set(1, 3, 4);
//		mapD.set(3, 1, 4);
//		mapD.set(1, 4, Integer.MAX_VALUE);
//		mapD.set(4, 1, Integer.MAX_VALUE);
//		mapD.set(2, 3, 1);
//		mapD.set(3, 2, 1);
//		mapD.set(2, 4, 5);
//		mapD.set(4, 2, 5);
//		mapD.set(3, 4, 3);
//		mapD.set(4, 3, 3);
		

		
		// Fülle HashMap mit oo
		for (int i = 1; i <= vertex; i++) {
			for (int j = 1; j <= vertex; j++) {
				mapD.set(i, j, Integer.MAX_VALUE);
			}
		}
		
		// Fülle Diagonalen mit -1
		for (int i = 1; i <= vertex; i++) {
			for (int j = 1; j <= vertex; j++) {
				if (i == j) {
					mapD.set(i, j, -1);
				}
			}
		}
	
		// Fülle HashMap mit Daten
		for (int i = 1; i <= source.length - 1;) {
			mapD.set(source[i], source[i+1], source[i+2]);
			i = i + 3;
			}
		
	
		
		System.out.println("mapD0 - gefüllt mit Ausgangsdaten");
		for (int i = 1; i <= vertex; i++) {
			System.out.print("[ ");
			for (int j = 1; j <= vertex; j++) {
				System.out.print(mapD.get(i, j) + " ");
			}
			System.out.print("]");
			System.out.println("");
		}

		// fülle mapS
		for (int i = 1; i <= vertex; i++) {
			for (int j = 1; j <= vertex; j++) {
				if (i == j) {
					mapS.set(i, j, -1);
				
				} else {
					mapS.set(i, j, j);
				}
			}
		}
		
		System.out.println("");
		System.out.println("mapS0 - gefüllt mit Ausgangsdaten");
		for (int i = 1; i <= vertex; i++) {
			System.out.print("[ ");
			for (int j = 1; j <= vertex; j++) {
				System.out.print(mapS.get(i, j) + " ");
			}
			System.out.print("]");
			System.out.println("");
		}
		
		for (iteration = 1; iteration <= iterationMax; iteration++) {
			// Kopiere k. Zeile/Spalte nach mapDTmp / mapSTmp
			fillMaps();
			
			// Berechne Map für aktuelle Iteration
			calculateMaps();
			
			// Ausgabe
			displayMaps();
		}

		readFile();


	}
	
}