package starfighter;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import javax.imageio.ImageIO;
import java.util.ArrayList;
import java.util.List;

public class AlienHorde
{
	private List<Alien> aliens;

	public List<Alien> getAliens() {
		return aliens;
	}

	public AlienHorde(int size)
	{
		aliens = new ArrayList<Alien>();
		for(int i = 0; i<size; i++) {
			aliens.add(new Alien((int)(Math.random()*800)-40, (int)(Math.random()*100*size)-(100*size+100)));
		}
	}

	public void add(Alien al)
	{
		aliens.add(al);
	}

	public void drawEmAll( Graphics window )
	{
		for(Alien a : aliens) {
			a.draw(window);
		}
	}

	public void moveEmAll()
	{
		for(Alien a : aliens) {
			a.move("DOWN");
		}
	}

	public void removeDeadOnes(List<Ammo> shots)
	{
		List<Alien> removeAliens = new ArrayList<Alien>();
		List<Ammo> removeAmmo = new ArrayList<Ammo>();
		for(Alien a : aliens) {
			for(Ammo b : shots) {
				if(b.getX() >= a.getX() && b.getX() + 5 <= a.getX() + a.getWidth()) {
					if(b.getY() <= a.getY() + a.getHeight()) {
						removeAliens.add(a);
						removeAmmo.add(b);
						break;
					}
				}
			}
		}
		aliens.removeAll(removeAliens);
		shots.removeAll(removeAmmo);
	}

	public String toString()
	{
		return "";
	}
}
