/**
 * @author Christopher Grate - clg9100
 * File: Edge.java
 * 
 * Class used to represent an edge on the graph
 * 
 *
 */
public class Edge {
	private int leftVertex;
	private int rightVertex;
	private int weight;
	
	/*
	 * Constructor for instance of an edge
	 *Left = left vertex, Right = Right vertex, w = weight
	*/
	public Edge(int left, int right,int w){
		leftVertex  = left;
		rightVertex = right;
		weight = w;
	}
	
	/*
	 * Method used to determine whether the current edge is less
	 * than the edge being contested.
	 * 
	 * Parameter: edge2 - the edge being compared to.
	 */
	public boolean lessThan(Edge edge2){
		//The current edge is less than the edge being compared
		if(this.weight < edge2.weight){
			return true;	
		}
		//If the current edge weight is the same as contested edge
		if(this.weight == edge2.weight){
			//If the current edges LV is less than the contested's LV
			if(this.leftVertex < edge2.leftVertex){
				return true;
			}
			//If the current edges LV is the same as contested's LV
			else if(this.leftVertex == edge2.leftVertex){
				//If the current edges RV is the same as contested's RV
				if(this.rightVertex < edge2.rightVertex){
					return true;
				}
				//Otherwise current edges RV is greater
				else{
					return false;
				}
			}
			//Left vertex for current is greater than contested edge
			else{
				return false;
			}
		}
		//The current edge is not less than the edge being compared
		else{
			return false;
		}
	}//End less than method
	
	
	/*
	 * Return the weight of this edge
	 */
	public int weight(){
		return this.weight;
	}
	
	/*
	 * Return the left vertex of this edge
	 */
	public int leftVertex(){
		return this.leftVertex;
	
	}
	
	/*
	 * Return the right vertex of this edge
	 */
	
	public int rightVertex(){
		return this.rightVertex;
	}
}
