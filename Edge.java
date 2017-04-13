//Jerahmeel Calma
//Project 2
//CS 331
public class Edge {

	private int u;
	private int v;
	private int weight;
	
	public Edge(int start, int end, int val) {
		u = start;
		v = end;
		weight = val;
	}
	
	public int getU() {
		return u;
	}
	
	public int getV() {
		return v;
	}
	
	public int getWeight() {
		return weight;
	}
	
}
