/**
 * This class creates a nonempty tree with a key value to find the tree, a value
 * value for the value of the tree, and a left and right reference to its child
 * trees.
 * @author Matt Harbour
 */
package names;

import java.util.List;

@SuppressWarnings("unchecked")
public class NonEmptyTree<K extends Comparable<K>, V> implements Tree<K, V> {

	//creates values described in class description
	private K key;
	private V value;
	private Tree<K, V> left, right;

	//constructor that initializes the key and value values to the parameter 
	//values and its left and right children to empty trees
	public NonEmptyTree(K keyIn, V valueIn) {
		key = keyIn;
		value = valueIn;
		left = right = EmptyTree.getInstance();
	}
	
	private NonEmptyTree(K keyIn, V valueIn, Tree<K, V> leftIn, 
			Tree<K, V> rightIn) {
		key = keyIn;
		value = valueIn;
		left = leftIn;
		right = rightIn;
	}
	//calls the helper method with an added tree parameter to keep track of
	//position
	public NonEmptyTree<K, V> add(K key, V value) {
		Tree<K, V> t = this;
		return add(t, key, value);
	}

	//if the parameter key is equal to the current key, it returns the 
	//unmodified tree, else if the parameter key is less than the current key,
	//recursively call the method on its left child, else recursively call the
	//method on its right child. Its creates a new nonempty tree with the
	//parameter key and value values once it reaches the appropriate spot at the
	//bottom of the tree.
	public NonEmptyTree<K, V> add(Tree<K, V> t, K key, V value) {
		if (key.compareTo(this.key) == 0) {
			return new NonEmptyTree<K, V>(key, value, left, right);
		} else if (key.compareTo(this.key) < 0) {
			left = left.add(left, key, value);
			return this;
		} else {
			right = right.add(right, key, value);
			return this;
		}
	}

	//calls the helper method with an added tree parameter to keep track of
	//position and an added int parameter giving it an initial size of 1
	public int size() {
		Tree<K, V> t = this;
		int size = 1;
		return size(t, size);
	}

	//searches first to the left then to the right of every tree and adds one to
	//the size for every nonempty tree found, returns the size
	public int size(Tree<K, V> t, int size) {
		size = left.size(left, size + 1);
		size = right.size(right, size + 1);
		return size;
	}

	//calls the helper method with an added tree parameter to keep track of
	//position
	public V lookup(K key) {
		Tree<K, V> t = this;
		return lookup(t, key);
	}

	//looks to see if the parameter key value is in the current tree. Based on
	//the parameter key value relative to the current key, the method searches 
	//down the tree. If the keys are equal, the method returns the value of the
	//current tree. If the method reaches the bottom of the tree, it returns
	//null.
	public V lookup(Tree<K, V> t, K key) {
		if (key.compareTo(this.key) == 0) {
			return value;
		} else if (key.compareTo(this.key) < 0) {
			return left.lookup(left, key);
		} else {
			return right.lookup(right, key);
		}
	}

	//calls the helper method with an added tree parameter to keep track of
	//position and a new copy in which to copy the values of the current tree
	//onto
	public Tree<K, V> copy() {
		Tree<K, V> copy = EmptyTree.getInstance(), t = this;
		return copy(t, copy);
	}

	//copies the current tree and its left and right children onto the copy tree
	//returns the copy tree when the copying is complete
	public Tree<K, V> copy(Tree<K, V> t, Tree<K, V> copy) {
		copy = copy.add(key, value);
		copy = left.copy(left, copy);
		copy = right.copy(right, copy);
		return copy;
	}

	//if the parameter key is not found in the current key, returns 0, else 
	//calls the helper method with an added tree parameter to keep track of
	//position and an added int parameter giving it an initial level of 1
	public int level(K key) {
		if (lookup(key) == null) {
			return 0;
		} else {
			int level = 1;
			Tree<K, V> t = this;
			return level(t, level, key);
		}
	}

	//searches down the tree for the parameter key and adds one to every
	//recursive call that is made. When the key is found, level is returned.
	public int level(Tree<K, V> t, int level, K key) {
		if (key.compareTo(this.key) == 0) {
			return level;
		} else if (key.compareTo(this.key) < 0) {
			return left.level(left, level + 1, key);
		} else {
			return right.level(right, level + 1, key);
		}
	}

	//calls the helper method with an added tree parameter to keep track of
	//position
	public K max() throws EmptyTreeException {
		Tree<K, V> t = this;
		return max(t, key);
	}

