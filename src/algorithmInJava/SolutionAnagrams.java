package algorithmInJava;

import java.util.*;

//-------------Input
//9
//pear
//amleth
//reap
//dormitory
//tinsel
//dirty room
//hamlet
//listen
//silent
//
//-------------Output
//amleth,hamlet
//dirty room,dormitory
//listen,silent,tinsel
//pear,reap

public class SolutionAnagrams {
    private static List<Integer> primes = new ArrayList<Integer>();
    
    public static class ArrayStringComparator implements Comparator<List<String>> {
        @Override
        public int compare(List<String> a, List<String> b) {
            return a.get(0).compareTo(b.get(0));
        }
    }
    
    public static long hashForAnagram(String s) {
    	String ts = s.replaceAll("\\s+","");
        char sa[] = ts.toCharArray();
        long hashCode = 1;
        for(int i = 0; i < sa.length; i++) {
            hashCode *= primes.get(sa[i] - 'A');
        }
        return hashCode;
    }
    
    public static void generatePrimes(int length) {
        int i = 2;
        while(primes.size() < length ) {
            boolean isPrime = true;
            for(int k = 2; k < i; k++) {
                if ( i % k == 0) {
                    isPrime = false;
                    break;
                }
            }
            if (isPrime) {
                primes.add(new Integer(i));
            }
            i++;
        }
    }
    public static void entryPoint(String args[] ) throws Exception {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT */
        Scanner sc = new Scanner(System.in);
        List<String> ar = new ArrayList<String>();
        int N = sc.nextInt();
        sc.nextLine();
        for(int i = 0; i < N && sc.hasNextLine(); i++) {
            String s = sc.nextLine();
            ar.add(s);
        }
        sc.close();
        generatePrimes(255);
        Map<Long, List<String>> hm = new HashMap<Long, List<String>>();
        for(String s : ar) {
            Long hashCode = new Long(hashForAnagram(s));
            if (!hm.containsKey(hashCode)) {
            	List<String> a = new ArrayList<String>();
            	a.add(s);
            	hm.put(hashCode, a);
            }
            else {
            	List<String> a = hm.get(hashCode);
            	a.add(s);
            }
        }
        List<List<String>> result = new ArrayList<List <String>>();
        for(List<String> a : hm.values()) {
        	Collections.sort(a);
        	result.add(a);
        }
        Collections.sort(result, new ArrayStringComparator());
        for(List<String> a: result) {
        	String r = "";
        	for(String s: a) {
        		r = r + s + ",";
        	}
        	System.out.println(r.substring(0, r.length() - 1));
        }
    }
}
