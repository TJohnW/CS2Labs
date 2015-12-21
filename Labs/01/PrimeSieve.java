import java.util.Scanner;
import java.util.ArrayList;

public class PrimeSieve {
	public static void main(String[] args) {
		System.out.print("Compute prime numbers from 2 to: ");
		Scanner in = new Scanner(System.in);
		int N = in.nextInt();
		in.close();
		if( N < 2 ) {
			System.out.println("N must be greater than or equal to 2.");
		} else {
			ArrayList<Integer> primes = findPrimes(N);
		
			// display output
			System.out.print("Prime numbers: ");
			for (Integer i : primes) {
				System.out.print(i + " ");
			}
		}
	}
	
	public static ArrayList<Integer> findPrimes(int N) {
		ArrayList<Integer> primes = new ArrayList<Integer>();
		ArrayList<Integer> values = new ArrayList<Integer>();
		for(int i = 2; i <= N; i++) {
			values.add(i);
		}
		while(values.size() != 0) {
			int prime = values.get(0);
			primes.add(prime);
			values.remove(0);
			ArrayList<Integer> nonMultiples = new ArrayList<Integer>();
			for(Integer v : values) {
				if(v % prime != 0) {
					nonMultiples.add(v);
				}
			}
			values = nonMultiples;
		}
		return primes;
	}
 }
