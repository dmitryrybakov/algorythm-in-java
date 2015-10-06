package com.example.dmitry.test45;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

class LIST {
    LIST next;
    int value;

    static LIST insertionSort(LIST list) {
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
    static void mergeSort(int[] a, int[] aux, int lo, int hi) {
        if (hi <= lo) return;
        int mid = lo + (hi - lo) / 2;
        mergeSort(a, aux, lo, mid);
        mergeSort(a, aux, mid + 1, hi);
        merge(a, aux, lo, mid, hi);
    }

    static int partitioning(int[] a, int lo, int hi) {
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

    static void quickSort(int[] a, int lo, int hi) {
        if (lo < hi) {
            int p = partitioning(a, lo, hi);
            quickSort(a, lo, p - 1);
            quickSort(a, p + 1, hi);
        }
    }

    static void heapSort(int[] a) {
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

    static void siftDown(int[] a, int start, int end) {
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

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        LIST head = null;
//        for(int i = 10; i > 0; i--) {
//            LIST tmp = new LIST();
//            tmp.value = i;
//            tmp.next = head;
//            head = tmp;
//        }
//        LIST sorted = LIST.insertionSort(head);
//        if (sorted != null) {
//            String output = "";
//            for (int i = 0; i < 10; i++) {
//                output = output + sorted.value + ":";
//                sorted = sorted.next;
//            }
//            System.out.println(output);
//        }
        int A[] = new int[]{8, 9, 2, 4, 5, 1, 3, 7, 6};
//        int B[] = new int[A.length];
//        LIST.topDownMergeSort(A, B, A.length);
        //LIST.mergeSort(A, B, 0, A.length - 1);
        //LIST.quickSort(A, 0, A.length - 1);
        LIST.heapSort(A);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
