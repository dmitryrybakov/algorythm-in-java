package algorithmInJava;

class LIST {
    LIST next;
    int value;
};

public class sortingAlgorithm {

    public static LIST insertionSort(LIST list) {
        if (list == null || list.next == null)
            return list;
        LIST head = null;
        while(list != null) {
            LIST current = list;
            list = list.next;
            if (head == null || current.value < head.value) {
                current.next = head;
                head = current;
            }
            else {
                LIST p = head;
                while (p != null) {
                    if (p.next == null || current.value < p.next.value) {
                        current.next = p.next;
                        p.next = current;
                        break;
                    }
                    p = p.next;
                }
            }
        }

        return head;
    }

    // stably merge a[lo .. mid] with a[mid+1 ..hi] using aux[lo .. hi]
    private static void merge(int[] a, int[] aux, int lo, int mid, int hi) {
        // precondition: a[lo .. mid] and a[mid+1 .. hi] are sorted subarrays
        //assert isSorted(a, lo, mid);
        //assert isSorted(a, mid+1, hi);

        // copy to aux[]
        for (int k = lo; k <= hi; k++) {
            aux[k] = a[k];
        }

        // merge back to a[]
        int i = lo, j = mid+1;
        for (int k = lo; k <= hi; k++) {
            if      (i > mid)              a[k] = aux[j++];
            else if (j > hi)               a[k] = aux[i++];
            else if (aux[j] < aux[i]) a[k] = aux[j++];
            else                           a[k] = aux[i++];
        }

        // postcondition: a[lo .. hi] is sorted
        //assert isSorted(a, lo, hi);
    }

    // mergesort a[lo..hi] using auxiliary array aux[lo..hi]
    public static void mergeSort(int[] a, int[] aux, int lo, int hi) {
        if (hi <= lo) return;
        int mid = lo + (hi - lo) / 2;
        mergeSort(a, aux, lo, mid);
        mergeSort(a, aux, mid + 1, hi);
        merge(a, aux, lo, mid, hi);
    }

    public static int partitioning(int[] a, int lo, int hi) {
        int p = a[hi];
        int j = lo;
        for(int i = lo; i <= hi - 1; i++){
            if (a[i] <= p) {
                int b = a[i];
                a[i] = a[j];
                a[j] = b;
                j++;
            }
        }

        int b = a[hi];
        a[hi] = a[j];
        a[j] = b;

        return j;
    }

    public static void quickSort(int[] a, int lo, int hi) {
        if (lo < hi) {
            int p = partitioning(a, lo, hi);
            quickSort(a, lo, p - 1);
            quickSort(a, p + 1, hi);
        }
    }

    public static void heapSort(int[] a) {
        int tmp;
        for(int i = a.length/2 - 1; i >= 0; i--) {
            siftDown(a, i, a.length);
        }
        for(int i = a.length - 1; i >= 1; i--) {
            tmp = a[0];
            a[0] = a[i];
            a[i] = tmp;
            siftDown(a, 0, i);
        }
    }

    public static void siftDown(int[] a, int start, int end) {
        boolean done = false;
        int maxChild;
        int tmp;

        while((start * 2 + 1) < end && !done) {
            if (start * 2 + 1 == end - 1) {
                maxChild = start * 2 + 1;
            }
            else if (a[start * 2 + 1] > a[start * 2 + 2]) {
                maxChild = a[start * 2 + 1];
            }
            else {
                maxChild = a[start * 2 + 2];
            }

            if (a[start] < a[maxChild]) {
                tmp = a[start];
                a[start] = a[maxChild];
                a[maxChild] = tmp;
                start = maxChild;
            }
            else {
                done = true;
            }
        }
    }
};
