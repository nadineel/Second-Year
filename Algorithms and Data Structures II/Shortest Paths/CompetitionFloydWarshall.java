
//@author: Bernadine Lao

import java.io.File;
import java.io.FileNotFoundException;
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
 * This class implements the competition using Floyd-Warshall algorithm
 */

public class CompetitionFloydWarshall {

	private int sA;
	private int sB;
	private int sC;
	private int intersections; // number of vertices
	private int streets; // number of edges
	double tableWeights[][];// matrix for the edges or weights
	double tableSP[][]; // table of shortest path sequesnce
	private boolean canInitialise = false;

	/**
	 * @param filename: A filename containing the details of the city road network
	 * @param sA,       sB, sC: speeds for 3 contestants
	 */
	CompetitionFloydWarshall(String filename, int sA, int sB, int sC) {

		this.sA = sA;
		this.sB = sB;
		this.sC = sC;
		File file;
		try {
			if (filename != null) {
				file = new File(filename);
				Scanner scan;
				scan = new Scanner(file);
				intersections = Integer.parseInt(scan.nextLine());
				streets = Integer.parseInt(scan.nextLine());
				initialiseTables(scan);

				scan.close();
			}
		} catch (FileNotFoundException e) {
			//canInitialise=false;
		}

	}

	/**
	 * @return int: minimum minutes that will pass before the three contestants can
	 *         meet
	 */
	public int timeRequiredforCompetition() {
		if (intersections >= 1) {
			if ((sA >= 50) && (sB >= 50) && (sC >= 50) && (sA <= 100) && (sB <= 100) && (sC <= 100)
					&& canInitialise == true) {
				getShortestPath();
				double max = 0.0;
				if (canMeet()) {
					for (int i = 0; i < intersections; i++) {
						for (int j = 0; j < intersections; j++) {
							if ((tableWeights[i][j] > max) && (tableWeights[i][j] != Double.POSITIVE_INFINITY)) {
								max = tableWeights[i][j];
							}
						}
					}
					int fastest = Math.min(sA, Math.min(sB, sC));
					// System.out.println("Distance:" + max);
					return (int) Math.ceil((max * 1000) / fastest);
				}
			}
		}
		// System.out.println("No distance");
		return -1;
	}

	// initialise matrix table for the distances(weights)
	public void initialiseTables(Scanner scan) {
		if (intersections <= Math.sqrt(Integer.MAX_VALUE)) {
			canInitialise = true;
			tableWeights = new double[intersections][intersections];
			tableSP = new double[intersections][intersections];

			for (int i = 0; i < intersections; i++) {
				for (int j = 0; j < intersections; j++) {
					tableWeights[i][j] = Double.POSITIVE_INFINITY; // know wont ever go to this value
				}
			}

			for (int i = 0; i < streets; i++) {
				tableWeights[scan.nextInt()][scan.nextInt()] = scan.nextDouble();

			}

			for (int i = 0; i < intersections; i++) {
				tableWeights[i][i] = 0;
			}

			for (int i = 0; i < intersections; i++) {
				for (int j = 0; j < intersections; j++) {
					if (j == i) {
						tableSP[i][j] = 0;
					} else {
						tableSP[i][j] = j;
					}
				}
			}
		} else {
			canInitialise = false;
		}

	}

//this function from Lecture 9: Shortest Path	
	public void getShortestPath() {
		for (int k = 0; k < intersections; k++) {
			for (int i = 0; i < intersections; i++) {
				for (int j = 0; j < intersections; j++) {
					if (tableWeights[i][k] + tableWeights[k][j] < tableWeights[i][j]) {
						tableWeights[i][j] = tableWeights[i][k] + tableWeights[k][j];
						tableSP[i][j] = k;
					}
				}
			}
		}
	}

	public boolean canMeet() {
		for (int i = 0; i < intersections; i++) {
			for (int j = 0; j < intersections; j++) {
				if (tableWeights[i][j] == Double.POSITIVE_INFINITY) {
					return false;
				}
			}
		}
		return true;
	}

}