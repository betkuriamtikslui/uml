package Evaluate;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class EvaluateTest {
	Evaluate eval;
	
	@Before
	public void setUp(){
		eval = new Evaluate();
	}
	
	
	

	@Test
	public void test() {
		String string = "true&&true";
		assertTrue(eval.eval(string) == true);
		string = "true&&false";

		assertTrue(eval.eval(string) != true);
		string = "true&&false";
		assertTrue(eval.eval(string) == false);

		string = "true&&false || true";
		assertTrue(eval.eval(string) == true);

	}

}
