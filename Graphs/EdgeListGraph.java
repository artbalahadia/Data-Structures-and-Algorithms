
package homework;
import stdlib.*;

import java.util.Iterator;
import java.util.LinkedList;

import algs13.Queue;


/**
 *  The <code>ELGraph</code> class represents an undirected graph of vertices
 *  named 0 through V-1.   
 *  It uses the EdgeList representation.
 *  
 *  It supports the following operations: 
 *    - addEdge    add an edge to the graph,
 *    - adj        iterate over all of the neighbors adjacent to a vertex.
 *    - delete     remove an edge
 *    - degree     determine the degree of a vertex
 *    - E          return the number of edges
 *    - hasEdge    determine if an edge exists
 *    
 *      Parallel edges and self-loops are NOT permitted.
 */

/*
 * DSII Homework7  version 1.0
 * 
 * Complete the 6 methods marked ToDo
 * For each of the ToDo methods, determine the order of growth of the method in terms of V,E
 * place your answers in the space provided below. You must include a brief justification.
 * 
 * You must not change the declaration of any method.
 * 
 *   Art Balahadia
 *  
 *  Delete one of the following:
 *  A) The work submitted here is solely mine. 
 *  
 *  
 *  Order of growth  answers go here
 *   - addEdge = E ; The implementation uses the hasEdge method to validate if an Edge is already in the list and therefore is dependent to the size of E
 *   - hasEdge = E ; We will iterate through the entire list of edges to check if a specific edge is in the list
 *   - delete = E ; We use the hasEdge method to locate the Edge to delete, once found and if present - we then remove that specific edge from the list.
 *   				The amount of deletions will grow depending on the number of edges in the list, which makes this depenedent to E.
 *   - E = E ; The built-in size() function of theEdges, that is a linkedlist, is O(n) which in this case is n = E
 *   - adj = E ; We iterate through the Edge list to check for a specific v's neighbors and store it in an iterable (Queue)
 *   - degree = E ; Like adj, we iterate and check for a specific v's neighbors but instead we count and return the number of neighbors
 *  
 */


public class ELGraph {   // an EdgeList Graph
	private final int V;				// number of vertices
	private LinkedList<Edge> theEdges;  // the edge list - uses Standard Java LinkedList class

	public class Edge {  // nested class to represent an edge
		public  int u,v;  // the end points of the edge
		public Edge(int u, int v) {  // constructor
			this.u = u;
			this.v = v;
		}
		public boolean equals(Object that) {      
			Edge thet = (Edge)that;
			if ( this == null || that == null ) throw new IllegalArgumentException(" bad edge");
			return ( this.u == thet.u && this.v == thet.v) || (this.u==thet.v && this.v==thet.u);
		}
	}
	/**
	 * Create an empty graph with V vertices.
	 */
	public ELGraph(int V) {
		if (V < 0) throw new Error("Number of vertices must be nonnegative");
		this.V = V;
		theEdges = new LinkedList<Edge>();
	}

	/**
	 * Add the undirected edge v-w to graph.
	 * @throws java.lang.IndexOutOfBoundsException unless both 0 <= v < V and 0 <= w < V
	 * 
	 * enforces No self loops or parallel edges
	 * 
	 * return true if edge was successfully added
	 * return false if edge already existed
	 */
	public boolean addEdge(int v, int w) {
		if (v < 0 || v >= V) throw new IndexOutOfBoundsException();
		if (w < 0 || w >= V) throw new IndexOutOfBoundsException();
		
		Edge newEdge = new Edge(v,w);
		
		// Validates if self loop or parallel edges prior to adding into edge list
		if(this.hasEdge(v, w)) {
			System.out.println(v + " " + w + " is already in our list" );
			return true;
		} else {
			//System.out.println(v + " " + w + " has been added" );
			return theEdges.add(newEdge);
		} 
		//ToDo 1.  implement this method
		//return false;
	}
	/*
	 * hasEdge  
	 *    Determine if the graph has an edge between  u,v
	 */
	public boolean hasEdge(int u,int v) {
		//check bounds of parameters
		if (v < 0 || v >= V) throw new IndexOutOfBoundsException();
		if (u < 0 || u >= V) throw new IndexOutOfBoundsException();
		
		Edge checkEdge = new Edge(u,v);
		
		/*
		 * Solution 1: Using iterator
		 * Iterator i = theEdges.iterator();
		 *
		 *	while(i.hasNext()) {
		 *		if(i.next().equals(checkEdge)) {			// "equals" method of Edge checks for u and v (vice versa)
		 *			return true;
		 *		}
		 *	}
		 */

		// Solution 2: Using forEach
		for(Edge edge : theEdges) {
			if(edge.equals(checkEdge)) {
				return true;
			}
		}

		//ToDo 2.  implement this method
		return false;
	}
	/* delete
	 * 
	 * remove edge between u,v if it exists.
	 * 
	 * returns: 
	 *     true if successful
	 *     false if edge did not exist
	 */

