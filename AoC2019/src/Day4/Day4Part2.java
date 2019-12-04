package Day4;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Day4Part2 {

	static final String RANGE = "138307-654504";
	
	public static boolean orderChecker(String password, int prev) {
        if(password.length() == 0){
            return true;
        }

        final int front = Integer.parseInt(password.substring(0, 1));
        if(front < prev){
            return false;
        }

        return orderChecker(password.substring(1), front);
	}
	
	public static void main(String[] args) {
		List<String> passwords = new ArrayList<String>();
		String[] split = RANGE.split("-");
		int lowerBound = Integer.parseInt(split[0]);
		int upperBound = Integer.parseInt(split[1]);

		Pattern doublePattern = Pattern.compile("(\\d)\\1+");
		
		IntStream.range(lowerBound, upperBound).forEachOrdered(password -> {
			final String stringPassword = String.valueOf(password);
			final Matcher matcher = doublePattern.matcher(stringPassword);
			
			boolean contains = false;
			while (matcher.find()) {
				String match = matcher.group();
				if(match.length() == 2) {
					contains = true;
					break;
				}
			};
			
			if(orderChecker(stringPassword, -1) && contains) {
				passwords.add(stringPassword);
			}
		});

		System.out.println("Amount of passwords: " + passwords.size());
	}
	
}
