package com.github.yanaguseva.lsdsort;

import java.util.Arrays;
import java.util.Random;

public class LSDSort {
	private final static int BITS = 8;
	private final static int WORD = 32;
	private final static int MASK = (1 << BITS) - 1;
	private static final int MAX = 1000000;

	public static void main(final String[] args) {
		int[] data = generate();
		int[] cloneData = data.clone();

		System.out.println();
		long start = System.currentTimeMillis();
		Arrays.sort(data);
		long stop = System.currentTimeMillis();
		System.out.println("Method sort() elapsed = " + (stop - start));

		start = System.currentTimeMillis();
		lsdSort(cloneData);
		stop = System.currentTimeMillis();

		System.out.println();
		System.out.println("LSD sort elapsed = " + (stop - start));
	}

	
	private static int[] generate() {

		int[] data = new int[MAX];
		Random random = new Random();
		for (int i = 0; i < data.length; i++) {
			data[i] = 500 - random.nextInt(MAX);
		}
		return data;
	}


	private static void lsdSort(final int data[]) {
		int[] arr;

		for (int num = 0; num < WORD / BITS; num++) {
			arr = new int[data.length];
			int[] count = new int[MASK + 1];

			for (int i = 0; i < data.length; i++) {
				int index = (data[i] >> num * BITS) & MASK;
				count[index]++;
			}

			for (int j = 1; j < (MASK + 1); j++) {
				count[j] += count[j - 1];
			}

			if (num == (WORD / BITS - 1)) {
				int shift1 = count[MASK] - count[MASK / 2];
				int shift2 = count[MASK / 2];
				for (int r = 0; r < MASK / 2; r++)
					count[r] += shift1;
				for (int r = MASK / 2; r <= MASK; r++)
					count[r] -= shift2;
			}

			for (int k = MAX - 1; k >= 0; k--) {
				int index = (data[k] >> num * BITS) & MASK;
				arr[--count[index]] = data[k];
			}

			for (int i = 0; i < data.length; i++) {
				data[i] = arr[i];
			}
		}
	}
}
