import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.JPanel;
import javax.swing.Timer;

import com.leapmotion.leap.CircleGesture;
import com.leapmotion.leap.Controller;
import com.leapmotion.leap.KeyTapGesture;
import com.leapmotion.leap.ScreenTapGesture;
import com.leapmotion.leap.SwipeGesture;

public class GamePanel extends JPanel implements ActionListener, Runnable
{
	private static final long serialVersionUID = 1L;

	private Graphics g;

	private int width;
	private int height;

	private boolean gameOver = false;

	private BufferedImage background;
	private BufferedImage hoverBoard;

	private Character player;

	private ArrayList<Character> characters = new ArrayList<Character>();

	private static ArrayList<Projectile> projectiles = new ArrayList<Projectile>();

	static Timer timer;
	static Timer leapTimer;

	KeyListener ArrowKeyListener = new KeyListener()
	{
		public void keyPressed(KeyEvent e)
		{    
			if(!gameOver)
			{
				if(e.getKeyCode() == KeyEvent.VK_W)
				{	
					player.changeVY(-15);
				}
				if(e.getKeyCode() == KeyEvent.VK_S)
				{ 
					player.changeVY(15);
				}
				if(e.getKeyCode() == KeyEvent.VK_A)
				{
					player.changeVX(-15);

					if(player.getDirection().equals("right"))
					{
						player.setDirection("left");
					}
				}
				if(e.getKeyCode() == KeyEvent.VK_D)
				{
					player.changeVX(15);

					if(player.getDirection().equals("left"))
					{
						player.setDirection("right");
					}
				}
			}
		}

		@Override
		public void keyReleased(KeyEvent arg0) 
		{

		}

		@Override
		public void keyTyped(KeyEvent arg0) 
		{

		}
	};

	Controller leapMotion = new Controller();
	LeapListener listener = new LeapListener();

	public GamePanel(int x, int y)
	{	
		leapMotion.addListener(listener);

		width = x;
		height = y;

		this.setPreferredSize(new Dimension(width,height));

		importImages();

		this.setFocusable(true);

		this.addKeyListener(ArrowKeyListener);
	}

	public void actionPerformed(ActionEvent e) 
	{   
		this.requestFocus();
		
		for(Character c : characters)
		{
			c.step();
		}

		for(Projectile p : projectiles)
		{
			p.calculateLocation();
		}

		this.update();
		this.repaint(); 
	}

	public void run()
	{	
		this.initialize();

		player = new Character(100,100);

		characters.add(player);

		timer = new Timer(50, this);
		timer.setInitialDelay(0);

		timer.start();

		
		
		leapTimer = new Timer(1, new ActionListener()
		{
			public void actionPerformed(ActionEvent e) 
			{
				if(leapMotion.isConnected())
				{
					try
					{
						String gestureType = listener.getGestureType();

						if(gestureType.equals("CIRCLE"))
						{
							System.out.println("Shooting");
							player.setState("Shooting");
						}
					} 
					catch (Exception f)
					{
						f.printStackTrace();
					}
				}
			}
		}	
				);


		leapTimer.setInitialDelay(0);

		leapTimer.start();
		
		
	}

	public void initialize()
	{ 
		g = this.getGraphics();

		this.paint(g);
	}

	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);

		g.drawImage(background, 0, 0, null);

		for(Character c : characters)
		{
			g.drawImage(c.getSprite(), c.getX(), c.getY(), null);

			if(c.getDirection().equals("left"))
			{
				g.drawImage(hoverBoard, c.getX() - 30, c.getY() + c.getHeight(), null);
			}
			else
			{
				g.drawImage(hoverBoard, c.getX() - 50, c.getY() + c.getHeight(), null);
			}
		}

		g.setColor(new Color((int)(Math.random() * 256), (int)(Math.random() * 256), (int)(Math.random() * 256)));

		for(Projectile p : projectiles)
		{
			g.fillOval(p.getX(), p.getY(), p.getWidth(), p.getHeight());
		}
	}

	public static void addProjectile(Projectile p)
	{
		projectiles.add(p);
	}

	public void update()
	{
	}

	public void importImages()
	{
		try 
		{
			background = ImageIO.read(new File("Images/background1.png"));  //import the background
			hoverBoard = ImageIO.read(new File("Images/hoverBoard.png"));  //import the hoverboard sprite
		}
		catch (IOException e) 
		{
			System.out.println("Error loading background");
			e.printStackTrace();
		}
	}
}
