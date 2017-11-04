import java.util.ArrayList;
import java.util.Arrays;


/**
 * 
 * @author Christopher Grate - clg9100
 * File: MstHeap
 * 
 * Function used to perform prims algorithm using a priority queue
 * implemented as a binary heap
 *
 */

public class MstHeap {
	
	//The heap used to store the vertexes.
	private ArrayList<PrimVertex> theHeap  = new ArrayList<PrimVertex>();
	
	
	/*
	 * Constructor used to make an instance of an MstHeap
	 * 
	 * Parameters:
	 * 
	 * int n - Used to determine the number of vertexes to add.
	 * 
	 */
	public MstHeap(int n){
		PrimVertex blank = new PrimVertex(-1,-1,-1);
		theHeap.add(blank);
		 for(int i = 0; i <n;i++){
				PrimVertex newVert = new PrimVertex(i,Integer.MAX_VALUE,-1);
				
				theHeap.add(newVert);
			}
		 	//theHeap.set(0, blank);
			theHeap.get(1).setPrior(0);
		//Need vertex 0's information, don't need position 0
	}
	
	
	/*
	 * Method used to perform prims algorithm to fill the heap with
	 * the appropriate vertexes.
	 * 
	 * Also handles printing of the mstArray once filled
	 * 
	 * Parameters:
	 * 
	 * int n - Used to determine the number of vertexes
	 * ArrayList<Edge> edgeArray - used to hold the edges to be inspected.
	 * 
	 * Returns:
	 * 
	 * The completed MST array
	 */
public int[] prim(int n, ArrayList<Edge> edgeArray){
	int[] mstedgeArray = new int[n];
	int[] mstweightArray = new int[n];
	Arrays.fill(mstedgeArray, -1);
	
	heapify();//Create a min heap
	while(theHeap.size()!=1){
		//Prior test prints
		//for(int i =0; i < theHeap.size();i++){
		//System.out.println("Element: "+ theHeap.get(i).getIdentity());	
		//}
		PrimVertex u = delMin();
		//System.out.println("Identity of u: " + u.getIdentity());
		//System.out.println("Neighbor of u: " + u.getEdgeNeighbor()); 
		//System.out.println("Prioirty of u: " + u.getPrior());
		//System.out.println(); Test prints
		
		for(int i = 0; i < edgeArray.size(); i ++){
			//the id of the neighbor of u
			int neighbor = -1;
			//weight of the edge between u and neighbor
			int weight = 0;
			
			if( edgeArray.get(i).leftVertex() == u.getIdentity()){
				
				//for(int j = 0; j < theHeap.size();j++){
					//if(theHeap.get(j).getIdentity()==edgeArray.get(i).leftVertex())
					//&& edgeArray.get(i).weight() < edgeArray.get(i).leftVertex()
				//}
				neighbor = edgeArray.get(i).rightVertex();
				weight = edgeArray.get(i).weight();
				
			}
			if(edgeArray.get(i).rightVertex() == u.getIdentity()){
				neighbor = edgeArray.get(i).leftVertex();
				weight = edgeArray.get(i).weight();
			}
			
			if(neighbor!=-1){
				//if neighbor is still in PQ.....
				for(int j = 1; j <theHeap.size();j++){
					if(theHeap.get(j).getIdentity() == neighbor &&
							 weight < theHeap.get(j).getPrior() ){
						mstedgeArray[neighbor] = u.getIdentity();
						mstweightArray[neighbor] = weight;
						theHeap.get(j).setEdgeNeighbor(u.getIdentity());
						theHeap.get(j).setPrior(weight);
					}
				}		
			}//end if				
		}//end for
		heapify(); //re-heapify the pq					 
	}//end while
	
	if(n<10){
		for(int i = 1; i<mstedgeArray.length;i++){
			System.out.println(mstedgeArray[i]+ " " + i + " weight = "+mstweightArray[i]);
		}
	}
	else{
		System.out.println();
	}
	return mstedgeArray;
}
	
	//Child swap with parent
	private void swim(int k){
		while(k > 1 && More(k/2,k)){
		exchange(k,k/2);	
		k = k/2;	
		}//End while
	}
	
	//Parent swap with child
	private void sink(int k){
		while(2*k <= theHeap.size()-1){
			int j = 2*k;//J = left child
			if(j < theHeap.size()-1 && More(j,j+1)){
				j+=1;//J = right child
			}
			if(!More(k,j)){
				break;
			}
			exchange(k,j);
			k = j;
		}//End while
	}//End sink
	
	//Heapify the heap
	public void heapify(){
		for(int i = theHeap.size();i >= 1; i--){
			sink(i);
		}
		
		
	}
	//Delete the minimum value from the heap
	public PrimVertex delMin(){
		PrimVertex min = theHeap.get(1);
		exchange(1, theHeap.size()-1);
		theHeap.remove(theHeap.size()-1);
		sink(1);
		
		return min;
		
	}
	/*
	 *  Comparator of heap vertexes
	 *  
	 *  Parameters:
	 *  
	 *  int khalf- and index in the heap being looked at (half of k).
	 *  
	 *  int k - and index in the heap being looked at.
	 *  
	 *  Returns:
	 *  
	 *  Boolean, determining which is greater than the other
	 */

	private boolean More(int Khalf, int k){
		
		if(k < theHeap.size()){
			
			if( theHeap.get(Khalf).getPrior() > theHeap.get(k).getPrior()){
				return true;
				
			}
			else{
				return false;
			}	
		}
		else{
			return false;
		}
	}
	
	/*
	 * Swaps k and khalf
	 * 
	 * Parameters:
	 * 
	 * int k - an index in the heap
	 * 
	 * int khalf - and index in the heap (half of k)
	 */
	private void exchange(int k, int Khalf){
		PrimVertex temp = theHeap.get(k);
		theHeap.set(k, theHeap.get(Khalf));
		theHeap.set(Khalf, temp);
	}

}
