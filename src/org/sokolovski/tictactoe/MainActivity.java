package org.sokolovski.tictactoe;

import org.sokolovski.tictactoe.game.Game;
import org.sokolovski.tictactoe.game.IGameListener;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends Activity implements IGameListener
{
	private static final int[] FIELD_IDS = {R.id.a1, R.id.a2, R.id.a3, R.id.a4, R.id.a5, R.id.a6, R.id.a7, R.id.a8, R.id.a9};
	private static final String UNEXPECTED_ERROR = "Unexpected error occured. Please contact with the vendor.";
	private static final Game GAME = new Game();
	
	private static long total_time = 0;
	private static short clicks = 0;
	
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu)
	{
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	public void areaClicked(View view)
	{
		switch(view.getId())
		{
		case R.id.a1:
			mark(Game.TOP, Game.LEFT);
			break;
		case R.id.a2:
			mark(Game.TOP, Game.MIDDLE);
			break;
		case R.id.a3:
			mark(Game.TOP, Game.RIGHT);
			break;
		case R.id.a4:
			mark(Game.MIDDLE, Game.LEFT);
			break;
		case R.id.a5:
			mark(Game.MIDDLE, Game.MIDDLE);
			break;
		case R.id.a6:
			mark(Game.MIDDLE, Game.RIGHT);
			break;
		case R.id.a7:
			mark(Game.BOTTOM, Game.LEFT);
			break;
		case R.id.a8:
			mark(Game.BOTTOM, Game.MIDDLE);
			break;
		case R.id.a9:
			mark(Game.BOTTOM, Game.RIGHT);
			break;
		default:
			throw new Error(UNEXPECTED_ERROR);
		}
	}
	
	public void restart(View view)
	{
		GAME.restart();
		
		for(final int field_id : FIELD_IDS)
		{
			final TextView field = (TextView) findViewById(field_id);
			field.setText("Z");
			field.setTextColor(Color.BLACK);
		}
		
		final TextView game_over = (TextView) findViewById(R.id.game_over);
		game_over.setText("");
	}
	
	@Override
	public void markFieldPlayerOne(byte row, byte col)
	{
		getField(row, col).setText("X");
	}
	
	@Override
	public void markFieldPlayerTwo(byte row, byte col)
	{
		getField(row, col).setText("O");
	}
	
	@Override
	public void gameWonByPlayerOne(byte r1, byte c1, byte r2, byte c2, byte r3, byte c3)
	{
		getField(r1, c1).setTextColor(Color.GREEN);
		getField(r2, c2).setTextColor(Color.GREEN);
		getField(r3, c3).setTextColor(Color.GREEN);
		final TextView game_over = (TextView) findViewById(R.id.game_over);
		game_over.setText("Game Won By Player One");
	}
	
	@Override
	public void gameWonByPlayerTwo(byte r1, byte c1, byte r2, byte c2, byte r3, byte c3)
	{
		getField(r1, c1).setTextColor(Color.GREEN);
		getField(r2, c2).setTextColor(Color.GREEN);
		getField(r3, c3).setTextColor(Color.GREEN);
		final TextView game_over = (TextView) findViewById(R.id.game_over);
		game_over.setText("Game Won By Player Two");
	}
	
	private void mark(byte row, byte col)
	{
		final long start_time = System.currentTimeMillis();
		GAME.marked(this, row, col);
		final long end_time = System.currentTimeMillis();
		
		total_time = total_time + end_time - start_time;
		clicks += 1;
		final TextView avg = (TextView) findViewById(R.id.avg);
		avg.setText(String.valueOf(total_time / clicks));
	}
	
	private TextView getField(byte row, byte col)
	{
		switch(row)
		{
		case Game.TOP:
			switch(col)
			{
			case Game.LEFT:
				return (TextView) findViewById(R.id.a1);
			case Game.MIDDLE:
				return (TextView) findViewById(R.id.a2);
			case Game.RIGHT:
				return (TextView) findViewById(R.id.a3);
			}
		case Game.MIDDLE:
			switch(col)
			{
			case Game.LEFT:
				return (TextView) findViewById(R.id.a4);
			case Game.MIDDLE:
				return (TextView) findViewById(R.id.a5);
			case Game.RIGHT:
				return (TextView) findViewById(R.id.a6);
			}
		case Game.BOTTOM:
			switch(col)
			{
			case Game.LEFT:
				return (TextView) findViewById(R.id.a7);
			case Game.MIDDLE:
				return (TextView) findViewById(R.id.a8);
			case Game.RIGHT:
				return (TextView) findViewById(R.id.a9);
			}
		default:
			throw new Error(UNEXPECTED_ERROR);
		}
	}
}