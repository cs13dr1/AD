package FiBo;



public class FiBo_Rekursiv {

	public long fib(int n) {
	    if(n <= 2)
	        return 1;
	    
		return fib(n - 1) + fib(n - 2);
		
		
	}
	
	public static void main(String[] args) {
		FiBo_Rekursiv fibo = new FiBo_Rekursiv();
				
		for (int i = 1; i < 16; i++) {
			long tStart = System.currentTimeMillis();
			System.out.println("i=" + i + " : " + fibo.fib(i));
			long tEnd = System.currentTimeMillis();
			long tDelta = tEnd - tStart;
			double elapsedSeconds = tDelta / 1000.0;
			System.out.println("Time in sec: " + elapsedSeconds);
		}

	}

}
