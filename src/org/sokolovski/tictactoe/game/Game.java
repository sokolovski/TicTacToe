package org.sokolovski.tictactoe.game;

public class Game
{
	public static final byte TOP = 0, MIDDLE = 1, BOTTOM = 2, LEFT = 0, RIGHT = 2;
	private static final byte NONE = 0, P1 = 1, P2 = 2;
	private static final byte RUNNING = 1, OVER = 2;

	private static final byte board[][] = {{NONE, NONE, NONE}, {NONE, NONE, NONE}, {NONE, NONE, NONE}};
	private static final AIEasy AI = new AIEasy();
	
	private static byte state = RUNNING, player = P1;

	public void marked(IGameListener listener, byte row, byte col)
	{
		if(state == RUNNING && board[row][col] == NONE)
		{
			board[row][col] = player;
			if(P1 == player)
				listener.markFieldPlayerOne(row, col);
			else
				listener.markFieldPlayerTwo(row, col);
			
			checkBoard(listener, row, col);
			switchPlayer();
			if(RUNNING == state)
			{
				AI.makeMove(this, listener);
				switchPlayer();
			}
		}
	}
	
	public void restart()
	{
		for(byte r = 0; r < board.length; ++r)
			for(byte c = 0; c < board[r].length; ++c)
				board[r][c] = NONE;
		state = RUNNING;
		player = P1;
	}
	
	boolean aiMove(IGameListener listener, byte row, byte col)
	{
		if(board[row][col] != NONE)
			return false;
		
		board[row][col] = P2;
		listener.markFieldPlayerTwo(row, col);
		checkBoard(listener, row, col);
		return true;
	}
	
	private void checkBoard(IGameListener listener, byte row, byte col)
	{
		switch(row)
		{
		case TOP:
			checkBoardTop(listener, col);
			break;
		case MIDDLE:
			checkBoardMiddle(listener, col);
			break;
		case BOTTOM:
			checkBoardBottom(listener, col);
		}
	}
	
	private void checkBoardTop(IGameListener listener, byte col)
	{
		switch(col)
		{
		case LEFT:
			checkTopLeft(listener);
			break;
		case MIDDLE:
			checkTopMiddle(listener);
			break;
		case RIGHT:
			checkTopRight(listener);
		}
	}
	
	private void checkBoardMiddle(IGameListener listener, byte col)
	{
		switch(col)
		{
		case LEFT:
			checkMiddleLeft(listener);
			break;
		case MIDDLE:
			checkMiddleMiddle(listener);
			break;
		case RIGHT:
			checkMiddleRight(listener);
		}
	}
	
	private void checkBoardBottom(IGameListener listener, byte col)
	{
		switch(col)
		{
		case LEFT:
			checkBottomLeft(listener);
			break;
		case MIDDLE:
			checkBottomMiddle(listener);
			break;
		case RIGHT:
			checkBottomRight(listener);
		}
	}
	
	private void checkTopLeft(IGameListener listener)
	{
		if(board[TOP][MIDDLE] == player && board[TOP][RIGHT] == player)
			gameOver(listener, TOP, LEFT, TOP, MIDDLE, TOP, RIGHT);
		else
		if(board[MIDDLE][LEFT] == player && board[BOTTOM][LEFT] == player)
			gameOver(listener, TOP, LEFT, MIDDLE, LEFT, BOTTOM, LEFT);
		else
		if(board[MIDDLE][MIDDLE] == player && board[BOTTOM][RIGHT] == player)
			gameOver(listener, TOP, LEFT, MIDDLE, MIDDLE, BOTTOM, RIGHT);
	}
	
	private void checkTopMiddle(IGameListener listener)
	{
		if(board[TOP][LEFT] == player && board[TOP][RIGHT] == player)
			gameOver(listener, TOP, LEFT, TOP, MIDDLE, TOP, RIGHT);
		else
		if(board[MIDDLE][MIDDLE] == player && board[BOTTOM][MIDDLE] == player)
			gameOver(listener, TOP, MIDDLE, MIDDLE, MIDDLE, BOTTOM, MIDDLE);
	}
	
	private void checkTopRight(IGameListener listener)
	{
		if(board[TOP][LEFT] == player && board[TOP][MIDDLE] == player)
			gameOver(listener, TOP, LEFT, TOP, MIDDLE, TOP, RIGHT);
		else
		if(board[MIDDLE][RIGHT] == player && board[BOTTOM][RIGHT] == player)
			gameOver(listener, TOP, RIGHT, MIDDLE, RIGHT, BOTTOM, RIGHT);
		else
		if(board[MIDDLE][MIDDLE] == player && board[BOTTOM][LEFT] == player)
			gameOver(listener, TOP, RIGHT, MIDDLE, MIDDLE, BOTTOM, LEFT);
	}
	
