/**
 * This class sends requests to http://www.ssa.gov/cgi-bin/popularnames.cgi in
 * order to give a specified top number of boys and girls names for a specified
 * range of years. Also returns specific ranks of boy and girl names for a
 * specified year and specific boy and girl names for a specified year and rank.
 * @author Matt Harbour
 */
package names;

import java.net.*;
import java.io.*;
import java.util.*;

public class Names {

	//uses the Tree class from proj3 to store the info returned by the website.
	//It uses the year as the key, and a tree of the data from that year as the
	//value. In the value tree, it uses the rank as the key and the boy/girl
	//name pair (NamePair type) as the value.
	private Tree<Integer, Tree<Integer, NamePair>> names 
	= EmptyTree.getInstance();

	//This method (the method that would be called by a user) calls the
	//threadStarter method in the NameCounter class using the current Names
	//object, the start and end yead, and numNames as parameters.
	public void getNames(int startYear, int endYear, int numNames) {
		try {
			NameCounter.threadStarter(this, startYear, endYear, numNames);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	//using synchronization, each thread will receive the HTML of the webpage
	//specific to the thread's year. Then it will extract the data from the HTML
	//(the boy and girl name for each rank up to numNames) and add that data to
	//the names Tree.
	void getNames(int year, int numNames) {

		//uses the private names Tree as a lock
		synchronized(names) {
			//used to store the data from the thread's year
			Tree<Integer, NamePair> currentYear = EmptyTree.getInstance();
			URL url = null;
			URLConnection site = null;
			BufferedReader reader = null;
			PrintWriter writer = null;
			String HTML = null;

			try {
				url = new URL("http://www.ssa.gov/cgi-bin/popularnames.cgi");
			} catch (MalformedURLException e) {
				e.printStackTrace();
			}

			try {
				site = url.openConnection();
				site.setDoOutput(true);
				writer = new PrintWriter(
						new OutputStreamWriter(site.getOutputStream()));
				writer.print("year=" + year + "&top=" + numNames);
				writer.flush();
				reader = new BufferedReader(
						new InputStreamReader(site.getInputStream()));

			} catch (IOException e) {
				e.printStackTrace();
			}

			//condenses the HTML of the webpage to the size that's in the
			//project description
			try {
				String readLine;
				boolean start = false, end = false;
				while ((readLine = reader.readLine()) != null) {
					if (readLine.contains("Popularity in " + year) && !start 
							&& !end) {
						HTML = readLine + "\n";
						start = true;
					} else if (readLine.contains("Note:") && start && !end) {
						end = true;
					} else if (start && !end) {
						HTML += readLine + "\n";
					}
				}
				reader.close();
			} catch (IOException e) {
				e.printStackTrace();
			}

			//feeds the HTML into a scanner in order to read the HTML line by
			//line
			Scanner HTMLScan = new Scanner(HTML);
			String HTMLLine;
			while (HTMLScan.hasNextLine()) {
				HTMLLine = HTMLScan.nextLine();
				boolean boyAdded = false;
				int rank = -1;
				String boy = null, girl = null;
				//checks to see if the line contains "<td>" (lines that contain
				//"<td>" are the lines that contain the necessary data
				if (HTMLLine.contains("<td>")) {
					for (int i = 0; i < HTMLLine.length(); i ++) {
						if (i + 1 < HTMLLine.length() 
								&& HTMLLine.charAt(i) == '>') {
							//attempts to parse the String as an integer, if it
							//is successful, then it must be the rank
							try {
								rank = Integer.parseInt(HTMLLine
										.substring(i + 1, 
												HTMLLine.indexOf('<', i)));
								currentYear = currentYear.add(rank, 
										new NamePair(null, null));
							} catch (NumberFormatException e) {
								String name = 
										HTMLLine.substring(i + 1, 
												HTMLLine.indexOf('<', i));
								//if next char isn't a space and the boy name
								//hasn't been added yet, the following subString
								//must be the boy name, else it the girl name
								if (HTMLLine.charAt(i + 1) != ' ' 
										&& !boyAdded) {
									boy = name;
									currentYear = currentYear.add(rank, 
											new NamePair(boy, null));
									boyAdded = true;
								} else {
									girl = name;
									currentYear 
									= currentYear.add(rank, 
											new NamePair(boy, girl));
								}
							}
						}
					}
				}
			}
			HTMLScan.close();
			//adds the extract data into the names Tree by using the thread's
			//year as the key and the currentYear data Tree as the value
			names = names.add(year, currentYear);
			//the purpose of this statement is so the user can visually see the
			//data as it gets added to the names Tree.
			System.out.println(names.toString());
		}
	}

	//returns the girl name that was the parameter rank in the parameter year by
	//looking up the girl name in the specified rank in the specified year by 
	//using the lookup method of the Tree interface and the getBoy method in the 
	//NamePair class
	public String getGirlName(int year, int rank) {
		if(names.lookup(year) == null 
				|| names.lookup(year).lookup(rank) == null) {
			return null;
		} else {
			return names.lookup(year).lookup(rank).getGirl();
		}
	}

	//returns the boy name that was the parameter rank in the parameter year by
	//looking up the boy name in the specified rank in the specified year by 
	//using the lookup method of the Tree interface and the getBoy method in the 
	//NamePair class
	public String getBoyName(int year, int rank) {
		if(names.lookup(year) == null 
				|| names.lookup(year).lookup(rank) == null) {
			return null;
		} else {
			return names.lookup(year).lookup(rank).getBoy();
		}
	}

	//returns the rank of the parameter girl name in the parameter year by
	//iterating through the ranks of the specified year using the listOfKeys
	//method of the Tree interface. It looks up the girl name by using the 
	//lookup method of the Tree interface and the getGirl method in the NamePair 
	//class. If it is found then it rank it was found in is returned, else -1 is
	//returned.
	public int getGirlRank(int year, String name) {
		if(names.lookup(year) == null) {
			return -1;
		} else {
			for (Integer i: names.lookup(year).listOfKeys()) {
				if (names.lookup(year).lookup(i).getGirl().compareTo(name) 
						== 0) {
					return i;
				}
			}
			return -1;
		}
	}

	//returns the rank of the parameter boy name in the parameter year by
	//iterating through the ranks of the specified year using the listOfKeys
	//method of the Tree interface. It looks up the boy name by using the 
	//lookup method of the Tree interface and the getBoy method in the NamePair 
	//class. If it is found then it rank it was found in is returned, else -1 is
	//returned.
	public int getBoyRank(int year, String name) {
		if(names.lookup(year) == null) {
			return -1;
		} else {
			for (Integer i: names.lookup(year).listOfKeys()) {
				if (names.lookup(year).lookup(i).getBoy().compareTo(name) 
						== 0) {
					return i;
				}
			}
			return -1;
		}
	}
}
