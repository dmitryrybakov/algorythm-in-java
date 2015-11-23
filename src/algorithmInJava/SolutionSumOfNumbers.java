package algorithmInJava;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class SolutionSumOfNumbers {
    public static void entryPoint(String args[] ) throws Exception {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT */
        int result = 0;
    	Scanner sc = new Scanner(System.in);
        int number = sc.nextInt();
        int N = sc.nextInt();
        List<Integer> a = new ArrayList<Integer>();
        for(int i = 0; i < N && sc.hasNextInt(); i++) {
        	a.add(new Integer(sc.nextInt()));
        }
        sc.close();
        Collections.sort(a);
        int startIndex = a.size() - 1;
        for(int i = a.size() - 1; i >= 0; i--) {
        	if (a.get(i) <= number) {
        		startIndex = i;
        		break;
        	}
        }
        for(int i = startIndex; i >= 0; i--) {
        	
        	if (a.get(i) < number / 2) break;
        	
        	int secondSummand = number - a.get(i);
        	int sIdx = 0;
        	int eIdx = i - 1;
        	int foundIdx = -1;
        	while(sIdx <= eIdx) {
        		int mid = sIdx + (eIdx - sIdx) / 2;
        		if(a.get(mid) == secondSummand) {
        			foundIdx = mid;
        			break;
        		}
        		else if (a.get(mid) < secondSummand) {
        			sIdx = mid + 1;
        		}
        		else {
        			eIdx = mid - 1;
        		}
        	}
        	if (foundIdx >= 0) {
        		result++;
        		//System.out.println(a.get(i) + "+" + a.get(foundIdx) + "=" + number);
        	}
        }
        System.out.println(result);
    }
}
