
/*
 * Class used to represent a PrimVertex,
 * which are vertexes specifically used in the prim algorithm
 * so as to not be confused with previously used vertexes.
 */
public class PrimVertex {
	//The identity of a vertex
	private int identity;
	
	//The priority of a vertex
	private int prior;
	
	//The neighbor of this vertex
	private int edgeNeighbor;
	
	/*
	 * Constructor for an instance of a prim vertex
	 * 
	 * Parameters:
	 * int ID - the identifier of the vertex.
	 * 
	 * int priority - the priority associated with this vertex.
	 * 
	 * int neighbor - the neighbor of this vertex.
	 */
	
	public PrimVertex(int ID, int priority, int neighbor){
		identity = ID;//Vertice identifer 0 - n
		prior = priority;//Smallest edge weight to vertex
		edgeNeighbor = neighbor;//Neighbor to current vertex
		
	}
	
	/*
	 * Getter for the identity of the vertex
	 * 
	 * Returns:
	 *  identity
	 */
	public int getIdentity() {
		return identity;
	}


	/*
	 * Setter for the identity of the vertex
	 * 
	 */
	public void setIdentity(int identity) {
		this.identity = identity;
	}


	/*
	 * Getter for the priority of the vertex
	 * 
	 * Returns:
	 *  prior
	 */
	public int getPrior() {
		return prior;
	}


	/*
	 * Setter for the identity of the vertex
	 * 
	 */
	public void setPrior(int prior) {
		this.prior = prior;
	}


	/*
	 * Getter for the neighbor of the vertex
	 * 
	 * Returns:
	 *  edgeNeighbor
	 */
	public int getEdgeNeighbor() {
		return edgeNeighbor;
	}


	/*
	 * Setter for the identity of the vertex
	 * 
	 */
	public void setEdgeNeighbor(int edgeNeighbor) {
		this.edgeNeighbor = edgeNeighbor;
	}

}
