package tests;

import names.Names;
import org.junit.*;
import static org.junit.Assert.*;

public class PublicTests {

  // test basic functionality; just get the top name (both for girls and
  // boys) for one year, and verify that the #1 girl's name is correct
  @Test public void testPublic1() {
    Names names= new Names();

    names.getNames(2008, 2008, 1);

    assertEquals("Emma", names.getGirlName(2008, 1));
  }

  // test basic functionality; just get the top name (both for girls and
  // boys) for one year, and verify that the #1 boy's name is correct
  @Test public void testPublic2() {
    Names names= new Names();

    names.getNames(2009, 2009, 1);

    assertEquals("Jacob", names.getBoyName(2009, 1));
  }

  // test basic functionality; just get the top name (both for girls and
  // boys) for one year, and verify that the #1 girl's name is recorded with
  // the correct rank
  @Test public void testPublic3() {
    Names names= new Names();

    names.getNames(1998, 1998, 1);

    assertEquals(1, names.getBoyRank(1998, "Michael"));
  }

  // test basic functionality; just get the top name (both for girls and
  // boys) for one year, and verify that the #1 girl's name is recorded with
  // the correct rank
  @Test public void testPublic4() {
    Names names= new Names();

    names.getNames(2007, 2007, 1);

    assertEquals(1, names.getGirlRank(2007, "Emily"));
  }

  // get the top four names for one year, and verify that the girls names
  // are all correct
  @Test public void testPublic5() {
    Names names= new Names();
    String[] topFourGirlsNames= {"Emily", "Hannah", "Madison", "Ashley"};
    int i;

    names.getNames(2000, 2000, 4);

    for (i= 0; i < 4; i++)
      assertEquals(topFourGirlsNames[i], names.getGirlName(2000, i + 1));
  }

  // get the top four names for one year, and verify that the boys names are
  // all correct
  @Test public void testPublic6() {
    Names names= new Names();
    String[] topFourBoysNames= {"Michael", "Christopher", "Jason", "David"};
    int i;

    names.getNames(1980, 1980, 4);

    for (i= 0; i < 4; i++)
      assertEquals(topFourBoysNames[i], names.getBoyName(1980, i + 1));
  }

  // get the top four names for one year, and verify that the ranks of the
  // girls names are correct
  @Test public void testPublic7() {
    Names names= new Names();
    String[] topFourGirlsNames= {"Jessica", "Ashley", "Jennifer", "Amanda"};
    int i;

    names.getNames(1985, 1985, 4);

    for (i= 0; i < 4; i++)
      assertEquals(i + 1, names.getGirlRank(1985, topFourGirlsNames[i]));
  }

  // get the top four names for one year, and verify that the ranks of the
  // boys names are all correct
  @Test public void testPublic8() {
    Names names= new Names();
    String[] topFourBoysNames= {"Michael", "Christopher", "Matthew",
                                "Joshua"};
    int i;

    names.getNames(1984, 1984, 4);

    for (i= 0; i < 4; i++)
      assertEquals(i + 1, names.getBoyRank(1984, topFourBoysNames[i]));
  }

  // get the top name for four years, and verify that the girls names are
  // all correct
  @Test public void testPublic9() {
    Names names= new Names();
    String[] topFourGirlsNames= {"Emily", "Emily", "Emma", "Isabella"};
    int i;

    names.getNames(2006, 2009, 1);

    for (i= 0; i < 4; i++)
      assertEquals(topFourGirlsNames[i], names.getGirlName(2006 + i, 1));
  }

  // get the top name for four years, and verify that the boys names are
  // all correct
  @Test public void testPublic10() {
    Names names= new Names();
    String[] topFourBoysNames= {"James", "Robert", "Michael", "Michael"};
    int i;

    names.getNames(1952, 1955, 1);

    for (i= 0; i < 4; i++)
      assertEquals(topFourBoysNames[i], names.getBoyName(1952 + i, 1));
  }

  // get the top name for four years, and verify that the ranks of the girls
  // names are all correct
  @Test public void testPublic11() {
    Names names= new Names();
    String[] topFourGirlsNames= {"Emily", "Emily", "Emma", "Isabella"};
    int i;

    names.getNames(2006, 2009, 1);

    for (i= 0; i < 4; i++)
      assertEquals(1, names.getGirlRank(2006 + i, topFourGirlsNames[i]));
  }

  // get the top name for four years, and verify that the ranks of the boys
  // names are all correct
  @Test public void testPublic12() {
    Names names= new Names();
    String[] topFourBoysNames= {"James", "Robert", "Michael", "Michael"};
    int i;

    names.getNames(1952, 1955, 1);

    for (i= 0; i < 4; i++)
      assertEquals(1, names.getBoyRank(1952 + i, topFourBoysNames[i]));
  }

  // get the top four names for four years, and verify that all of the names
  // are all correct
  @Test public void testPublic13() {
    String girlNames[][]= {{"Emily", "Emma", "Madison", "Isabella", },
                           {"Emily", "Isabella", "Emma", "Ava"},
                           {"Emma", "Isabella", "Emily", "Olivia"},
                           {"Isabella", "Emma", "Olivia", "Sophia"}};
    String boyNames[][]= {{"Jacob", "Michael", "Joshua", "Ethan"},
                          {"Jacob", "Michael", "Ethan", "Joshua"},
                          {"Jacob", "Michael", "Ethan", "Joshua"},
                          {"Jacob", "Ethan", "Michael", "Alexander"}};
    int i, j;

    Names names= new Names();
    names.getNames(2006, 2009, 4);

    for (i= 0; i < 4; i++)
      for (j= 0; j < 4; j++) {
        assertEquals(girlNames[i][j], names.getGirlName(2006 + i, j + 1));
        assertEquals(boyNames[i][j], names.getBoyName(2006 + i, j + 1));
      }
  }

  // get the top four names for four years, and verify that the ranks of all
  // of the names are all correct
  @Test public void testPublic14() {
    String girlNames[][]= {{"Jennifer", "Lisa", "Kimberly", "Michelle"},
                           {"Jennifer", "Michelle", "Lisa", "Kimberly"},
                           {"Jennifer", "Michelle", "Lisa", "Kimberly"},
                           {"Jennifer", "Amy", "Michelle", "Kimberly"}};
    String boyNames[][]= {{"Michael", "James", "David", "John"},
                          {"Michael", "James", "David", "John"},
                          {"Michael", "Christopher", "James", "David"},
                          {"Michael", "Christopher", "Jason", "James"}};
    int i, j;

    Names names= new Names();
    names.getNames(1970, 1973, 4);

    for (i= 0; i < 4; i++)
      for (j= 0; j < 4; j++) {
        assertEquals(j + 1, names.getGirlRank(1970 + i, girlNames[i][j]));
        assertEquals(j + 1, names.getBoyRank(1970 + i, boyNames[i][j]));
      }
  }

}
