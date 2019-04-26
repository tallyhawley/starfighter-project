package starfighter;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Canvas;
import java.awt.event.ActionEvent;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;
import static java.lang.Character.*;
import java.awt.image.BufferedImage;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class OuterSpace extends Canvas implements KeyListener, Runnable
{
	private Ship ship;
//	private Alien alienOne;
//	private Alien alienTwo;
	boolean canShoot;
	boolean lost;

	// uncomment once you are ready for this part
	
    private AlienHorde horde;
	private Bullets shots;


	private boolean[] keys;
	private BufferedImage back;

	public OuterSpace()
	{
		setBackground(Color.black);

		keys = new boolean[5];

		//instantiate other instance variables
		//Ship, Alien
		ship = new Ship();
		horde = new AlienHorde(30);
		shots = new Bullets();
		canShoot = true;
		lost = false;

		this.addKeyListener(this);
		new Thread(this).start();

		setVisible(true);
	}

   public void update(Graphics window)
   {
	   paint(window);
   }

	public void paint( Graphics window )
	{	
		//set up the double buffering to make the game animation nice and smooth
		Graphics2D twoDGraph = (Graphics2D)window;

		//take a snap shop of the current screen and same it as an image
		//that is the exact same width and height as the current screen
		if(back==null)
		   back = (BufferedImage)(createImage(getWidth(),getHeight()));

		//create a graphics reference to the back ground image
		//we will draw all changes on the background image
		Graphics graphToBack = back.createGraphics();

		graphToBack.setColor(Color.BLUE);
		graphToBack.drawString("StarFighter ", 25, 50 );
		graphToBack.setColor(Color.BLACK);
		graphToBack.fillRect(0,0,800,600);
		
		// actual game play
		if(!lost) {
		
			ship.draw(graphToBack);
			horde.drawEmAll(graphToBack);
	
			//add code to move Ship, Alien, etc.
			if(keys[0]) ship.move("LEFT");
			if(keys[1]) ship.move("RIGHT");
			if(keys[2]) ship.move("UP");
			if(keys[3]) ship.move("DOWN");
			horde.moveEmAll();
			
			shots.drawEmAll(graphToBack);
			shots.moveEmAll();
	
			//add in collision detection to see if Bullets hit the Aliens and if Bullets hit the Ship
			horde.removeDeadOnes(shots.getList());
			
			//check to see if aliens have hit the bottom
			for(Alien a : horde.getAliens()) {
				if(a.getY() >= getHeight()-40) {
					lost = true;
				}
			}
			
			//check to see if ship has been hit by aliens or bullets
			for(Alien a : horde.getAliens()) {
				if(a.getX() >= ship.getX() && a.getX() <= ship.getX() + ship.getWidth()) {
					if(a.getY() + a.getHeight() >=ship.getY()) {
						lost = true;
					}
				}
			}
			
			for(Ammo a : shots.getList()) {
				if(a.getX() >= ship.getX() && a.getX() <= ship.getX() + ship.getWidth()) {
					if(a.getY() + a.getHeight() >=ship.getY()) {
						lost = true;
					}
				}
			}
		}
		else {
			graphToBack.setColor(Color.red);
			
			graphToBack.drawString("YOU LOSE!", 375, 200);
		}
		twoDGraph.drawImage(back, null, 0, 0);
		
	}


	public void keyPressed(KeyEvent e)
	{
		if (e.getKeyCode() == KeyEvent.VK_A)
		{
			keys[0] = true;
		}
		if (e.getKeyCode() == KeyEvent.VK_D)
		{
			keys[1] = true;
		}
		if (e.getKeyCode() == KeyEvent.VK_W)
		{
			keys[2] = true;
		}
		if (e.getKeyCode() == KeyEvent.VK_S)
		{
			keys[3] = true;
		}
		if (e.getKeyCode() == KeyEvent.VK_SPACE)
		{
			if(canShoot) {
				keys[4] = true;
				shots.add(new Ammo(ship.getX()+25,ship.getY()-5, 1));
			}
			canShoot = false;
		}
		repaint();
	}

	public void keyReleased(KeyEvent e)
	{
		if (e.getKeyCode() == KeyEvent.VK_A)
		{
			keys[0] = false;
		}
		if (e.getKeyCode() == KeyEvent.VK_D)
		{
			keys[1] = false;
		}
		if (e.getKeyCode() == KeyEvent.VK_W)
		{
			keys[2] = false;
		}
		if (e.getKeyCode() == KeyEvent.VK_S)
		{
			keys[3] = false;
		}
		if (e.getKeyCode() == KeyEvent.VK_SPACE)
		{
			keys[4] = false;
			canShoot = true;
		}
		repaint();
	}

	public void keyTyped(KeyEvent e)
	{
      //no code needed here
	}

   public void run()
   {
   	try
   	{
   		while(true)
   		{
   		   Thread.currentThread().sleep(8);
            repaint();
         }
      }catch(Exception e)
      {
      }
  	}
}

