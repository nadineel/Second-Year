//@author: Bernadine Lao
//loosely based on the book from Princeton

import java.util.HashSet;

public class Graph {
	
	protected int V;						//#of vertices in the graph
	protected int E;						//#of edges in the graph
	private int[] indegree;					//# of nodes with arrow coming to a vertex
	protected HashSet<DirectedEdge>[]adj;	//edges adjacency list for vertex v
	
	@SuppressWarnings("unchecked")
	Graph(int V, int E){
		this.V=V;
		this.E=E;	
		this.indegree=new int[V];
				
		//creates a list for each vertex to store adjacent stuff to it
		adj = (HashSet<DirectedEdge>[]) new HashSet[V];
        for (int vertex = 0; vertex < V; vertex++)
            adj[vertex] = new HashSet<DirectedEdge>();
	}
	
	void addStreet(int from, int to, double distance){
		DirectedEdge edge=new DirectedEdge(from,to,distance);
		adj[from].add(edge);
		indegree[to]++;
		
	}

}
