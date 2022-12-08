import pkg.*;
import java.util.*;
import java.time.*;
import java.lang.*;


class main {
	static final long createdNano = System.nanoTime();

	public static void main(String args[]) {
		long start = System.nanoTime();
		long finish = System.nanoTime();
		System.out.println("Created: " + createdNano);
		
		int [] times = {10, 100, 1000, 10000, 100000, 1000000, 10000000};
		int [] nums = new int[100];


		System.out.println("-------------------Test-------------------");
		System.out.println("");
		for(int i : times){
			System.out.println("Interval: " + i);
			
			nums = new int[i];
			start = System.nanoTime();

			//  Put your method between these two comments
			randomize(nums);
			search(nums);
			bubble(nums);
			insertion(nums, nums.length);
			selectionSort(nums, nums.length);
			//

			finish = System.nanoTime();
			System.out.println("Started: " + start);
			System.out.println("Finished: " + finish);
			System.out.println("Duration: " + (finish-start));
			System.out.println("");
		}
	}

	public static void randomize(int[] nums) {
		for (int j=0; j<nums.length; j++) {
			nums[j] = (int) (Math.random()*200001);
		}
	}

	public static Boolean search(int[] nums) {
		int randNum = (int) (Math.random()*200001);
		//int in = (int) (Math.random()*nums.length);
		for (int j=0; j<nums.length; j++) {
			if (nums[j] == randNum)
				return true;
		}
		return false;
	}

	public static void bubble(int[] nums) {
		int corrections = 0;
		while (true) {
			corrections = 0;
			for (int i=0; i<nums.length-1; i++) {
				if (nums[i] > nums[i+1]) {
					int temp = nums[i+1];
					nums[i+1] = nums[i];
					nums[i] = temp;
					corrections++;
				}
			}
			if (corrections == 0) {
				break;
			}
		}
	}

	public static void insertion(int[] arr, int n) {
		int i, key, j;
		for (i=0; i<n; i++) {
			key = arr[i];
			j=i-1;
			while (j >=0 && arr[j] > key) {
				arr[j+1] = arr[j];
				j=j-1;
			}
			arr[j+1]=key;
		}
	}
	public static void selectionSort(int[] arr, int n) {
		int i, j, min_idx;
		for (i=0; i<n-1; i++) {
			min_idx = i;
			for(j=i+1; j<n; j++)
				if(arr[j] < arr[min_idx])
				min_idx = j;
			
			int temp = arr[min_idx];
			arr[min_idx] = arr[i];
			arr[i] = arr[min_idx];
		}
	} 
		
}