	public boolean delete(int u, int v) {
		//check bounds of parameters
		if (v < 0 || v >= V) throw new IndexOutOfBoundsException();
		if (u < 0 || u >= V) throw new IndexOutOfBoundsException();
		
		Edge delEdge = new Edge(u,v);
				
		if(hasEdge(u,v)) {
			return theEdges.remove(delEdge);
		}	
		//To do 3   implement this method
		return false;
	}
	/*
	 * Return the number of vertices in the graph.
	 */
	public int V() { return V; }

	/*
	 * Return the number of edges in the graph.
	 */
	public int E() { 
		return theEdges.size();   // ToDo 4   implement this method
	}

	/*
	 * adj
	 * 
	 * Return the list of neighbors of vertex v as an Iterable.
	 * @throws java.lang.IndexOutOfBoundsException unless 0 <= v < V
	 */
	public Iterable<Integer> adj(int v) {
		if (v < 0 || v >= V) throw new IndexOutOfBoundsException();
		
		Queue<Integer> myAdj = new Queue<>();	// Iterable list of choice to store neighbors
		
		/*
		* Solution 1: Using iterator
		* Iterator i = theEdges.iterator();
		*
		* while(i.hasNext()) {					// Check each Edge's v and u to capture neighbors
		*	 Edge checkEdge = (Edge) i.next();
		*	 if(checkEdge.v == v) {
		*	 	 myAdj.enqueue(checkEdge.u);
		*	 } else if(checkEdge.u == v) {
		*		 myAdj.enqueue(checkEdge.v);
		*	 }
		* }
		*/
		
		// Solution 2: Using forEach
		for(Edge edge : theEdges) {
			if(edge.v == v) {
				myAdj.enqueue(edge.u);
			} else if(edge.u == v) {
				myAdj.enqueue(edge.v);
			}
		}
		// ToDo 5    implment this method
		return myAdj;
	}

	/**
	 * Returns the degree of vertex {@code v}.
	 *
	 * @param  v the vertex
	 * @return the degree of vertex {@code v}
	 * @throws IllegalArgumentException unless {@code 0 <= v < V}
	 */
	public int degree(int v) {
		if (v < 0 || v >= V) throw new IndexOutOfBoundsException();
		
		int vDegree = 0;
		
		/*
		 * Iterator i = theEdges.iterator();
		 *
		 *	while(i.hasNext()) {
		 *		Edge checkEdge = (Edge) i.next();
		 *		if(checkEdge.v == v || checkEdge.u == v) {
		 *			vDegree++;
		 *		}
		 *	}
		 *
		 */
		
		for(Edge edge : theEdges) {
			if(edge.v == v || edge.u == v) {
				vDegree++;
			}
		}

		return vDegree;  // To Do 6    implement this method
	}
		


	/* perform tests
	 * 
	 * comment in/out as needed
	 */
	public static void main(String[] args) {

		AllAddEdgeTests();
		//adjTest();
		//hasEdgeTest();
		//degreeTest();
		//deleteEdgeTest();
		//size  tests?

	}

	/*
	 * AllAddEdgeTests
	 * 
	 * invoke addEdge tests
	 * 
	 */
	public static void AllAddEdgeTests() {

		String fileName;
		fileName = "data/tinyG.txt";
		// fileName = "data/tinyCG.txt";

		// test addEdge using  fromIn
		StdOut.println( "FromIn test.  graph data from file");
		In in = new In(fileName);
		while (! in.isEmpty()) {
			StdOut.println( in.readLine());
		}

		in = new In(fileName);
		ELGraph G = fromIn(in);   // create graph from data file
		StdOut.println( "actual graph info");
		StdOut.println( G);

		addEdgeTest(fileName,0,4);   // try to add edge that is not there
		addEdgeTest(fileName,0,5);	 // try to add an edge that already there!

		// add your own addEdge tests if you want

	}
	/* addEdgeTest
	 * 
	 * a single test of addEdge
	 * method
	 *      create a graph using fromIn
	 *      invoke addEdge using parameters
	 *      manually compare before and after graph data
	 */
	public static void addEdgeTest(String fileName, int u, int v) {
		In in = new In(fileName);

		ELGraph G = fromIn(in);   // create graph from data file

		//G.toGraphviz ("g.png");    // manually verify that the GraphViz images is correct
		StdOut.format("addEdge test:  file: %s  edge: (%d,%d)\n", fileName,u,v);
		StdOut.println("graph Before");
		StdOut.println(G);         // or the printed graph corresponds to the data file

		if ( G.addEdge(u,v) ) 
			StdOut.format("Success: add edge (%d,%d) \n",u,v);
		else
			StdOut.format("Failure: add edge (%d,%d) \n",u,v);

		//G.toGraphviz ("g.png");    // manually verify that the GraphViz images is correct
		StdOut.println("    graph After");
		StdOut.println(G);         // or the printed graph corresponds to the data file
	}
	/* adjTests
	 * 
	 * check that the adj method is correct
	 * method:  create a graph using fromIn
	 * 		    
	 *          print the graph file info or view graphViz file
	 *          choose a vertexToCheck
	 *          print the adjacency list using  adj method
	 *          manually compare the results
	 *          
	 *  precondition:   addEdge is correct
	 */
	public static void adjTest(){
		String fileName;
		fileName = "data/tinyG.txt";
		//fileName = "data/tinyCG.txt";
		In in = new In(fileName);
		ELGraph G = fromIn(in);   // create graph from data file

		int vertexToCheck = 5;   // try different values for the vertex
		StdOut.format("adj Test   file: %s  vertex to check %d \n", fileName, vertexToCheck);
		StdOut.println("graph info");
		StdOut.println(G);  

		StdOut.println("vertex  adjaceny list"); 
		for (Integer u : G.adj(5) )
			StdOut.println(u);

	}

