package solution;

import java.util.Hashtable;
import java.util.Iterator;
import java.util.Map;

/**
 * DONE
 * */

public class TwoStrings {

    private static Hashtable<String, Integer> getCountChars(String s) {
        Hashtable<String, Integer> table = new Hashtable<>();
        for (int i = 0; i < s.length() ; i++) {
            String c = String.valueOf(s.charAt(i));
            if (table.containsKey(c)) {
                table.put(c, table.get(c) + 1);
            }
            else {
                table.put(c, 1);
            }
        }
        return table;
    }

    // Complete the twoStrings function below.
    static String twoStrings(String s1, String s2) {
        Hashtable<String, Integer> table1 = getCountChars(s1);
        Hashtable<String, Integer> table2 = getCountChars(s2);
        Iterator<Map.Entry<String, Integer>> it = table1.entrySet().iterator();
        boolean flag = false;
        while (it.hasNext()) {
            Map.Entry<String, Integer> pair = it.next();
            if (table2.containsKey(pair.getKey())) {
                flag = true;
                break;
            }
        }
        return flag ? "Yes" : "No";
    }

    private static void test() {
        String [][] table = {
            {"hello", "world"}
            ,{"hi", "world"}
        };
        for (int i = 0; i <2 ; i++) {
            System.out.println(twoStrings(table[i][0], table[i][1]));;
        }

    }

    public static void main(String[] args) {
        test();
    }
}
