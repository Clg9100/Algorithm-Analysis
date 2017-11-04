import java.util.ArrayList;
import java.util.Random;


/**
 * 
 * @author Christopher Grate - clg9100
 * File: Graph.java
 * 
 * Class which handles graph generation.
 * 
 *
 **/

public class Graph {
	
	//Represents the adjacency matrix
	private int [][] matrix;
	
	//Array which represents whether a vertice has been visited
	private int[] DFSArray;
	
	//Boolean representing whether the current graph is connected
	private boolean connected;
	
	//Used as a counter to compare to n
	// to determine whether to set connected to true or false.
	private int visited;
	
	//The array of edges (Currently unused, may need later)
	private ArrayList<Edge> edgeArray = new ArrayList<Edge>();
	
	//To sum total weight of all edges(Currently unused)
	private int totalWeight;
	
	//Special use, to adhere to upper half of matrix
	private int anotherUp;
	
	//To hold Edges for the adjacency list
	private ArrayList<ArrayList<Edge>> adjArray;
	//Array used to represent the edges chosen for the mst (Kruskal)
	private ArrayList<Edge> mstArray = new ArrayList<Edge>();

	
	
	/*
	 * Constructor for a graph (which is randomly generated until
	 * connected)
	 */
	public Graph(int n,int seed,double p){

		adjArray =  new ArrayList<ArrayList<Edge>>();
		
		//Create matrix representative of the graph
		matrix = new int[n][n];
		
		DFSArray = new int[n];
		//Second seed value to determine weight
		int secondSeed;
		
		//First random number to determine if vertices are connected
		double firstRandom;
		
		//Second random number to determine weight
		int secondRandom;
		
		//The weight of the connection
		int weight = 0;
		
		//First Seed Generation
		Random random1 = new Random(seed);
		
		secondSeed = (seed * 2);
		
		//Second Seed Generation
		Random random2 = new Random(secondSeed);
		
		//Special case to handle triangle formation in matrix
		int oneUP = 1;
		
		connected = false;
		
		for(int i = 0; i < n;i++){
			adjArray.add(new ArrayList<Edge>());
		}
		
		//While the generated graph proves to not be connected
		while(connected != true){
			//System.out.println("Not connected");
			//Reset graph to 0
			for(int f = 0; f< n; f++){
				for( int u = 0; u < n; u ++){
					matrix[f][u] = 0;
				}
			}
		oneUP = 1;
		
		for(int i = 0; i < n-1;i++){
			
			//System.out.println("This is i: " + i);
			for( int j = oneUP; j < n; j++){
				
			firstRandom  = random1.nextDouble();
			
			//System.out.println("First Seed: "+ firstRandom);
			
			
			
			//System.out.println("This is j: " + j);
				weight = 0;
				
				if(firstRandom <= p){
					secondRandom = random2.nextInt(n);
					secondRandom +=1;
					weight += secondRandom;
					//System.out.println("Weight: "+ weight);
					matrix[i][j] = weight;
					matrix[j][i] = weight;
					Edge newOne = new Edge(i,j,weight);
					Edge newOneOther = new Edge(j,i,weight);
					adjArray.get(i).add(newOne);
					adjArray.get(j).add(newOneOther);
				}
				
				else{
					matrix[i][j] = 0;
					matrix[j][i] = 0;
				}
				
			}//End J loop
			
			oneUP+=1;
		}//End I loop
		
		
		for (int i = 0; i < DFSArray.length;i++){
			DFSArray[i] = -2; //Fill array with indicators
		}
		
		DFSArray[0] = -1; //Signifies head of graph
	
			
		DFSVisit(0);
		isConnected(n);//Check if graph is connected
		
		}//End while graph generation	
		
	}//End graph constructor

	
/*
 * Method which performs the DFS search on the matrix
 * to mark nodes as visited
 */
public void DFSVisit(int v){

		for(int j = 0; j < matrix.length;j++){
			//We're at a connected edge for v
			if(matrix[v][j] != 0){
				//If a predecessor has not been set
				if(DFSArray[j] == -2){
					DFSArray[j] = v;
					
					DFSVisit(j);
				}
			}
		}//End for loop	
}


/*
 * Method responsible for returning whether the current graph
 * being tested is connected or not
 * 
 * Parameters: n - Number of vertices
 * 
 * Returns: Boolean (true or false) through setting connected
 */
public void isConnected(int n){
	connected = false;
	visited = 0;
	for(int i = 0;i< DFSArray.length;i++){
		if(DFSArray[i] != -2){
			visited +=1;
		}
	}
	
	//If count of visited != number of vertices; not connected
	if(visited !=n){
		connected = false;
	}
	//Graph is connected
	else{
		connected = true;
	}
	
}
/*
 * Method used to print the adjacency matrix
 * Parameter: n -  Number of vertices
 */
public void matrixPrint(int n){
		
		int count = 0;//To be used for row endings
		
		for(int i = 0; i < n;i++){
			//Reset Counter
			count = 0;
			
			for(int j = 0; j<=n;j++){
				
				if(count <= n-1){
				System.out.print(matrix[i][j]+"   ");
				count+=1;
				}
				
				else{
					System.out.println();
					System.out.println();
				}
			
			}//End printing for loop
			
		}//End for loop
		
	}//End matrixPrint function


/*
 * Method used to print an adjacency list
 * Parameter: n - Number of vertices
 */
public void adjacencyPrint(int n){
	
		
		for(int i = 0; i < n; i++){
			
			System.out.print(i+"-> ");
			
			for(int j = 0; j<n;j++){
				if(matrix[i][j]==0){
					//Do nothing, weight doesn't matter
				}
				
				else{
					System.out.print(j+"("+matrix[i][j]+") ");
				}
				//To handle end of line
				if(j+1 == n){
					System.out.println();
				}
				
			}//End for loop
			
		}		
		
		//Perhaps move at a later time
		System.out.println();
		System.out.println("Depth-First Search:");
		System.out.println("Vertices:");
		for( int i = 0; i<matrix.length;i++){
			if(i == 0){
				System.out.print(" ");
				System.out.print(i+" ");
			}
			else{
				System.out.print(i+" ");
			}
			
		}
		System.out.println();
		System.out.println("Predecessors:");
		for(int k = 0; k < matrix.length;k++){
			System.out.print(DFSArray[k]+" ");
		}
		System.out.println();
		
		
	}//End adjacency print function

/*
 * Method used to fill the edgeArray,
 * from information obtained in the adjacency matrix.
 * 
 * Parameter: edgeArray - the array of edge objects.
 */
public void edgeFillMatrix(ArrayList<Edge> edgeArray){
	//Rest array
	edgeArray.clear();
	anotherUp  = 1;
	
	for(int i = 0; i <matrix.length;i++){
		for(int j = anotherUp; j < matrix.length;j++){
			//If edge is not a 0, add it to edge list
			if(matrix[i][j] != 0){
				Edge newEdge = new Edge(i,j,matrix[i][j]);
				
				edgeArray.add(newEdge);
				
			}
			//Edge is a 0, don't add it
			else{
				//Do Nothing
			}//End Else
		}//End J loop
		anotherUp+=1;
	}//End I loop
	
}//End edgeFill method


/*
 * Method used to fill the edgeArray,
 * from information obtained in the adjacency list.
 * 
 * Parameter: edgeArray - the array of edge objects.
 */
public void edgeFillList(ArrayList<Edge> edgeArray){
	edgeArray.clear();
	anotherUp  = 1;
	
	for(int i = 0; i < matrix.length;i++){
		for(int j = 0;j<adjArray.get(i).size();j++){
			int col = adjArray.get(i).get(j).rightVertex();
			if(i < col){
			edgeArray.add(adjArray.get(i).get(j));
			}
			else{
				//Do nothing, don't add it.
			}
		}//End J loop
		anotherUp+=1;
	}//End I
	
	
}

/*
 * Function used to fill the MST using Kruskals algorithm.
 * 
 * Solves cycle-checking through the use of the union-find algorithm with
 * compression.
 * 
 * Also handles printing of the completed MST.
 * 
 * Parameters:
 * 			 edgeArray - an array of edges to be compared
 * 			 n - The total number of edges
 */
public ArrayList<Edge> mstFill(ArrayList<Edge> edgeArray,int n){
	int mstCount = 0;
	int root1;
	int root2;
	
	Kruskal aninstance = new Kruskal(n);
	
	while(mstCount < n-1){
		root1 = aninstance.Find(edgeArray.get(0).leftVertex());
		root2 = aninstance.Find(edgeArray.get(0).rightVertex());
		
		
		if(root1!=root2){
			mstArray.add(edgeArray.get(0));
			edgeArray.remove(0);
			mstCount+=1;	
			aninstance.union(root1,root2);
		}
		
		else{
		edgeArray.remove(0);
		}
				
	}//End while
	if(n<10){
		for(int i = 0; i < mstArray.size();i++){
			System.out.println(mstArray.get(i).leftVertex()+" "+
					+ mstArray.get(i).rightVertex()
					+" weight = "+ mstArray.get(i).weight());
			}//End for
	}
	
	else{
		//n >= 10, therefore no need to print
	}

	return mstArray;
}//End mstFill

/*
 * Method which performs insertion sort on edgeArray
 * 
 * Parameter: edgeArray - an array of edge objects
 */
public void insertionSort(ArrayList<Edge> edgeArray){
	for(int i = 1; i < edgeArray.size() ; i++){
		int j = i;
		while(j > 0 && (edgeArray.get(j).lessThan(edgeArray.get(j-1)))){
			//Make variable to hold edge about to be overwritten
			Edge holder = edgeArray.get(j-1);
			//J-1 becomes j
			edgeArray.set(j-1, edgeArray.get(j));
			//J becomes holder (Array[j] before overwritten)
			edgeArray.set(j,holder);
			j-=1;
		}//End while
	}//End for
}//End insertionSort method

/*
 * Performs Count sort on edgeArray
 * 
 * Parameters: edgeArray - an array of edge objects
 * 			   R - the range of the edgeArray
 */
public void countSort(ArrayList<Edge> edgeArray,int R){
	int N = edgeArray.size();
	int [] count = new int[R+2];
	Edge [] aux = new Edge[edgeArray.size()];
	
	for(int i = 0; i < N; i++){
		count[edgeArray.get(i).weight()+1]++;
	}
	
	for(int r = 0; r < R; r++){
		count[r+1] += count[r];
	}
	
	for (int i = 0; i < N; i++){
		aux[count[edgeArray.get(i).weight()]++] = edgeArray.get(i);
	}
	
	for(int i = 0; i < N; i++){
		edgeArray.set(i, aux[i]);
	}
}

/*
 * Performs quicksort on the edgeArray
 * 
 * Parameters: edgeArray - an array of edge objects
 * 			   low - the given low of the edgeArray
 * 			   high - the given high of the edgeArray
 */
public void quickSort(ArrayList<Edge> edgeArray, int low, int high){
	
	if(low < high){
		int pivotLocation = partition(edgeArray,low,high);
		quickSort(edgeArray,low,pivotLocation - 1);
		quickSort(edgeArray,pivotLocation+1,high);
	}
	
}
/*
 * Also part of the quicksort algorithm, partitions the edgeArray
 * for sorting.
 * 
 * Parameters: edgeArray - an array of edge objects
 * 			   low - the given low of the edgeArray
 * 			   high - the given high of the edgeArray
 */
public int partition(ArrayList<Edge>edgeArray,int low, int high){
	
	Edge pivot = edgeArray.get(high);
	int leftWall = low;
	
	for(int i = low; i < high;i++){
		if(edgeArray.get(i).lessThan(pivot)){
			Edge holder2 = edgeArray.get(leftWall);
			edgeArray.set(leftWall, edgeArray.get(i) );
			edgeArray.set(i, holder2);
			leftWall+=1;		
		}//End if
	}//End for
	
	//swap ....
	Edge holder3 = edgeArray.get(leftWall);
	edgeArray.set(leftWall,edgeArray.get(high));
	edgeArray.set(high,holder3);
	
	return leftWall;
}

/*
 * Return the total weight for the edgeArray
 * 
 * Parameters: totalWeight - represents total weight of edgeArray
 * 			   edgeArray - the array of edges to pull weights from
 */
public int totalWeight(ArrayList<Edge> edgeArray){
	int theWeight = 0;
	for(int i = 0; i < edgeArray.size();i++){
		theWeight+= edgeArray.get(i).weight();
	}
	return theWeight;
	
}

/*
 * Print edge weights and their left and right vertex's
 * 
 * Parameter: edgeArray - an array of edge objects
 */
public void edgePrint(ArrayList<Edge> edgeArray){

	for(int i = 0; i < edgeArray.size();i++){
		System.out.println(edgeArray.get(i).leftVertex()+" "+
				+ edgeArray.get(i).rightVertex()
				+" weight = "+ edgeArray.get(i).weight());
		}//End for
		//System.out.println("Element: " +edgeArray.get(i).weight());	
}



}//End graph class
