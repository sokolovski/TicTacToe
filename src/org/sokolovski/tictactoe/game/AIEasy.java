package org.sokolovski.tictactoe.game;

class AIEasy
{
	private static final byte FIELDS_COUNT = 9;
	
	void makeMove(Game game, IGameListener listener)
	{
		byte spot_no = (byte) (System.currentTimeMillis() % FIELDS_COUNT);

		for(byte round = 0; round < FIELDS_COUNT; ++round)
			if(move(game, listener, (byte) ((spot_no + round) % FIELDS_COUNT)))
				break;
	}
	
	private boolean move(Game game, IGameListener listener, byte field)
	{
		switch(field)
		{
		case 0:
			return game.aiMove(listener, Game.TOP, Game.LEFT);
		case 1:
			return game.aiMove(listener, Game.TOP, Game.MIDDLE);
		case 2:
			return game.aiMove(listener, Game.TOP, Game.RIGHT);
		case 3:
			return game.aiMove(listener, Game.MIDDLE, Game.LEFT);
		case 4:
			return game.aiMove(listener, Game.MIDDLE, Game.MIDDLE);
		case 5:
			return game.aiMove(listener, Game.MIDDLE, Game.RIGHT);
		case 6:
			return game.aiMove(listener, Game.BOTTOM, Game.LEFT);
		case 7:
			return game.aiMove(listener, Game.BOTTOM, Game.MIDDLE);
		case 8:
			return game.aiMove(listener, Game.BOTTOM, Game.RIGHT);
		default: throw new Error("WTF");
		}
	}

}
