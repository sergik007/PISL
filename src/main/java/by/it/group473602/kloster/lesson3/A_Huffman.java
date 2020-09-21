package by.it.group473602.kloster.lesson3;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;



public class A_Huffman {
    abstract class Node implements Comparable<Node> {
        private final int frequence; //частота символов
        abstract void fillCodes(String code);
        private Node(int frequence) {
            this.frequence = frequence;
        }
        @Override
        public int compareTo(Node o) {
            return Integer.compare(frequence, o.frequence);
        }
    }

    private class InternalNode extends Node {
        Node left;
        Node right;

        InternalNode(Node left, Node right) {
            super(left.frequence + right.frequence);
            this.left = left;
            this.right = right;
        }

        @Override
        void fillCodes(String code) {
            left.fillCodes(code + "0");
            right.fillCodes(code + "1");
        }

    }

    private class LeafNode extends Node {
        char symbol;

        LeafNode(int frequence, char symbol) {
            super(frequence);
            this.symbol = symbol;
        }

        @Override
        void fillCodes(String code) {
            codes.put(this.symbol, code);
        }
    }

    static private Map<Character, String> codes = new TreeMap<>();

    String encode(File file) throws FileNotFoundException {
        Scanner scanner = new Scanner(file);
        String s = scanner.next();
        Map<Character, Integer> count = countCharacterFrequency(s);
        PriorityQueue<Node> priorityQueue = buildTree(count);
        priorityQueue.peek().fillCodes(new String());
        return buildString(s);
    }

    private Map<Character, Integer> countCharacterFrequency(String string) {
        Map<Character, Integer> count = new HashMap<>();
        for(int i=0; i<string.length();i++) {
            Character key = string.charAt(i);
            if(count.containsKey(key))
                count.put(key, count.get(key)+1);
            else
                count.put(key, 1);
        }
        return count;
    }

    private PriorityQueue<Node> buildTree (Map<Character, Integer> count) {
        PriorityQueue<Node> priorityQueue = new PriorityQueue<>();
        Iterator iterator = count.entrySet().iterator();
        while(iterator.hasNext()) {
            Map.Entry<Character, Integer> entry = (Map.Entry<Character, Integer>) iterator.next();
            priorityQueue.add(new LeafNode(entry.getValue(), entry.getKey()));
        }
        while(priorityQueue.size()>1) {
            priorityQueue.add(new InternalNode(priorityQueue.poll(), priorityQueue.poll()));
        }
        return priorityQueue;
    }

    private String buildString (String string) {
        StringBuilder sb = new StringBuilder();
        for(int i=0; i<string.length();i++) {
            Character character = string.charAt(i);
            String code = codes.get(character);
            sb.append(code);
        }
        return sb.toString();
    }
    public static void main(String[] args) throws FileNotFoundException {
        String root = System.getProperty("user.dir") + "/src/";
        File f = new File(root + "/by/it/group473602/kloster/lesson3/dataHuffman.txt");
        A_Huffman instance = new A_Huffman();
        long startTime = System.currentTimeMillis();
        String result = instance.encode(f);
        long finishTime = System.currentTimeMillis();
        System.out.printf("%d %d\n", codes.size(), result.length());
        for (Map.Entry<Character, String> entry : codes.entrySet()) {
            System.out.printf("%s: %s\n", entry.getKey(), entry.getValue());
        }
        System.out.println(result);
    }

}