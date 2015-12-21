package Players.TXW65292;

import java.util.ArrayList;
import java.util.Stack;

import Interface.Coordinate;
import Interface.PlayerMove;

/**
 * A static/(utility)! Class to test the validity of a PlayerMove object
 * based on the given state of the Game. Does not verify
 * 'broken' moves, (where broken means wrong player id or
 * there isnt even a piece there to start).
 * @author TJohnW
 *
 */
public class Validation {
	
	public static ArrayList<PlayerMove> generateValidMoves(int player_id, Game game_instance) {
		
		ArrayList<PlayerMove> validMoves = new ArrayList<PlayerMove>();
		
		// Its okay to use references here, I promise I wont change anything.
		Stack<Piece>[][] board = game_instance.getBoardReference();
		Stack<Piece>[][] players = game_instance.getPlayersReference();
		
		// Moves from our stacks
		for(int s = 0; s < players[0].length; s++) {
			if(players[player_id-1][s].size() > 0) {
				for(int i = 0; i < board.length; i++) {
					for(int j = 0; j < board.length; j++) {
						PlayerMove mv = new PlayerMove(player_id, s+1, players[player_id-1][s].peek().piece_size,
							new Coordinate(-1, -1), new Coordinate(i, j));
						
						if(isValid(player_id, game_instance, mv))
							validMoves.add(mv);
					}
				}
			}
		}
		
		// moves from pieces already on the board.
		for(int i = 0; i < board.length; i++) {
			for(int j = 0; j < board.length; j++) {
				if(board[i][j].size() > 0 && board[i][j].peek().player_id == player_id) {
					// now lets try validating moves
					for(int ii = 0; ii < board.length; ii++) {
						for(int jj = 0; jj < board.length; jj++) {
							PlayerMove move = new PlayerMove(player_id, 0, board[i][j].peek().piece_size,
								new Coordinate(i, j), new Coordinate(ii, jj));
							
							if(isValid(player_id, game_instance, move))
								validMoves.add(move);
						}
					}
				}
			}
		}
		return validMoves;
	}
	
	private static boolean isValid(int player_id, Game game_instance, PlayerMove move) {
		// Its okay to use references here, I promise I wont change anything.
		Stack<Piece>[][] board = game_instance.getBoardReference();
		// loop through board and find our pieces,
		// loop through all spaces on board and make move to test
		if(board[move.getEndRow()][move.getEndCol()].size() > 0) {
			// there is a piece here, lets handle this accordingly.
			// if WE are placing a piece from our stack, we need to make sure
			// that it is 'gobble'able.
			if(move.getStack() != 0)
				return canGobble(player_id, game_instance, move);
			else if(board[move.getEndRow()][move.getEndCol()].peek().piece_size < move.getSize())
				return true;
				
			return false;
			
		}
		// return true bc no piece there to block it or
		// potentially "gobble" up, woohoo! the easy case.
		return true;
	}

	private static boolean canGobble(int player_id, Game game_instance, PlayerMove mv) {
		int enemy_id = 1;
		if(player_id == 1) enemy_id = 2;
		
		if(mv.getSize() <= game_instance.getBoardReference()[mv.getEndRow()][mv.getEndCol()].peek().piece_size)
			return false;
		
		if(countInRow(enemy_id, game_instance, mv.getEndRow()) == 3)
			return true;
		
		if(countInCol(enemy_id, game_instance, mv.getEndCol()) == 3)
			return true;
		
		if(mv.getEndCol() == mv.getEndRow() && countInDiag(enemy_id, game_instance, true) == 3)
			return true;
		else if (mv.getEndCol() + mv.getEndRow() == 3 && countInDiag(enemy_id, game_instance, false) == 3)
			return true;
		
		return false;
	}
	
	public static int countInRow(int player_id, Game game_instance, int row_number) {
		
		Stack<Piece>[][] board = game_instance.getBoardReference();
		
		int count = 0;
		
		for(Stack<Piece> s: board[row_number])
			if(s.size() > 0 && s.peek().player_id == player_id)
				count++;
		
		return count;
	}
	
	/**
	 * Counts number of player not player_id in the column that matches
	 * the end col of move
	 *
	 * @param player_id
	 * @param game_instance
	 * @param move
	 * @return
	 */
	public static int countInCol(int player_id, Game game_instance, int col_number) {
		
		Stack<Piece>[][] board = game_instance.getBoardReference();
		
		int count = 0;
		Stack<Piece>[] col = new Stack[4];
		for(int i = 0; i < 4; i++)
			col[i] = board[i][col_number];
		
		for(Stack<Piece> s: col)
			if(s.size() > 0 && s.peek().player_id == player_id)
				count++;
		
		return count;
	}
	
	/**
	 * Counts number of player not player_id in the diagonal that matches
	 * the end position of move.
	 * 
	 * @param player_id
	 * @param game_instance
	 * @param move
	 * @param leftToRight
	 * @return
	 */
	public static int countInDiag(int player_id, Game game_instance, boolean leftToRight) {
		
		Stack<Piece>[][] board = game_instance.getBoardReference();
		
		int colVariation = 1, r = 0, c = 0, count = 0; // the difference variable
		
		if(!leftToRight) {
			colVariation = -1;
			c = 3;
		}
		
		for(int i = 0; i < 4; i++, c += colVariation)
			if(board[i][c].size() > 0 &&
				board[i][c].peek().player_id == player_id)
					count++;
		
		return count;
	}
	
}
