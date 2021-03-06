package Uebung2;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class ListDevide {
	
	public static  int[] listArray;
	public static int[] pivotArray;
	public static int vA, vM, vE, vPivot, vTmp, median;
	public int[] lTB;
	public int[] rTB;

	
	public int pivotElement(int vA, int vM, int vE) {
		//Berechnung des Medians.
		int median = vA;
	 
		if((vA <= vM && vM <= vE) || (vE <= vM && vM <= vA)){
			median = vM;
		}
		else if ((vA <= vE && vE <= vM) || (vM <= vE && vE <= vA)){
			median = vE;
		}
			return median;
	}
	
	public int[] pivotTausch(int median) {
		// Position ermitteln an der der Median im Array steht
		// Element[Median] mit Element[0] tauschen
		
		pivotArray = listArray.clone();
		
		if (median == vM) {
			vTmp = pivotArray[0];
			pivotArray[0] = median;
			pivotArray[(listArray.length / 2)] = vTmp;
			
		} else if (median == vE) {
			vTmp = pivotArray[0];
			pivotArray[0] = median;
			pivotArray[(listArray.length - 1)] = vTmp;
		}
		
		return pivotArray;		
	}
	
	public int[] sortieren(int[] arr) {
		
		int[] sortArray = arr;
		int countlTB = 0;
		int countrTB = 0;
		
		// Round 1 - Count for Arraysize
		
		for (int i = 1; i < sortArray.length; i++) {
			if (sortArray[i] < sortArray[0]) {
				countlTB = countlTB + 1;
			
			} else if (sortArray[i] > sortArray[0]) {
				countrTB = countrTB + 1;
			}
		}
		
		// Round 2 - Create Arrays and fill in data
		
		lTB = new int[countlTB];
		rTB = new int[countrTB];
		
		countlTB = 0;
		countrTB = 0;
		
		for (int i = 1; i < sortArray.length; i++) {
			if (sortArray[i] < sortArray[0]) {
				this.lTB[countlTB] = sortArray[i];
				countlTB = countlTB + 1;
			
			} else if (sortArray[i] > sortArray[0]) {
				this.rTB[countrTB] = sortArray[i];
				countrTB = countrTB + 1;
			}
		}
		
		System.out.println("linker Teilbaum (alles < " + sortArray[0] + "): " + Arrays.toString(lTB));
		System.out.println("rechter Teilbaum (alles > " + sortArray[0] + "): " + Arrays.toString(rTB));
		
		return sortArray;
	}
	
	public int[] importData() throws IOException {
		File in = new File("src\\Uebung2\\Daten_�2_A1.txt");
		FileReader fr = new FileReader(in);
		FileReader fr2 = new FileReader(in);
		BufferedReader br = new BufferedReader(fr);
		BufferedReader br2 = new BufferedReader(fr2);
		
		System.out.println("Datei: " + in);
		
		// Round 1 - Count lines
		String line = br.readLine();
		int lineCount = 0;
		
		while (line != null) {
			lineCount = lineCount + 1;
			//System.out.println(line);
			line = br.readLine();
		}
		
		br.close();
		fr.close();
		System.out.println("LineCount: " + lineCount);
		this.listArray = new int[lineCount];
		
		// Round 2 - Create Array and fill with data
		
		String newLine = br2.readLine();
		int count = 0;
		
		while (newLine != null) {
			this.listArray[count] = Integer.parseInt(newLine);
			//System.out.println("Bla" + list[count]);
			count = count + 1;
			newLine = br2.readLine();
		}
		
		//System.out.println("filled Array: " + Arrays.toString(listArray));
		return this.listArray;
	}
	
	public static void main(String[] args) throws IOException {
		ListDevide listDevide = new ListDevide();
		
		listDevide.importData();
		
		System.out.println("listArray: " + Arrays.toString(listArray));
		
		// vA = erste Pos. | vM = mittlere Pos. | vE = letzte Pos.
		vA = listArray[0];
		vM = listArray[((listArray.length) / 2)];
		vE = listArray[(listArray.length - 1)];
		
		System.out.println("vA: " + vA + " (ArrayPos: 0)");
		System.out.println("vM: " + vM + " (ArrayPos: " + (listArray.length / 2) + ")");
		System.out.println("vE: " + vE + " (ArrayPos: " + listArray.length + ")");
		
		// Pivotelement bestimmen
		median = listDevide.pivotElement(vA, vM, vE);
		System.out.println("Median: " + median);
		
		// Pivotelement an erste Stelle im Array verschieben
		listDevide.pivotTausch(median);
		System.out.println("pivotArray: " + Arrays.toString(pivotArray));
		
		// Arrays in lTB und rTB teilen
		listDevide.sortieren(pivotArray);
		
	}
}
