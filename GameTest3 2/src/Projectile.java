import java.awt.Rectangle;


public class Projectile 
{
	private int x = 0;
	private int y = 0;
	
	private int width;
	private int height;
	
	private Rectangle bounds;
	
	private double vX = -50;
	private double vY = 5;
	
	int damage = 10;
	
	private Character parent;
	
	public Projectile (int xCoord, int yCoord, Character c)
	{
		x = xCoord;
		y = yCoord;
		
		width = 15;
		height = 15;
		
		bounds = new Rectangle(x, y, width, height);
		
		parent = c;
	}
	
	public Projectile (int xCoord, int yCoord, int w, int h, Character c)
	{
		x = xCoord;
		y = yCoord;
		
		width = w;
		height = h;
		
		bounds = new Rectangle(x, y, width, height);
		
		parent = c;
	}
	
	public void setParent(Character c)
	{
		parent = c;
	}
	
	public Character getParent()
	{
		return parent;
	}
	
	public int getX() 
	{
		return x;
	}

	public void setX(int x) 
	{
		this.x = x;
	}

	public int getY() 
	{
		return y;
	}

	public void setY(int y) 
	{
		this.y = y;
	}

	public Rectangle getBounds() 
	{
		bounds = new Rectangle(x, y, width, height);
		return bounds;
	}

	public void setBounds(Rectangle newBounds) 
	{
		this.bounds = newBounds;
	}
	
	public int getWidth()
	{
		return width;
	}
	
	public int getHeight()
	{
		return height;
	}
	
	public double getvX() 
	{
		return vX;
	}

	public void setvX(double vX) 
	{
		this.vX = vX;
	}

	public double getvY() 
	{
		return vY;
	}

	public void setvY(double vY) 
	{
		this.vY = vY;
	}

	public int getDamage()
	{
		return damage;
	}
	
	public void calculateLocation()
	{
		x += vX;
		y += vY;
		
		vY *= 1.1;
	}
}
