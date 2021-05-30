package Bonus;

import java.util.Stack;

//-------------------------------------------------------------------------
/**
 * Utility class containing validation/evaluation/conversion operations for
 * infix arithmetic expressions.
 *
 * @author Bernadine Lao
 * @version 1/12/15 13:03:48
 */

public class Arith {

//~ Validation methods ..........................................................

	/**
	 * Validation method for infix notation.
	 *
	 * @param infixLiterals : an array containing the string literals hopefully in
	 *                      infix order. The method assumes that each of these
	 *                      literals can be one of: - "+", "-", "*", or "/" - or a
	 *                      valid string representation of an integer "0", "1" ,
	 *                      "2", ..., "-1", "-2", ...
	 *
	 * @return true if the parameter is indeed in infix notation, and false
	 *         otherwise.
	 **/
	public static boolean validateInfixOrder(String infixLiterals[]) {
		int count = 1;
		for (int i = 0; i < infixLiterals.length; i++) {
			if (infixLiterals[i] == "+" || infixLiterals[i] == "-" || infixLiterals[i] == "*"
					|| infixLiterals[i] == "/") {
				count++;
			} else if (infixLiterals[i] == "(") {
				count++;

			} else if (infixLiterals[i] == ")") {
				count--;

			} else {

				try {
					Integer.parseInt(infixLiterals[i]);
					count--;
				} catch (NumberFormatException e) {

				}
			}
		}
		if (count == 0) {
			return true;
		}
		return false;
	}

//~ Evaluation  methods ..........................................................

	/**
	 * Evaluation method for infix notation.
	 *
	 * @param infixLiterals : an array containing the string literals in infix
	 *                      order. The method assumes that each of these literals
	 *                      can be one of: - "+", "-", "*", or "/" - or a valid
	 *                      string representation of an integer.
	 *
	 * @return the integer result of evaluating the expression
	 **/
	public static int evaluateInfixOrder(String infixLiterals[]) {
		String array[] = convertInfixToPostfix(infixLiterals);
		Stack<Integer> stack = new Stack<Integer>();
		int op1 = 0;
		int op2 = 0;
		for (int i = 0; i < array.length; i++) {
			// System.out.println(array[i]);
			if (array[i].contentEquals("+")) {
				op1 = stack.pop();
				op2 = stack.pop();
				stack.push(op2 + op1);
			} else if (array[i].contentEquals("-")) {
				op1 = stack.pop();
				op2 = stack.pop();
				stack.push(op2 - op1);
			} else if (array[i].contentEquals("*")) {
				op1 = stack.pop();
				op2 = stack.pop();
				stack.push(op2 * op1);
			} else if (array[i].contentEquals("/")) {
				op1 = stack.pop();
				op2 = stack.pop();
				stack.push(op2 / op1);
			} else {
				stack.push(Integer.parseInt(array[i]));
			}
		}
		return stack.pop();
	}

//~ Conversion  methods ..........................................................

	/**
	 * Converts infix to postfix.
	 *
	 * @param infixLiterals : an array containing the string literals in infix
	 *                      order. The method assumes that each of these literals
	 *                      can be one of: - "+", "-", "*", or "/" - or a valid
	 *                      string representation of an integer.
	 *
	 * @return the expression in postfix order.
	 **/
	public static String[] convertInfixToPostfix(String infixLiterals[]) {
		String output = "";
		if (validateInfixOrder(infixLiterals) == true) {

			Stack<String> stack = new Stack<String>();

			for (int i = 0; i < infixLiterals.length; i++) {

				if (infixLiterals[i] == "(") {
					stack.push(infixLiterals[i]);
				} else if (infixLiterals[i] == ")") {
					while (stack.peek() != "(") {
						output += stack.pop() + " ";
					}
					stack.pop();// pops '('
				} else if (infixLiterals[i] == "+") {
					if (stack.isEmpty() || stack.peek() == "(" || stack.peek() == "+" || stack.peek() == "-") {
						stack.push(infixLiterals[i]);
					}
				} else if (infixLiterals[i] == "-") {
					if (stack.isEmpty() || stack.peek() == "(" || stack.peek() == "+" || stack.peek() == "-") {
						stack.push(infixLiterals[i]);
					}
				} else if (infixLiterals[i] == "*") {
					stack.push(infixLiterals[i]);

				} else if (infixLiterals[i] == "/") {
					stack.push(infixLiterals[i]);

				}

				else {
					output += infixLiterals[i] + " ";
				}

			}

			while (!stack.isEmpty()) {

				output += stack.pop() + " "; // the last

			}

		}
		String[] array = output.split(" ");
		return array;
	}

	/**
	 * Converts postfix to infix.
	 *
	 * @param postfixLiterals : an array containing the string literals in postfix
	 *                        order. The method assumes that each of these literals
	 *                        can be one of: - "+", "-", "*", or "/" - or a valid
	 *                        string representation of an integer.
	 *
	 * @return the expression in infix order.
	 **/
	public static String[] convertPostfixToInfix(String postfixLiterals[]) {
		Stack<String> stack = new Stack<String>();
		String op1 = "";
		String op2 = "";
		for (int i = 0; i < postfixLiterals.length; i++) {
			if (postfixLiterals[i] == "+" || postfixLiterals[i] == "-" || postfixLiterals[i] == "*"
					|| postfixLiterals[i] == "/") {
				op1 = stack.pop();
				op2 = stack.pop();
				stack.push("( " + op2 + " " + postfixLiterals[i] + " " + op1 + " " + ")");
			} else {
				stack.push(postfixLiterals[i]);
			}
		}
		String[] array = stack.pop().split(" ");
		return array;

	}

}

/*
 * Q.5 I have used Stack throughout my Arith.java file stack.push(object): has
 * O(1) as it just has condition statement if the index of the stack is full. if
 * not, it will insert the object in it.
 * 
 * stack.pop(): O(1) condition statements that only involves the top of the
 * stack. deletes the index stack.peek(): O(1) condition statement only
 * involving the top of the stack. returns item on top of the stack
 * stack.isEmpty(): O(1) condition statement returns true if stack is empty else
 * false;
 * 
 * Q6 validateInfixOrder - i did not use stack in this method. It has theta(N)
 * running time as my for loop goes through all the infixLiterals. inside the
 * for loop are condition statements that take theta(1). therefore the running
 * time of the method is theta(N) by highest order
 * 
 * evaluateInfixOrder -takes convertInfixToPostfix which is O(N^2) -has a for
 * loop with theta(N) as I know the loop will reiterate till the end of the
 * array. -inside the loop are simple condition statements with
 * stack.pop(),.push(String). which are O(1) therefore, the method takes O(N^2)
 * by highest order
 * 
 * convertInfixToPostfix -theta(N) from calling validInfixOrder, theta(N) from
 * the forloop as I know it need to go till the end of the array passed into it.
 * -inside the loop are condition statements which are O(1) with the
 * .pop(),.push(String), .peek() and .isEmpty(), theres a while loop in one of
 * the condition statements that at worst, the input might be (1+2-3/.....n)
 * where operators are N/2 and the stack will contain ( and operands so O (N/2)
 * -after the forloop, is a while loop that pops the last item:O(N) I have a
 * .split(" ") which thates O(N) therefore, this method takes at worst case
 * O(N^2)by highest order
 * 
 * convertPostfixToInfix -theta(N) for the forloop iterates till the last
 * element. -has conditions that take O(1) from the stack data structure
 * -theta(N+N-1) for the .split(" ") the output will include not only the
 * numbers and operators but also the "("")" -worstcase running time is
 * theta(N)by highest order
 */