/*
 * BoardGame.java
 *
 * Version: 
 *	$Id: BoardGame.java,v 1.1 2014/02/26 03:15:39 txw6529 Exp $
 *
 * Revisions:
 *	$Log: BoardGame.java,v $
 *	Revision 1.1  2014/02/26 03:15:39  txw6529
 *	Initial commit
 *
 */

/**
 * Class that represent a board game.  In addition to the features
 * of a regular game, it includes a variable for the luck factor,
 * which is a real value between 0 and 1.
 * @author zjb
 */
public class BoardGame extends Game {

    /**
     * Luck factor of the game
     */
    private double luckFactor;

    /**
     * Constructor, takes three args to set the instance variables
     * @param n Name of the game
     * @param np Number of players that can play the game at once
     * @param l Luck factor
     */
    public BoardGame(String n, int np, double l) {
        super(n,np);
        luckFactor = l;
    }

    /**
     * Plays the game and chooses a winner.  Winner is chosen to be
     * the person with the largest value of 
     * intelligence + (luck * luckFactor), where luck refer's to the
     * Player's luckiness and luckFactor refers to the Game's instance
     * variable.  This method returns nothing but does call youWin()
     * on the Player that won the game.
     */
    public void play() {
        System.out.println("Playing " + name + "...");
        double bestval = 0;
        Player winner = null;
        // find player with max intelligence + luck*luckFactor
        for (Player player : players) {
            double thisval = player.getIntelligence() + 
                player.getLuck() * luckFactor;
            if (thisval > bestval) {
                bestval = thisval;
                winner = player;
            }
        }
        System.out.println("Winner is " + winner.getName());
        winner.youWin();
    }
}
