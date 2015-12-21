
/*
 * $Id: Game.java,v 1.2 2014/04/30 01:05:24 txw6529 Exp $
 * 
 * $Log: Game.java,v $
 * Revision 1.2  2014/04/30 01:05:24  txw6529
 * Finished
 *
 */

package Players.TXW6529;

import java.util.Stack;

import utilities.Cloner;
import Interface.PlayerMove;

public class Game {

	private Stack<Piece>[][] board = new Stack[4][4];
	
	private Stack<Piece>[][] players = new Stack[2][3];
	
	public Game() {
		
		for(int i = 0; i < board.length; i++)
			for(int j = 0; j < board.length; j++)
				board[i][j] = new Stack<Piece>();
		
		for(int i = 0; i < players[0].length; i++) {
			players[0][i] = new Stack<Piece>();
			players[1][i] = new Stack<Piece>();
			for(int j = 1; j <= 4; j++) {
				players[0][i].push(new Piece(1, j));
				players[1][i].push(new Piece(2, j));
			}
		}
	}
	
	/**
	 * Constructor to copy a game into a new instance using
	 * the Cloner class. Used to get a copy of the entire
	 * game state, useful when predicting past one move,
	 * to keep the players stacks up to date.
	 * 
	 * @param game
	 */
	public Game(Game game) {
		this.board = game.getBoard();
		this.players = game.getPlayers();
	}
	
	public void update(PlayerMove move) {
		int r1 = move.getStartRow(), c1 = move.getStartCol();
		int r2 = move.getEndRow(), c2 = move.getEndCol();
		
		if(move.getStack() > 0)
			board[r2][c2].push(players[move.getPlayerId() - 1][move.getStack()-1].pop());
		else
			board[r2][c2].push(board[r1][c1].pop());
	}
	
	/**
	 * Gets a COPY of the board state.
	 * If you need a reference, refer to getBoardReference();
	 * This should only be used by the Validator.
	 * @return A copy of the current board.
	 */
	public Stack<Piece>[][] getBoard() {
		return (Stack<Piece>[][]) Cloner.deepCopy(board);
	}
	
	/**
	 * Careful, this can change the state of the games AI.
	 * @return
	 */
	public Stack<Piece>[][] getBoardReference() {
		return this.board;
	}
	
	/**
	 * Gets a copy of the player stacks
	 * 
	 * @param player_id the player you want
	 * @return their stacks
	 */
	public Stack<Piece>[][] getPlayers() {
		return (Stack<Piece>[][]) Cloner.deepCopy(this.players);
	}
	
	/**
	 * Gets a reference to the player stacks
	 * 
	 * @param player_id the player you want
	 * @return their stacks
	 */
	public Stack<Piece>[][] getPlayersReference() {
		return this.players;
	}
	
	public String toString() {
		String out = "";
		for(int i = 0; i < board.length; i++) {
			for(int j = 0; j < board.length; j++) {
				if(board[i][j].size() == 0) {
					out += (" []   ");
				} else {
					Piece topPiece = board[i][j].peek();
					out += (topPiece.piece_size + "(" + topPiece.player_id +")  ");
				}
			}
			
			if(i == 0) {
				out += (" " + stackString(players[0]));
			} else if(i == 2) {
				out += (" " + stackString(players[1]));
			}
			out += "\n";
		}
		return out;
	}
	
	/**
	 * Creates a string to represent a players hand/stacks
	 * @param stacks the player stacks
	 * @return a string of the stacks top numbers or _
	 */
	private String stackString(Stack<Piece>[] stacks) {
		String str = "";
		for(int i = 0; i < stacks.length; i++) {
			if(stacks[i].size() != 0)
				str += stacks[i].peek().piece_size + " ";
			else
				str += "_ ";
		}
		return str;
	}
}
