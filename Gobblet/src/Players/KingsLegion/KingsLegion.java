/**
 * KingsLegion.java
 * 
 * $Id: KingsLegion.java,v 1.4 2014/04/15 20:11:17 p142-06b Exp $
 * 
 * $Log: KingsLegion.java,v $
 * Revision 1.4  2014/04/15 20:11:17  p142-06b
 * Added Documentation
 *
 * Revision 1.3  2014/04/14 16:42:43  p142-06b
 * Added generateValidMoves functionality. Can play a game to completion.
 *
 * Revision 1.2  2014/04/10 21:43:12  p142-06b
 * added canGobble method
 *
 * Revision 1.1  2014/04/03 19:57:57  p142-06b
 * stuff
 *
 * Revision 1.1  2014/03/21 16:19:09  p142-06b
 * Initial Commit
 *
 * Revision 1.2  2014/03/12 00:01:07  txw6529
 * Updated
 *
 * Revision 1.1  2014/03/09 00:02:34  txw6529
 * Gobblet Part 1 initial commit
 *
 */
package Players.KingsLegion;

import java.util.ArrayList;
import java.util.Stack;

import Engine.Logger;
import Interface.Coordinate;
import Interface.GobbletPart1;
import Interface.PlayerModule;
import Interface.PlayerMove;

/**
 * @author TJohnW
 *
 */
public class KingsLegion implements PlayerModule, GobbletPart1 {

	/**
	 * Used to store game pieces in our stacks and on the board.
	 */
	private static class GamePiece {
		
		private int player_id, piece_size;
		
		/**
		 * Constructs a new GamePiece
		 * 
		 * @param id the playerid of the piece
		 * @param size the size of the piece
		 */
		private GamePiece(int id, int size) {
			this.player_id = id;
			this.piece_size = size;
		}
		
		@Override
		public boolean equals(Object other){
		    if (other == null) return false;
		    if (other == this) return true;
		    if (!(other instanceof GamePiece)) return false;
		    GamePiece p = (GamePiece) other;
		    if(p.player_id != this.player_id || p.piece_size != this.piece_size)
		    	return false;
		    return true;
		}
	}
	
	private int player_id, enemy_id;
	
	private Logger out;
	
	private Stack<GamePiece>[][] board = new Stack[4][4];
	
	private Stack<GamePiece>[] player_stacks = new Stack[3];
	private Stack<GamePiece>[] enemy_stacks  = new Stack[3];
	
	/* (non-Javadoc)
	 * @see Interface.PlayerModule#init(Engine.Logger, int)
	 */
	public void init(Logger logger, int playerId) {
		
		this.out = logger;
		this.player_id = playerId;
		
		if(this.player_id == 1) {
			this.enemy_id = 2;
		} else {
			this.enemy_id = 1;
		}
		
		for(int i = 0; i < board.length; i++)
			for(int j = 0; j < board.length; j++)
				board[i][j] = new Stack<GamePiece>();
		
		for(int i = 0; i < player_stacks.length; i++) {
			player_stacks[i] = new Stack<GamePiece>();
			enemy_stacks[i] = new Stack<GamePiece>();
			for(int j = 1; j <= 4; j++) {
				player_stacks[i].push(new GamePiece(this.player_id, j));
				enemy_stacks[i].push(new GamePiece(this.enemy_id, j));
			}
		}
		
	}
	
	/* (non-Javadoc)
	 * @see Interface.GobbletPart1#getID()
	 */
	public int getID() { return player_id; }
	
	/* (non-Javadoc)
	 * @see Interface.PlayerModule#lastMove(Interface.PlayerMove)
	 */
	public void lastMove(PlayerMove mv) {
		int r1 = mv.getStartRow(), c1 = mv.getStartCol();
		int r2 = mv.getEndRow(), c2 = mv.getEndCol();
		GamePiece piece;
		
		if(mv.getStack() > 0) {
			if(mv.getPlayerId() != player_id)
				board[r2][c2].push(enemy_stacks[mv.getStack()-1].pop());
			else 
				board[r2][c2].push(player_stacks[mv.getStack()-1].pop());
		} else
			board[r2][c2].push(board[r1][c1].pop());
	}
	
