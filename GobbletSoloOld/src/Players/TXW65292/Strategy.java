package Players.TXW65292;

import java.util.List;
import java.util.Stack;

import Interface.PlayerMove;

/**
 * A class with static methods for determining the worthiness and/or
 * usefulness of a current board state. Basis this on the player_id specified.
 * 
 * @author TJohnW
 */
public class Strategy {
	
	/**
	 * Tests if a winning move is available next turn for player_id
	 * in the given state
	 * 
	 * @param player_id
	 * @param game_instance
	 * @return
	 */
	public static boolean hasWinningMove(int player_id, Game game_instance) {
		List<PlayerMove> moves = Validation.generateValidMoves(player_id, game_instance);
		for(PlayerMove mv: moves) {
			Game cp = new Game(game_instance);
			cp.update(mv);
			if(hasWonState(player_id, cp))
				return true;
		}
		return false;
	}
	
	/**
	 * Tests if player_id has winning state in the passed game instance
	 * 
	 * @param player_id
	 * @param game_instance
	 * @return
	 */
	public static boolean hasWonState(int player_id, Game game_instance) {
		for(int i = 0; i < game_instance.getBoardReference().length; i++) {
			if(Validation.countInRow(player_id, game_instance, i) == 4
					|| Validation.countInCol(player_id, game_instance, i) == 4
					|| Validation.countInDiag(player_id, game_instance, true) == 4
					|| Validation.countInDiag(player_id, game_instance, false) == 4) {
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Calculates the worth of a board regarding directional evaluation.
	 * Does not take winning and losing board states into account. Logic
	 * should flow as follows:
	 * 	-> Check for valid winning moves,
	 *  -> do those moves create a loss?
	 *     -> if not, play move,
	 *  -> check to make sure next move doesnt allow enemy
	 *     to have a valid move.
	 *     
	 *  -> out of those states ^ use the one with the highest (worth).
	 *  
	 * @param player_id
	 * @param board
	 * @return The boards worth.
	 */
	public static int worth(int player_id, Game game_instance) {
		int enemy_id = 1;
		
		if(player_id == 1)
			enemy_id = 2;
		
		int elo = 0;
		
		for(int i = 0; i < game_instance.getBoardReference().length; i++) {
			elo += rowWorth(player_id, game_instance);
			elo += colWorth(player_id, game_instance);			
		}
		
		elo += diagWorth(player_id, game_instance, false);
		elo += diagWorth(player_id, game_instance, true);
		
		return elo;

	}
	
	/**
	 * Calculates the worth of the rows
	 * 
	 * @param player_id
	 * @param game_instance
	 * @return
	 */
	private static int rowWorth(int player_id, Game game_instance) {
		int sum = 0;
		for(Stack<Piece>[] row: game_instance.getBoardReference()) {
			sum += directionalWorth(player_id, row);
		}
		return sum;
	}

	/**
	 * Calculates the worth of the columns
	 * 
	 * @param player_id
	 * @param game_instance
	 * @return
	 */
	private static int colWorth(int player_id, Game game_instance) {
		Game cols = new Game(game_instance);
		for(int i = 0; i < 4; i++)
			for(int j = 0; j < 4; j++)
				cols.getBoardReference()[i][j] = game_instance.getBoardReference()[j][i];
		
		return rowWorth(player_id, cols);
	}
	
	/**
	 * Calculates the dimensional worth of an array of Stacks (board positions)
	 * 
	 * @param player_id
	 * @param line
	 * @return
	 */
	private static int directionalWorth(int player_id, Stack<Piece>[] line) {
		int sum = 0;
		for(Stack<Piece> s: line) {
			if(s.size() > 0) {
				if(s.peek().player_id != player_id) {
					sum -= s.peek().piece_size;
				} else {
					sum += s.peek().piece_size;
				}
			}
		}
		return sum;
	}
	
	/**
	 * Calculates diagonal worthiness.
	 * 
	 * @param player_id
	 * @param game_instance
	 * @param leftToRight
	 * @return
	 */
	private static int diagWorth(int player_id, Game game_instance, boolean leftToRight) {
		
		int colVariation = 1, r = 0, c = 0, sum = 0; // the difference variable
		
		if(!leftToRight) {
			colVariation = -1;
			c = 3;
		}
		
		for(int i = 0; i < 4; i++, c += colVariation) {
			if(game_instance.getBoardReference()[i][c].size() > 0) {
				if(game_instance.getBoardReference()[i][c].peek().player_id != player_id) {
					sum -= game_instance.getBoardReference()[i][c].peek().piece_size;
				} else {
					sum += game_instance.getBoardReference()[i][c].peek().piece_size;
				}
			}
		}
		return sum;
	}

}
