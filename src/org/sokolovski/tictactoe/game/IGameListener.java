package org.sokolovski.tictactoe.game;

public interface IGameListener
{
	void markFieldPlayerOne(byte row, byte col);
	void markFieldPlayerTwo(byte row, byte col);
	void gameWonByPlayerOne(byte r1, byte c1, byte r2, byte c2, byte r3, byte c3);
	void gameWonByPlayerTwo(byte r1, byte c1, byte r2, byte c2, byte r3, byte c3);
}
