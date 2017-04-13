//Jerahmeel Calma
//Project 2
//CS 331

public class Prim {

	private int[][] graph;
	private boolean[][] mst;
	private int minCost;
	private int numEdges;
	
	public Prim(int[][] array, int val) {
		graph = array;
		mst = new boolean[array.length][array.length];
		minCost = 0;
		numEdges = val;
	}
	
	public void primAlg() {
		int[] near = new int[graph.length];
		int j = 1;
		int cost;
		near[1] = 0;
		for(int i = 2; i < near.length; i++) {
			near[i] = 1;
		}
		for(int i = 1; i < near.length-1; i++) {
			cost = Integer.MAX_VALUE;
			for(int y = 1; y < near.length; y++) {
				if(near[y] != 0 && graph[y][near[y]] < cost) {
					j = y;
					cost = graph[y][near[y]];
				}
			}
			if(cost != Integer.MAX_VALUE) {
				mst[j][near[j]] = true;
				minCost += graph[j][near[j]];
				near[j] = 0;
			}
			for(int k = 1; k < near.length; k++) {
				if(near[k] != 0 && graph[k][near[k]] > graph[k][j]) {
					near[k] = j;
				}
			}
		}
	}
	
	public int getMinCost() {
		return minCost/10;
	}
	
	public boolean[][] getMST() {
		return mst;
	}
	
	public int getNumEdges() {
		return numEdges;
	}
	
}
