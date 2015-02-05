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
		int j = 0;
		
		//
		for (; i <= j; i++) {
			
		}
	}

}
