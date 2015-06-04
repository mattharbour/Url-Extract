/**
 * This singleton class creates an empty tree with no data for all nonempty
 * tree leaves to reference to.
 * 
 * @author Matt Harbour
 */
package names;

import java.util.List;

@SuppressWarnings({ "unchecked", "rawtypes" })
public final class EmptyTree<K extends Comparable<K>, V> implements Tree<K, V> {

	// creates the one unique empty tree
	private static EmptyTree uniq = new EmptyTree();

	// blank private constructor
	private EmptyTree() {

	}

	// return a reference to the one unique empty tree
	public static EmptyTree getInstance() {
		return uniq;
	}

	// adds a nonempty tree to an empty tree by changing the current empty tree
	// into a nonempty tree with the parameter values
	public NonEmptyTree<K, V> add(K key, V value) {
		return new NonEmptyTree(key, value);
	}

	// helper method for the add method in the NonEmptyTree class
	public NonEmptyTree<K, V> add(Tree<K, V> t, K key, V value) {
		return add(key, value);
	}

	// returns size 0 for empty trees
	public int size() {
		return 0;
	}

	// helper method for the size method in the NonEmptyTree class
	public int size(Tree<K, V> t, int size) {
		return size - 1;
	}

	// returns null since empty trees have no key values
	public V lookup(K key) {
		return null;
	}

	// helper method for the lookup method in the NonEmptyTree class
	public V lookup(Tree<K, V> t, K key) {
		return null;
	}

	// returns an empty tree
	public Tree<K, V> copy() {
		Tree<K, V> copy = EmptyTree.getInstance();
		return copy;
	}

	// helper method for the copy method in the NonEmptyTree class
	public Tree<K, V> copy(Tree<K, V> t, Tree<K, V> copy) {
		return copy;
	}

	// returns 0 since empty trees have no key values
	public int level(K key) {
		return 0;
	}

	// helper method for the level method in the NonEmptyTree class
	public int level(Tree<K, V> t, int level, K key) {
		return level - 1;
	}

	// throws an EmptyTreeException since empty trees have no key values
	public K max() throws EmptyTreeException {
		throw new EmptyTreeException();
	}

	// helper method for the max method in the NonEmptyTree class
	public K max(Tree<K, V> t, K key) {
		return key;
	}

	// throws an EmptyTreeException since empty trees have no key values
	public K min() throws EmptyTreeException {
		throw new EmptyTreeException();
	}

	// helper method for the min method in the NonEmptyTree class
	public K min(Tree<K, V> t, K key) {
		return key;
	}

	// returns the unmodified current tree since empty trees have no key values
	public Tree<K, V> delete(K key) {
		return this;
	}

	// if the trees are equal, return true since they both must be empty trees,
	// else return false
	public boolean haveSameKeys(Tree<K, V> otherTree) {
		if (this == otherTree) {
			return true;
		} else {
			return false;
		}
	}

	// helper method for the haveSameKeys method in the NonEmptyTree class
	public boolean haveSameKeys(Tree<K, V> t, Tree<K, V> otherTree,
			boolean sameKey) {
		return sameKey;
	}

	//does nothing since empty trees have no key values
	public void pathFromRoot(K key, List<K> list) {

	}

	//helper method for the pathFromRoot method in the NonEmptyTree class
	public void pathFromRoot(Tree<K, V> t, K key, List<K> list) {

	}

	//created for proj5
	public UnsortedList<Integer> listOfKeys() {
		return null;
	}

	//created for proj5
	public UnsortedList<Integer> listOfKeys(UnsortedList<Integer> list) {
		return list;
	}

	//returns an empty string for empty trees
	public String toString() {
		return "";
	}

	//helper method for the toString method in the NonEmptyTree class
	public String toString(Tree<K, V> t) {
		return "";
	}

}
