package Day5;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

public class Day5Part2 {

	static final int INPUT = 5;
	
	static final int OPT_ADD = 1;
	static final int OPT_MUL = 2;
	static final int OPT_IN = 3;
	static final int OPT_OUT = 4;
	static final int OPT_JIT = 5;
	static final int OPT_JIF = 6;
	static final int OPT_CLT = 7;
	static final int OPT_CEQ = 8;
	static final int OPT_END = 99;
	
	public static void main(String[] args) {
		String fileName = "inputDay5.txt";
		Integer[] optcodes = null;
		
		try (Stream<String> stream = Files.lines(Paths.get(fileName))) {
			String[] temp = stream.toArray(String[]::new)[0].split(",");
			optcodes = new Integer[temp.length];

			for(int i = 0; i < temp.length; ++i) {
				optcodes[i] = Integer.parseInt(temp[i]);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	
		int stepSize = 4;
		int output = -1;
		boolean done = false;
		for(int i = 0; i < optcodes.length; i += stepSize) {
			OptContainer opt = new OptContainer(optcodes[i]);
			
			switch(opt.optCode) {
			case OPT_ADD:
				optcodes[optcodes[i + 3]] = opt.get(optcodes, optcodes[i + 1], 1) + opt.get(optcodes, optcodes[i + 2], 2);
				stepSize = 4;
				break;
			case OPT_MUL:
				optcodes[optcodes[i + 3]] = opt.get(optcodes, optcodes[i + 1], 1) * opt.get(optcodes, optcodes[i + 2], 2);
				stepSize = 4;
				break;
			case OPT_IN:
				optcodes[optcodes[i + 1]] = INPUT;
				stepSize = 2;
				break;
			case OPT_OUT:
				output = opt.get(optcodes, optcodes[i + 1], 1);
				stepSize = 2;
				break;
			case OPT_JIT:
				if(opt.get(optcodes, optcodes[i + 1], 1) != 0) {
					i = opt.get(optcodes, optcodes[i + 2], 2);
					stepSize = 0;
					break;
				}
				stepSize = 3;
				break;
			case OPT_JIF:
				if(opt.get(optcodes, optcodes[i + 1], 1) == 0) {
					i = opt.get(optcodes, optcodes[i + 2], 2);
					stepSize = 0;
					break;
				}
				stepSize = 3;
				break;
			case OPT_CLT:
				optcodes[optcodes[i + 3]] = opt.get(optcodes, optcodes[i + 1], 1) < opt.get(optcodes, optcodes[i + 2], 2) ? 1 : 0;
				stepSize = 4;
				break;
			case OPT_CEQ:
				optcodes[optcodes[i + 3]] = opt.get(optcodes, optcodes[i + 1], 1) == opt.get(optcodes, optcodes[i + 2], 2) ? 1 : 0;
				stepSize = 4;
				break;
			default:
				if(opt.optCode != OPT_END) {
					System.out.println("Unknown optcode: " + opt.optCode);
				}
				done = true;
				break;
			}
			
			if(done) {
				break;
			}
		}
		
		System.out.println("Output was: " + output);
	}
}
