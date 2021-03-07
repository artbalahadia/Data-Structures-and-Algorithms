
package homework;
import stdlib.*;

import java.util.ArrayList;
import java.util.LinkedList;


/**
 *  The <code>GraphPP</code> class represents an undirected graph of vertices
 *  named 0 through V-1.   
 *  It uses an 'enhanced' adjacency list representation. (Q. what is the enhancement? )
 *  
 *  It supports the following operations: 
 *    - addEdge      add an edge to the graph,
 *    - adj          iterate over all of the neighbors adjacent to a vertex.
 *    - addVertex    add a vertex to the graph
 *    - deleteVertex remove a vertex to the graph
 *    - V            return the number of vertices
 *    - E            return the number of edges
 *    - hasEdge      determine if an edge exists
 *    
 *      Parallel edges and self-loops are NOT permitted.
 */

/*
 * DSII Homework9  version 1.0
 * 
 * Complete the 7 methods (0-6) marked To Do
 * For each of the ToDo methods 1-6, determine the order of growth of the method in terms of V,E
 * place your answers in the space provided below. You must include a brief justification.
 * 
 * You must not change the declaration of any method.
 * You may add your own utility instance methods if you want
 * 
 *   Your name goes here
 *  
 *  Delete one of the following:
 *  A) The work submitted here is solely mine. 
 *  B) I had *some* help on the following parts (list them!) of this assignment
 *  
 *  Order of growth  answers go here
 *  addEdge
 *  deleteEdge
 *  hasEdge
 *  addVertex
 *  deleteVertex
 */


public class GraphPP {   // Graph++   - incrementally better than the old Graph
	private int V;				        // number of actual vertices
	private int E;                      // actual number of edges
	private ArrayList<Integer>[] adjE;  // enhanced adjacency list
	private LinkedList<Integer>  av;    // available vertex index list

	/**
	 * Create an empty graph with V vertices.
	 */
	@SuppressWarnings("unchecked")
	public GraphPP(int V) {
		if (V < 0) throw new Error("Number of vertices must be nonnegative");
		this.V = V;
		this.E = 0;
		adjE = new ArrayList[V];                  // ToDo 0.1  Creates an array of ArrayLists with a size of V
		for (int i=0; i < V; i++) {               // ToDo 0.2  Creates an ArrayList of integers for every index in the adjE array
			adjE[i] = new ArrayList<Integer>();
		}
		av = new LinkedList<Integer>();           // ToDo 0.3 Creates a LinkedList of integers to store available vertices
	}

	/*  V
	 *   return the number of vertices in the graph
	 */
	public int V() { return V; }

	/*
	 * Return the number of edges in the graph.
	 */
	public int E() { return E;  }

	/* isValid
	 * 
	 * returns true if the parameter is a valid vertex value, false otherwise
	 */
	private boolean isValid(int v) {
		if ( v < 0 || v >= V) return false;
		return ! av.contains(v);
	}

	/**
	 * Add the undirected edge v-w to graph.
	 * @throws java.lang.IndexOutOfBoundsException unless both 0 <= v < V and 0 <= w < V
	 * 
	 * MUST:
	 *        enforces no self loops or parallel edges
	 *        verify that v,w are valid vertices - throw an exception if not
	 *        
	 * return true if edge was successfully added
	 * return false otherwise
	 */
	public boolean addEdge(int v, int w) {
		if (v < 0 || v >= V) throw new IndexOutOfBoundsException();
		if (w < 0 || w >= V) throw new IndexOutOfBoundsException();
		
		if(isValid(v) && isValid(w) && !adjE[v].contains(w) && !adjE[w].contains(v)) {		// checks all MUSTs as outlined above
			E++;
			adjE[v].add(w);
			adjE[w].add(v);
			return true;
		}
		
		//ToDo 1.  implement this method
		return false;
	}

	/**
	 * delete the undirected edge v-w from the graph
	 * 
	 * MUST:
	 *        enforces no self loops or parallel edges
	 *        verify that v,w are valid vertices - throw an exception if not
	 *        
	 * return true if edge was successfully deleted
	 * return false otherwise
	 */

