package OBST;

import java.util.Arrays;

public class OptimalBinarySearchTree {
	
	// Anzahl an Schlüsseln
	public static int k;
	
	// Tabelle
	public static double[][] root;
	
	// p
	public static double[] p= {0.1, 0.2, 0.12, 0.05, 0.28, 0.25};

	public static void main(String[] args) {
		k = 6;
		root = new double[k][k];
		
		// bekannte Wahrscheinlichkeiten hinzufügen
		for (int i = 0; i < k; i++) {
			root[i][i] = p[i];
			//System.out.println(root[i][i]);
		}
		
		System.out.println(Arrays.deepToString(root));
		
		// restlichen Wahrscheinlichkeiten berechnen
		// t(i,j) = w(i,j) + min(i<=k<=j) [ t(i,k-1) + t(k+2,j) ]
		// w(i,j) = Summe w(i,i) + w(i,i+1) ... + w(i,j)

		int i = 0;
		int j = 1;
		int max = 5;
		int c1 = 0; 
		int c2 = 0;
		
		// 0,1, 1,2, 2,3 3,4 4,5 (+1)
		// 0,2, 1,3, 2,4 3,5 (+2)
		// 0,3, 1,4, 2,5 (+3)
		// 0,4, 1,5 (+4)
		// 0,5 (+5)
		
		for (c1 = 0; c1 < max; c1++) {
			i = 0;
			c2 = c1 + 1;
			int x = c2;
			
			while(c2 < max) {
				System.out.println(c1);
				System.out.println(c2);
				System.out.println("[" + i + "]" + " [" + j + "]");
				i = i + x;
				j = j + x;
				x++;
			}
			
			
		}
		
		
	}

}
