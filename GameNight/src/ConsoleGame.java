/*
 * ConsoleGame.java
 *
 * Version: 
 *	$Id: ConsoleGame.java,v 1.1 2014/02/26 03:15:39 txw6529 Exp $
 *
 * Revisions:
 *	$Log: ConsoleGame.java,v $
 *	Revision 1.1  2014/02/26 03:15:39  txw6529
 *	Initial commit
 *
 */

/**
 * Class that represent a console game.
 * @author Tristan Whitcher
 */
public class ConsoleGame extends Game {

	private boolean usesBrains;
	
    /**
     * Constructor, takes a name for the game
     * 
     * @param n Name of the game
     * @param np Number of players for the game
     * @param usesBrains does this game use brains?
     */
    public ConsoleGame(String n, int np, boolean usesBrains) {
        super(n, np);
        this.usesBrains = usesBrains;
    }

    /**
     * Plays the game and chooses a winning team based on
     * dexterity and intelligence if the game uses brains
     */
    public void play() {
        System.out.println("Playing " + name + "...");
        int highest = 0;
        Player winner = players.get(0);
        for(Player p: players) {
        	int score = p.getDexterity();
        	if(usesBrains) score += p.getIntelligence();
        	if(score > highest) {
        		highest = score;
        		winner = p;
        	}
        }
        System.out.println("Winner is " + winner.getName());
        winner.youWin();
    }
}
