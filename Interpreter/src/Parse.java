/* 
 * Parse.java 
 * 
 * Version: 
 *     $Id: Parse.java,v 1.1 2014/02/18 07:47:27 txw6529 Exp $ 
 * 
 * Revisions: 
 *     $Log: Parse.java,v $
 *     Revision 1.1  2014/02/18 07:47:27  txw6529
 *     Initial commit / lab complete
 *
 */

import java.util.ArrayList;
import java.util.Arrays;

/**
 * A class with static methods to parse a String into
 * one of the Expression objects.
 * 
 * @author Tristan Whitcher
 */
public class Parse {
	
	/**
	 * Parses a given prefix String into one of the Expression
	 * implemented objects. Handles invalid syntax exception.
	 * 
	 * @param s The string to parse
	 * @return the parsed expression object.
	 */
	public static Expression parseString(String s) {
		try {
			return parse(new ArrayList<String>(Arrays.asList(s.split("\\s+"))));
		} catch(NumberFormatException nfe) {
			return null;
		}
	}
	
	/**
	 * Parses a given ArrayList of tokens into the Expression
	 * implemented object representing those tokens. Cases all
	 * return so they do not need breaks.
	 * 
	 * @param tokens the tokens to parse into an expression.
	 * @return an expression representing the tokens.
	 */
	private static Expression parse(ArrayList<String> tokens) {
		String current = tokens.remove(0);
		switch(current) {
			case "*": return new MulExpression(parse(tokens), parse(tokens));
			case "+": return new AddExpression(parse(tokens), parse(tokens));
			case "-": return new SubExpression(parse(tokens), parse(tokens));
			default:  return new IntExpression(current);
		}
	}
	
}
