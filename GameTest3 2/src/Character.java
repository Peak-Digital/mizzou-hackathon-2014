import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;


public class Character
{
	private int x;
	private int y;

	private int width;
	private int height;

	private double vX = 0;
	private double vY = 0;

	private Rectangle bounds;

	private String state = "Idle";

	private String direction = "left";

	private int timeSpentInState = 0;

	private String spritePath = "Images/character1Idle.png";

	private BufferedImage sprite;

	public Character(int xLoc, int yLoc)
	{
		x = xLoc;
		y = yLoc;

		this.importSprite();
	}

	public void step()
	{
		calculateLocation();

		if(state.equals("Idle"))
		{
			timeSpentInState++;

			if(timeSpentInState >= 10)
			{
				if(!(spritePath.equals("Images/character1Idle.png")))
				{
					spritePath = "Images/character1Idle.png";
					importSprite();
				}
			}


		}
		else if(state.equals("Shooting"))
		{	
			timeSpentInState++;

			spritePath = "Images/character1Shooting.png";
			importSprite();

			shoot();

			if(timeSpentInState > 5)
			{
				state = "Idle";
			}
		}
	}

	public void shoot()
	{
		Projectile p;

		if(direction.equals("right"))
		{
			p = new Projectile(x + 52, y + 38, this);
			p.setvX(p.getvX() * -1);
		}
		else
		{
			p = new Projectile(x + 25, y + 37, this);
		}

		GamePanel.addProjectile(p);
	}

	public void setState(String s)
	{
		timeSpentInState = 0;
		state = s;
	}

	public void calculateLocation()
	{
		this.setCoords((int)(x + vX), (int)(y + vY));

		if(x < 0)
		{
			this.setCoords(0, y);
		}
		else if(x > 1100)
		{
			this.setCoords(1100, y);
		}

		if(y < 0)
		{
			this.setCoords(x, 0);
		}
		else if(y > 600 - height)
		{
			this.setCoords(x, 600 - height);
		}

		if(vX > 0)
		{
			vX--;
		}
		else if(vX < 0)
		{
			vX++;
		}

		if(vY > 0)
		{
			vY--;
		}
		else if(vY < 0)
		{
			vY++;
		}
	}

	public Rectangle getBounds()
	{
		bounds = new Rectangle(x, y, width, height);
		return bounds;
	}

	public int getX()
	{
		return x;
	}

	public int getY()
	{
		return y;
	}

	public String getDirection()
	{
		return direction;
	}

	public void setDirection(String dir)
	{
		direction = dir;

		importSprite();
	}

	public int getHeight()
	{
		return height;
	}

	public int getWidth()
	{
		return width;
	}

	public void setCoords(int newX, int newY)
	{
		x = newX;
		y = newY;
	}

	public void changeVX(double change)
	{
		vX += change;
	}
	
	public double getVX()
	{
		return vX;
	}
	
	public double getVY()
	{
		return vY;
	}

	public void changeVY(double change)
	{
		vY += change;
	}

	public BufferedImage getSprite()
	{
		return sprite;
	}

	public void importSprite()
	{
		try 
		{
			if(direction.equals("right"))
			{
				spritePath = spritePath.substring(0, spritePath.length() - 4) + "Reversed.png";
			}

			sprite = ImageIO.read(new File(spritePath));  //import the sprite

			width = sprite.getWidth();
			height = sprite.getHeight();
		}
		catch (IOException e) 
		{
			System.out.println("Error loading file: " + spritePath);
			e.printStackTrace();
		}
	}
}
