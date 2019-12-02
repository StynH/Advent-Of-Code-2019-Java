package Day1;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Stream;

public class Day1Part2 {

	public static int recursiveTotal(String module, int total) {
		int conv = Integer.parseInt(module);
		int product = (int)(Math.floor(conv / 3.0) - 2);
		if(product <= 0) return total;
		total += product + recursiveTotal(String.valueOf(product), total);
		return total;
	}
	
	public static void main(String[] args) {
		String fileName = "inputDay1.txt";
		AtomicInteger total = new AtomicInteger(0);
		
		try (Stream<String> stream = Files.lines(Paths.get(fileName))) {
			stream.forEach((module) -> {
				total.addAndGet(recursiveTotal(module, 0));
			});
		} catch (IOException e) {
			e.printStackTrace();
		}

		System.out.println("Output: " + total);
	}

}
