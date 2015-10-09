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

}
