package Day4;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.IntStream;

public class Day4 {

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
		
		Pattern doublePattern = Pattern.compile("(\\d)\\1");
		
		IntStream.range(lowerBound, upperBound).forEachOrdered(password -> {
			final String stringPassword = String.valueOf(password);
			if(orderChecker(stringPassword, -1) && doublePattern.matcher(stringPassword).find()) {
				passwords.add(stringPassword);
			}
		});
		
		System.out.println("Amount of passwords: " + passwords.size());
	}
	
}
