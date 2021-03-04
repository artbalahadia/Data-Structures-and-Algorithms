
package homework;
import java.util.Arrays;

import algs41.Graph;
import algs41.GraphGenerator;
import stdlib.*;

/**
 * class socialNetwork   version 1.0
 * 
 * computes several 'social Network' (graph) characteristics
 * Terms: the popularity of a vertex is simply its degree
 *        vertices are 'friends' if they are directly connected
 * 
 * toCompute:   (each characteristic is defined in the corresponding functions below)
 * 
 - numberOfIsoTrips     
 - avgBalanceRatio                               
 - indirectPopularity
 - socialRank
 - numberOfAboveAverageFriendships  
 *
 * There are two levels to this assignment.
 * B-Level
 *     Complete the methods marked ToDo.
 * A-Level
 *     Complete ToDo5-A in the  balancedFriendships method
 * 
 * You may add additional instance methods to the SocialNetwork class to facilitate completing the ToDos
 * You may NOT add any additional instance variables to the SocialCircles class
 * You may NOT change the Graph class.
 * You may use basic Java arrays, however you may NOT use any additional data structures without permission (e.g. queue)
 * 
 * Note: you are free to change main; we will test your class using a different driver.
 * Some test graphs (with answers) are included in main; you are encouraged to create additional test graphs
 * using the textbook file format ( see data/tinyG.txt  for an example)
 * OR create small simpleConnected graphs using the GraphGenerator class (view with graphViz?) and verify your functions
 * compute the correct values
 * 
 */

/* this class employs the Authors design pattern. 
 * the class constructor invokes methods to compute values for the instance variables
 * which can be accessed by the client through the accessor methods
 */

public class SocialNetwork {
	private int numIsoTrips;                       // the number of isolated triples in the graph
	private int[] indirectPopularity;			   // the indirectPopularity of each vertex
	private double avgBalanceRatio;                // the average of all edge balance ratios
	private int numberOfAboveAverageFriendships;   // the number of 'above average' friendships
	private int [] socialRank;	                   // the social rank of each vertex

	// accessor functions
	public int getIndirectPopularity(int v) {	// 	   getIndirectPopularity of vertex v
		return indirectPopularity[v];
	}
	public int getNumberOfIsoTrips() {	     
		return numIsoTrips;
	}
	public double getAvgBalanceRatio() {       
		return avgBalanceRatio;
	}
	public int getNumberOfAboveAverageFriendships() {
		return numberOfAboveAverageFriendships;
	}
	public int getSocialRank(int v) {    // get getSocialRank of vertex v
		return socialRank[v];
	}

	// ---end accessors

	/**
	 * utility functions
	 * 
	 * Suggestion.  copy the degree function (or see if you can write it from scratch) from the textbook.
	 *              you may find it a useful utility function for your functions
	 *              
	 *              put other utilities you create here as well
	 */
	
    public int degree(Graph G, int v) {
		if (v < 0 || v >= G.V()) throw new IndexOutOfBoundsException();
		return G.degree(v);
    }

	/**
	 * CountNumberOfIsoTrips
	 * 
	 * determine how many groups of 3 vertices (u,v,w) form an Isolated Triple - 
	 *     that is: 3 vertices directly connected only to each other
	 * Each group should be counted only one time.
	 * 
	 * the functions stores the computed result in the  numberOfIsoTrips  instance variable
	 */

	private void countNumberOfIsoTrips(Graph G) {
		/*
		 * For every vertex with degree of 2, check if u is adj to v and is adj to x (in memory of w)
		 * Divide by 3 to disregard duplicates
		 */
		int x = 0;		
		for(int u = 0; u < G.V(); u++) {
			if(this.degree(G, u) == 2) {
				for(int v : G.adj(u)) {					
					if(this.degree(G, v) == 2) {
						if(v == x) {
							StdOut.println(u + " " + v + " " + x);
							numIsoTrips++;
							break;
						}
						for(int w : G.adj(v)) {							
							if(w == u) {
								continue;
							} else {
								x = w;
								break;
							}
						}
					}
				}
			}
		}
		numIsoTrips = numIsoTrips / 3; // ToDo 1    fix this
	}
	

