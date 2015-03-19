package com.github.yanaguseva.flippingbits;

import java.util.Scanner;

public class Solution {
	private static final int BIT_PER_BYTE = 8;
	private static final int INTEGER_SIZE = 4; // bytes
	
	public static void main(final String[] args) {
		Scanner scanner = new Scanner(System.in);
		int count = scanner.nextInt();
		
		long [] intArr = new long [count];
		
		for (int i = 0; i < count; i++) {
			intArr[i] = scanner.nextLong();
		}

		for (int i = 0; i < count; i++) {
			intArr[i] = inverseBits(intArr[i]);
			System.out.println(intArr[i] + " ");
		}
		
		scanner.close();
	}
	
	
	private static long inverseBits(long number) {
		for (int i = 0; i < BIT_PER_BYTE*INTEGER_SIZE; i++) {
			if ( ((1L<<i)&number) == 0) {
				number = ((1L<<i) | number);
			} else {
				number = (Long.MAX_VALUE - (1L<<i)) & number;
			}
		}
		
		return number;
	}
	

	private static void printBits(final long number) {
		for (long i = 2*BIT_PER_BYTE*INTEGER_SIZE-1; i >= 0; i--) {
			if ( ((1L<<i) & number) == 0)
				System.out.print(0);
			else
				System.out.print(1);
		}
		System.out.println();
	}
}
