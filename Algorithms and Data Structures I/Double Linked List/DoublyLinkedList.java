package Assign2;

import java.util.Iterator;
import java.util.ListIterator;
import java.util.NoSuchElementException;

// -------------------------------------------------------------------------
/**
 *  This class contains the methods of Doubly Linked List.
 *
 *  @author Bernadine Lao 
 *  @version 21/10/20 11:13:22
 */

/**
 * Class DoublyLinkedList: implements a *generic* Doubly Linked List.
 * 
 * @param <T> This is a type parameter. T is used as a class name in the
 *            definition of this class.
 *
 *            When creating a new DoublyLinkedList, T should be instantiated
 *            with an actual class name that extends the class Comparable. Such
 *            classes include String and Integer.
 *
 *            For example to create a new DoublyLinkedList class containing
 *            String data: DoublyLinkedList<String> myStringList = new
 *            DoublyLinkedList<String>();
 *
 *            The class offers a toString() method which returns a
 *            comma-separated sting of all elements in the data structure.
 * 
 *            This is a bare minimum class you would need to completely
 *            implement. You can add additional methods to support your code.
 *            Each method will need to be tested by your jUnit tests -- for
 *            simplicity in jUnit testing introduce only public methods.
 */
class DoublyLinkedList<T extends Comparable<T>> {

	/**
	 * private class DLLNode: implements a *generic* Doubly Linked List node.
	 */
	private class DLLNode {
		public final T data; // this field should never be updated. It gets its
								// value once from the constructor DLLNode.
		public DLLNode next;
		public DLLNode prev;

		/**
		 * Constructor
		 * 
		 * @param theData  : data of type T, to be stored in the node
		 * @param prevNode : the previous Node in the Doubly Linked List
		 * @param nextNode : the next Node in the Doubly Linked List
		 * @return DLLNode
		 */
		public DLLNode(T theData, DLLNode prevNode, DLLNode nextNode) {
			data = theData;
			prev = prevNode;
			next = nextNode;
		}
	}

	// Fields head and tail point to the first and last nodes of the list.
	private DLLNode head, tail;

	/**
	 * Constructor of an empty DLL
	 * 
	 * @return DoublyLinkedList
	 */

	public DoublyLinkedList() {
		head = null;
		tail = null;
	}

	// to get size
	public int dllSize() {
		int size = 0;

		DLLNode tempHead = head; // to not alter head while getting the size
		while (tempHead != null) {
			tempHead = tempHead.next;
			size++;
		}
		return size;
	}

	/**
	 * Tests if the doubly linked list is empty
	 * 
	 * @return true if list is empty, and false otherwise
	 *
	 *         Worst-case asymptotic running time cost: theta(1)
	 *
	 *         Justification: just checks if the list is empty or not. constant
	 *         runningtime
	 * 
	 * 
	 */
	public boolean isEmpty() {
		if (head == null) {
			return true;
		}
		return false;
	}

	/**
	 * Inserts an element in the doubly linked list
	 * 
	 * @param pos  : The integer location at which the new data should be inserted
	 *             in the list. We assume that the first position in the list is 0
	 *             (zero). If pos is less than 0 then add to the head of the list.
	 *             If pos is greater or equal to the size of the list then add the
	 *             element at the end of the list.
	 * @param data : The new data of class T that needs to be added to the list
	 * @return none
	 *
	 *         Worst-case asymptotic running time cost: theta(N)
	 *
	 *         Justification: for-loops take theta(N) runningtime as the loop will
	 *         continue till it passed n elements, the rest is constant as they are
	 *         basic operations. theta(1) keep the highest order term therefore
	 *         1*theta(N) = theta(N)
	 */
	public void insertBefore(int pos, T data) {
		if (isEmpty()) { // no items before
			DLLNode node = new DLLNode(data, null, null);
			head = node;
			tail = node;
		} else if (pos <= 0) { // theres one item before
			DLLNode node = new DLLNode(data, null, head);
			head.prev = node; // connection between the nodes
			head = node; // change header position
		} else if (pos >= dllSize()) {
			DLLNode node = new DLLNode(data, tail, null);
			tail.next = node;
			tail = tail.next;
		} else {
			DLLNode navigatingNode = head; // reference to find the position
			if (pos == 1 && dllSize() == 2) {
				navigatingNode = head.next;
				DLLNode node = new DLLNode(data, head, head.next);
				head.next.prev = node;
				head.next = node;

			} else {
				for (int i = 0; i < pos; i++) {
					navigatingNode = navigatingNode.next;
				}
				DLLNode node = new DLLNode(data, navigatingNode.prev, navigatingNode); // actual node with data

				navigatingNode.prev.next = node;
				node.prev = navigatingNode.prev;/////
				navigatingNode.prev = node;
				node.next = navigatingNode;//////////////
//change			navigatingNode.next = node;
			}

		}
	}

