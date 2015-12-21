/**
 * Snowflake.java
 *
 * File:
 *	$Id: Snowflake.java,v 1.1 2014/02/05 01:21:46 txw6529 Exp $
 *
 * Revisions:
 *	$Log: Snowflake.java,v $
 *	Revision 1.1  2014/02/05 01:21:46  txw6529
 *	Snowflake Project
 *
 */

/**
 * A snowflake drawing program
 *
 * @author Tristan Whitcher
 */

import java.util.Scanner;

public class Snowflake {
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);
		System.out.print("S: ");
		int sideLength = s.nextInt();
		System.out.print("N: ");
		int depth = s.nextInt();
		Turtle t = init(sideLength);
		snowflake(sideLength, depth, t);
	}
	
	public static Turtle init(int S) {
		Turtle t = new Turtle(S*1.5, S*1.5, 0.0);
		t.setWorldCoordinates(0, 0, S*3, S*3);
		return t;
	}
	
	public static void snowflakeSide(int S, int N, Turtle t) {
		if(N > 0) {
			t.goForward(S);
			if(N > 1) {
				t.turnLeft(120);
				for(int i = 0; i < 5; i++) {
					snowflakeSide((S/3), N-1, t);
					t.turnRight(60);
				}
				t.turnRight(180);
			}
			t.goForward(-S);
		}
		
	}
	
	public static void snowflake(int S, int N, Turtle t) {
		for(int i = 0; i < 6; i++) {
			snowflakeSide(S, N, t);
			t.turnLeft(60);
		}
		
	}
}
