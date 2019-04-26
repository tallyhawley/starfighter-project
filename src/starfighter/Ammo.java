package starfighter;


import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import javax.imageio.ImageIO;

public class Ammo extends MovingThing
{
	private int speed;

	public Ammo()
	{
		this(0,0,1);
	}

	public Ammo(int x, int y)
	{
		this(x,y,1);
	}

	public Ammo(int x, int y, int s)
	{
		super(x,y,5,5);
		speed = s;
	}

	public void setSpeed(int s)
	{
	   speed = s;
	}

	public int getSpeed()
	{
	   return speed;
	}

	public void draw( Graphics window )
	{	
		window.setColor(Color.white);
		window.fillRect(getX(), getY(), 5, 5);
	}
	
	
	public void move( String direction )
	{
		switch (direction) {
		case "LEFT": setX(getX()-getSpeed()); break;
		case "RIGHT": setX(getX() + getSpeed()); break;
		case "UP": setY(getY() - getSpeed()); break;
		case "DOWN": setY(getY() + getSpeed()); break;
		}
	}

	public String toString()
	{
		return "";
	}
}
