/**
 * @author Christopher Grate - clg9100
 * File: Vertex.java
 * 
 * Class used to represent a vertex in the graph
 * 
 *
 */
public class Vertex {
	private int theParent;
	private int theRank;
	
	/*
	 * Constructor for instance of a vertex
	 * theParent = parent of the vertex, 
	 * theRank = rank of the vertex.
	*/
	public Vertex(int parent, int rank){
	
		theParent  = parent;
		theRank = rank;
	}
	
	/*
	 * Return the parent of this vertex
	 */
	public int theParent(){
		return this.theParent;
	}
	
	/*
	 * Return the rank of this vertex
	 */
	public int theRank(){
		return this.theRank;
	}
	
	/*
	 * Update the parent of this vertex to the newParent
	 */
	public void updateParent(int newParent){
		this.theParent = newParent;
	}
	
	/*
	 * Update the rank of this vertex to the newRank
	 */
	public void updateRank(int newRank){
		this.theRank = newRank;
	}
}