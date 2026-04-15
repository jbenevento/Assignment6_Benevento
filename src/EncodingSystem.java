public class EncodingSystem {
    public static void main(String[] args) {

        String[] texts = {
                "marcus fenix is a gear",
                "hello world",
                "computer science"
        };

        String[] encoded = new String[texts.length];

        Huffman huffman = new Huffman();

        for (int i = 0; i < texts.length; i++) {

            String text = texts[i];

            huffman.frequencyCount(text);
            huffman.buildHuffman();
            huffman.genCode();

            encoded[i] = huffman.encode(text);

            System.out.println("\n======================");
            System.out.println("TEXT " + (i + 1));
            System.out.println("======================");

            huffman.printStats(text);
        }
    }
}
/*
Sources:
ChatGPT for formatting output
 */