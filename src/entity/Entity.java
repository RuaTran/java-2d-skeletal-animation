package entity;

import java.awt.Graphics;
import java.awt.Rectangle;

public abstract class Entity
{

	protected int x;
	protected int y;

	public Entity(int x, int y)
	{
		this.x = x;
		this.y = y;
	}

	public abstract void tick();

	public abstract void render(Graphics g);

	public int getX()
	{
		return x;
	}

	public int getY()
	{
		return y;
	}

}