	private void checkMiddleLeft(IGameListener listener)
	{
		if(board[TOP][LEFT] == player && board[BOTTOM][LEFT] == player)
			gameOver(listener, TOP, LEFT, MIDDLE, LEFT, BOTTOM , LEFT);
		else
		if(board[MIDDLE][MIDDLE] == player && board[MIDDLE][RIGHT] == player)
			gameOver(listener, MIDDLE, LEFT, MIDDLE, MIDDLE, MIDDLE, RIGHT);
	}
	
	private void checkMiddleMiddle(IGameListener listener)
	{
		if(board[TOP][MIDDLE] == player && board[BOTTOM][MIDDLE] == player)
			gameOver(listener, TOP, MIDDLE, MIDDLE, MIDDLE, BOTTOM, MIDDLE);
		else
		if(board[MIDDLE][LEFT] == player && board[MIDDLE][RIGHT] == player)
			gameOver(listener, MIDDLE, LEFT, MIDDLE, MIDDLE, MIDDLE, RIGHT);
		else
		if(board[TOP][LEFT] == player && board[BOTTOM][RIGHT] == player)
			gameOver(listener, TOP, LEFT, MIDDLE, MIDDLE, BOTTOM, RIGHT);
		else
		if(board[TOP][RIGHT] == player && board[BOTTOM][LEFT] == player)
			gameOver(listener, TOP, RIGHT, MIDDLE, MIDDLE, BOTTOM, LEFT);
	}
	
	private void checkMiddleRight(IGameListener listener)
	{
		if(board[TOP][RIGHT] == player && board[BOTTOM][RIGHT] == player)
			gameOver(listener, TOP, RIGHT, MIDDLE, RIGHT, BOTTOM, RIGHT);
		else
		if(board[MIDDLE][LEFT] == player && board[MIDDLE][MIDDLE] == player)
			gameOver(listener, MIDDLE, LEFT, MIDDLE, MIDDLE, MIDDLE, RIGHT);
	}
	
	private void checkBottomLeft(IGameListener listener)
	{
		if(board[BOTTOM][MIDDLE] == player && board[BOTTOM][RIGHT] == player)
			gameOver(listener, BOTTOM, LEFT, BOTTOM, MIDDLE, BOTTOM, RIGHT);
		else
		if(board[TOP][LEFT] == player && board[MIDDLE][LEFT] == player)
			gameOver(listener, TOP, LEFT, MIDDLE, LEFT, BOTTOM, LEFT);
		else
		if(board[MIDDLE][MIDDLE] == player && board[TOP][RIGHT] == player)
			gameOver(listener, TOP, RIGHT, MIDDLE, MIDDLE, BOTTOM, LEFT);
	}
	
	private void checkBottomMiddle(IGameListener listener)
	{
		if(board[BOTTOM][LEFT] == player && board[BOTTOM][RIGHT] == player)
			gameOver(listener, BOTTOM, LEFT, BOTTOM, MIDDLE, BOTTOM, RIGHT);
		else
		if(board[MIDDLE][MIDDLE] == player && board[TOP][MIDDLE] == player)
			gameOver(listener, TOP, MIDDLE, MIDDLE, MIDDLE, BOTTOM, MIDDLE);
	}
	
	private void checkBottomRight(IGameListener listener)
	{
		if(board[BOTTOM][MIDDLE] == player && board[BOTTOM][LEFT] == player)
			gameOver(listener, BOTTOM, LEFT, BOTTOM, MIDDLE, BOTTOM, RIGHT);
		else
		if(board[MIDDLE][RIGHT] == player && board[TOP][RIGHT] == player)
			gameOver(listener, TOP, RIGHT, MIDDLE, RIGHT, BOTTOM, RIGHT);
		else
		if(board[MIDDLE][MIDDLE] == player && board[TOP][LEFT] == player)
			gameOver(listener, TOP, LEFT, MIDDLE, MIDDLE, BOTTOM, RIGHT);
	}
	
	private void gameOver(IGameListener listener, byte r1, byte c1, byte r2, byte c2, byte r3, byte c3)
	{
		state = OVER;
		if(P1 == player)
			listener.gameWonByPlayerOne(r1, c1, r2, c2, r3, c3);
		else
			listener.gameWonByPlayerTwo(r1, c1, r2, c2, r3, c3);
	}
	
	private void switchPlayer()
	{
		player = P1 == player ? P2 : P1;
	}
}
