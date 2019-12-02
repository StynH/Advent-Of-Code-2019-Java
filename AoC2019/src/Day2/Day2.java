package Day2;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

public class Day2 {

	public static void main(String[] args) {
		String fileName = "inputDay2.txt";
		Integer[] optcodes = null;
		
		try (Stream<String> stream = Files.lines(Paths.get(fileName))) {
			String[] temp = stream.toArray(String[]::new)[0].split(",");
			optcodes = new Integer[temp.length];

			for(int i = 0; i < temp.length; ++i) {
				optcodes[i] = Integer.parseInt(temp[i]);
			}
			
			optcodes[1] = 12;
			optcodes[2] = 2;
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		boolean done = false;
		for(int i = 0; i < optcodes.length; i += 4) {
			switch(optcodes[i]) {
			case 1:
				optcodes[optcodes[i + 3]] = optcodes[optcodes[i + 1]] + optcodes[optcodes[i + 2]];
				break;
			case 2:
				optcodes[optcodes[i + 3]] = optcodes[optcodes[i + 1]] * optcodes[optcodes[i + 2]];
				break;
			case 99:
				done = true;
				break;
			}
			
			if(done) {
				break;
			}
		}
		
		System.out.println("Output: " + optcodes[0]);
	}

}