	//returns the key of the right-most nonempty tree, since the trees were
	//added in order
	public K max(Tree<K, V> t, K key) {
		return right.max(right, this.key);
	}

	//calls the helper method with an added tree parameter to keep track of
	//position
	public K min() throws EmptyTreeException {
		Tree<K, V> t = this;
		return min(t, key);
	}

	//returns the key of the left-most nonempty tree, since the trees were
	//added in order
	public K min(Tree<K, V> t, K key) {
		return left.min(left, this.key);
	}

	//if the parameter key value is found, replace the key value closest to the
	//current key and the value associated with that key (if the current tree is
	//a leaf, it is replace with an empty tree), else search the children for
	//the key based on how it compares to the current key. If the parameter key
	//is not found (reaches the bottom of the tree), the unmodified tree is
	//returned.
	public Tree<K, V> delete(K key) {
		if (key.compareTo(this.key) == 0) {
			try {
				this.key = left.max();
				this.value = left.lookup(left.max());
				left = left.delete(this.key); 
			} catch (EmptyTreeException e) {
				try {
					this.key = right.min();
					this.value = right.lookup(right.min());
					right = right.delete(this.key);
				} catch (EmptyTreeException f) {
					return EmptyTree.getInstance();
				}
			}
		} else if (key.compareTo(this.key) < 0) {
			left = left.delete(key);
		} else {
			right = right.delete(key);
		}
		return this;
	}

	//if the sizes the both trees are different, returns false, else calls the 
	//helper method with an added tree parameter to keep track of position and
	//a boolean parameter initialized to true
	public boolean haveSameKeys(Tree<K, V> otherTree) {
		if (this.size() != otherTree.size()) {
			return false;
		} else {
			Tree<K, V> t = this;
			boolean sameKey = true;
			return haveSameKeys(t, otherTree, sameKey);
		}
	}

	//compares the key values of every tree. If/once the two keys are not equal,
	//the boolean variable is set to false and remains false until it is
	//returned at the end of the method. If no unequal keys are found, the
	//method returns true.
	public boolean haveSameKeys(Tree<K, V> t, Tree<K, V> otherTree,
			boolean sameKey) {
		if (!sameKey) {
			return false;
		} else if (otherTree.lookup(key) == null) {
			sameKey = false;
		} else {
			sameKey = left.haveSameKeys(left, otherTree, sameKey);
			sameKey = right.haveSameKeys(right, otherTree, sameKey);
		}
		return sameKey;
	}
	//if the parameter key is found in the current tree, clears the parameter
	//list and calls the helper method with an added tree parameter to keep 
	//track of position
	public void pathFromRoot(K key, List<K> list) {
		if (lookup(key) != null) {
			list.clear();
			Tree<K, V> t = this;
			pathFromRoot(t, key, list);
		}
	}

	//adds every key from the root to where the parameter key is found in the
	//current tree to the parameter list. It searches the tree based on the
	//compared values of the parameter key to the current key.
	public void pathFromRoot(Tree<K, V> t, K key, List<K> list) {
		list.add(this.key);
		if (key.compareTo(this.key) != 0) {
			if (key.compareTo(this.key) < 0) {
				left.pathFromRoot(left, key, list);
			} else {
				right.pathFromRoot(left, key, list);
			}
		}
	}
	
	//uses the Unsorted class from proj2 to store the keys then calls the helper
	//method
	public UnsortedList<Integer> listOfKeys() {
		UnsortedList<Integer> list 
		= new UnsortedList<Integer>(new IntegerComparator());
		return listOfKeys(list);
	}
	
	//goes through every key in the tree an adds it to the UnsortedList. Since
	//this method was created specifically from proj5, the keys are always going
	//to be Integer, so each key in the Tree is casted as an Integer.
	public UnsortedList<Integer> listOfKeys(UnsortedList<Integer> list) {
		list.add((Integer) key);
		left.listOfKeys(list);
		right.listOfKeys(list);
		return list;
	}
	
	//calls the helper method with an added tree parameter to keep track of
	//position
	public String toString() {
		return toString(this);
	}

	//returns key/value pairs for the left child, current tree, and right tree,
	//in that order, adding a space between each
	public String toString(Tree<K, V> t) {
		String result = "", leftString, rightString;

		leftString = left.toString(left);
		rightString = right.toString(right);
		result = leftString + (leftString.equals("") ? "" : " ") + key + "/"
				+ value + (rightString.equals("") ? "" : " ") + right;

		return result;
	}
}
