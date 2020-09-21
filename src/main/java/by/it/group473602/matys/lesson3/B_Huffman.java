package by.it.group473602.matys.lesson3;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;
import java.util.regex.Pattern;

// Lesson 3. B_Huffman.
// Восстановите строку по её коду и беспрефиксному коду символов.

// В первой строке входного файла заданы два целых числа
// kk и ll через пробел — количество различных букв, встречающихся в строке,
// и размер получившейся закодированной строки, соответственно.
//
// В следующих kk строках записаны коды букв в формате "letter: code".
// Ни один код не является префиксом другого.
// Буквы могут быть перечислены в любом порядке.
// В качестве букв могут встречаться лишь строчные буквы латинского алфавита;
// каждая из этих букв встречается в строке хотя бы один раз.
// Наконец, в последней строке записана закодированная строка.
// Исходная строка и коды всех букв непусты.
// Заданный код таков, что закодированная строка имеет минимальный возможный размер.
//
//        Sample Input 1:
//        1 1
//        a: 0
//        0

//        Sample Output 1:
//        a

//        Sample Input 2:
//        4 14
//        a: 0
//        b: 10
//        c: 110
//        d: 111
//        01001100100111

//        Sample Output 2:
//        abacabad

public class B_Huffman {

    String decode(File file) throws FileNotFoundException {

	// прочитаем строку для кодирования из тестового файла
	Scanner scanner = new Scanner(file);
	@SuppressWarnings("unused")
	Integer count = scanner.nextInt();
	Integer length = scanner.nextInt();

	Map<String, Character> codes = new TreeMap<>();

	Pattern pattern = Pattern.compile("[a-zA-Z]{1}:[0-1]{1,}");

	while (scanner.hasNext(pattern)) {
	    String line = scanner.next();
	    String key = line.substring(2);
	    Character value = line.charAt(0);
	    codes.put(key, value);
	}
	String inputString = scanner.next();

	scanner.close();

	StringBuilder result = new StringBuilder();
	StringBuilder currentCode = new StringBuilder();

	for (int i = 0; i < length; i++) {
	    currentCode.append(inputString.charAt(i));
	    if (inputString.charAt(i) == '0') {
		result.append(codes.get(currentCode.toString()));
		currentCode = new StringBuilder();
	    }
	}
	result.append(codes.get(currentCode.toString()));

	return result.toString(); // 01001100100111
    }

    public static void main(String[] args) throws FileNotFoundException {
	String root = System.getProperty("user.dir") + "/src/";
	File f = new File(root + "by/it/group473602/matys/lesson3/encodeHuffman.txt");
	B_Huffman instance = new B_Huffman();
	String result = instance.decode(f);
	System.out.println(result);
    }

}
