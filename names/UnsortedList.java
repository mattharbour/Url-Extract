package names;
/**
 * This class uses the user's comparator to order the linked list by using node.
 * It contains methods that return and manipulate the nodes in the list and the
 * data that is stored in those nodes.
 * 
 * @author Matt Harbour
 */

import java.util.Comparator;
import java.lang.Iterable;
import java.util.Iterator;
import java.lang.IndexOutOfBoundsException;
import java.util.NoSuchElementException;

public class UnsortedList<T> implements Iterable<T>, Comparable<UnsortedList<T>> {

	/*
	 * this was added in order for constructor to be assigned something within
	 * the class
	 */
	protected Comparator<T> comparator;

	// you may ADD TO this inner class, but not CHANGE what's already here!
	protected class Node<D> {
		D data;
		Node<D> next;

		protected Node(D data) {
			this.data = data;
			next = null;
		}
	}

	//indicates the beginning of the list
	protected UnsortedList<T>.Node<T> head = null;

	//assigns the parameter comparator to the class comparator
	public UnsortedList(Comparator<T> comparator) {
		this.comparator = comparator;
	}

	//copies all the data from the parameter list to the current list using a
	//shallow copy
	public UnsortedList(UnsortedList<T> otherUnsortedList) {
		for (T item: otherUnsortedList) {
			this.add(item);
		}
		comparator = otherUnsortedList.comparator;
	}

	//adds the data to the end list of the list, if the list has more than one
	//element
	public UnsortedList<T> add(T newElt) {
		UnsortedList<T>.Node<T> n= new UnsortedList.Node(newElt);
		UnsortedList<T>.Node<T> current = head, prev = null;

		if (size() == 0) {
			head = n;
		} else {
			while(current != null) {
				prev = current;
				current = current.next;
			}
			if (prev == null) {
				head.next = n;
			} else {
				prev.next = n;
			}
		}
		return this;
	}

	//goes to the specified index and returns the element in that index, throws
	//an exception if it is an invalid index
	public T get(int index) throws IndexOutOfBoundsException {
		UnsortedList<T>.Node<T> current = head;
		
		if (index < 0 || index >= size()) {
			throw new IndexOutOfBoundsException("Invalid index.");
		} else {
			current = head;
			int pos = 0;
			while (pos < size()) {
				if (pos == index) {
					return current.data;
				} else {
					current = current.next;
					pos++;
				}
			}
		}
		throw new AssertionError("Shouldn't get to this point.");
	}

	//searches for a specific element in the list and returns it if its found, 
	//otherwise it returns null
	public T lookup(T element) {
		UnsortedList<T>.Node<T> current = head;
		
		while (current != null 
				&& comparator.compare(current.data, element) != 0) {
			current= current.next;
		}
		if (current != null) {
			return current.data;
		} else {
			return null;
		}
	}

	//counts the number if nodes in the list and returns that number as an int
	public int size() {
		UnsortedList<T>.Node<T> current = head;
		
		int size = 0;
		while (current != null) {
			current = current.next;
			size++;
		}
		return size;
	}

	//returns true if there is no elements (if there is no first element),
	//otherwise it returns false
	public boolean isEmpty() {
		if (head == null) {
			return true;
		} else {
			return false;
		}
	}

	//searches the list for a specific element, if found, it is deleted (all
	//references to it are removed), otherwise it throws an exception
	public UnsortedList<T> delete(T element) throws NoSuchElementException {
		UnsortedList<T>.Node<T> current = head, prev = null;
		
		while (current != null 
				&& comparator.compare(current.data, element) != 0) {
			prev = current;
			current = current.next;
		}
		if (current != null) {
			if (current == head) {
				head= head.next;
			} else  {
				prev.next= current.next;
			}
			return this;
		} else {
			throw new NoSuchElementException("Element not found.");
		}
	}

	//searches the list for a specific element, if found, the old element in
	//that node is replaced by the new element, otherwise it throws an exception
	public void replace(T oldElt, T newElt) throws NoSuchElementException {
		UnsortedList<T>.Node<T> current = head, prev = null;
		
		if (lookup(oldElt) == null) {
			throw new NoSuchElementException("Element not found.");
		} else {
			UnsortedList<T>.Node<T> n = new UnsortedList.Node(newElt);
			while (current != null 
					&& comparator.compare(current.data, oldElt) != 0) {
				prev = current;
				current = current.next;
			}
			prev.next = n;
			n.next = current.next;
		}
	}

