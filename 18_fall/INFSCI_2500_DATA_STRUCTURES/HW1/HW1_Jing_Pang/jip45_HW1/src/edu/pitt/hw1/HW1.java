package edu.pitt.hw1;

import java.util.ArrayList;

public class HW1 {

	public static int countPrimes(int n) {
		
		n = n-1;
		
		ArrayList<Integer> primes = new ArrayList<Integer>();
		
		if(n==2)
			return 1;
		
		primes.add(2);
		
		for(int i=3; i<=n; i++) {
			boolean isPrime=true;
			for(int p: primes) {
				int m = i%p;
				if (m==0) {
					isPrime = false;
					break;
				}
			}
			
			if(isPrime) {
				primes.add(i);
			}
		}
		
		return primes.size();
		
	}
	public static void main(String[] args) {
		
		for(int i = 10000; i<=100000; i+=10000) {
			
			long startTime = System.nanoTime();
			
			int countRow = countPrimes(i);
			
			long endTime = System.nanoTime();
			
			long timeSpentRow = endTime - startTime;
			
			System.out.println(i + " " + countRow + " " + timeSpentRow);
			
		}

	}

}
