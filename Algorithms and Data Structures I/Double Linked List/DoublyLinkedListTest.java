package Assign2;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertNull;

import org.junit.Test;
import org.junit.Ignore;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

//-------------------------------------------------------------------------
/**
 * Test class for Doubly Linked List
 *
 * @author Bernadine Lao
 * @version 13/10/16 18:15
 */
@RunWith(JUnit4.class)
public class DoublyLinkedListTest {
	// ~ Constructor ........................................................
	@Test
	public void testConstructor() {
		new DoublyLinkedList<Integer>();
	}

	// ~ Public Methods ........................................................

	// ----------------------------------------------------------
	/**
	 * Check if the insertBefore works
	 */
	@Test
	public void testInsertBefore() {
		// test non-empty list
		DoublyLinkedList<Integer> testDLL = new DoublyLinkedList<Integer>();
		testDLL.insertBefore(0, 1);
		testDLL.insertBefore(1, 2);
		testDLL.insertBefore(2, 3);

		testDLL.insertBefore(0, 4);
		assertEquals("Checking insertBefore to a list containing 3 elements at position 0", "4,1,2,3",
				testDLL.toString());
		
		testDLL.insertBefore(1, 5);
		assertEquals("Checking insertBefore to a list containing 4 elements at position 1", "4,5,1,2,3",
				testDLL.toString());
		testDLL.insertBefore(2, 6);
		assertEquals("Checking insertBefore to a list containing 5 elements at position 2", "4,5,6,1,2,3",
				testDLL.toString());
		testDLL.insertBefore(-1, 7);
		assertEquals(
				"Checking insertBefore to a list containing 6 elements at position -1 - expected the element at the head of the list",
				"7,4,5,6,1,2,3", testDLL.toString());
		testDLL.insertBefore(7, 8);
		assertEquals(
				"Checking insertBefore to a list containing 7 elemenets at position 8 - expected the element at the tail of the list",
				"7,4,5,6,1,2,3,8", testDLL.toString());
		testDLL.insertBefore(700, 9);
		
		assertEquals(
				"Checking insertBefore to a list containing 8 elements at position 700 - expected the element at the tail of the list",
				"7,4,5,6,1,2,3,8,9", testDLL.toString());

		// test empty list
		testDLL = new DoublyLinkedList<Integer>();
		testDLL.insertBefore(0, 1);
		assertEquals(
				"Checking insertBefore to an empty list at position 0 - expected the element at the head of the list",
				"1", testDLL.toString());
//		System.out.println(testDLL.toString());
		testDLL = new DoublyLinkedList<Integer>();
		testDLL.insertBefore(10, 1);
		assertEquals(
				"Checking insertBefore to an empty list at position 10 - expected the element at the head of the list",
				"1", testDLL.toString());
//		System.out.println(testDLL.toString());
		testDLL = new DoublyLinkedList<Integer>();
		testDLL.insertBefore(-10, 1);
		assertEquals(
				"Checking insertBefore to an empty list at position -10 - expected the element at the head of the list",
				"1", testDLL.toString());
//		System.out.println(testDLL.toString());

	}
	
	@Test
	public void testIsEmpty() {
		DoublyLinkedList<Integer> testDLL = new DoublyLinkedList<Integer>();
		assertTrue("Checking if empty", testDLL.isEmpty());
		testDLL.insertBefore(0, 0);
		testDLL.insertBefore(1, 0);
		assertFalse("Checking if not empty", testDLL.isEmpty());
	}

	@Test
	public void testDllSize() {
		DoublyLinkedList<Integer> testDLL = new DoublyLinkedList<Integer>();
		testDLL.insertBefore(0, 0);
		testDLL.insertBefore(1, 0);
		testDLL.insertBefore(2, 0);
		testDLL.insertBefore(3, 0);
		assertEquals("Checking dllSize", 4, testDLL.dllSize());
	}

	@Test
	public void testGet() {
		DoublyLinkedList<Integer> testDLL = new DoublyLinkedList<Integer>();
		assertTrue("is empty?",testDLL.isEmpty());

		assertNull("Check if we get the right data if empty", testDLL.get(2));		
		testDLL.insertBefore(0, 0);
		testDLL.insertBefore(1, 1);
		testDLL.insertBefore(2, 2);
		testDLL.insertBefore(3, 3);
		assertEquals("Check if we get the right data", "2", testDLL.get(2).toString());
		assertNull("Check if we get the right data pos>size", testDLL.get(20));		
		assertNull("Check if we get the right data if pos<size", testDLL.get(-2));		
//		System.out.println(testDLL.dllSize());
	}

	@Test
	public void testDeleteAt() {
		DoublyLinkedList<Integer> testDLL = new DoublyLinkedList<Integer>();
		assertTrue("is empty?",testDLL.isEmpty());
		assertFalse("Check if false when empty", testDLL.deleteAt(4));
		testDLL.insertBefore(0, 1);
		assertTrue("Check if the size =1", testDLL.deleteAt(0));
//		System.out.println(testDLL.dllSize());
		testDLL.insertBefore(1, 1);
		testDLL.insertBefore(2, 2);
		testDLL.insertBefore(3, 3);
		assertTrue("Check if the size decreases whe 2 elements", testDLL.deleteAt(1));
//		System.out.println(testDLL.toString());
		assertTrue("Check if the size decreases whe 2 elements", testDLL.deleteAt(0));
//		System.out.println(testDLL.get(0).toString());
		testDLL.insertBefore(3, 3);
		testDLL.insertBefore(4, 4);
		testDLL.insertBefore(5, 5);
		assertTrue("Check if it works for the tail", testDLL.deleteAt(testDLL.dllSize()-1));

//    	System.out.println(testDLL.dllSize());
//    	System.out.println(testDLL.toString());
		assertTrue("Check if the size decreases", testDLL.deleteAt(1));
//		System.out.println(testDLL.toString());
		assertFalse("Shouldnt do anything if pos<0", testDLL.deleteAt(-1));
//		System.out.println(testDLL.toString());
		assertFalse("Shouldnt do anything if pos>size", testDLL.deleteAt(50));
//    	System.out.println(testDLL.dllSize());

	}