	/**
	 * Returns the data stored at a particular position
	 * 
	 * @param pos : the position
	 * @return the data at pos, if pos is within the bounds of the list, and null
	 *         otherwise.
	 *
	 *         Worst-case asymptotic running time cost: theta(N)
	 *
	 *         Justification: for-loops take theta(N) runningtime as the loop will
	 *         continue till it passed n elements, the rest is constant as they are
	 *         basic operations. theta(1) keep the highest order term therefore
	 *         1*theta(N) = theta(N)
	 */
	public T get(int pos) {

		if (isEmpty()) {

		} else if (pos >= dllSize()) {

		} else if (pos < 0) {

		} else {
			DLLNode node = head;
			for (int i = 0; i < pos; i++) {
				node = node.next;
			}
			return node.data;
		}

		return null;
	}

	/**
	 * Deletes the element of the list at position pos. First element in the list
	 * has position 0. If pos points outside the elements of the list then no
	 * modification happens to the list.
	 * 
	 * @param pos : the position to delete in the list.
	 * @return true : on successful deletion, false : list has not been modified.
	 *
	 *         Worst-case asymptotic running time cost: theta(N)
	 *
	 *         Justification: for-loops take theta(N) runningtime as the loop will
	 *         continue till it passed n elements, the if statements take theta(1)
	 *         running time. keep the highest order term therefore 1*theta(N) =
	 *         theta(N)
	 */
	public boolean deleteAt(int pos) {
		DLLNode node;
		if (isEmpty()) {
			return false;
		} else if (pos >= dllSize()) {
			return false;
		} else if (pos < 0) {
			return false;
		}

		// if first and size 1
		else if (pos == 0 && dllSize() == 1) {
			head = null;
			tail = null;
		}
		// if first
		else if (pos == 0) {
			node = head.next;
			head.next = null;
			head = node;
			node.prev = null;
		}

		// if in the middle
		else {
			node = head; // navigating Node
			for (int i = 0; i < pos; i++) {
				
				node = node.next;
			}

			if (node.next != null && node.prev != null) {
				node.prev.next = node.next;
				node.next.prev = node.prev;
			} else if (node.next == null) {		//
				tail = node.prev;
				tail.next = null;
			}
			node = null;

		}
		return true;
	}

	/**
	 * Reverses the list. If the list contains "A", "B", "C", "D" before the method
	 * is called Then it should contain "D", "C", "B", "A" after it returns.
	 *
	 * Worst-case asymptotic running time cost: theta(N)
	 *
	 * Justification: while loops take N runningtime as the loop will continue till
	 * it passed n elements ie. till the pointer points to null in this case, the
	 * rest is constant. keep the highest order term therefore theta(N).
	 */
	public void reverse() {

		DLLNode current = head;
		DLLNode temp = null;

		while (current != null) {
			temp = current.prev;
			current.prev = current.next; // turn around the prev pointer to face right
			current.next = temp; // turn around the next pointer to face left
			current = current.prev; // since the swap, the'next' node is the prev now
		}
		if (temp != null) {
			head = temp.prev;
		}
	}

