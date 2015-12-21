/*
 * TeamCardGame.java
 *
 * Version: 
 *	$Id: TeamCardGame.java,v 1.1 2014/02/26 03:15:40 txw6529 Exp $
 *
 * Revisions:
 *	$Log: TeamCardGame.java,v $
 *	Revision 1.1  2014/02/26 03:15:40  txw6529
 *	Initial commit
 *
 */

/**
 * Class that represent a Team Cardgame.
 * @author Tristan Whitcher
 */
public class TeamCardGame extends Game {

    /**
     * Constructor, takes a name for the game
     * @param n Name of the game
     */
    public TeamCardGame(String n) {
        super(n, 4);
    }

    /**
     * Plays the game and chooses a winning team based on intelligence of the teams
     */
    public void play() {
        System.out.println("Playing " + name + "...");
        
        Player t1p1 = players.get(0), t1p2 = players.get(2),
        		t2p1 = players.get(1), t2p2 = players.get(3);
        
        int team1 = Math.max(t1p1.getIntelligence(), t1p2.getIntelligence())
        		  + (2 * Math.min(t1p1.getIntelligence(), t1p2.getIntelligence()));
        
        int team2 = Math.max(t2p1.getIntelligence(), t2p2.getIntelligence())
      		  	  + (2 * Math.min(t2p1.getIntelligence(), t2p2.getIntelligence()));
        
        if(team1 > team2) {
        	System.out.println("Winning team is " + t1p1.getName() + " and " + t1p2.getName());
        	t1p1.youWin();
        	t1p2.youWin();
        } else if(team1 < team2) {
        	System.out.println("Winning team is " + t2p1.getName() + " and " + t2p2.getName());
        	t2p1.youWin();
        	t2p2.youWin();
        } else {
        	System.out.println("It was a tie! No winners!");
        }
    }
}
