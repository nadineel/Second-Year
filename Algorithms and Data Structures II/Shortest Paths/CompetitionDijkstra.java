
//@author: Bernadine Lao

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Scanner;

/*
 * A Contest to Meet (ACM) is a reality TV contest that sets three contestants at three random
 * city intersections. In order to win, the three contestants need all to meet at any intersection
 * of the city as fast as possible.
 * It should be clear that the contestants may arrive at the intersections at different times, in
 * which case, the first to arrive can wait until the others arrive.
 * From an estimated walking speed for each one of the three contestants, ACM wants to determine the
 * minimum time that a live TV broadcast should last to cover their journey regardless of the contestants’
 * initial positions and the intersection they finally meet. You are hired to help ACM answer this question.
 * You may assume the following:
 *     Each contestant walks at a given estimated speed.
 *     The city is a collection of intersections in which some pairs are connected by one-way
 * streets that the contestants can use to traverse the city.
 *
 * This class implements the competition using Dijkstra's algorithm
 */

public class CompetitionDijkstra {

	private int sA;
	private int sB;
	private int sC;
	private int intersection;
	private int street;
	private Graph graph;

	/**
	 * @param filename: A filename containing the details of the city road network
	 * @param sA,       sB, sC: speeds for 3 contestants
	 * @throws Exception
	 * @throws NumberFormatException
	 */
	CompetitionDijkstra(String filename, int sA, int sB, int sC) {

		this.sA = sA;
		this.sB = sB;
		this.sC = sC;
		graph = null;

		File file;
		try {
			if (filename != null) {
				file = new File(filename);
				Scanner scan = new Scanner(file);
				intersection = Integer.parseInt(scan.nextLine());
				street = Integer.parseInt(scan.nextLine());
				if (intersection <= Math.sqrt(Integer.MAX_VALUE)) {
					graph = new Graph(intersection, street);
					while (scan.hasNextLine()) {
						try {
							graph.addStreet(scan.nextInt(), scan.nextInt(), scan.nextDouble());
							/*
							 * if (scan.hasNextLine()) { scan.nextLine(); }
							 */
						} catch (Exception e) {

						}
					}
				} else {
					graph = null;
				}
				scan.close();
			}
		} catch (FileNotFoundException e) {
			graph = null;
		}

	}

	/**
	 * @return int: minimum minutes that will pass before the three contestants can
	 *         meet
	 */
	public int timeRequiredforCompetition() {
		if ((sA >= 50) && (sB >= 50) && (sC >= 50) && (sA <= 100) && (sB <= 100) && (sC <= 100) && graph != null) {
			int slowestSpeed = Math.min(sA, Math.min(sB, sC));
//each row holds the shortest path to vertex i in distances
			double[][] distances = new double[graph.V][graph.V];
			for (int i = 0; i < graph.V; i++) {
				distances[i] = getShortestPathToVertex(i);
			}
			double maxD = getMaxDistance(distances);
			if (Double.POSITIVE_INFINITY == maxD || maxD == 0) {
				// System.out.println("No distance");
				return -1;
			}
			// System.out.println("Distance:"+maxD);
			return (int) Math.ceil((maxD * 1000) / slowestSpeed);
		}
		// System.out.println("No distance");
		return -1;
	}

	private double getMaxDistance(double[][] distance) {
		double max = 0;
		for (double[] array : distance) {
			for (double dist : array) {
				if (max < dist)
					max = dist;
			}
		}
		return max;
	}

	private double[] getShortestPathToVertex(int i) {
		double[] distTo = new double[graph.V];
		for (int v = 0; v < graph.V; v++) {
			distTo[v] = Double.POSITIVE_INFINITY;
		}
		distTo[i] = 0.0; // except distTo[i]

//similar to IndexPQ from the book        

		Comparator<Path> comparator = new PathComparator();
		PriorityQueue<Path> pathPQ = new PriorityQueue<Path>(graph.V, comparator);

		pathPQ.add(new Path(i, distTo[i])); // insert vertex i and its weight
		while (!pathPQ.isEmpty()) {
			Path path = pathPQ.poll(); // get first element from PQ(least at the start)
			for (DirectedEdge edge : graph.adj[path.v]) {
				int v = edge.v;
				int w = edge.w;
				if (distTo[w] > distTo[v] + edge.weight) { // if distanceto(head>tail+weight) of the edge
					distTo[w] = distTo[v] + edge.weight; // want shorter path
					if (!foundAnotherStreet(pathPQ, w)) {
						pathPQ.add(new Path(w, distTo[w]));
					} else {
						pathPQ = inCycle(pathPQ, w, distTo[w]);
					}
				}
			}
		}
		return distTo;
	}

	private PriorityQueue<Path> inCycle(PriorityQueue<Path> pathPQ, int w, double newWeight) {
		for (Path Path : pathPQ) {
			if (Path.v == w) {
				pathPQ.remove(Path);
				Path.weight = newWeight;
				pathPQ.add(Path);
				break;
			}
		}
		return pathPQ;
	}

	private boolean foundAnotherStreet(PriorityQueue<Path> pathPQ, int w) {
		for (Path Path : pathPQ) {
			if (Path.v == w)
				return true;
		}
		return false;
	}

}