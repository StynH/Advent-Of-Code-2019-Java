package Day3;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Day3 {
	
	public static void main(String[] args) {
		String fileName = "inputDay3.txt";

		final Node origin = new Node(0, 0, 0);
		Path pathOne = null;
		Path pathTwo = null;
		
		try (Stream<String> stream = Files.lines(Paths.get(fileName))) {
			String[] temp = stream.toArray(String[]::new);
			
			pathOne = new Path();
			pathOne.buildPath(temp[0].split(","));
			
			pathTwo = new Path();
			pathTwo.buildPath(temp[1].split(","));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		List<Node> intersections = pathOne.findIntersections(pathTwo);
		List<Integer> distances = intersections.stream()
						        .map(node -> Path.findManhattanDistance(origin, node))
						        .collect(Collectors.toList());
		distances.sort((a, b) -> { return a - b; });
						        
		System.out.println("Shortest distance: " + distances.get(0));
	}
	
}
