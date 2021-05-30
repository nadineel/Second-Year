/*
 * extract from DirectedGraph.java from Princeton website
@author Robert Sedgewick
@author Kevin Wayne

*/

public class DirectedEdge {
	protected final int v;
	protected final int w;
	protected final double weight;

	/**
	 * Initializes a directed edge from vertex {@code v} to vertex {@code w} with
	 * the given {@code weight}.
	 * 
	 * @param v      the tail vertex
	 * @param w      the head vertex
	 * @param weight the weight of the directed edge
	 * @throws IllegalArgumentException if either {@code v} or {@code w} is a
	 *                                  negative integer
	 * @throws IllegalArgumentException if {@code weight} is {@code NaN}
	 */
	public DirectedEdge(int v, int w, double weight) {
		/*
		 * note: not including this bit bc input is given
		 * 
		 * if (v < 0) throw new
		 * IllegalArgumentException("Vertex names must be non-negative integers"); 
		 * if (w< 0) throw new
		 * IllegalArgumentException("Vertex names must be non-negative integers"); 
		 * if(Double.isNaN(weight)) throw new 
		 * IllegalArgumentException("Weight is NaN");
		 */
		this.v = v;
		this.w = w;
		this.weight = weight;
	}
}