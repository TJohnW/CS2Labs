/* 
 * MulExpression.java 
 * 
 * Version: 
 *     $Id: MulExpression.java,v 1.1 2014/02/18 07:47:25 txw6529 Exp $ 
 * 
 * Revisions: 
 *     $Log: MulExpression.java,v $
 *     Revision 1.1  2014/02/18 07:47:25  txw6529
 *     Initial commit / lab complete
 *
 */

/**
 * A class to represent an MulExpression ex: (5 * 3)
 * 
 * @author Tristan Whitcher
 */
public class MulExpression implements Expression {

	private Expression leftExpression;
	private Expression rightExpression;
	
	/**
	 * Constructs an instance of MulExpression with a new left and
	 * right expression set. left * right.
	 * 
	 * @param exp1 the left expression
	 * @param exp2 the right expression
	 */
	public MulExpression(Expression exp1, Expression exp2) {
		leftExpression = exp1;
		rightExpression = exp2;
	}
	
	/**
	 * Returns the Integer value of this MulExpression.
	 */
	public Integer evaluate() {
		return leftExpression.evaluate() * rightExpression.evaluate();
	}
	
	/**
	 * Returns the String representation of this MulExpression.
	 */
	public String emit() {
		return "(" + leftExpression.emit() + " * " + rightExpression.emit() + ")";
	}
	
}