	/* hasEdgeTests
	 * 
	 * check that the hasEdge method is correct
	 * method:  create a graph using fromIn
	 * 		    
	 *          print the graph file info or view graphViz file
	 *          choose an edge to validate
	 *          print result of function
	 *          manually compare the results
	 *          
	 *  precondition:   addEdge is correct
	 */
	public static void hasEdgeTest() {
		String fileName;
		fileName = "data/tinyG.txt";
		//fileName = "data/tinyCG.txt";
		In in = new In(fileName);
		ELGraph G = fromIn(in);   // create graph from data file

		StdOut.format("hasEdge Test  file: %s  \n", fileName );
		StdOut.println("graph info");
		StdOut.println(G); 
		//G.toGraphviz ("g.png");

		int u = 0, v = 2;   // check for this edge, try different values

		boolean result =  G.hasEdge(u,v);
		StdOut.format(" hasEdge(%d,%d) --> %b   ;  manually check this\n\n", u,v,result);
	}

	/* degreeTest
	 * 
	 * check that the degree method works correctly.
	 * 
	 * method:  create a graph using fromIn
	 * 		    
	 *          print the graph file info or view graphViz file
	 *          choose an vertex to check
	 *          print result of function
	 *          manually compare the results
	 *          
	 *  precondition:   addEdge is correct
	 */
	public static void degreeTest() {
		String fileName;
		fileName = "data/tinyG.txt";
		//fileName = "data/tinyCG.txt";
		In in = new In(fileName);
		ELGraph G = fromIn(in);   // create graph from data file

		int vertexToCheck = 5;   // try different values for the vertex
		StdOut.format("degree Test   file: %s  vertex to check %d \n", fileName, vertexToCheck);
		StdOut.println("graph info");
		StdOut.println(G);  
		//G.toGraphviz ("g.png");

		int result = G.degree(vertexToCheck);
		StdOut.format(" degree(%d) --> %d   ;  manually check this\n\n", vertexToCheck,result);
	}


	/* deleteEdgeTest
	 * 
	 * remove an edge if it exits
	 * method:  create a graph using fromIn
	 * 		    
	 *          print the before graph file info or view graphViz file
	 *          choose an edge to delete
	 *          print status result of function
	 *          print the after graph file info
	 *          manually compare the results
	 *          
	 *  precondition:   addEdge is correct
	 */
	public static void deleteEdgeTest() {
		String fileName;
		fileName = "data/tinyG.txt";
		//fileName = "data/tinyCG.txt";
		In in = new In(fileName);
		ELGraph G = fromIn(in);   // create graph from data file

		StdOut.format("deleteEdge Test  file: %s  \n", fileName );
		StdOut.println("graph Before");
		StdOut.println(G); 
		//G.toGraphviz ("g.png");

		int u = 0, v = 2;   // attempt to delete edge, try different values

		boolean result =  G.delete(u,v);
		StdOut.format(" deleteEdge(%d,%d) --> %b   ;  manually check this\n\n", u,v,result);
		//G.toGraphviz ("g.png");    // manually verify that the GraphViz images is correct
		StdOut.println("    graph After");
		StdOut.println(G);         // or the printed graph corresponds to the data file
	}


	/* **********************************************************************
	 *               utility routines;  look but don't alter                
	 */


	/**
	 * Return a string representation of the graph.
	 */
	public String toString() {
		StringBuilder s = new StringBuilder();
		String NEWLINE = System.getProperty("line.separator");
		s.append(V + " vertices, " + E() + " edges " + NEWLINE);
		for ( Edge e : theEdges ) {
			s.append(e.u + " " + e.v);
			s.append(NEWLINE);
		}
		return s.toString();
	}

	/**
	 * Save a graphviz representation of the graph.
	 * See <a href="http://www.graphviz.org/">graphviz.org</a>.
	 */
	public void toGraphviz(String filename) {
		GraphvizBuilder gb = new GraphvizBuilder ();
		for (int v = 0; v < V; v++) {
			gb.addNode (v);
		}
		for ( Edge e : theEdges ) {
			gb.addEdge(e.u,e.v);
		}
		gb.toFileUndirected (filename);
	}

	/* fromIn
	 * 
	 * create an ELGraph from an input file
	 * 
	 * requires addEdge for correct operation
	 */
	public static ELGraph fromIn (In in) {
		ELGraph G = new ELGraph (in.readInt());
		int E = in.readInt();
		for (int i = 0; i < E; i++) {
			int v = in.readInt();
			int w = in.readInt();
			G.addEdge(v, w);
		}
		return G;
	}

}