	/* (non-Javadoc)
	 * @see Interface.GobbletPart1#dumpGameState()
	 */
	public void dumpGameState() {
		for(int i = 0; i < board.length; i++) {
			for(int j = 0; j < board.length; j++) {
				if(board[i][j].size() == 0) {
					System.out.print(" []   ");
				} else {
					GamePiece topPiece = board[i][j].peek();
					System.out.print(topPiece.piece_size + "(" + topPiece.player_id +")  ");
				}
			}
		
			if(i == 0) {
				if(player_id == 1) {
					System.out.print(" " + stackString(player_stacks));
				} else {
					System.out.print(" " + stackString(enemy_stacks));
				}
			} else if(i == 2) {
				if(player_id == 2) {
					System.out.print(" " + stackString(player_stacks));
				} else {
					System.out.print(" " + stackString(enemy_stacks));
				}
			}
			System.out.println();
		}
	}

	/* (non-Javadoc)
	 * @see Interface.GobbletPart1#getTopOwnerOnBoard(int, int)
	 */
	public int getTopOwnerOnBoard(int row, int col) {
		if(board[row][col].size() == 0) {
			return -1;
		} else {
			return board[row][col].peek().player_id;
		}
	}

	/* (non-Javadoc)
	 * @see Interface.GobbletPart1#getTopSizeOnBoard(int, int)
	 */
	public int getTopSizeOnBoard(int row, int col) {
		if(board[row][col].size() == 0) {
			return -1;
		} else {
			return board[row][col].peek().piece_size;
		}
	}

	/* (non-Javadoc)
	 * @see Interface.GobbletPart1#getTopSizeOnStack(int, int)
	 */
	public int getTopSizeOnStack(int playerId, int stackNum) {
		if(playerId != player_id && enemy_stacks[stackNum-1].size() > 0) {
			return enemy_stacks[stackNum - 1].peek().piece_size;
		} else if(playerId == player_id && player_stacks[stackNum-1].size() > 0) {
			return player_stacks[stackNum - 1].peek().piece_size;
		} else {
			return -1;
		}
	}

	/* (non-Javadoc)
	 * @see Interface.PlayerModule#move()
	 */
	public PlayerMove move() {
		ArrayList<PlayerMove> moves = this.generateValidMoves(this.player_id);
		System.out.println("\n\nVALID MOVE COUNT: " + moves.size() + "\n\n"); 
		return moves.get(randomWithRange(0, moves.size() - 1));
	}
	
	/**
	 * Utility for random
	 * 
	 * @param min min number inclusive
	 * @param max max number inclusive
	 * @return random number between max and min
	 */
	private int randomWithRange(int min, int max)
	{
	   int range = (max - min) + 1;     
	   return (int)(Math.random() * range) + min;
	}

	/* (non-Javadoc)
	 * @see Interface.PlayerModule#playerInvalidated(int)
	 */
	public void playerInvalidated(int playerId) {
		//throw new UnsupportedOperationException();
	}
	
	/**
	 * Creates a string to represent a players hand/stacks
	 * @param stacks the player stacks
	 * @return a string of the stacks top numbers or _
	 */
	private String stackString(Stack<GamePiece>[] stacks) {
		String str = "";
		for(int i = 0; i < stacks.length; i++) {
			if(stacks[i].size() != 0)
				str += stacks[i].peek().piece_size + " ";
			else
				str += "_ ";
		}
		return str;
	}
	
	
	/**
	 * Generates all valid moves for a player
	 * 
	 * @param playerID the player
	 * @return the valid moves
	 */
	private ArrayList<PlayerMove> generateValidMoves(int playerID) {
		ArrayList<PlayerMove> validMoves = new ArrayList<PlayerMove>();
		//moves from our stacks
		Stack<GamePiece>[] stacks;
		
		if(playerID == this.player_id)
			stacks = this.player_stacks;
		else
			stacks = this.enemy_stacks;
		
		for(int s = 0; s < stacks.length; s++) {
			if(stacks[s].size() > 0) {
				for(int i = 0; i < board.length; i++) {
					for(int j = 0; j < board.length; j++) {
						PlayerMove mv = new PlayerMove(playerID, s+1, stacks[s].peek().piece_size,
							new Coordinate(-1, -1), new Coordinate(i, j));
						
						if(isValid(mv))
							validMoves.add(mv);
					}
				}
			}
		}
		
		//moves from pieces already on the board.
		for(int i = 0; i < board.length; i++) {
			for(int j = 0; j < board.length; j++) {
				if(board[i][j].size() > 0 && board[i][j].peek().player_id == playerID) {
					// now lets try validating moves
					for(int ii = 0; ii < board.length; ii++) {
						for(int jj = 0; jj < board.length; jj++) {
							PlayerMove mv = new PlayerMove(playerID, 0, board[i][j].peek().piece_size,
								new Coordinate(i, j), new Coordinate(ii, jj));
							
							if(isValid(mv))
								validMoves.add(mv);
						}
					}
				} //end if
			}
		}
		return validMoves;
	}
	
