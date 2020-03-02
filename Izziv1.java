public class Izziv1{
	public static void main(String [] args){
		System.out.printf("%10s | %10s | %10s %n", "n", "linearno", "binarno");
		int n = 1000;
		while(n <= 100000){
			long linear = timeLinear(n);
			long binary = timeBinary(n);
			System.out.printf("%10d | %10d | %10d %n", n, linear, binary);
			n += 1000;
		}
	}

	static int[] generateTable(int n){
		int [] x = new int[n];
		for(int i = 0; i < n; i++){
			x[i] = i+1;
		}
		return x;
	}

	static int findLinear(int[] a, int v){
		for(int i = 0; i < a.length; i++){
			if(a[i] == v){
				return i;
			}
		}
		return -1;
	}

	static int findBinary(int[] a, int l, int r, int v){
		if(r>=l){
			int s = l + (r-l)/2; //sredina
			if(a[s] == v){
				return s;
			}
			if(a[s] < v){
				//pogledamo desno od sredine
				return findBinary(a, s + 1, r, v);
			}
			//pogledamo levo od sredine
			return findBinary(a, l, s - 1, v);
		}
		//ni elementa v drevesu
		return -1;
	}

	static long timeLinear(int n){
		int [] a = generateTable(n);
		long startTime = System.nanoTime();
		for(int i = 0; i < 1000; i++){
			int x = (int)(Math.random() * n) + 1;
			findLinear(a, x);
		}
		long executionTime = System.nanoTime() - startTime;
		return executionTime/1000;
	}

	static long timeBinary(int n){
		int [] a = generateTable(n);
		long startTime = System.nanoTime();
		for(int i = 0; i < 1000; i++){
			int x = (int)(Math.random() * n) + 1;
			findBinary(a, 0, n-1, x);
		}
		long executionTime = System.nanoTime() - startTime;
		return executionTime/1000;
	}
}
