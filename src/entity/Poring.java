package entity;

import input.KeyInput;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.util.Random;

import util.ResourceLoader;
import enums.Direction;
import enums.MobState;

/**
 * <strong>Project:</strong> CataclysmicBattles <br>
 * 
 * <strong>Class:</strong> Player
 * 
 * @author <a href = "http://youtube.com/BossLetsPlays"> BossLetsPlays</a>
 * 
 */
public class Poring extends Mob
{
	int newXOffset = 150;

	public Poring(int x, int y)
	{
		super(x, y);
	}

	public Poring(int x, int y, Mob target)
	{
		super(x, y);
		this.target = target;
	}

	public void init()
	{
		super.init();
		animationMap.put(mobState.STANDING, ResourceLoader.poringAnimations.get(MobState.STANDING));
		animationMap.put(mobState.WALKRIGHT, ResourceLoader.poringAnimations.get(MobState.WALKRIGHT));
		animationMap.put(mobState.WALKLEFT, ResourceLoader.poringAnimations.get(MobState.WALKLEFT));
		animationMap.put(mobState.DUCKING, ResourceLoader.poringAnimations.get(MobState.DUCKING));
	}

	@Override
	public void tick()
	{
		if (target != null)
		{
			if (velX == 0)
			{
				if (new Random().nextInt(100) == 1)
				{			
					newXOffset = 100 + new Random().nextInt(300);
				}
			}
			
			int xOffset = 0;
			if (target.getFacing() == Direction.LEFT)
			{
				xOffset = newXOffset;
			}
			else
			{
				xOffset = - newXOffset;
			}
			if (!(Math.abs((target.getX() + xOffset) - this.getX()) < 3))
			{
				if(target.getMoving())
					newXOffset = 150;
				if ((target.getX() + xOffset) < this.getX())
				{
					velX = -3;
				}
				if ((target.getX() + xOffset) > this.getX())
				{
					velX = 3;
				}
			}
			else
			{
			//	this.mobDirection = target.getFacing();
			}
		}
		ducking = false;
		super.tick();
	}

	@Override
	public void render(Graphics g)
	{
		super.render(g);
		g.setColor(new Color(20, 20, 100));
		g.setFont(new Font("Arial", Font.BOLD, 18));
		g.drawString("Pet Poring", x - 35, y - 100);
	}

	public void render(int xx, int yy, Graphics g)
	{
		super.render(xx, yy, g);
		g.setColor(new Color(20, 20, 100));
		g.setFont(new Font("Arial", Font.BOLD, 18));
		g.drawString("Pet Poring", x - 35, y - 100);
	}
}
