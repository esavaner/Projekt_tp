package tests;

import static org.junit.Assert.*;

import java.awt.*;
import java.awt.AWTException;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;

import org.junit.Test;

import main.start.Client;
import main.start.GetIPWindow;
import server.ProjectServer;

public class GetIpTest {


	@Test
	public void testJoin() throws Exception {
		String text = " 192.168.42.184";
		StringSelection stringSelection = new StringSelection(text);
		Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
		clipboard.setContents(stringSelection, stringSelection);

		Robot bot = new Robot();
		GetIPWindow ipWindow = new GetIPWindow();
		ipWindow.setVisible();
		bot.mouseMove(720, 250);
		bot.mousePress(InputEvent.BUTTON1_MASK);
		try {
			Thread.sleep(700);
		}
		catch (InterruptedException e){}
		bot.mouseRelease(InputEvent.BUTTON1_MASK);
		bot.keyPress(KeyEvent.VK_CONTROL);
		bot.keyPress(KeyEvent.VK_V);
		bot.keyRelease(KeyEvent.VK_V);
		bot.keyRelease(KeyEvent.VK_CONTROL);
		bot.mouseMove(920, 250);
		bot.mousePress(InputEvent.BUTTON1_MASK);
		try {
			Thread.sleep(700);
		}
		catch (InterruptedException e){}
		bot.mouseRelease(InputEvent.BUTTON1_MASK);
		try {
			Thread.sleep(700);
		}
		catch (InterruptedException e){}
		assertEquals(ipWindow.getIp(), "192.168.42.184");
	}

}
