package algorithmInJava;

public class differentSolutions {
	
	//  A[0] = 0    B[0] = 500,000
	//  A[1] = 1    B[1] = 500,000
	//  A[2] = 2    B[2] = 0
	//  A[3] = 2    B[3] = 0
	//  A[4] = 3    B[4] = 0
	//  A[5] = 5    B[5] = 20,000
	
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
	
	
	// 1. If 0 <= C[a] < 1, then (a,b) is multiplicative only if C[a] = 0
	// 2. If 1 < C[a[] < 2, then C[a] / (C[a] - 1) > 2, therefore C[b] > 2. So, C[b] > C[a], but this is impossible
	// because the array is sorted
	// 3. If C[a] > 2 then the pair is multiplicative for any C[b] where C[b] >= C[a] / (C[a] - 1)
	public static int countMultiplicativePairsV2(int[] A, int[] B){
		int result = 0;
		int len = A.length;
		long scale = 1000000;
		if(len > 1) {
			int lo_index = 0;
			int hi_index = len -1;
			
			// Skip all C[i] less than 1
			// lo_index corresponds to b and hi_index corresponds to a
			for(lo_index = 0; lo_index < len; lo_index++) {
				if(A[lo_index] * scale + B[lo_index] > scale) break;
			}

			while(hi_index > lo_index){
				double v = A[hi_index] * scale + B[hi_index] / (A[hi_index]*scale + B[hi_index] - scale);
				while(lo_index < hi_index && (A[lo_index]*scale + B[lo_index]) < v*scale){
					lo_index++;
				}
				if(lo_index == hi_index) {
					break;
				}
				result += hi_index - lo_index;
				hi_index--;
			}
		}
		
		if(result > 1000000000) {
			return 1000000000;
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
