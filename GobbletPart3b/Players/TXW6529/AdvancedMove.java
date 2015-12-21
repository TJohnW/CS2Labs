
/*
 * $Id: AdvancedMove.java,v 1.2 2014/04/30 01:05:25 txw6529 Exp $
 * 
 * $Log: AdvancedMove.java,v $
 * Revision 1.2  2014/04/30 01:05:25  txw6529
 * Finished
 *
 */

package Players.TXW6529;

import Interface.PlayerMove;

public class AdvancedMove {

	public PlayerMove move;
	
	public int worth;
	
	public AdvancedMove(PlayerMove move, int worth) {
		this.move = move;
		this.worth = worth;
	}
	
}
