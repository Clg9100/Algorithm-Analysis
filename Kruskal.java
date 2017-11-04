import java.util.ArrayList;

/**
 * @author Christopher Grate - clg9100
 * File: Kruskal.java
 * 
 * Uses Kruskals algorithm along with the union-find algorithm with
 * path compression to sort the edges by weight that will be added
 * to the MST
 * 
 *
 */

public class Kruskal {
	private ArrayList<Vertex> vertexArray = new ArrayList<Vertex>();
	
	/*
	 * Constructor for an instance of
	 */
	public Kruskal(int n){
		for(int i = 0; i < n; i++){
			Vertex vertex1 = new Vertex(i,0);
			vertexArray.add(vertex1);
		}
		
	}
	
	
	/*
	 * Method used to find the root of the subtree for a given vertex, 
	 * and make all vertices in that subtree point to the root when 
	 * it is found.
	 * 
	 * Parameter: 
	 * 			v - the vertex in which we want to find the subtree root for.
	 * 
	 * Returns:
	 * 			The root of the subtree of the given vertex
	 */
	public int Find(int v){
		if(v!= vertexArray.get(v).theParent()){
			vertexArray.get(v).updateParent(Find(vertexArray.get(v).theParent()));
		}
		return vertexArray.get(v).theParent();
		
	}
	
	/*
	 * Method that is used to update the rank and parent of the respective
	 * vertex that will be used with Kruskals algorithm
	 * 
	 * Parameters:
	 * 				u - a given vertex(Rather a number to idenfity it)
	 * 				v - another given vertex(Rather a number to idenfity it)
	 */
	public void union (int u, int v){
		int i = Find(u);
		int j = Find(v);
		
		if(vertexArray.get(i).theRank() > vertexArray.get(j).theRank()){
			vertexArray.get(j).updateParent(i);
		}
		
		else{
			vertexArray.get(i).updateParent(j);
			if(vertexArray.get(i).theRank() == vertexArray.get(j).theRank()){
				vertexArray.get(j).updateRank(vertexArray.get(j).theRank()+1);
			}
		}
		
	}
	
}