	public boolean deleteEdge(int v, int w) {
		if (v < 0 || v >= V) throw new IndexOutOfBoundsException();
		if (w < 0 || w >= V) throw new IndexOutOfBoundsException();
		
		if(isValid(v) && isValid(w) && adjE[v].contains(w) && adjE[w].contains(v)) {		// checks all MUSTs as outlined above
			E--;
			adjE[v].remove(w);
			adjE[w].remove(v);
			return true;
		}
		
		//ToDo 2.  implement this method
		return false;
	}
	/*
	 * hasEdge  
	 *    Determine if the graph has an edge between  v,w
	 *    return true if v,w are valid and edge exists
	 *    return false otherwise  (don't throw an exception )
	 */
	public boolean hasEdge(int v,int w) {
		if (v < 0 || v >= V) throw new IndexOutOfBoundsException();
		if (w < 0 || w >= V) throw new IndexOutOfBoundsException();
		
		if(isValid(v) && isValid(w) && adjE[v].contains(w) && adjE[w].contains(v)) {		// checks all MUSTs as outlined above
			return true;
		}
		//ToDo 3.  implement this method
		return false;
	}
	/* add Vertex
	 * 
	 * adds a vertex to the graph.  
	 * the new vertex can come from:
	 *    a) the available vertex list  (if there are any)
	 *    b) resizing the adjE array.  For testing purposes, you only should increase the size by 1!  
	 *   
	 *   return:  the numerical index of the newly added vertex
	 *   
	 *    fair number of issues here
	 */
	@SuppressWarnings("unchecked")
	public int  addVertex() {
		int nv = 0;
		if(av.isEmpty()) {
			nv = V++;
			ArrayList<Integer>[] tempArr = new ArrayList[V];
			for(int i = 0; i < V; i++) {
				adjE[i] = tempArr[i];
			}
			adjE = tempArr;
		} else {
			nv = av.pop();
			adjE[nv] = new ArrayList<Integer>();
		}
		//ToDo 4  implement this method
		return nv;
	}

	/* deleteVertex
	 * 
	 * MUST:
	 *   remove vertex v if it exists AND
	 *   all edges involving vertex v
	 *   vertex v is added to the 'available vertex' list 
	 *   AND there is now 1 fewer vertex in the graph
	 * 
	 * returns: 
	 *     true if successful
	 *     false if v was invalid    //don't throw an exception
	 */

	public boolean deleteVertex(int v) {
		if(adjE[v] != null && v >= 0 && v < V) {
			for(int i = 0; i < V; i++) {
				if(adjE[i].contains(v)) {
					adjE[i].remove(v);
				}
			}
			adjE[v] = null;
			av.add(v);
		}
		//ToDo 5   implement this method
		return false;
	}

	/*
	 * adj
	 * 
	 * Return the list of neighbors of vertex v as an Iterable.
	 * @throws java.lang.IndexOutOfBoundsException for an invalid vertex 
	 */
	public Iterable<Integer> adj(int v) {
		// ToDo 6 fix/implement this method
		if (v < 0 || v >= V) throw new IndexOutOfBoundsException();

		return null;
	}


	/* 
	 * You are responsible for testing.
	 * 
	 * The following is just to get you started
	 * 
	 */
	public static void main(String[] args) {

		try {   // put in a try catch block to test that exceptions are thrown correctly
			constructorTest();
		}
		catch (Exception e) {
			StdOut.println(" Exception occured in constuctor test: " + e.getMessage());;
		}
	}

	/*
	 * Constructor test
	 * 
	 * reads graph info from file:
	 *   prints the file contents
	 *   actual graph data from  adjacency lists
	 *   
	 *   requires correct implementation of  adj method
	 * 
	 */
	public static void constructorTest() {

		String fileName;
		fileName = "data/tinyG.txt";
		// fileName = "data/tinyCG.txt";

		// test constructor using  fromIn
		StdOut.println( "FromIn test.  graph data from file");
		In in = new In(fileName);
		while (! in.isEmpty()) {
			StdOut.println( in.readLine());
		}

		in = new In(fileName);
		GraphPP G = fromIn(in);   // create graph from data file
		StdOut.println( "actual graph info");
		StdOut.println( G);
		//G.toGraphviz ("g.png");

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
		for (int v = 0; v < V; v++) {
			if ( isValid(v)) {
				s.append(v + ": ");
				for (int w : adjE[v]) {
					s.append(w + " ");
				}
				s.append(NEWLINE);
			}
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
			if ( isValid(v)) {
				gb.addNode (v);
				boolean showSelfLoop = false;
				for (int w : adjE[v]) {
					if (v < w) // only once each edge
						gb.addEdge (v, w);
					if (v == w) {
						showSelfLoop = !showSelfLoop;
						if (showSelfLoop)
							gb.addEdge (v, w);
					}
				}
			} // if valid
		}
		gb.toFileUndirected (filename);
	}

	/* fromIn
	 * 
	 * create an GraphPP from an input file
	 * 
	 * requires addEdge for correct operation
	 */
	public static GraphPP fromIn (In in) {
		GraphPP G = new GraphPP (in.readInt());
		int E = in.readInt();
		for (int i = 0; i < E; i++) {
			int v = in.readInt();
			int w = in.readInt();
			G.addEdge(v, w);
		}
		return G;
	}

}
