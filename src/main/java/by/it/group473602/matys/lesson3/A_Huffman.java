package by.it.group473602.matys.lesson3;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

//Lesson 3. A_Huffman.
//Разработайте метод encode(File file) для кодирования строки (код Хаффмана)

// По данным файла (непустой строке ss длины не более 104104),
// состоящей из строчных букв латинского алфавита,
// постройте оптимальный по суммарной длине беспрефиксный код.

// Используйте Алгоритм Хаффмана — жадный алгоритм оптимального
// безпрефиксного кодирования алфавита с минимальной избыточностью.

// В первой строке выведите количество различных букв kk,
// встречающихся в строке, и размер получившейся закодированной строки.
// В следующих kk строках запишите коды букв в формате "letter: code".
// В последней строке выведите закодированную строку. Примеры ниже

//        Sample Input 1:
//        a
//
//        Sample Output 1:
//        1 1
//        a: 0
//        0

//        Sample Input 2:
//        abacabad
//
//        Sample Output 2:
//        4 14
//        a: 0
//        b: 10
//        c: 110
//        d: 111
//        01001100100111

public class A_Huffman {

    abstract class Node implements Comparable<Node> {

	private final int frequence; // частота символов

	// генерация кодов (вызывается на корневом узле
	// один раз в конце, т.е. после построения дерева)
	abstract void fillCodes(String code);

	// конструктор по умолчанию
	private Node(int frequence) {
	    this.frequence = frequence;
	}

	// метод нужен для корректной работы узла в приоритетной очереди
	// или для сортировок
	@Override
	public int compareTo(Node o) {
	    return Integer.compare(frequence, o.frequence);
	}
    }

    ////////////////////////////////////////////////////////////////////////////////////
    // расширение базового класса до внутреннего узла дерева
    private class InternalNode extends Node {
	// внутренный узел дерева
	Node left; // левый ребенок бинарного дерева
	Node right; // правый ребенок бинарного дерева

	// для этого дерева не существует внутренних узлов без обоих детей
	// поэтому вот такого конструктора будет достаточно
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

    ////////////////////////////////////////////////////////////////////////////////////
    // расширение базового класса до листа дерева
    private class LeafNode extends Node {
	// лист
	char symbol; // символы хранятся только в листах

	LeafNode(int frequence, char symbol) {
	    super(frequence);
	    this.symbol = symbol;
	}

	@Override
	void fillCodes(String code) {
	    // добрались до листа, значит рекурсия закончена, код уже готов
	    // и можно запомнить его в индексе для поиска кода по символу.
	    codes.put(this.symbol, code);
	}
    }

    // индекс данных из листьев
    static private Map<Character, String> codes = new TreeMap<>();

    String encode(File file) throws FileNotFoundException {

	// Read the input string
	Scanner scanner = new Scanner(file);
	String inputString = scanner.next();
	scanner.close();

	Map<Character, Integer> letterFrequency = new HashMap<>();

	// Count the frequency of every letter in string
	countLettersFrequency(inputString, letterFrequency);

	// Move letters and frequency to the PriorityQueue
	PriorityQueue<Node> priorityQueue = new PriorityQueue<>();
	for (Map.Entry<Character, Integer> entry : letterFrequency.entrySet()) {
	    priorityQueue.add(new LeafNode(entry.getValue(), entry.getKey()));
	}
	// Build a tree
	while (priorityQueue.size() > 1) {
	    priorityQueue.add(new InternalNode(priorityQueue.poll(), priorityQueue.poll()));
	}

	// Get a root and fill codes of letters
	priorityQueue.peek().fillCodes(new String());

	
	return buildEncodedString(inputString);

    }

    private static void countLettersFrequency(String inputString, Map<Character, Integer> letterFrequency) {
	for (int i = 0; i < inputString.length(); i++) {
	    Character key = inputString.charAt(i);
	    if (letterFrequency.containsKey(key)) {
		letterFrequency.put(key, letterFrequency.get(key) + 1);
	    } else {
		letterFrequency.put(key, 1);
	    }
	}
    }

    private static String buildEncodedString(String inputString){
	StringBuilder stringBuilder = new StringBuilder();
	for (int i = 0; i < inputString.length(); i++) {
	    Character letter = inputString.charAt(i);
	    String code = codes.get(letter);
	    stringBuilder.append(code);
	}
	return stringBuilder.toString();
    }


    public static void main(String[] args) throws FileNotFoundException {
	String root = System.getProperty("user.dir") + "/src/";
	File f = new File(root + "/by/it/group473602/matys/lesson3/dataHuffman.txt");
	A_Huffman instance = new A_Huffman();
	long startTime = System.currentTimeMillis();
	String result = instance.encode(f);
	long finishTime = System.currentTimeMillis();
	System.out.printf("Time: %d\n", finishTime-startTime);
	System.out.printf("%d %d\n", codes.size(), result.length());
	for (Map.Entry<Character, String> entry : codes.entrySet()) {
	    System.out.printf("%s: %s\n", entry.getKey(), entry.getValue());
	}
	System.out.println(result);
    }

}
