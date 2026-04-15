import java.util.*;

public class Huffman {

    public class Node implements Comparable<Node> {
        char ch;
        int freq;
        Node left, right;

        Node(char ch, int freq) {
            this.ch = ch;
            this.freq = freq;
        }

        Node(int freq, Node left, Node right) {
            this.ch = '\0';
            this.freq = freq;
            this.left = left;
            this.right = right;
        }

        public int compareTo(Node other) {
            return this.freq - other.freq;
        }

    }

    // Character frequency table
    private static Map<Character, Integer> freqMap = new HashMap<>();

    // Character to code
    private static Map<Character, String> codeMap = new HashMap<>();

    private static Node root;


    public static void frequencyCount(String text) {
        freqMap.clear(); // Clear the map

        for (int i = 0; i < text.length(); i++) {
            char c = text.charAt(i);

            //Check if the character already exists
            if (freqMap.containsKey(c)) { // Already in the map
                freqMap.put(c, freqMap.get(c) + 1);
            } else { // Not in the map, add it
                freqMap.put(c, 1);
            }
        }
    }

    public void buildHuffman() {
        PriorityQueue<Node> minHeap = new PriorityQueue<>();

        // Turn characters into heap nodes
        for (char c : freqMap.keySet()) {
            minHeap.add(new Node(c, freqMap.get(c)));
        }

        // Build Huffman tree
        while (minHeap.size() > 1) {
            Node left = minHeap.poll();
            Node right = minHeap.poll();

            Node parent = new Node((left.freq) + right.freq, left, right);
            minHeap.add(parent);
        }

        root = minHeap.poll();
    }

    public static void genCode() {
        codeMap.clear();
        buildCodes(root, "");
    }

    // Assign each character a binary code by traversing the Huffman tree
    private static void buildCodes(Node node, String code) {
        if (node == null) return;

        //Leaf node
        if (node.left == null && node.right == null) {
            codeMap.put(node.ch, code);
            return;
        }

        buildCodes(node.left, code + "0"); // Go left, add "0"
        buildCodes(node.right, code + "1"); // Go right, add "0"
    }

    public static String encode(String text) {
        StringBuilder encodedText = new StringBuilder();

        for (char c : text.toCharArray()) {
            encodedText.append(codeMap.get(c));
        }

        return encodedText.toString();
    }

    public void printStats(String text) {

        System.out.println("Stats for \"" + text + "\"");

        List<Character> freqKeys = new ArrayList<>(freqMap.keySet());
        Collections.sort(freqKeys, (a, b) -> {
            if (a == ' ') return -1; //if a is a space, it goes before
            if (b == ' ') return 1; // if b is a space, a comes after
            return Character.compare(a, b); // default case
        });

        for (char c : freqKeys) {
            if (c == ' ') {
                System.out.println("(space) : " + freqMap.get(c));
            } else {
                System.out.println(c + " : " + freqMap.get(c));
            }
        }

        System.out.println("\nHuffman Codes:");

        List<Character> codeKeys = new ArrayList<>(codeMap.keySet());

        Collections.sort(codeKeys, (a, b) -> {
            if (a == ' ') return -1;
            if (b == ' ') return 1;
            return Character.compare(a, b);
        });

        for (char c : codeKeys) {
            if (c == ' ') {
                System.out.println("(space): " + codeMap.get(c));
            } else {
                System.out.println(c + ": " + codeMap.get(c));
            }
        }

        System.out.println("\nEncoded:");
        System.out.println(encode(text));
    }
}

/*
Sources:
https://www.geeksforgeeks.org/java/huffman-coding-java/
https://algs4.cs.princeton.edu/55compression/Huffman.java.html
https://liveexample.pearsoncmg.com/html/HuffmanCode.html
https://medium.com/@AlexanderObregon/building-huffman-coding-for-data-compression-in-java-a9e3f239e162
https://docs.oracle.com/javase/8/docs/api/java/util/HashMap.html#HashMap--
https://docs.oracle.com/javase/8/docs/api/java/util/PriorityQueue.html
https://www.geeksforgeeks.org/java/priority-queue-in-java/
https://cs.lmu.edu/~ray/notes/pqueues/
https://docs.oracle.com/javase/8/docs/api/java/lang/StringBuilder.html
https://www.geeksforgeeks.org/java/stringbuilder-class-in-java-with-examples/
https://leetcode.com/discuss/post/5498326/java-time-complexities-of-all-built-in-m-gcp1/
https://www.geeksforgeeks.org/java/collections-sort-java-examples/
https://docs.oracle.com/javase/8/docs/api/java/util/Collections.html
https://www.w3schools.com/java/java_sort_list.asp
ChatGPT
 */
