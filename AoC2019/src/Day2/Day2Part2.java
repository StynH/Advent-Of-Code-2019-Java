package Day2;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

public class Day2Part2 {

	static final int TARGET = 19690720;
	
	public static void main(String[] args) {
		String fileName = "inputDay2.txt";
		Integer[] originalCodes = null;
		
		try (Stream<String> stream = Files.lines(Paths.get(fileName))) {
			String[] temp = stream.toArray(String[]::new)[0].split(",");
			originalCodes = new Integer[temp.length];

			for(int i = 0; i < temp.length; ++i) {
				originalCodes[i] = Integer.parseInt(temp[i]);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		boolean found = false;
		for(int a = 0; a <= 99; ++a) {
			for(int b = 0; b <= 99; ++b) {
				Integer[] optcodes = new Integer[originalCodes.length];
				System.arraycopy(originalCodes, 0, optcodes, 0, originalCodes.length);
				
				optcodes[1] = a;
				optcodes[2] = b;
				
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
						if(optcodes[0] == TARGET) {
							System.out.println("100 x " + a + "(noun) + " + b + "(verb) = " + TARGET);
							found = true;
						}
						break;
					}
				}
				
				if(found) {
					break;
				}
			}
		}
	}

}
