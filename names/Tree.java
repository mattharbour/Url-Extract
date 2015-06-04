package names;

import java.util.List;

public interface Tree<K extends Comparable<K>, V> {
	public NonEmptyTree<K, V> add(K key, V value);
	//added helper method
	public NonEmptyTree<K, V> add(Tree<K, V> t, K key, V value);
	public int size();
	//added helper method
	public int size(Tree<K, V> t, int size);
	public V lookup(K key);
	//added helper method
	public V lookup(Tree<K, V> t, K key);
	public Tree<K, V> copy();
	//added helper method
	public Tree<K, V> copy(Tree<K, V> t, Tree<K, V> copy);
	public int level(K key);
	//added helper method
	public int level(Tree<K, V> t, int level, K key);
	public K max() throws EmptyTreeException;
	//added helper method
	public K max(Tree<K, V> t, K key);
	public K min() throws EmptyTreeException;
	//added helper method
	public K min(Tree<K, V> t, K key);
	public Tree<K, V> delete(K key);
	public boolean haveSameKeys(Tree<K, V> otherTree);
	//added helper method
	public boolean haveSameKeys(Tree<K, V> t, Tree<K, V> otherTree, boolean sameKey);
	public void pathFromRoot(K key, List<K> list);
	//added helper method
	public void pathFromRoot(Tree<K, V> t, K key, List<K> list);
	//added helper method
	public String toString(Tree<K, V> t);
	//created for proj5
	public UnsortedList<Integer> listOfKeys();
	//created for proj5
	public UnsortedList<Integer> listOfKeys(UnsortedList<Integer> list);
}
