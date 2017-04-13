//Jerahmeel Calma
//Project 2
//CS 331

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

public class CS331_Project2 {

	public static void main(String[] args) throws IOException {
		File file = new File("CostandTime.txt");
		FileWriter fw = new FileWriter(file);
		Prim p;
		Kruskal k;
		int size, nsize, totalCost;
		long startTime, stopTime;
		long totalTimeP = 0;
		long totalTimeK = 0;
		int[][] graph ;
		int[] n = {10, 20, 30, 40, 50, 60, 70, 80, 90,
				100, 200, 300, 400, 500, 600, 700, 800, 900, 1000};

		for(int i = 0; i < n.length; i++) {
			size = (n[i]*(n[i] - 1)) / 2;
			for(double j = 1.0; j > 0.2; j-= 0.2) {
				totalCost = 0;
				totalTimeP = 0;
				totalTimeK = 0;
				nsize = (int)(size*j);
				graph = new int[n[i]+1][n[i]+1];
				graph = initGraph(graph, n[i], nsize);
				
				fw.write("n = " + n[i] + " and e = " + nsize + "\n");

				p = new Prim(graph, nsize);

				for(int a = 0; a < 10; a++) {
					startTime = System.nanoTime();
					p.primAlg();
					stopTime = System.nanoTime();
					totalTimeP = totalTimeP + (stopTime - startTime);
				}
				totalTimeP = totalTimeP / 10;
				
				if(n[i] == 10) {
					printPMST(p, graph, nsize);
				}
				fw.write("Average time(in nanoseconds) of Prim: " + totalTimeP + "\n");

				k = new Kruskal(graph, nsize);

				for(int a = 0; a < 10; a++) {
					startTime = System.nanoTime();
					k.kruskalAlg();
					stopTime = System.nanoTime();
					totalTimeK = totalTimeK + (stopTime - startTime);
				}
				totalTimeK = totalTimeK / 10;
				
				if(n[i] == 10) {
					printKMST(k, graph, nsize);
				}
				for(int x = 0; x < k.getMST().size(); x++) {
					totalCost += k.getMST().get(x).getWeight();
				}
				fw.write("Average time(in nanoseconds) of Kruskal: " + totalTimeK + "\n");
				fw.write("Total cost from minimum spanning tree: " + totalCost +"\n\n");
			}
			fw.write("\n\n");
		}
		fw.close();
		
	}
	
	 private static int[][] initGraph(int[][] graph, int n, int numEdge) {
		Random rand = new Random();
		int counter = 0;
		int rNum;
		int edgesLeft = numEdge;
		int space = (n*(n - 1)) / 2;
		for(int i = 0; i <= n; i++) {
			for(int j = i; j <= n; j++) {
				if(i == 0 || j == 0 || i == j) {
					graph[i][j] = 0;
				} else if(counter < numEdge) {
					if(rand.nextInt(100) % 2 == 0) {
						rNum = rand.nextInt(99) + 1;
						graph[i][j] = rNum;
						graph[j][i] = rNum;
						counter++;
						space--;
						edgesLeft--;
					} else if(space == edgesLeft) {
						rNum = rand.nextInt(99) + 1;
						graph[i][j] = rNum;
						graph[j][i] = rNum;
						counter++;
						space--;
						edgesLeft--;
					} else {
						graph[i][j] = Integer.MAX_VALUE;
						graph[j][i] = Integer.MAX_VALUE;
						space--;
					}
				} else {
					graph[i][j] = Integer.MAX_VALUE;
					graph[j][i] = Integer.MAX_VALUE;
					space--;
				}
			}
		}
		return graph;
	}
	
	private static void printPMST(Prim p, int[][] graph, int num) throws IOException {
		File file = new File("PrimOutput1.txt");;
		if(num == 45) {
		} else if(num == 36) {
			file = new File("PrimOutput2.txt");
		} else if(num == 27) {
			file = new File("PrimOutput3.txt");
		} else if(num == 18) {
			file = new File("PrimOutput4.txt");
		} else if(num == 9) {
			file = new File("PrimOutput5.txt");
		}
		FileWriter fw = new FileWriter(file);
		
		for(int i = 0; i < graph.length; i++) {
			for(int j = 0; j < graph.length; j++) {
				fw.write("" + graph[i][j] + " ");
				if(j == graph.length-1) {
					fw.write("\n");
				}
			}
		}
		fw.write("\nNumber of edges = " + p.getNumEdges() + "\n\n");
		fw.write("Prim's Algorithm \n" + "edge     cost\n");
		for(int i = 1; i < p.getMST().length; i++) {
			for(int j = 1; j < p.getMST().length; j++) {
				if(p.getMST()[i][j] == true) {
					fw.write(i + " - " + j + "     " + graph[i][j] + "\n");
				}
			}
		}
		if(p.getMinCost() != 0) {
			fw.write("Total cost: " + p.getMinCost());
		} else {
			fw.write("\nThe nodes do not connect.");
		}
		fw.close();
	}
	
	private static void printKMST(Kruskal k, int[][] graph, int num) throws IOException {
		File file = new File("KruskalOutput1.txt");
		if(num == 45) {
		} else if(num == 36) {
			file = new File("KruskalOutput2.txt");
		} else if(num == 27) {
			file = new File("KruskalOutput3.txt");
		} else if(num == 18) {
			file = new File("KruskalOutput4.txt");
		} else if(num == 9) {
			file = new File("KruskalOutput5.txt");
		}
		FileWriter fw = new FileWriter(file);
		
		int totalCost = 0;
		
		for(int i = 0; i < graph.length; i++) {
			for(int j = 0; j < graph.length; j++) {
				fw.write("" + graph[i][j] + " ");
				if(j == graph.length-1) {
					fw.write("\n");
				}
			}
		}
		fw.write("\nNumber of edges = " + k.getNumEdges() + "\n\n");
		fw.write("Kruskal's Algorithm \n" + "edge     cost\n");
		for(int i = 0; i < k.getMST().size(); i++) {
			fw.write(k.getMST().get(i).getU() + " - " + k.getMST().get(i).getV() + "     " + k.getMST().get(i).getWeight() + "\n");
			totalCost += k.getMST().get(i).getWeight();
		}
		if(totalCost != 0) {
			fw.write("total cost: " + totalCost);
		} else {
			fw.write("\nThe nodes do not connect.");
		}
		
		fw.close();
	}
	
}
