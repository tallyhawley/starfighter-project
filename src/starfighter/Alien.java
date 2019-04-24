package starfighter;

import java.io.File;
import java.net.URL;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import javax.imageio.ImageIO;

public class Alien extends MovingThing
{
	private int speed;
	private Image image;

	public Alien()
	{
		this(0,0,40,40,4);
	}

	public Alien(int x, int y)
	{
		this(x,y,40,40,1);
	}

	public Alien(int x, int y, int s)
	{
		this(x,y,40,40,s);
	}

	public Alien(int x, int y, int w, int h, int s)
	{
		super(x, y, w,h);
		speed=s;
		try
		{
			URL url = getClass().getResource("/images/alien.jpg");
			image = ImageIO.read(url);
		}
		catch(Exception e)
		{
			//feel free to do something here
		}
	}

	public void setSpeed(int s)
	{
	   speed = s;
	}

	public int getSpeed()
	{
	   return speed;
	}

   public void move(String direction)
	{
	   switch (direction) {
		case "LEFT": setX(getX()-getSpeed()); break;
		case "RIGHT": setX(getX() + getSpeed()); break;
		case "UP": setY(getY() - getSpeed()); break;
		case "DOWN": setY(getY() + getSpeed()); break;
		}
	}

	public void draw( Graphics window )
	{
		window.drawImage(image,getX(),getY(),getWidth(),getHeight(),null);
	}

	public String toString()
	{
		return "";
	}
}