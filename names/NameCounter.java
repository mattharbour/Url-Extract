/**
 * The purpose of this class is the start the appropriate amount of threads (the
 * range from startYear to endYear) in order to call the getNames method in the
 * Names class and store the data in its Tree names field.
 * @author Matt Harbour
 */
package names;

public class NameCounter implements Runnable {

	private int year, numNames;
	private Names names; 
	
	public NameCounter(Names namesIn, int yearIn, int numNamesIn) {
		names = namesIn;
		year = yearIn;
		numNames = numNamesIn;
	}
	
	//creates the appropriate amount of threads (the range from startYear to 
	//endYear) and store each thread as a value in a Tree, using the year that
	//was used to create the thread as the key. Then it goes through the Tree
	//and calls start on each thread and once again to call join.
	static void threadStarter(Names namesIn, int startYear, int endYear, 
			int numNames) throws InterruptedException {
		Tree<Integer,Thread> threads = EmptyTree.getInstance();
		for (int i = startYear; i <= endYear; i++) {
			threads 
			= threads.add(i, new Thread(new NameCounter(namesIn, i, numNames)));
		}
		for (int i = startYear; i <= endYear; i++) {
			threads.lookup(i).start();
		}
		for (int i = startYear; i <= endYear; i++) {
			threads.lookup(i).join();
		}
	}
	
	//when this is called by each thread's start method, it calls the getNames
	//helper method in the Names class using the year of the thread and numNames
	//as parameters.
	@Override
	public void run() {
		names.getNames(year, numNames);
	}

}
