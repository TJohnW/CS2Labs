/*
 * GermanBoardGame.java
 *
 * Version: 
 *	$Id: GermanBoardGame.java,v 1.1 2014/02/26 03:15:38 txw6529 Exp $
 *
 * Revisions:
 *	$Log: GermanBoardGame.java,v $
 *	Revision 1.1  2014/02/26 03:15:38  txw6529
 *	Initial commit
 *
 */

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * GameNight, runs the game!
 * @author Tristan Whitcher
 */
public class GermanBoardGame extends BoardGame {

	/**
     * Constructor, takes a name for the game
     * 
     * @param n Name of the game
     * @param np Number of players for the game
     */
	public GermanBoardGame(String n, int np) {
		super(n, np, 0);
	}
	
	/**
	 * Picks players from the given playerlist and picks
	 * num amount of adults. (>10)
	 * 
	 * @param playerList ArrayList of players
	 * @param num Amount of players to pick
	 */
	public void pickPlayers(ArrayList<Player> playerList, int num) {
		System.out.println("Picking players for " + name);
		Collections.shuffle(playerList);
		
		List<Player> adults = new ArrayList<Player>();
		for(Player p: playerList) {
			if(p.getAge() > 10)
				adults.add(p);
		}
		
		int maxAdultPlayers = adults.size();
		
		if(num > maxAdultPlayers)
			num = maxAdultPlayers;
		
		players = new ArrayList<Player>(adults.subList(0, num));
		for(Player p: players) {
			System.out.println(p.getName());
		}
	}
	
}
