package Bonus;

import static org.junit.Assert.*;

import java.util.Arrays;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

/*
 * @author: Bernadine Lao
 */

@RunWith(JUnit4.class)
public class ArithTest {

	@Test
	public void testValidateInfixOrder() {
		Arith calc = new Arith();
		String[] in= {};
		assertFalse("Check if valid input", calc.validateInfixOrder(in));
		String[] in1= {"(", "(", "1"};
		assertFalse("Check if valid input", calc.validateInfixOrder(in1));
		String[] in2= {"1", "+", "a"};
		assertFalse("Check if valid input", calc.validateInfixOrder(in2));
		String[] input = { "(", "(", "1", "-", "2", ")", "*", "3", ")", "+", "(", "10", "-", "(", "3", "+", "(", "6",
				"/", "3", ")", ")", ")" };
		assertTrue("Check if valid input", calc.validateInfixOrder(input));
	}

	@Test
	public void testConvertInfixToPostfix() {
		Arith calc = new Arith();
		
		String[] in1= {"1", "+", "a"};
		assertEquals("Check if same","[]",
				Arrays.deepToString(calc.convertInfixToPostfix(in1)));
		
		String[] in2= {"(", "1", "+", "1", ")"};
		assertEquals("Check if same","[1, 1, +]",
				Arrays.deepToString(calc.convertInfixToPostfix(in2)));
		String[] in3= {"1", "-", "1"};
		assertEquals("Check if same","[1, 1, -]",
				Arrays.deepToString(calc.convertInfixToPostfix(in3)));
		
		String[] in4= {"(","1","+","1",")","-","(","1","+","1",")","-","(","1","-","1",")"};
//		System.out.println(Arrays.deepToString(calc.convertInfixToPostfix(in2)));
		assertEquals("Check if same","[1, 1, +, 1, 1, +, 1, 1, -, -, -]",
				Arrays.deepToString(calc.convertInfixToPostfix(in4)));
			
		String[] input = { "(", "(", "1", "-", "2", ")", "*", "3", ")", "+", "(", "10", "-", "(", "3", "+", "(", "6",
				"/", "3", ")", ")", ")" };
//		System.out.println(Arrays.deepToString(calc.convertInfixToPostfix(input)));
		assertEquals("Check if the same", "[1, 2, -, 3, *, 10, 3, 6, 3, /, +, -, +]",
				Arrays.deepToString(calc.convertInfixToPostfix(input)));
	}

	@Test
	public void testEvaluateInfixOrder() {
		Arith calc = new Arith();
		String[] input = { "(", "(", "1", "-", "2", ")", "*", "3", ")", "+", "(", "10", "-", "(", "3", "+", "(", "6",
				"/", "3", ")", ")", ")" };
//		System.out.println(calc.evaluateInfixOrder(input));
		assertEquals("Check if same", 2, calc.evaluateInfixOrder(input));
	}

	@Test
	public void testConvertPostfixToInfix() {
		Arith calc = new Arith();
		String[] input = { "10", "7", "3", "2", "+", "*", "-", "7", "2", "/", "+"};
//		System.out.println(Arrays.deepToString(calc.convertPostfixToInfix(input)));
		assertEquals("Check if same", "[(, (, 10, -, (, 7, *, (, 3, +, 2, ), ), ), +, (, 7, /, 2, ), )]",
				Arrays.deepToString(calc.convertPostfixToInfix(input)));
	}

}