	@Test
	public void testReverse() {
		DoublyLinkedList<Integer> testDLL = new DoublyLinkedList<Integer>();
		
		testDLL.insertBefore(0, 1);
		testDLL.insertBefore(1, 1);
		testDLL.insertBefore(2, 2);
		testDLL.insertBefore(3, 3);
		assertEquals("Normal order", "1,1,2,3", testDLL.toString());
//    	System.out.println(testDLL.toString());
		testDLL.reverse();
//		System.out.println(testDLL.toString());
		assertEquals("Reversed order", "3,2,1,1", testDLL.toString());
//    	System.out.println(testDLL.toString());
		DoublyLinkedList<Integer> testDLL1 = new DoublyLinkedList<Integer>();
		testDLL1.insertBefore(0, 1);
		testDLL1.insertBefore(1, 2);
//		System.out.println(testDLL1.toString());
		testDLL1.reverse();
		assertEquals("Reversed order", "2,1", testDLL1.toString());
//    	System.out.println(testDLL1.toString());
    	testDLL1.reverse();
		assertEquals("Reverse reversed order", "1,2", testDLL1.toString());
//    	System.out.println(testDLL1.toString());
	}

	@Test
	public void testMakeUnique() {
		DoublyLinkedList<Integer> testDLL = new DoublyLinkedList<Integer>();
		testDLL.insertBefore(0, 2);
		testDLL.insertBefore(1, 2);
		testDLL.insertBefore(2, 2);
		testDLL.insertBefore(3, 1);
		testDLL.insertBefore(4, 1);
		testDLL.insertBefore(5, 1);		
//		System.out.println(testDLL.toString());
		testDLL.makeUnique();
//		System.out.println(testDLL.toString());
		testDLL.insertBefore(1, 3);
		System.out.println(testDLL.toString());
//		assertEquals("Unique:", "2,3,1", testDLL.toString());
		
		DoublyLinkedList<Integer> testDLL1 = new DoublyLinkedList<Integer>();
		testDLL1.insertBefore(0, 1);
		testDLL1.insertBefore(1, 2);
		testDLL1.insertBefore(2, 2);
		testDLL1.insertBefore(3, 3);
		testDLL1.insertBefore(4, 1);
		testDLL1.insertBefore(5, 1);
		testDLL1.makeUnique();
		assertEquals("Unique:", "1,2,3", testDLL1.toString());
		
		DoublyLinkedList<Integer> testDLL2 = new DoublyLinkedList<Integer>();
		testDLL2.insertBefore(0, 2);
		testDLL2.insertBefore(1, 2);
		testDLL2.insertBefore(2, 2);
		testDLL2.insertBefore(3, 1);
		testDLL2.insertBefore(4, 1);
		testDLL2.insertBefore(5, 1);
//		System.out.println(testDLL2.toString());
		testDLL2.makeUnique();
//		System.out.println(testDLL2.toString());
		assertEquals("Unique:", "2,1", testDLL2.toString());

	}

	@Test
	public void testPush() {
		DoublyLinkedList<Integer> testDLL = new DoublyLinkedList<Integer>();
		testDLL.push(5);
		assertEquals("Push on empty:", "5", testDLL.toString());
//    	System.out.println(testDLL.toString());
    	testDLL.push(6);
		assertEquals("Push on 1 item:", "6,5", testDLL.toString());
//    	System.out.println(testDLL.toString());
    	testDLL.push(57);
		assertEquals("Push on empty:", "57,6,5", testDLL.toString());
//    	System.out.println(testDLL.toString());
	}

	@Test
	public void testPop() {
		DoublyLinkedList<Integer> testDLL = new DoublyLinkedList<Integer>();
		testDLL.pop();
//		System.out.println(testDLL.toString());
		assertEquals("Pop from empty set:", null, testDLL.pop());
		testDLL.push(5);
		testDLL.push(4);
		testDLL.push(3);
		testDLL.push(2);
		// System.out.println(testDLL.toString());
		testDLL.pop();
		assertEquals("Pop:", "3,4,5", testDLL.toString());
//    	System.out.println(testDLL.toString());
	}

	@Test
	public void testEnqueue() {
		DoublyLinkedList<Integer> testDLL = new DoublyLinkedList<Integer>();
		testDLL.enqueue(8);
		assertEquals("Enqueue from empty set:", "8", testDLL.toString());
		testDLL.enqueue(9);
		assertEquals("Enqueue from empty set:", "8,9", testDLL.toString());


	}
	@Test
	public void testDequeue() {
		DoublyLinkedList<Integer> testDLL = new DoublyLinkedList<Integer>();
		testDLL.dequeue();
		assertEquals("Dequeue from empty set:", null, testDLL.dequeue());
		testDLL.enqueue(8);
		assertEquals("Dequeue from 1 elem set:", "8", testDLL.dequeue().toString());
		testDLL.enqueue(9);
		testDLL.enqueue(10);
		testDLL.enqueue(11);
		testDLL.dequeue();
		assertEquals("Dequeue:", "10,11", testDLL.toString());


	}


}
