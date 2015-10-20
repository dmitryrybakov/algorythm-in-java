package algorithmInJava;

public class slicingAlgorithm {
    public static int[] findLargestIncreasingSequence(int[] x) {
        int p[] = new int[x.length];
        int m[] = new int[x.length + 1];
        int L = 0;
        for(int i = 0; i < x.length; i++) {
            // Binary search for the largest positive j ≤ L
            // such that x[m[j]] < x[i]
            int lo = 1;
            int hi = L;
            while (lo <= hi) {
                int mid = lo +  (hi - lo) / 2;
                if (x[m[mid]] < x[i]) {
                    lo = mid + 1;
                }
                else {
                    hi = mid - 1;
                }
            }
            // The predecessor of X[i] is the last index of
            // the subsequence of length newL-1
            int newL = lo;
            p[i] = m[newL - 1];
            m[newL] = i;
            // If we found a subsequence longer than any we've
            // found yet, update L
            if (newL > L) {
                L = newL;
            }
        }
        int s[] = new int[L];
        int k = m[L];
        for(int i = L-1; i >= 0; i--) {
            s[i] = x[k];
            k = p[k];
        }
        return s;
    }
    
    public static int minAvgTwoSlice(int[] A) {
    	int minpos = 0, minavg = (A[0] + A[1]) / 2;
    	int N = A.length, N1 = N - 1, N2 = N - 2;
    	int sumTwo, t, i;

    	for (i = 0; i < N2; i += 1) {
    		sumTwo = A[i] + A[i + 1];
    		t = sumTwo / 2;
    		if (minavg > t) {
    			minavg = t;
    			minpos = i;
    		}
    		t = (sumTwo + A[i + 2]) / 3;
    		if (minavg > t) {
    			minavg = t;
    			minpos = i;
    		}
    	}

    	t = (A[N2] + A[N1]) / 2;

    	if (minavg > t) {
    		minavg = t;
    		minpos = N2;
    	}

    	return minpos;
    }
    
    public static int maxSliceSum(int[] A) {
        int maxEndingHere = A[0];
        int maxSoFar = A[0];
        for(int i = 1; i < A.length; i++){
            maxEndingHere = Math.max(A[i], maxEndingHere + A[i]);
            maxSoFar = Math.max(maxSoFar, maxEndingHere);
        }
        return maxSoFar;
    }
    
    public static int minAbsSlice(int[] A, int[] N) {
        int minSum = Integer.MAX_VALUE;
        for(int startIndex = 0; startIndex < N.length; startIndex++) {
            for(int endIndex = startIndex; endIndex < N.length; endIndex++) {
                int sum = 0;
                for(int i = startIndex; i <= endIndex; i++) {
                    sum += A[i];
                }
                sum = Math.abs(sum);
                if (sum < minSum) {
                    minSum = sum;
                }
            }
        }
        return minSum;
    }
}
