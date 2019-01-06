package tests;

import static org.junit.Assert.*;

import java.awt.Color;
import java.util.concurrent.TimeUnit;

import org.junit.Test;

import server.Bot;
import server.Game;
import table.Field;

public class BotTest {
	
	@Test
	public void botMoveTest() throws Exception {
		Bot bot = new Bot();
		Game game = new Game();
		game.add(bot);
		bot.addPlayer1();
		bot.starting(1);
		bot.start();
		game.start();
		try {
            TimeUnit.MILLISECONDS.sleep(700);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
		assertEquals(bot.pola[8].FieldColor, Color.WHITE);
		assertEquals(bot.pola[17].FieldColor, Color.RED);
		assertTrue(bot.pola[17].isOccupied());
		assertFalse(bot.pola[8].isOccupied());
	}
	
	@Test
	public void botUpdateTest() throws Exception {
		Bot bot = new Bot();
		bot.addPlayer1();
		bot.update("MOVE;8;17");
		assertEquals(bot.pola[8].FieldColor, Color.WHITE);
		assertEquals(bot.pola[17].FieldColor, Color.RED);
		assertTrue(bot.pola[17].isOccupied());
		assertFalse(bot.pola[8].isOccupied());
	}

	@Test 
	public void botIsNextToTest() throws Exception {
		Bot bot = new Bot();
		Field f1 = new Field(Color.RED, 1, 5, 5);
		Field f2 = new Field(Color.RED, 1, 7, 5);
		assertTrue(bot.isNextTo(f1, f2));
	}
	
	@Test
	public void botAddRedTest() throws Exception {
		Bot bot = new Bot();
		bot.addPlayer(1);
		for (int z=1; z<=10; z++) {
			assertEquals(bot.pola[z].FieldColor, Color.RED);
		}
	}
	
	@Test
	public void botColorTest() throws Exception {
		Bot bot = new Bot();
		bot.starting(1);
		assertEquals(bot.playerColor, Color.RED);
	}
	
	@Test
	public void botXYTest() throws Exception {
		Bot bot = new Bot();
		bot.starting(1);
		assertEquals(bot.targetX, 12);
		assertEquals(bot.targetY, 16);
	}
	
	@Test
	public void botMoveChangeTest() throws Exception {
		Bot bot = new Bot();
		bot.yourMove();
		assertEquals(bot.canMove, true);
	}
	
	@Test
	public void botOccupiedTest() throws Exception {
		Bot bot = new Bot();
		bot.addPlayer(1);
		for (int z=1; z<=10; z++) {
			assertTrue(bot.pola[z].isOccupied());
		}
	}
}
