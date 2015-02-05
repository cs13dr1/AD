package OBST;

import java.text.DecimalFormat;
import java.util.Arrays;

public class OptimalBinarySearchTree {
	
	static DecimalFormat f = new DecimalFormat("#0.00"); 
	
	// Anzahl an Schlüsseln
	public static int k;
	
	// Tabelle
	public static double[][] root;
	public static int[][] kroot;
	
	// p
	public static double[] p = {0.0, 0.1, 0.2, 0.12, 0.05, 0.28, 0.25};
	public static int[] kvalue = {0, 1, 2, 3, 4 ,5 ,6};
	
	
	public static double returnW(int a, int b) {
		// w(i,j) = Summe w(i,i) + w(i,i+1) ... + w(i,j)
		double w = 0.0;
		int start = a;
		int end = b;
		
			for (; a <= b; a++) {
				w = w + root[a][a];
			}
		
		return w;
		
	}
	
	public static double[] returnMinT(int a, int b) {
		// minT(i,j) =  min(i<=k<=j) [ t(i,k-1) + t(k+2,j) ]
		double minT = 0.0;
		int i = a;
		int j = b;
		int pos = 0;
		double key = 0.0;
		int size = (j-i) + 1;
		System.out.println("Size: " + size);
		double[] arrTmp = new double[size];
		double[] arrTmpK = new double[size];
		for (int x = 0; x < arrTmp.length; x++) {
			arrTmp[x] = Double.NaN;
			arrTmpK[x] = Double.NaN;
		}

		System.out.println("Element an Stelle " + i + "|" + (j-1)+  " = " + root[i][j-1]);
		
		for (int k = a; k <= b; k++) {
			System.out.println("k = " + k);

				try {
					System.out.println("t1: root[" + i + "][" + (k-1) + "] = " + (root[i][k-1]));
				} catch (Exception e) {
					// TODO: handle exception
				}
				
				try {
					System.out.println("t2: root[" + (k+1) + "][" + j + "] = " + (root[k+1][j]));
				} catch (Exception e) {
					// TODO: handle exception
				}
				
				try {
					if ((root[i][k-1]) + (root[k+1][j]) == 0.0) {
						arrTmp[pos] = (Double.NaN);
					
					} else
						arrTmp[pos] = ((root[i][k-1]) + (root[k+1][j]));
						arrTmpK[pos] = ((root[i][k-1]) + (root[k+1][j]));
						key = a;
				} catch (Exception e) {
					// TODO: handle exception
				}
				pos++;
			}
		
		System.out.println("arrTmp vorSort: " + Arrays.toString(arrTmp));
		// Sortieren = kleinstes Element vorn
		Arrays.sort(arrTmp);
		
		double v = arrTmp[0];
		//double key = 0.0;
		
		for (int m = 0; m < arrTmpK.length; m++) {
			System.out.println(arrTmp[0] + " = " + arrTmpK[m]);
			if (arrTmp[0] == arrTmpK[m]) {
				key = key + m;
			}
		}
		
		
		System.out.println("arrTmp[0]: " + arrTmp[0]);
		System.out.println("arrTmp: " + Arrays.toString(arrTmp));
		System.out.println(">> minT K: " + key);
		return new double[] {v, key};
	}

	public static void main(String[] args) {
		k = 7;
		root = new double[k][k];
		kroot = new int[k][k];

		
		// bekannte Wahrscheinlichkeiten hinzufügen
		for (int i = 0; i < k; i++) {
			root[i][i] = p[i];
			kroot[i][i] = kvalue[i];
			//System.out.println(root[i][i]);
		}
		
		System.out.println(Arrays.deepToString(root));
		System.out.println(Arrays.deepToString(kroot));
		
		// restlichen Wahrscheinlichkeiten berechnen
		// t(i,j) = w(i,j) + min(i<=k<=j) [ t(i,k-1) + t(k+1,j) ]
		// w(i,j) = Summe w(i,i) + w(i,i+1) ... + w(i,j)

		int i = 0;
		int j = 0;
		int max = 6;
		int c1 = 0; 
		int c2 = 0;
		
		// 0,1, 1,2, 2,3 3,4 4,5 (+1)
		// 0,2, 1,3, 2,4 3,5 (+2)
		// 0,3, 1,4, 2,5 (+3)
		// 0,4, 1,5 (+4)
		// 0,5 (+5)
		
		
		for (c1 = 0; c1 <= max; c1++) {
			//System.out.println("c1: " + c1);
			
			for (j = 2 + c1; j <= max; j++) {
				//System.out.println("j: " + j);
				i = j - c1 - 1;
				System.out.println(i + " | " + j);
				System.out.println("w(" + i + "," + j + ") = " + returnW(i, j));
				
				double[] outputMinT = returnMinT(i, j);
				double outputW = returnW(i, j);
				
				double tij = ( outputW + outputMinT[0] );
				int kij = (int) outputMinT[1];
				
				System.out.println("t(" + i + "," + j + ") = " + tij + " , k = " + kij);
				root[i][j] = tij;
				kroot[i][j] = kij;
				
			}
			System.out.println("");
		}	
		
		//System.out.println(Arrays.deepToString(root));
		System.out.println("Tabelle mit Wahrscheinlichkeiten");
		for (int y = 1; y <= 6; y++) {
			System.out.print("[");
			
			for (int x = 1; x <= 6; x++) {
				System.out.print(" " + f.format(root[y][x]) + " ");
			}
			
			System.out.print("]");
			System.out.println("");
		}
		
		System.out.println("");
		System.out.println("Tabelle mit k-Werten");
		for (int y = 1; y <= 6; y++) {
			System.out.print("[");
			
			for (int x = 1; x <= 6; x++) {
				System.out.print(" " + (kroot[y][x]) + " ");
			}
			
			System.out.print("]");
			System.out.println("");
		}
		
	}

}
