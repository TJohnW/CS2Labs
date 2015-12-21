/*
 * $Id: TXW6529.java,v 1.2 2014/04/30 01:05:24 txw6529 Exp $
 * 
 * $Log: TXW6529.java,v $
 * Revision 1.2  2014/04/30 01:05:24  txw6529
 * Finished
 *
 */

package Players.TXW6529;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;

import utilities.Cloner;
import Engine.Logger;
import Interface.GobbletPart1;
import Interface.PlayerModule;
import Interface.PlayerMove;

/**
 * @author TJohnW
 *
 */
public class Milky implements PlayerModule, GobbletPart1 {
	
	private int player_id;
	private int enemy_id;
	
	private Logger out;
	
	private Game game;
	
	/**
	 * Initializes the game state
	 */
	public void init(Logger logger, int playerId) {
		
		Cloner.addImmutable(Piece.class);
		
		this.out = logger;
		
		this.player_id = playerId;
		if(this.player_id == 1) this.enemy_id = 2;
		else this.enemy_id = 1;
		
		this.game = new Game();
	}
	
	/**
	 * Gets the ID
	 */
	public int getID() { 
		return player_id; 
	}
	
	public int getEnemyID() {
		return enemy_id;
	}
	
	/**
	 * Updates the game with the last move played.
	 * 
	 * Called from the engine overlords.
	 */
	public void lastMove(PlayerMove mv) {
		game.update(mv);
	}

	/**
	 * Generates the move to make.
	 * Chaning this to be strategic soon.
	 */
	public PlayerMove move() {
		int enemy_id = 1;
		if(this.player_id == 1)
			enemy_id = 2;
		
		List<PlayerMove> moves = Validation.generateValidMoves(player_id, game);
		
		HashMap<Integer, ArrayList<AdvancedMove>> priorities = new HashMap<Integer, ArrayList<AdvancedMove>>();
		for(int i = 0; i <= 7; i++)
			priorities.put(i, new ArrayList<AdvancedMove>());
		
		for(PlayerMove mv: moves) {
			
			/* Simulation */
			Game cp = new Game(this.game);
			cp.update(mv);
			
			/* Calculations */
			int worth = Strategy.worth(player_id, cp);
			
			boolean enemy_won = Strategy.hasWonState(enemy_id, cp);
			boolean enemy_winning_move = Strategy.hasWinningMove(enemy_id, cp);
			boolean enemy_winning_winning_move = Strategy.hasWinningWinningMove(enemy_id, cp);
			
			boolean player_won = Strategy.hasWonState(player_id, cp);
			boolean player_winning_move = Strategy.hasWinningMove(player_id, cp);
			boolean player_winning_winning_move = Strategy.hasWinningWinningMove(player_id, cp);
			
			/*
			first priority is:
				> enemy hasn't won and the player has won
			second priority is:
				> enemy hasn't won and the player has a winning move and the enemy doesn't have a winning winning move
			third priority is:
				> enemy hasn't won and the player has a winning winning move and the enemy doesn't have a winning winning move
			fourth priority is:
				> the enemy hasn't won and the enemy doesn't have a winning winning move
			fifth priority is:
				> enemy hasn't won and the enemy doesn't have a winning move but the player has a winning move
			sixth priority is:
				> enemy hasn't won and the enemy doesn't have a winning move
			seventh priority is:
				> enemy hasn't won
			eighth priority is:
				> give up
			*/


			//Priority 0
			if(!enemy_won && player_won) {
				priorities.get(0).add(new AdvancedMove(mv, worth));
			}
			
			//Priority 1
			else if(!enemy_won && player_winning_move && !enemy_winning_winning_move) {
				priorities.get(1).add(new AdvancedMove(mv, worth));
			}
			
			//Priority 2
			else if(!enemy_won && player_winning_winning_move && !enemy_winning_winning_move) {
				priorities.get(2).add(new AdvancedMove(mv, worth));
			}
			
			//Priority 3
			else if(!enemy_won && !enemy_winning_move && player_winning_move) {
				priorities.get(3).add(new AdvancedMove(mv, worth));
			}
			
			//Priority 4
			else if(!enemy_won && !enemy_winning_winning_move) {
				priorities.get(4).add(new AdvancedMove(mv, worth));
			}
			//Priority 5
			else if(!enemy_won && !enemy_winning_move) {
				priorities.get(5).add(new AdvancedMove(mv, worth));
			}
			
			//Priority 6
			else if(!enemy_won) {
				priorities.get(6).add(new AdvancedMove(mv, worth));
			}
			
			//Priority 7
			else {
				priorities.get(7).add(new AdvancedMove(mv, worth));
			}	
		}
		
		for(int i = 0; i <= 7; i++) {
			ArrayList<AdvancedMove> lst = priorities.get(i);
			if(lst.size() > 0) {
				Collections.sort(lst, new Comparator<AdvancedMove>() {

					@Override
					public int compare(AdvancedMove o1,
							AdvancedMove o2) {
						return o2.worth - o1.worth;
					}
					
				});
				System.out.println("Moving with Priority: " + i);
				System.out.println("Worth: " + lst.get(0).move);
				return lst.get(0).move;
			}
			
		}
		System.out.println("Something wrong..");
		return null;
	}

	/**
	 * I don't really understand this. If the player is invalidated,
	 * how vital is that to an AI that actually knows how to play?
	 */
	public void playerInvalidated(int player_id) {}

	/**
	 * Gets the top owner at row, col
	 */
	public int getTopOwnerOnBoard(int row, int col) {
		if(game.getBoardReference()[row][col].size() == 0) {
			return -1;
		} else {
			return game.getBoardReference()[row][col].peek().player_id;
		}
	}

	/**
	 * Gets the top size at row, col
	 */
	public int getTopSizeOnBoard(int row, int col) {
		if(game.getBoardReference()[row][col].size() == 0) {
			return -1;
		} else {
			return game.getBoardReference()[row][col].peek().piece_size;
		}
	}

	/**
	 * Gets the top size on the players stack
	 */
	public int getTopSizeOnStack(int player_id, int stack_number) {
		if(game.getPlayersReference()[player_id-1][stack_number-1].size() > 0) {
			return game.getPlayersReference()[player_id-1][stack_number-1].peek().piece_size;
		}
		return -1;
	}
	
	/**
	 * Outputs the current game state.
	 */
	public void dumpGameState() {
		System.out.println(game);
	}

}