	//uses the comparator to search the list for the largest element and returns
	//that element, if the list is empty, it throws an exception
	public T getLargest() throws NoSuchElementException {
		UnsortedList<T>.Node<T> current = head, prev = null;
		T largest = null;
				
		if (size() == 0) {
			throw new NoSuchElementException("Empty list.");
		} else {
			while (current != null) {
				if (prev == null) {
					largest = current.data;
				} else {
					if (comparator.compare(current.data, largest) > 0) {
						largest = current.data;
					}
				}
				prev = current;
				current = current.next;
			}
			return largest;
		}
	}

	//uses the comparator to search the list for the smallest element and 
	//returns that element, if the list is empty, it throws an exception
	public T getSmallest() throws NoSuchElementException {
		UnsortedList<T>.Node<T> current = head, prev = null;
		T smallest = null;
				
		if (size() == 0) {
			throw new NoSuchElementException("Empty list.");
		} else {
			while (current != null) {
				if (prev == null) {
					smallest = current.data;
				} else {
					if (comparator.compare(current.data, smallest) < 0) {
						smallest = current.data;
					}
				}
				prev = current;
				current = current.next;
			}
			return smallest;
		}
	}

	//returns the elements of the list in the order that they were added and
	//adds a space between each element
	public String toString() {
		UnsortedList<T>.Node<T> current = head, prev = null;
		String result = "";
		
		while (current != null) {
			if (prev != null) {
				result += " ";
			}
			result += current.data.toString();
			prev = current;
			current = current.next;
		}
		return result;
	}

	//deletes all the elements of the list, changing the current list to an
	//empty list
	public void clear() {
		UnsortedList<T>.Node<T> current = head;
		
		while(current != null) {
			current = current.next;
		}
		head.next = current;
		head = null;
	}

	//Compares a different unordered list to the current unordered list. If the
	//lists matches both in ordering of elements and in size, it returns 0. If
	//the elements don't compare identically and the first nonmatching element
	//of the current list is less than that of the parameter list, it returns
	//-1. If the elements don't compare identically and the first nonmatching
	//element of the current list is greater than that of the parameter list, it 
	//returns 1. If the elements of one list compare identically to the items of
	//the other list, but the size of the parameter list is greater than the
	//size of the current list, it returns -1, it returns 1 if less.
	public int compareTo(UnsortedList<T> otherUnsortedList) {
		UnsortedList<T>.Node<T> currentElt = head, 
				otherElt = otherUnsortedList.head;
		int commonElts = 0;
		boolean lessNonmatchElt = false;
		boolean greaterNonmatchElt = false;
		while (currentElt != null && otherElt != null) {
			if (comparator.compare(currentElt.data, otherElt.data) == 0) {
				commonElts++;
			} else if (comparator.compare(currentElt.data, otherElt.data) < 0 
					&& !lessNonmatchElt && !greaterNonmatchElt) {
				lessNonmatchElt = true;
			} else if (comparator.compare(currentElt.data, otherElt.data) > 0 
					&& !greaterNonmatchElt && !lessNonmatchElt) {
				greaterNonmatchElt = true;
			}
			currentElt = currentElt.next;
			otherElt = otherElt.next;
		}
		if (commonElts == size() && commonElts == otherUnsortedList.size()) {
			return 0;
		} else if (lessNonmatchElt) {
			return -1;
		} else if (greaterNonmatchElt) {
			return 1;
		} else if ((commonElts == size() 
				|| commonElts == otherUnsortedList.size()) 
				&& size() < otherUnsortedList.size()) {
			return -1;
		} else if ((commonElts == size() 
				|| commonElts == otherUnsortedList.size()) 
				&& size() > otherUnsortedList.size()) {
			return 1;
		} else {
			throw new AssertionError("Shouldn't get to this point.");
		}
	}

	//overrides the methods in the iterator class in order to specifically
	//iterate through unordered lists
	public Iterator<T> iterator() {
		return new Iterator<T>() {
			private UnsortedList<T>.Node<T> current = head;

			@Override
			public boolean hasNext() {
				return current != null;
			}

			@Override
			public T next() throws NoSuchElementException{
				T next = null;
				if (current == null) {
					throw new NoSuchElementException("No more elements.");
				} else {
					next = current.data;
					current = current.next;
				}
				return next;
			}

			public void remove() {

			}
		};
	}
}


