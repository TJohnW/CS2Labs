/* 
 * IntExpression.java 
 * 
 * Version: 
 *     $Id: IntExpression.java,v 1.1 2014/02/18 07:47:24 txw6529 Exp $ 
 * 
 * Revisions: 
 *     $Log: IntExpression.java,v $
 *     Revision 1.1  2014/02/18 07:47:24  txw6529
 *     Initial commit / lab complete
 *
 */

/**
 * A class to represent an IntExpression ex: 5
 * 
 * @author Tristan Whitcher
 */
public class IntExpression implements Expression {

	private Integer value;
	
	/**
	 * Constructs an instance of IntExpression with a new value.
	 * 
	 * @param valString the new integer as a string
	 */
	public IntExpression(String valString) {
		value = Integer.parseInt(valString);
	}
	
	/**
	 * Returns the Integer value of this IntExpression.
	 */
	public Integer evaluate() {
		return value;
	}
	
	/**
	 * Returns the String representation of this IntExpression.
	 */
	public String emit() {
		return value.toString();
	}
	
}