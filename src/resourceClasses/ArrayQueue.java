package resourceClasses;

public class ArrayQueue<E> implements Queue<E> {
	private final static int INITCAP = 4; 
	private E[] elements; 
	private int first, size; 
	public ArrayQueue() { 
		elements = (E[]) new Object[INITCAP]; 
		first = 0; 
		size = 0; 
	}
	public int size() {
		return size;
	}

	public boolean isEmpty() {
		return size == 0;
	}

	public E first() {
		if (isEmpty()) 
			return null; 
		return elements[first]; 
	}

	public E dequeue() {
		if (isEmpty()) 
			return null;
		E etr = elements[first]; 
		
		int carry = 1;
		while (carry < size) {
			elements[carry-1]=elements[carry];
			carry++;
		}
		elements[carry-1]=null;
		size--;
		// Check if number of available positions in the array exceed 3/4
		// of its total length. If so, and if the current capacity is not
		// less than 2*INITCAP, shrink the internal array to 1/2 of its
		// current length (the capacity of the queue). 
		if (elements.length >= 2*INITCAP && size < elements.length/4)
			changeCapacity(elements.length/2); 
		return etr; 
	}

	public void enqueue(E e) { //
		if (size == elements.length)   // check capacity, double it if needed
			changeCapacity(2*size); 
		elements[size]=e;
		size++;
	}

	private void changeCapacity(int newCapacity) { 
		// PRE: newCapacity >= size
		int carry = 0;
		E[] temp = (E[]) new Object[newCapacity];
		while (carry<size) {
			temp[carry]=elements[carry];
			carry++;
		}
		elements = temp;
	}
}