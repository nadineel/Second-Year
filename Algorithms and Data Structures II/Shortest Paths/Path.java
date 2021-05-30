//@author: Bernadine Lao
//loosely based on the book from Princeton

import java.util.Comparator;

public class Path {
	protected int v;
	protected double weight;

	Path(int v, double weight) {
		this.v = v;
		this.weight = weight;
	}

}

//to sort array of Objects using comparator
class PathComparator implements Comparator<Path> {
	@Override
	public int compare(Path path1, Path path2) {
		return Double.compare(path1.weight, path2.weight);
	}
}