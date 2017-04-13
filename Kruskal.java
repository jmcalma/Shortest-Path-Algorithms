//Jerahmeel Calma
//Project 2
//CS 331

import java.util.ArrayList;

public class Kruskal {

	private int[][] graph;
	private int[] A;
	private int[] height;
	private ArrayList<Edge> edges;
	private ArrayList<Edge> T = new ArrayList<Edge>();
	private int numEdges;
	
	public Kruskal(int[][] array, int val) {
		graph = array;
		A = new int[array.length];
		height = new int[array.length];
		initializeAH();
		edges = new ArrayList<Edge>();
		initializeE();
		numEdges = val;
	}
	
	public void kruskalAlg() {
		Edge edge;
		while(edges.size() != 0 && T.size() < height.length-2) {
			edge = edges.remove(0);
			int start = find2(edge.getU());
			int end = find2(edge.getV());
			if(start != end) {
				T.add(edge);
				merge3(start, end);
			}
		}
	}
	
	private void initializeAH() {
		for(int i = 1; i < A.length; i++) {
			A[i] = i;
			height[i] = 0;
		}
	}
	
	private void initializeE() {
		for(int i = 1; i < graph.length; i++) {
			for(int j = i+1; j < graph.length; j++) {
				if(graph[i][j] != Integer.MAX_VALUE) {
					edges.add(new Edge(i, j, graph[i][j]));
				}
			}
		}
		heapsort();
	}
	
	private void heapsort() {
		int size = edges.size();
		for(int i = (size/2)-1; i >= 0; i--) {
			heapify(size, i);
		}
		for(int i = size-1; i >= 0; i--) {
			Edge temp = edges.get(0);
			edges.set(0, edges.get(i));
			edges.set(i, temp);
			heapify(i, 0);
		}
	}

	private void heapify(int size, int index) {
		int min = index;
		int left = (2*index)+ 1;
		int right = (2*index)+ 2;

		if(left < size && edges.get(left).getWeight() > edges.get(min).getWeight()) {
			min = left;
		}
		if(right < size && edges.get(right).getWeight() > edges.get(min).getWeight()) {
			min = right;
		}
		if(min != index) {
			Edge temp = edges.get(index);
			edges.set(index, edges.get(min));
			edges.set(min, temp);
			heapify(size, min);
		}
	}

	private int find2(int x) {
		int i = x;
		while(A[i] != i) {
			i = A[i];
		}
		return i;
	}
	
	private void merge3(int a, int b) {
		if(height[a] == height[b]) {
			A[b] = a;
			height[a] = height[a] + 1;
		} else if(height[a] > height[b]) {
			A[b] = a;
		} else {
			A[a] = b;
		}
	}
	
	public ArrayList<Edge> getMST() {
		return T;
	}
	
	public int getNumEdges() {
		return numEdges;
	}
	
}
