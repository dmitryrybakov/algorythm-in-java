package algorithmInJava;

public class differentSolutions {
	
	// The array is sorted in increasing way
	public static int countMultiplicativePairs(int[] A, int[] B){
		long scale = 1000000;
		int N = A.length;
		int count = 0;
		int left = 0;
		int right = N - 1;
		long C_left = 0;

		while(left < right){
			// To avoid rounding errors, use multiplication
			C_left = A[left] * scale + B[left];
			if(C_left >= scale) {
				if(C_left * (A[right] * scale + B[right]) >=
						(C_left + (A[right] * scale + B[right])) * scale) {
					// if C[left],C[right] is ok, that means that all elements C[left++],C[right] to satisfy condition
					count += right - left;
					right--;
				}
				else{
					left++;
				}
			}
			else{
				left++;
			}
		}
		//condition from codility.com test
		if(count > 1000000000) {
			return 1000000000;
		}
		else{
			return count;
		}
	}
	
	public static int countMultiplicativePairsV2(int[] C){
		int result = 0;
		int len = C.length;
		if(len > 1) {
			int lo_index = 0;
			int hi_index = len -1;
			
			// Skip all C[i] less than 1
			for(lo_index = 0; lo_index < len; lo_index++) {
				if(C[lo_index] > 1) break;
			}

			while(hi_index > lo_index){
				double v = C[hi_index] / (C[hi_index] - 1);
				while(lo_index < hi_index && C[lo_index] < v){
					lo_index++;
				}
				if(lo_index == hi_index) {
					break;
				}
				result += hi_index - lo_index;
				hi_index--;
			}
		}
		return result;
	}
	
	// The goal is to find the maximum number of blocks into which the array A can be divided.
	// each block should contain a peak.
	// The peak is defined as  index P such that 0 < P < N − 1, A[P − 1] < A[P] and A[P] > A[P + 1].
	public static int solutionPeaks(int[] A) {
		boolean[] B = new boolean[A.length];
		int peakSize = 0;
		for (int i = 1; i < A.length-1; i++) {
			if (A[i] > A[i-1] && A[i] > A[i+1]) {
				B[i] = true;
				peakSize++;
			}
		}
		int result = 0;
		for (int i = 1; i <= peakSize; i++) {
			if (A.length % i == 0) {
				boolean allHasPeak = true;
				for (int j = 0; j < i; j++) {
					boolean hasPeak = false;
					for (int k = 0; k < A.length / i; k++) {
						if (B[j * (A.length / i) + k]) {
							hasPeak = true;
							break;
						}
					}
					if (!hasPeak) {
						allHasPeak = false;
						break;
					}
				}
				if (allHasPeak) {
					result = i;
				}
			}
		}
		return result;
	}
}
