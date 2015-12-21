/*
 * $Id: SolutionChecker.java,v 1.1 2014/03/19 00:57:17 txw6529 Exp $
 * $Log: SolutionChecker.java,v $
 * Revision 1.1  2014/03/19 00:57:17  txw6529
 * initial commit
 *
 * 
 */


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Checks the solution for the specified puzzle of Meeples.
 * @author TJohnW
 */
public class SolutionChecker implements PuzzleVerifier {
	
	/**
	 * A little utility to help clean up my code.
	 * @author TJohnW
	 */
	private static class ColorData {
		private int id, total, length;
		/**
		 * Constructs a datatype to represent a color and its properties.
		 * @param colorId the id of the color.
		 * @param connected the amount connected from the first occurance.
		 */
		private ColorData(int colorId, int connected) {
			this.id = colorId;
			this.length = connected;
			this.total = 0;
		}
	}
	
	/* The size and grid dimensions of the input data */
	private int size, grid_size;
	private Map<Integer, ColorData> colors;
	
	@Override
	public int checkSolution(int[] values, int nColors) {
		
		colors = new HashMap<Integer, ColorData>(nColors);
		size = values.length;
		grid_size = (int) Math.sqrt(values.length);
		
		for(int i = 0; i < values.length; i++) {
			if(values[i] == 0) continue;
			if(!colors.containsKey(values[i]))
				colors.put(values[i], new ColorData(values[i], pathLength(values, i)));
			colors.get(values[i]).total++;
		}
		
		for(ColorData color: colors.values())
			if(color.total != color.length)
				return color.id;
		
		return -1;
	}
	
	/**
	 * Helper method for pathLength
	 * @see #pathLength(int[] values, int index, ArrayList<Integer> visited)
	 */
	private int pathLength(int[] values, int index) {
		return pathLength(values, index, new ArrayList<Integer>());
	}
	
	/**
	 * Helper method for pathLength
	 * @param values the list of meeples
	 * @param index the meeple to start with
	 * @param visited the previously visited meeple positions
	 * @return the total length
	 */
	private int pathLength(int[] values, int index, List<Integer> visited) {
		if(visited.contains(index)) return 0;
		visited.add(index);
		
		int count = 1;
		for(int n: neighbors(index))
			if(values[n] == values[index])
				count += pathLength(values, n, visited);
		return count;
	}
	
	/**
	 * Computes the neighbors of an element from a one dimensional array.
	 * Uses the size and grid_size to determine its neighbors.
	 * @param index the index of the meeple
	 * @return an ArrayList of indicies for neighbor meeples.
	 */
	private List<Integer> neighbors(int index) {
		List<Integer> neighbors = new ArrayList<Integer>(4);
		
		if(index % grid_size != 0)
			neighbors.add(index - 1);
		
		if((index + 1) % grid_size != 0)
			neighbors.add(index + 1);
		
		if((index - grid_size) >= 0)
			neighbors.add(index - grid_size);
		
		if((index + grid_size) < size)
			neighbors.add(index + grid_size);
		
		return neighbors;
	}

}
