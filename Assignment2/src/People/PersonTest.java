package People;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class PersonTest {
	Member member;
	String name;
	String id ;
	@Before
	public void setUp(){
		id =  "19890619-657";
		name = "name";
		member = new Member(name, id);

	}
	@After
	public void destroy(){
		
	}


	@Test
	public void testGetName() {
		assertTrue(member.getName().equals(name));
	}

	@Test
	public void testSetName() {
		String newName = "newName";
		member.setName(newName);
		assertTrue(member.getName().equals(newName));
	}

	@Test
	public void testGetPersonalID() {
		assertTrue(member.getPersonalID().equals(id));

	}

	
	
	@Test
	public void testCompareTo() {
		Member second = new Member(name,id);
		Member  third = new Member(name, "19893449-768");
		assert(member.compareTo(second) == 0 );
		assert(member.compareTo(third) != 0 );
	}

	@Test
	public void testGetMonth() {
		assertTrue(member.getMonth().equals("06"));
	}

	@Test
	public void testGetYear() {
		assertTrue(member.getYear().equals("1989"));
	}

	@Test
	public void testGetDay() {
		assertTrue(member.getDay().equals("19"));
	}

}
