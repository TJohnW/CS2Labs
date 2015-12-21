/*
 * GameNight.java
 *
 * Version: 
 *	$Id: GameNight.java,v 1.1 2014/02/26 03:15:39 txw6529 Exp $
 *
 * Revisions:
 *	$Log: GameNight.java,v $
 *	Revision 1.1  2014/02/26 03:15:39  txw6529
 *	Initial commit
 *
 */

import java.util.ArrayList;
import java.util.Arrays;

/**
 * GameNight, runs the game!
 * @author Tristan Whitcher
 */
public class GameNight {
	
	private static ArrayList<Player> players;
	private static ArrayList<Game> games;
	
	private static String[] names =  {
		"Helix Fossil", "Dome Fossil", "S.S. Ticket", "Bird Jesus",
		"Jay Leno", "Abby", "False Prophet", "C3KO", "X-Wing",
		"Red", "TPP", "Cabbage", "DigRat", "Slayer of Trees",
		"The Keeper", "Rick Gastly"
	};
	
	public static void main(String[] args) {
		players = generatePlayers(8, 12);
		games = generateGames();
		init();
		stats();
	}
	
	/**
	 * Outputs the stats of the players and the big winner
	 */
	private static void stats() {
		Player bigWinner = players.get(0);
		int wins = bigWinner.getWins();
		for(Player p : players) {
			if(p.getWins() > wins) {
				bigWinner = p;
				wins = p.getWins();
			}
			System.out.println(p);
		}
		System.out.println("The big winner is: " + bigWinner.getName());
	}
	
	/**
	 * Initializes the games
	 */
	private static void init() {
		for(Game g: games) g.pickPlayers(players);
		for(Game g: games) g.play();
	}
	
	/**
	 * Generates the list of games to play
	 * @return an ArrayList of games to be played during the gamenight
	 */
	private static ArrayList<Game> generateGames() {
		ArrayList<Game> games = new ArrayList<Game>();
		games.add(new BoardGame("Monopoly", 6, 0));
		games.add(new ConsoleGame("MarioKart", 4, false));
		games.add(new TeamCardGame("Bridge"));
		games.add(new GermanBoardGame("SomeGermanGame", 4));
		games.add(new ConsoleGame("Portal 2: (time trial)", 4, true));
		return games;
	}
	
	/**
	 * Returns an ArrayList of Player's to be in the GameNight.
	 * Intel varies based on age slightly, one under 10 has possible
	 * intel of 1-6, while an adult has possible intel 3-10.
	 * Dexterity is higher in kids than adults.
	 * 
	 * @param min the minimum number of players to be generated
	 * @param max the maximum number of players to be generated
	 * @return an ArrayList of players for the gamenight
	 */
	private static ArrayList<Player> generatePlayers(int min, int max) {
		ArrayList<Player> playerList = new ArrayList<Player>();
		int kidCount = 0, adultCount = 0;
		
		for(int i = 0; i < randomWithRange(min, max); i++) {
			ArrayList<String> pNames = new ArrayList<String>(Arrays.asList(names));
			String name = pNames.remove(randomWithRange(0, pNames.size() - 1)); // No two names the same
			
			if(kidCount < 4) {
				playerList.add(playerFromAge(name, randomWithRange(5, 9)));
				kidCount++;
			} else if(adultCount < 4) {
				playerList.add(playerFromAge(name, randomWithRange(10, 90)));
				adultCount++;
			} else {
				playerList.add(playerFromAge(name, randomWithRange(5, 90)));
			}
			
		}
		return playerList;
	}
	
	/**
	 * Returns a player customized based on the age.
	 * 
	 * @param name the name of the player
	 * @param age the age of the player
	 * @return a player tailored to the parameters.
	 */
	private static Player playerFromAge(String name, int age) {
		int intel, dexter, luck;
		if(age < 10) {
			intel = randomWithRange(1, 6);
			dexter = randomWithRange(5, 10);
			luck = randomWithRange(0, 10);
			return new Player(name, intel, dexter, luck, age);
		} else {
			intel = randomWithRange(3, 10);
			dexter = randomWithRange(3, 10);
			luck = randomWithRange(0, 10);
			return new Player(name, intel, dexter, luck, age);
		}
	}
	
	/**
	 * Utility function for random number generation.
	 * 
	 * @param min inclusive minimum for the random number
	 * @param max inclusive max for the random number
	 * @return number between min and max inclusive.
	 */
	private static int randomWithRange(int min, int max) { 
		return (int)(Math.random() * ((max - min) + 1)) + min;
	}
	
}
