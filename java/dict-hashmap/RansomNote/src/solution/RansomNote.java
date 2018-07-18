package solution;

import java.util.Hashtable;
import java.util.Iterator;
import java.util.Map;

/**
 * https://www.hackerrank.com/challenges/ctci-ransom-note/problem?h_l=interview&playlist_slugs%5B%5D%5B%5D=interview-preparation-kit&playlist_slugs%5B%5D%5B%5D=dictionaries-hashmaps
 * DONE
 * */

public class RansomNote {


    private static Hashtable<String, Integer> getWordTable(String [] words) {
        Hashtable<String, Integer> table = new Hashtable<>();
        for (String s : words) {
            table.put(s, table.containsKey(s) ? table.get(s) + 1 : 1);
        }
        return table;
    }

    // Complete the checkMagazine function below.
    static void checkMagazine(String[] magazine, String[] note) {
        Hashtable<String, Integer> wordInMagazine = getWordTable(magazine);
        Hashtable<String, Integer> wordsInNote = getWordTable(note);
        Iterator<Map.Entry<String, Integer>> itInWords =
                wordsInNote.entrySet().iterator();
        boolean flag = true;
        while (itInWords.hasNext()) {
            Map.Entry<String, Integer> pair = itInWords.next();
            if ( wordInMagazine.containsKey(pair.getKey()) ) {
                if (wordInMagazine.get(pair.getKey()) < pair.getValue()) {
                    flag = false;
                    break;
                }
            }
            else {
                flag = false;
                break;
            }
        }
        System.out.println(flag ? "Yes" : "No");
    }


    private static void test() {
        String [][] tableWordsOfNote = {
            {"give", "one", "grand", "today"}
            ,{"two", "times", "two", "is", "four"}
            ,{"ive", "got", "some", "coconuts"}
        };

        String [][] tableWordsInMagazines = {
            {"give", "me", "one", "grand", "today", "night"}
            ,{"two", "times", "three", "is", "not", "four"}
            ,{"ive", "got", "a", "lovely", "bunch", "of", "coconuts"}
        };

        for (int i = 0; i <3 ; i++) {
            checkMagazine(tableWordsInMagazines[i], tableWordsOfNote[i]);
        }
    }

    public static void main(String[] args) {
        test();
    }

}
