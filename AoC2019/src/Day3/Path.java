package Day3;

import java.util.ArrayList;
import java.util.List;

public class Path {
	
	public List<Node> nodes;
	public int steps;
	public int lastX;
	public int lastY;
	
	static int findManhattanDistance(Node nodeOne, Node nodeTwo) {
        return Math.abs(nodeTwo.x - nodeOne.x) + Math.abs(nodeTwo.y - nodeOne.y);
	}
	
	public Path() {
		nodes = new ArrayList<Node>();
		this.steps = 0;
		this.lastX = 0;
		this.lastY = 0;
	}

	public void buildPath(String[] split) {
		for(String path : split) {
			int velX = 0;
			int velY = 0;
			
			char command = path.substring(0, 1).charAt(0);
			int amount = Integer.parseInt(path.substring(1));
			
			switch(command) {
		     case 'U':
		    	 velY = amount;
                 break;
             case 'R':
            	 velX = amount;
                 break;
             case 'D':
            	 velY = -amount;
                 break;
             case 'L':
            	 velX = -amount;
                 break;
			}
			
			int xIt = velX < 0 ? -1 : 1;
            for(int i = 0; i != velX; i += xIt){      
                this.lastX += xIt;
                ++this.steps;
                
                this.nodes.add(new Node(this.lastX, this.lastY, this.steps));
            }

            int yIt = velY < 0 ? -1 : 1;
            for(int i = 0; i != velY; i += yIt){
                this.lastY += yIt;
                ++this.steps;
                
            	this.nodes.add(new Node(this.lastX, this.lastY, this.steps));
            }
		}
	}

	public List<Node> findIntersections(Path rhs) {
		List<Node> intersections = new ArrayList<Node>();
		
		for(Node n : this.nodes) {
			Node other = rhs.findNode(n.x, n.y);
			if(other != null) {
				intersections.add(new Node(n.x, n.y, n.length + other.length));
			}
		}
		
		return intersections;
	}
	
	public Node findNode(int x, int y) {
		for(Node n : this.nodes) {
			if(n.x == x && n.y == y) {
				return n;
			}
		}
		return null;
	}
}
