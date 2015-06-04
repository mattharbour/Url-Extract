/**
 * The purpose of this class is to keep the extracted boy and girl name for each
 * rank for each year together.
 * @author Matt Harbour  
 */
package names;

public class NamePair {

	private String boy, girl; 
	
	public NamePair(String boyName, String girlName) {
		boy = boyName;
		girl = girlName;
	}
	
	public String getBoy() {
		return boy;
	}
	
	public String getGirl() {
		return girl;
	}
	
	public String toString() {
		return toString(boy, girl);
	}
	
	private String toString(String boyName, String girlName) {
		return boyName + "&" + girlName;
	}
}
