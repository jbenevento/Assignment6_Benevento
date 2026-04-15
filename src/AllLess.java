import java.util.*;

public class AllLess {
    public static void main(String[] args) {
        String[] string = {
                "zero", " size", "nutella", "jojo", "luna",
                "isse", "astor", "as", "entretien","", "cal"
        };

        int x = 3;

        List<String> result = findAllLess(string, x);
        System.out.println(result);
    }

    public static List<String> findAllLess(String[] string, int x) {
        List<String> result = new ArrayList<>();

        // If array is empty, return empty list
        if(string == null) return result;

        for(String s : string) {

            // Skip if null or empty
            if(s == null) continue;

            // Add string to array if not empty and less than x
            if (!s.equals("") && s.length() < x) {
                result.add(s);
            }
        }

        return result;

    }

}

/*
Sources:
https://www.geeksforgeeks.org/dsa/binary-heap/
https://docs.oracle.com/javase/8/docs/api/java/lang/String.html
 */