/*
 * Game.java
 *
 * Version: 
 *	$Id: Game.java,v 1.1 2014/02/26 03:15:39 txw6529 Exp $
 *
 * Revisions:
 *	$Log: Game.java,v $
 *	Revision 1.1  2014/02/26 03:15:39  txw6529
 *	Initial commit
 *
 */

import java.util.ArrayList;
import java.util.Collections;

/**
 * Abstract class to represent a game
 * @author Tristan Whitcher
 */
public abstract class Game {
	
	protected int maxPlayers;
	
	protected String name;
	protected ArrayList<Player> players;
	
	/**
     * Constructor, takes a name for the game
     * 
     * @param n Name of the game
     * @param np Number of players for the game
     */
	public Game(String n, int np) {
		name = n;
		maxPlayers = np;
	}
	
	/**
	 * Abstract method to be created by all classes extending
	 * Game.
	 */
	public abstract void play();
	
	/**
	 * Don't really understand why this is needed. 
	 * (shuffle list and return subset??)
	 * 
	 * @param p Player to check if playing
	 * @return true if p is in the player arraylist.
	 */
	public boolean isPlaying(Player p) {
		if(players.contains(p)) return true;
		return false;
	}
	
	/**
	 * Picks players from the given playerlist
	 * 
	 * @param playerList ArrayList of players
	 */
	public void pickPlayers(ArrayList<Player> playerList) {
		pickPlayers(playerList, maxPlayers);
	}
	
	/**
	 * Picks players from the given playerlist and picks
	 * num amount.
	 * 
	 * @param playerList ArrayList of players
	 * @param num Amount of players to pick
	 */
	public void pickPlayers(ArrayList<Player> playerList, int num) {
		System.out.println("Picking players for " + name);
		Collections.shuffle(playerList);
		if(num > maxPlayers) num = maxPlayers;
		players = new ArrayList<Player>(playerList.subList(0, num));
		for(Player p: players) {
			System.out.println(p.getName());
		}
	}
	
	/**
	 * Get the name of the game
	 * @return the name of the game
	 */
	public String toString() {
		return name;
	}
	
}