	/**
	 * Removes all duplicate elements from the list. The method should remove the
	 * _least_number_ of elements to make all elements uniqueue. If the list
	 * contains "A", "B", "C", "B", "D", "A" before the method is called Then it
	 * should contain "A", "B", "C", "D" after it returns. The relative order of
	 * elements in the resulting list should be the same as the starting list.
	 *
	 * Worst-case asymptotic running time cost: theta(N^2)
	 *
	 * Justification: for-loops take theta(N) runningtime as the loop will continue
	 * till it passed n elements, within that is another for-loop which is another
	 * theta(N) therefore its theta(N*N);
	 */
	public void makeUnique() {

		DLLNode temp = null;
		DLLNode node = head;
		DLLNode sameNode = null;

		while (node != null && node.next != null) {
			temp = node; // compare to other nodes
			while (temp.next != null) {
				if (node.data == temp.next.data) {
					sameNode = temp.next;
					temp.next = temp.next.next; // skips the similar node

				} else {
					temp = temp.next;// move to the next node if not similar
				}
			}
			node = node.next; // move to next node if finish going through the inner while loop
		}

	}

	/*----------------------- STACK API 
	 * If only the push and pop methods are called the data structure should behave like a stack.
	 */

	/**
	 * This method adds an element to the data structure. How exactly this will be
	 * represented in the Doubly Linked List is up to the programmer.
	 * 
	 * @param item : the item to push on the stack
	 *
	 *             Worst-case asymptotic running time cost: theta(N)
	 *
	 *             Justification: insertBefore method uses theta(N) asymptitic
	 *             runningtime. it is used to implement push, hence, runningtime for
	 *             push() is theta(n) as well
	 */
	public void push(T item) {
		insertBefore(0, item); // pushing from the 'front'
	}

	/**
	 * This method returns and removes the element that was most recently added by
	 * the push method.
	 * 
	 * @return the last item inserted with a push; or null when the list is empty.
	 *
	 *         Worst-case asymptotic running time cost: theta(1)
	 *
	 *         Justification:Popping an item from the top of stack is constant
	 *         theta(1)*1* 1* 1 =theta(1)
	 */
	public T pop() {
		if (isEmpty()) {

		}

		else {

			DLLNode node = head;
			T data = node.data;
			head = node.next;

			return data;
		}
		return null;
	}

	/*----------------------- QUEUE API
	 * If only the enqueue and dequeue methods are called the data structure should behave like a FIFO queue.
	 */

	/**
	 * This method adds an element to the data structure. How exactly this will be
	 * represented in the Doubly Linked List is up to the programmer.
	 * 
	 * @param item : the item to be enqueued to the stack
	 *
	 *             Worst-case asymptotic running time cost: theta(1);
	 *
	 *             Justification: there are no loops therefore it is assumed to be
	 *             running in constant time. theta(1)*1 =theta(1)
	 */
	public void enqueue(T item) {
		if (isEmpty()) {
			insertBefore(0, item);
		} else {
			insertBefore(dllSize(), item);
		}
	}

	/**
	 * This method returns and removes the element that was least recently added by
	 * the enqueue method.
	 * 
	 * @return the earliest item inserted with an enqueue; or null when the list is
	 *         empty.
	 *
	 *         Worst-case asymptotic running time cost: theta(1)
	 *
	 *         Justification: there are no loops in the method therefore the
	 *         runningtime is constant. The method pop() runs in theta(1) asymptotic
	 *         runningtime theta(1)*1 =theta(1),
	 */
	public T dequeue() {

		if (isEmpty()) {
			return null;
		} else {
			return pop();
		}

	}

	/**
	 * @return a string with the elements of the list as a comma-separated list,
	 *         from beginning to end
	 *
	 *         Worst-case asymptotic running time cost: Theta(n)
	 *
	 *         Justification: We know from the Java documentation that
	 *         StringBuilder's append() method runs in Theta(1) asymptotic time. We
	 *         assume all other method calls here (e.g., the iterator methods above,
	 *         and the toString method) will execute in Theta(1) time. Thus, every
	 *         one iteration of the for-loop will have cost Theta(1). Suppose the
	 *         doubly-linked list has 'n' elements. The for-loop will always iterate
	 *         over all n elements of the list, and therefore the total cost of this
	 *         method will be n*Theta(1) = Theta(n).
	 */
	public String toString() {
		StringBuilder s = new StringBuilder();
		boolean isFirst = true;

		// iterate over the list, starting from the head
		for (DLLNode iter = head; iter != null; iter = iter.next) {
			if (!isFirst) {
				s.append(",");
			} else {
				isFirst = false;
			}
			s.append(iter.data.toString());
		}

		return s.toString();
	}

}
