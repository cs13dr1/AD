package FiBo_Matrix;

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
		long[][] matrixB = {{fiBo.fib(n)},{fiBo.fib(n - 1)}};
		System.out.println("matrixB: " + Arrays.deepToString(matrixB));
		
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
				
		// n-2 Loops Potenzieren
		for (int i = 1; i <= n - 2; i++) {
		
			
		matrixC[0][0] = (matrixTemp[0][0]*matrixA[0][0]) + (matrixTemp[0][1]*matrixA[1][0]);
		matrixC[0][1] = (matrixTemp[0][0]*matrixA[0][1]) + (matrixTemp[0][1]*matrixA[1][1]);
		matrixC[1][0] = (matrixTemp[1][0]*matrixA[0][0]) + (matrixTemp[1][1]*matrixA[1][0]);
		matrixC[1][1] = (matrixTemp[1][0]*matrixA[0][1]) + (matrixTemp[1][1]*matrixA[1][1]);
		
		// Clone matrixC to matrixTemp .. bacause matrixTemp = matrixC did not work :/
		matrixTemp = matrixC.clone();
		for (int j = 0; j < matrixC.length; j++) {
		    matrixTemp[j] = matrixTemp[j].clone();
		}
			
		//System.out.println("matrixTemp: " + i + " : " + Arrays.deepToString(matrixTemp));
		System.out.println("matrixC für A^(" + n + "-2) " +  "["+ i + " Loop] " + " : " + Arrays.deepToString(matrixC));
		}
		
		
		// Potenzierte matrixC mit matrixD multiplizieren
		System.out.println("");
		System.out.println("matrixC: " + Arrays.deepToString(matrixC));
		System.out.println("matrixD: " + Arrays.deepToString(matrixD));
		System.out.println("matrixRes = matrixC * matrixD");
		matrixRes[0][0] = ((matrixC[0][0]*matrixD[0][0]) + (matrixC[0][1]*matrixD[1][0]));
		matrixRes[1][0] = ((matrixC[1][0]*matrixD[0][0]) + (matrixC[1][1]*matrixD[1][0]));
		System.out.println("matrixRes: " + Arrays.deepToString(matrixRes));
		System.out.println("matrixB (Kontrolle): " + Arrays.deepToString(matrixB));
		

		
}
			
		
		
		
	public long fib(int n) {
		if(n <= 2) {
			return 1;
		
		} else {		    
			return fib(n - 1) + fib(n - 2);			
		}

	}

}