	/**
	 * determineIndirectPopularity
	 * 
	 * concept:  people accrue social status from the people they associate with.
	 * and while an individual may not be 'popular', they may achieve some level
	 * of popularity - indirectly - through the popularity of their friends. This
	 * is the notion of 'indirectPopularity'
	 * 
	 * for each vertex v, determine the average popularity of its friends, rounded down.
	 *    The popularity of each friend of v should NOT include v itself
	 * 
	 * 
	 *   the IndirectPopularity of a vertex with no friends is 0
	 *   
	 *   store the answer in indirectPopularity[v]
	 */

	private void determineIndirectPopularity(Graph G) {
		/*
		 * numFriends - counts the total number of friends of v
		 * totalDegFriends - sums up degree of friends
		 */
		for (int v = 0; v < G.V(); v++ ) {
			int totalDegFriends = 0, numFriends = 0;
			for(int w : G.adj(v)) {
				numFriends++;
				totalDegFriends += (this.degree(G, w) - 1);
			}
			 indirectPopularity[v] = totalDegFriends/numFriends;  // ToDo 2 fix this, compute the indirect Popularity of every v
		}
	}
	/**
	 * socialRank
	 * 
	 * for each vertex v, determine how many vertices have degree less than v
	 *     "how many people are less popular than v?"
	 *     store the answer in socialRank[v] 
	 */
	private  void determineSocialRank(Graph G) {
		for (int v = 0; v < G.V(); v++ ) {
			int howPopIsV = this.degree(G, v);
			int lessPop = 0;
			// Increment lessPop for every vertex with less degree than v
			for(int x = 0; x < G.V(); x++) {
				if(this.degree(G, x) < howPopIsV) {
					lessPop++;
				}
			}
			socialRank[v] = lessPop;  		    // ToDo 3 fix this, compute the social Rank of every v
		}
	}
	/**
	 * balancedFriendships
	 * 
	 * say the 'balanceRatio' of a friendship is the ratio between the two friends'
	 *          popularities, with the larger popularity as the denominator.
	 *          If two friends have the same popularity, then the balanceRatio would
	 *          be 1.  If one of the friends had N total friends and the other had 1, then the
	 *          balanceRatio would be 1/N - it would be a 'lopsided' friendship
	 *                            
	 *         the average balanceRatio for a graph is the average of all the balanceRatios for all the edges in the Graph
	 *         (note it would always be >=0)
	 * 
	 * B-Level:
	 *     determine the average balanceRatio for the graph.
	 *     store the answer in the avgBalanceRatio instance variable
	 * 
	 * A-Level
	 * 
	 *      determine the number of 'friendships' for which the balanceRatio is greater than the average Balance ratio for the graph
	 *      store the answer in the numberOfAboveAverageFriendships instance variable
	 *      
	 *      this level is optional.  if you choose NOT to complete it, simply leave the assignment 
	 *      statement to   numberOfAboveAverageFriendships    as given below.
	 * 
	 * Example:  if all vertices have the same number of friends, then all balanceRatios would be 1 
	 *           and so the averageBalanceRatio would be 1
	 *
	 *           A-Level:   since all balanceRatios are the same, none would be aboveAverage
	 *                      the numberOfAboveAverageFriendships would be 0
	 *           
	 * Example:  if one vertex 'a' was connected to 5 other vertices (b,c,d,e,f) and b-c-d-e-f made a cycle
	 *           edges, then a's popularity would be 5, all the others would be 3.  The balanceRatio for
	 *           all 5 friendships involving 'a' would be  3/5; for the other 5 friendships it would be 1
	 *           so the averageBalanceRatio would 4/5
	 *           
	 *           A-Level:   numberOfAboveAverageFriendships would be 5.
	 * 
	 */
	private void balancedFriendships(Graph G) {
		/*
		 * balanceRatio - ratio between two adjacent vertices (degV/degW or vice-versa)
		 * total - total ratios of all edges
		 * x - total number of edges
		 */
		double balanceRatio = 0, total = 0;
		int x = 0; 
		for(int v = 0; v < G.V(); v++) {
			double degV = this.degree(G, v);
			for(int w : G.adj(v)) {
				x++;		
				double degW = this.degree(G, w);
				if(degV > degW) {
					balanceRatio = degW/degV;
				} else {
					balanceRatio = degV/degW;
				}
				total += balanceRatio;	
			}
			//StdOut.println(balanceRatio);
			avgBalanceRatio = total/x;   // ToDo 4  fix this
			if(balanceRatio > avgBalanceRatio) {
				numberOfAboveAverageFriendships += 1;    // toDo5-A  optional A Level  fix this 	
			}
		}
	}

	// the constructor  instantiates all instance variables and
	//     calls methods to compute their values for the input graph G
	//     nothing for you to change here