	/**
	 * Tests whether a playmove is valid.
	 * 
	 * @param m The move
	 * @return true/false
	 */
	private boolean isValid(PlayerMove m) {
		// loop through board and find our pieces,
		// loop through all spaces on board and make move to test
		if(board[m.getEndRow()][m.getEndCol()].size() > 0) {
			// there is a piece here, lets handle this accordingly.
			// if WE are placing a piece from our stack, we need to make sure
			// that it is 'gobble'able.
			if(m.getStack() != 0)
				return canGobble(m);
			else if(board[m.getEndRow()][m.getEndCol()].peek().piece_size < m.getSize())
				return true;
				
			return false;
			
		}
		// return true bc no piece there to block it or
		// potentially "gobble" up, woohoo! the easy case.
		return true;
	}
	
	/**
	 * Is the playermove valid to "gobble" another piece
	 * 
	 * @param mv The move
	 * @return true/false
	 */
	private boolean canGobble(PlayerMove mv) {
		if(mv.getSize() <= board[mv.getEndRow()][mv.getEndCol()].peek().piece_size)
			return false;
		
		if(countEnemyInRow(mv) == 3)
			return true;
		
		if(countEnemyInCol(mv) == 3)
			return true;
		
		if(mv.getEndCol() == mv.getEndRow() && countEnemyInDiag(mv, true) == 3)
			return true;
		else if (mv.getEndCol() + mv.getEndRow() == 3 && countEnemyInDiag(mv, false) == 3)
			return true;
		
		return false;
	}
	
	/**
	 * Counts the enemy pieces in the row section of mv
	 * 
	 * @param mv The player move
	 * @return the number of enemy pieces
	 */
	private int countEnemyInRow(PlayerMove mv) {
		int count = 0;
		Stack<GamePiece>[] row = board[mv.getEndRow()];
		for(Stack<GamePiece> s: row)
			if(s.size() > 0 && s.peek().player_id != this.player_id)
				count++;
		
		return count;
	}
	
	/**
	 * Counts the number of pieces that are not ours,
	 * in the column section of the end position of mv
	 * 
	 * @param mv the player move
	 * @return the number of enemy pieces
	 */
	private int countEnemyInCol(PlayerMove mv) {
		int count = 0;
		Stack<GamePiece>[] col = new Stack[4];
		for(int i = 0; i < 4; i++)
			col[i] = board[i][mv.getEndCol()];
		
		for(Stack<GamePiece> s: col)
			if(s.size() > 0 && s.peek().player_id != this.player_id)
				count++;
		return count;
	}
	
	/**
	 * Counts the number of pieces that are not ours,
	 * in the diagonal sections.
	 * 
	 * @param mv the player move
	 * @param leftToRight which diagonal to count, top to bottom
	 * @return the number of enemy pieces
	 */
	private int countEnemyInDiag(PlayerMove mv, boolean leftToRight) {
		
		int colVariation = 1, r = 0, c = 0, count = 0; // the difference variable
		
		if(!leftToRight) {
			colVariation = -1;
			c = 3;
		}
		
		for(int i = 0; i < 4; i++) {
			if(board[r][c].size() > 0 &&
				board[r][c].peek().player_id != this.player_id)
				count++;
			
			r++; c += colVariation;
		}
		
		return count;
	}
	

}
