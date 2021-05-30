package BinarySearchTree;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

//-------------------------------------------------------------------------
/**
 * Test class for Doubly Linked List
 *
 * @version 3.1 09/11/15 11:32:15
 *
 * @author TODO
 */

@RunWith(JUnit4.class)
public class BSTTest {

	// TODO write more tests here.

	/**
	 * <p>
	 * Test {@link BST#prettyPrintKeys()}.
	 * </p>
	 */

	@Test
	public void testContains() {
		BST<Integer, Integer> bst = new BST<Integer, Integer>();
		// empty
		assertFalse("Check if if get a key from empty set", bst.contains(5));
		// non-empty
		bst.put(5, 1);
		assertTrue("Check if if get a key from empty set", bst.contains(5));

	}

	@Test
	public void testGet() {
		BST<Integer, Integer> bst = new BST<Integer, Integer>();
		// empty
		assertEquals("Check if if get a key from empty set", null, bst.get(1));
		// non-empty
		bst.put(1, 1);
		assertEquals("Check if if get a key ", "1", bst.get(1).toString());
		bst.put(2, 2);
		bst.put(5, 5);
		bst.put(3, 3);
		assertEquals("Check if if get a key ", "3", bst.get(3).toString());
		bst.put(6, 6);
		bst.put(7, 7);
		assertEquals("Check if if get a key", "5", bst.get(5).toString());

	}

	@Test
	public void testPut() {
		BST<Integer, Integer> bst = new BST<Integer, Integer>();
		bst.put(1, null);
		bst.put(1, 8);
		bst.put(1, 1);
		assertEquals("Check if put 1 will output 1", "1", bst.get(1).toString());
		bst.put(5,5);
		assertEquals("Check if put 5 will output 5", "5", bst.get(5).toString());
		bst.put(0, 0);
		assertEquals("Check if put 0 will output 0", "0", bst.get(0).toString());
	}

	@Test
	public void testHeight() {
		BST<Integer, Integer> bst = new BST<Integer, Integer>();
		// empty
		assertEquals("Check the height", -1, bst.height());
		// one node
		bst.put(1, 1);
		assertEquals("Check the height", 0, bst.height());
		// more nodes
		bst.put(2, 2);
		bst.put(5, 5);
		assertEquals("Check the height", 2, bst.height());
		bst.put(3, 3);
		assertEquals("Check the height", 3, bst.height());
		bst.put(6, 6);
		assertEquals("Check the height", 3, bst.height());
	}

	@Test
	public void testMedian() {
		BST<Integer, Integer> bst = new BST<Integer, Integer>();
		// empty
		assertEquals("Check median null", null, bst.median());
//	System.out.println("med"+bst.median());
		// more nodes
		bst.put(7, 7);
		bst.put(8, 8);
		bst.put(3, 3);
		bst.put(1, 1);
//     System.out.println("med"+bst.median().toString());
		assertEquals("Check median", "3", bst.median().toString());
		bst.put(2, 2);
		bst.put(6, 6);
		bst.put(4, 4);
		bst.put(5, 5);

		// System.out.println("med"+bst.median().toString());
		assertEquals("Check median", "4", bst.median().toString());

	}

	@Test
	public void testPrintKeysInOder() {
		BST<Integer, Integer> bst = new BST<Integer, Integer>();
		// empty
		assertEquals("Check key for empty", "()", bst.printKeysInOrder());
		bst.put(5, 5);
		bst.put(6, 6);
		bst.put(3, 3);
		bst.put(7, 7);
		assertEquals("Check order", "((()3())5(()6(()7())))", bst.printKeysInOrder());
		// System.out.println(bst.printKeysInOrder());

	}

	@Test
	public void testPrettyPrint() {
		BST<Integer, Integer> bst = new BST<Integer, Integer>();
		assertEquals("Checking pretty printing of empty tree", "-null\n", bst.prettyPrintKeys());

		// -7
		// |-3
		// | |-1
		// | | |-null
		bst.put(7, 7); // | | -2
		bst.put(8, 8); // | | |-null
		bst.put(3, 3); // | | -null
		bst.put(1, 1); // | -6
		bst.put(2, 2); // | |-4
		bst.put(6, 6); // | | |-null
		bst.put(4, 4); // | | -5
		bst.put(5, 5); // | | |-null
						// | | -null
						// | -null
						// -8
						// |-null
						// -null

		String result = "-7\n" + " |-3\n" + " | |-1\n" + " | | |-null\n" + " | |  -2\n" + " | |   |-null\n"
				+ " | |    -null\n" + " |  -6\n" + " |   |-4\n" + " |   | |-null\n" + " |   |  -5\n"
				+ " |   |   |-null\n" + " |   |    -null\n" + " |    -null\n" + "  -8\n" + "   |-null\n"
				+ "    -null\n";
//     System.out.println(bst.prettyPrintKeys());
		assertEquals("Checking pretty printing of non-empty tree", result, bst.prettyPrintKeys());

	}

	/**
	 * <p>
	 * Test {@link BST#delete(Comparable)}.
	 * </p>
	 */
	@Test
	public void testDelete() {
		BST<Integer, Integer> bst = new BST<Integer, Integer>();
		bst.delete(1);
		assertEquals("Deleting from empty tree", "()", bst.printKeysInOrder());

		bst.put(1, 1);
		//System.out.println(bst.printKeysInOrder());
		bst.delete(1);
		//System.out.println(bst.printKeysInOrder());
		assertEquals("Deleting one node", "()", bst.printKeysInOrder());

		bst.put(7, 7); // _7_
		bst.put(8, 8); // / \
		bst.put(3, 3); // _3_ 8
		bst.put(1, 1); // / \
		bst.put(2, 2); // 1 6
		bst.put(6, 6); // \ /
		bst.put(4, 4); // 2 4
		bst.put(5, 5); // \
						// 5

		assertEquals("Checking order of constructed tree", "(((()1(()2()))3((()4(()5()))6()))7(()8()))",
				bst.printKeysInOrder());
		// System.out.println(bst.printKeysInOrder());

		bst.delete(9);
		assertEquals("Deleting non-existent key", "(((()1(()2()))3((()4(()5()))6()))7(()8()))", bst.printKeysInOrder());

		bst.delete(8);
		assertEquals("Deleting leaf", "(((()1(()2()))3((()4(()5()))6()))7())", bst.printKeysInOrder());

		bst.delete(6);
		assertEquals("Deleting node with single child", "(((()1(()2()))3(()4(()5())))7())", bst.printKeysInOrder());

		bst.delete(3);
		assertEquals("Deleting node with two children", "(((()1())2(()4(()5())))7())", bst.printKeysInOrder());
		// System.out.println(bst.printKeysInOrder());

		bst.delete(4);
		// System.out.println(bst.printKeysInOrder());
		assertEquals("Deleting node with single child", "(((()1())2(()5()))7())", bst.printKeysInOrder());
	}

}
