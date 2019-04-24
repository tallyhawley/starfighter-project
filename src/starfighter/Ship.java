package starfighter;

import java.io.File;
import java.net.URL;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import javax.imageio.ImageIO;

public class Ship extends MovingThing
{
	private int speed;
	private Image image;

	public Ship()
	{
		this(350,300,50,50,5);
	}

	public Ship(int x, int y)
	{
	   super(x,y);
	   speed = 10;
	}

	public Ship(int x, int y, int s)
	{
	   super(x,y);
	   speed = s;
	}

	public Ship(int x, int y, int w, int h, int s)
	{
		super(x, y, w, h);
		speed=s;
		try
		{
			URL url = getClass().getResource("/images/ship.jpg");
			image = ImageIO.read(url);
		}
		catch(Exception e)
		{
			System.out.println(e);//feel free to do something here
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
		return super.toString() + " " + getSpeed();
	}
}