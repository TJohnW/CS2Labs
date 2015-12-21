
/*
 * $Id: AdvancedMove.java,v 1.2 2014/05/11 23:25:03 p142-06b Exp $
 * 
 * $Log: AdvancedMove.java,v $
 * Revision 1.2  2014/05/11 23:25:03  p142-06b
 * Updated docs and some strategy
 *
 * Revision 1.1  2014/05/07 23:14:05  p142-06b
 * commit
 *
 * Revision 1.2  2014/04/30 01:05:25  txw6529
 * Finished
 *
 */

package Players.Milky;

import Interface.PlayerMove;

public class AdvancedMove {

	public PlayerMove move;
	
	public int worth;
	
	/**
	 * Creates a wrapper with a playermove and the worth
	 * @param move the move itself
	 * @param worth the worth of the move
	 */
	public AdvancedMove(PlayerMove move, int worth) {
		this.move = move;
		this.worth = worth;
	}
	
}
