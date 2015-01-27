package FiBo_Matrix;

import java.awt.image.ConvolveOp;
import java.util.Arrays;

public class FiBo_Matrix {

	public static void main(String[] args) {
		
		FiBo_Matrix fiBo = new FiBo_Matrix();
		int n = 7;
		
		// Matrix A zum späteren potenzieren
		// A = 	( 1 1 )
		//		( 1 0 )
		long[][] matrixA =  {{1,1},{1,0}};
		System.out.println("matrixA: " + Arrays.deepToString(matrixA));
		
		// Matrix B gibt das Ergebnis direkt aus
		// B=	( fib(n)  )
		//		( fib(n-1 )
		//long[][] matrixB = {{fiBo.fib(n)},{fiBo.fib(n - 1)}};
		//System.out.println("matrixB: " + Arrays.deepToString(matrixB));
		
		// Matrix C als Zwischenspeicher für das Potenzieren
		long[][] matrixC = {{0,0},{0,0}};
		long[][] matrixTemp = matrixA;
		System.out.println("matrixC: " + Arrays.deepToString(matrixC));
		
		// Matrix D wird mit matrixA^(n-2) multipliziert
		// D = 	( fib(2) )
		//		( fib(1) )
		long[][] matrixD = {{fiBo.fib(2)},{fiBo.fib(1)}};
		System.out.println("matrixB: " + Arrays.deepToString(matrixD));
		
		// Matrix Res als Ergebnis
		long[][] matrixRes = {{0},{0}};
		System.out.println("matrixRes: " + Arrays.deepToString(matrixRes));
		
		System.out.println("");
		
//		//------
//		// n-2 Loops Potenzieren
//		for (int i = 1; i < n - 2; i++) {
//		
//			
//		matrixC[0][0] = (matrixTemp[0][0]*matrixA[0][0]) + (matrixTemp[0][1]*matrixA[1][0]);
//		matrixC[0][1] = (matrixTemp[0][0]*matrixA[0][1]) + (matrixTemp[0][1]*matrixA[1][1]);
//		matrixC[1][0] = (matrixTemp[1][0]*matrixA[0][0]) + (matrixTemp[1][1]*matrixA[1][0]);
//		matrixC[1][1] = (matrixTemp[1][0]*matrixA[0][1]) + (matrixTemp[1][1]*matrixA[1][1]);
//		
//		// Clone matrixC to matrixTemp .. bacause matrixTemp = matrixC did not work :/
//		matrixTemp = matrixC.clone();
//		for (int j = 0; j < matrixC.length; j++) {
//		    matrixTemp[j] = matrixTemp[j].clone();
//		}
//			
//		//System.out.println("matrixTemp: " + i + " : " + Arrays.deepToString(matrixTemp));
//		System.out.println("matrixC für A^(" + n + "-2) " +  "["+ i + " Loop] " + " : " + Arrays.deepToString(matrixC));
//		}
		//------
		
		//------
		// Binärzerlegung von exp(n-2) um Rechenoperationen zu sparen
		//
		long[][] matX = {{1,1},{1,0}};
		long[][] matT = {{1,1},{1,0}};
		long[][] matRes = {{0,0},{0,0}};
		
		// 1) exp(n-2) -> Binärdarstellung
		// 2) in Array[] speichern
		// 3) Binärdarstellung auswerten
		//		0 -> quadrieren
		//		1 -> quadrieren + multiplizieren
		// 4) führende 1 wird gestrichen
		// 5) Operationen entsprechend durchführen
		int exp = n - 2;
		String binary = Integer.toBinaryString(exp);
		String[] array = binary.split("");
		System.out.println("Binary: " + binary);
		System.out.println(Arrays.toString(array));
		System.out.println("");
		
		int j = 0;
		int count = 0;
		
		for (int i = 2; i < array.length; i++) {
			if (array[i].equals("1")) {
				// quadrieren + multiplizieren
				System.out.println("Durchlauf " + i + " (QM)");
				System.out.println("Arrayinhalt: " + array[i]);
				
				//Q
				matRes[0][0] = (matT[0][0]*matT[0][0]) + (matT[0][1]*matT[1][0]);
				matRes[0][1] = (matT[0][0]*matT[0][1]) + (matT[0][1]*matT[1][1]);
				matRes[1][0] = (matT[1][0]*matT[0][0]) + (matT[1][1]*matT[1][0]);
				matRes[1][1] = (matT[1][0]*matT[0][1]) + (matT[1][1]*matT[1][1]);
				count = count + 1;
				
				// Clone matrix
				matT = matRes.clone();
				for (j = 0; j < matRes.length; j++) {
				    matT[j] = matT[j].clone();
				}
				
				//M
				matRes[0][0] = (matT[0][0]*matX[0][0]) + (matT[0][1]*matX[1][0]);
				matRes[0][1] = (matT[0][0]*matX[0][1]) + (matT[0][1]*matX[1][1]);
				matRes[1][0] = (matT[1][0]*matX[0][0]) + (matT[1][1]*matX[1][0]);
				matRes[1][1] = (matT[1][0]*matX[0][1]) + (matT[1][1]*matX[1][1]);
				count= count + 1;
				
				// Clone matrix
				matT = matRes.clone();
				for (j = 0; j < matRes.length; j++) {
				    matT[j] = matT[j].clone();
				}
				
				
				} else if (array[i].equals("0")) {
				// quadrieren
					System.out.println("Durchlauf " + i + " (Q)");
				System.out.println("Arrayinhalt: " + array[i]);
				
				//Q
				matRes[0][0] = (matT[0][0]*matT[0][0]) + (matT[0][1]*matT[1][0]);
				matRes[0][1] = (matT[0][0]*matT[0][1]) + (matT[0][1]*matT[1][1]);
				matRes[1][0] = (matT[1][0]*matT[0][0]) + (matT[1][1]*matT[1][0]);
				matRes[1][1] = (matT[1][0]*matT[0][1]) + (matT[1][1]*matT[1][1]);
				count= count + 1;
				
				// Clone matrix
				matT = matRes.clone();
				for (j = 0; j < matRes.length; j++) {
				    matT[j] = matT[j].clone();
				}
				
			}
				}
			
		
				
		System.out.println(Arrays.deepToString(matRes));
		System.out.println("Anzahl der Operationen: " + count);
		
		// Potenzierte matrixC mit matrixD multiplizieren
		System.out.println("");
		System.out.println("matrixC: " + Arrays.deepToString(matRes));
		System.out.println("matrixD: " + Arrays.deepToString(matrixD));
		System.out.println("matrix = matRes * matrixD");
		matrixRes[0][0] = ((matRes[0][0]*matrixD[0][0]) + (matRes[0][1]*matrixD[1][0]));
		matrixRes[1][0] = ((matRes[1][0]*matrixD[0][0]) + (matRes[1][1]*matrixD[1][0]));
		System.out.println("matrixRes: " + Arrays.deepToString(matrixRes));
		//System.out.println("matrixB (Kontrolle): " + Arrays.deepToString(matrixB));
		

		
}
					
		
	public long fib(int n) {
		if(n <= 2) {
			return 1;
		
		} else {		    
			return fib(n - 1) + fib(n - 2);			
		}

	}

}
