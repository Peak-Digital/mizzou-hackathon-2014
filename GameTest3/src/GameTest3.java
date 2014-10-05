
import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JFrame;
import javax.swing.Timer;


public class GameTest3 
{
	private static BorderLayout mainLayout = new BorderLayout();
	
	private static JFrame mainWindow = new JFrame("GameTest3");

	private static GamePanel gamePanel;
	
	private static int width = 1200;
	private static int height = 600;
	
	public static void main(String[] args) 
	{	
		startGame();
	}
	
	public static void startGame()
	{
		mainWindow.setSize(width,height);
		mainWindow.setLocationRelativeTo(null);
		mainWindow.setBackground(Color.WHITE);
		mainWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainWindow.setLayout(mainLayout);
		
		gamePanel = new GamePanel(width, height);

		mainWindow.add(gamePanel, BorderLayout.CENTER);
		
		mainWindow.pack();
		mainWindow.setVisible(true);
		
		gamePanel.run();
	}
}
