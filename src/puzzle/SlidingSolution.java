package puzzle;

import java.util.Collections;
import java.util.List;

public class SlidingSolution
{
	private List<Node> solution;
	private int explored;
	private Node node;

	public SlidingSolution(Node node, int explored) {
		this.node = node;
		this.solution = exploreSolution(node);
		this.explored = explored;
	}

	private List<Node> exploreSolution(Node node) {
		if(node == null)
			return null;
		return node.getPath();
	}

	public List<Node> getSolution() {
		return solution;
	}

	public int getExplored() {
		return explored;
	}

	public void printSolution() {
		System.out.println("Number of moves: " + getSolution().size());
		System.out.print("Path: ");
		Collections.reverse(solution);
		for(Node node : solution) {
			System.out.print(node.getMove().getMove());
		}
		System.out.println();
		System.out.println("Number of nodes expanded: " + getExplored());
	}

}