	public SocialNetwork(Graph G) {
		indirectPopularity = new int[G.V()];
		socialRank = new int[G.V()];
		countNumberOfIsoTrips(G);
		determineIndirectPopularity(G);
		determineSocialRank(G);
		balancedFriendships(G);
	}

	// test client
	//
	// use this test client to test your SocialCircles class
	// to perform a test, select an input graph by commenting it  "in" and the others "out" below

	public static void main(String[] args) {

		// the B-level answers for each graph given below are included in a comment in the right margin
		// in order:  # iosTrips, average indirect popularity, average social rank, avgBalanceRatio
		// * means the answer is an approximation due to the random nature of the corresponding graph

		In in = new In("data/tinyG.txt" );
		//Graph G = GraphGenerator.fromIn (in);		        // 0 , 1.31 , 4.54  , 0.65
		//Graph G = GraphGenerator.complete(4);  			// 0 , 2    , 0.0   , 1.0
		//Graph G = GraphGenerator.cycle(8);	            // 0 , 1    , 0.0   , 1.0
		//Graph G = GraphGenerator.binaryTree(15);			// 0 , 1.33 , 4.13  , 0.57
		//Graph G = completeBipartite(1,6);                 // 0 , 4.29 , 0.86  , 0.17
		Graph G = isolatedTripGraph(20, 3);		        // 3 , 2-3*, 6.80* , 0.79*


		// the following are 'random' graphs, so answers may vary due to randomness
		// 'answers' provided are approximate
		//Graph G  = GraphGenerator.simpleConnected(50,200); //  0,  4.?   , 20-22  , 0.6-0.7                              
		//Graph G = erRandom(100,0.1);                       //  0,  9-11  , 44-45  , 0.7-0.76

		//StdOut.println(G);       // uncomment to have the graph adj-list printed
		//G.toGraphviz ("tinyG.png");    // uncomment to get a picture of the graph

		// create the SocialNetwork instance using the graph G
		SocialNetwork community = new SocialNetwork(G);

		// display the results computed by the SocialNetwork instance

		StdOut.format("The number of isolated triples is         %d\n", community.getNumberOfIsoTrips());
		double averageIP =0.0;

		for (int v=0; v < G.V(); v++) {
			averageIP += community.getIndirectPopularity(v);
		}
		averageIP /= G.V();
		StdOut.format("Average indirect Popularity:  %5.2f \n", averageIP);

		// determine the average social rank
		double averageSR=0.0;
		for (int v=0; v < G.V(); v++) {
			averageSR+= community.getSocialRank(v);
		}
		averageSR /= G.V();
		StdOut.format("Average social rank:          %5.2f \n", averageSR);

		StdOut.format("The average balance ratio is  %5.2f\n", community.getAvgBalanceRatio());
		StdOut.format("\nA Level: # of above average Friendships is %d\n", community.getNumberOfAboveAverageFriendships() );
	}

	// Some additional graphs for testing purposes
	//
	public static Graph completeBipartite(int m, int n) {
		Graph G = new Graph(n+m);
		for (int i=0; i < n; i++ )
			for (int j=n; j < n+m; j++) {
				G.addEdge(i, j);
			}
		return G;
	}
	/* 
	 * create a graph with V vertices and numTrips number of isolated triples for testing purposes
	 */
	public static Graph isolatedTripGraph(int V, int numTrips) {
		if ( numTrips > V/3)  throw new   IllegalArgumentException(" isolatedGraph argument error");
		Graph G = new Graph(V);
		for (int i = 0; i < numTrips; i++ ) {
			int v1 = 3* i;
			int v2 = v1+1;
			int v3 = v2 + 1;
			G.addEdge(v1, v2);
			G.addEdge(v2, v3);
			G.addEdge(v3, v1);
		}
		int nv = 3*numTrips;
		for ( int u = nv; u < V; u++) 
			for (int v = u+1; v < V; v++) {
				if ( StdRandom.uniform()>0.5) G.addEdge(u, v);
			}

		return G;
	}

	//Erdos-Renyi  (N,p) random graph  
	public static Graph erRandom(int V, double p) {   
		if (V < 0 || p < 0) throw new IllegalArgumentException ();   
		Graph G = new Graph (V);   
		for ( int v = 0; v < V; v++)     
			for (int w = v+1; w < V; w++)    
				if ( StdRandom.uniform() <= p)     
					G.addEdge(v, w);       
		return G; 
	} 
}

