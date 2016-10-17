package model;

import org.junit.After;
import org.junit.Before;
import People.Member;

public class ModelTest {

	Model model;
	Member member;

	@Before
	public void setUp() {
		model = new Model();
		member = new Member("name", "19901117-213");
	}

	@After
	public void destroy() {
		model = null;
	}
//
//	@Test
//	public void testAuthentificate() {
//		model.addMember(member);
//		Member member2 = model.authentificate(member.getName());
//		assertEquals(member2, member);
//
//	}
//
//	@Test
//	public void testQuery() {
//		model.addMember(member);
//		ArrayList<Member> newList;
//		model.addMember(new Member("ururu", "19881111-647"));
//
//		newList = model.Query("name:" + "n*" + "||birthday:" + "17");
//		System.out.println(newList.size());
//	}

//	@Test
//	public void testDeleteMemberMember() {
//		model.addMember(member.getName(), member.getPersonalID());
//		assert (model.getLength() == 1);
//		model.deleteMember(member.getName(), member.getPersonalID());
//		System.out.println(model.getLength());
//		assert (model.getLength() == 0);
//
//	}
//
//	//
//	// @Test
//	// public void testDeleteMemberStringInt() {
//	// fail("Not yet implemented");
//	// }
//	//
//	@Test
//	public void testAddMember() {
//		assert (model.getLength() == 0);
//		try {
//			model.addMember("name", "19901011");
//		} catch (Exception e) {
//
//		}
//		try {
//			model.addMember("name", "19901117-213");
//		} catch (Exception e) {
//
//		}
//		assert (model.getLength() == 1);
//
//	}
//
//	@Test
//	public void testDateIsValid() {
//
//		assertTrue(model.dateIsValid("19901117-213"));
//		assertFalse(model.dateIsValid("19900932-132"));
//	}
//
//	@Test
//	public void testNameIsValid() {
//		String name = "asd2";
//		assertFalse(model.nameIsValid(name));
//		name = "wqe";
//		assertTrue(model.nameIsValid(name));
//	}
//
}
