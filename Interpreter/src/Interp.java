/*
 * Interp.java 
 * 
 * Version: 
 *     $Id: Interp.java,v 1.1 2014/02/18 07:47:26 txw6529 Exp $ 
 * 
 * Revisions: 
 *     $Log: Interp.java,v $
 *     Revision 1.1  2014/02/18 07:47:26  txw6529
 *     Initial commit / lab complete
 *
 */

import java.util.ArrayList;
import java.util.Scanner;

/**
 * A container for the static methods, including a main method,
 * that converts prefix expressions to infix and evaluates them.
 * 
 * @author      Arthur Nunes-Harwitt
 *
 */

public class Interp {


	
	/**
	 * The main method for the Interp.  It reads in variable bindings
	 * from standard input.  It creates an environment,
	 * and performs evaluation and generates the infix form.
	 *
	 * @param       args    Command line arguments are not used.
	 *
	 *
	 */

    public static void main(String[] args){ 
		Scanner in = new Scanner(System.in);
	
		System.out.println("Welcome to Interp v1.0");
	
		System.out.println("Enter prefix expressions, RETURN to quit...");
		while(true){
		    System.out.print("Interp> ");
		    String s = in.nextLine();
	
		    if (s.isEmpty()){
		    	break;
		    }
		    
		    Expression e = Parse.parseString(s);
		    
		    if (e == null){
		    	System.out.println("Syntax Error.");
		    } else {
				System.out.println("Infix expression: " + e.emit());
				Integer result = e.evaluate();
				if (result == null) {
				    System.out.println("Run time error.");
				} else {
				    System.out.println("Evaluation: " + result);
				}
		    }
		}
		System.out.println("Goodbye Interp.");

    }

}