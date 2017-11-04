import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;



/**
 * 
 * @author Christopher Grate - clg9100
 * File: MST.java
 * 
 * Main program which handles the call to printing of the
 * adjacency matrix / list if applicable.
 * 
 * Handles input error checking as well.
 *
 */

public class MST {

public static void main(String[] args){
	
	
	int n = 0; // # of vertices 
	int seed = 0;// The seed for the number generator
	double p = 0.0;// Probability
	ArrayList<Edge> matrixEdge = new ArrayList<Edge>();
	ArrayList<Edge> mstArray = new ArrayList<Edge>();
	Scanner in = null;
	int totalWeight = 0;//Total weight of the MST
	int largestElement = 0;//Used in count sort
	//System.out.println("Args: " + args[0]);
	
	try {
		in = new Scanner(new File(args[0]));
	} catch (FileNotFoundException e) {
		System.out.println("Input file not found");
		System.exit(0);
	}
	
	//Get n, the seed, and p
	
	//Number of vertices
	//Also, error check 2
	try{
		n = in.nextInt();	
	}
	
	catch(Exception e){
		System.out.println("n and seed must be integers");
		System.exit(0);
	}
	
	
	//Error check 2
	try{
		seed = in.nextInt();
	}
	
	catch(Exception e){
		System.out.println("n and seed must be integers");
	}
	
	//Error Check 3
	if(n < 2){
		System.out.println("n must be greater than 1");
		System.exit(0);
	}
	
	p = in.nextDouble();
	
	//Error check 4 & 5
	if(p < 0 || p > 1){
		System.out.println("p must be between 0 and 1");
		System.exit(0);
	}
	
	
	//n = 7;
	//seed = 100000;
	//p = 0.5;
	
	
	//Call to adjacency matrix printer
	//- ONLY if number of vertices is less than 10.
	if(n<10){
		
		//Timing for Graph Generation
		long tStart = System.currentTimeMillis();
		Graph graph1 = new Graph(n,seed,p);
		long tEnd = System.currentTimeMillis();
		long elapsed = tEnd - tStart;
		
		System.out.println("TEST: "+"n="+n+","+" seed="+seed+","+" p="+p);
		System.out.println("Time to generate the graph: "+ elapsed+" milliseconds");
		System.out.println();
		System.out.println("The graph as an adjacency matrix:");
		System.out.println();
		graph1.matrixPrint(n);
		System.out.println("The graph as an adjacency list:");
		graph1.adjacencyPrint(n);
		
		
		
		//Timing for Insertion Sort Kruskal(Matrix)
		long tStartMatrixInsertKrusk = System.currentTimeMillis();
		graph1.edgeFillMatrix(matrixEdge);
		graph1.insertionSort(matrixEdge);
		System.out.println("===================================");
		System.out.println("KRUSKAL WITH MATRIX USING INSERTION SORT");
		mstArray = graph1.mstFill(matrixEdge, n);
		System.out.println();
		long tEndMatrixInsertKrusk = System.currentTimeMillis();
		long elapsedMatrixInsertKrusk = tEndMatrixInsertKrusk - tStartMatrixInsertKrusk;
		
		
		totalWeight = graph1.totalWeight(mstArray);
		System.out.println("Total weight of MST using Kruskal: "+totalWeight);
		System.out.println("Runtime: "+elapsedMatrixInsertKrusk+ " milliseconds");
		System.out.println();
		
		
		//Caveat for Countsort algorithm
		largestElement = matrixEdge.get(matrixEdge.size()-1).weight();
		
		//Timing for Count Sort Kruskal(Matrix)
		long tStartMatrixCountKrusk = System.currentTimeMillis();
		graph1.edgeFillMatrix(matrixEdge);
		graph1.countSort(matrixEdge, largestElement);
		System.out.println("===================================");
		System.out.println("KRUSKAL WITH MATRIX USING COUNT SORT");
		mstArray.clear();//Empty mstArray
		mstArray = graph1.mstFill(matrixEdge, n);
		System.out.println();
		long tEndMatrixCountKrusk = System.currentTimeMillis();
		long elapsedMatrixCountKrusk = tEndMatrixCountKrusk - tStartMatrixCountKrusk;
		
		
		totalWeight = graph1.totalWeight(mstArray);
		System.out.println("Total weight of MST using Kruskal: "+totalWeight);
		System.out.println("Runtime: "+elapsedMatrixCountKrusk+ " milliseconds");
		System.out.println();
		
		
		
		//Timing for Quicksort Kruskal(Matrix)
		long tStartMatrixQuickKrusk = System.currentTimeMillis();
		graph1.edgeFillMatrix(matrixEdge);
		graph1.quickSort(matrixEdge, 0, matrixEdge.size()-1);
		System.out.println("===================================");
		System.out.println("KRUSKAL WITH MATRIX USING QUICKSORT");
		mstArray.clear();//Empty mstArray
		mstArray = graph1.mstFill(matrixEdge, n);
		System.out.println();
		long tEndMatrixQuickKrusk = System.currentTimeMillis();
		long elapsedMatrixQuickKrusk = tEndMatrixQuickKrusk - tStartMatrixQuickKrusk;
		
		
		totalWeight = graph1.totalWeight(mstArray);
		System.out.println("Total weight of MST using Kruskal: "+totalWeight);
		System.out.println("Runtime: "+elapsedMatrixQuickKrusk+ " milliseconds");
		System.out.println();
		
		
		//Timing for Insertion Sort Kruskal(List)
		long tStartListInsertKrusk = System.currentTimeMillis();
		graph1.edgeFillList(matrixEdge);
		graph1.insertionSort(matrixEdge);
		System.out.println("===================================");
		System.out.println("KRUSKAL WITH LIST USING INSERTION SORT");
		mstArray.clear(); // Empty MstArray
		mstArray = graph1.mstFill(matrixEdge, n);
		System.out.println();
		long tEndListInsertKrusk = System.currentTimeMillis();
		long elapsedListInsertKrusk = tEndListInsertKrusk - tStartListInsertKrusk;
		
		totalWeight = graph1.totalWeight(mstArray);
		System.out.println("Total weight of MST using Kruskal: "+totalWeight);
		System.out.println("Runtime: "+elapsedListInsertKrusk+ " milliseconds");
		System.out.println();
		
		
		//Timing for Count Sort Kruskal(List)
		long tStartListCountKrusk = System.currentTimeMillis();
		graph1.edgeFillList(matrixEdge);
		graph1.countSort(matrixEdge, largestElement);
		System.out.println("===================================");
		System.out.println("KRUSKAL WITH LIST USING COUNT SORT");
		mstArray.clear(); // Empty MstArray
		mstArray = graph1.mstFill(matrixEdge, n);
		System.out.println();
		long tEndListCountKrusk = System.currentTimeMillis();
		long elapsedListCountKrusk = tEndListCountKrusk - tStartListCountKrusk;
		
		totalWeight = graph1.totalWeight(mstArray);
		System.out.println("Total weight of MST using Kruskal: "+totalWeight);
		System.out.println("Runtime: "+elapsedListCountKrusk+ " milliseconds");
		System.out.println();
		
		
		//Timing for QuickSort Kruskal(List)
		long tStartListQuickKrusk = System.currentTimeMillis();
		graph1.edgeFillList(matrixEdge);
		graph1.quickSort(matrixEdge, 0, matrixEdge.size()-1);
		System.out.println("===================================");
		System.out.println("KRUSKAL WITH LIST USING QUICKSORT");
		mstArray.clear(); // Empty MstArray
		mstArray = graph1.mstFill(matrixEdge, n);
		System.out.println();
		long tEndListQuickKrusk = System.currentTimeMillis();
		long elapsedListQuickKrusk = tEndListQuickKrusk - tStartListQuickKrusk;
		
		totalWeight = graph1.totalWeight(mstArray);
		System.out.println("Total weight of MST using Kruskal: "+totalWeight);
		System.out.println("Runtime: "+elapsedListQuickKrusk+ " milliseconds");
		System.out.println();
		
		MstHeap theMst =  new MstHeap(n);
		MstHeap theMst2 = new MstHeap(n);
		
		//Timing for Prim(Matrix)
		long tStartMatrixPrim = System.currentTimeMillis();
		graph1.edgeFillMatrix(matrixEdge);
		System.out.println("===================================");
		System.out.println("PRIM WITH ADJACENCY MATRIX");
		theMst.prim(n, matrixEdge);
		long tEndMatrixPrim = System.currentTimeMillis();
		long elapsedMatrixPrim = tEndMatrixPrim - tStartMatrixPrim;
		
		totalWeight = graph1.totalWeight(mstArray);
		System.out.println("Total weight of MST using Prim: "+totalWeight);
		System.out.println("Runtime: "+elapsedMatrixPrim+ " milliseconds");
		System.out.println();
		
		
		
		
		//Timing for Prim(List)
		long tStartListPrim = System.currentTimeMillis();
		graph1.edgeFillList(matrixEdge);
		System.out.println("===================================");
		System.out.println("PRIM WITH ADJACENCY LIST");
		theMst2.prim(n,matrixEdge);
		long tEndListPrim = System.currentTimeMillis();
		long elapsedListPrim = tEndListPrim - tStartListPrim;
		
		totalWeight = graph1.totalWeight(mstArray);
		System.out.println("Total weight of MST using Prim: "+totalWeight);
		System.out.println("Runtime: "+elapsedListPrim+ " milliseconds");
		System.out.println();
		
		

		
	
	}//End if case (n<10)
		
		/*    [Project Output for Project Parts 1-2]
		
		//Timing for insertion sort(Matrix)
		long tStartMatrixInsert = System.currentTimeMillis();
		graph1.edgeFillMatrix(matrixEdge);
		graph1.insertionSort(matrixEdge);
		long tEndMatrixInsert = System.currentTimeMillis();
		long elapsedMatrixInsert = tEndMatrixInsert - tStartMatrixInsert;
		
		System.out.println("===================================");
		System.out.println("SORTED EDGES WITH MATRIX USING INSERTION SORT");
		graph1.edgePrint(matrixEdge);
		System.out.println();
		totalWeight = graph1.totalWeight(matrixEdge);
		System.out.println("Total weight = "+totalWeight);
		System.out.println("Runtime: "+elapsedMatrixInsert+ " milliseconds");
		System.out.println();
		
		//Caveat for Countsort algorithm
		largestElement = matrixEdge.get(matrixEdge.size()-1).weight();
		
		//Timing for count sort(Matrix)
		long tStartMatrixCount = System.currentTimeMillis();
		graph1.edgeFillMatrix(matrixEdge);
		graph1.countSort(matrixEdge, largestElement);
		long tEndMatrixCount = System.currentTimeMillis();
		long elapsedMatrixCount = tEndMatrixCount - tStartMatrixCount;
		
		System.out.println("===================================");
		System.out.println("SORTED EDGES WITH MATRIX USING COUNT SORT");
		graph1.edgePrint(matrixEdge);
		System.out.println();
		totalWeight = graph1.totalWeight( matrixEdge);
		System.out.println("Total weight = "+totalWeight);
		System.out.println("Runtime: "+elapsedMatrixCount+" milliseconds");
		System.out.println();
		
		
		
		//Timing for QuickSort(Matrix)
		long tStartMatrixQuick = System.currentTimeMillis();
		graph1.edgeFillMatrix(matrixEdge);
		graph1.quickSort(matrixEdge, 0, matrixEdge.size() -1);
		long tEndMatrixQuick = System.currentTimeMillis();
		long elapsedMatrixQuick = tEndMatrixQuick - tStartMatrixQuick;
		
		
		System.out.println("===================================");
		System.out.println("SORTED EDGES WITH MATRIX USING QUICKSORT");
		System.out.println();
		graph1.edgePrint(matrixEdge);
		System.out.println();
		totalWeight = graph1.totalWeight(matrixEdge);
		System.out.println("Total weight = "+ totalWeight);
		System.out.println("Runtime: "+elapsedMatrixQuick+" milliseconds");	
		System.out.println();
	
	
		//Timing for insertion sort(List)
		long tStartListInsert = System.currentTimeMillis();
		graph1.edgeFillList(matrixEdge);
		graph1.insertionSort(matrixEdge);
		long tEndListInsert = System.currentTimeMillis();
		long elapsedListInsert = tEndListInsert - tStartListInsert;
				
		
		System.out.println("===================================");
		System.out.println("SORTED EDGES WITH LIST USING INSERTION SORT");
		graph1.edgePrint(matrixEdge);
		System.out.println();
		totalWeight = graph1.totalWeight(matrixEdge);
		System.out.println("Total weight = "+totalWeight);
		System.out.println("Runtime: "+elapsedListInsert+ " milliseconds");
		System.out.println();
		
		
		//Timing for Count Sort(List)
		long tStartListCount = System.currentTimeMillis();
		graph1.edgeFillList(matrixEdge);
		graph1.countSort(matrixEdge,largestElement);
		long tEndListCount = System.currentTimeMillis();
		long elapsedListCount = tEndListCount - tStartListCount;
						
				
		System.out.println("===================================");
		System.out.println("SORTED EDGES WITH LIST USING COUNT SORT");
		graph1.edgePrint(matrixEdge);
		System.out.println();
		totalWeight = graph1.totalWeight(matrixEdge);
		System.out.println("Total weight = "+totalWeight);
		System.out.println("Runtime: "+elapsedListCount+ " milliseconds");
		System.out.println();
		
		
		//Timing for QuickSort(List)
		long tStartListQuick = System.currentTimeMillis();
		graph1.edgeFillList(matrixEdge);
		graph1.quickSort(matrixEdge, 0, matrixEdge.size() -1);
		long tEndListQuick = System.currentTimeMillis();
		long elapsedListQuick = tEndListQuick - tStartListQuick;
	
				
		System.out.println("===================================");
		System.out.println("SORTED EDGES WITH LIST USING QUICKSORT");
		System.out.println();
		graph1.edgePrint(matrixEdge);
		System.out.println();
		totalWeight = graph1.totalWeight(matrixEdge);
		System.out.println("Total weight = "+ totalWeight);
		System.out.println("Runtime: "+elapsedListQuick+" milliseconds");
		System.out.println();
		
	}
	*/
	//Holder for test information prints
	else{ // n>=10
	
		//Timing for Graph Generation
		long tStart = System.currentTimeMillis();
		Graph graph1 = new Graph(n,seed,p);
		long tEnd = System.currentTimeMillis();
		long elapsed = tEnd - tStart;
		
		System.out.println("TEST: "+"n="+n+","+" seed="+seed+","+" p="+p);
		System.out.println("Time to generate the graph: "+ elapsed+" milliseconds");
		
		
		//Timing for insertion sort(Matrix)
		long tStartMatrixInsert = System.currentTimeMillis();
		graph1.edgeFillMatrix(matrixEdge);
		graph1.insertionSort(matrixEdge);
		mstArray.clear();
		mstArray = graph1.mstFill(matrixEdge, n);
		long tEndMatrixInsert = System.currentTimeMillis();
		long elapsedMatrixInsert = tEndMatrixInsert - tStartMatrixInsert;
		
		System.out.println("===================================");
		System.out.println("KRUSKAL WITH MATRIX USING INSERTION SORT");
		System.out.println();
		totalWeight = graph1.totalWeight(mstArray);
		System.out.println("Total weight of MST using Kruskal: "+ totalWeight);
		System.out.println("Runtime: "+ elapsedMatrixInsert +" milliseconds");
		System.out.println();
		
		
		//Caveat for Countsort algorithm
		largestElement = matrixEdge.get(matrixEdge.size()-1).weight();
		
		//Timing for Count sort(Matrix)
		long tStartMatrixCount = System.currentTimeMillis();
		graph1.edgeFillMatrix(matrixEdge);
		graph1.countSort(matrixEdge, largestElement);
		mstArray.clear();
		mstArray = graph1.mstFill(matrixEdge, n);
		long tEndMatrixCount = System.currentTimeMillis();
		long elapsedMatrixCount = tEndMatrixCount - tStartMatrixCount;
		
		System.out.println("===================================");
		System.out.println("KRUSKAL WITH MATRIX USING COUNT SORT");
		System.out.println();
		totalWeight = graph1.totalWeight(mstArray);
		System.out.println("Total weight of MST using Kruskal: "+ totalWeight);
		System.out.println("Runtime: "+ elapsedMatrixCount +" milliseconds");
		System.out.println();
		
		//Timing for QuickSort(Matrix)
		long tStartMatrixQuick = System.currentTimeMillis();
		graph1.edgeFillMatrix(matrixEdge);
		graph1.quickSort(matrixEdge, 0, matrixEdge.size()-1);
		mstArray.clear();
		mstArray = graph1.mstFill(matrixEdge, n);
		long tEndMatrixQuick = System.currentTimeMillis();
		long elapsedMatrixQuick = tEndMatrixQuick - tStartMatrixQuick;
		
		System.out.println("===================================");
		System.out.println("KRUSKAL WITH MATRIX USING QUICKSORT");
		System.out.println();
		totalWeight = graph1.totalWeight(mstArray);
		System.out.println("Total weight of MST using Kruskal: "+ totalWeight);
		System.out.println("Runtime: "+ elapsedMatrixQuick +" milliseconds");
		System.out.println();
		
		
		//Timing for insertion sort(List)
		long tStartListInsert = System.currentTimeMillis();
		graph1.edgeFillList(matrixEdge);
		graph1.insertionSort(matrixEdge);
		mstArray.clear();
		mstArray = graph1.mstFill(matrixEdge, n);
		long tEndListInsert = System.currentTimeMillis();
		long elapsedListInsert = tEndListInsert - tStartListInsert;
		
		System.out.println("===================================");
		System.out.println("KRUSKAL WITH LIST USING INSERTION SORT");
		System.out.println();
		totalWeight = graph1.totalWeight(mstArray);
		System.out.println("Total weight of MST using Kruskal: "+ totalWeight);
		System.out.println("Runtime: "+ elapsedListInsert +" milliseconds");
		System.out.println();
		
		
		//Timing for Count sort(List)
		long tStartListCount = System.currentTimeMillis();
		graph1.edgeFillList(matrixEdge);
		graph1.countSort(matrixEdge, largestElement);
		mstArray.clear();
		mstArray = graph1.mstFill(matrixEdge, n);
		long tEndListCount = System.currentTimeMillis();
		long elapsedListCount = tEndListCount - tStartListCount;
		
		System.out.println("===================================");
		System.out.println("KRUSKAL WITH LIST USING COUNT SORT");
		System.out.println();
		totalWeight = graph1.totalWeight(mstArray);
		System.out.println("Total weight of MST using Kruskal: "+ totalWeight);
		System.out.println("Runtime: "+ elapsedListCount +" milliseconds");
		System.out.println();
		
		//Timing for Quicksort(List)
		long tStartListQuick = System.currentTimeMillis();
		graph1.edgeFillList(matrixEdge);
		graph1.quickSort(matrixEdge, 0, matrixEdge.size()-1);
		mstArray.clear();
		mstArray = graph1.mstFill(matrixEdge, n);
		long tEndListQuick = System.currentTimeMillis();
		long elapsedListQuick = tEndListQuick - tStartListQuick;
		
		System.out.println("===================================");
		System.out.println("KRUSKAL WITH LIST USING QUICKSORT");
		System.out.println();
		totalWeight = graph1.totalWeight(mstArray);
		System.out.println("Total weight of MST using Kruskal: "+ totalWeight);
		System.out.println("Runtime: "+ elapsedListQuick +" milliseconds");
		System.out.println();
				
		
		
		MstHeap primHeap = new MstHeap(n);
		MstHeap primHeap2 = new MstHeap(n);
		
		
		//Timing for Prim(Matrix)
		long tStartMatrixPrim = System.currentTimeMillis();
		graph1.edgeFillMatrix(matrixEdge);
		System.out.println("===================================");
		System.out.println("PRIM WITH ADJACENCY MATRIX");
		primHeap.prim(n, matrixEdge);
		long tEndMatrixPrim = System.currentTimeMillis();
		long elapsedMatrixPrim = tEndMatrixPrim - tStartMatrixPrim;
		
		totalWeight = graph1.totalWeight(mstArray);
		System.out.println("Total weight of MST using Prim: "+totalWeight);
		System.out.println("Runtime: "+elapsedMatrixPrim+ " milliseconds");
		System.out.println();
		
		//Timing for Prim(List)
		long tStartListPrim = System.currentTimeMillis();
		graph1.edgeFillList(matrixEdge);
		System.out.println("===================================");
		System.out.println("PRIM WITH ADJACENCY LIST");
		primHeap2.prim(n,matrixEdge);
		long tEndListPrim = System.currentTimeMillis();
		long elapsedListPrim = tEndListPrim - tStartListPrim;
		
		totalWeight = graph1.totalWeight(mstArray);
		System.out.println("Total weight of MST using Prim: "+totalWeight);
		System.out.println("Runtime: "+elapsedListPrim+ " milliseconds");
		System.out.println();
		
		
		
		/*	 [Output for Project Parts 1-2]

		//Timing for insertion sort(Matrix)
		long tStartMatrixInsert = System.currentTimeMillis();
		graph1.edgeFillMatrix(matrixEdge);
		graph1.insertionSort(matrixEdge);
		long tEndMatrixInsert = System.currentTimeMillis();
		long elapsedMatrixInsert = tEndMatrixInsert - tStartMatrixInsert;
		
		System.out.println("===================================");
		System.out.println("SORTED EDGES WITH MATRIX USING INSERTION SORT");
		System.out.println();
		totalWeight = graph1.totalWeight(matrixEdge);
		System.out.println("Total weight = "+ totalWeight);
		System.out.println("Runtime: "+ elapsedMatrixInsert +" milliseconds");
		System.out.println();
		
		
		//Caveat for Countsort algorithm
		largestElement = matrixEdge.get(matrixEdge.size()-1).weight();
		
		//Timing for count sort(Matrix)
		long tStartMatrixCount = System.currentTimeMillis();
		graph1.edgeFillMatrix(matrixEdge);
		graph1.countSort(matrixEdge, largestElement);
		long tEndMatrixCount = System.currentTimeMillis();
		long elapsedMatrixCount = tEndMatrixCount - tStartMatrixCount;
		
		
		System.out.println("===================================");
		System.out.println("SORTED EDGES WITH MATRIX USING COUNT SORT");
		System.out.println();
		totalWeight = graph1.totalWeight(matrixEdge);
		System.out.println("Total weight = "+totalWeight);
		System.out.println("Runtime: "+elapsedMatrixCount+" milliseconds");
		System.out.println();
		
		
		//Timing for QuickSort(Matrix)
		long tStartMatrixQuick = System.currentTimeMillis();
		graph1.edgeFillMatrix(matrixEdge);
		graph1.quickSort(matrixEdge, 0, matrixEdge.size() -1);
		long tEndMatrixQuick = System.currentTimeMillis();
		long elapsedMatrixQuick = tEndMatrixQuick - tStartMatrixQuick;
		
		
		System.out.println("===================================");
		System.out.println("SORTED EDGES WITH MATRIX USING QUICKSORT");
		System.out.println();
		totalWeight = graph1.totalWeight(matrixEdge);
		System.out.println("Total weight = "+totalWeight);
		System.out.println("Runtime: "+elapsedMatrixQuick+" milliseconds");
		System.out.println();
		
		
		//Timing for insertion sort(List)
		long tStartListInsert = System.currentTimeMillis();
		graph1.edgeFillList(matrixEdge);
		graph1.insertionSort(matrixEdge);
		long tEndListInsert = System.currentTimeMillis();
		long elapsedListInsert = tEndListInsert - tStartListInsert;
				
		System.out.println("===================================");
		System.out.println("SORTED EDGES WITH LIST USING INSERTION SORT");
		System.out.println();
		totalWeight = graph1.totalWeight(matrixEdge);
		System.out.println("Total weight = "+ totalWeight);
		System.out.println("Runtime: "+ elapsedListInsert +" milliseconds");
		System.out.println();
		
		//Timing for count sort(List)
		long tStartListCount = System.currentTimeMillis();
		graph1.edgeFillList(matrixEdge);
		graph1.countSort(matrixEdge, largestElement);
		long tEndListCount = System.currentTimeMillis();
		long elapsedListCount = tEndListCount - tStartListCount;
				
				
		System.out.println("===================================");
		System.out.println("SORTED EDGES WITH LIST USING COUNT SORT");
		System.out.println();
		totalWeight = graph1.totalWeight(matrixEdge);
		System.out.println("Total weight = "+totalWeight);
		System.out.println("Runtime: "+elapsedListCount+" milliseconds");
		System.out.println();
		
		//Timing for QuickSort(List)
		long tStartListQuick = System.currentTimeMillis();
		graph1.edgeFillList(matrixEdge);
		graph1.quickSort(matrixEdge, 0, matrixEdge.size() -1);
		long tEndListQuick = System.currentTimeMillis();
		long elapsedListQuick = tEndListQuick - tStartListQuick;
				
				
		System.out.println("===================================");
		System.out.println("SORTED EDGES WITH LIST USING QUICKSORT");
		System.out.println();
		totalWeight = graph1.totalWeight(matrixEdge);
		System.out.println("Total weight = "+totalWeight);
		System.out.println("Runtime: "+elapsedListQuick+" milliseconds");
		System.out.println();
		*/
	}//End else
	
	
}//End main


}//End class
