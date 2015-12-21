/* 
 * SubExpression.java 
 * 
 * Version: 
 *     $Id: SubExpression.java,v 1.1 2014/02/18 07:47:27 txw6529 Exp $ 
 * 
 * Revisions: 
 *     $Log: SubExpression.java,v $
 *     Revision 1.1  2014/02/18 07:47:27  txw6529
 *     Initial commit / lab complete
 *
 */

/**
 * A class to represent an SubExpression ex: (5 - 3)
 * 
 * @author Tristan Whitcher
 */
public class SubExpression implements Expression {

	private Expression leftExpression;
	private Expression rightExpression;
	
	/**
	 * Constructs an instance of SubExpression with a new left and
	 * right expression set. left - right.
	 * 
	 * @param exp1 the left expression
	 * @param exp2 the right expression
	 */
	public SubExpression(Expression exp1, Expression exp2) {
		leftExpression = exp1;
		rightExpression = exp2;
	}
	
	/**
	 * Returns the Integer value of this SubExpression.
	 */
	public Integer evaluate() {
		return leftExpression.evaluate() - rightExpression.evaluate();
	}
	
	/**
	 * Returns the String representation of this SubExpression.
	 */
	public String emit() {
		return "(" + leftExpression.emit() + " - " + rightExpression.emit() + ")";
	}
	
}
