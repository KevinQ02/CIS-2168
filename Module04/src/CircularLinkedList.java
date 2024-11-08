import java.util.Iterator;

public class CircularLinkedList<E> implements Iterable<E> {

	// Your variables
	Node<E> head;
	Node<E> tail;
	int size;  // BE SURE TO KEEP TRACK OF THE SIZE

	// implement this constructor
	//Done so don't work on it
	public CircularLinkedList() {
	}


	// I highly recommend using this helper method
	// Return Node<E> found at the specified index
	// be sure to handle out of bounds cases *
	private Node<E> getNode(int index ) {
		if(index < 0 || index >= size) {
			throw new IndexOutOfBoundsException();
		}
		Node<E> current = head;
		for(int i = 0; i < index; i++) {
			current = current.next;
		}
		return current;
	}


	// attach a node to the end of the list
	public boolean add(E item) {
		this.add(size,item);
		return true;
	}
	// Cases to handle
	// out of bounds
	// adding to empty list
	// adding to front
	// adding to "end"
	// adding anywhere else
	// REMEMBER TO INCREMENT THE SIZE
	public void add(int index, E item){
		if (index < 0 || index > size) {
			throw new IndexOutOfBoundsException();
		}
		Node<E> newNode = new Node<>(item);
		if (size == 0) {
			head = newNode;
			tail = newNode;
			tail.next = head;
		} else if (index == 0) {
			newNode.next = head;
			head = newNode;
			tail.next = head;
		} else if (index == size) {
			tail.next = newNode;
			tail = newNode;
			tail.next = head;
		} else {
			Node<E> prev =  getNode(index - 1);
			newNode.next  = prev.next;
			prev.next = newNode;
		}
		size++;
	}

	// remove must handle the following cases
	// out of bounds
	// removing the only thing in the list
	// removing the first thing in the list (need to adjust the last thing in the list to point to the beginning)
	// removing the last thing
	// removing any other node
	// REMEMBER TO DECREMENT THE SIZE
	public E remove(int index) {
		if (index < 0 || index >= size) {
			throw new IndexOutOfBoundsException();
		}
		Node<E> target;
		if (size == 1) {
			target = head;
			head = null;
			tail = null;
		} else if (index == 0) {
			target = head;
			head = head.next;
			tail.next = head;
		} else {
			Node<E> prev = getNode(index - 1);
			target = prev.next;
			prev.next = target.next;
			if (index == size - 1) {
				tail = prev;
				tail.next = head;
			}
		}
		size--;
		return target.item;	}

	// Turns your list into a string
	// Useful for debugging
	// Just for testing, don't change
	public String toString(){
		Node<E> current =  head;
		StringBuilder result = new StringBuilder();
		if(size == 0){
			return "";
		}
		if(size == 1) {
			return head.item.toString();

		}
		else{
			do{
				result.append(current.item);
				result.append(" ==> ");
				current = current.next;
			} while(current != head);
		}
		return result.toString();
	}


	public Iterator<E> iterator() {
		return new ListIterator<E>();
	}

	// provided code for different assignment
	// you should not have to change this
	// change at your own risk!
	// this class is not static because it needs the class it's inside of to survive!
	private class ListIterator<E> implements Iterator<E>{

		Node<E> nextItem;
		Node<E> prev;
		int index;

		@SuppressWarnings("unchecked")
		//Creates a new iterator that starts at the head of the list
		public ListIterator(){
			nextItem = (Node<E>) head;
			index = 0;
		}

		// returns true if there is a next node
		// this is always should return true if the list has something in it
		public boolean hasNext() {
			// TODO Auto-generated method stub
			return size != 0;
		}

		// advances the iterator to the next item
		// handles wrapping around back to the head automatically for you
		public E next() {
			// TODO Auto-generated method stub
			prev =  nextItem;
			nextItem = nextItem.next;
			index =  (index + 1) % size;
			return prev.item;

		}

		// removed the last node was visted by the .next() call
		// for example if we had just created a iterator
		// the following calls would remove the item at index 1 (the second person in the ring)
		// next() next() remove()
		public void remove() {
			int target;
			if(nextItem == head) {
				target = size - 1;
			} else{
				target = index - 1;
				index--;
			}
			CircularLinkedList.this.remove(target); //calls the above class
		}

	}

	// It's easiest if you keep it a singly linked list
	// SO DON'T CHANGE IT UNLESS YOU WANT TO MAKE IT HARDER
	private static class Node<E>{
		E item;
		Node<E> next;

		public Node(E item) {
			this.item = item;
		}

	}
	// Main will be used for testing examples

	public static void main(String[] args){
		simulateJosephusProblem(5, 2); // Simulate the Josephus problem for n=5, k=2
		System.out.println();
		simulateJosephusProblem(13, 2); // Simulate the Josephus problem for n=13, k=2
	}
	private static void simulateJosephusProblem(int n, int k) {
		CircularLinkedList<Integer> list = new CircularLinkedList<>();
		for (int i = 1; i <= n; i++) {
			list.add(i);
		}

		System.out.println("Initial circle: " + list);

		int index = 0;
		while (list.size > 1) {
			index = (index + k - 1) % list.size;
			list.remove(index);
			System.out.println("After removing: " + list);
		}
		System.out.println("Last remaining: " + list);
	}
}
