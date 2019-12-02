package Day1;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Consumer;
import java.util.stream.Stream;

public class Day1 {

	public static void main(String[] args) {
		String fileName = "inputDay1.txt";
		AtomicInteger total = new AtomicInteger(0);
		
		Consumer<String> func = (module) -> {
			total.addAndGet((int)(Math.floor(Integer.parseInt(module) / 3) - 2));
		};
		Wrapper<String> funcWrapper = new Wrapper<String>(func);
		
		try (Stream<String> stream = Files.lines(Paths.get(fileName))) {
			stream.forEach(funcWrapper.func);
		} catch (IOException e) {
			e.printStackTrace();
		}

		System.out.println("Output: " + total);
	}

}
