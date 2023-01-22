package entity;

import input.KeyInput;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;

import util.ResourceLoader;
import enums.MobState;

/**
 * <strong>Project:</strong> CataclysmicBattles <br>
 * 
 * <strong>Class:</strong> Player
 * 
 * @author <a href = "http://youtube.com/BossLetsPlays"> BossLetsPlays</a>
 * 
 */
public class Player extends Mob
{
	public Player(int x, int y)
	{
		super(x,y);
	}
	
	public void init()
	{
		super.init();
		animationMap.put(mobState.STANDING, ResourceLoader.m_swordmanAnimations.get(MobState.STANDING));
		animationMap.put(mobState.WALKRIGHT, ResourceLoader.m_swordmanAnimations.get(MobState.WALKRIGHT));
		animationMap.put(mobState.WALKLEFT, ResourceLoader.m_swordmanAnimations.get(MobState.WALKLEFT));
		animationMap.put(mobState.DUCKING, ResourceLoader.m_swordmanAnimations.get(MobState.DUCKING));
	}

	@Override
	public void tick()
	{
		 if (KeyInput.getKey(KeyEvent.VK_RIGHT))
			 {
			 	velX = 3;
			 }
	     if (KeyInput.getKey(KeyEvent.VK_LEFT)) 
	    	 {
	    	 	velX = -3;
	    	 }
	     if (KeyInput.getKey(KeyEvent.VK_DOWN)) 
    	 {
    	 	ducking = true;
    	 }
	     else
	     {
	    	 ducking = false;
	     }
		super.tick();
	}
	
	@Override
	public void render(Graphics g)
	{
		super.render(g);
		g.setColor(new Color(20,20,100));
		g.setFont(new Font("Arial", Font.BOLD, 18));
		g.drawString("Player 1", x-35, y-200);
	}
	
	public void render(int xx, int yy, Graphics g)
	{
		super.render(xx, yy, g);
		g.setColor(new Color(20,20,100));
		g.setFont(new Font("Arial", Font.BOLD, 18));
		g.drawString("Player 1", x-35, y-200);
	}
}
