package entity;

import java.awt.Graphics;
import java.util.HashMap;

import util.ResourceLoader;
import util.SkeletonResourceLoader;
import anim.SkeletonAnimation;
import enums.Direction;
import enums.MobState;

public abstract class Mob extends Entity
{

	protected float velX;
	protected float velY;
	protected int maxVelY = 7;
	protected float gravity = 0.5f;
	protected MobState mobState;
	protected Direction mobDirection;
	protected int direction;
	protected boolean ducking;
	protected Mob target;
	protected HashMap<MobState, SkeletonAnimation> animationMap;

	public Mob(int x, int y)
	{
		super(x, y);
		target = null;
		mobState = MobState.STANDING;
		mobDirection = Direction.RIGHT;
		ducking = true;
		init();
	}

	public void init()
	{
		animationMap = new HashMap<MobState, SkeletonAnimation>();
	}

	@Override
	public void tick()
	{
		mobState = mobState.STANDING;
		if (velX > 0)
		{
			mobState = mobState.WALKRIGHT;
			mobDirection = Direction.RIGHT;
		}
		if (velX < 0)
		{
			mobState = mobState.WALKLEFT;
			mobDirection = Direction.LEFT;
		}
		
		if (ducking)
		{
			mobState = mobState.DUCKING;
			velX = 0;
			velY = 0;
		}
		else
		{
			animationMap.get(MobState.DUCKING).play();
		}

		x += velX;
		y += velY;

		velX = 0;
		velY = 0;

		switch (mobState)
		{
		case STANDING:
			animationMap.get(mobState.STANDING).tick();
			break;
		case WALKRIGHT:
			animationMap.get(mobState.WALKRIGHT).tick();
			break;
		case WALKLEFT:
			animationMap.get(mobState.WALKLEFT).tick();
		case DUCKING:
			animationMap.get(mobState.DUCKING).tick();
			break;
		default:
			break;
		}
		
		if (mobDirection.equals(Direction.RIGHT)) direction = 1;
		else direction = 0;
	}
	
	public Direction getFacing()
	{
		return mobDirection;
	}
	
	public boolean getMoving()
	{
		if ((mobState == MobState.WALKLEFT  || mobState == MobState.WALKRIGHT))
			return true;
		return false;
	}

	@Override
	public void render(Graphics g)
	{
		switch (mobState)
		{
		case STANDING:
			animationMap.get(mobState.STANDING).render(x, y, g, direction);
			break;
		case WALKRIGHT:
			animationMap.get(mobState.WALKRIGHT).render(x, y, g, direction);
			break;
		case WALKLEFT:
			animationMap.get(mobState.WALKLEFT).render(x, y, g, direction);
			break;
		case DUCKING:
			animationMap.get(mobState.DUCKING).render(x, y, g, direction);
			break;
		default:
			break;

		}
	}
	
	public void render(int xx, int yy, Graphics g)
	{
		switch (mobState)
		{
		case STANDING:
			animationMap.get(mobState.STANDING).render(x+xx, y+yy, g, direction);
			break;
		case WALKRIGHT:
			animationMap.get(mobState.WALKRIGHT).render(x+xx, y+yy, g, direction);
			break;
		case WALKLEFT:
			animationMap.get(mobState.WALKLEFT).render(x+xx, y+yy, g, direction);
			break;
		case DUCKING:
			animationMap.get(mobState.DUCKING).render(x+xx, y+yy, g, direction);
			break;
		default:
			break;

		}
	}
}
