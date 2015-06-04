package tests;

import names.Names;

import org.junit.*;

import static org.junit.Assert.*;

@SuppressWarnings("unchecked")
public class StudentTests {

	@Test
	public void Test1() {
	    Names names= new Names();

	    String [] top10BoyNames1995 = {"Michael", "Matthew", "Christopher", 
	    		"Jacob", "Joshua", "Nicholas", "Tyler", "Brandon", "Daniel", 
	    		"Austin"};
	    
	    String [] top10GirlNames1995 = {"Jessica", "Ashley", "Emily", 
	    		"Samantha", "Sarah", "Taylor", "Hannah", "Brittany", "Amanda",
	    		"Elizabeth"};
	    
	    names.getNames(1995, 1995, 10);
	    
	    for (int i = 0; i < 10; i++) {
	    	assertEquals(top10BoyNames1995[i], names.getBoyName(1995, i + 1));
	    }
	    for (int i = 0; i < 10; i++) {
	    	assertEquals(top10GirlNames1995[i], names.getGirlName(1995, i + 1));
	    }
	}
	
	@Test
	public void Test2() {
	    Names names= new Names();

	    names.getNames(1995, 1995, 10);

	    assertNull(names.getBoyName(1996, 10));
	    assertNull(names.getGirlName(1996, 10));
	    assertNull(names.getBoyName(1995, 11));
	    assertNull(names.getGirlName(1995, 11));
	}
	
	@Test
	public void Test3() {
	    Names names= new Names();

	    names.getNames(1995, 1995, 10);
	    
	    assertEquals(-1, names.getBoyRank(1996, "Andrew"));
	    assertEquals(-1, names.getGirlRank(1996, "Kayla"));
	    assertEquals(-1, names.getBoyRank(1995, "Andrew"));
	    assertEquals(-1, names.getGirlRank(1995, "Kayla"));
	}
	
	@Test
	public void Test4() {
	    Names names= new Names();

	    names.getNames(1995, 1995, 10);
	    
	    String [] top10BoyNames1995 = {"Michael", "Matthew", "Christopher", 
	    		"Jacob", "Joshua", "Nicholas", "Tyler", "Brandon", "Daniel", 
	    		"Austin"};
	    
	    String [] top10GirlNames1995 = {"Jessica", "Ashley", "Emily", 
	    		"Samantha", "Sarah", "Taylor", "Hannah", "Brittany", "Amanda",
	    		"Elizabeth"};
	    
	    names.getNames(1995, 1995, 11);
	    
	    for (int i = 0; i < 10; i++) {
	    	assertEquals(top10BoyNames1995[i], names.getBoyName(1995, i + 1));
	    }
	    for (int i = 0; i < 10; i++) {
	    	assertEquals(top10GirlNames1995[i], names.getGirlName(1995, i + 1));
	    }
	    
	    assertEquals("Andrew", names.getBoyName(1995, 11));
	    assertEquals("Kayla", names.getGirlName(1995, 11));
	    assertEquals(11, names.getBoyRank(1995, "Andrew"));
	    assertEquals(11, names.getGirlRank(1995, "Kayla"));
	}
	
	@Test
	public void Test5() {
	    Names names= new Names();

	    names.getNames(1995, 1995, 10);
	    
	    names.getNames(1995, 1995, 11);

	    String [] top10BoyNames1996 = {"Michael", "Matthew", "Jacob", 
	    		"Christopher", "Joshua", "Nicholas", "Tyler", "Brandon", 
	    		"Austin", "Andrew"};
	    
	    String [] top10GirlNames1996 = {"Emily", "Jessica", "Ashley", 
	    		"Sarah", "Samantha", "Taylor", "Hannah", "Alexis", "Rachel",
	    		"Elizabeth"};
	    
	    names.getNames(1995, 1996, 12);

	    for (int i = 0; i < 10; i++) {
	    	assertEquals(top10BoyNames1996[i], names.getBoyName(1996, i + 1));
	    }
	    for (int i = 0; i < 10; i++) {
	    	assertEquals(top10GirlNames1996[i], names.getGirlName(1996, i + 1));
	    }
	    
	    assertEquals("Joseph", names.getBoyName(1995, 12));
	    assertEquals("Rachel", names.getGirlName(1995, 12));
	    assertEquals(12, names.getBoyRank(1995, "Joseph"));
	    assertEquals(12, names.getGirlRank(1995, "Rachel"));
	    
	    assertEquals("Joseph", names.getBoyName(1996, 12));
	    assertEquals("Megan", names.getGirlName(1996, 12));
	    assertEquals(12, names.getBoyRank(1996, "Joseph"));
	    assertEquals(12, names.getGirlRank(1996, "Megan"));
	}
}
